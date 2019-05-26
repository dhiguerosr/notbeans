/* ---==: Codigo de usuario :==---*/
package gui.syntax.highlighter;

import java_cup.runtime.Symbol;

%%
/* ---==: Opciones y declaraciones :==---*/

%cupsym sym
//%caseless
//%ignorecase
%class Scanner
%cup
%public
%line
%column

/*inicializar variables */
%init{ 
//errores = new ArrayList<>();
%init}

%{
%}

/*valores*/

CADENA = [\"][^\"\n]*[\"\n]
EXTENSION = [{"frc"}{"pj"}]

FILE = [<][^>\^:;,`´\'\n]*[".frc"][>\n]   
CARACTER = [\'][^\'\n][\'\n] 

//string_cs = [\'][^\'\n]+[\'\n] 

DEC = [0-9]+\.[0-9]+
NUM =[0-9]+
ID = [a-zA-Z_][a-zA-Z0-9_]*


%{

/*Codigo de Usuario*/ 
/*hexa = "#"[a-fA-F0-9][a-fA-F0-9][a-fA-F0-9][a-fA-F0-9][a-fA-F0-9][a-fA-F0-9] */

%}

%%
/*---==:Reglas léxicas:==--- */

/* Comentarios*/
"//" [^\n]*                  {return new Symbol(sym.COMENT,yycolumn+1,yyline+1,new String(yytext()));}
//"/"[*] [^*]+ [*]"/" | "/"[*] [*]+ "/"      {return new Symbol(sym.COMENT,yycolumn+1,yyline+1,new String(yytext()));}
"/*" [^*] ~"*/" | "/*" "*"+ "/" {return new Symbol(sym.COMENT,yycolumn+1,yyline+1,new String(yytext()));}

{FILE}  {return new Symbol(sym.FILE,yycolumn+1,yyline+1,new String(yytext()));}


"=="	{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
"!="	{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
">="	{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
"<="	{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
"&&"	{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
"||"	{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
"!"		{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
"&"		{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));}  
"<"		{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
">"		{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 

"++"	{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
"--"	{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 

"+="	{return new Symbol(sym.SIGNO,yycolumn+1,yyline+1,new String(yytext()));}  
"-="	{return new Symbol(sym.SIGNO,yycolumn+1,yyline+1,new String(yytext()));}  
"*="	{return new Symbol(sym.SIGNO,yycolumn+1,yyline+1,new String(yytext()));}   
"/="	{return new Symbol(sym.SIGNO,yycolumn+1,yyline+1,new String(yytext()));}  

"."		{return new Symbol(sym.SIGNO,yycolumn+1,yyline+1,new String(yytext()));}  
","		{return new Symbol(sym.SIGNO,yycolumn+1,yyline+1,new String(yytext()));} 
"="		{return new Symbol(sym.SIGNO,yycolumn+1,yyline+1,new String(yytext()));} 
 
"+"		{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
"-"		{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
"/"		{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
"*"		{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
"^"		{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
"?"		{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 
":"		{return new Symbol(sym.OPERADOR,yycolumn+1,yyline+1,new String(yytext()));} 

"("		{return new Symbol(sym.SIGNO,yycolumn+1,yyline+1,new String(yytext()));} 
")"		{return new Symbol(sym.SIGNO,yycolumn+1,yyline+1,new String(yytext()));}  
"{"		{return new Symbol(sym.SIGNO,yycolumn+1,yyline+1,new String(yytext()));}  
"}"		{return new Symbol(sym.SIGNO,yycolumn+1,yyline+1,new String(yytext()));} 
"["		{return new Symbol(sym.SIGNO,yycolumn+1,yyline+1,new String(yytext()));}  
"]"		{return new Symbol(sym.SIGNO,yycolumn+1,yyline+1,new String(yytext()));} 

"#"		{return new Symbol(sym.SIGNO,yycolumn+1,yyline+1,new String(yytext()));}   

"import"		{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"public"		{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"private"		{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"void"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"Array"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"switch"		{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"case"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"break"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"default"		{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"if"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"else"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"continue"		{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"for"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"do"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"while"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"new"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"int"			{return new Symbol(sym.TIPO,yycolumn+1,yyline+1,new String(yytext()));} 
"float"			{return new Symbol(sym.TIPO,yycolumn+1,yyline+1,new String(yytext()));} 
"bool"			{return new Symbol(sym.TIPO,yycolumn+1,yyline+1,new String(yytext()));} 
"string"		{return new Symbol(sym.TIPO,yycolumn+1,yyline+1,new String(yytext()));} 
"char"			{return new Symbol(sym.TIPO,yycolumn+1,yyline+1,new String(yytext()));} 
"Linea"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"Texto"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"Arco"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"Rectangulo"	{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"Ovalo"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"Poligono"		{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"Lienzo"		{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"Imprimir"		{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 

"true"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"false"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"null"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"return"		{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"class"			{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"static"		{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 
"extends" 		{return new Symbol(sym.RESERVADA,yycolumn+1,yyline+1,new String(yytext()));} 

{ID}			{return new Symbol(sym.ID,yycolumn+1,yyline+1,new String(yytext()));} 
{DEC}			{return new Symbol(sym.VALUE,yycolumn+1,yyline+1,new String(yytext()));} 
{NUM}			{return new Symbol(sym.VALUE,yycolumn+1,yyline+1,new String(yytext()));} 
{CADENA} 		{return new Symbol(sym.CADENA,yycolumn+1,yyline+1,new String(yytext()));}
{CARACTER}		{return new Symbol(sym.CADENA,yycolumn+1,yyline+1,new String(yytext()));}



{ID} 		{return new Symbol(sym.ID,yycolumn+1,yyline+1,new String(yytext()));}



/* BLANCOS */
[ \t\r\f\n]      {return new Symbol(sym.E_BLANCO,yycolumn+1,yyline+1,new String(yytext()));}

/* Cualquier Otro */
.                 {return new Symbol(sym.ERROR,yycolumn+1,yyline+1,new String(yytext()));}

