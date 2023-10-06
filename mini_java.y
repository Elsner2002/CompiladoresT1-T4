%{
import java.io.*;
%}

%token <dval> CLASS, PUBLIC, STATIC, VOID, MAIN, STRING, EXTENDS, RETURN, BOOLEAN, INT, IF, ELSE, WHILE, PRINT, IDENTIFIER, INTEGERLITERAL, LENGTH, TRUE, FALSE, NEW, THIS

%right '='
%nonassoc '<'
%left '&&'
%left '-' '+'
%left '*'

%%

Goal: MainClass ClassDeclaretionRepetition
;

MainClass: CLASS Identifier '{'PUBLIC STATIC VOID MAIN '(' STRING '[' ']' Identifier ')' '{' Statement '}' '}'
;

ClassDeclaretionRepetition: ClassDeclaretionRepetition ClassDeclaretion
|
;

ClassDeclaretion: CLASS Identifier ExtendID '{' VarDeclaretionRepetition MethodDeclarationRepetition '}' 
;

ExtendID: EXTENDS Identifier 
|
;

VarDeclaretionRepetition: VarDeclaretionRepetition VarDeclaretion
|
;

MethodDeclarationRepetition: MethodDeclarationRepetition MethodDeclaration
|
;

VarDeclaretion: Type Identifier ';'
;

MethodDeclaration: PUBLIC Type Identifier '(' DeclarationTypes ')' '{' VarDeclaretionRepetition StatementRepetition RETURN Expression ';' '}'
;

DeclarationTypes: Type Identifier DeclarationTypesRepetition
|
;

DeclarationTypesRepetition: ',' Type Identifier DeclarationTypesRepetition
|
;

StatementRepetition: StatementRepetition Statement
|
;

Type: INT '[' ']'
| BOOLEAN
| INT
| Identifier
;

Statement: '{' StatementRepetition '}'
| IF '(' Expression ')' Statement ELSE Statement
| WHILE '(' Expression ')' Statement
| PRINT '(' Expression ')' ';'
| Identifier '=' Expression ';'
| Identifier '[' Expression ']' '=' Expression ';'
;

Expression: Expression Operator Expression
| Expression '[' Expression ']'
| Expression '.' LENGTH
| Expression '.' Identifier '(' ')'
| Expression '.' Identifier '(' ExpressionRepetition ')'
| INTEGERLITERAL
| TRUE
| FALSE
| Identifier
| THIS
| NEW INT '[' Expression ']'
| NEW Identifier '(' ')'
| '!' Expression
| '(' Expression ')'
;

ExpressionRepetition: ExpressionRepetition ',' Expression
| Expression
;

Operator: '&&'
| '<'
| '+'
| '-'
| '*'
;

Identifier: IDENTIFIER
;

%%

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
