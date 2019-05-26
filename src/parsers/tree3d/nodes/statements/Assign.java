/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.statements;

import java.util.Stack;
import parsers.frc.managers.Type;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;
import parsers.tree3d.nodes.expressions.ArrayItemCall;
import parsers.tree3d.nodes.expressions.Cast;
import parsers.tree3d.nodes.expressions.VarCall;

/**
 *
 * @author Dennis
 */
public class Assign extends Node{
    Stack<Node> vars;
    Node value;
    public Assign(Node var, Node value){
        vars = new Stack<>();
        vars.push(var);
        this.value = value;
    }
    public Assign(Stack<String> ids,Type t,Node value){
        this.vars = new Stack<>();
        while(!ids.isEmpty()){
            vars.push(new VarCall(ids.pop()));
        }
        this.value = value;
    }
    @Override
    public Result genCode() {
       Result val = value.genCode();
       while(!vars.isEmpty()){
          Node var = vars.pop();
          if(var instanceof VarCall){
              if(((VarCall)var).getNext()==null &&!((VarCall)var).isGlobal()){
                  //se guarda en el stack
                  Result tVar =  var instanceof ArrayItemCall?
                          ((ArrayItemCall)var).genCode(true):
                          ((VarCall)var).genCode(true,val.getType().getDims());
                  val = new Cast(val,tVar.getType()).genCode();
                  emite( (var instanceof ArrayItemCall ? "heap[" : "stack[")+tVar+"] = " + val);
              }else{
                  Result tVar = ((VarCall)var).genCode(true,val.getType().getDims());
                  val = new Cast(val,tVar.getType()).genCode();
                  emite("heap["+tVar+"] = " + val);
                  //se guarda en el heap
              }
          }
       }
       return null;
    }
    
}
