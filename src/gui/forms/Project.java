/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.forms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author dennis
 */
public class Project {
    private File root_folder;
    private ArrayList <File> sources;
    private File mainFile;
    private String name;
    private File pjFile;
    private DefaultMutableTreeNode project_node;
    
    public Project(String name,File root_folder,File main){
        this.root_folder =root_folder;
        this.name = name;
        sources = new ArrayList<>();
        sources.add(main);
        this.mainFile = main;
        project_node = new DefaultMutableTreeNode(name);
        project_node.setUserObject(this);
        DefaultMutableTreeNode src = new DefaultMutableTreeNode("Source Files");
        List<File> source_files = sources;
        for(File f: source_files){
            DefaultMutableTreeNode file = new DefaultMutableTreeNode(f.getName());
            project_node.add(file);
        }
    }
    
    public Project(String name, String root, Stack<String> files,String main ){
        this.root_folder = new File(root);
        this.name = name;
        sources = new ArrayList<>();
        while(!files.isEmpty()){
            sources.add(new File(root+"\\src\\"+files.pop()));
        }
        mainFile = new File(root+"\\src\\"+main);
        project_node = new DefaultMutableTreeNode(name);
        project_node.setUserObject(this);
        DefaultMutableTreeNode src = new DefaultMutableTreeNode("Source Files");
        List<File> source_files = sources;
        for(File f: source_files){
            DefaultMutableTreeNode file = new DefaultMutableTreeNode(f.getName());
            project_node.add(file);
        }
    }
    
     public Project(String name, String root, Stack<String> files ){
        this.root_folder = new File(root);
        sources = new ArrayList<>();
        while(!files.isEmpty()){
            sources.add(new File(root+"\\src\\"+files.pop()));
        }
        mainFile = null;
        project_node = new DefaultMutableTreeNode(name);
        project_node.setUserObject(this);
        DefaultMutableTreeNode src = new DefaultMutableTreeNode("Source Files");
        List<File> source_files = sources;
        for(File f: source_files){
            DefaultMutableTreeNode file = new DefaultMutableTreeNode(f.getName());
            project_node.add(file);
        }
    }
     
    public Project(String name,File root_folder,ArrayList<File> sources){
        this.root_folder =root_folder;
        this.name = name;
        project_node = new DefaultMutableTreeNode(name);
        DefaultMutableTreeNode src = new DefaultMutableTreeNode("Source Files");
        project_node.setUserObject(this);
        this.sources = sources;
        List<File> source_files = sources;
        for(File f: source_files){
            DefaultMutableTreeNode file = new DefaultMutableTreeNode(f.getName());
            project_node.add(file);
        }
    }
    private void updateModel(){
        project_node = new DefaultMutableTreeNode(name);
        DefaultMutableTreeNode src = new DefaultMutableTreeNode("Source Files");
        project_node.setUserObject(this);
        List<File> source_files = sources;
        for(File f: source_files){
            DefaultMutableTreeNode file = new DefaultMutableTreeNode(f.getName());
            project_node.add(file);
        }  
    }
    public File addFile(String nombre,String ext){
        try {
            String url = this.root_folder.getAbsolutePath()+"\\src\\"+nombre+"."+ext;
            File f = new File(url);
            if(!f.createNewFile()){
                JOptionPane.showMessageDialog(null,"El Archivo ya existe","Error",JOptionPane.ERROR_MESSAGE);
                return null;
            }else{
                f = new File(url);
                this.sources.add(f);
                printFile();
                updateModel();
                return f;
            }
        } catch (IOException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
   
    private void printFile(){
        
        FileWriter proj = null;
        PrintWriter pw;
        try{
            mainFile.createNewFile();
            proj = new FileWriter(this.getPjFile().getAbsoluteFile());
            pw = new PrintWriter(proj);
            pw.print(toXML());
        } catch (Exception e) {return;} 
        finally {
           try{
           if (proj!=null)
               proj.close();
           } catch (Exception e2) {return;}
        }
         
    }
    
    @Override
    public String toString(){
        return this.getName();
    }
    public String toXML(){
        String s = new String();
        s += "<Proyecto nombre = \"" + this.getName() + "\" ruta = \"" + this.getRoot_folder() + "\">\n";
        s += "\t<Archivos>\n";
        for(File f : sources){
            s+= "\t\t<Archivo nombre = \"" +f.getName() + "\"/>\n";
        }
        s += "\t</Archivos>\n";
        if(mainFile!=null){
            s += "\t<Principal>\n";
                s+= "\t\t<Archivo nombre = \"" +mainFile.getName() + "\"/>\n";
            s += "\t</Principal>\n";  
        }
        s += "</Proyecto>";
        return s;
    }
    

    /**
     * @return the root_folder
     */
    public File getRoot_folder() {
        return root_folder;
    }

    /**
     * @param root_folder the root_folder to set
     */
    public void setRoot_folder(File root_folder) {
        this.root_folder = root_folder;
    }

    /**
     * @return the sources
     */
    public ArrayList <File> getSources() {
        return sources;
    }

    /**
     * @param sources the sources to set
     */
    public void setSources(ArrayList <File> sources) {
        this.sources = sources;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the project_node
     */
    public DefaultMutableTreeNode getProject_node() {
        return project_node;
    }

    /**
     * @param project_node the project_node to set
     */
    public void setProject_node(DefaultMutableTreeNode project_node) {
        this.project_node = project_node;
    }

    /**
     * @return the mainFile
     */
    public File getMainFile() {
        return mainFile;
    }

    /**
     * @param mainFile the mainFile to set
     */
    public void setMainFile(File mainFile) {
        this.mainFile = mainFile;
    }

    /**
     * @return the pjFile
     */
    public File getPjFile() {
        return pjFile;
    }

    /**
     * @param pjFile the pjFile to set
     */
    public void setPjFile(File pjFile) {
        this.pjFile = pjFile;
    }
    
}
