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
public final static short IDENT=271;
public final static short NUM=272;
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
   12,   12,   12,   12,   12,   12,   12,   12,   13,   14,
   14,
};
final static short yylen[] = {                            2,
    2,    1,   17,    2,    8,    6,    7,    5,    7,    5,
    6,    4,    2,    1,    2,    1,    3,   13,   12,   12,
   11,   12,   11,   11,   10,    4,    2,    2,    1,    3,
    1,    1,    1,    3,    2,    7,    5,    5,    4,    7,
    3,    3,    3,    3,    3,    4,    3,    5,    6,    1,
    1,    1,    1,    1,    5,    4,    2,    3,    1,    3,
    1,
};
final static short yydefred[] = {                         0,
    0,    0,    2,    0,    0,    0,    0,    4,    0,    0,
    0,    0,    0,    0,    0,    0,   31,    0,   33,   12,
    0,    0,   14,   16,    0,    0,    0,    0,    0,   10,
    0,   13,    8,   15,    0,    0,   11,    0,    0,    0,
   30,    6,   17,    0,    9,    0,    7,    0,    0,    5,
    0,    0,    0,    0,    0,   27,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   29,    0,    0,    0,    0,
    0,   53,   50,   51,   52,    0,   54,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   35,    0,    0,    0,
    0,   28,    0,    0,    0,   26,    0,    0,    0,    0,
    0,   57,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   34,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   58,   45,   41,   43,
   42,   44,    0,   25,    0,   47,    0,    0,    0,   39,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   56,   46,    0,    0,   37,   38,    0,   21,    0,   24,
   23,    0,    0,    0,    3,   55,   48,    0,    0,    0,
    0,   20,   19,    0,   22,   49,    0,   36,   40,   18,
    0,
};
final static short yydgoto[] = {                          2,
    3,    5,   66,    8,   21,   22,   23,   24,   25,   53,
   68,  101,  102,  169,
};
final static short yysindex[] = {                      -252,
 -258,    0,    0, -108, -240, -237, -244,    0, -221, -122,
 -214, -217,    8, -188,  -46, -199,    0,   -5,    0,    0,
   62, -114,    0,    0, -183,   81,   73, -169,   14,    0,
  -89,    0,    0,    0,   70, -113,    0,   82,  -63,  115,
    0,    0,    0,   49,    0,  -47,    0,  -39,   64,    0,
   38, -109,   59, -104,  -52,    0,   58, -199,  151,  -24,
  157,  176,  184,  -58,  -34,    0,   -7,    1,   31,  -68,
  107,    0,    0,    0,    0, -174,    0,  -24,  -24,    5,
  -24,  -24,  -24,  -24,  -24,  -58,    0,   65,  -24,   11,
  -24,    0,  -24,   43,   55,    0,   98,  137,  194,  -23,
  163,    0,  -24,  -24,  -24,  -24,  -24,  -24,  120, -267,
  -17,  -11,   -1,   10,   15,    0,   39,  -24,   67,   72,
  -24,   87,  -24,  131,  -24,  199,    0,    0,    0,    0,
    0,    0,   77,    0,  229,    0,   98,   98,  217,    0,
  224,  161,   93,  164,  165,  100,  -24,  105,  166,  129,
    0,    0,  -33,   24,    0,    0,  -24,    0,  174,    0,
    0,  179,  134,  180,    0,    0,    0,  163,  160,   98,
  140,    0,    0,  181,    0,    0,  -24,    0,    0,    0,
  163,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,  315,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   45,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   50,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   35,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  205,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  243,
};
final static short yygindex[] = {                         0,
    0,    0,  305,    0,   56,   66,  135,  272,   47,    0,
  -55,  303,  310,    0,
};
final static int YYTABLESIZE=480;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         79,
   13,   51,   84,  135,    1,  136,   78,  167,   79,   88,
   33,   90,    4,   95,    6,   78,    7,  127,  107,  106,
    9,  105,  110,  137,  107,  106,   10,  105,  110,  138,
  107,  106,   85,  105,  110,   42,  103,   11,  122,  139,
  107,  106,  103,  105,  110,   14,  107,  106,  103,  105,
  110,  107,  106,   15,  105,  110,  107,  106,  103,  105,
  110,   47,   28,  109,  103,   17,   18,  108,  140,  103,
   65,   19,   26,  108,  103,   59,   27,   50,   59,  108,
  107,  106,   38,  105,  110,   29,   31,   35,   65,  108,
   87,   98,   39,   59,   52,  108,   99,  142,  103,   57,
  108,   40,   58,   46,   70,  108,   41,  141,  107,  106,
   67,  105,  110,  107,  106,   65,  105,  110,  107,  106,
   36,  105,  110,   65,   94,  144,  103,   59,   43,  108,
  145,  103,   20,   65,  107,  106,  103,  105,  110,   49,
   12,  107,  106,   16,  105,  110,  107,  106,   44,  105,
  110,  159,  103,   65,   48,   32,   54,  108,  162,  103,
   55,   56,  108,  164,  103,   65,   59,  108,   16,  152,
  107,  106,   32,  105,  110,  107,  106,   65,  105,  110,
   69,  107,  106,  108,  105,  110,   30,   65,  103,  116,
  108,   71,  174,  103,   16,  108,   81,   37,  179,  103,
  176,   32,   96,  177,  107,  106,   45,  105,  110,   65,
   16,   60,   17,   18,   61,   82,   62,   63,   64,  108,
   65,  166,  103,   83,  108,   17,   18,  125,   32,   97,
  108,   19,   61,  126,   62,   63,   86,   72,   73,  151,
   74,   75,   76,   77,  134,   61,   72,   73,   61,   74,
   75,   76,   77,  108,  104,  149,   89,   17,   18,   61,
  104,   62,   63,   64,   91,   16,  104,   61,  153,   62,
   63,   86,   17,   18,  118,  156,  104,   61,   19,   62,
   63,   86,  104,   60,  157,  158,   60,  104,  160,  161,
  165,  170,  104,   34,   93,   17,   18,   61,  172,   62,
   63,   64,   34,  173,  175,  180,  121,   17,   18,   61,
   34,   62,   63,   64,    1,   32,  104,   34,  123,   16,
   33,   61,    0,   62,   63,   86,   17,   18,    0,    0,
   16,   61,   19,   62,   63,   86,    0,   17,   18,   16,
    0,    0,    0,   19,  104,    0,   17,   18,    0,  104,
  147,    0,   19,   61,  104,   62,   63,   86,    0,    0,
    0,    0,   80,    0,   61,    0,   62,   63,   86,    0,
  104,    0,   92,    0,    0,    0,    0,  104,    0,    0,
  100,    0,  104,  111,  112,  113,  114,  115,    0,    0,
    0,  117,   92,  119,   92,  120,    0,    0,    0,   92,
    0,  124,    0,    0,    0,    0,  104,    0,    0,    0,
  133,  104,  128,  129,  130,  131,  132,  104,    0,    0,
  143,    0,    0,  146,    0,  148,   92,  150,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  104,  154,  155,    0,    0,    0,    0,    0,    0,  163,
    0,    0,    0,    0,    0,  168,    0,    0,    0,  171,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  178,    0,    0,    0,    0,  181,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
  123,   41,   61,  271,  257,  273,   40,   41,   33,   65,
  125,   67,  271,   69,  123,   40,  257,   41,   42,   43,
  258,   45,   46,   41,   42,   43,  271,   45,   46,   41,
   42,   43,   91,   45,   46,  125,   60,  259,   94,   41,
   42,   43,   60,   45,   46,  260,   42,   43,   60,   45,
   46,   42,   43,  271,   45,   46,   42,   43,   60,   45,
   46,  125,   16,   59,   60,  265,  266,   91,   59,   60,
  123,  271,  261,   91,   60,   41,  123,  125,   44,   91,
   42,   43,   27,   45,   46,   91,   21,  271,  123,   91,
  125,  266,   27,   59,   48,   91,  271,   59,   60,   41,
   91,  271,   44,   38,   58,   91,   93,   93,   42,   43,
   55,   45,   46,   42,   43,  123,   45,   46,   42,   43,
   40,   45,   46,  123,   69,   59,   60,   93,   59,   91,
   59,   60,  125,  123,   42,   43,   60,   45,   46,   91,
  263,   42,   43,  258,   45,   46,   42,   43,  262,   45,
   46,   59,   60,  123,   40,   21,   93,   91,   59,   60,
  123,  271,   91,   59,   60,  123,  271,   91,  258,   93,
   42,   43,   38,   45,   46,   42,   43,  123,   45,   46,
  123,   42,   43,   91,   45,   46,  125,  123,   60,  125,
   91,   41,   59,   60,  258,   91,   40,  125,   59,   60,
   41,   67,  271,   44,   42,   43,  125,   45,   46,  123,
  258,  264,  265,  266,  267,   40,  269,  270,  271,   91,
  123,   93,   60,   40,   91,  265,  266,   91,   94,  123,
   91,  271,  267,   40,  269,  270,  271,  271,  272,   41,
  274,  275,  276,  277,  125,   41,  271,  272,   44,  274,
  275,  276,  277,   91,  278,  125,  264,  265,  266,  267,
  278,  269,  270,  271,  264,  258,  278,  267,   40,  269,
  270,  271,  265,  266,  264,   59,  278,  267,  271,  269,
  270,  271,  278,   41,   61,  125,   44,  278,  125,  125,
  125,  268,  278,   22,  264,  265,  266,  267,  125,  269,
  270,  271,   31,  125,  125,  125,  264,  265,  266,  267,
   39,  269,  270,  271,    0,  271,  278,   46,  264,  258,
  271,  267,   -1,  269,  270,  271,  265,  266,   -1,   -1,
  258,  267,  271,  269,  270,  271,   -1,  265,  266,  258,
   -1,   -1,   -1,  271,  278,   -1,  265,  266,   -1,  278,
  264,   -1,  271,  267,  278,  269,  270,  271,   -1,   -1,
   -1,   -1,   60,   -1,  267,   -1,  269,  270,  271,   -1,
  278,   -1,   68,   -1,   -1,   -1,   -1,  278,   -1,   -1,
   78,   -1,  278,   81,   82,   83,   84,   85,   -1,   -1,
   -1,   89,   88,   91,   90,   93,   -1,   -1,   -1,   95,
   -1,   97,   -1,   -1,   -1,   -1,  278,   -1,   -1,   -1,
  108,  278,  103,  104,  105,  106,  107,  278,   -1,   -1,
  118,   -1,   -1,  121,   -1,  123,  122,  125,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  278,  137,  138,   -1,   -1,   -1,   -1,   -1,   -1,  147,
   -1,   -1,   -1,   -1,   -1,  153,   -1,   -1,   -1,  157,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  170,   -1,   -1,   -1,   -1,  177,
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
"IDENT","NUM","LENGTH","TRUE","FALSE","NEW","THIS","\"&&\"",
};
final static String yyrule[] = {
"$accept : Goal",
"Goal : MainClass ClassDeclaretionRepetition",
"Goal : MainClass",
"MainClass : CLASS IDENT '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENT ')' '{' Statement '}' '}'",
"ClassDeclaretionRepetition : ClassDeclaretionRepetition ClassDeclaretion",
"ClassDeclaretion : CLASS IDENT EXTENDS IDENT '{' VarDeclaretionRepetition MethodDeclarationRepetition '}'",
"ClassDeclaretion : CLASS IDENT '{' VarDeclaretionRepetition MethodDeclarationRepetition '}'",
"ClassDeclaretion : CLASS IDENT EXTENDS IDENT '{' MethodDeclarationRepetition '}'",
"ClassDeclaretion : CLASS IDENT '{' MethodDeclarationRepetition '}'",
"ClassDeclaretion : CLASS IDENT EXTENDS IDENT '{' VarDeclaretionRepetition '}'",
"ClassDeclaretion : CLASS IDENT '{' VarDeclaretionRepetition '}'",
"ClassDeclaretion : CLASS IDENT EXTENDS IDENT '{' '}'",
"ClassDeclaretion : CLASS IDENT '{' '}'",
"VarDeclaretionRepetition : VarDeclaretionRepetition VarDeclaretion",
"VarDeclaretionRepetition : VarDeclaretion",
"MethodDeclarationRepetition : MethodDeclarationRepetition MethodDeclaration",
"MethodDeclarationRepetition : MethodDeclaration",
"VarDeclaretion : Type IDENT ';'",
"MethodDeclaration : PUBLIC Type IDENT '(' DeclarationTypesRepetition ')' '{' VarDeclaretionRepetition StatementRepetition RETURN Expression ';' '}'",
"MethodDeclaration : PUBLIC Type IDENT '(' DeclarationTypesRepetition ')' '{' VarDeclaretionRepetition RETURN Expression ';' '}'",
"MethodDeclaration : PUBLIC Type IDENT '(' ')' '{' VarDeclaretionRepetition StatementRepetition RETURN Expression ';' '}'",
"MethodDeclaration : PUBLIC Type IDENT '(' ')' '{' VarDeclaretionRepetition RETURN Expression ';' '}'",
"MethodDeclaration : PUBLIC Type IDENT '(' DeclarationTypesRepetition ')' '{' StatementRepetition RETURN Expression ';' '}'",
"MethodDeclaration : PUBLIC Type IDENT '(' DeclarationTypesRepetition ')' '{' RETURN Expression ';' '}'",
"MethodDeclaration : PUBLIC Type IDENT '(' ')' '{' StatementRepetition RETURN Expression ';' '}'",
"MethodDeclaration : PUBLIC Type IDENT '(' ')' '{' RETURN Expression ';' '}'",
"DeclarationTypesRepetition : DeclarationTypesRepetition ',' Type IDENT",
"DeclarationTypesRepetition : Type IDENT",
"StatementRepetition : StatementRepetition Statement",
"StatementRepetition : Statement",
"Type : INT '[' ']'",
"Type : BOOLEAN",
"Type : INT",
"Type : IDENT",
"Statement : '{' StatementRepetition '}'",
"Statement : '{' '}'",
"Statement : IF '(' Expression ')' Statement ELSE Statement",
"Statement : WHILE '(' Expression ')' Statement",
"Statement : PRINT '(' Expression ')' ';'",
"Statement : IDENT '=' Expression ';'",
"Statement : IDENT '[' Expression ']' '=' Expression ';'",
"Expression : Expression \"&&\" ExpressionAux",
"Expression : Expression '+' ExpressionAux",
"Expression : Expression '-' ExpressionAux",
"Expression : Expression '*' ExpressionAux",
"Expression : Expression '<' ExpressionAux",
"Expression : Expression '[' Expression ']'",
"Expression : Expression '.' LENGTH",
"Expression : Expression '.' IDENT '(' ')'",
"Expression : Expression '.' IDENT '(' ExpressionRepetition ')'",
"Expression : NUM",
"Expression : TRUE",
"Expression : FALSE",
"Expression : IDENT",
"Expression : THIS",
"Expression : NEW INT '[' Expression ']'",
"Expression : NEW IDENT '(' ')'",
"Expression : '!' ExpressionAux",
"Expression : '(' Expression ')'",
"ExpressionAux : Expression",
"ExpressionRepetition : ExpressionRepetition ',' Expression",
"ExpressionRepetition : Expression",
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
//#line 449 "Parser.java"
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
