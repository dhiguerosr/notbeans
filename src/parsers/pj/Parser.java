
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Sat Jun 14 05:39:32 CST 2014
//----------------------------------------------------

package parsers.pj;

import gui.forms.Project;
import gui.forms.ProjectManager;
import java.util.Stack;
import java_cup.runtime.Symbol;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Sat Jun 14 05:39:32 CST 2014
  */
public class Parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public Parser() {super();}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\013\000\002\002\003\000\002\002\004\000\002\003" +
    "\021\000\002\003\021\000\002\003\020\000\002\003\020" +
    "\000\002\004\012\000\002\005\004\000\002\005\003\000" +
    "\002\006\011\000\002\007\012" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\100\000\004\004\005\001\002\000\004\002\102\001" +
    "\002\000\004\006\007\001\002\000\004\002\001\001\002" +
    "\000\006\007\011\010\010\001\002\000\004\015\062\001" +
    "\002\000\004\015\012\001\002\000\004\016\013\001\002" +
    "\000\004\010\014\001\002\000\004\015\015\001\002\000" +
    "\004\016\016\001\002\000\004\005\017\001\002\000\004" +
    "\004\021\001\002\000\004\004\043\001\002\000\004\011" +
    "\022\001\002\000\004\005\023\001\002\000\004\004\026" +
    "\001\002\000\004\004\ufff9\001\002\000\004\004\036\001" +
    "\002\000\004\012\027\001\002\000\004\007\030\001\002" +
    "\000\004\015\031\001\002\000\004\016\032\001\002\000" +
    "\004\014\033\001\002\000\004\005\034\001\002\000\004" +
    "\004\ufff8\001\002\000\004\004\ufffa\001\002\000\006\012" +
    "\027\014\037\001\002\000\004\011\040\001\002\000\004" +
    "\005\041\001\002\000\004\004\ufffb\001\002\000\004\004" +
    "\056\001\002\000\006\013\044\014\045\001\002\000\004" +
    "\005\050\001\002\000\004\006\046\001\002\000\004\005" +
    "\047\001\002\000\004\002\ufffd\001\002\000\004\004\026" +
    "\001\002\000\004\004\052\001\002\000\004\014\053\001" +
    "\002\000\004\013\054\001\002\000\004\005\055\001\002" +
    "\000\004\004\ufff7\001\002\000\004\014\057\001\002\000" +
    "\004\006\060\001\002\000\004\005\061\001\002\000\004" +
    "\002\uffff\001\002\000\004\016\063\001\002\000\004\007" +
    "\064\001\002\000\004\015\065\001\002\000\004\016\066" +
    "\001\002\000\004\005\067\001\002\000\004\004\021\001" +
    "\002\000\004\004\072\001\002\000\004\004\076\001\002" +
    "\000\006\013\044\014\073\001\002\000\004\006\074\001" +
    "\002\000\004\005\075\001\002\000\004\002\ufffc\001\002" +
    "\000\004\014\077\001\002\000\004\006\100\001\002\000" +
    "\004\005\101\001\002\000\004\002\ufffe\001\002\000\004" +
    "\002\000\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\100\000\006\002\003\003\005\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\004\004\017\001\001\000\004\007\041\001" +
    "\001\000\002\001\001\000\002\001\001\000\006\005\024" +
    "\006\023\001\001\000\002\001\001\000\004\006\034\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\004\006\050\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\004\067\001\001\000\004" +
    "\007\070\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    

    /**Metodo al que se llama automaticamente ante algun error sintactico.*/
    public void syntax_error(Symbol s){
		//ErrorManager.syntaxError(new Err(s.value.toString(),s.right,s.left)); 
		//ErrorManager.setDesc("");
        System.out.println("Error Sintactico -> lexema: " +s.value+" Linea: " +s.right+ " Columna: "+s.left);
        
    }
   


    /**Metodo al que se llama en el momento en que ya no es posible una recuperacion de errores.*/
    public void unrecovered_syntax_error(Symbol s) throws java.lang.Exception{
		//ErrorManager.syntaxError(new Err(s.value.toString(),s.right,s.left)); 
		//ErrorManager.setDesc("");
		System.out.println("Error Sintactico -> lexema: " +s.value+" Linea: " +s.right+ " Columna: "+s.left);
    }


}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$Parser$actions {


	

  private final Parser parser;

  /** Constructor */
  CUP$Parser$actions(Parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Parser$result;

      /* select the action based on the action number */
      switch (CUP$Parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // main ::= OPEN PRINCIPAL CLOSE file OPEN PRECLOSE PRINCIPAL CLOSE 
            {
              String RESULT =null;
		int mainleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int mainright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		String main = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		 RESULT = main; 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("main",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-7)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // file ::= OPEN ARCHIVO NOMBRE EQL CAD PRECLOSE CLOSE 
            {
              String RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int fright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		String f = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		RESULT = f;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("file",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // list_files ::= file 
            {
              Stack<String> RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String f = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
			Stack<String> files = new Stack<>();
			files.push(f);
			RESULT = files;
		
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("list_files",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // list_files ::= list_files file 
            {
              Stack<String> RESULT =null;
		int filesleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int filesright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Stack<String> files = (Stack<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int fleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String f = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
			files.push(f);
			RESULT = files;
		
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("list_files",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // files ::= OPEN ARCHIVOS CLOSE list_files OPEN PRECLOSE ARCHIVOS CLOSE 
            {
              Stack<String> RESULT =null;
		int filesleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int filesright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		Stack<String> files = (Stack<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		RESULT = files;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("files",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-7)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // project ::= OPEN PROYECTO RUTA EQL CAD NOMBRE EQL CAD CLOSE files OPEN PRECLOSE PROYECTO CLOSE 
            {
              Project RESULT =null;
		int rutaleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-9)).left;
		int rutaright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-9)).right;
		String ruta = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-9)).value;
		int nombreleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)).left;
		int nombreright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)).right;
		String nombre = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-6)).value;
		int fsleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int fsright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		Stack<String> fs = (Stack<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		
		RESULT = new Project(nombre, ruta, fs);
	
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("project",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-13)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // project ::= OPEN PROYECTO NOMBRE EQL CAD RUTA EQL CAD CLOSE files OPEN PRECLOSE PROYECTO CLOSE 
            {
              Project RESULT =null;
		int nombreleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-9)).left;
		int nombreright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-9)).right;
		String nombre = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-9)).value;
		int rutaleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)).left;
		int rutaright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)).right;
		String ruta = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-6)).value;
		int fsleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int fsright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		Stack<String> fs = (Stack<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		
		RESULT = new Project(nombre, ruta, fs);
	
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("project",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-13)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // project ::= OPEN PROYECTO RUTA EQL CAD NOMBRE EQL CAD CLOSE files main OPEN PRECLOSE PROYECTO CLOSE 
            {
              Project RESULT =null;
		int rutaleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-10)).left;
		int rutaright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-10)).right;
		String ruta = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-10)).value;
		int nombreleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-7)).left;
		int nombreright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-7)).right;
		String nombre = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-7)).value;
		int fsleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).left;
		int fsright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).right;
		Stack<String> fs = (Stack<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-5)).value;
		int mleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int mright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		String m = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		
		RESULT = new Project(nombre, ruta, fs,m);
	
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("project",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-14)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // project ::= OPEN PROYECTO NOMBRE EQL CAD RUTA EQL CAD CLOSE files main OPEN PRECLOSE PROYECTO CLOSE 
            {
              Project RESULT =null;
		int nombreleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-10)).left;
		int nombreright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-10)).right;
		String nombre = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-10)).value;
		int rutaleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-7)).left;
		int rutaright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-7)).right;
		String ruta = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-7)).value;
		int fsleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).left;
		int fsright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).right;
		Stack<String> fs = (Stack<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-5)).value;
		int mleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int mright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		String m = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		
		RESULT = new Project(nombre, ruta, fs,m);
	
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("project",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-14)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= program EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		RESULT = start_val;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Parser$parser.done_parsing();
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // program ::= project 
            {
              Object RESULT =null;
		int pleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int pright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Project p = (Project)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		ProjectManager.addProject(p);
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("program",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

