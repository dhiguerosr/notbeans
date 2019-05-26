
package parsers.frc.ts;

import java_cup.runtime.Symbol;
import parsers.frc.managers.*;

%%
%cupsym sym
//%caseless
//%ignorecase
%class Scanner
%cup
%public
%line
%column

%init{ 
%init}

%{
%}


CADENA = [\"][^\"\n]*[\"\n] 
FILE = [<][^>\^:;,`´\'\n]*[".frc"][>\n]   
CARACTER = [\'][^\'\n][\'\n] 

DEC = 	[0-9]+\.[0-9]+
NUM =	[0-9]+
ID = 	[a-zA-Z_][a-zA-Z0-9_]*


%{
/*CODIGO USUARIO*/ 
%}

%%

/* Comentarios*/
"//" [^\n]*                  		{/*comentarios se ignoran*/}
"/*" [^*] ~"*/" | "/*" "*"+ "/" 	{/*comentarios se ignoran*/}

{FILE}  {return new Symbol(sym.FILE,yycolumn+1,yyline+1,new String(yytext()));}

"=="	{return new Symbol(sym.EQUALS,yycolumn+1,yyline+1,new String(yytext()));} 
"!="	{return new Symbol(sym.NOTEQL,yycolumn+1,yyline+1,new String(yytext()));} 
">="	{return new Symbol(sym.GEQL,yycolumn+1,yyline+1,new String(yytext()));} 
"<="	{return new Symbol(sym.LEQL,yycolumn+1,yyline+1,new String(yytext()));} 
"&&"	{return new Symbol(sym.AND,yycolumn+1,yyline+1,new String(yytext()));} 
"||"	{return new Symbol(sym.OR,yycolumn+1,yyline+1,new String(yytext()));} 
"!"		{return new Symbol(sym.NOT,yycolumn+1,yyline+1,new String(yytext()));} 
"&"		{return new Symbol(sym.REF,yycolumn+1,yyline+1,new String(yytext()));} 
"<"		{return new Symbol(sym.LESS,yycolumn+1,yyline+1,new String(yytext()));} 
">"		{return new Symbol(sym.GREATER,yycolumn+1,yyline+1,new String(yytext()));} 

"++"	{return new Symbol(sym.PLUSPLUS,yycolumn+1,yyline+1,new String(yytext()));} 
"--"	{return new Symbol(sym.MINUSMINUS,yycolumn+1,yyline+1,new String(yytext()));} 

"+="	{return new Symbol(sym.PLUSEQUALS,yycolumn+1,yyline+1,new String(yytext()));} 
"-="	{return new Symbol(sym.MINUSEQUALS,yycolumn+1,yyline+1,new String(yytext()));} 
"*="	{return new Symbol(sym.TIMESEQUALS,yycolumn+1,yyline+1,new String(yytext()));} 
"/="	{return new Symbol(sym.DIVEQUALS,yycolumn+1,yyline+1,new String(yytext()));} 

"."		{return new Symbol(sym.DOT,yycolumn+1,yyline+1,new String(yytext()));} 
","		{return new Symbol(sym.COMA,yycolumn+1,yyline+1,new String(yytext()));} 
"="		{return new Symbol(sym.EQL,yycolumn+1,yyline+1,new String(yytext()));} 
"+"		{return new Symbol(sym.PLUS,yycolumn+1,yyline+1,new String(yytext()));} 
"-"		{return new Symbol(sym.MINUS,yycolumn+1,yyline+1,new String(yytext()));} 
"/"		{return new Symbol(sym.DIV,yycolumn+1,yyline+1,new String(yytext()));} 
"*"		{return new Symbol(sym.TIMES,yycolumn+1,yyline+1,new String(yytext()));} 
"^"		{return new Symbol(sym.POW,yycolumn+1,yyline+1,new String(yytext()));} 
"?"		{return new Symbol(sym.ASK,yycolumn+1,yyline+1,new String(yytext()));} 
":"		{return new Symbol(sym.COLON,yycolumn+1,yyline+1,new String(yytext()));} 

"("		{return new Symbol(sym.LPAREN,yycolumn+1,yyline+1,new String(yytext()));} 
")"		{return new Symbol(sym.RPAREN,yycolumn+1,yyline+1,new String(yytext()));} 
"{"		{return new Symbol(sym.LBRACKET,yycolumn+1,yyline+1,new String(yytext()));} 
"}"		{return new Symbol(sym.RBRACKET,yycolumn+1,yyline+1,new String(yytext()));} 
"["		{return new Symbol(sym.LHOOK,yycolumn+1,yyline+1,new String(yytext()));} 
"]"		{return new Symbol(sym.RHOOK,yycolumn+1,yyline+1,new String(yytext()));} 

"#"		{return new Symbol(sym.SHARP,yycolumn+1,yyline+1,new String(yytext()));} 

"import"		{return new Symbol(sym.IMPORT,yycolumn+1,yyline+1,new String(yytext()));} 
"public"		{return new Symbol(sym.PUBLIC,yycolumn+1,yyline+1,new String(yytext()));} 
"private"		{return new Symbol(sym.PRIVATE,yycolumn+1,yyline+1,new String(yytext()));} 
"void"			{return new Symbol(sym.VOID,yycolumn+1,yyline+1,new String(yytext()));} 
"Array"			{return new Symbol(sym.ARRAY,yycolumn+1,yyline+1,new String(yytext()));} 
"switch"		{return new Symbol(sym.SWITCH,yycolumn+1,yyline+1,new String(yytext()));} 
"case"			{return new Symbol(sym.CASE,yycolumn+1,yyline+1,new String(yytext()));} 
"break"			{return new Symbol(sym.BREAK,yycolumn+1,yyline+1,new String(yytext()));} 
"default"		{return new Symbol(sym.DEFAULT,yycolumn+1,yyline+1,new String(yytext()));} 
"if"			{return new Symbol(sym.IF,yycolumn+1,yyline+1,new String(yytext()));} 
"else"			{return new Symbol(sym.ELSE,yycolumn+1,yyline+1,new String(yytext()));} 
"continue"		{return new Symbol(sym.CONTINUE,yycolumn+1,yyline+1,new String(yytext()));} 
"for"			{return new Symbol(sym.FOR,yycolumn+1,yyline+1,new String(yytext()));} 
"do"			{return new Symbol(sym.DO,yycolumn+1,yyline+1,new String(yytext()));} 
"while"			{return new Symbol(sym.WHILE,yycolumn+1,yyline+1,new String(yytext()));} 
"new"			{return new Symbol(sym.NEW,yycolumn+1,yyline+1,new String(yytext()));} 
"int"			{return new Symbol(sym.INT,yycolumn+1,yyline+1,new String(yytext()));} 
"float"			{return new Symbol(sym.FLOAT,yycolumn+1,yyline+1,new String(yytext()));} 
"bool"			{return new Symbol(sym.BOOL,yycolumn+1,yyline+1,new String(yytext()));} 
"string"		{return new Symbol(sym.STRING,yycolumn+1,yyline+1,new String(yytext()));} 
"char"			{return new Symbol(sym.CHAR,yycolumn+1,yyline+1,new String(yytext()));} 
"Linea"			{return new Symbol(sym.LINEA,yycolumn+1,yyline+1,new String(yytext()));} 
"Texto"			{return new Symbol(sym.TEXTO,yycolumn+1,yyline+1,new String(yytext()));} 
"Arco"			{return new Symbol(sym.ARC0,yycolumn+1,yyline+1,new String(yytext()));} 
"Rectangulo"	{return new Symbol(sym.RECTANGULO,yycolumn+1,yyline+1,new String(yytext()));} 
"Ovalo"			{return new Symbol(sym.OVALO,yycolumn+1,yyline+1,new String(yytext()));} 
"Poligono"		{return new Symbol(sym.POLIGONO,yycolumn+1,yyline+1,new String(yytext()));} 
"Lienzo"		{return new Symbol(sym.LIENZO,yycolumn+1,yyline+1,new String(yytext()));} 
"Imprimir"		{return new Symbol(sym.IMPRIMIR,yycolumn+1,yyline+1,new String(yytext()));} 

"true"			{return new Symbol(sym.TRUE,yycolumn+1,yyline+1,new String(yytext()));} 
"false"			{return new Symbol(sym.FALSE,yycolumn+1,yyline+1,new String(yytext()));} 
"null"			{return new Symbol(sym.NULL,yycolumn+1,yyline+1,new String(yytext()));} 
"this"			{return new Symbol(sym.THIS,yycolumn+1,yyline+1,new String(yytext()));} 
"return"		{return new Symbol(sym.RETURN,yycolumn+1,yyline+1,new String(yytext()));} 
"class"			{return new Symbol(sym.CLASS,yycolumn+1,yyline+1,new String(yytext()));} 
"extends" 		{return new Symbol(sym.EXTENDS,yycolumn+1,yyline+1,new String(yytext()));}
"static"		{return new Symbol(sym.STATIC,yycolumn+1,yyline+1,new String(yytext()));} 


{ID}			{return new Symbol(sym.ID,yycolumn+1,yyline+1,new String(yytext()));} 
{DEC}			{return new Symbol(sym.DEC,yycolumn+1,yyline+1,new String(yytext()));} 
{NUM}			{return new Symbol(sym.NUM,yycolumn+1,yyline+1,new String(yytext()));} 
{CADENA} 		{return new Symbol(sym.CAD,yycolumn+1,yyline+1,new String(yytext()).replaceAll("\"",""));}
{CARACTER}		{return new Symbol(sym.CHR,yycolumn+1,yyline+1,new String(yytext()).replaceAll("'",""));}

[\n][ \n\t\r\f]*	{return new Symbol(sym.NEWLINE,yycolumn+1,yyline+1,new String(yytext()));} 

/* BLANCOS */
[ \t\r\f]      {}

/* Cualquier Otro */
.                 {ErrorManager.add(new String(yytext()),yyline+1,yycolumn+1,Err.LEXICAL);}

