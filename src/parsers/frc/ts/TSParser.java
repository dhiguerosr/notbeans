/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.frc.ts;

import gui.forms.Console;
import gui.forms.MainFrame;
import gui.forms.ProjectManager;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import parsers.frc.managers.Err;
import parsers.frc.managers.ErrorManager;
import parsers.frc.managers.SymbolTableManager;

/**
 *
 * @author Dennis
 */
public class TSParser {
    public static boolean parse(){
        FileReader fr = null;
        FileReader tcFr = null;
        if(ProjectManager.getCurrentProject().getMainFile()==null){
            JOptionPane.showMessageDialog(null,"No se ha definido un archivo principal","Error",JOptionPane.ERROR_MESSAGE);
        }
        try {
            
            String f_path = ProjectManager.getCurrentProject().getMainFile().getAbsolutePath();
            fr = new FileReader(f_path);
            tcFr = new FileReader(f_path);
            SymbolTableManager.init(new File(f_path).getName());
            Console.println("Parseando "+f_path +"...");
            Scanner lex = new Scanner(fr);
            Scanner tcLex = new Scanner(tcFr);
            Parser miParser=new Parser(lex);
            parsers.frc.tc.Parser tcParser = new parsers.frc.tc.Parser(tcLex);
            miParser.parse();
            tcParser.parse();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(fr!=null)
                    fr.close();
                if(tcFr!=null)
                    tcFr.close();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    public static boolean parseImport(String f_path){
        FileReader fr = null;
        FileReader tcFr = null;
        try {
            fr = new FileReader(f_path);
            tcFr = new FileReader(f_path);
            SymbolTableManager.setCurrentFile(new File(f_path).getName());
            Console.println("Parseando "+f_path +"...");
            Scanner lex = new Scanner(fr);
            Scanner tcLex = new Scanner(tcFr);
            Parser miParser=new Parser(lex);
            parsers.frc.tc.Parser tcParser = new parsers.frc.tc.Parser(tcLex);
            miParser.parse();
            tcParser.parse();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(fr!=null)
                    fr.close();
                if(tcFr!=null)
                    tcFr.close();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
