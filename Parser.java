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
    2,    0,    1,    3,    3,    5,    6,    6,    7,    7,
    8,    8,    9,   10,   12,   12,   15,   15,   13,   13,
   11,   11,   11,   11,    4,    4,    4,    4,    4,    4,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   14,   14,   14,   14,   14,   14,   14,   16,   16,
};
final static short yylen[] = {                            2,
    0,    3,   17,    2,    0,    7,    2,    0,    2,    0,
    2,    0,    3,   13,    1,    0,    4,    2,    2,    0,
    3,    1,    1,    1,    3,    7,    5,    5,    4,    7,
    3,    3,    3,    3,    3,    4,    3,    5,    6,    1,
    1,    1,    1,    1,    5,    4,    2,    3,    3,    1,
};
final static short yydefred[] = {                         1,
    0,    0,    0,    5,    0,    0,    0,    0,    4,    0,
    0,    0,    0,    0,    0,    7,   10,    0,    0,    0,
   22,    0,   24,    0,    9,    0,    0,    0,    0,    6,
   11,    0,    0,   21,    0,   13,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   18,   10,    0,    0,    0,
    0,    0,   20,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   20,   17,    0,   40,   41,   42,    0,
   44,    0,    0,    0,    0,    0,    0,    0,   25,   19,
    3,    0,    0,    0,    0,    0,   47,    0,    0,   29,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   37,    0,    0,    0,   48,    0,    0,    0,    0,   34,
    0,    0,   27,   28,    0,    0,   36,    0,   46,    0,
    0,    0,   38,    0,    0,   45,   30,   26,   14,    0,
   39,    0,
};
final static short yydgoto[] = {                          1,
    4,    2,    6,   80,    9,   14,   19,   24,   25,   31,
   26,   43,   62,   74,   44,  125,
};
final static short yysindex[] = {                         0,
    0, -248, -197,    0,  -42, -172, -169, -174,    0, -160,
 -147, -138, -141,    8, -134,    0,    0,   90, -191, -132,
    0,   42,    0, -123,    0, -130,   50,   47, -191,    0,
    0,   85,   53,    0, -120,    0, -116,  108,  114, -191,
   39, -107,  122,  120, -118,    0,    0, -191,  -51,  125,
  127,  128,    0,   44,  -93,  -96,  -29,  -29,  -29,  -29,
  -29, -122,   51,    0,    0,  -34,    0,    0,    0, -225,
    0,  -29,  -29,   37,  -24,   -8,    4,   13,    0,    0,
    0,  -97, -205,  -29,   89,  139,    0,   18,  -29,    0,
  -29,  -29,  -29,  -29,  121, -118, -118,  124,  -29,  141,
    0,   41,  -29,  143,    0,   78,  116,  144,  144,    0,
  -29,  -88,    0,    0,   45,  -33,    0,   49,    0,   65,
 -118,   63,    0,   69,   54,    0,    0,    0,    0,  -29,
    0,   69,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,  187,    0,    0,    0,    0,
   66,    0,    0,    0,    0,    0,    0,    0, -119,    0,
    0,  -77,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  150,
    0,    0,    0,  151,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -28,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    7,    9,  -21,  -16,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   62,    0,    0,    0,    0,    0,    0,
    0,   72,
};
final static short yygindex[] = {                         0,
    0,    0,    0,   22,    0,    0,  146,    0,    0,    0,
   88,    0,  130,  199,    0,    0,
};
final static int YYTABLESIZE=340;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         72,
   53,   30,   79,   72,   53,   12,   73,  123,    3,   57,
   73,   83,   43,   43,   43,   43,   43,   94,   93,   33,
   92,   33,   33,   33,   32,   53,   32,   32,   32,   64,
   43,   43,   96,   94,   93,   91,   92,   33,   33,   58,
   85,   86,   32,   32,   97,   94,   93,   31,   92,   35,
   31,   91,   35,   98,   94,   93,   84,   92,  105,   94,
   93,  100,   92,   91,   43,   31,   54,   35,   95,    5,
  101,   33,   91,   21,   22,   23,   32,   91,   94,   93,
    7,   92,   94,   93,    8,   92,   94,   93,   10,   92,
   94,   93,   11,   92,  131,   90,   91,  130,   12,   31,
   91,   35,   50,  122,   91,   50,   94,   93,   91,   92,
   94,   93,   49,   92,   13,   49,   35,  112,  113,   94,
   93,   15,   92,  127,   91,   16,   18,   42,   91,   20,
   17,   27,   28,  117,   29,   56,   32,   91,   12,   34,
   33,  126,  128,   36,   49,   37,   38,   40,   49,   50,
   39,   51,   52,   50,   41,   51,   52,   94,   93,   46,
   92,   45,   47,   48,   59,   99,   60,   61,   63,   49,
   65,   21,   22,   23,   50,   81,   51,   52,  104,  103,
  116,  111,  114,  119,  121,   94,    2,  129,    8,   23,
   16,   15,   55,   82,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   66,   67,   68,   69,   66,   67,   68,
   69,    0,   43,   70,   71,    0,   89,   70,   71,   33,
    0,    0,    0,    0,   32,    0,   75,   76,   77,   78,
    0,    0,   89,    0,    0,    0,    0,    0,    0,    0,
   87,   88,    0,    0,   89,    0,    0,   31,    0,   35,
    0,    0,  102,   89,    0,    0,    0,  106,   89,  107,
  108,  109,  110,    0,    0,    0,    0,  115,    0,    0,
    0,  118,    0,    0,    0,    0,    0,   89,    0,  120,
    0,   89,    0,    0,  124,   89,    0,    0,    0,   89,
    0,    0,    0,    0,    0,    0,    0,    0,  132,    0,
    0,    0,    0,    0,    0,   89,    0,    0,    0,   89,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
  123,  125,  125,   33,  123,  125,   40,   41,  257,   61,
   40,   46,   41,   42,   43,   44,   45,   42,   43,   41,
   45,   43,   44,   45,   41,  123,   43,   44,   45,  123,
   59,   60,   41,   42,   43,   60,   45,   59,   60,   91,
  266,  267,   59,   60,   41,   42,   43,   41,   45,   41,
   44,   60,   44,   41,   42,   43,   91,   45,   41,   42,
   43,  267,   45,   60,   93,   59,   45,   59,   93,  267,
  276,   93,   60,  265,  266,  267,   93,   60,   42,   43,
  123,   45,   42,   43,  257,   45,   42,   43,  258,   45,
   42,   43,  267,   45,   41,   59,   60,   44,  259,   93,
   60,   93,   41,   59,   60,   44,   42,   43,   60,   45,
   42,   43,   41,   45,  262,   44,   29,   96,   97,   42,
   43,  260,   45,   59,   60,  267,  261,   40,   60,   40,
  123,  264,   91,   93,  258,   48,  267,   60,  258,   93,
   91,   93,  121,   59,  267,   93,  267,   40,  267,  272,
  267,  274,  275,  272,   41,  274,  275,   42,   43,  267,
   45,  123,   41,   44,   40,  263,   40,   40,  125,  267,
  267,  265,  266,  267,  272,  125,  274,  275,   40,   91,
   40,   61,   59,   41,  273,   42,    0,  125,  123,  267,
   41,   41,   47,   64,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  267,  268,  269,  270,  267,  268,  269,
  270,   -1,  271,  277,  278,   -1,  271,  277,  278,  271,
   -1,   -1,   -1,   -1,  271,   -1,   58,   59,   60,   61,
   -1,   -1,  271,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   72,   73,   -1,   -1,  271,   -1,   -1,  271,   -1,  271,
   -1,   -1,   84,  271,   -1,   -1,   -1,   89,  271,   91,
   92,   93,   94,   -1,   -1,   -1,   -1,   99,   -1,   -1,
   -1,  103,   -1,   -1,   -1,   -1,   -1,  271,   -1,  111,
   -1,  271,   -1,   -1,  116,  271,   -1,   -1,   -1,  271,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  130,   -1,
   -1,   -1,   -1,   -1,   -1,  271,   -1,   -1,   -1,  271,
};
}
final static short YYFINAL=1;
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
"$$1 :",
"Goal : $$1 MainClass ClassDeclarationRepetition",
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
"Expression : IDENT '[' Expression ']'",
"Expression : IDENT '.' LENGTH",
"Expression : IDENT '.' IDENT '(' ')'",
"Expression : IDENT '.' IDENT '(' ExpressionRepetition ')'",
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

