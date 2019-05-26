/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.forms;
import gui.syntax.highlighter.Painter;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author dennis
 */
public class TextFile extends JTextPane 
    implements CaretListener, ActionListener, KeyListener{
    private boolean state;//Estado [Guardado/No guardado] del fichero abierto
    private TabPanel parent;
    private HTMLEditorKit kit;
    public TextFile(TabPanel parent,String title,String content){
       // super();
        this.setContentType("text/html");
        this.initTexDocument();
        this.parent = parent;
        this.setEditable(false);
        kit = new HTMLEditorKit();
        this.setEditorKit(kit);
        addCSSRules();
        Document doc = kit.createDefaultDocument();
        this.setDocument(doc);
        String s="";
        s += writeHeaders(title);
        s+=content;
        this.setText(s);
        System.out.println(this.getText());
        //this.getStyledDocument().putProperty(PlainDocument.tabSizeAttribute,3);
        
        //.putProperty(PlainDocument.tabSizeAttribute,4);     
        //this.writeHeaders(title);
    }
    
    private void addCSSRules(){
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
        styleSheet.addRule("h1 {color: blue;}");
        styleSheet.addRule("h2 {color: #ff0000;}");
        styleSheet.addRule("#pre {font : 10px monaco; color : green; background-color : #fafafa; }");
        
    }
    private String writeHeaders(String title){
        String s = "";
        s += "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01//EN'";
        s += "'http://www.w3.org/TR/html4/strict.dtd'>";
        return s;
    }
    private String writeEnd(){ 
        return "</body></html>";
    }
    public TextFile(TabPanel parent){
        super();
        this.setContentType("text/plain");
        this.initTexDocument();
        this.parent = parent;
        this.getStyledDocument().putProperty(PlainDocument.tabSizeAttribute,3);
  
    }
    private void initTexDocument(){
        this.setState(false);
        this.setMargin(new Insets(5, 5, 5, 5));
        this.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN,13));
        this.addKeyListener(this);
        this.addCaretListener(this);
     //   this.addInputMethodListener(this);
    }
    private boolean isEdited(KeyEvent e){
        return !e.isActionKey() && !e.isAltDown() &&
               !e.isAltGraphDown() && !e.isControlDown() &&
               !e.isShiftDown();
    }
    @Override
    public void caretUpdate(CaretEvent e) {
        int pos = e.getDot();
        int row = 1;
        int column=0;
        
        int lastNewline=-1;
        String text = this.getText().replaceAll("\r", "");
        for(int i=0;i<pos;i++){
            if(i<text.length()){
                if(text.charAt(i)==10){
                        row++;
                        lastNewline=i;
                }
            }
        }

        column=pos-lastNewline;			
				//rowCol.setText("Col: " + column + "\nRow: " + row);
        parent.main.pos.setText("Ln: " + row + " Col: " + column );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet.");
        //private void painter(Scanner lex,TextFile textpanel){
        
    
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");  
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        if(this.state && this.isEdited(e)){
           this.state = false;
           parent.notSave(parent.getSelectedIndex());
           //System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
        }
        
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int x = this.getCaretPosition();
        if(e!=null){
            try {
                if(
                        e.getKeyCode()==KeyEvent.VK_SPACE||
                        e.getKeyCode()==KeyEvent.VK_ENTER||
                        e.getKeyCode()==KeyEvent.VK_TAB){
                        Painter.painter(TextFile.this);
                }
                //Painter.painter(TextFile.this);
                switch(e.getKeyChar()){
                    case '[':
                        this.getStyledDocument().insertString(x, "]", null);
                        this.setCaretPosition(x);
                        break;
                    case '{':
                        this.getStyledDocument().insertString(x, "}", null);
                        this.setCaretPosition(x);
                        break;
                    case '(':
                        this.getStyledDocument().insertString(x, ")", null);
                        this.setCaretPosition(x);
                        break;
                    case '\"':
                        this.getStyledDocument().insertString(x, "\"", null);
                        this.setCaretPosition(x);
                        break;
                }
            } catch (BadLocationException ex) {
                Logger.getLogger(TextFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            Painter.painter(this);
        }
        
    }
    
    /**
     * @return the state
     */
    public boolean getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(boolean state) {
        this.state = state;
    }
    
    
}
