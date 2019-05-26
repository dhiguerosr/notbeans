/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.forms;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
/**
 *
 * @author Dennis
 */
public class ProjectManager {
    private static Project currentProject;
    private static ArrayList<Project> projects = new ArrayList<>();
    private static DefaultTreeModel model;
    public static void init(DefaultTreeModel m){
        model = m;
    }
    public static void setCurrentProject(Project p){
        currentProject = p;
    }
    public static void setCurrentProject(String root){
        for(Project p: projects){
            if(root.equals(p.getRoot_folder()+"\\src")){
                currentProject = p;
            }
        }
    }
    public static String getFile(String name){
        for(File f: getCurrentProject().getSources()){
            if(name.equals(f.getName())){
                return f.getAbsolutePath();
            }
        }
        return null;
    }
    
    public static Project getProject(String name){
        for(Project p: projects){
            if(name.equals(p.getName())){
                return p;
            }
        }
        return null;
    }

    /**
     * @return the currentProject
     */
    public static Project getCurrentProject() {
        return currentProject;
    }

    /**
     * @return the projects
     */
    public static ArrayList<Project> getProjects() {
        return projects;
    }

    /**
     * @param aProjects the projects to set
     */
    public static void setProjects(ArrayList<Project> aProjects) {
        projects = aProjects;
    }
    
    public static boolean addProject(Project p){
        if(p.getRoot_folder().exists() || p.getMainFile().exists() && new File(p.getRoot_folder().getAbsolutePath()+"\\src\\").exists()){
            for(File f : p.getSources()){
                if(!f.exists()){
                    JOptionPane.showMessageDialog(null,"No se encontró el archivo "+ f.getName(),"Error",JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            projects.add(p);
            setCurrentProject(p);
            if(p.getMainFile()==null||!p.getMainFile().exists()){
                JOptionPane.showMessageDialog(null,"No se encontró el archivo principal","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        }else{
            JOptionPane.showMessageDialog(null,"No se encontró el directorio raiz","Error",JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
