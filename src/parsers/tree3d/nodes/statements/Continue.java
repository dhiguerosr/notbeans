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
public class Continue extends Node {

    @Override
    public Result genCode() {
        String nextLabel = this.getNextLoopLabel();
        if(nextLabel!=null){
            emite("goto "+nextLabel);
        }else{
            //continue sin ciclo
        }
        return null;
    }
    
}
