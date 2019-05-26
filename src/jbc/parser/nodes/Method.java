/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser.nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author Dennis
 */
public class Method {
    String id;
    private ArrayList<Node> code;
    private HashMap<String,Bloque> bloques;
    private ArrayList<Bloque> bloquesLst;
    public Method(String id,Stack<Node> args){
        this.id = id;
        code = new ArrayList<>();
        bloques = new HashMap<>();
        bloquesLst = new ArrayList<>();
        Bloque b = new Bloque(id);
        while(!args.isEmpty()){
            Node arg = args.pop();
            if(arg instanceof Label){
                Bloque next = new Bloque(id+code.size());
                b.setNext(next);
                bloques.put(b.getLabel(),b);
                bloquesLst.add(b);
                b = next;
                b.getNodes().add(arg);
                ((Label)arg).execute(code.size());
            }else{
                code.add(arg);
                b.getNodes().add(arg);
                if(
                        arg instanceof MethodCall ||
                        arg instanceof IfGoto
                        ){
                    if(arg instanceof IfGoto && ((IfGoto)arg).getArg1()!=null){
                        Bloque next = new Bloque(id+code.size());
                        b.setNext(next);
                        bloques.put(b.getLabel(),b);
                        bloquesLst.add(b);
                        b = next;
                    }else{
                        Bloque next = new Bloque(id+code.size());
                        b.setNext(next);
                        bloques.put(b.getLabel(),b);
                        bloquesLst.add(b);
                        b = next;
                    }
                }
            }
        }
        bloques.put(b.getLabel(),b);
        bloquesLst.add(b);
    }

    /**
     * @return the code
     */
    public ArrayList<Node> getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(ArrayList<Node> code) {
        this.code = code;
    }
    
    @Override
    public String toString(){
        String method =  "method " + id + "(){\n";
        for(Node n:code){
            method+="\t"+n.toString()+"\n";
        }
        method+="}\n";
        return method;
    }
    public String optimize(){
         String method =  "method " + id + "(){\n";
         for(Bloque b:bloquesLst){
             for(int i = 0;i<10;i++){
                b.optimize();
             }
             this.notReferenced();
            // this.globalExpressions();
            // this.notReferenced();
             for(Node n:b.getNodes()){
                 method+="\t"+n.toString()+"\n";
             }
         }
         return method + "}\n";
    }
    public String optimizeGlobal(){
         String method =  "method " + id + "(){\n";
         for(Bloque b:bloquesLst){
             for(int i = 0;i<10;i++){
                b.optimize();
             }
             this.notReferenced();
             this.globalExpressions();
             this.globalCopys();
             this.notReferenced();
             for(Node n:b.getNodes()){
                 method+="\t"+n.toString()+"\n";
             }
         }
         return method + "}\n";
    }
    public String toDot(int i){
        String dot = "subgraph cluster"+i+" {\n";
        dot+="style=filled;\n" +
             "color=lightgrey;\n";
        for(String bid:bloques.keySet()){
            dot+= bloques.get(bid).toDot()+"\n";
        }
        dot+="label=\"Metodo: "+id +"\";";
        return dot+"}\n";
    }
    
    public String getDotRelations(){
        String relations = new String();
        for(String bid:bloques.keySet()){
            Bloque b = bloques.get(bid);
            for(Node n: b.getNodes()){
                if(n instanceof IfGoto){
                    IfGoto ifGoto = (IfGoto)n;
                    relations += "\"node"+b.getLabel()+"\":f1->\"node"+id+(int)CodeManager.getTempValue(ifGoto.getLabel())+"\":f0\n";
                }else if(n instanceof MethodCall){
                    relations += "\"node"+b.getLabel()+"\":f1->\"node"+((MethodCall)n).id+"\":f0\n";
                }
            }
            if(b.getNext()!=null){
                relations += "\"node"+b.getLabel()+"\":f1->\"node"+b.getNext().getLabel()+"\":f0\n";
            }
        }
        return relations;
    }
    
    private void notReferenced(){
        for(Bloque b:bloquesLst){
            ArrayList<Node> nodes = b.getNodes();
            for (Iterator<Node> iterator = nodes.iterator(); iterator.hasNext(); ) {
                Node n = iterator.next();
                if(n instanceof Assign && ((Assign)n).getIndex()==null 
                        && !((Assign)n).getId().equals("P")
                        && !((Assign)n).getId().equals("H")){
                    Assign arg1 = (Assign)n;
                    boolean reference = false;
                    for(Bloque bb:bloquesLst){
                        reference = bb.reference(arg1.getId());
                        if(reference)
                            break;
                    }
                    if(!reference)
                        iterator.remove();
                }
          }
        }
    }
    
    private void globalExpressions(){
        for(int i = 0;i<bloquesLst.size();i++){
            Bloque b = bloquesLst.get(i);
            ArrayList<Node> nodes = b.getNodes();
            for (int x = 0;x<nodes.size();x++) {
                Node n = nodes.get(x);
                if(n instanceof Assign && ((Assign)n).getIndex()==null 
                        && !((Assign)n).getId().equals("P")
                        && !((Assign)n).getId().equals("H")){
                    Assign arg1 = (Assign)n;
                    for(int w = i+1;w<bloquesLst.size();w++){
                        if(bloquesLst.get(w).commonGlobalExpr(n))
                            break;
                    }
                }
          }
        }
    }
    
    private void globalCopys(){
        for(int i = 0;i<bloquesLst.size();i++){
            Bloque b = bloquesLst.get(i);
            ArrayList<Node> nodes = b.getNodes();
            for (int x = 0;x<nodes.size();x++) {
                Node n = nodes.get(x);
                if(n instanceof Assign && ((Assign)n).getIndex()==null 
                        && !((Assign)n).getId().equals("P")
                        && !((Assign)n).getId().equals("H")){
                    Assign arg1 = (Assign)n;
                    if((arg1.getValue() instanceof ID 
                        && ((ID)arg1.getValue()).index == null 
                        && !((ID)arg1.getValue()).id.equals("H")
                        && !((ID)arg1.getValue()).id.equals("P")
                        ) || arg1.getValue() instanceof Value){
                        for(int w = i+1;w<bloquesLst.size();w++){
                            if(bloquesLst.get(w).eliminateGlobalCopys(arg1))
                                break;
                        }
                    }
                }
          }
        }
    }
    
}
