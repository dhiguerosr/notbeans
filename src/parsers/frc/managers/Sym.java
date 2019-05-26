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
public class Sym {
    public static int PRIVATE_ACCESS = 0;
    public static int PUBLIC_ACCESS = 1;
    
    private String name;
    private Type type;
    private boolean array;
    private int arrayDimCount;
    private SymbolTable atributes;
    private ArrayList<Integer> arrayDimSize;
    private int accessType;
    private boolean _static;
    private boolean reference;
    private boolean inicialized;
    private Object value;
    private Sym parent;
    private boolean function;
    private ArrayList<Sym> arguments;
    private boolean constructor;
    private int line;
    private int column;
    private int size;
    private boolean param;
    private Integer position;
    public void init(){
        this.array = false;
        this.accessType = PRIVATE_ACCESS;
        this._static = false;
        this.reference = false;
        this.inicialized = false;
        this.function = false;
        this.constructor = false;
        this.param = false;
        this.size = 1;
    }
    public Sym(int line,int column){
        Sym.this.init();
        this.line = line;
        this.column = column;
    }
    public Sym(String name, int line, int column){
        Sym.this.init();
        this.name =name; 
        this.line = line;
        this.column = column;
    }
    public Sym(String name, Type t,int line, int column){
        Sym.this.init();
        this.name =name; 
        Sym.this.setType(t);
        this.line = line;
        this.column = column;
    }
    
    public void setAttributes(){
        if(type!=null && !type.isPrimitive() && !array && !function ){
            if(this.atributes==null) atributes = new SymbolTable();
            SymbolTable st = type.getContext();
            for(String key: st.getSymbolTable().keySet()){
                Sym s = st.getSymbolTable().get(key);
                if(s.getAccessType()==Sym.PUBLIC_ACCESS && !s.isStatic()){
                    atributes.add(s);
                }
            }
        }
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
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        array = type!=null && type.getName()!=null && type.getName().equals("Array");
        this.type = type;
    }

    /**
     * @return the array
     */
    public boolean isArray() {
        return array;
    }

    /**
     * @param array the array to set
     */
    public void setArray(boolean array) {
        this.array = array;
    }

    /**
     * @return the arrayDimCount
     */
    public int getArrayDimCount() {
        return arrayDimCount;
    }

    /**
     * @param arrayDimCount the arrayDimCount to set
     */
    public void setArrayDimCount(int arrayDimCount) {
        this.arrayDimCount = arrayDimCount;
    }

    /**
     * @return the arrayDimSize
     */
    public ArrayList<Integer> getArrayDimSize() {
        return arrayDimSize;
    }

    /**
     * @param arrayDimSize the arrayDimSize to set
     */
    public void setArrayDimSize(ArrayList<Integer> arrayDimSize) {
        this.arrayDimSize = arrayDimSize;
    }

    /**
     * @return the accessType
     */
    public int getAccessType() {
        return accessType;
    }

    /**
     * @param accessType the accessType to set
     */
    public void setAccessType(int accessType) {
        this.accessType = accessType;
    }

    /**
     * @return the _static
     */
    public boolean isStatic() {
        return _static;
    }

    /**
     * @param _static the _static to set
     */
    public void setStatic(boolean _static) {
        this._static = _static;
    }

    /**
     * @return the reference
     */
    public boolean isReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(boolean reference) {
        this.reference = reference;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return the inicialized
     */
    public boolean isInicialized() {
        return inicialized;
    }

    /**
     * @param inicialized the inicialized to set
     */
    public void setInicialized(boolean inicialized) {
        this.inicialized = inicialized;
    }

    /**
     * @return the parent
     */
    public Sym getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Sym parent) {
        this.parent = parent;
    }

    /**
     * @return the function
     */
    public boolean isFunction() {
        return function;
    }

    /**
     * @param function the function to set
     */
    public void setFunction(boolean function) {
        this.function = function;
    }


    /**
     * @return the constructor
     */
    public boolean isConstructor() {
        return constructor;
    }

    /**
     * @param constructor the constructor to set
     */
    public void setConstructor(boolean constructor) {
        this.constructor = constructor;
    }

    /**
     * @return the arguments
     */
    public ArrayList<Sym> getArguments() {
        return arguments;
    }

    /**
     * @param arguments the arguments to set
     */
    public void setArguments(ArrayList<Sym> arguments) {
        this.arguments = arguments;
    }
    
    public boolean setArguments(Stack<Sym> arguments){
        boolean correct = true;
        this.function = true;
        this.arguments = new ArrayList<>();
        while(!arguments.isEmpty()){
            Sym s = arguments.pop();
            name += "_"+s.getType().getName();
            s.setParam(true);
            this.arguments.add(s);
           
            if(this.getAtributes()==null){
                this.setAtributes(new SymbolTable());
            }
            if(!this.getAtributes().add(s)){
                ErrorManager.add(s.getName(),s.getLine(),s.getColumn(),ErrorManager.VAR_EXIST,Err.SEMANTIC);
                correct = false;
            }
        }
        this.name += "()";
        return correct;
    }
    
    public boolean validateArguments(Stack<Type> types){
        int count = 0;
        while(!types.isEmpty()){
            Type t = types.pop();
            if(!t.equals(arguments.get(count).getType()))
                return false;
            count ++;
        }
        return true;
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
     * @return the atributes
     */
    public SymbolTable getAtributes() {
        return atributes;
    }

    /**
     * @param atributes the atributes to set
     */
    public void setAtributes(SymbolTable atributes) {
        this.atributes = atributes;
    }

    /**
     * @return the size
     */
    public int getSize() {
        if(isFunction()){
            return this.getAtributes().getSymbolTable().size();
        }
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    @Override
    public String toString(){
        return this.getName();
        
    }

    /**
     * @return the param
     */
    public boolean isParam() {
        return param;
    }

    /**
     * @param param the param to set
     */
    public void setParam(boolean param) {
        this.param = param;
    }

    /**
     * @return the position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Integer position) {
        this.position = position;
    }
}
