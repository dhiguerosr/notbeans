/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.expressions;

import java.util.Stack;
import parsers.frc.managers.SymbolTable;
import parsers.frc.managers.SymbolTableManager;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;

/**
 *
 * @author Dennis
 */
public class ArrayItemCall extends VarCall {
    Stack<Node> indexs;
    public ArrayItemCall(String name,Stack<Node> indexs){
        super(name);
        this.indexs = indexs;
    }
    
    
    public Result genCode(String temp,boolean reference) {
        if(SymbolTableManager.getCurrentContext(true)==null)
            SymbolTableManager.setContext(SymbolTableManager.getCurrentContext(),true);
        setSymbol(SymbolTableManager.getSymbol(getName(), true));
        setType(getSymbol().getType().getSubtype());
        
        String result = genTemp();
        emite(result + " = "+ temp + " + " + getSymbol().getPosition());
        String pos = result;
        result = genTemp();
        emite(result + " = heap["+pos+"]");

        /**** INICIA DIRECCIONAMIENO DE ARREGLO ****/
            String tmp = genTemp();
            emite(tmp + " = heap[" + result + "]");
           // String base = tmp;
            String base = genTemp();
            emite(base + " = " + tmp + " + 1");
            /*agregado*/
            String t = genTemp();
            emite(t + " = " + base + " + " + result);
            base = t;
            /*agregado*/
            String index = genTemp();
            if(!indexs.isEmpty()){
                Result i1 = indexs.pop().genCode();
                emite(index + " = " + i1);
            }
            int dimCount = 0;
            while(!indexs.isEmpty()){
                Result i = indexs.pop().genCode();
                String dimPos = genTemp();
                emite(dimPos + " = " + result + " + " + (dimCount+1));
                String dimSize = genTemp();
                emite(dimSize + " = heap[" + dimPos + "]" );
                String _index = genTemp();
                emite(_index + " = " + index  + " * " + dimSize);
                index = _index;
                _index = genTemp();
                emite(_index + " = " + index + " + " + i);
                index = _index;
                dimCount++;
            }

            String itemPos = genTemp();
            emite(itemPos + " = " + index + " + " + base);
            result = itemPos;
            if(getNext()!=null || !reference){
                String _pos = result;
                result = genTemp();
                emite(result + " = heap["+_pos+"]");
            }

            /**** TERMINA DIRECCIONAMIENTO DE ARREGLO ****/
        
        if(getNext()!=null){
            if(getNext() instanceof VarCall){
                SymbolTableManager.setContext(getType(),true);
                Result r = getNext() instanceof ArrayItemCall?
                            ((ArrayItemCall)getNext()).genCode(result,reference):
                            ((VarCall)getNext()).genCode(result,reference);
                result = r.getResult();
                SymbolTableManager.removeContext(true);
                setType(r.getType());
            }else if(getNext() instanceof MethodCall){
                Result r = ((MethodCall)getNext()).genCode(getType(),result);
                result = r.getResult();
                setType(r.getType());
            }
        }
        return new Result(result,getType());
        
    }

    public Result genCode(boolean reference) {
        setSymbol(SymbolTableManager.getSymbol(getName()));
        setType(getSymbol().getType().getSubtype());
        SymbolTable syms = SymbolTableManager.getCurrentContext();
        String temp = genTemp();
        int scopeSize = 0;
        
        while(!syms.exist(getSymbol().getName())){
            if(syms.getParent()!=null){
                scopeSize +=syms.getParent().getSymbolTable().size();
                syms = syms.getParent();
            }
        }
        
        //String result = genTemp();
        if(syms.getParent()!=null){
            emite(temp+" = P - "+ scopeSize);
            String result = genTemp();
            emite(result +" = " + temp + " + " + getSymbol().getPosition());
            
            
            
            String pos = result;
            result = genTemp();
            emite(result + " = stack[" + pos+ "]");
            
            /**** INICIA DIRECCIONAMIENO DE ARREGLO ****/
            String tmp = genTemp();
            emite(tmp + " = heap[" + result + "]");
           // String base = tmp;
            
            String base = genTemp();
            emite(base + " = " + tmp + " + 1");
            /*agregado*/
            String t = genTemp();
            emite(t + " = " + base + " + " + result);
            base = t;
            /*agregado*/
            String index = genTemp();
            if(!indexs.isEmpty()){
                Result i1 = indexs.pop().genCode();
                emite(index + " = " + i1);
            }
            int dimCount = 0;
            while(!indexs.isEmpty()){
                Result i = indexs.pop().genCode();
                String dimPos = genTemp();
                emite(dimPos + " = " + result + " + " + (dimCount+1));
                String dimSize = genTemp();
                emite(dimSize + " = heap[" + dimPos + "]" );
                String _index = genTemp();
                emite(_index + " = " + index  + " * " + dimSize);
                index = _index;
                _index = genTemp();
                emite(_index + " = " + index + " + " + i);
                index = _index;
                dimCount++;
            }

            String itemPos = genTemp();
            emite(itemPos + " = " + index + " + " + base);
            result = itemPos;
            if(getNext()!=null || !reference){
                String _pos = result;
                result = genTemp();
                emite(result + " = heap["+_pos+"]");
            }

            /**** TERMINA DIRECCIONAMIENTO DE ARREGLO ****/
        
            if(getNext()!=null){
                if(getNext() instanceof VarCall){
                    SymbolTableManager.setContext(getType(),true);
                    Result r = getNext() instanceof ArrayItemCall?
                            ((ArrayItemCall)getNext()).genCode(result,reference):
                            ((VarCall)getNext()).genCode(result,reference);
                    result = r.getResult();
                    SymbolTableManager.removeContext(true);
                    setType(r.getType());
                }else if(getNext() instanceof MethodCall){
                    Result r = ((MethodCall)getNext()).genCode(getType(),result);
                    result = r.getResult();
                    setType(r.getType());
                }
            }
            return new Result(result,getType());
        }else{
            VarCall v = new VarCall("this");
            v.setNext(this);
            return  v.genCode(reference);
        }
    }
    
    @Override
    public Result genCode(String temp) {
        return genCode(temp,false); 
    }

    @Override
    public Result genCode() {
        return genCode(false); 
    }
    
            
}
