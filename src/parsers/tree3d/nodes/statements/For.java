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
public class For extends Node {
    Stack<Node> nodes;
    Node assign;
    Node expression;
    Node increment;
    public For(Node assign, Node expression, Node increment, Stack<Node> nodes){
        this.assign = assign;
        this.expression = expression;
        this.increment = increment;
        this.nodes = nodes;
    }
    @Override
    public Result genCode() {
        
        String startLabel = genLabel();
        String insLabel = genLabel();
        String exitLabel = genLabel();
        
        setNextLoopLabel(startLabel);
        setExitLoopLabel(exitLabel);
        
        emite("P = P + " + SymbolTableManager.getCurrentContext().getSymbolTable().size());
        SymbolTableManager.openScope();
        assign.genCode();
        emiteLabel(startLabel);
        Result result = expression.genCode();
        emite("if "+result+" == 1 then goto "+insLabel);
        emite("goto " + exitLabel);
        emiteLabel(insLabel);
        
        while(!nodes.isEmpty()){
            nodes.pop().genCode();
        }
        
        increment.genCode();
        
        emite("goto "+startLabel);
        SymbolTableManager.removeContext();
        emiteLabel(exitLabel);
        emite("P = P - " + SymbolTableManager.getCurrentContext().getSymbolTable().size());
        
        removeExitLoopLabel();
        removeNextLoopLabel();
        return null;
    }
    
}
