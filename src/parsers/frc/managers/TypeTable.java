/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.frc.managers;

import java.util.HashMap;

/**
 *
 * @author Dennis
 */
public class TypeTable {
    HashMap<String,Type> typeTable;
    public TypeTable(){
        typeTable = new HashMap<>();
        typeTable.put("int", new Type("int",true));
        typeTable.put("float", new Type("float",true));
        typeTable.put("bool", new Type("bool",true));
        typeTable.put("string", new Type("string",true));
        typeTable.put("char", new Type("char",true));
    }
    public boolean add(Type t){
        if(!exist(t.getName())){
            typeTable.put(t.getName(), t);
            return true;
        }
        return false;
    }
    public boolean exist(String name){
        return typeTable.containsKey(name);
    }
    public Type get(String name){
        return (exist(name))?typeTable.get(name):null;
    }
    
}
