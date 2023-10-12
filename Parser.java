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
public final static short AND=257;
public final static short CLASS=258;
public final static short PUBLIC=259;
public final static short STATIC=260;
public final static short VOID=261;
public final static short MAIN=262;
public final static short STRING=263;
public final static short EXTENDS=264;
public final static short RETURN=265;
public final static short BOOLEAN=266;
public final static short INT=267;
public final static short IF=268;
public final static short ELSE=269;
public final static short WHILE=270;
public final static short PRINT=271;
public final static short IDENT=272;
public final static short NUM=273;
public final static short LENGTH=274;
public final static short TRUE=275;
public final static short FALSE=276;
public final static short NEW=277;
public final static short THIS=278;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    2,    2,    4,    4,    4,    4,    4,
    4,    4,    4,    5,    5,    6,    6,    7,    8,    8,
    8,    8,    8,    8,    8,    8,   10,   10,   11,   11,
    9,    9,    9,    9,    3,    3,    3,    3,    3,    3,
    3,   12,   12,   12,   12,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   13,
   13,
};
final static short yylen[] = {                            2,
    2,    1,   17,    2,    1,    8,    6,    7,    5,    7,
    5,    6,    4,    2,    1,    2,    1,    3,   13,   12,
   12,   11,   12,   11,   11,   10,    4,    2,    2,    1,
    3,    1,    1,    1,    3,    2,    7,    5,    5,    4,
    7,    3,    3,    3,    3,    3,    4,    3,    5,    6,
    1,    1,    1,    1,    1,    5,    4,    2,    3,    3,
    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    5,    0,    0,    4,
    0,    0,    0,    0,    0,    0,   32,    0,   34,   13,
    0,    0,   15,   17,    0,    0,    0,    0,    0,   11,
    0,   14,    9,   16,    0,    0,   12,    0,    0,    0,
   31,    7,   18,    0,   10,    0,    8,    0,    0,    6,
    0,    0,    0,    0,    0,   28,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   30,    0,    0,    0,    0,
    0,   54,   51,   52,   53,    0,   55,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   36,    0,    0,    0,
    0,   29,   27,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   35,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   59,    0,   26,    0,    0,
    0,    0,    0,   48,    0,    0,    0,    0,   40,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,
    0,   47,    0,   38,   39,    0,   22,    0,   25,   24,
    0,    0,    0,    0,   56,   49,    0,    0,    0,    0,
   21,   20,    0,   23,    3,    0,   50,   37,   41,   19,
    0,
};
final static short yydgoto[] = {                          2,
    3,    6,   66,    7,   21,   22,   23,   24,   25,   53,
   68,   80,  168,
};
final static short yysindex[] = {                      -248,
 -253,    0, -196,  -52, -200, -196,    0, -178,  -96,    0,
 -170, -181,  -94, -125,   15,   40,    0,   48,    0,    0,
   99, -113,    0,    0, -132, -121,  122, -122,   70,    0,
  -97,    0,    0,    0,   88,  119,    0,  146,  -88,  127,
    0,    0,    0,  -87,    0,  -68,    0,  -40,   86,    0,
   60,  -84,   32,  104,   22,    0,   40,   77,  -70,  -24,
  167,  181,  185,  -32,  138,    0,   56,    7,  -61,   71,
  192,    0,    0,    0,    0, -222,    0,  -24,  -24,   53,
  -24,  -24,  -24,  -24,  -24,  -32,    0,  172,  -24,   92,
  -24,    0,    0,  -24,   82,  105,  111,  121,  242,  -35,
  -28,  -24,  178,  -24,  -24,  -24,  -24, -214,  -24,   -7,
    1,   23,   63,   73,    0,   83,  -24,   89,  110,  -24,
  132,  -24,   28,  -24,  268,    0,  379,    0,  340,    9,
    9,  -35,  273,    0,  115,   28,   28,  255,    0,  257,
  191,  139,  204,  208,  144,  -24,  150,  209,  171,    0,
  -33,    0,   75,    0,    0,  -24,    0,  210,    0,    0,
  220,  177,  226,  230,    0,    0,  224,   66,   28,  214,
    0,    0,  231,    0,    0,  -24,    0,    0,    0,    0,
  224,
};
final static short yyrindex[] = {                         0,
    0,    0,  361,    0,    0,  368,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   97,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  102,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -39,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -11,    0,    8,   34,
   44,  -19,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   76,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   80,
};
final static short yygindex[] = {                         0,
    0,    0,  327,  372,  249,   59,  264,  286,   54,    0,
  216,  395,    0,
};
final static int YYTABLESIZE=571;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         78,
   51,   58,   58,   58,   58,   58,   79,  166,   78,    1,
  108,   33,  126,  107,  106,   79,  105,  108,    4,   58,
   58,   45,   45,   45,   45,   45,   13,   42,   84,   42,
   20,  104,   42,  136,  107,  106,   47,  105,  108,   45,
   45,  137,  107,  106,   98,  105,  108,   42,   46,   99,
  107,   46,  104,   58,  108,  109,   50,  133,   85,  134,
  104,    5,  109,  138,  107,  106,   46,  105,  108,   28,
    8,    9,   58,   45,   44,   57,   44,   44,   44,   31,
   11,   42,  104,  109,   43,   39,   43,   43,   43,   14,
   15,  109,   44,   44,  107,  106,   46,  105,  108,  109,
   46,   52,   43,   43,  107,  106,  177,  105,  108,  176,
   69,  103,  104,  109,  107,  106,   61,  105,  108,   61,
   60,  139,  104,   60,  107,  106,   44,  105,  108,   65,
  107,  106,  104,  105,  108,   26,   43,   27,   29,   35,
   36,  141,  104,  109,   65,   16,   43,  143,  104,   40,
   65,  107,  106,  109,  105,  108,  107,  106,   44,  105,
  108,   16,   41,  109,   16,  140,   48,   12,  144,  104,
   16,   17,   18,  109,  104,   49,   54,   19,   65,  109,
  107,  106,   55,  105,  108,  107,  106,   56,  105,  108,
   16,  107,  106,   65,  105,  108,   59,  158,  104,   70,
  109,   71,  161,  104,   65,  109,   81,  152,  163,  104,
   93,  124,  107,  106,   65,  105,  108,   58,  107,  106,
   82,  105,  108,   30,   83,   17,   18,   65,  102,  109,
  104,   19,   97,  123,  109,  173,  104,   45,   72,   73,
  109,   74,   75,   76,   77,   42,   37,   72,   73,  102,
   74,   75,   76,   77,   65,  107,  106,  102,  105,  108,
   65,  109,   87,  165,   46,  107,  106,  109,  105,  108,
   45,   91,  179,  104,   61,   38,   62,   63,   86,  102,
   88,  125,   90,  104,   32,   96,   60,   17,   18,   61,
   44,   62,   63,   64,   65,   61,  115,   62,   63,   86,
   43,   32,  128,   67,  109,   17,   18,   34,  150,  102,
  121,   19,  151,  155,  109,  157,   34,  156,   95,  102,
   89,   17,   18,   61,   34,   62,   63,   64,  159,  102,
   32,   34,  160,  164,  171,   94,   17,   18,   61,  102,
   62,   63,   64,  169,  172,  102,  120,   17,   18,   61,
  174,   62,   63,   64,  175,  180,  117,   16,   32,   61,
    2,   62,   63,   86,   17,   18,  102,    1,   33,  122,
   19,  102,   61,   34,   62,   63,   86,   10,    0,    0,
   16,  107,  106,    0,  105,  108,    0,   17,   18,    0,
    0,    0,    0,   19,   92,  102,  146,    0,    0,   61,
  102,   62,   63,   86,   16,   61,  102,   62,   63,   86,
    0,   17,   18,    0,   92,    0,   92,   19,    0,    0,
  107,  106,   92,  105,  108,    0,    0,  102,    0,    0,
  109,    0,    0,  102,    0,    0,    0,    0,  104,   61,
    0,   62,   63,   86,    0,    0,    0,   92,    0,  148,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  153,  154,    0,    0,    0,    0,    0,  109,
  102,    0,  100,  101,    0,  110,  111,  112,  113,  114,
  102,    0,    0,  116,    0,  118,    0,    0,  119,    0,
    0,    0,    0,    0,    0,  178,  127,    0,  129,  130,
  131,  132,    0,  135,    0,    0,    0,    0,    0,    0,
    0,  142,    0,    0,  145,    0,  147,    0,  149,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  162,    0,    0,    0,    0,  167,    0,    0,    0,    0,
  170,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  181,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   41,   41,   42,   43,   44,   45,   40,   41,   33,  258,
   46,  125,   41,   42,   43,   40,   45,   46,  272,   59,
   60,   41,   42,   43,   44,   45,  123,  125,   61,   41,
  125,   60,   44,   41,   42,   43,  125,   45,   46,   59,
   60,   41,   42,   43,  267,   45,   46,   59,   41,  272,
   42,   44,   60,   93,   46,   91,  125,  272,   91,  274,
   60,  258,   91,   41,   42,   43,   59,   45,   46,   16,
  123,  272,   41,   93,   41,   44,   43,   44,   45,   21,
  259,   93,   60,   91,   41,   27,   43,   44,   45,  260,
  272,   91,   59,   60,   42,   43,   38,   45,   46,   91,
   93,   48,   59,   60,   42,   43,   41,   45,   46,   44,
   57,   59,   60,   91,   42,   43,   41,   45,   46,   44,
   41,   59,   60,   44,   42,   43,   93,   45,   46,  123,
   42,   43,   60,   45,   46,  261,   93,  123,   91,  272,
  262,   59,   60,   91,  123,  259,   59,   59,   60,  272,
  123,   42,   43,   91,   45,   46,   42,   43,   40,   45,
   46,  259,   93,   91,  259,   93,   40,  264,   59,   60,
  259,  266,  267,   91,   60,  263,   91,  272,  123,   91,
   42,   43,  123,   45,   46,   42,   43,  272,   45,   46,
  259,   42,   43,  123,   45,   46,   93,   59,   60,  123,
   91,  272,   59,   60,  123,   91,   40,   93,   59,   60,
  272,   91,   42,   43,  123,   45,   46,  257,   42,   43,
   40,   45,   46,  125,   40,  266,  267,  123,  257,   91,
   60,  272,   41,  123,   91,   59,   60,  257,  272,  273,
   91,  275,  276,  277,  278,  257,  125,  272,  273,  257,
  275,  276,  277,  278,  123,   42,   43,  257,   45,   46,
  123,   91,  125,   93,  257,   42,   43,   91,   45,   46,
  125,  265,   59,   60,  268,   27,  270,  271,  272,  257,
   65,   40,   67,   60,   21,   70,  265,  266,  267,  268,
  257,  270,  271,  272,  123,  268,  125,  270,  271,  272,
  257,   38,  125,   55,   91,  266,  267,   22,   41,  257,
   95,  272,   40,   59,   91,  125,   31,   61,   70,  257,
  265,  266,  267,  268,   39,  270,  271,  272,  125,  257,
   67,   46,  125,  125,  125,  265,  266,  267,  268,  257,
  270,  271,  272,  269,  125,  257,  265,  266,  267,  268,
  125,  270,  271,  272,  125,  125,  265,  259,   95,  268,
    0,  270,  271,  272,  266,  267,  257,    0,  272,  265,
  272,  257,  268,  272,  270,  271,  272,    6,   -1,   -1,
  259,   42,   43,   -1,   45,   46,   -1,  266,  267,   -1,
   -1,   -1,   -1,  272,   68,  257,  265,   -1,   -1,  268,
  257,  270,  271,  272,  259,  268,  257,  270,  271,  272,
   -1,  266,  267,   -1,   88,   -1,   90,  272,   -1,   -1,
   42,   43,   96,   45,   46,   -1,   -1,  257,   -1,   -1,
   91,   -1,   -1,  257,   -1,   -1,   -1,   -1,   60,  268,
   -1,  270,  271,  272,   -1,   -1,   -1,  121,   -1,  123,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  136,  137,   -1,   -1,   -1,   -1,   -1,   91,
  257,   -1,   78,   79,   -1,   81,   82,   83,   84,   85,
  257,   -1,   -1,   89,   -1,   91,   -1,   -1,   94,   -1,
   -1,   -1,   -1,   -1,   -1,  169,  102,   -1,  104,  105,
  106,  107,   -1,  109,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  117,   -1,   -1,  120,   -1,  122,   -1,  124,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  146,   -1,   -1,   -1,   -1,  151,   -1,   -1,   -1,   -1,
  156,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  176,
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
null,null,null,null,null,null,null,null,null,"AND","CLASS","PUBLIC","STATIC",
"VOID","MAIN","STRING","EXTENDS","RETURN","BOOLEAN","INT","IF","ELSE","WHILE",
"PRINT","IDENT","NUM","LENGTH","TRUE","FALSE","NEW","THIS",
};
final static String yyrule[] = {
"$accept : Goal",
"Goal : MainClass ClassDeclaretionRepetition",
"Goal : MainClass",
"MainClass : CLASS IDENT '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENT ')' '{' Statement '}' '}'",
"ClassDeclaretionRepetition : ClassDeclaretionRepetition ClassDeclaretion",
"ClassDeclaretionRepetition : ClassDeclaretion",
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
"Expression : Expression AND Expression",
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

//#line 112 "mini_java.y"

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
    System.out.println("BYACC/Java with JFlex Mini Java");

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
//#line 470 "Parser.java"
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
