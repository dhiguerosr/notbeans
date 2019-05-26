/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d.nodes;

import gui.forms.Console;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Stack;
import parsers.frc.managers.SymbolTableManager;

/**
 *
 * @author Dennis
 */
public abstract class Node {
    private static int tempCount;
    private static int labelCount;
    
    private static Stack<String> methodExitLabel;
    private static Stack<String> loopNextLabel;
    private static Stack<String> loopExitLabel;
    private static String output;
    
    public static void init(){
        labelCount = 0;
        tempCount = 0;
        methodExitLabel = new Stack<>();
        loopNextLabel = new Stack<>();
        loopExitLabel = new Stack<>();
        SymbolTableManager.restore();
        output = new String();
    }
    
    public abstract Result genCode();
    
    public void emite(String code){
        Console.print("\t"+code +";\n");
        output+="\t"+code +";\n";
    }
    public void emiteLabel(String code){
        Console.println("\t"+code+":\n");
        output+="\t"+code+":\n";
    }
    public void emite(String code,String delimiter){
        Console.println(code+delimiter);
        output+= code + delimiter;
    }
    public String genTemp(){
        return "t"+tempCount++;
    }
    public String genLabel(){
        return "L"+labelCount++;
    }
    
    public String getNextLoopLabel(){
        return(loopNextLabel.isEmpty()?null:loopNextLabel.peek());
    }
    public String getExitLoopLabel(){
        return(loopExitLabel.isEmpty()?null:loopExitLabel.peek());
    }
    public String getExitMethodLabel(){
        return(methodExitLabel.isEmpty()?null:methodExitLabel.peek());
    }
    
    public void setNextLoopLabel(String label){
        loopNextLabel.push(label);
    }
    
    public void setExitLoopLabel(String label){
        loopExitLabel.push(label);
    }
    
    public void setExitMethodLabel(String label){
        methodExitLabel.push(label);
    }
    
    public void removeNextLoopLabel(){
        loopNextLabel.pop();
    }
    public void removeExitLoopLabel(){
        loopExitLabel.pop();
    }
    public void removeExitMethodLabel(){
        methodExitLabel.pop();
    }
    
    public static void toFile(String f_name){
        FileWriter proj = null;
        PrintWriter pw;
        try{
            proj = new FileWriter(f_name);
            pw = new PrintWriter(proj);
            pw.print(output);
        } catch (Exception e) {return;} 
        finally {
           try{
           if (proj!=null)
               proj.close();
           } catch (Exception e2) {return;}
        }
    }
}