//#line 188 "mini_java_redo.y"

  private Yylex lexer;

  private TabSimb ts;

  public static TS_entry Tp_INT =  new TS_entry("int", null, ClasseID.TipoBase);
  public static TS_entry Tp_ARRAYINT = new TS_entry("int[]", null,  ClasseID.TipoBase);
  public static TS_entry Tp_BOOL = new TS_entry("bool", null,  ClasseID.TipoBase);
  public static TS_entry Tp_ERRO = new TS_entry("_erro_", null,  ClasseID.TipoBase);
  
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
    System.err.printf("Erro (linha: %2d \tMensagem: %s)\n", lexer.getLine(), error);
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
    ts.insert(Tp_ARRAYINT);
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
        //System.out.println("Tipo 1: " + A + "\nTipo 2: " + B);
       
         switch ( operador ) {
              case ATRIB:
                    if ( A == B.getTipo() )
                         return B;
                     else
                         yyerror("(sem) tipos incomp. para atribuicao: "+ A.getTipoStr() + " = "+B.getTipoStr());
                    break;

              case '+' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( A == Tp_ARRAYINT && B == Tp_ARRAYINT) 
                         return Tp_ARRAYINT;     
                    else
                        yyerror("(sem) tipos incomp. para soma: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

              case '-' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( A == Tp_ARRAYINT && B == Tp_ARRAYINT) 
                         return Tp_ARRAYINT;     
                    else
                        yyerror("(sem) tipos incomp. para subtracao: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

              case '*' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( A == Tp_ARRAYINT && B == Tp_ARRAYINT) 
                         return Tp_ARRAYINT;     
                    else
                        yyerror("(sem) tipos incomp. para multiplicacao: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

             case '<' :
                     if ((A == Tp_INT && B == Tp_INT) || ( A == Tp_ARRAYINT && B == Tp_ARRAYINT) || (A.getTipo() == B.getTipo()))
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " < "+B.getTipoStr());
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

//#line 479 "Parser.java"
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
case 1:
//#line 24 "mini_java_redo.y"
{currClass = ClasseID.NomeStruct;}
break;
case 3:
//#line 27 "mini_java_redo.y"
{  TS_entry nodo = ts.pesquisa(val_peek(15).sval);
   if (nodo != null) 
     yyerror("main class >" + val_peek(15).sval + "< jah declarada");
   else ts.insert(new TS_entry(val_peek(15).sval, (TS_entry)val_peek(16).obj, currClass)); 
}
break;
case 5:
//#line 35 "mini_java_redo.y"
{currClass = ClasseID.NomeStruct;}
break;
case 6:
//#line 38 "mini_java_redo.y"
{ TS_entry nodo = ts.pesquisa(val_peek(5).sval);
  if (nodo != null) 
    yyerror("classe >" + val_peek(5).sval + "< jah declarada");
  else ts.insert(new TS_entry(val_peek(5).sval, (TS_entry)val_peek(6).obj, currClass)); 
}
break;
case 7:
//#line 45 "mini_java_redo.y"
{  TS_entry nodo = ts.pesquisa(val_peek(0).sval);
    if (nodo == null) 
      yyerror("ident >" + val_peek(0).sval + "< nao declarado");
    else ts.insert(new TS_entry(val_peek(0).sval, (TS_entry)val_peek(1).obj, currClass)); 
}
break;
case 10:
//#line 54 "mini_java_redo.y"
{currClass = ClasseID.VarLocal;}
break;
case 12:
//#line 58 "mini_java_redo.y"
{currClass = ClasseID.NomeParam;}
break;
case 13:
//#line 61 "mini_java_redo.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    if (nodo != null) 
      yyerror("variavel >" + val_peek(1).sval + "< jah declarada");
    else ts.insert(new TS_entry(val_peek(1).sval, (TS_entry)val_peek(2).obj, currClass)); 
    /*System.out.println((TS_entry)$1.obj);*/
}
break;
case 14:
//#line 69 "mini_java_redo.y"
{  TS_entry nodo = ts.pesquisa(val_peek(10).sval);
  if (nodo != null) 
    yyerror("metodo >" + val_peek(10).sval + "< jah declarado");
  else ts.insert(new TS_entry(val_peek(10).sval, (TS_entry)val_peek(11).obj, currClass)); 
}
break;
case 16:
//#line 77 "mini_java_redo.y"
{currClass = ClasseID.NomeParam;}
break;
case 17:
//#line 80 "mini_java_redo.y"
{  TS_entry nodo = ts.pesquisa(val_peek(0).sval);
    if (nodo != null) 
      yyerror("variavel >" + val_peek(0).sval + "< jah declarada");
    else ts.insert(new TS_entry(val_peek(0).sval, (TS_entry)val_peek(1).obj, currClass)); 
}
break;
case 18:
//#line 85 "mini_java_redo.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
  if (nodo != null) 
    yyerror("variavel >" + val_peek(0).sval + "< jah declarada");
  else ts.insert(new TS_entry(val_peek(0).sval, (TS_entry)val_peek(1).obj, currClass)); 
}
break;
case 20:
//#line 93 "mini_java_redo.y"
{currClass = ClasseID.NomeFuncao;}
break;
case 21:
//#line 96 "mini_java_redo.y"
{ yyval.obj = Tp_ARRAYINT; }
break;
case 22:
//#line 97 "mini_java_redo.y"
{ yyval.obj = Tp_BOOL; }
break;
case 23:
//#line 98 "mini_java_redo.y"
{ yyval.obj = Tp_INT; }
break;
case 24:
//#line 99 "mini_java_redo.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
 if (nodo == null ) 
   yyerror("(sem) Nome de tipo <" + val_peek(0).sval + "> nao declarado ");
 else 
   yyval.obj = nodo;
}
break;
case 26:
//#line 108 "mini_java_redo.y"
{ if ( ((TS_entry)val_peek(4).obj) != Tp_BOOL) 
    yyerror("(sem) expressao (if) deve ser logica "+((TS_entry)val_peek(4).obj).getTipo());
}
break;
case 27:
//#line 111 "mini_java_redo.y"
{ if ( ((TS_entry)val_peek(2).obj) != Tp_BOOL) 
    yyerror("(sem) expressao (while) deve ser logica "+((TS_entry)val_peek(2).obj).getTipo());
}
break;
case 29:
//#line 115 "mini_java_redo.y"
{ TS_entry nodo = ts.pesquisa(val_peek(3).sval);
if (nodo == null) {
  yyerror("(sem) var <" + val_peek(3).sval + "> nao declarada");     
}           
else
  yyval.obj = validaTipo(ATRIB, nodo.getTipo(), (TS_entry)val_peek(1).obj); }
