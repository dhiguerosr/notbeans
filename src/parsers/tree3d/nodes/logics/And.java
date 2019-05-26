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
public class And extends Node{
    Node arg1;
    Node arg2;
    public And(Node arg1,Node arg2){
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
    @Override
    public Result genCode() {
       
        Result res1 = arg1.genCode();
        Result res2 = arg2.genCode();
        Type type = SymbolTableManager.getType("bool");
        
        String label1 = genLabel();
        String label2 = genLabel();
        String label3 = genLabel();
        String label4 = genLabel();
        
        String result = genTemp();
        
        emite("if "+res1+ " == 1 then goto "+ label1);
        emite("goto "+label3);
        emiteLabel(label1);
        emite("if "+res2+ " == 1 then goto " +label2);
        emite("goto "+label3);
        emiteLabel(label2);
        emite(result+" = 1");
        emite("goto " +label4);
        emiteLabel(label3);
        emite(result+" = 0");
        emiteLabel(label4);
        return new Result(result,type);
    }
    
}
