/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser.nodes;

/**
 *
 * @author Dennis
 */
public class IfGoto extends Node{
    private Node arg1;
    private String label;
    public IfGoto(String label){
        this.label = label;
    }
    public IfGoto(Node arg1, String label){
        this.arg1 = arg1;
        this.label = label;
    }
    @Override
    public float execute() {
        float result = (getArg1()!=null)?getArg1().execute():1f;
        if(result>0){
            float index = CodeManager.getTempValue(getLabel());
            CodeManager.setInstructionPointer(index);
        }
        return 0f;
    }
    @Override
    public String toString(){
        return ((getArg1()!=null)?"if " + getArg1() + " then ":"") + "goto " + getLabel() + ";";
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the arg1
     */
    public Node getArg1() {
        return arg1;
    }

    /**
     * @param arg1 the arg1 to set
     */
    public void setArg1(Node arg1) {
        this.arg1 = arg1;
    }
    
    @Override
    public Node replace(Node node, String match) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(arg1!=null){
           arg1 = arg1.replace(node, match);
        }
        //arg1.replace(node, match);
        return this;
    }
    
    @Override
    public boolean reference(String id) {
        return (arg1!=null)?arg1.reference(id):false;
    }
    
}
