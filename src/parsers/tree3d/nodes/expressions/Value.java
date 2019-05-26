/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.expressions;

import parsers.frc.managers.Type;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;

/**
 *
 * @author Dennis
 */
public class Value extends Node{
    String value;
    Type type;
    public Value(Object v, Type t){
        value = String.valueOf(v);
        type = t;
    }
    @Override
    public Result genCode() {
        if(type.getName().equals("string")){
            //reserva espacio memoria string
            int s_size = value.length();
            String result = genTemp();
            emite(result + " = " + "H");
            emite("H = H + "+ (s_size+1));
            emite("heap["+result+"] = " + s_size);
            for(int x = 0;x<s_size;x++){
                String t = genTemp();
                emite(t+" = "+ result + " + " + (x + 1));
                emite("heap["+t+"]" + " = " + (int)value.charAt(x));
            }
            return new Result(result,type);
            
        }else{
            if(type.getName().equals("char")){
                Integer ival =(int)value.charAt(0);
                return new Result(ival.toString(),type);
            }
            return new Result(value,type);
        }
    }
    
}
