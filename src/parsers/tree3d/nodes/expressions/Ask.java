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
public class Ask extends Node {
    Node logicExp;
    Node arg1;
    Node arg2;
    public Ask(Node l, Node arg1, Node arg2){
        this.logicExp = l;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
    @Override
    public Result genCode() {
        Result l_res = logicExp.genCode();
        Result res1 = arg1.genCode();
        Result res2 = arg2.genCode();
        
        Type t = res1.getType();
        
        String label1 = genLabel();
        String label2 = genLabel();
        String label3 = genLabel();
        
        String result = genTemp();
        
        emite("if "+l_res + " == 1 then goto "+label1);
        emite("goto "+label2);
        emiteLabel(label1);
        emite(result +" = "+res1);
        emite("goto "+label3);
        emiteLabel(label2);
        emite(result +" = "+res2);
        emiteLabel(label3);
        return new Result(result,t);
    }
    
}
