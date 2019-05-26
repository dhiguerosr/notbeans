/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.frc.managers;

import java.util.Objects;

/**
 *
 * @author Dennis
 */
public class Type {
    private String name;
    private Type parent;
    private Type subtype;
    private SymbolTable context;
    private boolean primitive;
    private int dims;
    
    public Type(String name){
        this.name = name;
        context = new SymbolTable();
        primitive = false;
    }
    public Type(String name,boolean primitive){
        this.name = name;
        this.primitive = primitive;
        context = new SymbolTable();
    }
    
    public int getSize(){
        int count  = 0;
        for(String key : context.getSymbolTable().keySet()){
            Sym s = context.getSymbolTable().get(key);
            if(!s.isFunction())
                count++;
        }
        return count;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return the parent
     */
    public Type getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Type parent) {
        this.parent = parent;
    }

    /**
     * @return the context
     */
    public SymbolTable getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(SymbolTable context) {
        this.context = context;
    }

    /**
     * @return the primitive
     */
    public boolean isPrimitive() {
        return primitive;
    }

    /**
     * @param primitive the primitive to set
     */
    public void setPrimitive(boolean primitive) {
        this.primitive = primitive;
    }
    
    @Override
    public boolean equals(Object o){
        if(o==null)
            return false;
        if(o instanceof Type){
            if(this.getName().equals("Array") && ((Type)o).getName().equals("Array") && ((Type)o).getSubtype()==null)
                return true;
            else if(this.getSubtype()!=null)
                return (this.getSubtype().equals(((Type)o).getSubtype()));
            return (o.hashCode()==this.hashCode() );
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + (this.primitive ? 1 : 0);
        return hash;
    }
    
    public Type implicitCast(Type t,char op){
        return cast(t,op,-1,-1);
    }
    public Type cast(Type t){
        return cast(t,null,-1,-1);
    }
    public Type implicitCast(Type t,char op,int ln,int cl){
        return cast(t,op,ln,cl);
    }
    public Type cast(Type t, int ln, int cl){
        return cast(t,null,ln,cl);
    }
    public Type cast(Type t,Character op,int ln, int cl){
        if(this.isPrimitive()&&t!=null&&t.isPrimitive()){
            switch(this.getName()){
                case "int":
                        switch(t.getName()){
                            case "int":
                                if(op!=null&&op=='/')
                                    return SymbolTableManager.getType("float");
                                return SymbolTableManager.getType("int");
                            case "float":
                                return SymbolTableManager.getType("float");
                            case "string":
                                if(op==null||op=='+')
                                    return SymbolTableManager.getType("string");
                                ErrorManager.add(t.getName(),ln, cl, "No se puede convertir implícitamente de int a string");
                                return null;
                            case "bool":
                                ErrorManager.add(t.getName(),ln, cl, "No se puede convertir implícitamente de int a bool");
                                return null;
                            case "char":
                                if(op==null)
                                    return SymbolTableManager.getType("char");
                                if(op=='/')
                                    return SymbolTableManager.getType("float");
                                return SymbolTableManager.getType("int");
                            default:
                                ErrorManager.add(t.getName(),ln, cl, "No se puede realizar una conversión implícita");
                                return null;
                        }
                case "float":
                    switch(t.getName()){
                            case "int":
                                return SymbolTableManager.getType("int");
                            case "float":
                                return SymbolTableManager.getType("float");
                            case "string":
                                if(op==null||op=='+')
                                    return SymbolTableManager.getType("string");
                                ErrorManager.add(t.getName(),ln, cl, "No se puede convertir implícitamente de float a string");
                                return null;
                            case "bool":
                                ErrorManager.add(t.getName(),ln, cl, "No se puede convertir implícitamente de float a bool");
                                return null;
                            case "char":
                                if(op==null)
                                    return SymbolTableManager.getType("char");
                                return SymbolTableManager.getType("float");
                            default:
                                ErrorManager.add(t.getName(),ln, cl, "No se puede realizar una conversión implícita");
                                return null;
                    }
                case "string":
                    if((op==null && t.getName().equals("string")) ||(op!=null&&op=='+'))
                        return SymbolTableManager.getType("string");
                    else{
                        ErrorManager.add(t.getName(),ln, cl, "Tipos de datos incompatibles");
                        return null;
                    }
                case "bool":
                    if(op==null && t.getName().equals("bool"))
                        return SymbolTableManager.getType("bool");
                    else if(op=='+' && t.getName().equals("string"))
                        return SymbolTableManager.getType("string");
                    else{
                        ErrorManager.add(t.getName(),ln, cl, "Tipos de datos incompatibles");
                        return null;
                    }
                case "char":
                    switch(this.getName()){
                            case "int":
                                if(op!=null&&op=='/')
                                    return SymbolTableManager.getType("float");
                                return SymbolTableManager.getType("int");
                            case "float":
                                return SymbolTableManager.getType("float");
                            case "string":
                                if(op!=null && op=='+')
                                    return SymbolTableManager.getType("string");
                                ErrorManager.add(t.getName(),ln, cl, "No se puede convertir implícitamente de char a string");
                                return null;
                            case "bool":
                                ErrorManager.add(t.getName(),ln, cl, "No se puede convertir implícitamente de char a bool");
                                return null;
                            case "char":
                                if(op==null)
                                    return SymbolTableManager.getType("char");
                                if(op=='/')
                                    return SymbolTableManager.getType("float");
                                else if(op=='+')
                                    return SymbolTableManager.getType("char");
                                else 
                                    return SymbolTableManager.getType("int");
                            default:
                                ErrorManager.add(t.getName(),ln, cl, "No se puede realizar una conversión implícita");
                                return null;
                    }
                default:
                    ErrorManager.add(t.getName(),ln, cl, "No se puede realizar una conversión implícita");
                    return null;
                    
            }
        }else{
            ErrorManager.add(t.getName(),ln, cl, "No se puede realizar una conversión implícita de " + this + " a " + t);
            return null;
        }
    }
    
    @Override
    public String toString(){
        return this.getName()+ ((subtype!=null)?("<"+this.getSubtype()+">"):"");
    }

    /**
     * @return the dims
     */
    public int getDims() {
        return dims;
    }

    /**
     * @param dims the dims to set
     */
    public void setDims(int dims) {
        this.dims = dims;
    }

    /**
     * @return the subtype
     */
    public Type getSubtype() {
        return subtype;
    }

    /**
     * @param subtype the subtype to set
     */
    public void setSubtype(Type subtype) {
        this.subtype = subtype;
    }
    
}
