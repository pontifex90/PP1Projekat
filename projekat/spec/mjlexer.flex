package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}
%}

%cup
%line
%column

%xstate COMMENT

%eofval{ 
return new_symbol(sym.EOF);
%eofval}

%%
" " {}
"\b" {}
"\t" {}
"\r\n" {}
"\f" {}
"program"   { return new_symbol(sym.PROG, yytext()); }
"const"		{ return new_symbol(sym.CONST, yytext()); }
"class" 	{ return new_symbol(sym.CLASS, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"extends" 	{ return new_symbol(sym.EXTENDS, yytext()); }
"static"	{ return new_symbol(sym.STATIC, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"read"	 	{ return new_symbol(sym.READ, yytext()); }
"continue" 	{ return new_symbol(sym.CONTINUE, yytext()); }
"break" 	{ return new_symbol(sym.BREAK, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"if" 		{ return new_symbol(sym.IF, yytext()); }
"else" 		{ return new_symbol(sym.ELSE, yytext()); }
"new" 		{ return new_symbol(sym.NEW, yytext()); }
"for" 		{ return new_symbol(sym.FOR, yytext()); }
"this" 		{ return new_symbol(sym.THIS, yytext()); }
"super" 	{ return new_symbol(sym.SUPER, yytext()); }
"=" 		{ return new_symbol(sym.EQUALS, yytext()); }
"{" 		{ return new_symbol(sym.L_BRACE, yytext()); }
"}" 		{ return new_symbol(sym.R_BRACE, yytext()); }
"[" 		{ return new_symbol(sym.L_INDEXER, yytext()); }
"]" 		{ return new_symbol(sym.R_INDEXER, yytext()); }
"(" 		{ return new_symbol(sym.L_PAREN, yytext()); }
")"			{ return new_symbol(sym.R_PAREN, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MULTIPLY, yytext()); }
"/" 		{ return new_symbol(sym.DIVIDE, yytext()); }
"." 		{ return new_symbol(sym.DOT, yytext()); }
"++" 		{ return new_symbol(sym.INCREMENT, yytext()); }
"--" 		{ return new_symbol(sym.DECREMENT, yytext()); }
"+=" 		{ return new_symbol(sym.PLUS_EQUALS, yytext()); }
"-=" 		{ return new_symbol(sym.MINUS_EQUALS, yytext()); }
"%" 		{ return new_symbol(sym.MODULATE, yytext()); }
"*=" 		{ return new_symbol(sym.MULTIPLY_EQUALS, yytext()); }
"/=" 		{ return new_symbol(sym.DIVIDE_EQUALS, yytext()); }
"%=" 		{ return new_symbol(sym.MODULATE_EQUALS, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"||" 		{ return new_symbol(sym.OR, yytext()); }
"==" 		{ return new_symbol(sym.EQUALITY, yytext()); }
"!=" 		{ return new_symbol(sym.NOT_EQUAL, yytext()); }
">" 		{ return new_symbol(sym.GREATER, yytext()); }
">=" 		{ return new_symbol(sym.GREATER_EQUAL, yytext()); }
"<" 		{ return new_symbol(sym.LESS, yytext()); }
"<=" 		{ return new_symbol(sym.LESS_EQUAL, yytext()); }

"//"			{yybegin(COMMENT);}
<COMMENT>. 		{yybegin(COMMENT);}
<COMMENT>"\r\n" {yybegin(YYINITIAL);}

"true"|"false" {return new_symbol(sym.BOOL, new Boolean (yytext()));}

[0-9]+ {return new_symbol(sym.NUMBER, new Integer (yytext()));}

([a-z]|[A-Z])[a-z|A-Z|0-9|_]* {return new_symbol (sym.IDENT, yytext());}

"'"[\040-\176]"'" {return new_symbol (sym.CHAR, new Character (yytext().charAt(1)));}

. {System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1));}