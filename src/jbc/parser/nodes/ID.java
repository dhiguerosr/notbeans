/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser.nodes;


/**
 *
 * @author Dennis
 */
public class ID extends Node {
    String id;
    Node index;
    public ID(String id){
        this.id = id;
    }
    public ID(String id,Node index){
        this.id = id;
        this.index = index;
        
    }
    @Override
    public float execute() {
        float i = (index!=null)?index.execute():0f;
        switch(id){
            case "P":
                return CodeManager.getStackPointer();
            case "H":
                return CodeManager.getHeapPointer();
            case "stack":
                return CodeManager.getFromStack((int)i);
            case "heap":
                return CodeManager.getFromHeap((int)i);
            default:
                return CodeManager.getTempValue(id);       
        }
    }
    
    @Override
    public String toString(){
        return id + ((index!=null)? "["+index+"]":"");
    }
    
    @Override
    public Node replace(Node node, String match) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(this.index==null && id.equals(match)){
           return node;
        }
        return this;
    }
    @Override
    public boolean reference(String n) {
        return (this.index==null && id.equals(n)) || (index!=null && index.reference(n)) ;
    }
}
