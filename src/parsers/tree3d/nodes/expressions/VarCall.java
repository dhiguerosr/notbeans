/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.expressions;

import parsers.frc.managers.Sym;
import parsers.frc.managers.SymbolTable;
import parsers.frc.managers.SymbolTableManager;
import parsers.frc.managers.Type;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;

/**
 *
 * @author Dennis
 */
public class VarCall extends Node{

    private Sym symbol;
    private String name;
    private Node next;
    private Type type;
    public  VarCall(String name){
        this.name = name;
    }
   
    
    public Result genCode(String temp,boolean reference,int dims){
        if(SymbolTableManager.getCurrentContext(true)==null)
            SymbolTableManager.setContext(SymbolTableManager.getCurrentContext(),true);
        setSymbol(SymbolTableManager.getSymbol(getName(), true));
        setType(getSymbol().getType());
        
        String result = genTemp();
        emite(result + " = "+ temp + " + " + getSymbol().getPosition());
        if(getNext()!=null || !reference){
            String pos = result;
            result = genTemp();
            emite(result + " = heap["+pos+"]");
        }
        if(getNext()!=null){
            if(getNext() instanceof VarCall){
                SymbolTableManager.setContext(getType(),true);
                Result r = getNext() instanceof ArrayItemCall?
                            ((ArrayItemCall)getNext()).genCode(result,reference):
                            ((VarCall)getNext()).genCode(result,reference,dims);
                result = r.getResult();
                SymbolTableManager.removeContext(true);
                type = r.getType();
            }else if(getNext() instanceof MethodCall){
                Result r = ((MethodCall)getNext()).genCode(type,result);
                result = r.getResult();
                type = r.getType();
            }
        }else{
            if(getSymbol().isArray()){
                //si es un array asigna numero de dimensiones
                getSymbol().setArrayDimCount(dims);
            }
        }
        return new Result(result,type);
    }
    
    public boolean isGlobal(){
        SymbolTable syms = SymbolTableManager.getCurrentContext();
        while(!syms.exist(name)){
            syms = syms.getParent();
        }
        return syms.getParent()==null;
    }
    public Result genCode(boolean reference,int dims) {
       /* if(SymbolTableManager.getCurrentContext(true)==null)
            SymbolTableManager.setContext(SymbolTableManager.getCurrentContext(),true);*/
        setSymbol(SymbolTableManager.getSymbol(getName()));
        setType(getSymbol().getType());
        SymbolTable syms = SymbolTableManager.getCurrentContext();
        String temp = genTemp();
        //emite(temp+" = P");
        int scopeSize = 0;
        while(!syms.exist(symbol.getName())){
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
            if(next!=null || !reference){
                String pos = result;
                result = genTemp();
                emite(result + " = stack[" + pos+ "]");
                
            }
            if(getNext()!=null){
                if(getNext() instanceof VarCall){
                    SymbolTableManager.setContext(getType(),true);
                    Result r = getNext() instanceof ArrayItemCall?
                            ((ArrayItemCall)getNext()).genCode(result,reference):
                            ((VarCall)getNext()).genCode(result,reference,dims);
                    result = r.getResult();
                    SymbolTableManager.removeContext(true);
                    type = r.getType();
                }else if(getNext() instanceof MethodCall){
                Result r = ((MethodCall)getNext()).genCode(type,result);
                    result = r.getResult();
                    type = r.getType();
                }
            }else{
                if(getSymbol().isArray()){
                    //si es un array asigna numero de dimensiones
                    getSymbol().setArrayDimCount(dims);
                }
            }
            return new Result(result,type);
        }else{
            VarCall v = new VarCall("this");
            v.setNext(this);
            return  v.genCode(reference,dims);
        }
    }
    
    @Override
    public Result genCode() {
        return genCode(false,0);
    }
    
    public Result genCode(String temp){
        return genCode(temp,false,0);
    }
    
    public Result genCode(Boolean reference){
        return genCode(reference,0);
    }
    
    public Result genCode(String temp, Boolean reference){
        return genCode(temp,reference,0);
    }
    
    
  
     
    /**
     * @return the symbol
     */
    public Sym getSymbol() {
        return symbol;
    }

    /**
     * @param symbol the symbol to set
     */
    public void setSymbol(Sym symbol) {
        this.symbol = symbol;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the next
     */
    public Node getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

   
    
}
