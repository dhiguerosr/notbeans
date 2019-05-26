/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.statements;

import java.util.Stack;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.expressions.Value;

/**
 *
 * @author Dennis
 */
public class Case{
    private Value value;
    private Stack<Node> nodes;
    private boolean _default;
    public Case(Stack<Node> nodes){
        this.nodes= nodes;
        _default = true;
    }
    public Case(Value v,Stack<Node> nodes){
        this.value = v;
        this.nodes = nodes;
    }

    /**
     * @return the value
     */
    public Value getValue() {
        return value;
    }

    /**
     * @return the nodes
     */
    public Stack<Node> getNodes() {
        return nodes;
    }

    /**
     * @return the _default
     */
    public boolean isDefault() {
        return _default;
    }
    
}
