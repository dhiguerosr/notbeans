/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser.nodes;

/**
 *
 * @author Dennis
 */
public class Label extends Node{
    String label;
    public Label(String id){
        this.label = id;
    }
    @Override
    public float execute() {
        return 0f;
    }
    public float execute(int index){
        CodeManager.setTempValue(index, label);
        return 0f;
    }
    
    @Override
    public String toString(){
        return label + ":";
    }
    @Override
    public Node replace(Node nod, String match) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this;
    }

    @Override
    public boolean reference(String id) {
        return false;
    }
    
}
