%{
import java.io.*;
%}

%token CLASS, PUBLIC, STATIC, VOID, MAIN, EXTENDS, RETURN
%token STRING, BOOLEAN, INT,  IDENT, NUM, TRUE, FALSE
%token AND, IF, ELSE, WHILE, PRINT, LENGTH, NEW, THIS

%left ';'
%nonassoc ','
%right '='
%left AND
%nonassoc '<'
%left '-' '+'
%left '*'
%right '!'
%left '.'
%nonassoc '(' ')'
%nonassoc '[' ']'
%nonassoc '{' '}'

%%

Goal: MainClass ClassDeclarationRepetition
;

MainClass: CLASS IDENT '{'PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENT ')' '{' Statement '}' '}'
;

ClassDeclarationRepetition: ClassDeclarationRepetition ClassDeclaration
|
;

ClassDeclaration: CLASS IDENT Extends '{' VarDeclarationRepetition MethodDeclarationRepetition '}'  
;

Extends: EXTENDS IDENT
|
;

VarDeclarationRepetition: VarDeclarationRepetition VarDeclaration
|
;

MethodDeclarationRepetition: MethodDeclarationRepetition MethodDeclaration
|
;

VarDeclaration: Type IDENT ';'
;

MethodDeclaration: PUBLIC Type IDENT '(' DeclarationTypes ')' VarDeclarationRepetition '{' StatementRepetition RETURN Expression ';' '}'
;

DeclarationTypes: DeclarationTypesRepetition
|
;

DeclarationTypesRepetition: DeclarationTypesRepetition ',' Type IDENT 
| Type IDENT
;

StatementRepetition: StatementRepetition Statement
|
;

Type: INT '[' ']'
| BOOLEAN
| INT
| IDENT
;

Statement: '{' StatementRepetition '}'
| IF '(' Expression ')' Statement ELSE Statement
| WHILE '(' Expression ')' Statement
| PRINT '(' Expression ')' ';'
| IDENT '=' Expression ';'
| IDENT '[' Expression ']' '=' Expression ';'
;

Expression: Expression AND Expression 
| Expression '+' Expression 
| Expression '-' Expression 
| Expression '*' Expression 
| Expression '<' Expression
| Expression '[' Expression ']'
| Expression '.' LENGTH
| Expression '.' IDENT '(' ')'
| Expression '.' IDENT '(' ExpressionRepetition ')'
| NUM
| TRUE
| FALSE
| IDENT
| THIS
| NEW INT '[' Expression ']'
| NEW IDENT '(' ')'
| '!' Expression
| '(' Expression ')'
;

ExpressionRepetition: ExpressionRepetition ',' Expression
| Expression
;

%%

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
