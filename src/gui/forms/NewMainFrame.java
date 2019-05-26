/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.forms;
import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import static gui.forms.MainFrame.setTreeState;
import notbeans.NotBeans;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import parsers.frc.managers.Err;
import parsers.frc.managers.ErrorManager;
import parsers.frc.managers.SymbolTableManager;
import parsers.frc.ts.TSParser;
import parsers.tree3d.ICParser;
import parsers.tree3d.nodes.Node;


/**
 *
 * @author Dennis
 */
public class NewMainFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewMainFrame
     */
    TabPanel tabs;
    DefaultMutableTreeNode projectsNode;
    public NewMainFrame() {
        initComponents();
        setEnabledButtons(false);
        DefaultCaret caret = (DefaultCaret)txtConsole.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        txtConsole.setCaret(caret);
        txtConsole.setAutoscrolls(true);
        tabs = new TabPanel(this);
        tabsPane.add(tabs,BorderLayout.CENTER);
        projectsNode = new DefaultMutableTreeNode("Proyectos");
        fileTree.setModel(new DefaultTreeModel(projectsNode));
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
    private void updateModel(){
        projectsNode = new DefaultMutableTreeNode("Proyectos");
        fileTree.setModel(new DefaultTreeModel(projectsNode));
        for(Project p: ProjectManager.getProjects()){
            ((DefaultTreeModel)fileTree.getModel()).insertNodeInto(p.getProject_node(), projectsNode, projectsNode.getChildCount());
        }
        setTreeState(fileTree,true);
        /*if(getProject()!=null){
            root = new DefaultMutableTreeNode("Projects");
            tree_model = new DefaultTreeModel(root);
            tree_model.insertNodeInto(getProject().getProject_node(), root, 0);
            p_tree.setModel(tree_model);
            setTreeState(p_tree,true);
        }*/
    }
    
    private void setEnabledButtons(boolean v){
        mnuNuevo.setEnabled(v);
        mnuGuardar.setEnabled(v);
        mnuGuardarComo.setEnabled(v);
        mnuNuevo.setEnabled(v);
    }
    
    public JMenuItem getMnuGuardar(){
        return mnuGuardar;
    }
    
     private void openFile(File in){
        if(TabPanel.existDirectory(tabs.getDirectorios(), in.getAbsolutePath())>=0){
            int i = 0;
            for(String f:tabs.getDirectorios()){
                if(f.equals(in.getAbsolutePath())){
                    tabs.setSelectedIndex(i);
                    return;
                }
                i++;
            }
            //JOptionPane.showMessageDialog(this, "File is open","Open Error", 0);
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
    
    public void init() {
        /*try {
            UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(NewMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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
        Console.init(txtConsole);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        splitPane = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        fileTree = new javax.swing.JTree();
        tabsPane = new javax.swing.JPanel();
        pos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArchivo = new javax.swing.JMenu();
        mnuNewProject = new javax.swing.JMenuItem();
        mnuOpenProject = new javax.swing.JMenuItem();
        mnuAbrir = new javax.swing.JMenuItem();
        mnuNuevo = new javax.swing.JMenuItem();
        mnuGuardar = new javax.swing.JMenuItem();
        mnuGuardarComo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnuSalir = new javax.swing.JMenuItem();
        mnuEditar = new javax.swing.JMenu();
        mnuCopiar = new javax.swing.JMenuItem();
        mnuCortar = new javax.swing.JMenuItem();
        mnuPegar = new javax.swing.JMenuItem();
        mnuTodo = new javax.swing.JMenuItem();
        mnuGenerar = new javax.swing.JMenu();
        mnuAnalizar = new javax.swing.JMenuItem();
        mnuGen3D = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        mnuExecute = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mnuLexicos = new javax.swing.JMenuItem();
        mnuSemanticos = new javax.swing.JMenuItem();
        mnuSintacticos = new javax.swing.JMenuItem();
        mnuAyuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(750);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setOneTouchExpandable(true);

        splitPane.setDividerLocation(200);
        splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(true);

        fileTree.setPreferredSize(new java.awt.Dimension(200, 600));
        fileTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fileTreeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(fileTree);

        splitPane.setLeftComponent(jScrollPane2);

        tabsPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tabsPane.setLayout(new java.awt.BorderLayout());

        pos.setBackground(new java.awt.Color(204, 204, 204));
        pos.setText("Ln: 0 Col: 0");
        pos.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pos.setOpaque(true);
        tabsPane.add(pos, java.awt.BorderLayout.PAGE_END);

        splitPane.setRightComponent(tabsPane);

        jSplitPane1.setTopComponent(splitPane);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(200, 23));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 96));

        txtConsole.setEditable(false);
        txtConsole.setColumns(20);
        txtConsole.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        txtConsole.setRows(5);
        txtConsole.setText("Console:");
        jScrollPane1.setViewportView(txtConsole);

        jSplitPane1.setBottomComponent(jScrollPane1);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

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

        mnuGen3D.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document-export.png"))); // NOI18N
        mnuGen3D.setText("Generar Código Intermedio");
        mnuGen3D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGen3DActionPerformed(evt);
            }
        });
        mnuGenerar.add(mnuGen3D);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/insert-image.png"))); // NOI18N
        jMenuItem2.setText("Generar Grafo Codigo Intermedio");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mnuGenerar.add(jMenuItem2);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/system-upgrade.png"))); // NOI18N
        jMenuItem1.setText("Optimizar Código Intermedio");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnuGenerar.add(jMenuItem1);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/system-upgrade.png"))); // NOI18N
        jMenuItem4.setText("Optimización Código Intermedio (Global)");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        mnuGenerar.add(jMenuItem4);

        mnuExecute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arrow-right.png"))); // NOI18N
        mnuExecute.setText("Ejecutar");
        mnuExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExecuteActionPerformed(evt);
            }
        });
        mnuGenerar.add(mnuExecute);
        mnuGenerar.add(jSeparator3);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/view-restore.png"))); // NOI18N
        jMenuItem3.setText("Generar Tabla de Simbolos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnuGenerar.add(jMenuItem3);

        jMenuBar1.add(mnuGenerar);

        jMenu1.setText("Errores");

        mnuLexicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/process-stop.png"))); // NOI18N
        mnuLexicos.setText("Errores Léxicos");
        mnuLexicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLexicosActionPerformed(evt);
            }
        });
        jMenu1.add(mnuLexicos);

        mnuSemanticos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/process-stop.png"))); // NOI18N
        mnuSemanticos.setText("Errores Semánticos");
        mnuSemanticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSemanticosActionPerformed(evt);
            }
        });
        jMenu1.add(mnuSemanticos);

        mnuSintacticos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/process-stop.png"))); // NOI18N
        mnuSintacticos.setText("Errores Sintácticos");
        mnuSintacticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSintacticosActionPerformed(evt);
            }
        });
        jMenu1.add(mnuSintacticos);

        jMenuBar1.add(jMenu1);

        mnuAyuda.setText("Ayuda");
        jMenuBar1.add(mnuAyuda);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuNewProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNewProjectActionPerformed
        
        Project p = new NewProject(this,true).init();
        if(p!=null){
            //this.setTitle(getProject().getName()+" "+JBCF.TITLE);
                        
            ProjectManager.addProject(p);
            //updateModel(p);
            
            ((DefaultTreeModel)fileTree.getModel()).insertNodeInto(ProjectManager.getCurrentProject().getProject_node(), 
                                projectsNode, projectsNode.getChildCount());
                        for (int i = 0; i < fileTree.getRowCount(); i++)
                            fileTree.expandRow(i);

            mnuGuardar.setEnabled(true);
            mnuGuardarComo.setEnabled(true);
            List<File> sources = p.getSources();
            for(File f : sources){
                tabs.newVentana(f.getAbsolutePath(),f.getName());
            }
            setEnabledButtons(true);
        }else
        JOptionPane.showMessageDialog(this, "Cancelado");
    }//GEN-LAST:event_mnuNewProjectActionPerformed

    private void mnuOpenProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOpenProjectActionPerformed
        // TODO add your handling code here:
        
        if(tabs.notFileSaved()){
            JFileChooser files = new JFileChooser();
            files.setMultiSelectionEnabled(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fractal Project","pj");
            File _path = new File(System.getProperty("user.home")+"\\FractalDraw Projects");
            if(_path.exists())
                files.setCurrentDirectory(_path);
            files.setFileFilter(filter);
            int op = files.showOpenDialog(this);
            if(op==JFileChooser.APPROVE_OPTION){
                File in = files.getSelectedFile();
                if(in!=null){
                    String url = in.getParentFile().getAbsolutePath() +"/src/";
                    File f = new File(url);
                    if(f.exists()){
                        FileReader fr;
                        try {
                            fr = new FileReader(in.getAbsoluteFile());
                            Console.println("Abriendo archivo..  "+ in.getAbsolutePath() +"...");
                            parsers.pj.Scanner lex = new parsers.pj.Scanner(fr);
                            parsers.pj.Parser miParser=new parsers.pj.Parser(lex);
                            miParser.parse();
                            
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(NewMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(NewMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ProjectManager.getCurrentProject().setPjFile(in);
                        ((DefaultTreeModel)fileTree.getModel()).insertNodeInto(ProjectManager.getCurrentProject().getProject_node(), 
                                projectsNode, projectsNode.getChildCount());
                        for (int i = 0; i < fileTree.getRowCount(); i++)
                            fileTree.expandRow(i);
                        ArrayList<File> ss = ProjectManager.getCurrentProject().getSources();
                        for(File file :ss){
                            if(!file.isDirectory()&&file.exists()){
                                openFile(file);
                            }
                        }
                        setEnabledButtons(true);
                        
                    }else{
                        JOptionPane.showMessageDialog(this,"El proyecto no contiene fuentes","Error",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_mnuOpenProjectActionPerformed

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
        /*
        tabs.newVentana();
        tabs.setSelectedIndex(tabs.getTabCount()-1);*/
        String s = JOptionPane.showInputDialog(this,"Ingrese el nombre del archivo","Nuevo Archivo FRC", JOptionPane.OK_CANCEL_OPTION);
        if(s!=null){
            File f = ProjectManager.getCurrentProject().addFile(s,"frc");
            if(f!=null){
                updateModel();
                tabs.newVentana(f.getAbsolutePath(),f.getName());
            }

        }
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

    private void mnuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSalirActionPerformed
        // TODO add your handling code here:
        if(tabs.notFileSaved())
        System.exit(0);
    }//GEN-LAST:event_mnuSalirActionPerformed

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

    private void mnuAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAnalizarActionPerformed

        int select = tabs.getSelectedIndex();
        String f_path = tabs.getDirectorios().get(select);
        if(f_path.compareTo("No saved")==0){
            JOptionPane.showMessageDialog(this,"Debe guardar el archivo antes de continuar","Analizar",1);
        }else{
            File f = new File(f_path);
            ProjectManager.setCurrentProject(f.getParentFile().getAbsolutePath());
            System.out.println(f.getParentFile().getAbsolutePath());
            if(!TSParser.parse()){
                JOptionPane.showMessageDialog(this,"Imposible analizar el archivo","Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_mnuAnalizarActionPerformed

    private void mnuLexicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLexicosActionPerformed

        new ErrorViewer(this,true).init(ErrorManager.getErrorList(Err.LEXICAL));
    }//GEN-LAST:event_mnuLexicosActionPerformed

    private void mnuSintacticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSintacticosActionPerformed
        // TODO add your handling code here:
        new ErrorViewer(this,true).init(ErrorManager.getErrorList(Err.SYNTACTIC));
    }//GEN-LAST:event_mnuSintacticosActionPerformed

    private void mnuSemanticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSemanticosActionPerformed
        // TODO add your handling code here:
        new ErrorViewer(this,true).init(ErrorManager.getErrorList(Err.SEMANTIC));
    }//GEN-LAST:event_mnuSemanticosActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        if(SymbolTableManager.getContext()!=null){
            new SymbolTableViewer(this,false).init();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void fileTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileTreeMouseClicked
        // TODO add your handling code here:
       if(evt.getClickCount()==2){
           Object obj = fileTree.getSelectionPath().getLastPathComponent();
            String name = obj.toString();
            if(name.contains(".")&&name.endsWith("frc")){
                Object parent = fileTree.getSelectionPath().getParentPath().getLastPathComponent();
                Project p = ProjectManager.getProject(String.valueOf(parent));
                File f = new File(p.getRoot_folder()+"\\src\\"+name);
                if(f.exists()){
                    openFile(f);
                }
                    
                
             }
           
       }
    }//GEN-LAST:event_fileTreeMouseClicked

    private void mnuGen3DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGen3DActionPerformed
        // TODO add your handling code here:
        Console.init(txtConsole);
        Node.init();
        ICParser.parse(ProjectManager.getCurrentProject().getMainFile().getAbsolutePath());
        File buildFolder = new File(ProjectManager.getCurrentProject().getRoot_folder()+"\\build");
        if(!buildFolder.exists()){
               boolean success =  buildFolder.mkdir();
        }
        Node.toFile(buildFolder.getAbsolutePath()+"\\output.3dr");
    }//GEN-LAST:event_mnuGen3DActionPerformed

    private void mnuExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExecuteActionPerformed
        // TODO add your handling code here:
        
        JFileChooser files = new JFileChooser();
        files.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fractal 3DR","3dr");
        files.setFileFilter(filter);
        files.setCurrentDirectory(new File(ProjectManager.getCurrentProject().getRoot_folder()+"\\build\\"));
        int op = files.showOpenDialog(this);
        if(op==JFileChooser.APPROVE_OPTION){
            Console.init(txtConsole);
            String main = ProjectManager.getCurrentProject().getMainFile().getName().replace(".frc","_main");
           
            File in = files.getSelectedFile();
            jbc.parser.Executer.parse(in.getAbsolutePath());
            //String main = JOptionPane.showInputDialog("Ingrese nombre método principal");
   
            try{
                jbc.parser.nodes.CodeManager.execute(main);
            }catch(Exception ex){
                main = JOptionPane.showInputDialog("El nombre del archivo no coincide, Ingrese el nombre de la clase");
                jbc.parser.nodes.CodeManager.execute(main);
            }
        }
        
    }//GEN-LAST:event_mnuExecuteActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JFileChooser files = new JFileChooser();
        files.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fractal 3DR","3dr");
        files.setFileFilter(filter);
        files.setCurrentDirectory(new File(ProjectManager.getCurrentProject().getRoot_folder()+"\\build\\"));
        int op = files.showOpenDialog(this);
        if(op==JFileChooser.APPROVE_OPTION){
            Console.init(txtConsole);
            //String main = ProjectManager.getCurrentProject().getMainFile().getName().replace(".frc","_main");
           
            File in = files.getSelectedFile();
            jbc.parser.Executer.parse(in.getAbsolutePath());
            //String main = JOptionPane.showInputDialog("Ingrese nombre método principal");
            jbc.parser.nodes.CodeManager.optimize();
           
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        JFileChooser files = new JFileChooser();
        files.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fractal 3DR","3dr");
        files.setFileFilter(filter);
        files.setCurrentDirectory(new File(ProjectManager.getCurrentProject().getRoot_folder()+"\\build\\"));
        int op = files.showOpenDialog(this);
        if(op==JFileChooser.APPROVE_OPTION){
            Console.init(txtConsole);
            //String main = ProjectManager.getCurrentProject().getMainFile().getName().replace(".frc","_main");
           
            File in = files.getSelectedFile();
            jbc.parser.Executer.parse(in.getAbsolutePath());
            //String main = JOptionPane.showInputDialog("Ingrese nombre método principal");
            jbc.parser.nodes.CodeManager.graph(in.getAbsolutePath().replaceAll("3dr", "png"));
           
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
         JFileChooser files = new JFileChooser();
        files.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fractal 3DR","3dr");
        files.setFileFilter(filter);
        files.setCurrentDirectory(new File(ProjectManager.getCurrentProject().getRoot_folder()+"\\build\\"));
        int op = files.showOpenDialog(this);
        if(op==JFileChooser.APPROVE_OPTION){
            Console.init(txtConsole);
            //String main = ProjectManager.getCurrentProject().getMainFile().getName().replace(".frc","_main");
           
            File in = files.getSelectedFile();
            jbc.parser.Executer.parse(in.getAbsolutePath());
            //String main = JOptionPane.showInputDialog("Ingrese nombre método principal");
            jbc.parser.nodes.CodeManager.optimizeGlobal();
           
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NewMainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree fileTree;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JMenuItem mnuAbrir;
    private javax.swing.JMenuItem mnuAnalizar;
    private javax.swing.JMenu mnuArchivo;
    private javax.swing.JMenu mnuAyuda;
    private javax.swing.JMenuItem mnuCopiar;
    private javax.swing.JMenuItem mnuCortar;
    private javax.swing.JMenu mnuEditar;
    private javax.swing.JMenuItem mnuExecute;
    private javax.swing.JMenuItem mnuGen3D;
    private javax.swing.JMenu mnuGenerar;
    private javax.swing.JMenuItem mnuGuardar;
    private javax.swing.JMenuItem mnuGuardarComo;
    private javax.swing.JMenuItem mnuLexicos;
    private javax.swing.JMenuItem mnuNewProject;
    private javax.swing.JMenuItem mnuNuevo;
    private javax.swing.JMenuItem mnuOpenProject;
    private javax.swing.JMenuItem mnuPegar;
    private javax.swing.JMenuItem mnuSalir;
    private javax.swing.JMenuItem mnuSemanticos;
    private javax.swing.JMenuItem mnuSintacticos;
    private javax.swing.JMenuItem mnuTodo;
    public javax.swing.JLabel pos;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JPanel tabsPane;
    private javax.swing.JTextArea txtConsole;
    // End of variables declaration//GEN-END:variables
}
