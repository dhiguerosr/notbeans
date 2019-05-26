/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.forms;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import notbeans.NotBeans;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import parsers.frc.managers.Err;
import parsers.frc.managers.ErrorManager;
import parsers.frc.managers.SymbolTableManager;
import parsers.frc.ts.Parser;
import parsers.frc.ts.Scanner;
import parsers.frc.ts.TSParser;

/**
 *
 * @author dennis
 */
public class MainFrame extends javax.swing.JFrame implements TreeSelectionListener{
    TabPanel tabs;
    JLabel pos;
    JTree p_tree;
    private Project project;
    DefaultTreeModel tree_model;
    DefaultMutableTreeNode root;
    
    /**
     * Creates new form MainFrame
     */
    
    
    private void initTree(){
        root = new DefaultMutableTreeNode("Projects");
        tree_model = new DefaultTreeModel(root);
        p_tree = new JTree(tree_model);
        p_tree.addTreeSelectionListener(this);
    }
    private void setEnabledButtons(boolean v){
        mnuNuevo.setEnabled(v);
        mnuGuardar.setEnabled(v);
        mnuGuardarComo.setEnabled(v);
        mnuNuevoArchivo.setEnabled(v);
    }
    public void init() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(this);
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle(NotBeans.TITLE + " :: " + NotBeans.VERSION);
        this.setVisible(true);
    }
    public MainFrame() {
        initComponents();
        setComponents();
        setEnabledButtons(false);
        
    }
    private void setComponents(){
        this.getContentPane().setLayout(new BorderLayout());
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        
        
        tabs = new TabPanel(this);
        pos = new JLabel("Ln:0 Col:0");
        p_tree = new JTree();
        initTree();
        
        //tree.setBorder(BorderFactory.createEtchedBorder());
        
        //tree.ad
        JScrollPane container_tree = new JScrollPane(p_tree);
        container_tree.setBorder(BorderFactory.createEtchedBorder());
        
        JPanel main_container = new JPanel();
        main_container.setLayout(new BorderLayout());
        JTextArea consola_txt = new JTextArea();
        
        
        DefaultCaret caret = (DefaultCaret)consola_txt.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        consola_txt.setCaret(caret);
        
        consola_txt.setMinimumSize(new Dimension(200,200));
        consola_txt.setAutoscrolls(true);
        
        JScrollPane panel_txt = new JScrollPane(consola_txt);
        panel_txt.setPreferredSize(new Dimension(200,150));
        container_tree.setPreferredSize(new Dimension(200,600));
        
        this.getContentPane().add(container_tree,BorderLayout.WEST);
        main_container.add(tabs,BorderLayout.CENTER);
        this.getContentPane().add(main_container,BorderLayout.CENTER);
        p_tree.setPreferredSize(new Dimension(200,600));
        
        container.add(panel_txt,BorderLayout.CENTER);
        container.add(pos,BorderLayout.SOUTH);
        this.getContentPane().add(container,BorderLayout.SOUTH);
        
        mnuGuardar.setEnabled(false);
        Console.init(consola_txt);
        //consola = new Consola(consola_txt);
        
    }
    public JMenuItem getMnuGuardar(){
        return mnuGuardar;
    }
    
    private void openFile(File in){
        if(TabPanel.existDirectory(tabs.getDirectorios(), in.getAbsolutePath())>=0){
            JOptionPane.showMessageDialog(this, "File is open","Open Error", 0);
            return;
        }
        try{
            BufferedReader br;
            try (FileReader fr = new FileReader(in)) {
                br = new BufferedReader(fr);
                tabs.newVentana(in.getAbsolutePath(),in.getName());
                tabs.setTitleAt(tabs.getTabCount()-1,in.getName());
                String line = br.readLine();
                String text = "";
                while(line!=null){
                    text += line+"\n";
                    line = br.readLine();
                }
                int count = tabs.getTabCount()-1;
                TextFile tf = tabs.getFicheros().get(count);
                tf.setText(text);
                tf.setState(true);
                tf.keyReleased(null);
                mnuGuardar.setEnabled(false);
            }
            br.close();
        }catch(IOException ex){
             System.out.println("Exception: "+ ex);
        }
        tabs.setSelectedIndex(tabs.getTabCount()-1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArchivo = new javax.swing.JMenu();
        mnuNewProject = new javax.swing.JMenuItem();
        mnuOpenProject = new javax.swing.JMenuItem();
        mnuAbrir = new javax.swing.JMenuItem();
        mnuNuevo = new javax.swing.JMenuItem();
        mnuGuardar = new javax.swing.JMenuItem();
        mnuGuardarComo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnuNuevoArchivo = new javax.swing.JMenu();
        mnuNuevoFUN = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuCerrar = new javax.swing.JMenuItem();
        mnuSalir = new javax.swing.JMenuItem();
        mnuEditar = new javax.swing.JMenu();
        mnuCopiar = new javax.swing.JMenuItem();
        mnuCortar = new javax.swing.JMenuItem();
        mnuPegar = new javax.swing.JMenuItem();
        mnuTodo = new javax.swing.JMenuItem();
        mnuGenerar = new javax.swing.JMenu();
        mnuAnalizar = new javax.swing.JMenuItem();
        mnuLexicos = new javax.swing.JMenuItem();
        mnuSintacticos = new javax.swing.JMenuItem();
        mnuSemanticos = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        mnuAyuda = new javax.swing.JMenu();

        jScrollPane1.setViewportView(tree);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mnuArchivo.setText("Archivo");

        mnuNewProject.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mnuNewProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/project.png"))); // NOI18N
        mnuNewProject.setText("Nuevo Proyecto");
        mnuNewProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNewProjectActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuNewProject);

        mnuOpenProject.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mnuOpenProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document-open.png"))); // NOI18N
        mnuOpenProject.setText("Abrir Proyecto");
        mnuOpenProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuOpenProjectActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuOpenProject);

        mnuAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document-open.png"))); // NOI18N
        mnuAbrir.setText("Abrir");
        mnuAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAbrirActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuAbrir);

        mnuNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document-new.png"))); // NOI18N
        mnuNuevo.setText("Nuevo Archivo");
        mnuNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNuevoActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuNuevo);

        mnuGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mnuGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document-save.png"))); // NOI18N
        mnuGuardar.setText("Guardar");
        mnuGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGuardarActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuGuardar);

        mnuGuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        mnuGuardarComo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document-save-as.png"))); // NOI18N
        mnuGuardarComo.setText("Guardar como");
        mnuGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGuardarComoActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuGuardarComo);
        mnuArchivo.add(jSeparator2);

        mnuNuevoArchivo.setText("Nuevo Archivo");

        mnuNuevoFUN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/New16.gif"))); // NOI18N
        mnuNuevoFUN.setText("Archivo FRC");
        mnuNuevoFUN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuNuevoFUNActionPerformed(evt);
            }
        });
        mnuNuevoArchivo.add(mnuNuevoFUN);

        mnuArchivo.add(mnuNuevoArchivo);
        mnuArchivo.add(jSeparator1);

        mnuCerrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        mnuCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/window-close.png"))); // NOI18N
        mnuCerrar.setText("Cerrar ");
        mnuCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCerrarActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuCerrar);

        mnuSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/window-close.png"))); // NOI18N
        mnuSalir.setText("Salir");
        mnuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSalirActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuSalir);

        jMenuBar1.add(mnuArchivo);

        mnuEditar.setText("Editar");

        mnuCopiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mnuCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit-copy.png"))); // NOI18N
        mnuCopiar.setText("Copiar");
        mnuCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCopiarActionPerformed(evt);
            }
        });
        mnuEditar.add(mnuCopiar);

        mnuCortar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        mnuCortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit-cut.png"))); // NOI18N
        mnuCortar.setText("Cortar");
        mnuCortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCortarActionPerformed(evt);
            }
        });
        mnuEditar.add(mnuCortar);

        mnuPegar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        mnuPegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit-paste.png"))); // NOI18N
        mnuPegar.setText("Pegar");
        mnuPegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPegarActionPerformed(evt);
            }
        });
        mnuEditar.add(mnuPegar);

        mnuTodo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        mnuTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit-select-all.png"))); // NOI18N
        mnuTodo.setText("Seleccionar Todo");
        mnuTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTodoActionPerformed(evt);
            }
        });
        mnuEditar.add(mnuTodo);

        jMenuBar1.add(mnuEditar);

        mnuGenerar.setText("Análisis");

        mnuAnalizar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_MASK));
        mnuAnalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gtk-yes.png"))); // NOI18N
        mnuAnalizar.setText("Analizar");
        mnuAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAnalizarActionPerformed(evt);
            }
        });
        mnuGenerar.add(mnuAnalizar);

        mnuLexicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/process-stop.png"))); // NOI18N
        mnuLexicos.setText("Errores Léxicos");
        mnuLexicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLexicosActionPerformed(evt);
            }
        });
        mnuGenerar.add(mnuLexicos);

        mnuSintacticos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/process-stop.png"))); // NOI18N
        mnuSintacticos.setText("Errores Sintácticos");
        mnuSintacticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSintacticosActionPerformed(evt);
            }
        });
        mnuGenerar.add(mnuSintacticos);

        mnuSemanticos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/process-stop.png"))); // NOI18N
        mnuSemanticos.setText("Errores Semánticos");
        mnuSemanticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSemanticosActionPerformed(evt);
            }
        });
        mnuGenerar.add(mnuSemanticos);
        mnuGenerar.add(jSeparator3);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/insert-image.png"))); // NOI18N
        jMenuItem3.setText("Generar Tabla de Simbolos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnuGenerar.add(jMenuItem3);

        jMenuBar1.add(mnuGenerar);

        mnuAyuda.setText("Ayuda");
        jMenuBar1.add(mnuAyuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 889, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void mnuAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAbrirActionPerformed

        JFileChooser files = new JFileChooser();
        files.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fractal Source File","frc");
        files.setFileFilter(filter);
        int fileI = files.showOpenDialog(this);
        if(fileI == JFileChooser.APPROVE_OPTION){
           File in = files.getSelectedFile();
           openFile(in);
        }
    }//GEN-LAST:event_mnuAbrirActionPerformed

    private void mnuNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNuevoActionPerformed
        // TODO add your handling code here:
        tabs.newVentana();
        tabs.setSelectedIndex(tabs.getTabCount()-1);
    }//GEN-LAST:event_mnuNuevoActionPerformed

    private void mnuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGuardarActionPerformed
        // TODO add your handling code here:
        int select = tabs.getSelectedIndex();
        JFileChooser files = new JFileChooser();
        files.setMultiSelectionEnabled(false);
        if(select<0)
               return;
        String title = tabs.getTitleAt(select);
        if(tabs.getDirectorios().get(select).compareTo("No saved")==0){
           int fileO = files.showSaveDialog(this);
           if(fileO == JFileChooser.APPROVE_OPTION){
              File fo = new File(files.getSelectedFile().getAbsolutePath());
              if(fo.isFile()){
                 JOptionPane.showMessageDialog(this, "File exists",
                         "Save As Error", 0);
                 return;
              }
              tabs.newSave(fo,select);
           }
        }else if(title.charAt(title.length()-1)=='*'){
           tabs.save(select);
           tabs.setTitleAt(select, title.substring(0, title.length()-1));
        }
    }//GEN-LAST:event_mnuGuardarActionPerformed

    private void mnuGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGuardarComoActionPerformed
        // TODO add your handling code here:}
        int select = tabs.getSelectedIndex();
        if(select<0)
               return;
        JFileChooser files = new JFileChooser();
        files.setMultiSelectionEnabled(false);
        int fs = files.showSaveDialog(this);
        if(fs==JFileChooser.APPROVE_OPTION){
            File fsave = new File(
                    files.getSelectedFile().getAbsoluteFile().toString());
            if(fsave.isFile()){
              JOptionPane.showMessageDialog(this, "File exists",
                      "Save As Error", 0);
              return;
            }
            tabs.newSave(fsave,select);
        }
    }//GEN-LAST:event_mnuGuardarComoActionPerformed

    private void mnuCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCerrarActionPerformed
        // TODO add your handling code here:
        int select = tabs.getSelectedIndex();
        tabs.close(select);
        
    }//GEN-LAST:event_mnuCerrarActionPerformed

    private void mnuCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCopiarActionPerformed
        // TODO add your handling code here:
        int select = tabs.getSelectedIndex();
        if(tabs.getFicheros().get(select).getSelectedText()!= null)
            tabs.getFicheros().get(select).copy();
    }//GEN-LAST:event_mnuCopiarActionPerformed

    private void mnuCortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCortarActionPerformed
        // TODO add your handling code here:
        int select = tabs.getSelectedIndex();
        if(select>=0){
            if(tabs.getFicheros().get(select).getSelectedText()!= null)
            {
                tabs.getFicheros().get(select).cut();
                tabs.getFicheros().get(select).setState(false);
                tabs.notSave(select);
            }
        }
    }//GEN-LAST:event_mnuCortarActionPerformed

    private void mnuPegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPegarActionPerformed
        // TODO add your handling code here:
        int select = tabs.getSelectedIndex();
        if(select>=0){
            if(tabs.getFicheros().get(select).getSelectedText()!= null)
            {
                tabs.getFicheros().get(select).paste();
                tabs.getFicheros().get(select).setState(false);
                tabs.notSave(select);
            }
        }
    }//GEN-LAST:event_mnuPegarActionPerformed

    private void mnuTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTodoActionPerformed
        // TODO add your handling code here:
        int select = tabs.getSelectedIndex();
        tabs.getFicheros().get(select).selectAll();
    }//GEN-LAST:event_mnuTodoActionPerformed

    private void mnuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSalirActionPerformed
        // TODO add your handling code here:
        if(tabs.notFileSaved())
            System.exit(0);
    }//GEN-LAST:event_mnuSalirActionPerformed

    private void mnuAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAnalizarActionPerformed
      
        int select = tabs.getSelectedIndex();
        String f_path = tabs.getDirectorios().get(select);
        if(f_path.compareTo("No saved")==0){
            JOptionPane.showMessageDialog(this,"Debe guardar el archivo antes de continuar","Analizar",1);
        }else{
            System.out.println(f_path);
           /* if(!TSParser.parse(f_path)){
                JOptionPane.showMessageDialog(this,"Imposible analizar el archivo","Error", JOptionPane.ERROR_MESSAGE);
            }*/
        }
        
    }//GEN-LAST:event_mnuAnalizarActionPerformed
    
    private void mnuLexicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLexicosActionPerformed
   
        new ErrorViewer(this,true).init(ErrorManager.getErrorList(Err.LEXICAL));
    }//GEN-LAST:event_mnuLexicosActionPerformed

    private void mnuNewProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNewProjectActionPerformed

       NewProject np = new NewProject(this,true);
       np.setLocationRelativeTo(this);
       np.setVisible(true);
       
       if(getProject()!=null){
           this.setTitle(getProject().getName()+" "+NotBeans.TITLE);
           
           updateModel();
            
            mnuGuardar.setEnabled(true);
            mnuGuardarComo.setEnabled(true);
            List<File> sources = getProject().getSources();
            for(File f : sources){
                tabs.newVentana(f.getAbsolutePath(),f.getName());
            }
            setEnabledButtons(true);
       }else
            JOptionPane.showMessageDialog(this, "Cancelado");
    }//GEN-LAST:event_mnuNewProjectActionPerformed
    private void updateModel(){
        if(getProject()!=null){
            root = new DefaultMutableTreeNode("Projects");
            tree_model = new DefaultTreeModel(root);
            tree_model.insertNodeInto(getProject().getProject_node(), root, 0);
            p_tree.setModel(tree_model);
            setTreeState(p_tree,true);
        }
    }
    private void mnuOpenProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOpenProjectActionPerformed
        // TODO add your handling code here:
        if(tabs.notFileSaved()){
            JFileChooser files = new JFileChooser();
            files.setMultiSelectionEnabled(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fractal Project","pj");
            files.setFileFilter(filter);
            int op = files.showOpenDialog(this);
            if(op==JFileChooser.APPROVE_OPTION){
                File in = files.getSelectedFile();
                if(in!=null){
                    String url = in.getParentFile().getAbsolutePath() +"/src/";
                    File f = new File(url);
                    if(f.exists()){
                        ArrayList<File> sources = new ArrayList<>();
                        String[] files_src = f.list();
                        for(String file_src:files_src){
                            //System.out.println(file_src);
                            File file = new File(url+file_src);
                            if(!file.isDirectory()&&file.exists()){
                                sources.add(file);
                                openFile(file);
                            }
                        }
                        project = new Project(in.getName(),f,sources);
                        updateModel();
                        ProjectManager.setCurrentProject(project);
                        if(getProject()!=null)
                            setEnabledButtons(true);
                    }else{
                        JOptionPane.showMessageDialog(this,"El archivo no contiene fuentes","Error",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_mnuOpenProjectActionPerformed

    private void mnuSintacticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSintacticosActionPerformed
        // TODO add your handling code here:
        new ErrorViewer(this,true).init(ErrorManager.getErrorList(Err.SYNTACTIC));
    }//GEN-LAST:event_mnuSintacticosActionPerformed

    private void mnuSemanticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSemanticosActionPerformed
        // TODO add your handling code here:
        new ErrorViewer(this,true).init(ErrorManager.getErrorList(Err.SEMANTIC));
    }//GEN-LAST:event_mnuSemanticosActionPerformed

    private void mnuNuevoFUNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNuevoFUNActionPerformed
        // TODO add your handling code here:
        String s = JOptionPane.showInputDialog(this,"Ingrese el nombre del archivo","Nuevo Archivo FUN", JOptionPane.OK_CANCEL_OPTION);
        if(s!=null){
            File f = getProject().addFile(s,"frc");
            if(f!=null){
                updateModel();
                tabs.newVentana(f.getAbsolutePath(),f.getName());
            }

        }
    }//GEN-LAST:event_mnuNuevoFUNActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        if(SymbolTableManager.getContext()!=null){
            new SymbolTableViewer(this,false).init();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem mnuAbrir;
    private javax.swing.JMenuItem mnuAnalizar;
    private javax.swing.JMenu mnuArchivo;
    private javax.swing.JMenu mnuAyuda;
    private javax.swing.JMenuItem mnuCerrar;
    private javax.swing.JMenuItem mnuCopiar;
    private javax.swing.JMenuItem mnuCortar;
    private javax.swing.JMenu mnuEditar;
    private javax.swing.JMenu mnuGenerar;
    private javax.swing.JMenuItem mnuGuardar;
    private javax.swing.JMenuItem mnuGuardarComo;
    private javax.swing.JMenuItem mnuLexicos;
    private javax.swing.JMenuItem mnuNewProject;
    private javax.swing.JMenuItem mnuNuevo;
    private javax.swing.JMenu mnuNuevoArchivo;
    private javax.swing.JMenuItem mnuNuevoFUN;
    private javax.swing.JMenuItem mnuOpenProject;
    private javax.swing.JMenuItem mnuPegar;
    private javax.swing.JMenuItem mnuSalir;
    private javax.swing.JMenuItem mnuSemanticos;
    private javax.swing.JMenuItem mnuSintacticos;
    private javax.swing.JMenuItem mnuTodo;
    private javax.swing.JTree tree;
    // End of variables declaration//GEN-END:variables

    void setProject(Project project) {
        this.project = project;
        ProjectManager.setCurrentProject(project);
    }
    
    public static void setTreeState(JTree tree, boolean expanded) {
        Object root = tree.getModel().getRoot();
        setTreeState(tree, new TreePath(root),expanded);
    }
  
    public static void setTreeState(JTree tree, TreePath path, boolean expanded) {
        Object lastNode = path.getLastPathComponent();
        for (int i = 0; i < tree.getModel().getChildCount(lastNode); i++) {
        Object child = tree.getModel().getChild(lastNode,i);
        TreePath pathToChild = path.pathByAddingChild(child);
        setTreeState(tree,pathToChild,expanded);
        }
        if (expanded) 
        tree.expandPath(path);
        else
        tree.collapsePath(path);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        try{
            String name = p_tree.getSelectionPath().getLastPathComponent().toString();
            if(name.contains(".")&&!name.endsWith("proj")){
                if(getProject()!=null){
                    File f = new File(getProject().getRoot_folder()+"/"+name);
                    if(f.exists()){
                        openFile(f);
                    }
                }

            }
        }catch(Exception exp){}
        
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }
}