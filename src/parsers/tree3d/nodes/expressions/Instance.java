/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.expressions;

import java.util.ArrayList;
import java.util.Stack;
import parsers.frc.managers.Sym;
import parsers.frc.managers.SymbolTableManager;
import parsers.frc.managers.Type;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;

/**
 *
 * @author Dennis
 */
public class Instance extends Node {
    Stack<Node> args;
    String name;
    Sym method;
    int ccSize; //current context size
    public Instance(String name,Stack<Node> args){
        this.name = name;
        this.args = args;
    }
    
    @Override
    public Result genCode() {
        Type type = SymbolTableManager.getType(name);
        ccSize = SymbolTableManager.getCurrentContext().getSymbolTable().size();
        SymbolTableManager.setContext(type,true); //contexto auxiliar
        ArrayList<String> results = new ArrayList<>();
        while(!args.isEmpty()){
            Node n =args.pop();
            Result result = n.genCode();
            name += "_" + result.getType().getName();
            results.add(result.getResult());
        }
        name += "()";
        
        method = SymbolTableManager.getSymbol(name,true);
        //setea argumentos
        String pTemp = genTemp();
        emite(pTemp + " = P + "+ccSize);        //cambio virtual de ambito
        int index = 0;
        for(Sym s : method.getArguments()){
            String posTemp = genTemp();
            emite(posTemp + " = " + pTemp +" + " + s.getPosition());    //posicion de parametro en stack
            emite("stack["+posTemp +"] = " + results.get(index));       //envia valor de parametro
            index++;
        }
        //cambio de ambito
        emite("P = P + " + ccSize);
        emite("call " +type.getName() + "_"+  name);
        //si retorna valor lo envia en temporal y retorna
        String result = new String();
        if(method.getType()!=null){
            String retPos = genTemp();
            emite(retPos + " = P + " + method.getAtributes().get("return").getPosition());
            result = genTemp();
            emite(result + " = stack[" + retPos+"]");
        }
        emite("P = P - " + ccSize);
        
        SymbolTableManager.removeContext(true); //remueve contexto auxiliar
        return new Result(result,type);
    }
    
}
