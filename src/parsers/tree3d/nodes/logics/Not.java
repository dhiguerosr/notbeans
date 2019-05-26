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
public class Not extends Node{
    Node arg1;
    public Not(Node arg1){
        this.arg1 = arg1;
    }
    @Override
    public Result genCode() {
        Result res1 = arg1.genCode();
        Type type = SymbolTableManager.getType("bool");
        
        String result = genTemp();
        String label1 = genLabel();
        String label2 = genLabel();
        String label3 = genLabel();
       
        emite("if " + res1 + " == 0  goto "+label1);
        emite("goto "+label2);
        emiteLabel(label2);
        emite("if  "+ res1 + " == 1  goto "+label3);
        emite("goto "+label1);
        emiteLabel(label3);
        emite(result + " = 0");
        emiteLabel(label1);
        emite(result + " = 1");
        return new Result(result,type);
    }
    
}
