/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser.nodes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author Dennis
 */
public class Canvas extends javax.swing.JFrame {
    abstract class Figura{
        private int x1;
        private int x2;
        private int y1;
        private int y2;
        private int r;
        private int g;
        private int b;
        private boolean filled;
        
        public abstract void paint(Graphics g);
        /**
         * @return the x1
         */
        public int getX1() {
            return x1;
        }

        /**
         * @return the x2
         */
        public int getX2() {
            return x2;
        }

        /**
         * @return the y1
         */
        public int getY1() {
            return y1;
        }

        /**
         * @return the y2
         */
        public int getY2() {
            return y2;
        }

        /**
         * @return the r
         */
        public int getR() {
            return r;
        }

        /**
         * @return the g
         */
        public int getG() {
            return g;
        }

        /**
         * @return the b
         */
        public int getB() {
            return b;
        }

        /**
         * @return the filled
         */
        public boolean isFilled() {
            return filled;
        }

        /**
         * @param filled the filled to set
         */
        public void setFilled(boolean filled) {
            this.filled = filled;
        }
    }
    class Linea extends Figura{
        public Linea(int x1,int x2,int y1,int y2, int r,int g,int b){
            super.x1 = x1;
            super.x2 = x2;
            super.y1 = y1;
            super.y2 = y2;
            super.r = r;
            super.g = g;
            super.b = b;
        }
        @Override
        public void paint(Graphics g){
            g.setColor(new Color(getR(),getG(),getB()));
            g.drawLine(getX1(),getX2(),getY1(),getY2());
        }
    }
    class Texto extends Figura{
        String texto;
        public Texto(String texto, int x, int y,int r,int g,int b){
            this.texto = texto;
            super.x1 = x;
            super.y1 = y;
            super.r = r;
            super.g = g;
            super.b = b;
        }
        @Override
        public void paint(Graphics g) {
            g.setColor(new Color(getR(),getG(),getB()));
            g.drawString(texto, getX1(), getY1());
        }
        
    }
    class Arco extends Figura{
        int angInit;
        int grados;
        
        public Arco(int x, int y, int alto,int ancho, int angInit,int grados,int r, int g,int b,boolean filled){
            super.x1 = x;
            super.y1 = y;
            super.x2 = alto;
            super.y2 = ancho;
            this.angInit = angInit;
            this.grados = grados;
            super.r = r;
            super.g = g;
            super.b = b;
            super.filled = filled;
        }
        @Override
        public void paint(Graphics g) {
            g.setColor(new Color(getR(),getG(),getB()));
            if(!isFilled())
                g.drawArc(getX1(), getY1(),getX2(), getY2(), angInit, grados);
            else
                g.fillArc(getX1(), getY1(),getX2(), getY2(), angInit, grados);
        }
        
    }
    class Rectangulo extends Figura{
        public Rectangulo(int x, int y, int alto,int ancho,int r, int g,int b,boolean filled){
            super.x1 = x;
            super.y1 = y;
            super.x2 = alto;
            super.y2 = ancho;
            super.r = r;
            super.g = g;
            super.b = b;
            super.filled = filled;
        }
        @Override
        public void paint(Graphics g) {
            g.setColor(new Color(getR(),getG(),getB()));
            if(!isFilled())
                g.drawRect(getX1(), getY1(),getX2(), getY2());
            else
                g.fillRect(getX1(), getY1(),getX2(), getY2());
        }
        
    }
    class Ovalo extends Figura{
        public Ovalo(int x, int y, int alto,int ancho,int r, int g,int b,boolean filled){
            super.x1 = x;
            super.y1 = y;
            super.x2 = alto;
            super.y2 = ancho;
            super.r = r;
            super.g = g;
            super.b = b;
            super.filled = filled;
        }
        @Override
        public void paint(Graphics g) {
            g.setColor(new Color(getR(),getG(),getB()));
            if(!isFilled())
                g.drawOval(getX1(), getY1(),getX2(), getY2());
            else
                g.fillOval(getX1(), getY1(),getX2(), getY2());
        }
        
    }
    class Poligono extends Figura{
        int[] puntosX;
        int[] puntosY;
        public Poligono(int[] puntosX, int[] puntosY, int r, int g, int b,boolean filled){
            this.puntosX = puntosX;
            this.puntosY = puntosY;
            super.r = r;
            super.g = g;
            super.b = b;
            super.filled = filled;
        }
        @Override
        public void paint(Graphics g) {
            g.setColor(new Color(getR(),getG(),getB()));
            if(!isFilled())
                g.drawPolygon(puntosX, puntosY, (puntosX.length<puntosY.length)?puntosX.length:puntosY.length);
            else
                g.fillPolygon(puntosX, puntosY, (puntosX.length<puntosY.length)?puntosX.length:puntosY.length);
        }
        
    }
    /**
     * Creates new form Canvas
     */
    class Lienzo extends JPanel{
        ArrayList<Figura> figuras;
        public Lienzo(){
            figuras = new ArrayList<>();
            this.setOpaque(true);
        }
        public void addFigura(Figura f){
            figuras.add(f);
            this.repaint();
        }
        public void setCanvas(int alto,int ancho, int r,int g,int b){
            this.setSize(ancho, alto);
            this.setBackground(new Color(r,g,b));
        }
        @Override
        public void paint(Graphics g) {
            super.paint(g); //To change body of generated methods, choose Tools | Templates.
            for(Figura f: figuras){
                f.paint((Graphics2D)g);
            }
        }
    }
    
