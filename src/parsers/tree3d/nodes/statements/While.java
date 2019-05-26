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
public class While extends Node{
    Stack<Node> nodes;
    Node expression;
    public While(Node expression,Stack<Node> nodes){
        this.expression = expression;
        this.nodes = nodes;
    }
    
    @Override
    public Result genCode() {
        String startLabel = genLabel();
        String insLabel = genLabel();
        String exitLabel = genLabel();
        
        setNextLoopLabel(startLabel);
        setExitLoopLabel(exitLabel);
                
        emiteLabel(startLabel);
        Result result = expression.genCode();
        emite("if "+result+" == 1 then goto "+insLabel);
        emite("goto " + exitLabel);
        emiteLabel(insLabel);
        emite("P = P + " + SymbolTableManager.getCurrentContext().getSymbolTable().size());
        SymbolTableManager.openScope();
        while(!nodes.isEmpty()){
            nodes.pop().genCode();
        }
        SymbolTableManager.removeContext();
        int cSize = SymbolTableManager.getCurrentContext().getSymbolTable().size();
        emite("P = P - " +cSize );
        emite("goto "+ startLabel);
        emiteLabel(exitLabel);
        emite("P = P - " + cSize);
        
        removeExitLoopLabel();
        removeNextLoopLabel();
        return null;
    }
    
}
