/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.expressions;

import parsers.frc.managers.Type;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;

/**
 *
 * @author Dennis
 */
public class Cast extends Node{
    Node expression;
    Type type;
    Result result;
    public Cast(Result r, Type t){
        this.result = r;
        this.type = t;
    }
    public Cast(Node expression, Type t){
        this.expression = expression;
        this.type = t;
    }
    @Override
    public Result genCode() {
        Result r = (expression!=null)?expression.genCode():result;
        if(!type.equals(r.getType())){
            String temp = genTemp();
            emite(temp +" = " + r.getType().getName() +"_to_"+type.getName() + " "+r);
            return new Result(temp, type);
        }
        return r;
    }
    
}
