/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import parsers.frc.managers.Sym;
import parsers.frc.managers.SymbolTable;
import parsers.frc.managers.SymbolTableManager;

/**
 *
 * @author Dennis
 */
public class SymbolTableViewer extends javax.swing.JDialog {

    /**
     * Creates new form SymbolTableViewer
     */
    ArrayList<String> contexto;
    Stack<TableModel> models;
    public SymbolTableViewer(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ((JComponent) tblSymbolTable.getDefaultRenderer(Boolean.class)).setOpaque(true);
        setLocationRelativeTo(parent);
        contexto = new ArrayList<>();
    }
    
    public void init(){
        this.addSymbols(SymbolTableManager.getContext().getContext().getInherits(),0,true);
        this.addSymbols(SymbolTableManager.getContext().getContext().getSymbolTable(),0,false);
        models = new Stack<>();
        contexto.add(SymbolTableManager.getContext().getName());
        setContexto();
        this.setVisible(true);
    }
    private void setContexto(){
        String s = contexto.get(0);
        for(int i = 1;i<contexto.size();i++){
            s+="."+contexto.get(i);
        }
        lblContexto.setText(s);
    }
    private void setEmptyModel(){
        tblSymbolTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Simbolo", "Acceso", "Tipo", "Array", "N. Dim.", "Static", "Función", "Param.", "P. Ref.", "Tamaño", "Heredado", "Ámbito", "Posición"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, 
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.Boolean.class, 
                java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false,false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    private void  addSymbols(HashMap<String,Sym> st,int ambito,boolean heredado){
        addSymbols(st,ambito,heredado,false);
    }
    private void  addSymbols(HashMap<String,Sym> st,int ambito,boolean heredado,boolean imported){
        int x = tblSymbolTable.getRowCount();
        for(String key: st.keySet()){
            Sym s = st.get(key);
            if(s!=null){
                Object[] data = new Object[]{
                   x,
                   s,
                   (s.getAccessType()==Sym.PUBLIC_ACCESS)?"public":"private",
                   (s.getType()==null)?"void":s.getType().getName(),
                   s.isArray(),
                   s.getArrayDimCount(),
                   s.isStatic(),
                   s.isFunction(),
                   s.isParam(),
                   s.isReference(),
                   s.getSize(),
                   heredado,
                   ambito,
                   (s.getPosition()!=null)?s.getPosition():""
                };
                if(imported&&(s.getAccessType()==Sym.PRIVATE_ACCESS||s.isStatic()))
                    continue;
                ((DefaultTableModel)tblSymbolTable.getModel()).addRow(data);
                x++;
            }
        }
    }
    private int load(SymbolTable stt,int ambito){
        if(stt!=null){
            addSymbols(stt.getInherits(),ambito,true);
            addSymbols(stt.getSymbolTable(),ambito,false);
            for(SymbolTable st: stt.getNodes()){
                ambito = load(st,ambito+1);
            }
        }
        return ambito;
    }
    private int load(SymbolTable stt,int ambito,boolean heredado){
        if(stt!=null){
            addSymbols(stt.getInherits(),ambito,true,heredado);
            addSymbols(stt.getSymbolTable(),ambito,false,heredado);
            for(SymbolTable st: stt.getNodes()){
                ambito = load(st,ambito+1);
            }
        }
        return ambito;
    }

    private void clearSymbolTable(){
        setContexto();
        models.push((DefaultTableModel) tblSymbolTable.getModel());
        setEmptyModel();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSymbolTable = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        btnBack = new javax.swing.JButton();
        lblContexto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setText("TABLA DE SIMBOLOS");

        tblSymbolTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Simbolo", "Acceso", "Tipo", "Array", "N. Dim.", "Static", "Función", "Param.", "P. Ref.", "Tamaño", "Heredado", "Ámbito", "Posición"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSymbolTable.setRowHeight(20);
        tblSymbolTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSymbolTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSymbolTable);
        tblSymbolTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSymbolTable.getColumnModel().getColumn(2).setResizable(false);
        tblSymbolTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblSymbolTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblSymbolTable.getColumnModel().getColumn(4).setResizable(false);
        tblSymbolTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblSymbolTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        tblSymbolTable.getColumnModel().getColumn(6).setResizable(false);
        tblSymbolTable.getColumnModel().getColumn(6).setPreferredWidth(50);
        tblSymbolTable.getColumnModel().getColumn(7).setResizable(false);
        tblSymbolTable.getColumnModel().getColumn(7).setPreferredWidth(50);
        tblSymbolTable.getColumnModel().getColumn(8).setPreferredWidth(50);
        tblSymbolTable.getColumnModel().getColumn(9).setPreferredWidth(50);
        tblSymbolTable.getColumnModel().getColumn(10).setResizable(false);
        tblSymbolTable.getColumnModel().getColumn(10).setPreferredWidth(65);
        tblSymbolTable.getColumnModel().getColumn(11).setPreferredWidth(65);
        tblSymbolTable.getColumnModel().getColumn(12).setResizable(false);
        tblSymbolTable.getColumnModel().getColumn(12).setPreferredWidth(65);

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnBack.setText("Regresar");
        btnBack.setEnabled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblContexto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblContexto.setText("[contexto]");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblContexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 10, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBack)
                    .addComponent(lblContexto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(366, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(186, 186, 186)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(221, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSymbolTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSymbolTableMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
           int row = tblSymbolTable.getSelectedRow();
           if(row>=0){
               Sym s = (Sym) tblSymbolTable.getValueAt(row,1);
               if(s.isFunction()){
                  btnBack.setEnabled(true);
                  contexto.add(s.getName());
                  clearSymbolTable();
                  btnBack.setEnabled(true);
                  this.load(s.getAtributes(),0);
               }else if((s.getType()!=null&&!s.getType().isPrimitive())){
                  btnBack.setEnabled(true);
                  contexto.add(s.getName());
                  clearSymbolTable();
                  btnBack.setEnabled(true);
                  this.load(s.getType().getContext(),0,true);
               }
           }
        }
    }//GEN-LAST:event_tblSymbolTableMouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        if(models.size()>0){
            tblSymbolTable.setModel(models.pop());
            contexto.remove(contexto.size()-1);
            setContexto();
            if(models.size()==0){
                btnBack.setEnabled(false);
            }
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting codetblSymbolTablenal) ">
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
            java.util.logging.Logger.getLogger(SymbolTableViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SymbolTableViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SymbolTableViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SymbolTableViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SymbolTableViewer dialog = new SymbolTableViewer(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblContexto;
    private javax.swing.JTable tblSymbolTable;
    // End of variables declaration//GEN-END:variables
}
