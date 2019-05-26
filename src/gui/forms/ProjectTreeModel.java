/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.forms;

import java.io.File;
import java.io.Serializable;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author dennis
 */
public class ProjectTreeModel implements TreeModel,Serializable{
    private File root;
    public ProjectTreeModel(File root) { 
        this.root = root; 
    }
    @Override
    public Object getRoot() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        //throw new UnsupportedOperationException("Not supported yet.");
        String[] children = ((File)parent).list();
        if ((children == null) || (index >= children.length)) return null;
            return new File((File) parent, children[index]);
    }

    @Override
    public int getChildCount(Object parent) {
        //throw new UnsupportedOperationException("Not supported yet.");
        String[] children = ((File)parent).list();
        if (children == null) 
            return 0;
        return children.length;
    }

    @Override
    public boolean isLeaf(Object node) {
        // throw new UnsupportedOperationException("Not supported yet
        return ((File)node).isFile(); 
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
       // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        //throw new UnsupportedOperationException("Not supported yet.");
        String[] children = ((File)parent).list();
        if (children == null) return -1;
            String childname = ((File)child).getName();
            for(int i = 0; i < children.length; i++) {
                if (childname.equals(children[i])) 
                    return i;
            }
        return -1;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
       // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
