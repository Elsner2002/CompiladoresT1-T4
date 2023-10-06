//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "mini_java.y"
import java.io.*;
//#line 19 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short CLASS=257;
public final static short PUBLIC=258;
public final static short STATIC=259;
public final static short VOID=260;
public final static short MAIN=261;
public final static short STRING=262;
public final static short EXTENDS=263;
public final static short RETURN=264;
public final static short BOOLEAN=265;
public final static short INT=266;
public final static short IF=267;
public final static short ELSE=268;
public final static short WHILE=269;
public final static short PRINT=270;
public final static short IDENTIFIER=271;
public final static short INTEGERLITERAL=272;
public final static short LENGTH=273;
public final static short TRUE=274;
public final static short FALSE=275;
public final static short NEW=276;
public final static short THIS=277;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    2,    4,    4,    4,    4,    4,    4,
    4,    4,    5,    5,    6,    6,    7,    8,    8,    8,
    8,    8,    8,    8,    8,   10,   10,   11,   11,    9,
    9,    9,    9,    3,    3,    3,    3,    3,    3,    3,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   14,   14,   13,   13,   13,   13,
   13,
};
final static short yylen[] = {                            2,
    2,    1,   17,    2,    8,    6,    7,    5,    7,    5,
    6,    4,    2,    1,    2,    1,    3,   13,   12,   12,
   11,   12,   11,   11,   10,    4,    2,    2,    1,    3,
    1,    1,    1,    3,    2,    7,    5,    5,    4,    7,
    3,    4,    3,    5,    6,    1,    1,    1,    1,    1,
    5,    4,    2,    3,    3,    1,    1,    1,    1,    1,
    1,
};
final static short yydefred[] = {                         0,
    0,    0,    2,    0,    0,    0,    0,    4,    0,    0,
    0,    0,    0,    0,    0,    0,   31,    0,   33,   12,
    0,    0,   14,   16,    0,    0,    0,    0,    0,   10,
    0,   13,    8,   15,    0,    0,   11,    0,    0,    0,
   30,    6,   17,    0,    9,    0,    7,    0,    0,    5,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   29,    0,    0,    0,    0,    0,
   49,   46,   47,   48,    0,   50,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   35,    0,    0,    0,    0,
   28,   26,    0,    0,    0,    0,    0,    0,    0,    0,
   58,   57,   60,   59,   61,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   34,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   54,    0,   25,    0,   43,
    0,    0,    0,    0,   39,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   52,   42,    0,    0,   37,
   38,    0,   21,    0,   24,   23,    0,    0,    0,    3,
   51,   44,    0,    0,    0,    0,   20,   19,    0,   22,
   45,    0,   36,   40,   18,    0,
};
final static short yydgoto[] = {                          2,
    3,    5,   65,    8,   21,   22,   23,   24,   25,   53,
   67,   79,  109,  164,
};
final static short yysindex[] = {                      -251,
 -250,    0,    0,  -96, -219, -204, -232,    0, -196, -122,
 -193, -199,    8, -188,  -46, -261,    0,   -5,    0,    0,
   62, -114,    0,    0, -183,   57,   73, -164,   28,    0,
  -89,    0,    0,    0,   70, -113,    0,   82,  -63,  115,
    0,    0,    0,   49,    0,  -47,    0,  -39,   64,    0,
   38, -109,  126,  -90,  -52,  148,   74,  161,  -24,  164,
  176,  184,  -58,  -34,    0,   -7,    1, -261,   31,   80,
    0,    0,    0,    0, -254,    0,  -24,  -24,    5,  -24,
  -24,  -24,  -24,  -24,  -58,    0,   65,  -24,   11,  -24,
    0,    0,  -24,   43,   55,   98,  137,  190,  -23,  163,
    0,    0,    0,    0,    0,  -24,  109, -258,  -24,  -17,
  -11,   -1,   10,   15,    0,   39,  -24,   67,   72,  -24,
   87,  -24,  120,  -24,  199,    0,   77,    0,  206,    0,
  163,   98,   98,  197,    0,  188,  144,   93,  151,  159,
  100,  -24,  105,  166,  129,    0,    0,  -33,   18,    0,
    0,  -24,    0,  167,    0,    0,  174,  134,  179,    0,
    0,    0,  163,   51,   98,  140,    0,    0,  180,    0,
    0,  -24,    0,    0,    0,  163,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,  289,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   45,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  265,    0,    0,    0,    0,
    0,    0,   50,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   35,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  305,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   59,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   61,
};
final static short yygindex[] = {                         0,
    0,    0,  292,    0,   56,   66,  135,  272,   -2,  255,
  221,  313,    0,    0,
};
final static int YYTABLESIZE=485;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         78,
   13,   51,   83,   17,   18,    1,   77,  162,   78,   19,
   33,   97,  129,   28,  130,   77,   98,  126,  105,  104,
    4,  103,  108,  132,  105,  104,    6,  103,  108,  133,
  105,  104,   84,  103,  108,   42,  101,    7,   10,  134,
  105,  104,  101,  103,  108,   52,  105,  104,  101,  103,
  108,  105,  104,    9,  103,  108,  105,  104,  101,  103,
  108,   47,   11,  107,  101,   52,   14,  106,  135,  101,
   64,   15,   26,  106,  101,   53,   27,   50,   53,  106,
  105,  104,   38,  103,  108,   29,   31,   35,   64,  106,
   86,  171,   39,   53,  172,  106,   36,  137,  101,   56,
  106,   55,   56,   46,   55,  106,   40,  136,  105,  104,
   66,  103,  108,  105,  104,   64,  103,  108,  105,  104,
   41,  103,  108,   64,   94,  139,  101,   53,   43,  106,
  140,  101,   20,   64,  105,  104,  101,  103,  108,   49,
   12,  105,  104,   16,  103,  108,  105,  104,   44,  103,
  108,  154,  101,   64,   48,   32,   54,  106,  157,  101,
   55,   56,  106,  159,  101,   64,   57,  106,   16,  147,
  105,  104,   32,  103,  108,  105,  104,   64,  103,  108,
   58,  105,  104,  106,  103,  108,   30,   64,  101,  115,
  106,   68,  169,  101,   16,  106,   69,   37,  174,  101,
   32,   70,   96,   80,  105,  104,   45,  103,  108,   64,
   16,   59,   17,   18,   60,   81,   61,   62,   63,  106,
   64,  161,  101,   82,  106,   17,   18,  124,   32,  125,
  106,   19,   60,  128,   61,   62,   85,   71,   72,  146,
   73,   74,   75,   76,  144,  148,   71,   72,  152,   73,
   74,   75,   76,  106,  102,  151,   88,   17,   18,   60,
  102,   61,   62,   63,   90,   16,  102,   60,  153,   61,
   62,   85,   17,   18,  117,  155,  102,   60,   19,   61,
   62,   85,  102,  156,   87,  165,   89,  102,    1,   95,
  160,  167,  102,   34,   93,   17,   18,   60,  168,   61,
   62,   63,   34,  170,  175,   27,  120,   17,   18,   60,
   34,   61,   62,   63,  121,   32,  102,   34,  122,   16,
   33,   60,   92,   61,   62,   85,   17,   18,    0,    0,
   16,   60,   19,   61,   62,   85,    0,   17,   18,   16,
    0,    0,    0,   19,  102,   41,   17,   18,   41,  102,
  142,    0,   19,   60,  102,   61,   62,   85,   91,    0,
    0,    0,    0,   41,   60,    0,   61,   62,   85,    0,
  102,    0,    0,    0,    0,    0,    0,  102,   91,    0,
   91,    0,  102,    0,    0,    0,   91,  123,    0,   99,
  100,    0,  110,  111,  112,  113,  114,   41,    0,    0,
  116,    0,  118,    0,    0,  119,  102,    0,    0,    0,
    0,  102,   91,    0,    0,    0,    0,  102,  127,    0,
    0,  131,    0,  149,  150,    0,    0,    0,    0,  138,
    0,    0,  141,    0,  143,    0,  145,    0,    0,    0,
  102,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  158,    0,  173,    0,    0,    0,
  163,    0,    0,    0,  166,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  176,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
  123,   41,   61,  265,  266,  257,   40,   41,   33,  271,
  125,  266,  271,   16,  273,   40,  271,   41,   42,   43,
  271,   45,   46,   41,   42,   43,  123,   45,   46,   41,
   42,   43,   91,   45,   46,  125,   60,  257,  271,   41,
   42,   43,   60,   45,   46,   48,   42,   43,   60,   45,
   46,   42,   43,  258,   45,   46,   42,   43,   60,   45,
   46,  125,  259,   59,   60,   68,  260,   91,   59,   60,
  123,  271,  261,   91,   60,   41,  123,  125,   44,   91,
   42,   43,   27,   45,   46,   91,   21,  271,  123,   91,
  125,   41,   27,   59,   44,   91,   40,   59,   60,   41,
   91,   41,   44,   38,   44,   91,  271,   93,   42,   43,
   55,   45,   46,   42,   43,  123,   45,   46,   42,   43,
   93,   45,   46,  123,   69,   59,   60,   93,   59,   91,
   59,   60,  125,  123,   42,   43,   60,   45,   46,   91,
  263,   42,   43,  258,   45,   46,   42,   43,  262,   45,
   46,   59,   60,  123,   40,   21,   93,   91,   59,   60,
  123,  271,   91,   59,   60,  123,   41,   91,  258,   93,
   42,   43,   38,   45,   46,   42,   43,  123,   45,   46,
  271,   42,   43,   91,   45,   46,  125,  123,   60,  125,
   91,   44,   59,   60,  258,   91,  123,  125,   59,   60,
   66,   41,  123,   40,   42,   43,  125,   45,   46,  123,
  258,  264,  265,  266,  267,   40,  269,  270,  271,   91,
  123,   93,   60,   40,   91,  265,  266,   91,   94,   40,
   91,  271,  267,  125,  269,  270,  271,  271,  272,   41,
  274,  275,  276,  277,  125,   40,  271,  272,   61,  274,
  275,  276,  277,   91,  278,   59,  264,  265,  266,  267,
  278,  269,  270,  271,  264,  258,  278,  267,  125,  269,
  270,  271,  265,  266,  264,  125,  278,  267,  271,  269,
  270,  271,  278,  125,   64,  268,   66,  278,    0,   69,
  125,  125,  278,   22,  264,  265,  266,  267,  125,  269,
  270,  271,   31,  125,  125,   41,  264,  265,  266,  267,
   39,  269,  270,  271,   94,  271,  278,   46,  264,  258,
  271,  267,   68,  269,  270,  271,  265,  266,   -1,   -1,
  258,  267,  271,  269,  270,  271,   -1,  265,  266,  258,
   -1,   -1,   -1,  271,  278,   41,  265,  266,   44,  278,
  264,   -1,  271,  267,  278,  269,  270,  271,   67,   -1,
   -1,   -1,   -1,   59,  267,   -1,  269,  270,  271,   -1,
  278,   -1,   -1,   -1,   -1,   -1,   -1,  278,   87,   -1,
   89,   -1,  278,   -1,   -1,   -1,   95,   96,   -1,   77,
   78,   -1,   80,   81,   82,   83,   84,   93,   -1,   -1,
   88,   -1,   90,   -1,   -1,   93,  278,   -1,   -1,   -1,
   -1,  278,  121,   -1,   -1,   -1,   -1,  278,  106,   -1,
   -1,  109,   -1,  132,  133,   -1,   -1,   -1,   -1,  117,
   -1,   -1,  120,   -1,  122,   -1,  124,   -1,   -1,   -1,
  278,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  142,   -1,  165,   -1,   -1,   -1,
  148,   -1,   -1,   -1,  152,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  172,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=278;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,null,null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'",null,null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"CLASS","PUBLIC","STATIC","VOID",
