/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.frc.managers;

import gui.forms.Console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author Dennis
 */
public class ErrorManager {
    public static final String L_DESC = "Error léxico";
    public static final String S_DESC = "Error de sintáxis";
    public static final String TYPE_EXIST = "Tipo de datos ya definido";
    public static final String UNDEFINED_TYPE = "Tipo de datos no definido en este contexto";
    public static final String VAR_EXIST = "Variable ya declarada en este contexto";
    public static final String METHOD_EXIST = "Método ya declarado en este contexto";
    public static final String INVALID_METHOD_DEC = "Declaración de método inválida";
    public static final String FILE_NOT_FOUND = "No se encontró el fichero especificado";
    public static final String CANT_PARSE = "Imposible analizar el fichero";
    private static ArrayList<Err> errors;
    public static void init(){
        errors = new ArrayList<>();
    }
    
    public static void add(String error, int line, int column, String type){
        switch(type){
            case Err.LEXICAL:
                errors.add(new Err(error,line,column,type,L_DESC,SymbolTableManager.getCurrentFile()));
                break;
            case Err.SYNTACTIC:
                errors.add(new Err(error,line,column,type,S_DESC,SymbolTableManager.getCurrentFile()));
                break;
        }
       
    }
    
    public static void add(String error, int line, int column,String desc, String type){
        errors.add(new Err(error,line,column,type,desc,SymbolTableManager.getCurrentFile()));
    }
    
    public static String errorsToHTML(){
        String content ="<html><head><title>Errores Sintácticos</title></head><body>";
        content+="<center><h1>Errores Léxicos</h1></center>";
        content+="<hr />";
        content+="<center><table border='1' width='600px' >";
        content+="<tr><td>No</td><td>Lexema</td><td>Descripcion</td><td>Línea</td><td>Columna</td><td>Archivo</td></tr>";
        
        
        
        if(errors!=null){
        Iterator lex = errors.iterator();
        int x=0;
            while(lex.hasNext()){
                x++;
                Err error = (Err)lex.next();
                content+="</tr><td>"+x+"</td><td>"+error.getError()+"</td><td>"+error.getDescription()+"</td><td>"+error.getLine()+"</td><td>"+error.getColumn()+"</td><td>"+error.getFile()+"</td></tr>";
            }
        }
        content+="</center></table>";
        return content;
    }
    
    
    public static ArrayList<Err> getErrorList(String type){
        ArrayList<Err> errs = new ArrayList<>();
        if(errors!=null){
            for(Err err : errors){
                if(err.getType().equalsIgnoreCase(type)){
                    errs.add(err);
                }
            }
        }
        return errs;
    }
}
