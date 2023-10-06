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
    0,    1,    2,    2,    5,    6,    6,    7,    7,    8,
    8,    9,   10,   12,   12,   15,   15,   13,   13,   11,
   11,   11,   11,    4,    4,    4,    4,    4,    4,   14,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   14,   14,   17,   17,   16,   16,   16,   16,   16,
    3,
};
final static short yylen[] = {                            2,
    2,   17,    2,    0,    7,    2,    0,    2,    0,    2,
    0,    3,   13,    3,    0,    4,    0,    2,    0,    3,
    1,    1,    1,    3,    7,    5,    5,    4,    7,    3,
    4,    3,    5,    6,    1,    1,    1,    1,    1,    5,
    4,    2,    3,    3,    1,    1,    1,    1,    1,    1,
    1,
};
final static short yydefred[] = {                         0,
    0,    0,    4,   51,    0,    0,    0,    0,    3,    0,
    0,    0,    0,    0,    0,    6,    9,    0,    0,    0,
   21,    0,   23,    0,    8,    0,    0,    0,    0,    5,
   10,    0,    0,   20,    0,   12,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   19,
    0,    0,    0,   14,    9,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   35,   36,   37,    0,   39,    0,
    0,   38,    0,    0,    0,   24,   18,    0,    0,    2,
    0,    0,    0,    0,    0,    0,   47,   46,   49,   48,
   50,    0,    0,    0,    0,    0,    0,   28,    0,   16,
    0,    0,    0,   43,    0,    0,   32,    0,    0,   26,
   27,    0,    0,    0,   41,   31,    0,    0,    0,    0,
   40,   25,   33,    0,    0,   29,   13,   34,    0,    0,
};
final static short yydgoto[] = {                          2,
    3,    6,   72,   77,    9,   14,   19,   24,   25,   31,
   26,   43,   59,   73,   54,   95,  125,
};
final static short yysindex[] = {                      -230,
 -233,    0,    0,    0,  -61, -190, -187, -233,    0, -186,
 -185, -183, -233,  -44, -178,    0,    0,   53, -260, -155,
    0,   26,    0, -123,    0, -233,   29,   31, -260,    0,
    0,   62,   32,    0, -233,    0, -233,   82,   85, -260,
    6, -233,   90, -102,   88,   11,   99,  101,  103,    0,
  -15,   19, -260,    0,    0,  -24,  -24,  -24, -122,  -24,
  -24,   21, -233, -260,    0,    0,    0, -254,    0,  -24,
  -24,    0,  -23,  -17,  -11,    0,    0,    5,   10,    0,
   88, -113,   59,  112,   -1,   73,    0,    0,    0,    0,
    0,  -24, -102, -258,  -24, -102,   94,    0,  100,    0,
  -24,  -24,  114,    0,   15, -108,    0,  122,   73,    0,
    0,  -24,   39,   49,    0,    0, -102,  -33,   68,   38,
    0,    0,    0,   73,   28,    0,    0,    0,  -24,   73,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,  166,    0,    0,    0,    0,
  -61,    0,    0,    0,    0,    0,    0,    0, -121,    0,
    0, -101,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  130,
    0,    0,    0,    0,  131,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -90,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  131,    0,    0,    0,    0,   -5,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   45,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   56,    0,    0,    0,    0,    0,   61,
};
final static short yygindex[] = {                         0,
    0,    0,  261,  -30,    0,    0,  118,    0,    0,    0,
   83,    0,  111,  128,   95,    0,    0,
};
final static int YYTABLESIZE=378;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         71,
   50,   30,   76,   11,   21,   22,   70,  123,   71,   50,
    4,   83,    4,   52,  107,   70,    4,   93,   91,   90,
   50,   89,   94,   96,   91,   90,    1,   89,   94,   97,
   91,   90,   19,   89,   94,   42,   87,    4,   42,  104,
   91,   90,   87,   89,   94,   60,   91,   90,   87,   89,
   94,   91,   90,   42,   89,   94,   91,   90,   87,   89,
   94,    7,  106,   98,   87,  110,    8,   92,  128,   87,
   10,  129,   12,   92,   87,   61,   15,   13,   17,   92,
   91,   90,   18,   89,   94,   30,  122,   42,   30,   92,
   91,   90,   20,   89,   94,   92,   45,  120,   87,   45,
   92,   44,   99,   30,   44,   92,   27,  116,   87,   91,
   90,   35,   89,   94,   91,   90,   28,   89,   94,   33,
   36,   40,   42,   34,   37,   41,  126,   87,   44,   92,
   46,   53,   87,   55,   29,   63,   11,   30,   56,   92,
   57,  121,   58,   62,   47,   80,   48,   49,    4,  102,
  101,  103,  111,   47,  115,   48,   49,    4,   92,  117,
  112,  118,  127,   92,   47,    1,   48,   49,    4,   22,
   15,   17,   64,   19,   82,  100,   19,    0,   19,   19,
    0,    0,    0,    0,   74,   75,    0,   78,   79,    0,
    0,    0,    0,    0,    0,    0,    0,   85,   86,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  105,
    0,    0,  109,    0,    0,    0,    0,    0,  113,  114,
    0,    0,    0,    0,    0,    0,    0,    4,   65,  119,
   66,   67,   68,   69,    0,  124,    4,   65,    0,   66,
   67,   68,   69,    0,   88,    0,  130,    0,    0,    0,
   88,    5,    0,    0,    0,    0,   88,    0,   11,    0,
    0,    0,    0,   16,    0,    0,   88,    0,    0,   23,
    0,    0,   88,    0,    0,    0,   32,   88,    0,   23,
    0,    0,   88,    0,    0,   38,    0,   39,    0,    0,
   23,    0,   45,    0,   51,    0,    0,    0,    0,    0,
    0,    0,    0,   23,    0,    0,   88,    0,    0,   51,
    0,    0,    0,   81,   23,    0,   88,    0,   84,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   51,    0,    0,   88,    0,    0,    0,    0,
   88,    0,    0,   51,  108,    0,   51,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   51,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
  123,  125,  125,  125,  265,  266,   40,   41,   33,  123,
  271,  266,  271,   44,  273,   40,  271,   41,   42,   43,
  123,   45,   46,   41,   42,   43,  257,   45,   46,   41,
   42,   43,  123,   45,   46,   41,   60,  271,   44,   41,
   42,   43,   60,   45,   46,   61,   42,   43,   60,   45,
   46,   42,   43,   59,   45,   46,   42,   43,   60,   45,
   46,  123,   93,   59,   60,   96,  257,   91,   41,   60,
  258,   44,  259,   91,   60,   91,  260,  263,  123,   91,
   42,   43,  261,   45,   46,   41,  117,   93,   44,   91,
   42,   43,   40,   45,   46,   91,   41,   59,   60,   44,
   91,   41,   93,   59,   44,   91,  262,   93,   60,   42,
   43,   29,   45,   46,   42,   43,   91,   45,   46,   91,
   59,   40,   40,   93,   93,   41,   59,   60,  123,   91,
   41,   44,   60,  123,  258,   53,  258,   93,   40,   91,
   40,   93,   40,  125,  267,  125,  269,  270,  271,   91,
  264,   40,   59,  267,   41,  269,  270,  271,   91,  268,
   61,   40,  125,   91,  267,    0,  269,  270,  271,  271,
   41,   41,   55,  264,   64,   81,  267,   -1,  269,  270,
   -1,   -1,   -1,   -1,   57,   58,   -1,   60,   61,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   70,   71,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   92,
   -1,   -1,   95,   -1,   -1,   -1,   -1,   -1,  101,  102,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  271,  272,  112,
  274,  275,  276,  277,   -1,  118,  271,  272,   -1,  274,
  275,  276,  277,   -1,  278,   -1,  129,   -1,   -1,   -1,
  278,    1,   -1,   -1,   -1,   -1,  278,   -1,    8,   -1,
   -1,   -1,   -1,   13,   -1,   -1,  278,   -1,   -1,   19,
   -1,   -1,  278,   -1,   -1,   -1,   26,  278,   -1,   29,
   -1,   -1,  278,   -1,   -1,   35,   -1,   37,   -1,   -1,
   40,   -1,   42,   -1,   44,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   53,   -1,   -1,  278,   -1,   -1,   59,
   -1,   -1,   -1,   63,   64,   -1,  278,   -1,   68,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   82,   -1,   -1,  278,   -1,   -1,   -1,   -1,
  278,   -1,   -1,   93,   94,   -1,   96,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  117,
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
"MainClass : CLASS Identifier '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' Identifier ')' '{' Statement '}' '}'",
"ClassDeclaretionRepetition : ClassDeclaretionRepetition ClassDeclaretion",
"ClassDeclaretionRepetition :",
"ClassDeclaretion : CLASS Identifier ExtendID '{' VarDeclaretionRepetition MethodDeclarationRepetition '}'",
"ExtendID : EXTENDS Identifier",
"ExtendID :",
"VarDeclaretionRepetition : VarDeclaretionRepetition VarDeclaretion",
"VarDeclaretionRepetition :",
"MethodDeclarationRepetition : MethodDeclarationRepetition MethodDeclaration",
"MethodDeclarationRepetition :",
"VarDeclaretion : Type Identifier ';'",
"MethodDeclaration : PUBLIC Type Identifier '(' DeclarationTypes ')' '{' VarDeclaretionRepetition StatementRepetition RETURN Expression ';' '}'",
"DeclarationTypes : Type Identifier DeclarationTypesRepetition",
"DeclarationTypes :",
"DeclarationTypesRepetition : ',' Type Identifier DeclarationTypesRepetition",
"DeclarationTypesRepetition :",
"StatementRepetition : StatementRepetition Statement",
"StatementRepetition :",
"Type : INT '[' ']'",
"Type : BOOLEAN",
"Type : INT",
"Type : Identifier",
"Statement : '{' StatementRepetition '}'",
"Statement : IF '(' Expression ')' Statement ELSE Statement",
"Statement : WHILE '(' Expression ')' Statement",
"Statement : PRINT '(' Expression ')' ';'",
"Statement : Identifier '=' Expression ';'",
"Statement : Identifier '[' Expression ']' '=' Expression ';'",
"Expression : Expression Operator Expression",
"Expression : Expression '[' Expression ']'",
"Expression : Expression '.' LENGTH",
"Expression : Expression '.' Identifier '(' ')'",
"Expression : Expression '.' Identifier '(' ExpressionRepetition ')'",
"Expression : INTEGERLITERAL",
"Expression : TRUE",
"Expression : FALSE",
"Expression : Identifier",
"Expression : THIS",
"Expression : NEW INT '[' Expression ']'",
"Expression : NEW Identifier '(' ')'",
"Expression : '!' Expression",
"Expression : '(' Expression ')'",
"ExpressionRepetition : ExpressionRepetition ',' Expression",
"ExpressionRepetition : Expression",
"Operator : \"&&\"",
"Operator : '<'",
"Operator : '+'",
"Operator : '-'",
"Operator : '*'",
"Identifier : IDENTIFIER",
};

//#line 103 "mini_java.y"

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
//#line 399 "Parser.java"
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
