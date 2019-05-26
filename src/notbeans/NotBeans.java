/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package notbeans;

import gui.forms.MainFrame;
import gui.forms.NewMainFrame;

/**
 *
 * @author dennis
 */
public class NotBeans {
    
    
    public static String TITLE = "!(Beans)";
    public static String VERSION = "0.01-alpha";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         //new MainFrame().init();
        new NewMainFrame().init();
    }
}