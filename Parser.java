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
   12,   12,   12,   12,   12,   12,   12,   12,   13,   13,
};
final static short yylen[] = {                            2,
    2,    1,   17,    2,    8,    6,    7,    5,    7,    5,
    6,    4,    2,    1,    2,    1,    3,   13,   12,   12,
   11,   12,   11,   11,   10,    4,    2,    2,    1,    3,
    1,    1,    1,    3,    2,    7,    5,    5,    4,    7,
    3,    3,    3,    3,    3,    4,    3,    5,    6,    1,
    1,    1,    1,    1,    5,    4,    2,    3,    3,    1,
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
    0,   28,   26,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   34,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   58,   25,    0,    0,    0,
    0,    0,    0,   47,    0,    0,    0,    0,   39,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   56,
    0,   46,    0,   37,   38,    0,   21,    0,   24,   23,
    0,    0,    0,    3,   55,   48,    0,    0,    0,    0,
   20,   19,    0,   22,    0,   49,   36,   40,   18,    0,
};
final static short yydgoto[] = {                          2,
    3,    5,   66,    8,   21,   22,   23,   24,   25,   53,
   68,   80,  168,
};
final static short yysindex[] = {                      -243,
 -240,    0,    0,  -84, -214, -185, -197,    0, -161, -120,
 -156, -165,  152, -148,   11, -260,    0,   61,    0,    0,
  154, -123,    0,    0, -131,  114,  164, -111,   84,    0,
 -110,    0,    0,    0,  127,  -71,    0,  166,  -85,  162,
    0,    0,    0,  101,    0,  -65,    0,  -40,  105,    0,
   88,  -59,  -31,  -58,   57,    0, -260,   91,  175,  -24,
  179,  184,  188,  -57,  -34,    0,   67,   28,  -41,   76,
  123,    0,    0,    0,    0, -254,    0,  -24,  -24,   42,
  -24,  -24,  -24,  -24,  -24,  -57,    0,  109,  -24,   98,
  -24,    0,    0,  -24,   87,  117,  122,  172,  231,   19,
  -13,  147,  -24,  -24,  -24,  -24,  -24, -174,  -24,    3,
    9,   16,   66,   72,    0,   77,  -24,   85,   96,  -24,
  134,  -24,  149,  -24,  244,    0,    0,  116,  354,  222,
  222,   19,  246,    0,  104,  122,  122,  229,    0,  232,
  171,  124,  178,  180,  129,  -24,  158,  181,  136,    0,
  -33,    0,   32,    0,    0,  -24,    0,  186,    0,    0,
  187,  163,  191,    0,    0,    0,  224,   -6,  122,  216,
    0,    0,  192,    0,  -24,    0,    0,    0,    0,  224,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,  304,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   38,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   47,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -23,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  398,   12,   23,
   36,  -18,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   49,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   80,
};
final static short yygindex[] = {                         0,
    0,    0,  359,    0,  255,   65,  235,  344,  137,    0,
  213,  381,    0,
};
final static int YYTABLESIZE=556;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         78,
   51,   33,   13,   84,   17,   18,   79,  166,   78,   58,
   19,   98,   57,    1,   42,   79,   99,   57,   57,   57,
   57,   57,   44,   44,   44,   44,   44,  126,  107,  106,
    4,  105,  108,   85,  176,   57,   57,  175,    6,   47,
   44,   44,    7,  136,  107,  106,  103,  105,  108,  137,
  107,  106,   41,  105,  108,   41,  138,  107,  106,   50,
  105,  108,  103,   43,  108,   43,   43,   43,  103,   57,
   41,   41,    9,   10,   44,  103,   42,  109,   42,   42,
   42,   43,   43,  107,  106,   31,  105,  108,   65,   60,
   87,   39,   60,  109,   42,   42,  133,   11,  134,  109,
  102,  103,   46,   14,   41,   15,  109,  107,  106,  109,
  105,  108,   26,  107,  106,   43,  105,  108,  107,  106,
   59,  105,  108,   59,  139,  103,  107,  106,   42,  105,
  108,  103,  109,   27,   16,  141,  103,  107,  106,   35,
  105,  108,   12,  143,  103,  107,  106,   16,  105,  108,
   65,   29,   28,   36,  144,  103,  109,  107,  106,   40,
  105,  108,  109,  103,  140,  107,  106,  109,  105,  108,
  107,  106,   16,  105,  108,  109,   41,  107,  106,   65,
  105,  108,  158,  103,   52,   43,  109,  161,  103,   65,
   44,   49,   16,   69,  109,  103,  152,   54,   65,  107,
  106,   48,  105,  108,  107,  106,  109,  105,  108,   65,
   55,   56,   59,   70,  109,   71,  163,  103,   81,  109,
   65,  173,  103,   82,   17,   18,  109,   83,  165,   93,
   19,   65,   61,  115,   62,   63,   86,   72,   73,   65,
   74,   75,   76,   77,   65,   97,   72,   73,  109,   74,
   75,   76,   77,  109,   57,   32,   65,  107,  106,   44,
  105,  108,  124,  107,  104,  107,  106,  108,  105,  108,
  125,  127,   32,  148,  178,  103,   20,   88,   30,   90,
  104,   38,   96,  103,  150,  151,  104,  155,   37,   41,
   45,   91,  156,  104,   61,  157,   62,   63,   86,  169,
   43,   32,  159,    1,  160,  164,  109,  121,   32,   67,
  171,  172,  109,   42,  109,  174,  179,   33,    0,  104,
   60,   17,   18,   61,   95,   62,   63,   64,    0,   32,
   89,   17,   18,   61,    0,   62,   63,   64,    0,   94,
   17,   18,   61,  104,   62,   63,   64,    0,    0,  104,
  120,   17,   18,   61,  104,   62,   63,   64,    0,    0,
    0,  117,  104,    0,   61,   34,   62,   63,   86,    0,
    0,    0,    0,  104,   34,   61,    0,   62,   63,   86,
  122,  104,   34,   61,    0,   62,   63,   86,   61,   34,
   62,   63,   86,  104,    0,  107,  106,  146,  105,  108,
   61,  104,   62,   63,   86,    0,  104,    0,    0,   16,
    0,   16,    0,  104,    0,    0,   17,   18,   17,   18,
    0,   16,   19,   16,   19,    0,   92,    0,   17,   18,
   17,   18,    0,    0,   19,  104,   19,    0,   45,    0,
  104,   45,    0,    0,  109,    0,   92,    0,   92,    0,
    0,    0,    0,    0,   92,  123,   45,    0,  100,  101,
    0,  110,  111,  112,  113,  114,    0,    0,    0,  116,
    0,  118,    0,    0,  119,    0,    0,    0,    0,   92,
    0,    0,    0,  128,  129,  130,  131,  132,    0,  135,
   45,    0,    0,  104,  153,  154,    0,  142,    0,    0,
  145,  104,  147,    0,  149,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  162,  177,    0,    0,
    0,  167,    0,    0,    0,    0,  170,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  180,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   41,  125,  123,   61,  265,  266,   40,   41,   33,   41,
  271,  266,   44,  257,  125,   40,  271,   41,   42,   43,
   44,   45,   41,   42,   43,   44,   45,   41,   42,   43,
  271,   45,   46,   91,   41,   59,   60,   44,  123,  125,
   59,   60,  257,   41,   42,   43,   60,   45,   46,   41,
   42,   43,   41,   45,   46,   44,   41,   42,   43,  125,
   45,   46,   60,   41,   46,   43,   44,   45,   60,   93,
   59,   60,  258,  271,   93,   60,   41,   91,   43,   44,
   45,   59,   60,   42,   43,   21,   45,   46,  123,   41,
  125,   27,   44,   91,   59,   60,  271,  259,  273,   91,
   59,   60,   38,  260,   93,  271,   91,   42,   43,   91,
   45,   46,  261,   42,   43,   93,   45,   46,   42,   43,
   41,   45,   46,   44,   59,   60,   42,   43,   93,   45,
   46,   60,   91,  123,  258,   59,   60,   42,   43,  271,
   45,   46,  263,   59,   60,   42,   43,  258,   45,   46,
  123,   91,   16,   40,   59,   60,   91,   42,   43,  271,
   45,   46,   91,   60,   93,   42,   43,   91,   45,   46,
   42,   43,  258,   45,   46,   91,   93,   42,   43,  123,
   45,   46,   59,   60,   48,   59,   91,   59,   60,  123,
  262,   91,  258,   57,   91,   60,   93,   93,  123,   42,
   43,   40,   45,   46,   42,   43,   91,   45,   46,  123,
  123,  271,  271,  123,   91,   41,   59,   60,   40,   91,
  123,   59,   60,   40,  265,  266,   91,   40,   93,  271,
  271,  123,  267,  125,  269,  270,  271,  271,  272,  123,
  274,  275,  276,  277,  123,  123,  271,  272,   91,  274,
  275,  276,  277,   91,  278,   21,  123,   42,   43,  278,
   45,   46,   91,   42,  278,   42,   43,   46,   45,   46,
   40,  125,   38,  125,   59,   60,  125,   65,  125,   67,
  278,   27,   70,   60,   41,   40,  278,   59,  125,  278,
  125,  264,   61,  278,  267,  125,  269,  270,  271,  268,
  278,   67,  125,    0,  125,  125,   91,   95,  271,   55,
  125,  125,   91,  278,   91,  125,  125,  271,   -1,  278,
  264,  265,  266,  267,   70,  269,  270,  271,   -1,   95,
  264,  265,  266,  267,   -1,  269,  270,  271,   -1,  264,
  265,  266,  267,  278,  269,  270,  271,   -1,   -1,  278,
  264,  265,  266,  267,  278,  269,  270,  271,   -1,   -1,
   -1,  264,  278,   -1,  267,   22,  269,  270,  271,   -1,
   -1,   -1,   -1,  278,   31,  267,   -1,  269,  270,  271,
  264,  278,   39,  267,   -1,  269,  270,  271,  267,   46,
  269,  270,  271,  278,   -1,   42,   43,  264,   45,   46,
  267,  278,  269,  270,  271,   -1,  278,   -1,   -1,  258,
   -1,  258,   -1,  278,   -1,   -1,  265,  266,  265,  266,
   -1,  258,  271,  258,  271,   -1,   68,   -1,  265,  266,
  265,  266,   -1,   -1,  271,  278,  271,   -1,   41,   -1,
  278,   44,   -1,   -1,   91,   -1,   88,   -1,   90,   -1,
   -1,   -1,   -1,   -1,   96,   97,   59,   -1,   78,   79,
   -1,   81,   82,   83,   84,   85,   -1,   -1,   -1,   89,
   -1,   91,   -1,   -1,   94,   -1,   -1,   -1,   -1,  121,
   -1,   -1,   -1,  103,  104,  105,  106,  107,   -1,  109,
   93,   -1,   -1,  278,  136,  137,   -1,  117,   -1,   -1,
  120,  278,  122,   -1,  124,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  146,  169,   -1,   -1,
   -1,  151,   -1,   -1,   -1,   -1,  156,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  175,
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
"Expression : Expression \"&&\" Expression",
"Expression : Expression '+' Expression",
"Expression : Expression '-' Expression",
"Expression : Expression '*' Expression",
"Expression : Expression '<' Expression",
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
"Expression : '!' Expression",
"Expression : '(' Expression ')'",
"ExpressionRepetition : ExpressionRepetition ',' Expression",
"ExpressionRepetition : Expression",
};

//#line 111 "mini_java.y"

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
//#line 459 "Parser.java"
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
