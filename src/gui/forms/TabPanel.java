/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.forms;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author dennis
 */
public class TabPanel extends JTabbedPane 
    implements ActionListener, ChangeListener{

    private ArrayList <TextFile> ficheros;
    private ArrayList <String> directorios;
    NewMainFrame main;

    private Integer tabCreado;
    private ImageIcon icono;
    public TabPanel(MainFrame mf){
    	this.initComponents();
    }
    public TabPanel(NewMainFrame mf){
        this.initComponents();
        this.main = mf;
    }
    private void initComponents(){
    	ficheros = new ArrayList<>();
    	directorios = new ArrayList<>();
        icono = new ImageIcon(this.getClass().getResource("/img/New16.gif"));
    	tabCreado = 0;
        this.addChangeListener(this);
        //this.newVentana();
    }
    public void newVentanaHTML(String title, String content){
        getFicheros().add(new TextFile(this,title,content));
        getDirectorios().add(title);
        
        JScrollPane scroll = new JScrollPane(getFicheros().get(getFicheros().size()-1));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.addTab(title,icono,scroll);
    	//this.addTab(title, icono, new JScrollPane(getFicheros().get(getFicheros().size()-1)));
    }
    public void newVentana(String dir, String subtitle){
    	getFicheros().add(new TextFile(this));
        getDirectorios().add(dir);
        getFicheros().get(getFicheros().size()-1).setState(true);
        
        TextFile tf = getFicheros().get(getFicheros().size()-1);
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().setView(tf);
        scroll.setRowHeaderView(new TextLineNumber(tf));
        this.addTab(subtitle,icono,scroll);
    	//this.addTab(subtitle, icono,new JScrollPane(getFicheros().get(getFicheros().size()-1)));
    }

    public void newVentana(){
    	getFicheros().add(new TextFile(this));
        getDirectorios().add("No saved");
        
        TextFile tf = getFicheros().get(getFicheros().size()-1);
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().setView(tf);
        scroll.setRowHeaderView(new TextLineNumber(tf));
        this.addTab("Nueno"+(tabCreado++)+"*",icono,scroll);
    	//this.addTab("Nuevo" + (tabCreado++) + "*", 
        //icono, new JScrollPane(getFicheros().get(getFicheros().size()-1)));
    }
    @Override
    public void addTab(String s, Icon icon, Component c){
        super.addTab(s,icon,c);
        this.setTabComponentAt(this.getTabCount()-1, new ButtonTabComponent(this));
    }
    public void removeVentana(int pos){
    	this.removeTabAt(pos);
    	this.getFicheros().remove(pos);
        this.getDirectorios().remove(pos);
    }
    public boolean notFileSaved(){
        if(getTabCount() == 0)
            return true;
        for (int i = getTabCount() - 1; i >= 0; i--) {
            if(close(i) == 2){
                return false;
            }
        }
        return true;
    }
    
    public void notSave(int select)//Indicated '*': File no saved
    {
        String title = this.getTitleAt(select);
        if(title.charAt(title.length()-1) == '*')
           return;
        title = title + "*";
        this.setTitleAt(select, title);
        main.getMnuGuardar().setEnabled(true);
        //file[2].setEnabled(true);
        //button[2].setEnabled(true);
    }
    public void newSave(File fo, int select)//Save content to the tab in a file.
    {
        try{
            BufferedWriter bw = 
                    new BufferedWriter(new FileWriter(fo.getAbsolutePath()));
            String text = getFicheros().get(select).getText();
            getDirectorios().add(select, fo.getAbsolutePath());
            getFicheros().get(select).setState(true);
            this.setTitleAt(select,fo.getName());
            bw.write(text);
            bw.close();
        }
        catch(IOException ex){
                System.out.println("Exception: "+ ex);
        }
    }
    public void save(int select)//Save the chages made to the file
    {
        try{
                String dirt = getDirectorios().get(select);
                BufferedWriter bw = new BufferedWriter(new FileWriter(dirt));
                String text = getFicheros().get(select).getText();
                getFicheros().get(select).setState(true);
                bw.write(text);
                bw.close();
        }
        catch(IOException ex){
                System.out.println("Exception: "+ ex);
        }
    }
    
    public int close(int select){
        if(!this.getFicheros().isEmpty()){
        String title = this.getTitleAt(select);
            if(ficheros.get(select).getText().length()==0
               || title.charAt(title.length()-1)!='*')
            {
                
               this.removeVentana(select);
               //if(this.getTabCount() == 0)
                     // System.exit(0);
               return -1;
            }
            
            int conf = JOptionPane.showConfirmDialog(this,
                    "Desea guardar los Cambios de \n"+
                    title.substring(0, title.length()-1),"Cerrar",1);
            if(conf==0)
            {
               if(directorios.get(select).compareTo("No saved")==0)
               {
                  JFileChooser files = new JFileChooser();
                  files.setMultiSelectionEnabled(false);
                  int fileO = files.showSaveDialog(this);
                  if(fileO == JFileChooser.APPROVE_OPTION)
                  {
                     File fo = new File(files.getSelectedFile().getAbsolutePath());
                     if(fo.isFile()){
                        JOptionPane.showMessageDialog(this, 
                                "Fichero ya existe", "Save As Error", 0);
                        return -1;
                     }
                     this.newSave(fo, select);
                     if(this.getTabCount()==0)
                        System.exit(0);
                  }
               }
               else if(title.charAt(title.length()-1) == '*'){
                   this.save(select);
                   this.removeVentana(select);
                   if(this.getTabCount() == 0)
                      System.exit(0);
	          } 
             }
             else if(conf == 1){
                //System.out.println("Conf: " + conf);
                this.removeVentana(select);  
                //if(this.getTabCount() == 0)
                   // System.exit(0);
             }
             else if(conf == 2){
                 return 2;
             }
            return -1;
        }
        return -1;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void stateChanged(ChangeEvent e) {
       // throw new UnsupportedOperationException("Not supported yet.");
        int select = getSelectedIndex();
        if(select >=0){
            main.getMnuGuardar().setEnabled(!ficheros.get(select).getState());
        }
        
    }
    public static int existDirectory(ArrayList <String> dirs, String dir){
        ArrayList <String> aux = new ArrayList <> ();
        Iterator <String> it = dirs.iterator();
        while(it.hasNext())
            aux.add(it.next());
        Collections.sort(aux);
        return Collections.binarySearch(aux, dir);
    }

    /**
     * @return the directorios
     */
    public ArrayList <String> getDirectorios() {
        return directorios;
    }

    /**
     * @param directorios the directorios to set
     */
    public void setDirectorios(ArrayList <String> directorios) {
        this.directorios = directorios;
    }

    /**
     * @return the ficheros
     */
    public ArrayList <TextFile> getFicheros() {
        return ficheros;
    }

    /**
     * @param ficheros the ficheros to set
     */
    public void setFicheros(ArrayList <TextFile> ficheros) {
        this.ficheros = ficheros;
    }
}
