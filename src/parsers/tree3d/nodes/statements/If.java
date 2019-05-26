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
public class If extends Node{
    Stack<Node> nodes;
    Node expression;
    private Node nextNode;
    private Stack<Node> elseNodes;
    
    public If(Node expression, Stack<Node> nodes){
        this.expression = expression;
        this.nodes = nodes;
    }
    @Override
    public Result genCode() {
        Result result = expression.genCode();
        String tLabel = genLabel();
        String fLabel = genLabel();
        String endLabel = genLabel();
        emite("if "+result+" == 1 then goto " + tLabel);
        emite("goto "+fLabel);
        emiteLabel(tLabel);
        emite("P = P + " + SymbolTableManager.getCurrentContext().getSymbolTable().size());
        SymbolTableManager.openScope();
        while(!nodes.isEmpty()){
            nodes.pop().genCode();
        }
        SymbolTableManager.removeContext();
        emite("P = P - " + SymbolTableManager.getCurrentContext().getSymbolTable().size());
        emite("goto "+endLabel);
        emiteLabel(fLabel);
        if(nextNode!=null){
            nextNode.genCode();
        }else if(elseNodes!=null){
             emite("P = P + " + SymbolTableManager.getCurrentContext().getSymbolTable().size());
            SymbolTableManager.openScope();
            while(!elseNodes.isEmpty()){
                elseNodes.pop().genCode();
            }
            SymbolTableManager.removeContext();
            emite("P = P - " + SymbolTableManager.getCurrentContext().getSymbolTable().size());
            emite("goto "+endLabel);
        }
        emiteLabel(endLabel);
        return null;
    }

    /**
     * @return the nextNode
     */
    public Node getNextNode() {
        return nextNode;
    }

    /**
     * @param nextNode the nextNode to set
     */
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * @return the elseNode
     */
    public Stack<Node> getElseNodes() {
        return elseNodes;
    }

    /**
     * @param elseNode the elseNode to set
     */
    public void setElseNodes(Stack<Node> elseNodes) {
        this.elseNodes = elseNodes;
    }
    
}
