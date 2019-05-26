/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser.nodes;

/**
 *
 * @author Dennis
 */
public class Value extends Node {
    float value;
    public Value(Object value){
        this.value = Float.parseFloat(String.valueOf(value));
    }
    @Override
    public float execute() {
       return value;
    }
    
    @Override
    public String toString(){
        return String.valueOf(value);
    }
    @Override
    public Node replace(Node node, String match) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this;
    }
    @Override
    public boolean reference(String id) {
        return false;
    }
}
