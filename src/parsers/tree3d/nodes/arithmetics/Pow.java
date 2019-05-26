/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.arithmetics;

import parsers.frc.managers.Type;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;

/**
 *
 * @author Dennis
 */
public class Pow extends Node {
    Node arg1;
    Node arg2;
    
    public Pow(Node arg1,Node arg2){
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
    @Override
    public Result genCode() {
        Result res1 = arg1.genCode();
        Result res2 = arg2.genCode();
        Type t = res1.getType().implicitCast(res2.getType(),'^');
        String result = genTemp();
        emite(result +" = "+res1+" ^ "+res2);
        return new Result(result,t);
    }
    
}
