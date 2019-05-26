/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbc.parser.nodes;

import gui.forms.Console;

/**
 *
 * @author Dennis
 */
public class MethodCall extends Node {
    String id;
    public MethodCall(String id){
        this.id = id;
    }
    @Override
    public float execute() {
     //   Console.println("Ejecutando metodo "+id);
        switch(id){
            case "Imprimir_string":
                float stackPos = CodeManager.getStackPointer();
                float heapPos = CodeManager.getFromStack((int)stackPos);
                float size = CodeManager.getFromHeap((int)heapPos);
                String text = new String();
                for(int x = 0;x<size;x++){
                    int code = (int)CodeManager.getFromHeap((int)heapPos + x +1);
                    text += String.valueOf((char)code);
                }
                Console.println(text);
                break;
            case "Linea_int_int_int_int_int_int_int":
                stackPos = CodeManager.getStackPointer();
                int x1 = (int)CodeManager.getFromStack((int)stackPos);
                int x2 = (int)CodeManager.getFromStack((int)stackPos+1);
                int y1 = (int)CodeManager.getFromStack((int)stackPos+2);
                int y2 = (int)CodeManager.getFromStack((int)stackPos+3);
                int r = (int)CodeManager.getFromStack((int)stackPos+4);
                int g = (int)CodeManager.getFromStack((int)stackPos+5);
                int b = (int)CodeManager.getFromStack((int)stackPos+6);
                CodeManager.getCanvas().drawLine(x1, x2, y1, y2, r, g, b);
                break;
            case "Texto_string_int_int_int_int_int":
                stackPos = CodeManager.getStackPointer();
                int textPos  = (int)CodeManager.getFromStack((int)stackPos);
                heapPos = CodeManager.getFromStack((int)textPos);
                size = CodeManager.getFromHeap((int)heapPos);
                String cadena = new String();
                for(int x = 0;x<size;x++){
                    int code = (int)CodeManager.getFromHeap((int)heapPos + x +1);
                    cadena += String.valueOf((char)code);
                }
                int x = (int)CodeManager.getFromStack((int)stackPos+1);
                int y = (int)CodeManager.getFromStack((int)stackPos+2);
                r = (int)CodeManager.getFromStack((int)stackPos+3);
                g = (int)CodeManager.getFromStack((int)stackPos+4);
                b = (int)CodeManager.getFromStack((int)stackPos+5);
                CodeManager.getCanvas().drawText(cadena, x, y, r, g, b);
                
                break;
            case "Arco_int_int_int_int_int_int_int_int_int_bool":
                stackPos = CodeManager.getStackPointer();
                x1 = (int)CodeManager.getFromStack((int)stackPos);
                x2 = (int)CodeManager.getFromStack((int)stackPos+1);
                y1 = (int)CodeManager.getFromStack((int)stackPos+2);
                y2 = (int)CodeManager.getFromStack((int)stackPos+3);
                int andInit = (int)CodeManager.getFromStack((int)stackPos+4);
                int grados = (int)CodeManager.getFromStack((int)stackPos+5);
                r = (int)CodeManager.getFromStack((int)stackPos+6);
                g = (int)CodeManager.getFromStack((int)stackPos+7);
                b = (int)CodeManager.getFromStack((int)stackPos+8);
                boolean filled = ((int)CodeManager.getFromStack((int)stackPos+9)>0);
                CodeManager.getCanvas().drawArc(x1, x2, y1, y2, andInit, grados, r, g, b, filled);
                break;
            case "Rectangulo_int_int_int_int_int_int_int_bool":
                stackPos = CodeManager.getStackPointer();
                x1 = (int)CodeManager.getFromStack((int)stackPos);
                x2 = (int)CodeManager.getFromStack((int)stackPos+1);
                y1 = (int)CodeManager.getFromStack((int)stackPos+2);
                y2 = (int)CodeManager.getFromStack((int)stackPos+3);
                r = (int)CodeManager.getFromStack((int)stackPos+4);
                g = (int)CodeManager.getFromStack((int)stackPos+5);
                b = (int)CodeManager.getFromStack((int)stackPos+6);
                filled = ((int)CodeManager.getFromStack((int)stackPos+7)>0);
                CodeManager.getCanvas().drawRect(x1, x2, y1, y2, r, g, b, filled);
                break;
            case "Ovalo_int_int_int_int_int_int_int_bool":
                stackPos = CodeManager.getStackPointer();
                x1 = (int)CodeManager.getFromStack((int)stackPos);
                x2 = (int)CodeManager.getFromStack((int)stackPos+1);
                y1 = (int)CodeManager.getFromStack((int)stackPos+2);
                y2 = (int)CodeManager.getFromStack((int)stackPos+3);
                r = (int)CodeManager.getFromStack((int)stackPos+4);
                g = (int)CodeManager.getFromStack((int)stackPos+5);
                b = (int)CodeManager.getFromStack((int)stackPos+6);
                filled = ((int)CodeManager.getFromStack((int)stackPos+7)>0);
                CodeManager.getCanvas().drawOval(x1, x2, y1, y2, r, g, b, filled);
                break;
            case "Poligono_Array_Array_int_int_int_bool":
                stackPos = CodeManager.getStackPointer();
                int heapPosArrayX = (int)CodeManager.getFromStack((int)stackPos);
                int heapPosArrayY = (int)CodeManager.getFromStack((int)stackPos+1);
                r = (int)CodeManager.getFromStack((int)stackPos+2);
                g = (int)CodeManager.getFromStack((int)stackPos+3);
                b = (int)CodeManager.getFromStack((int)stackPos+4);
                filled = ((int)CodeManager.getFromStack((int)stackPos+5)>0);
                int sizePuntosX = (int)CodeManager.getFromHeap(heapPosArrayX+1);
                int sizePuntosY = (int)CodeManager.getFromHeap(heapPosArrayY+1);
                int[] puntosX = new int[sizePuntosX];
                int[] puntosY = new int[sizePuntosY];
                for(int i=0;i<puntosX.length;i++){
                    puntosX[i] = (int)CodeManager.getFromHeap(heapPosArrayX + i + 2);
                }
                for(int i=0;i<puntosY.length;i++){
                    puntosY[i] = (int)CodeManager.getFromHeap(heapPosArrayY + i + 2);
                }
                CodeManager.getCanvas().drawPoligon(puntosX, puntosY, r, g, b, filled);
                break;
       
            case "Lienzo_int_int_int_int_int":
                stackPos = CodeManager.getStackPointer();
                int ancho = (int)CodeManager.getFromStack((int)stackPos);
                int alto = (int)CodeManager.getFromStack((int)stackPos+1);
                r = (int)CodeManager.getFromStack((int)stackPos+2);
                g = (int)CodeManager.getFromStack((int)stackPos+3);
                b = (int)CodeManager.getFromStack((int)stackPos+4);
                CodeManager.getCanvas().setCanvas(alto, ancho, r, g, b);
                break;
            default:
                CodeManager.setMethodCode(id);
                break;
        }
        
        return 0f;
    }
    
    @Override
    public String toString(){
        return "call " + id + "();";
    }
    @Override
    public Node replace(Node node, String match) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this;
        
    }
    @Override
    public boolean reference(String id) {
        return false;
    }
}
