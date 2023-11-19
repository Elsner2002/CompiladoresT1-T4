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

Goal: {currClass = ClasseID.NomeStruct;} MainClass ClassDeclarationRepetition
;

MainClass: CLASS IDENT '{'PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENT ')' '{' Statement '}' '}' {  TS_entry nodo = ts.pesquisa($2.sval);
   if (nodo != null) 
     yyerror("main class >" + $2.sval + "< jah declarada");
   else ts.insert(new TS_entry($2.sval, (TS_entry)$1.obj, currClass)); 
}
;

ClassDeclarationRepetition: ClassDeclarationRepetition ClassDeclaration
| {currClass = ClasseID.NomeStruct;}
;

ClassDeclaration: CLASS IDENT Extends '{' VarDeclarationRepetition MethodDeclarationRepetition '}'  { TS_entry nodo = ts.pesquisa($2.sval);
  if (nodo != null) 
    yyerror("classe >" + $2.sval + "< jah declarada");
  else ts.insert(new TS_entry($2.sval, (TS_entry)$1.obj, currClass)); 
}
;

Extends: EXTENDS IDENT  {  TS_entry nodo = ts.pesquisa($2.sval);
    if (nodo == null) 
      yyerror("ident >" + $2.sval + "< nao declarado");
    else ts.insert(new TS_entry($2.sval, (TS_entry)$1.obj, currClass)); 
}
|
;

VarDeclarationRepetition: VarDeclarationRepetition VarDeclaration
| {currClass = ClasseID.VarLocal;}
;

MethodDeclarationRepetition: MethodDeclarationRepetition MethodDeclaration
| {currClass = ClasseID.NomeParam;}
;

VarDeclaration: Type IDENT ';'{  TS_entry nodo = ts.pesquisa($2.sval);
    if (nodo != null) 
      yyerror("variavel >" + $2.sval + "< jah declarada");
    else ts.insert(new TS_entry($2.sval, (TS_entry)$1.obj, currClass)); 
    //System.out.println((TS_entry)$1.obj);
}
;

MethodDeclaration: PUBLIC Type IDENT '(' DeclarationTypes ')' VarDeclarationRepetition '{' StatementRepetition RETURN Expression ';' '}' {  TS_entry nodo = ts.pesquisa($3.sval);
  if (nodo != null) 
    yyerror("metodo >" + $3.sval + "< jah declarado");
  else ts.insert(new TS_entry($3.sval, (TS_entry)$2.obj, currClass)); 
}
;

DeclarationTypes: DeclarationTypesRepetition
| {currClass = ClasseID.NomeParam;}
;

DeclarationTypesRepetition: DeclarationTypesRepetition ',' Type IDENT {  TS_entry nodo = ts.pesquisa($4.sval);
    if (nodo != null) 
      yyerror("variavel >" + $4.sval + "< jah declarada");
    else ts.insert(new TS_entry($4.sval, (TS_entry)$3.obj, currClass)); 
}
| Type IDENT { TS_entry nodo = ts.pesquisa($2.sval);
  if (nodo != null) 
    yyerror("variavel >" + $2.sval + "< jah declarada");
  else ts.insert(new TS_entry($2.sval, (TS_entry)$1.obj, currClass)); 
}
;

StatementRepetition: StatementRepetition Statement
| {currClass = ClasseID.NomeFuncao;}
;

Type: INT '[' ']' { $$.obj = Tp_ARRAYINT; }
| BOOLEAN { $$.obj = Tp_BOOL; }
| INT  { $$.obj = Tp_INT; }
| IDENT { TS_entry nodo = ts.pesquisa($1.sval);
 if (nodo == null ) 
   yyerror("(sem) Nome de tipo <" + $1.sval + "> nao declarado ");
 else 
   $$.obj = nodo;
} 
;

Statement: '{' StatementRepetition '}'
| IF '(' Expression ')' Statement ELSE Statement  { if ( ((TS_entry)$3.obj) != Tp_BOOL) 
    yyerror("(sem) expressao (if) deve ser logica "+((TS_entry)$3.obj).getTipo());
} 
| WHILE '(' Expression ')' Statement  { if ( ((TS_entry)$3.obj) != Tp_BOOL) 
    yyerror("(sem) expressao (while) deve ser logica "+((TS_entry)$3.obj).getTipo());
}
| PRINT '(' Expression ')' ';' 
| IDENT '=' Expression ';'  { TS_entry nodo = ts.pesquisa($1.sval);
if (nodo == null) {
  yyerror("(sem) var <" + $1.sval + "> nao declarada");     
}           
else
  $$.obj = validaTipo(ATRIB, nodo.getTipo(), (TS_entry)$3.obj); } 
