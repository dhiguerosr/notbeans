/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.statements;

import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;

/**
 *
 * @author Dennis
 */
public class Break extends Node{

    @Override
    public Result genCode() {
        String endLabel = this.getExitLoopLabel();
        if(endLabel!=null){
            emite("goto "+ endLabel);
        }else{
            //break sin ciclo|case
        }
        return null;
    }
    
}
