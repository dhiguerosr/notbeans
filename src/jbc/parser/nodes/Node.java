/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser.nodes;

/**
 *
 * @author Dennis
 */
public abstract class Node {
    public abstract float execute();
    public abstract Node replace(Node value,String match);
    public abstract boolean reference(String id);
}
