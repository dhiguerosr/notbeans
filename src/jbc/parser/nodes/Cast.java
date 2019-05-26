/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser.nodes;

/**
 *
 * @author Dennis
 */
public class Cast extends Node {
    String id;
    Node v1;
    public Cast(String id, Node v1) {
        this.id = id;
        this.v1 = v1;
    }

    @Override
    public float execute() {
        float val = v1.execute();
        switch(id){
            case "bool_to_string":
            case "char_to_string":
            case "float_to_string":
            case "int_to_string":
                String result= new String();
                switch(id){
                    case "bool_to_string":
                        result = String.valueOf(val>0);
                        break;
                    case "char_to_string":
                        result = String.valueOf((char)val);
                        break;
                    case "int_to_string":
                        result = String.valueOf((int)val);
                        break;
                    case "float_to_string":
                        result = String.valueOf(val);
                        break;
                }
                float res = CodeManager.getHeapPointer();
                CodeManager.setToHeap((float)result.length(),(int)res);
                CodeManager.setHeapPointer(res+1);
                for(int x = 0;x<result.length();x++){
                    int H = CodeManager.getHeapPointer();
                    CodeManager.setToHeap((float)result.charAt(x), H);
                    CodeManager.setHeapPointer(H+1);
                }
                return res;
            case "int_to_float":
                return val;
            case "float_to_int":
                return (int)val;
            case "int_to_char":
                return (float)(char)(int)val;
            case "char_to_int":
                return (int)(char)(int)(val);
            default:
                return 0f;
        }
    }
    
    @Override
    public String toString(){
        return id + " " + v1.toString();
    }
    @Override
    public Node replace(Node node, String match) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        v1 = v1.replace(node, match);
        return this;
    }
    @Override
    public boolean reference(String id) {
        return v1.reference(id);
    }
}
