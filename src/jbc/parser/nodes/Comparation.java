/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser.nodes;

/**
 *
 * @author Dennis
 */
public class Comparation extends Node{
    Node arg1;
    Node arg2;
    String op;
    public Comparation(Node arg1, Node arg2,String op){
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.op = op;
    }
    @Override
    public float execute() {
        float r1 = arg1.execute();
        float r2 = arg2.execute();
        switch(op){
            case "==":
                return (r1==r2)?1f:0f;
            case "!=":
                return (r1!=r2)?1f:0f;
            case ">":
                return (r1>r2)?1f:0f;
            case ">=":
                return (r1>=r2)?1f:0f;
            case "<":
                return (r1<r2)?1f:0f;
            case "<=":
                return (r1<=r2)?1f:0f;
            default:
                return 0f;
        }
    }
    
    @Override
    public String toString(){
        return arg1.toString() + " " + op + " " + arg2.toString();
    }
    
    @Override
    public Node replace(Node node, String match) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        arg1 = arg1.replace(node, match);
        arg2 = arg2.replace(node, match);
        return this;
    }
    @Override
    public boolean reference(String id) {
        return arg1.reference(id) || arg2.reference(id);
    }
}
