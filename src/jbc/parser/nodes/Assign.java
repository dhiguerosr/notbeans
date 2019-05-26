/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser.nodes;

import gui.forms.Console;

/**
 *
 * @author Dennis
 */
public class Assign extends Node {
    private String id;
    private Node value;
    private Node index;
    public Assign(String id, Node value){
        this.id = id;
        this.value = value;
    }
    public Assign(String id,Node index, Node value){
        this.id = id;
        this.value = value;
        this.index = index;
    }
    
    @Override
    public float execute() {
        float val = getValue().execute();
        float i = (getIndex()!=null)?getIndex().execute():0f;
        switch(getId()){
            case "stack":
                CodeManager.setToStack(val,(int)i);
                break;
            case "heap":
                CodeManager.setToHeap(val, (int)i);
                break;
            case "P":
                CodeManager.setStackPointer(val);
                break;
            case "H":
                CodeManager.setHeapPointer(val);
                break;
            default:
                CodeManager.setTempValue(val, getId());
                break;
        }
        System.out.println(getId()+ ((getIndex()!=null)?("["+(int)i+"]" ):" ")+ " = " + val + " :\t\t\t P = " + CodeManager.getStackPointer() + " H = " + CodeManager.getHeapPointer());
        return 0f;
    }
    
    @Override
    public String toString(){
        return getId() + ((getIndex()!=null)? "["+getIndex()+"]":"") + " = " + getValue().toString()+";";  
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the value
     */
    public Node getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Node value) {
        this.value = value;
    }

    /**
     * @return the index
     */
    public Node getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Node index) {
        this.index = index;
    }
    public void optimize(){
        if(this.value instanceof Operation){
            this.value = ((Operation)this.value).optimize();
        }
    }

    @Override
    public Node replace(Node val, String match) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        value = value.replace(val, match);
        return null;
    }

    @Override
    public boolean reference(String id) {
        return value.reference(id) || (index!=null && index.reference(id));
    }
}
