/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser.nodes;

import gui.forms.Console;
import gui.forms.ProjectManager;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import javax.swing.JFrame;

/**
 *
 * @author Dennis
 */
public class CodeManager {
    private static HashMap<String,Float> temps;
    private static ArrayList<Float> stack;
    private static ArrayList<Float> heap;
    private static HashMap<String,Method> methods;
    private static ArrayList<Method> methodLst;
    private static Stack<ArrayList<Node>> code;
    private static Stack<Integer> IP;
    private static int P;
    private static int H;
    private static Canvas canvas;
   
    
    public static void init(){
        temps = new HashMap<>();
        stack = new ArrayList<>();
        heap = new ArrayList<>();
        code = new Stack<>();
        IP = new Stack<>();
        methods = new HashMap<>();
        methodLst = new ArrayList<>();
        IP.push(0);
        P = 0;
        H = 0;
    }
    
    public static void initCanvas(){
        canvas = new Canvas();
        canvas.init();
    }
    public static Canvas getCanvas(){
        return canvas;
    }
    public static void execute(final String main){
        initCanvas();
        Thread t = new Thread(){
            @Override
            public void run(){
                code.push(methods.get(main).getCode());
                while(!code.isEmpty()){
                    while(IP.peek()<code.peek().size()){
                        code.peek().get(IP.peek()).execute();
                        int ip = IP.pop();
                        IP.push(ip+1);
                    }
                    IP.pop();
                    code.pop();
                }
            }
        };
        t.start();  
    }
    
    public static void graph(String out){
        String graph = "digraph g {\n";
        graph+= "rankdir = \"TB\";";
        int i = 0;
        for(Method m:methodLst){
            graph += m.toDot(++i);
        }
        
        for(Method m:methodLst){
            graph += m.getDotRelations();
        }      
        graph +="}\n";
        File buildFolder = new File(ProjectManager.getCurrentProject().getRoot_folder()+"\\build");
        FileWriter proj = null;
        PrintWriter pw;
        try{
            proj = new FileWriter(buildFolder.getAbsolutePath()+"\\g.dot");
            pw = new PrintWriter(proj);
            pw.print(graph);
        } catch (Exception e) {return;} 
        finally {
           try{
           if (proj!=null)
               proj.close();
           } catch (Exception e2) {return;}
        }
        try {
            String dotPath = "dot.exe";
            String fileInputPath = buildFolder.getAbsolutePath()+"\\g.dot";
            String fileOutputPath = out;
            String tParam = "-Tjpg";
            String tOParam = "-o";
            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;
            Runtime rt = Runtime.getRuntime();
            rt.exec( cmd );
            File f = new File(out);
            Console.println("Generando imagen... espere");
            while(!f.exists()){}
            Console.println("imagen generada en " + out);
            Desktop.getDesktop().open(new File(out));
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }
    public static void optimize(){
        String out = new String();
        for(int x=methodLst.size()-1;x>=0;x--){
            out+=methodLst.get(x).optimize();
        } 
        File buildFolder = new File(ProjectManager.getCurrentProject().getRoot_folder()+"\\build");
        FileWriter proj = null;
        PrintWriter pw;
        try{
            proj = new FileWriter(buildFolder.getAbsolutePath()+"\\output_opti.3dr");
            pw = new PrintWriter(proj);
            pw.print(out);
        } catch (Exception e) {return;} 
        finally {
           try{
           if (proj!=null)
               proj.close();
           } catch (Exception e2) {return;}
        }
       
    }
    public static void optimizeGlobal(){
        String out = new String();
        for(int x=methodLst.size()-1;x>=0;x--){
            out+=methodLst.get(x).optimizeGlobal();
        } 
        File buildFolder = new File(ProjectManager.getCurrentProject().getRoot_folder()+"\\build");
        FileWriter proj = null;
        PrintWriter pw;
        try{
            proj = new FileWriter(buildFolder.getAbsolutePath()+"\\output_opti.3dr");
            pw = new PrintWriter(proj);
            pw.print(out);
        } catch (Exception e) {return;} 
        finally {
           try{
           if (proj!=null)
               proj.close();
           } catch (Exception e2) {return;}
        }
    }
    public static void addMethod(Method m){
        methods.put(m.id, m);
        methodLst.add(m);
    }
    public static void setMethodCode(String id){
        IP.push(IP.pop()+1);
        Method m = methods.get(id);
        code.push(m.getCode());
        IP.push(-1);
    }
    public static int getStackPointer(){
        return P;
    }
    public static void setStackPointer(float p){
        while(p>stack.size()-1)
            stack.add(0f);
        P = (int) p;
    }
    public static int getHeapPointer(){
        return H;
    }
    public static void setHeapPointer(float h){
        while(h>heap.size()-1)
            heap.add(0f);
        H = (int) h;
    }
    public static float getTempValue(String id){
        return (temps.containsKey(id))?temps.get(id):0f;
    }
    public static void setToStack(float value,int p){
        while(p>stack.size()-1)
            stack.add(0f);
        stack.set(p, value);
    }
    public static float getFromStack(int p){
        while(p>stack.size()-1)
            stack.add(0f);
        return stack.get(p);
    }
    public static float getFromHeap(int h){
        while(h>heap.size()-1)
            heap.add(0f);
        return heap.get(h);
    }
    public static void setToHeap(float value,int h){
        while(h>heap.size()-1)
            heap.add(0f);
        heap.set(h, value);
    }
    public static void setTempValue(float value,String id){
        temps.put(id, value);
    }
    public static void setInstructionPointer(float value){
        IP.pop();
        IP.push((int)value - 1);
    }
    
}
