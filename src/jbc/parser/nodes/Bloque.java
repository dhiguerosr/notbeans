/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser.nodes;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Dennis
 */
public class Bloque {
    private ArrayList<Node> nodes;
    private String label;
    private Bloque next;
    public Bloque(String label){
        this.label = label;
        nodes = new ArrayList<>();
    }
    /**
     * @return the nodes
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * @param nodes the nodes to set
     */
    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }
    public void optimize(){
        commonExpressions();
        eliminateCopys();
        constantCalc();
        //notReference();
    }
    public boolean commonGlobalExpr(Node n){
        for(int i = 0; i<nodes.size();i++){
            Node n1 = nodes.get(i);
            if(n1 instanceof Assign && ((Assign)n1).getIndex()==null
                    && !((Assign)n1).getId().equals("P")
                    && !((Assign)n1).getId().equals("H")){
                Assign arg1 = (Assign)n;
                Assign arg2 = (Assign)n1;
                if((arg1.getId().equals(arg2.getId()) && !arg1.getValue().toString().equals(arg2.getValue().toString()))
                        || (arg1.getValue().toString().contains("P") && arg2.getId().equals("P"))
                        || (arg1.getValue().toString().contains("H") && arg2.getId().equals("H"))
                        ){
                    return true;
                }
                if(arg1.getValue().toString().equals(arg2.getValue().toString())){
                    arg2.setValue(new ID(arg1.getId()));
                }
            }
        }
        return false;
    }
    public void commonExpressions(){
        for(int x=0;x<nodes.size();x++){
            Node n = nodes.get(x);
            if(n instanceof Assign && ((Assign)n).getIndex()==null
                    && !((Assign)n).getId().equals("P")
                    && !((Assign)n).getId().equals("H")){
                for(int i = x+1; i<nodes.size();i++){
                    Node n1 = nodes.get(i);
                    if(n1 instanceof Assign && ((Assign)n1).getIndex()==null
                            && !((Assign)n1).getId().equals("P")
                            && !((Assign)n1).getId().equals("H")){
                        Assign arg1 = (Assign)n;
                        Assign arg2 = (Assign)n1;
                        if((arg1.getId().equals(arg2.getId()) && !arg1.getValue().toString().equals(arg2.getValue().toString()))
                                || (arg1.getValue().toString().contains("P") && arg2.getId().equals("P"))
                                || (arg1.getValue().toString().contains("H") && arg2.getId().equals("H"))
                                ){
                            break;
                        }
                        if(arg1.getValue().toString().equals(arg2.getValue().toString())){
                            arg2.setValue(new ID(arg1.getId()));
                        }
                    }
                }
            }
        }
    }
    
    public void eliminateCopys(){
        for(int x=0;x<nodes.size();x++){
            Node n = nodes.get(x);
            if(n instanceof Assign && ((Assign)n).getIndex()==null
                   && !((Assign)n).getId().equals("P")
                   && !((Assign)n).getId().equals("H") ){
                Assign arg1 = (Assign)n;
                if((arg1.getValue() instanceof ID 
                        && ((ID)arg1.getValue()).index == null 
                        && !((ID)arg1.getValue()).id.equals("H")
                        && !((ID)arg1.getValue()).id.equals("P")
                        ) || arg1.getValue() instanceof Value){
                    for(int i=x+1;i<nodes.size();i++){
                        Node n1 = nodes.get(i);
                        if(n1 instanceof Assign && ((Assign)n1).getIndex()==null ){
                            Assign arg2 = (Assign)n1;
                            if(arg1.getId().equals(arg2.getId()) && !arg1.getValue().toString().equals(arg2.getValue().toString())){
                                break;
                            }
                            n1.replace(arg1.getValue(), arg1.getId());
                        }else{
                            n1.replace(arg1.getValue(), arg1.getId());
                        }
                    }
                }
            }
        }
    }
    public boolean eliminateGlobalCopys(Assign arg1){
        for(int i=0;i<nodes.size();i++){
            Node n1 = nodes.get(i);
            if(n1 instanceof Assign && ((Assign)n1).getIndex()==null ){
                Assign arg2 = (Assign)n1;
                if(arg1.getId().equals(arg2.getId()) && !arg1.getValue().toString().equals(arg2.getValue().toString())){
                    return true;
                }
                n1.replace(arg1.getValue(), arg1.getId());
            }else{
                n1.replace(arg1.getValue(), arg1.getId());
            }
        }
        return false;
    }
    public boolean reference(String id){
        boolean reference = false;
        for(Node n1:nodes){
            if(n1.reference(id)){
                reference = true;
                break;
            }
        }
        return reference;
    }
    
    public void constantCalc(){
        for(Node n:nodes){
            if(n instanceof Assign){
                ((Assign)n).optimize();
            }
        }
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    public String toDot(){
        String dot =  "\"node"+label+"\" [ label = \"{<f0> "+label+"|";
        for(Node n :nodes){
            dot+= n + "|";
        }
        dot+= "<f1> }\" shape = \"record\"];";
        return dot;
        
    }

    /**
     * @return the next
     */
    public Bloque getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Bloque next) {
        this.next = next;
    }
    
}
