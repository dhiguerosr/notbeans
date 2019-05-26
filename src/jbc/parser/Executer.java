/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser;

import gui.forms.Console;
import gui.forms.MainFrame;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jbc.parser.nodes.CodeManager;

/**
 *
 * @author Dennis
 */
public class Executer {
    public static boolean parse(String f_path){
        FileReader fr = null;
        try {
            fr = new FileReader(f_path);
            CodeManager.init();
            Console.println("run: "+f_path +"...");
            
            Scanner lex = new Scanner(fr);
            Parser miParser=new Parser(lex);
            miParser.parse();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(fr!=null)
                    fr.close();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