"MAIN","STRING","EXTENDS","RETURN","BOOLEAN","INT","IF","ELSE","WHILE","PRINT",
"IDENTIFIER","INTEGERLITERAL","LENGTH","TRUE","FALSE","NEW","THIS","\"&&\"",
};
final static String yyrule[] = {
"$accept : Goal",
"Goal : MainClass ClassDeclaretionRepetition",
"Goal : MainClass",
"MainClass : CLASS IDENTIFIER '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENTIFIER ')' '{' Statement '}' '}'",
"ClassDeclaretionRepetition : ClassDeclaretionRepetition ClassDeclaretion",
"ClassDeclaretion : CLASS IDENTIFIER EXTENDS IDENTIFIER '{' VarDeclaretionRepetition MethodDeclarationRepetition '}'",
"ClassDeclaretion : CLASS IDENTIFIER '{' VarDeclaretionRepetition MethodDeclarationRepetition '}'",
"ClassDeclaretion : CLASS IDENTIFIER EXTENDS IDENTIFIER '{' MethodDeclarationRepetition '}'",
"ClassDeclaretion : CLASS IDENTIFIER '{' MethodDeclarationRepetition '}'",
"ClassDeclaretion : CLASS IDENTIFIER EXTENDS IDENTIFIER '{' VarDeclaretionRepetition '}'",
"ClassDeclaretion : CLASS IDENTIFIER '{' VarDeclaretionRepetition '}'",
"ClassDeclaretion : CLASS IDENTIFIER EXTENDS IDENTIFIER '{' '}'",
"ClassDeclaretion : CLASS IDENTIFIER '{' '}'",
"VarDeclaretionRepetition : VarDeclaretionRepetition VarDeclaretion",
"VarDeclaretionRepetition : VarDeclaretion",
"MethodDeclarationRepetition : MethodDeclarationRepetition MethodDeclaration",
"MethodDeclarationRepetition : MethodDeclaration",
"VarDeclaretion : Type IDENTIFIER ';'",
"MethodDeclaration : PUBLIC Type IDENTIFIER '(' DeclarationTypesRepetition ')' '{' VarDeclaretionRepetition StatementRepetition RETURN Expression ';' '}'",
"MethodDeclaration : PUBLIC Type IDENTIFIER '(' DeclarationTypesRepetition ')' '{' VarDeclaretionRepetition RETURN Expression ';' '}'",
"MethodDeclaration : PUBLIC Type IDENTIFIER '(' ')' '{' VarDeclaretionRepetition StatementRepetition RETURN Expression ';' '}'",
"MethodDeclaration : PUBLIC Type IDENTIFIER '(' ')' '{' VarDeclaretionRepetition RETURN Expression ';' '}'",
"MethodDeclaration : PUBLIC Type IDENTIFIER '(' DeclarationTypesRepetition ')' '{' StatementRepetition RETURN Expression ';' '}'",
"MethodDeclaration : PUBLIC Type IDENTIFIER '(' DeclarationTypesRepetition ')' '{' RETURN Expression ';' '}'",
"MethodDeclaration : PUBLIC Type IDENTIFIER '(' ')' '{' StatementRepetition RETURN Expression ';' '}'",
"MethodDeclaration : PUBLIC Type IDENTIFIER '(' ')' '{' RETURN Expression ';' '}'",
"DeclarationTypesRepetition : Type IDENTIFIER ',' DeclarationTypesRepetition",
"DeclarationTypesRepetition : Type IDENTIFIER",
"StatementRepetition : StatementRepetition Statement",
"StatementRepetition : Statement",
"Type : INT '[' ']'",
"Type : BOOLEAN",
"Type : INT",
"Type : IDENTIFIER",
"Statement : '{' StatementRepetition '}'",
"Statement : '{' '}'",
"Statement : IF '(' Expression ')' Statement ELSE Statement",
"Statement : WHILE '(' Expression ')' Statement",
"Statement : PRINT '(' Expression ')' ';'",
"Statement : IDENTIFIER '=' Expression ';'",
"Statement : IDENTIFIER '[' Expression ']' '=' Expression ';'",
"Expression : Expression Operator Expression",
"Expression : Expression '[' Expression ']'",
"Expression : Expression '.' LENGTH",
"Expression : Expression '.' IDENTIFIER '(' ')'",
"Expression : Expression '.' IDENTIFIER '(' ExpressionRepetition ')'",
"Expression : INTEGERLITERAL",
"Expression : TRUE",
"Expression : FALSE",
"Expression : IDENTIFIER",
"Expression : THIS",
"Expression : NEW INT '[' Expression ']'",
"Expression : NEW IDENTIFIER '(' ')'",
"Expression : '!' Expression",
"Expression : '(' Expression ')'",
"ExpressionRepetition : ExpressionRepetition ',' Expression",
"ExpressionRepetition : Expression",
"Operator : \"&&\"",
"Operator : '<'",
"Operator : '+'",
"Operator : '-'",
"Operator : '*'",
};

//#line 107 "mini_java.y"

  private Yylex lexer;


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }


  static boolean interactive;

  public static void main(String args[]) throws IOException {
    System.out.println("BYACC/Java with JFlex Calculator Demo");

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Expression: ");
      interactive = true;
	    yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();
    
    if (interactive) {
      System.out.println();
      System.out.println("Have a nice day");
    }
  }
//#line 448 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
