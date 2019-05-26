/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.statements;

import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;
import parsers.tree3d.nodes.expressions.VarCall;

/**
 *
 * @author Dennis
 */
public class Return extends Node {
    Node expression;
    public Return(){}
    public Return(Node expression){
        this.expression = expression;
    }
    @Override
    public Result genCode() {
        if(expression!=null){
            new Assign(new VarCall("return"),expression).genCode();
        }
        emite("goto "+this.getExitMethodLabel());
        return null;
    }
    
}