break;
case 30:
//#line 121 "mini_java_redo.y"
{ if ((TS_entry)val_peek(6).obj != Tp_ARRAYINT) 
    yyerror("expressao deve ser um array "+((TS_entry)val_peek(6).obj).getTipo());
  if ( ((TS_entry)val_peek(4).obj) != Tp_INT) 
    yyerror("a posicao do array deve ser um inteiro "+((TS_entry)val_peek(4).obj).getTipo());
  yyval.obj = validaTipo(ATRIB, Tp_INT, (TS_entry)val_peek(1).obj);
}
break;
case 31:
//#line 129 "mini_java_redo.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 32:
//#line 130 "mini_java_redo.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 33:
//#line 131 "mini_java_redo.y"
{ yyval.obj = validaTipo('-', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 34:
//#line 132 "mini_java_redo.y"
{ yyval.obj = validaTipo('*', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 35:
//#line 133 "mini_java_redo.y"
{ yyval.obj = validaTipo('<', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 36:
//#line 134 "mini_java_redo.y"
{ if ((TS_entry)val_peek(3).obj != Tp_ARRAYINT) 
    yyerror("expressao deve ser um array "+((TS_entry)val_peek(3).obj).getTipo());
  if ( ((TS_entry)val_peek(1).obj) != Tp_INT) 
    yyerror("a posição do array deve ser um inteiro "+((TS_entry)val_peek(1).obj).getTipo());
}
break;
case 37:
//#line 139 "mini_java_redo.y"
{ if ((TS_entry)val_peek(2).obj != Tp_ARRAYINT) 
    yyerror("expressao deve ser um array "+((TS_entry)val_peek(2).obj).getTipo());
  yyval.obj = Tp_INT; 
}
break;
case 38:
//#line 143 "mini_java_redo.y"
{ TS_entry classe = ts.pesquisa(val_peek(4).sval);
  if (classe == null) 
    yyerror("classe >" + val_peek(4).sval + "< nao declarada");
  TS_entry func = ts.pesquisa(val_peek(2).sval);
  if (func == null)
    yyerror("funcao >" + val_peek(2).sval + "< nao declarada");
}
break;
case 39:
//#line 150 "mini_java_redo.y"
{ TS_entry classe = ts.pesquisa(val_peek(5).sval);
  if (classe == null) 
    yyerror("classe >" + val_peek(5).sval + "< nao declarada");
  TS_entry func = ts.pesquisa(val_peek(3).sval);
  if (func == null)
    yyerror("funcao >" + val_peek(3).sval + "< nao declarada");
}
break;
case 40:
//#line 157 "mini_java_redo.y"
{ yyval.obj = Tp_INT; }
break;
case 41:
//#line 158 "mini_java_redo.y"
{ yyval.obj = Tp_BOOL; }
break;
case 42:
//#line 159 "mini_java_redo.y"
{ yyval.obj = Tp_BOOL; }
break;
case 43:
//#line 160 "mini_java_redo.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
  if (nodo == null ) 
    yyerror("(sem) Nome de tipo <" + val_peek(0).sval + "> nao declarado ");
  else 
    /*System.out.println("var: " + $1.sval + "\n Node: "+ nodo);*/
    yyval.obj = nodo;
}
break;
case 45:
//#line 168 "mini_java_redo.y"
{ if ((TS_entry)val_peek(1).obj != Tp_INT) 
    yyerror("posicao do array deve ser um inteiro "+((TS_entry)val_peek(4).obj).getTipo());
}
break;
case 46:
//#line 171 "mini_java_redo.y"
{  TS_entry nodo = ts.pesquisa(val_peek(2).sval);
  if (nodo != null) 
    yyerror("variavel >" + val_peek(2).sval + "< jah declarada");
  else ts.insert(new TS_entry(val_peek(2).sval, (TS_entry)val_peek(3).obj, currClass)); 
}
break;
case 47:
//#line 176 "mini_java_redo.y"
{ if ((TS_entry)val_peek(0).obj != Tp_BOOL)
    yyerror("expressao deve ser booleana "+((TS_entry)val_peek(0).obj).getTipo());
yyval.obj = Tp_BOOL; 
}
break;
case 48:
//#line 180 "mini_java_redo.y"
{ yyval.obj = val_peek(1).obj; }
break;
//#line 862 "Parser.java"
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
