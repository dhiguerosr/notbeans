/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.forms;

import javax.swing.JLabel;

/**
 *
 * @author dennis
 */
public class Cuadro extends JLabel{
    private int x_pos;
    private int y_pos;
    public Cuadro(){
        super();
    }
    public Cuadro(int x,int y){
        super();
        this.x_pos=x;
        this.y_pos=y;
    }

    /**
     * @return the x
     */
    public int getX_pos() {
        return x_pos;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x_pos = x;
    }

    /**
     * @return the y
     */
    public int getY_pos() {
        return y_pos;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y_pos = y;
    }
}
