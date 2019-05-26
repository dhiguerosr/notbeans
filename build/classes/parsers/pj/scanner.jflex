
package parsers.pj;

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


CADENA = [\"][^\"\n]*[\"\n] 

%{
/*CODIGO USUARIO*/ 
%}

%%

/* Comentarios*/

"<" 			{return new Symbol(sym.OPEN,yycolumn+1,yyline+1,new String(yytext()));} 
">" 			{return new Symbol(sym.CLOSE,yycolumn+1,yyline+1,new String(yytext()));}
"proyecto"  	{return new Symbol(sym.PROYECTO,yycolumn+1,yyline+1,new String(yytext()));}
"nombre" 		{return new Symbol(sym.NOMBRE,yycolumn+1,yyline+1,new String(yytext()));}
"ruta" 			{return new Symbol(sym.RUTA,yycolumn+1,yyline+1,new String(yytext()));}  
"archivos" 		{return new Symbol(sym.ARCHIVOS,yycolumn+1,yyline+1,new String(yytext()));} 
"archivo" 		{return new Symbol(sym.ARCHIVO,yycolumn+1,yyline+1,new String(yytext()));} 
"principal" 	{return new Symbol(sym.PRINCIPAL,yycolumn+1,yyline+1,new String(yytext()));} 
"/"				{return new Symbol(sym.PRECLOSE,yycolumn+1,yyline+1,new String(yytext()));} 
  
"="				{return new Symbol(sym.EQL,yycolumn+1,yyline+1,new String(yytext()));} 

{CADENA} 		{return new Symbol(sym.CAD,yycolumn+1,yyline+1,new String(yytext()).replaceAll("\"",""));}

/* BLANCOS */
[ \n\t\r\f]      {}

/* Cualquier Otro */
.                 {/* LANZAR ERROR LEXICO*/}

