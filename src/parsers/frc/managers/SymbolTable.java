/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.frc.managers;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Dennis
 */
public class SymbolTable {
    private HashMap<String,Sym> symbolTable;
    private ArrayList<SymbolTable> nodes;
    private SymbolTable parent;
    private HashMap<String,Sym> inherits;
    private int count = 0;
    private int scopeCount = 0;
    public SymbolTable(){
        symbolTable = new HashMap<>();
        nodes = new ArrayList<>();
        inherits = new HashMap<>();
    } 
    public boolean add(Sym t){
        if(!exist(t.getName())){
            if(!t.isFunction()){
                t.setPosition(getCount());
                setCount(getCount() + 1);
            }
            getSymbolTable().put(t.getName(), t);
            return true;
        }
        return false;
    }
    public boolean exist(String name){
        return getSymbolTable().containsKey(name);
    }
    public Sym get(String name){
        return (exist(name))?getSymbolTable().get(name):null;
    }
    public SymbolTable openBloque(SymbolTable st){
        getNodes().add(st);
        st.setParent(this);
        return st;
    }
    public void inherits(SymbolTable st){
        for(String key : st.getSymbolTable().keySet()){
            Sym s = st.getSymbolTable().get(key);
            if(s.getAccessType()==Sym.PUBLIC_ACCESS){
                System.out.println("Atributo "+key + " heredado");
                if(!s.isFunction()){
                    s.setPosition(getCount());
                    setCount(getCount() + 1);
                }
                
                symbolTable.put(key, s);
                //inherits.put(key, s);
                
            }
        }
    }

    /**
     * @return the parent
     */
    public SymbolTable getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(SymbolTable parent) {
        this.parent = parent;
    }

    /**
     * @return the nodes
     */
    public ArrayList<SymbolTable> getNodes() {
        return nodes;
    }

    /**
     * @param nodes the nodes to set
     */
    public void setNodes(ArrayList<SymbolTable> nodes) {
        this.nodes = nodes;
    }

    /**
     * @return the symbolTable
     */
    public HashMap<String,Sym> getSymbolTable() {
        return symbolTable;
    }

    /**
     * @param symbolTable the symbolTable to set
     */
    public void setSymbolTable(HashMap<String,Sym> symbolTable) {
        this.symbolTable = symbolTable;
    }

    /**
     * @return the inherits
     */
    public HashMap<String,Sym> getInherits() {
        return inherits;
    }

    /**
     * @param inherits the inherits to set
     */
    public void setInherits(HashMap<String,Sym> inherits) {
        this.inherits = inherits;
    }
    
    public SymbolTable getScope(){
        SymbolTable st = nodes.get(scopeCount);
        scopeCount++;
        return st;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the scopeCount
     */
    public int getScopeCount() {
        return scopeCount;
    }

    /**
     * @param scopeCount the scopeCount to set
     */
    public void setScopeCount(int scopeCount) {
        this.scopeCount = scopeCount;
    }
    
}
