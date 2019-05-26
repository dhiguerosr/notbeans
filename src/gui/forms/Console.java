/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.forms;

import javax.swing.JTextArea;

/**
 *
 * @author Dennis
 */
public class Console {
    private static JTextArea console;
    public static void init(JTextArea ta){
        ta.setText("Consola:\n");
        console = ta;
    }
    public static void println(String s){
        print(s+"\n");
    }
    public static void print(String s){
        console.setText(console.getText()+s);
    }
    public void reset(){
       console.setText("Consola:\n"); 
    }
}
