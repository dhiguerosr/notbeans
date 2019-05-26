/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.statements;

import java.util.Stack;
import parsers.frc.managers.SymbolTableManager;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;
import parsers.tree3d.nodes.arithmetics.Add;
import parsers.tree3d.nodes.expressions.Cast;
import parsers.tree3d.nodes.expressions.MethodCall;

/**
 *
 * @author Dennis
 */
public class NativeMethodCall extends Node{
    String name;
    Stack<Node> args;
    public NativeMethodCall(String name, Stack<Node> args){
        this.name = name;
        this.args = args;
    }
    @Override
    public Result genCode() {
        if(name.equals("Imprimir")){
            
            Node node = new Cast(args.pop(),SymbolTableManager.getType("string"));
            while(!args.isEmpty()){
                node = new Add(node,args.pop());
                //Stack<Node> nodes = new Stack<>();
                //nodes.push(node);
                //new MethodCall(name,nodes).genCode(true);
            }
            Stack<Node> nodes = new Stack<>();
            nodes.push(node);
            new MethodCall(name,nodes).genCode(true);
        }else{
            new MethodCall(name,args).genCode(true);
        }
        return null;
    }
    
}
