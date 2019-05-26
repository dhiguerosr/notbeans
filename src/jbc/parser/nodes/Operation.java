/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser.nodes;

/**
 *
 * @author Dennis
 */
public class Operation extends Node {
    Node arg1;
    Node arg2;
    String op;
    public Operation(Node arg1, Node arg2,String op){
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.op = op;
    }
    @Override
    public float execute() {
        float r1 = arg1.execute();
        float r2 = arg2.execute();
        switch(op){
            case "+":
                return r1 + r2;
            case "-":
                return r1 - r2;
            case "*":
                return r1 * r2;
            case "/":
                return r1 / r2;
            case "^":
                return (float) Math.pow(r1, r2);
            case "strcat":
                //concatenar las cadenas
                return strCat((int)r1,(int)r2);
            default:
                return 0f;
        }
    }
    
    public Node optimize(){
        if(arg1 instanceof Value && arg2 instanceof Value){
            //evaluacion de constantes
            float r = this.execute();
            return new Value(r);
        }else if(arg2 instanceof Value){
            float r2 = arg2.execute();
            if(r2==0f){
                switch(op){
                    case "+":
                    case "-":
                        return arg1;
                    case "*":
                        return new Value(0);
                    case "^":
                        return new Value(1);
                }
            }else if(r2==1f){
                switch(op){
                    case "/":
                        return arg1;
                    case "*":
                        return arg1;
                    case "^":
                        return arg1;
                }
            }else if(r2 == 2f){
                switch(op){
                    case "*":
                        return new Operation(arg1,arg1,"+");
                    case "^":
                        return new Operation(arg1,arg1,"+");
                }
            }
        }else if(arg1 instanceof Value){
            float r1 = arg1.execute();
            if(r1==0f){
                switch(op){
                    case "+":
                    case "-":
                        return arg2;
                    case "*":
                        return new Value(0);
                    case "^":
                        return new Value(1);
                }
            }else if(r1==1f){
                switch(op){
                    case "/":
                        return arg2;
                    case "*":
                        return arg2;
                    case "^":
                        return arg2;
                }
            }else if(r1 == 2f){
                switch(op){
                    case "*":
                        return new Operation(arg2,arg2,"+");
                    case "^":
                        return new Operation(arg2,arg2,"+");
                }
            }
        }
        return this;
    }
    
    public float strCat(int t1,int t2){  
        float size1 = CodeManager.getFromHeap(t1);
        float size2 = CodeManager.getFromHeap(t2);
        String result = new String();
        for(int x = 0; x < size1;x++){
            int code = (int)CodeManager.getFromHeap(t1 + x + 1);
            result += String.valueOf((char)code);
        }
        for(int x = 0; x < size2;x++){
            int code = (int)CodeManager.getFromHeap(t2 + x + 1);
            result += String.valueOf((char)code);
        }
        int size = result.length();
        
        float res = CodeManager.getHeapPointer();
        CodeManager.setToHeap((float)result.length(),(int)res);
        CodeManager.setHeapPointer(res + size + 1);
        for(int x = 0;x<result.length();x++){
            CodeManager.setToHeap((float)result.charAt(x), (int)res + x +1);
        }
        return res;
    }
    
    @Override
    public String toString(){
        return arg1.toString() + " " + op + " " + arg2.toString();
    }
    
    @Override
    public Node replace(Node node, String match) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(!op.equals("strcat")){
            arg1 = arg1.replace(node, match);
            arg2 = arg2.replace(node, match);
        }
        return this;
    }
    @Override
    public boolean reference(String id) {
        return arg1.reference(id)|| arg2.reference(id);
    }
}
