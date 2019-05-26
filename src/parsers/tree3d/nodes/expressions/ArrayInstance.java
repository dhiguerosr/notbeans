/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes.expressions;

import java.util.ArrayList;
import java.util.Stack;
import parsers.frc.managers.Type;
import parsers.tree3d.nodes.Node;
import parsers.tree3d.nodes.Result;

/**
 *
 * @author Dennis
 */
public class ArrayInstance extends Node{
    Stack<Node> args;
    int dimCount = 0;
   
    public ArrayInstance(Stack<Node> args){
        
        this.args = args;
        this.dimCount = args.size();
    }
    @Override
    public Result genCode() {
        ArrayList<String> dims = new ArrayList<>();
        Type t = new Type("Array");
        t.setDims(dimCount);
        while(!args.isEmpty()){
            String res = args.pop().genCode().getResult();
            dims.add(res);
        }
        String temp = genTemp();        //tamaño total de items (informacion para reservar memoria)
        emite(temp+ " = 1");
        for(String dim : dims){
            String n_temp = genTemp();
            emite(n_temp +" = " + temp + " * " + dim);
            temp = n_temp;
        }
        String result = genTemp();
        emite(result +" = H");                      // posicion base 
        int corrimiento = dimCount + 1;             //base + 1 + numero de dimensiones
        emite("H = H + " + temp);                   //reserva memoria
        emite("H = H + " + corrimiento);            //reserva memoria
        emite("heap["+result+"] = "+ dimCount);     //guarda el numero de dimensiones
        for(int i = 0; i<dimCount;i++){
            String tmp = genTemp();
            emite(tmp + " = "+result+" + "+(i+1));
            emite("heap["+tmp+"] = "+dims.get(i));
        }
        return new Result(result,t);
    }
    
}
