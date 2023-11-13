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

MainClass: CLASS IDENT '{'PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENT ')' '{' Statement '}' '}' {  TS_entry nodo = ts.pesquisa($2);
                                                                                                        if (nodo != null) 
                                                                                                          yyerror("main class >" + $2 + "< jah declarada");
                                                                                                        else ts.insert(new TS_entry($2, (TS_entry)$1, currClass)); 
                                                                                                     }
;

ClassDeclarationRepetition: ClassDeclarationRepetition ClassDeclaration
|
;

ClassDeclaration: CLASS IDENT Extends '{' VarDeclarationRepetition MethodDeclarationRepetition '}'  { TS_entry nodo = ts.pesquisa($2);
                                                                                                      if (nodo != null) 
                                                                                                        yyerror("classe >" + $2 + "< jah declarada");
                                                                                                      else ts.insert(new TS_entry($2, (TS_entry)$1, currClass)); 
                                                                                                    }
;

Extends: EXTENDS IDENT  {  TS_entry nodo = ts.pesquisa($2);
                            if (nodo == null) 
                              yyerror("ident >" + $2 + "< nao declarado");
                            else ts.insert(new TS_entry($2, (TS_entry)$1, currClass)); 
                        }
|
;

VarDeclarationRepetition: VarDeclarationRepetition VarDeclaration
|
;

MethodDeclarationRepetition: MethodDeclarationRepetition MethodDeclaration
|
;

VarDeclaration: Type IDENT ';'{  TS_entry nodo = ts.pesquisa($2);
                                  if (nodo != null) 
                                    yyerror("variavel >" + $2 + "< jah declarada");
                                  else ts.insert(new TS_entry($2, (TS_entry)$1, currClass)); 
                              }
;

MethodDeclaration: PUBLIC Type IDENT '(' DeclarationTypes ')' VarDeclarationRepetition '{' StatementRepetition RETURN Expression ';' '}' {  TS_entry nodo = ts.pesquisa($3);
                                                                                                                                            if (nodo != null) 
                                                                                                                                              yyerror("metodo >" + $3 + "< jah declarado");
                                                                                                                                            else ts.insert(new TS_entry($3, (TS_entry)$2, currClass)); 
                                                                                                                                          }
;

DeclarationTypes: DeclarationTypesRepetition
|
;

DeclarationTypesRepetition: DeclarationTypesRepetition ',' Type IDENT {  TS_entry nodo = ts.pesquisa($4);
                                                                          if (nodo != null) 
                                                                            yyerror("variavel >" + $4 + "< jah declarada");
                                                                          else ts.insert(new TS_entry($4, (TS_entry)$3, currClass)); 
                                                                      }
| Type IDENT                                        { TS_entry nodo = ts.pesquisa($2);
                                                      if (nodo != null) 
                                                        yyerror("variavel >" + $2 + "< jah declarada");
                                                      else ts.insert(new TS_entry($2, (TS_entry)$1, currClass)); 
                                                    }
;

StatementRepetition: StatementRepetition Statement
|
;

Type: INT '[' ']'                                   { $$ = Tp_ARRAYINT; }
| BOOLEAN                                           { $$ = Tp_BOOLEAN; }
| INT                                               { $$ = Tp_INT; }
| IDENT                                             { TS_entry nodo = ts.pesquisa($1);
                                                        if (nodo == null ) 
                                                          yyerror("(sem) Nome de tipo <" + $1 + "> nao declarado ");
                                                        else 
                                                          $$ = nodo;
                                                    } 
;

Statement: '{' StatementRepetition '}'
| IF '(' Expression ')' Statement ELSE Statement    { if ( ((TS_entry)$3) != Tp_BOOL) 
                                                        yyerror("(sem) expressao (if) deve ser logica "+((TS_entry)$3).getTipo());
                                                    } 
| WHILE '(' Expression ')' Statement                { if ( ((TS_entry)$3) != Tp_BOOL) 
                                                        yyerror("(sem) expressao (if) deve ser logica "+((TS_entry)$3).getTipo());
                                                    }
| PRINT '(' Expression ')' ';' 
| IDENT '=' Expression ';'                          { $$ = validaTipo(ATRIB, (TS_entry)$1, (TS_entry)$3);  } 
| IDENT '[' Expression ']' '=' Expression ';'       { if ((TS_entry)$1 != Tp_ARRAYINT) 
                                                        yyerror("expressao deve ser um array "+((TS_entry)$1).getTipo());
                                                      if ( ((TS_entry)$3) != Tp_INT) 
                                                        yyerror("a posicao do array deve ser um inteiro "+((TS_entry)$3).getTipo());
                                                      $$ = validaTipo(ATRIB, Tp_INT, (TS_entry)$6);
                                                    }
;

