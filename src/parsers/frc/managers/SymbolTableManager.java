/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.frc.managers;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Dennis
 */
public class SymbolTableManager {
   
    private static Stack<SymbolTable> context;
    private static SymbolTable currentContext;
    
    private static SymbolTable nativeMethods;
    
    private static Stack<SymbolTable> auxContext;
    private static SymbolTable currentAuxcontext;
    private static TypeTable tt ;
    private static Type typeContext;
    private static Stack<String> currentFile;
    public static String getCurrentFile(){
        return currentFile.peek();
    }
    public static void setCurrentFile(String file){
        currentFile.push(file);
    }
    public static void removeCurrentFile(){
        currentFile.pop();
    }
    public static void init(String s){
        tt = new TypeTable();
        currentFile = new Stack<>();
        context = new Stack<>();
        auxContext = new Stack<>();
        ErrorManager.init();
        setCurrentFile(s);
        setNativeMethods();
    }
    public static void restore(){
        currentContext= null;
        currentAuxcontext = null;
        context = new Stack<>();
        auxContext = new Stack<>();
        
    }
    public static boolean addType(Type t){
        return tt.add(t);
    }
    public static boolean typeExist(String t){
        return tt.exist(t);
    }
    public static Type getType(String t){
        return (tt.exist(t))?tt.get(t):null;
    }  
    public static Type getContext(){
        return typeContext;
    }
    public static void setContext(Type t){
        setContext(t,false);
    }
    public static void setContext(Type t,boolean auxiliar){
        ((auxiliar)?auxContext:context).push(t.getContext());
        if(!auxiliar){
            currentContext = t.getContext();
            typeContext = t;
        }else{
            currentAuxcontext = t.getContext();
        }
    }
    public static void setContext(SymbolTable st,boolean auxiliar){
        ((auxiliar)?auxContext:context).push(st);
        if(!auxiliar){
            currentContext = st;
        }else{
            currentAuxcontext = st;
        }
    }
    public static void setTempContext(Type t){
        context.push(t.getContext());
        currentContext = t.getContext();
    }
    public static void removeContext(){
       removeContext(false);
    }
    public static void removeContext(boolean auxiliar){
        if(((auxiliar)?auxContext.size():context.size())>1){
            ((auxiliar)?auxContext:context).pop().setScopeCount(0);
            if(!auxiliar)
                currentContext = context.peek();
            else
                currentAuxcontext = auxContext.peek();
        }else{
            if(auxiliar && auxContext.size()>0){
                auxContext.pop();
                currentAuxcontext = null;
            }
        }
    }
    public static SymbolTable getCurrentContext(){
        return getCurrentContext(false);
    }
    public static SymbolTable getCurrentContext(boolean auxiliar){
        return (auxiliar)?currentAuxcontext:currentContext;
    }
    public static boolean addSymbol(Sym s){

        System.out.println("Simbolo añadido: "+ s.getName());
        if(symbolExist(s.getName()))
            return false;
        return currentContext.add(s);
    }
    public static boolean symbolExist(String s){
        return symbolExist(s,false);
    }
    public static boolean symbolExist(String s,boolean auxiliar){
        SymbolTable cc = (auxiliar)? currentAuxcontext:currentContext;
        if(cc.exist(s)){
            return true;
        }else{
            SymbolTable st = cc;
            while(st!=null){
                if(st.exist(s)){
                    return true;
                }
                st = st.getParent();
            }
        }
        return false;
    }
    public static Sym getSymbol(String s){
        return getSymbol(s,false);
    }
    public static Sym getSymbol(String s,boolean auxiliar){
        SymbolTable cc = (auxiliar)? currentAuxcontext:currentContext;
        if(cc.exist(s)){
            return cc.get(s);
        }else{
            SymbolTable st = cc;
            while(st!=null){
                if(st.exist(s)){
                    return st.get(s);
                }
                st = st.getParent();
            }
        }
        return null;
    }
    
