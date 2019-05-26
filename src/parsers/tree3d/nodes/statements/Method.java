/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.statements;

import java.util.Stack;
import parsers.frc.managers.Sym;
import parsers.frc.managers.SymbolTableManager;
import parsers.frc.managers.Type;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;
import parsers.tree3d.nodes.expressions.VarCall;

/**
 *
 * @author Dennis
 */
public class Method extends Node{
    String name;
    Stack<Node> instructions;
    public Method(String id, String params,Stack<Node> instructions){
        this.name = id+params+"()";
        this.instructions = instructions;
    }
    
    @Override
    public Result genCode() {
        Type t = SymbolTableManager.getContext();
        emite("method " + t.getName()+"_"+name,"{\n");
        SymbolTableManager.setMethodContext(name);
        Sym method = SymbolTableManager.getSymbol(name);
        if(method.isConstructor()){
            Result posThis = new VarCall("this").genCode(true);
            emite("stack[" + posThis + "] = H");
            Result posReturn = new VarCall("return").genCode(true);
            emite("stack[" + posReturn + "] = H");
            emite("H = H + "+method.getType().getSize());
        }
        //genera instrucciones de metodo
        String exitLabel = genLabel();
        this.setExitMethodLabel(exitLabel);
        while(!instructions.isEmpty()){
            if(instructions.peek()!=null)
                instructions.pop().genCode();
            else
                instructions.pop();
        }
        SymbolTableManager.removeContext();
        emiteLabel(exitLabel);
        emite("\n}","\n");
        this.removeExitMethodLabel();
        return null;
    }
    
}
