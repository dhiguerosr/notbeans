/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.statements;

import java.util.Stack;
import parsers.frc.managers.SymbolTableManager;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;

/**
 *
 * @author Dennis
 */
public class Clazz extends Node{
    Stack<Node> nodes;
    String name;
    String extendsName;
    public Clazz(String name,Stack<Node> nodes){
        this.name = name;
        this.nodes = nodes;
    }
    public Clazz(String name,String extendsName,Stack<Node> nodes){
        this.name = name;
        this.extendsName = extendsName;
        this.nodes = nodes;
    }
    @Override
    public Result genCode() {
        SymbolTableManager.setContext(SymbolTableManager.getType(name));
        //genera metodos
        while(!nodes.isEmpty()){
            //pendiente revisar si es una asignacion global
            if(nodes.peek()!=null)
                nodes.pop().genCode();
            else 
                nodes.pop();
        }
        SymbolTableManager.removeContext();
        return null;
    }
    
}
