/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.frc.managers;

import gui.forms.Console;

/**
 *
 * @author Dennis
 */
public final class Err {
    public static final String LEXICAL = "lexico";
    public static final String SYNTACTIC = "sintáctico";
    public static final String SEMANTIC = "semantico";
    private String error;
    private int line;
    private int column;
    private String type;
    private String file;
    private String description;
    
    public Err(String error, int line, int column, String type,String desc, String file){
        this.error = error;
        this.line = line;
        this.column = column;
        this.type = type;
        this.description = desc;
        this.file = file;
        Console.println(this.toString());
    }
    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the line
     */
    public int getLine() {
        return line;
    }

    /**
     * @param line the line to set
     */
    public void setLine(int line) {
        this.line = line;
    }

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString(){
        return "Error:" + this.getDescription() + "\t" + this.getError() + "\tLn: "+ this.getLine() + "\tCol: "+this.getColumn() + "\tFile: " + this.getFile();
    }
}
