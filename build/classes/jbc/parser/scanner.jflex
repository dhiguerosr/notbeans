
package jbc.parser;

import java_cup.runtime.Symbol;

%%
%cupsym sym
%caseless
%ignorecase
%class Scanner
%cup
%public
%line
%column

%init{ 
	/*INICIALIZAR ERRORES LEXICOS*/
%init}

%{
	/*CODIGO USUARIO*/
%}

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

"=="			{return new Symbol(sym.EQUALS,yycolumn+1,yyline+1,new String(yytext()));} 
"!="			{return new Symbol(sym.NOTEQL,yycolumn+1,yyline+1,new String(yytext()));} 
">="			{return new Symbol(sym.GEQL,yycolumn+1,yyline+1,new String(yytext()));} 
"<="			{return new Symbol(sym.LEQL,yycolumn+1,yyline+1,new String(yytext()));}  
"<"				{return new Symbol(sym.LESS,yycolumn+1,yyline+1,new String(yytext()));} 
">"				{return new Symbol(sym.GREATER,yycolumn+1,yyline+1,new String(yytext()));} 

"="				{return new Symbol(sym.EQL,yycolumn+1,yyline+1,new String(yytext()));} 
"+"				{return new Symbol(sym.PLUS,yycolumn+1,yyline+1,new String(yytext()));} 
"-"				{return new Symbol(sym.MINUS,yycolumn+1,yyline+1,new String(yytext()));} 
"/"				{return new Symbol(sym.DIV,yycolumn+1,yyline+1,new String(yytext()));} 
"*"				{return new Symbol(sym.TIMES,yycolumn+1,yyline+1,new String(yytext()));} 
"^"				{return new Symbol(sym.POW,yycolumn+1,yyline+1,new String(yytext()));} 

"("				{return new Symbol(sym.LPAREN,yycolumn+1,yyline+1,new String(yytext()));} 
")"				{return new Symbol(sym.RPAREN,yycolumn+1,yyline+1,new String(yytext()));} 
"{"				{return new Symbol(sym.LBRACKET,yycolumn+1,yyline+1,new String(yytext()));} 
"}"				{return new Symbol(sym.RBRACKET,yycolumn+1,yyline+1,new String(yytext()));} 
"["				{return new Symbol(sym.LHOOK,yycolumn+1,yyline+1,new String(yytext()));} 
"]"				{return new Symbol(sym.RHOOK,yycolumn+1,yyline+1,new String(yytext()));} 
":"				{return new Symbol(sym.COLON,yycolumn+1,yyline+1,new String(yytext()));}
";"				{return new Symbol(sym.SEMI_COLON,yycolumn+1,yyline+1,new String(yytext()));}

"stack"			{return new Symbol(sym.STACK,yycolumn+1,yyline+1,new String(yytext()));} 
"heap"			{return new Symbol(sym.HEAP,yycolumn+1,yyline+1,new String(yytext()));} 
"method"		{return new Symbol(sym.METHOD,yycolumn+1,yyline+1,new String(yytext()));} 
"call"			{return new Symbol(sym.CALL,yycolumn+1,yyline+1,new String(yytext()));} 
"goto"			{return new Symbol(sym.GOTO,yycolumn+1,yyline+1,new String(yytext()));} 
"if"			{return new Symbol(sym.IF,yycolumn+1,yyline+1,new String(yytext()));} 
"then"			{return new Symbol(sym.THEN,yycolumn+1,yyline+1,new String(yytext()));}
"strcat"		{return new Symbol(sym.STRCAT,yycolumn+1,yyline+1,new String(yytext()));}

"int_to_string" 									{return new Symbol(sym.INT_STRING,yycolumn+1,yyline+1,new String(yytext()));} 
"int_to_float"										{return new Symbol(sym.INT_FLOAT,yycolumn+1,yyline+1,new String(yytext()));} 
"float_to_int"										{return new Symbol(sym.FLOAT_INT,yycolumn+1,yyline+1,new String(yytext()));} 
"int_to_char"										{return new Symbol(sym.INT_CHAR,yycolumn+1,yyline+1,new String(yytext()));}

"float_to_string"									{return new Symbol(sym.FLOAT_STRING,yycolumn+1,yyline+1,new String(yytext()));}
"bool_to_string"									{return new Symbol(sym.BOOL_STRING,yycolumn+1,yyline+1,new String(yytext()));}

"char_to_int"										{return new Symbol(sym.CHAR_INT,yycolumn+1,yyline+1,new String(yytext()));}
"char_to_string"									{return new Symbol(sym.CHAR_STRING,yycolumn+1,yyline+1,new String(yytext()));}

"Linea_int_int_int_int_int_int_int"					{return new Symbol(sym.LINEA,yycolumn+1,yyline+1,new String(yytext()));} 
"Texto_string_int_int_int_int_int"					{return new Symbol(sym.TEXTO,yycolumn+1,yyline+1,new String(yytext()));} 
"Arco_int_int_int_int_int_int_int_int_int_bool"		{return new Symbol(sym.ARC0,yycolumn+1,yyline+1,new String(yytext()));} 
"Rectangulo_int_int_int_int_int_int_int_bool"		{return new Symbol(sym.RECTANGULO,yycolumn+1,yyline+1,new String(yytext()));} 
"Ovalo_int_int_int_int_int_int_int_bool"			{return new Symbol(sym.OVALO,yycolumn+1,yyline+1,new String(yytext()));} 
"Poligono_Array_Array_int_int_int_bool"				{return new Symbol(sym.POLIGONO,yycolumn+1,yyline+1,new String(yytext()));} 
"Lienzo_int_int_int_int_int"						{return new Symbol(sym.LIENZO,yycolumn+1,yyline+1,new String(yytext()));} 
"Imprimir_string"		{return new Symbol(sym.IMPRIMIR,yycolumn+1,yyline+1,new String(yytext()));} 



{ID}			{return new Symbol(sym.ID,yycolumn+1,yyline+1,new String(yytext()));} 
{DEC}			{return new Symbol(sym.DEC,yycolumn+1,yyline+1,new String(yytext()));} 
{NUM}			{return new Symbol(sym.NUM,yycolumn+1,yyline+1,new String(yytext()));} 

/* BLANCOS */
[ \n\t\r\f]      {}

/* Cualquier Otro */
.                 {System.out.println(yytext());}

