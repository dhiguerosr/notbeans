/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.statements;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;
import parsers.tree3d.nodes.logics.Comparation;

/**
 *
 * @author Dennis
 */
public class Switch extends Node{
    Stack<Case> cases;
    Node var;
    Queue<Case> defaults;
    public Switch(Node var, Stack<Case> cases){
        this.cases = cases;
        this.var = var;
        defaults = new LinkedList<>();
    }
    @Override
    public Result genCode() {
        String doLabel;
        String startLabel = null;
        String endLabel = genLabel();
        this.setExitLoopLabel(endLabel);
        
        while(!cases.isEmpty()){
            Case c = cases.pop();
            if(!c.isDefault()){
                if(startLabel!=null)
                    emiteLabel(startLabel);
                Result result = new Comparation(var,c.getValue(),"==").genCode();
                doLabel = genLabel();
                emite("if "+result+" == 1 then goto "+doLabel);
                startLabel = genLabel();
                emite("goto " +startLabel);
                emiteLabel(doLabel);
                while(!c.getNodes().isEmpty()){
                    c.getNodes().pop().genCode();
                }
                
            }else{
                defaults.add(c);
            }
        }
        
        if(!defaults.isEmpty()){
           Case c = defaults.poll();
           if(startLabel!=null)
               emiteLabel(startLabel);
           while(!c.getNodes().isEmpty()){
               c.getNodes().pop().genCode();
           }
        }
        this.removeExitLoopLabel();
        emiteLabel(endLabel);
        return null;
    }
    
}