| IDENT '[' Expression ']' '=' Expression ';' { if ((TS_entry)$1.obj != Tp_ARRAYINT) 
    yyerror("expressao deve ser um array "+((TS_entry)$1.obj).getTipo());
  if ( ((TS_entry)$3.obj) != Tp_INT) 
    yyerror("a posicao do array deve ser um inteiro "+((TS_entry)$3.obj).getTipo());
  $$.obj = validaTipo(ATRIB, Tp_INT, (TS_entry)$6.obj);
}
;

Expression: Expression AND Expression  { $$.obj = validaTipo(AND, (TS_entry)$1.obj, (TS_entry)$3.obj); } 
| Expression '+' Expression    { $$.obj = validaTipo('+', (TS_entry)$1.obj, (TS_entry)$3.obj); }
| Expression '-' Expression    { $$.obj = validaTipo('-', (TS_entry)$1.obj, (TS_entry)$3.obj); }
| Expression '*' Expression    { $$.obj = validaTipo('*', (TS_entry)$1.obj, (TS_entry)$3.obj); }
| Expression '<' Expression    { $$.obj = validaTipo('<', (TS_entry)$1.obj, (TS_entry)$3.obj); }
| IDENT '[' Expression ']'     { if ((TS_entry)$1.obj != Tp_ARRAYINT) 
    yyerror("expressao deve ser um array "+((TS_entry)$1.obj).getTipo());
  if ( ((TS_entry)$3.obj) != Tp_INT) 
    yyerror("a posição do array deve ser um inteiro "+((TS_entry)$3.obj).getTipo());
}
| IDENT '.' LENGTH  { if ((TS_entry)$1.obj != Tp_ARRAYINT) 
    yyerror("expressao deve ser um array "+((TS_entry)$1.obj).getTipo());
  $$.obj = Tp_INT; 
}
| IDENT '.' IDENT '(' ')' { TS_entry classe = ts.pesquisa($1.sval);
  if (classe == null) 
    yyerror("classe >" + $1.sval + "< nao declarada");
  TS_entry func = ts.pesquisa($3.sval);
  if (func == null)
    yyerror("funcao >" + $3.sval + "< nao declarada");
  $$.obj = $3.obj;
}
| IDENT '.' IDENT '(' ExpressionRepetition ')'  { TS_entry classe = ts.pesquisa($1.sval);
  if (classe == null) 
    yyerror("classe >" + $1.sval + "< nao declarada");
  TS_entry func = ts.pesquisa($3.sval);
  if (func == null)
    yyerror("funcao >" + $3.sval + "< nao declarada");
  $$.obj = $3.obj;
}
| NUM   { $$.obj = Tp_INT; }
| TRUE  { $$.obj = Tp_BOOL; }
| FALSE { $$.obj = Tp_BOOL; }
| IDENT { TS_entry nodo = ts.pesquisa($1.sval);
  if (nodo == null ) 
    yyerror("(sem) Nome de tipo <" + $1.sval + "> nao declarado ");
  else 
    //System.out.println("var: " + $1.sval + "\n Node: "+ nodo);
    $$.obj = nodo;
}
| THIS
| NEW INT '[' Expression ']'  { if ((TS_entry)$4.obj != Tp_INT) 
    yyerror("posicao do array deve ser um inteiro "+((TS_entry)$1.obj).getTipo());
}
| NEW IDENT '(' ')' {  TS_entry nodo = ts.pesquisa($2.sval);
  if (nodo != null) 
    yyerror("variavel >" + $2.sval + "< jah declarada");
  else ts.insert(new TS_entry($2.sval, (TS_entry)$1.obj, currClass)); 
}
| '!' Expression { if ((TS_entry)$2.obj != Tp_BOOL)
    yyerror("expressao deve ser booleana "+((TS_entry)$2.obj).getTipo());
$$.obj = Tp_BOOL; 
}
| '(' Expression ')' { $$.obj = $2.obj; }
;

ExpressionRepetition: ExpressionRepetition ',' Expression
| Expression
;

%%

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
        while (A.getTipo() != null) {
          A = A.getTipo();
        }
        while (B.getTipo() != null) {
          B = B.getTipo();
        }
         switch ( operador ) {
              case ATRIB:
                    if ( A == B )
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
                     if ((A == Tp_INT && B == Tp_INT) || ( A == Tp_ARRAYINT && B == Tp_ARRAYINT) )
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

