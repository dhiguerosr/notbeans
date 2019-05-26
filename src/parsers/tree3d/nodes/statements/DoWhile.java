/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.statements;

import java.util.Stack;
import parsers.frc.managers.SymbolTableManager;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;

/**
 *
 * @author Dennis
 */
public class DoWhile extends Node{
    Stack<Node> nodes;
    Node expression;
    public DoWhile(Node expression,Stack<Node> nodes){
        this.expression = expression;
        this.nodes = nodes;
    }
    @Override
    public Result genCode() {
        String startLabel = genLabel();
        String exitLabel = genLabel();
        
        setNextLoopLabel(startLabel);
        setExitLoopLabel(exitLabel);
                
        emiteLabel(startLabel);
        emite("P = P + " + SymbolTableManager.getCurrentContext().getSymbolTable().size());
        SymbolTableManager.openScope();
        while(!nodes.isEmpty()){
            nodes.pop().genCode();
        }
        SymbolTableManager.removeContext();
        emite("P = P - " + SymbolTableManager.getCurrentContext().getSymbolTable().size());
        Result result = expression.genCode();
        emite("if "+result+" == 1 then goto "+startLabel);
        emite("goto " + exitLabel);
        emiteLabel(exitLabel);
        
        removeExitLoopLabel();
        removeNextLoopLabel();
        
        return null;
    }
    
}
