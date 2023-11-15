%%

%byaccj

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }

public static int IDENT = 257;
public static int NUM = 258;

public static int IF = 259; 
public static int ELSE = 260;
public static int PUBLIC = 261;
public static int PRIVATE = 262;
public static int CLASS = 263;
public static int EQUALS = 264;
public static int INT = 265;
public static int STATIC = 266;
public static int VOID = 267;
public static int MAIN = 268;
public static int WHILE = 269;
public static int TRUE = 270;
public static int FALSE = 271;
public static int THIS = 272;
public static int NEW = 273;
public static int BOOLEAN = 274;
public static int EXTENDS = 275;
public static int RETURN = 276;
public static int PRINT = 277;
public static int STRING = 278;
public static int COMMENT = 279;
public static int AND = 280;

  public int getLine() {
      return yyline;
  }

%} 
%integer
%line
%char

DIGIT= [0-9]
LETTER=		[a-zA-Z]
WHITESPACE= [ \t]
SYMBOL = [^\>]*
LineTerminator = \r|\n|\r\n



%%

"$TRACE_ON"  { yyparser.setDebug(true);  }
"$TRACE_OFF" { yyparser.setDebug(false); }
"$MOSTRA_TS" { yyparser.listarTS(); }

"//" [^\r\n]* { }
"/*" .* "*/" { }


if				{return Parser.IF;}
else			{return Parser.ELSE;} 
public		{return Parser.PUBLIC;}
class			{return Parser.CLASS;}
int       {return Parser.INT;}
static    {return Parser.STATIC;}
void      {return Parser.VOID;}
main      {return Parser.MAIN;}
while     {return Parser.WHILE;}
true      {return Parser.TRUE;}
false     {return Parser.FALSE;}
this      {return Parser.THIS;}
new       {return Parser.NEW;}
boolean   {return Parser.BOOLEAN;}
extends   {return Parser.EXTENDS;}
return    {return Parser.RETURN;}
System.out.println  {return Parser.PRINT;}
String    {return Parser.STRING;}
&&        {return Parser.AND;}


{LETTER}({LETTER}|{DIGIT}|_)* { yyparser.yylval = new ParserVal(yytext());
                                return Parser.IDENT;}
{DIGIT}+                    {return Parser.NUM;}

":" |
"{" |
"}" |
"[" |
"]" |
"(" |
")" |
"." |
";" |
"_" |
"-" |
"=" |
"*" |
"<" |
"!" |
"?" |
"+" |
"," {return yytext().charAt(0);}

{WHITESPACE}+ { }
{LineTerminator} {}
. {System.out.println(yyline+1 + ": caracter invalido: "+yytext());}