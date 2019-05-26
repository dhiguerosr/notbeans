/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.tree3d;

import gui.forms.Console;
import gui.forms.MainFrame;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import parsers.frc.managers.SymbolTableManager;
import parsers.frc.ts.Scanner;

/**
 *
 * @author Dennis
 */
public class ICParser {
    public static boolean parse(String f_path){
        FileReader fr = null;
        try {
            fr = new FileReader(f_path);
            SymbolTableManager.setCurrentFile(new File(f_path).getName());
            Console.println("Parseando "+f_path +"...");
            Scanner lex = new Scanner(fr);
            parsers.tree3d.Parser miParser=new  parsers.tree3d.Parser(lex);
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
