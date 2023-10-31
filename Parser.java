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






//#line 2 "mini_java_redo.y"
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
public final static short EXTENDS=262;
public final static short RETURN=263;
public final static short STRING=264;
public final static short BOOLEAN=265;
public final static short INT=266;
public final static short IDENT=267;
public final static short NUM=268;
public final static short TRUE=269;
public final static short FALSE=270;
public final static short AND=271;
public final static short IF=272;
public final static short ELSE=273;
public final static short WHILE=274;
public final static short PRINT=275;
public final static short LENGTH=276;
public final static short NEW=277;
public final static short THIS=278;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,    2,    4,    5,    5,    6,    6,    7,
    7,    8,    9,   11,   11,   14,   14,   12,   12,   10,
   10,   10,   10,    3,    3,    3,    3,    3,    3,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   15,   15,
};
final static short yylen[] = {                            2,
    2,   17,    2,    0,    7,    2,    0,    2,    0,    2,
    0,    3,   13,    1,    0,    4,    2,    2,    0,    3,
    1,    1,    1,    3,    7,    5,    5,    4,    7,    3,
    3,    3,    3,    3,    4,    3,    5,    6,    1,    1,
    1,    1,    1,    5,    4,    2,    3,    3,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    4,    0,    0,    0,    0,    3,    0,    0,
    0,    0,    0,    0,    6,    9,    0,    0,    0,   21,
    0,   23,    0,    8,    0,    0,    0,    0,    5,   10,
    0,    0,   20,    0,   12,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   17,    9,    0,    0,    0,    0,
    0,   19,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   19,   16,   42,   39,   40,   41,    0,   43,
    0,    0,    0,    0,    0,    0,    0,   24,   18,    2,
    0,    0,    0,    0,    0,    0,   28,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   47,    0,    0,    0,    0,    0,    0,   36,    0,    0,
    0,   26,   27,    0,    0,   45,    0,   35,    0,    0,
    0,   44,   37,    0,    0,   29,   25,   13,    0,   38,
    0,
};
final static short yydgoto[] = {                          2,
    3,    5,   79,    8,   13,   18,   23,   24,   30,   25,
   42,   61,   73,   43,  125,
};
final static short yysindex[] = {                      -255,
 -239,    0,    0,  -60, -190, -189, -199,    0, -186, -185,
 -181, -184,  -38, -167,    0,    0,   58, -224, -165,    0,
    9,    0, -113,    0, -163,   14,   13, -224,    0,    0,
   52,   26,    0, -147,    0, -138,   93,   99, -224,   18,
 -114,  113,  111,  -86,    0,    0, -224,  -25,  122,  124,
  140,    0,   62, -117,  -77,  -29,  -29,  -29,  -29,  -29,
  -90,   66,    0,    0,    0,    0,    0,    0, -248,    0,
  -29,  -29,   67,   72,  -16,    4,   10,    0,    0,    0,
  -63,  104,  157,  -43,   16,  -29,    0,  -29,  -29,  -29,
  -29, -266,  -29,  135,  -86,  -86,  144,  -29,  -29,  165,
    0,  156,  172,  -37,  -37,  -43,  167,    0,   79,  -29,
  -53,    0,    0,   92,  101,    0,  -33,    0,  114,  -86,
   85,    0,    0,  133,   87,    0,    0,    0,  -29,    0,
  133,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,  213,    0,    0,    0,    0,   98,
    0,    0,    0,    0,    0,    0,    0,  -91,    0,    0,
  -45,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  182,    0,
    0,    0,  184,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -28,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   30,   49,   37,   43,  -21,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  125,    0,    0,    0,    0,    0,    0,
  127,
};
final static short yygindex[] = {                         0,
    0,    0,   -4,    0,    0,  183,    0,    0,    0,  180,
    0,  163,  200,    0,    0,
};
final static int YYTABLESIZE=404;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         71,
  107,    1,   92,   71,   91,   63,   72,  123,   92,  108,
   72,   29,   46,   46,   46,   46,   46,   82,   83,   33,
   33,   33,   33,   33,   95,   91,   90,    4,   89,   92,
   46,   46,   52,   11,   78,   56,   52,   33,   33,   53,
   20,   21,   22,   88,   96,   91,   90,   93,   89,   92,
   97,   91,   90,   93,   89,   92,  101,   91,   90,   52,
   89,   92,    6,   88,   46,   57,    7,   10,    9,   88,
   30,   33,   11,   30,   93,   88,   12,   32,   14,   32,
   32,   32,   15,   31,   16,   31,   31,   31,   30,   34,
  111,  112,   34,   17,   93,   32,   32,   19,   26,   27,
   93,   31,   31,   31,   32,   33,   93,   34,   91,   90,
   35,   89,   92,   91,   90,  127,   89,   92,   36,   37,
   91,   90,   30,   89,   92,   87,   88,  130,   38,   32,
  129,   88,   39,   91,   90,   31,   89,   92,   88,   40,
   44,   34,   91,   90,   28,   89,   92,   20,   21,   22,
  121,   88,   45,   46,   47,   91,   90,   93,   89,   92,
   88,   58,   93,   59,   94,   49,   11,   48,   49,   93,
   48,  118,  126,   88,   91,   90,   48,   89,   92,   60,
   48,   49,   93,   50,   51,   49,   62,   50,   51,   64,
   80,   93,   88,  122,   99,  110,  100,   91,   90,   98,
   89,   92,  113,   48,   93,  116,  117,   34,   49,  128,
   50,   51,    1,   91,   90,   88,   89,   92,   41,  120,
    7,   22,   15,   93,   14,   81,   55,    0,   54,    0,
    0,    0,    0,   65,   66,   67,   68,   65,   66,   67,
   68,    0,   46,   69,   70,    0,   93,   69,   70,   33,
    0,    0,    0,    0,   86,    0,   74,   75,   76,   77,
    0,    0,   93,    0,    0,    0,    0,    0,    0,    0,
   84,   85,    0,    0,   86,    0,    0,    0,    0,    0,
   86,    0,    0,    0,    0,  102,   86,  103,  104,  105,
  106,    0,  109,    0,    0,    0,    0,  114,  115,    0,
   30,    0,    0,    0,    0,    0,    0,   32,    0,  119,
    0,    0,    0,   31,    0,    0,  124,    0,    0,   34,
    0,    0,    0,    0,    0,    0,    0,    0,  131,    0,
    0,    0,    0,    0,    0,    0,    0,   86,    0,    0,
    0,    0,   86,    0,    0,    0,    0,    0,    0,   86,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   86,    0,    0,    0,    0,    0,    0,    0,
    0,   86,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   86,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   86,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
  267,  257,   46,   33,   42,  123,   40,   41,   46,  276,
   40,  125,   41,   42,   43,   44,   45,  266,  267,   41,
   42,   43,   44,   45,   41,   42,   43,  267,   45,   46,
   59,   60,  123,  125,  125,   61,  123,   59,   60,   44,
  265,  266,  267,   60,   41,   42,   43,   91,   45,   46,
   41,   42,   43,   91,   45,   46,   41,   42,   43,  123,
   45,   46,  123,   60,   93,   91,  257,  267,  258,   60,
   41,   93,  259,   44,   91,   60,  262,   41,  260,   43,
   44,   45,  267,   41,  123,   43,   44,   45,   59,   41,
   95,   96,   44,  261,   91,   59,   60,   40,  264,   91,
   91,   59,   60,  267,   91,   93,   91,   59,   42,   43,
   59,   45,   46,   42,   43,  120,   45,   46,   93,  267,
   42,   43,   93,   45,   46,   59,   60,   41,  267,   93,
   44,   60,   40,   42,   43,   93,   45,   46,   60,   41,
  123,   93,   42,   43,  258,   45,   46,  265,  266,  267,
   59,   60,  267,   41,   44,   42,   43,   91,   45,   46,
   60,   40,   91,   40,   93,   41,  258,   41,   44,   91,
   44,   93,   59,   60,   42,   43,  267,   45,   46,   40,
  267,  272,   91,  274,  275,  272,  125,  274,  275,  267,
  125,   91,   60,   93,   91,   61,   40,   42,   43,  263,
   45,   46,   59,  267,   91,   41,   40,   28,  272,  125,
  274,  275,    0,   42,   43,   60,   45,   46,   39,  273,
  123,  267,   41,   91,   41,   63,   47,   -1,   46,   -1,
   -1,   -1,   -1,  267,  268,  269,  270,  267,  268,  269,
  270,   -1,  271,  277,  278,   -1,   91,  277,  278,  271,
   -1,   -1,   -1,   -1,  271,   -1,   57,   58,   59,   60,
   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   71,   72,   -1,   -1,  271,   -1,   -1,   -1,   -1,   -1,
  271,   -1,   -1,   -1,   -1,   86,  271,   88,   89,   90,
   91,   -1,   93,   -1,   -1,   -1,   -1,   98,   99,   -1,
  271,   -1,   -1,   -1,   -1,   -1,   -1,  271,   -1,  110,
   -1,   -1,   -1,  271,   -1,   -1,  117,   -1,   -1,  271,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  129,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  271,   -1,   -1,
   -1,   -1,  271,   -1,   -1,   -1,   -1,   -1,   -1,  271,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  271,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  271,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  271,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  271,
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
"MAIN","EXTENDS","RETURN","STRING","BOOLEAN","INT","IDENT","NUM","TRUE","FALSE",
"AND","IF","ELSE","WHILE","PRINT","LENGTH","NEW","THIS",
};
final static String yyrule[] = {
"$accept : Goal",
"Goal : MainClass ClassDeclarationRepetition",
"MainClass : CLASS IDENT '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENT ')' '{' Statement '}' '}'",
"ClassDeclarationRepetition : ClassDeclarationRepetition ClassDeclaration",
"ClassDeclarationRepetition :",
"ClassDeclaration : CLASS IDENT Extends '{' VarDeclarationRepetition MethodDeclarationRepetition '}'",
"Extends : EXTENDS IDENT",
"Extends :",
"VarDeclarationRepetition : VarDeclarationRepetition VarDeclaration",
"VarDeclarationRepetition :",
"MethodDeclarationRepetition : MethodDeclarationRepetition MethodDeclaration",
"MethodDeclarationRepetition :",
"VarDeclaration : Type IDENT ';'",
"MethodDeclaration : PUBLIC Type IDENT '(' DeclarationTypes ')' VarDeclarationRepetition '{' StatementRepetition RETURN Expression ';' '}'",
"DeclarationTypes : DeclarationTypesRepetition",
"DeclarationTypes :",
"DeclarationTypesRepetition : DeclarationTypesRepetition ',' Type IDENT",
"DeclarationTypesRepetition : Type IDENT",
"StatementRepetition : StatementRepetition Statement",
"StatementRepetition :",
"Type : INT '[' ']'",
"Type : BOOLEAN",
"Type : INT",
"Type : IDENT",
"Statement : '{' StatementRepetition '}'",
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

//#line 106 "mini_java_redo.y"

  private Yylex lexer;

  private TabSimb ts;

  public static TS_entry Tp_INT =  new TS_entry("int", null, ClasseID.TipoBase);
  public static TS_entry Tp_DOUBLE = new TS_entry("double", null,  ClasseID.TipoBase);
  public static TS_entry Tp_BOOL = new TS_entry("bool", null,  ClasseID.TipoBase);
  public static TS_entry Tp_ERRO = new TS_entry("_erro_", null,  ClasseID.TipoBase);

  public static final int ARRAY = 1500;
  public static final int ATRIB = 1600;

  private String currEscopo;
  private ClasseID currClass;

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
    //System.err.println("Erro (linha: "+ lexer.getLine() + ")\tMensagem: "+error);
    //System.err.printf("Erro (linha: %2d \tMensagem: %s)\n", lexer.getLine(), error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);

    ts = new TabSimb();

    //
    // não me parece que necessitem estar na TS
    // já que criei todas como public static...
    //
    ts.insert(Tp_ERRO);
    ts.insert(Tp_INT);
    ts.insert(Tp_DOUBLE);
    ts.insert(Tp_BOOL);
    

  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {
    System.out.println("\n\nVerificador semantico simples\n");
    

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Programa de entrada:\n");
        yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();

      yyparser.listarTS();

      System.out.print("\n\nFeito!\n");
    
  }


   TS_entry validaTipo(int operador, TS_entry A, TS_entry B) {
       
         switch ( operador ) {
              case ATRIB:
                    if ( (A == Tp_INT && B == Tp_INT)                        ||
                         ((A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE))) ||
                         (A == B) )
                         return A;
                     else
                         yyerror("(sem) tipos incomp. para atribuicao: "+ A.getTipoStr() + " = "+B.getTipoStr());
                    break;

              case '+' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
                                            (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) ) 
                         return Tp_DOUBLE;     
                    else
                        yyerror("(sem) tipos incomp. para soma: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

             case '>' :
                     if ((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE))
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
                      break;

             case AND:
                     if (A == Tp_BOOL && B == Tp_BOOL)
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " && "+B.getTipoStr());
                 break;
            }

            return Tp_ERRO;
           
     }

//#line 477 "Parser.java"
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
