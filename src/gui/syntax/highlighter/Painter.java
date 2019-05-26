/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.syntax.highlighter;

import gui.forms.MainFrame;
import gui.forms.TextFile;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;

/**
 *
 * @author dennis
 */
public class Painter{
    public static void painter(TextFile f){
        //f.setEnabled(false);
        Scanner lex = new Scanner(new BufferedReader(new StringReader(f.getText())));
        int x = f.getCaretPosition();
        try {
            SimpleAttributeSet set = null;
            f.setText("");
            Document doc = f.getStyledDocument();
            Symbol sim=lex.next_token();
            while(sim.sym!=sym.EOF){
                switch(sim.sym){
                    case sym.FILE:
                       set =new SimpleAttributeSet();
                       StyleConstants.setForeground(set,Color.ORANGE);
                       break;
                   case sym.COMENT:
                       set =new SimpleAttributeSet();
                       StyleConstants.setForeground(set,Color.LIGHT_GRAY);
                       break;
                   case sym.SIGNO:
                       set = new SimpleAttributeSet();
                       StyleConstants.setForeground(set,(sim.value.equals("#"))?Color.GREEN:Color.BLACK);
                       break;
                   case sym.OPERADOR:
                       set = new SimpleAttributeSet();
                       StyleConstants.setForeground(set, Color.DARK_GRAY);
                       break;
                   case sym.VALUE:
                       set = new SimpleAttributeSet();
                       StyleConstants.setBold(set, true);
                       StyleConstants.setForeground(set, Color.MAGENTA);
                       break;
                   case sym.CADENA:
                       set = new SimpleAttributeSet();
                       StyleConstants.setBold(set, true);
                       StyleConstants.setForeground(set, Color.ORANGE);
                       break;
                   case sym.RESERVADA:
                       set = new SimpleAttributeSet();
                       StyleConstants.setForeground(set,(String.valueOf(sim.value).equalsIgnoreCase("import"))?Color.GREEN:Color.BLUE);
                       break;
                   case sym.TIPO:
                       set = new SimpleAttributeSet();
                       StyleConstants.setBold(set, true);
                       StyleConstants.setForeground(set, Color.BLUE);
                       break;
                   case sym.ID:
                       set = new SimpleAttributeSet();
                       StyleConstants.setBold(set, true);
                       StyleConstants.setForeground(set, Color.BLACK);
                       break;
                   case sym.ERROR:
                       set = new SimpleAttributeSet();
                       StyleConstants.setBold(set, true);
                       StyleConstants.setForeground(set, Color.RED);
                       break;
                   
                  default:
                       break;
                }
                doc.insertString(doc.getLength(),((String) sim.value).replaceAll("\r", ""), set);
                set = new SimpleAttributeSet();
                StyleConstants.setForeground(set, Color.BLACK);
                sim=lex.next_token(); 
                
            }
            f.setCaretPosition(x);
        } catch (BadLocationException | IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            f.setCaretPosition(x);
        }
    }
}