Expression: Expression AND Expression                       { $$ = validaTipo(AND, (TS_entry)$1, (TS_entry)$3); } 
| Expression '+' Expression                                 { $$ = validaTipo('+', (TS_entry)$1, (TS_entry)$3); }
| Expression '-' Expression                                 { $$ = validaTipo('-', (TS_entry)$1, (TS_entry)$3); }
| Expression '*' Expression                                 { $$ = validaTipo('*', (TS_entry)$1, (TS_entry)$3); }
| Expression '<' Expression                                 { $$ = validaTipo('<', (TS_entry)$1, (TS_entry)$3); }
| IDENT '[' Expression ']'                                  { if ((TS_entry)$1 != Tp_ARRAYINT) 
                                                                yyerror("expressao deve ser um array "+((TS_entry)$1).getTipo());
                                                              if ( ((TS_entry)$3) != Tp_INT) 
                                                                yyerror("a posição do array deve ser um inteiro "+((TS_entry)$3).getTipo());
                                                            }
| IDENT '.' LENGTH                                          { if ((TS_entry)$1 != Tp_ARRAYINT) 
                                                                yyerror("expressao deve ser um array "+((TS_entry)$1).getTipo());
                                                              $$ = Tp_INT; 
                                                            }
| IDENT '.' IDENT '(' ')'                                   { TS_entry classe = ts.pesquisa($1);
                                                              if (classe == null) 
                                                                yyerror("classe >" + $1 + "< nao declarada");
                                                              TS_entry func = ts.pesquisa($3)
                                                              else if (func == null){
                                                                yyerror("funcao >" + $3 + "< nao declarada");
                                                              }
                                                            }
| IDENT '.' IDENT '(' ExpressionRepetition ')'              { TS_entry classe = ts.pesquisa($1);
                                                              if (classe == null) 
                                                                yyerror("classe >" + $1 + "< nao declarada");
                                                              TS_entry func = ts.pesquisa($3)
                                                              else if (func == null){
                                                                yyerror("funcao >" + $3 + "< nao declarada");
                                                              }
                                                            }
| NUM                                                       { $$ = Tp_INT; }
| TRUE                                                      { $$ = Tp_BOOLEAN; }
| FALSE                                                     { $$ = Tp_BOOLEAN; }
| IDENT                                                     { TS_entry nodo = ts.pesquisa($1);
                                                              if (nodo == null ) 
                                                                yyerror("(sem) Nome de tipo <" + $1 + "> nao declarado ");
                                                              else 
                                                                $$ = nodo;
                                                            }
| THIS
| NEW INT '[' Expression ']'                                { if ((TS_entry)$4 != Tp_INT) 
                                                                yyerror("posicao do array deve ser um inteiro "+((TS_entry)$1).getTipo());
                                                            }
| NEW IDENT '(' ')'                                         {  TS_entry nodo = ts.pesquisa($2);
                                                              if (nodo != null) 
                                                                yyerror("variavel >" + $2 + "< jah declarada");
                                                              else ts.insert(new TS_entry($2, (TS_entry)$1, currClass)); 
                                                            }
| '!' Expression                                            { $$ = Tp_BOOLEAN; }
| '(' Expression ')'                                        { $$ = $2; }
;

ExpressionRepetition: ExpressionRepetition ',' Expression
| Expression
;

%%

  private Yylex lexer;

  private TabSimb ts;

  public static TS_entry Tp_INT =  new TS_entry("int", null, ClasseID.TipoBase);
  public static TS_entry Tp_ARRAYINT = new TS_entry("int[]", null,  ClasseID.TipoBase);
  public static TS_entry Tp_BOOLEAN = new TS_entry("bool", null,  ClasseID.TipoBase);
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
    ts.insert(Tp_BOOLEAN);
    

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
                    if ( A == B )
                         return A;
                     else
                         yyerror("(sem) tipos incomp. para atribuicao: "+ A.getTipoStr() + " = "+B.getTipoStr());
                    break;

              case '+' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if (A == Tp_ARRAYINT && B == Tp_ARRAYINT) 
                         return Tp_ARRAYINT;     
                    else
                        yyerror("(sem) tipos incomp. para soma: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

              case '-' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if (A == Tp_ARRAYINT && B == Tp_ARRAYINT) 
                         return Tp_ARRAYINT;     
                    else
                        yyerror("(sem) tipos incomp. para subtracao: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

              case '*' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if (A == Tp_ARRAYINT && B == Tp_ARRAYINT) 
                         return Tp_ARRAYINT;     
                    else
                        yyerror("(sem) tipos incomp. para multiplicacao: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

             case '<' :
                     if ((A == Tp_INT && B == Tp_INT) || (A == Tp_ARRAYINT && B == Tp_ARRAYINT))
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