    Lienzo lienzo;
    public Canvas() {
        initComponents();
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void init(){
        lienzo = new Lienzo();
        lienzo.setSize(400,400);
        this.add(lienzo);
        this.pack();
        this.setVisible(true);
    }
    public void drawLine(final int x1, final int x2, final int y1, final int y2, final int r, final int g,final int b){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                   lienzo.addFigura(new Linea(x1,x2,y1,y2,r,g,b));
            }
            
        });
    }
    public void drawText(final String cadena, final int x,final int y,final int r, final int g,final int b){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                   lienzo.addFigura(new Texto(cadena,x,y,r,g,b));
            }
            
        });
    }
    public void drawArc(final int x,final int y,final int alto, final int ancho,final int andInit, final int grados, final int r, final int g,final int b, final boolean filled){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                   lienzo.addFigura(new Arco(x,y,alto,ancho,andInit, grados, r,g,b,filled));
            }
            
        });
    }
    public void drawRect(final int x,final int y,final int alto, final int ancho, final int r, final int g,final int b, final boolean filled){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                   lienzo.addFigura(new Rectangulo(x,y,alto,ancho,r,g,b,filled));
            }
            
        });
    }
    public void drawOval(final int x,final int y,final int alto, final int ancho, final int r, final int g,final int b, final boolean filled){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                   lienzo.addFigura(new Ovalo(x,y,alto,ancho,r,g,b,filled));
            }
            
        });
    }
    public void drawPoligon(final int[] puntosX,final int[] puntosY,final int r, final int g,final int b, final boolean filled){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                   lienzo.addFigura(new Poligono(puntosX,puntosY,r,g,b,filled));
            }
            
        });
    }
    public void setCanvas(final int alto, final int ancho,final int r,final int g,final int b){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                lienzo.setCanvas(alto, ancho, r, g, b);
                lienzo.repaint();
                Canvas.this.remove(lienzo);
                Canvas.this.add(lienzo);
                Canvas.this.pack();
            }
            
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");

        jMenuItem1.setText("Guardar ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        BufferedImage bImg = new BufferedImage(lienzo.getWidth(), lienzo.getWidth(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        lienzo.paint(cg);
        try {
                if (ImageIO.write(bImg, "png", new File("./output_image.png"))){
                   System.out.println("-- saved");
                }
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(Canvas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Canvas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Canvas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Canvas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Canvas().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
