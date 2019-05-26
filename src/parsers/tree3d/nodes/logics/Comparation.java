/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.logics;

import parsers.frc.managers.SymbolTableManager;
import parsers.frc.managers.Type;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;

/**
 *
 * @author Dennis
 */
public class Comparation extends Node{
    Node arg1;
    Node arg2;
    String operand;
    public Comparation(Node arg1,Node arg2,String operand){
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.operand = operand;
    }
    @Override
    public Result genCode() {
        
        Result res1 = arg1.genCode();
        Result res2 = arg2.genCode();
        Type type = SymbolTableManager.getType("bool");
        
        String result = genTemp();
        String label1 = genLabel();
        String label2 = genLabel();
        
        emite("if "+res1+ " "+operand+" "+ res2+" then goto "+ label1);
        emite(result +" = 0");
        emite("goto "+label2);
        emiteLabel(label1);
        emite(result +" = 1");
        emiteLabel(label2);
        return new Result(result,type);
    }
    
}