    public static Sym getGlobalSymbol(String s){
        return typeContext.getContext().get(s);
    }
    
   
    public static void openMethodBloque(Sym method){
        if(method.getAtributes()==null) method.setAtributes(new SymbolTable());
        method.getAtributes().setParent(currentContext);
        currentContext = method.getAtributes();
    }
    public static void openBloque(){
        System.out.println("Nuevo ámbito abierto");
        SymbolTable st = new SymbolTable();
        currentContext.openBloque(st);
        currentContext = st;
    }
    public static void closeBloque(){
        if(currentContext.getParent()!=null){
            System.out.println("ambito cerrado");
            currentContext = currentContext.getParent();
            if(currentContext.getParent()==null){
                System.out.println("retorno ámbito global");
            }
        }
    }
    public static void inherits(Type t){
        context.peek().inherits(t.getContext());
    }
    
    public static void setMethodContext(String id){
        Sym method = currentContext.getSymbolTable().get(id);
        context.push(method.getAtributes());
        currentContext = method.getAtributes();
    }
    
    public static void openScope(){
        SymbolTable st = currentContext.getScope();
        context.push(st);
        currentContext = st;
    }
    public static Sym getNativeMethod(String name){
        return nativeMethods.get(name);
    }
    public static void setNativeMethods(){
         nativeMethods = new SymbolTable();
         Sym method;
         Stack<Sym> arguments;
         
         //Metodo imrpimir
         method = new Sym("Imprimir",-1,-1);
         arguments = new Stack<>();
         arguments.push(new Sym("arg",SymbolTableManager.getType("string"),-1,-1));
         method.setArguments(arguments);
         nativeMethods.add(method);
         
         //metodo linea
         method = new Sym("Linea",-1,-1);
         arguments = new Stack<>();
         arguments.push(new Sym("b",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("g",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("r",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("y2",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("x2",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("y1",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("x1",SymbolTableManager.getType("int"),-1,-1));
         method.setArguments(arguments);
         nativeMethods.add(method);
         
         //metodo texto
         method = new Sym("Texto",-1,-1);
         arguments = new Stack<>();
         arguments.push(new Sym("b",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("g",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("r",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("y",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("x",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("cadena",SymbolTableManager.getType("string"),-1,-1));
         method.setArguments(arguments);
         nativeMethods.add(method);
         
         //metodo arco
         method = new Sym("Arco",-1,-1);
         arguments = new Stack<>();
         arguments.push(new Sym("fill",SymbolTableManager.getType("bool"),-1,-1));
         arguments.push(new Sym("b",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("g",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("r",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("grados",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("andInit",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("ancho",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("alto",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("y",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("x",SymbolTableManager.getType("int"),-1,-1));
         method.setArguments(arguments);
         nativeMethods.add(method);
         
         //metodo rectangulo
         method = new Sym("Rectangulo",-1,-1);
         arguments = new Stack<>();
         arguments.push(new Sym("fill",SymbolTableManager.getType("bool"),-1,-1));
         arguments.push(new Sym("b",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("g",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("r",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("ancho",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("alto",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("y",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("x",SymbolTableManager.getType("int"),-1,-1));
         method.setArguments(arguments);
         nativeMethods.add(method);
         
         //metodo ovalo
         method = new Sym("Ovalo",-1,-1);
         arguments = new Stack<>();
         arguments.push(new Sym("fill",SymbolTableManager.getType("bool"),-1,-1));
         arguments.push(new Sym("b",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("g",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("r",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("ancho",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("alto",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("y",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("x",SymbolTableManager.getType("int"),-1,-1));
         method.setArguments(arguments);
         nativeMethods.add(method);
         
         //metodo poligono
         method = new Sym("Poligono",-1,-1);
         Type t = new Type("Array");
         t.setSubtype(SymbolTableManager.getType("int"));
         
         arguments = new Stack<>();
         arguments.push(new Sym("fill",SymbolTableManager.getType("bool"),-1,-1));
         arguments.push(new Sym("b",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("g",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("r",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("puntosY",t,-1,-1));
         arguments.push(new Sym("puntosX",t,-1,-1));
         method.setArguments(arguments);
         nativeMethods.add(method);
         
          //metodo poligono
         method = new Sym("Lienzo",-1,-1);
         
         arguments = new Stack<>();
         arguments.push(new Sym("b",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("g",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("r",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("ancho",SymbolTableManager.getType("int"),-1,-1));
         arguments.push(new Sym("alto",SymbolTableManager.getType("int"),-1,-1));
         method.setArguments(arguments);
         nativeMethods.add(method);
    }
}
