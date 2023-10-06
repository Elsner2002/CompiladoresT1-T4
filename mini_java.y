%{
import java.io.*;
%}

%token CLASS, PUBLIC, STATIC, VOID, MAIN, STRING, EXTENDS, RETURN, BOOLEAN, INT, IF, ELSE, WHILE, PRINT, IDENTIFIER, INTEGERLITERAL, LENGTH, TRUE, FALSE, NEW, THIS

%right '='
%nonassoc '<'
%left '&&'
%left '-' '+'
%left '*'

%%

Goal: MainClass ClassDeclaretionRepetition
| MainClass
;

MainClass: CLASS IDENTIFIER '{'PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENTIFIER ')' '{' Statement '}' '}'
;

ClassDeclaretionRepetition: ClassDeclaretionRepetition ClassDeclaretion
;

ClassDeclaretion: CLASS IDENTIFIER EXTENDS IDENTIFIER '{' VarDeclaretionRepetition MethodDeclarationRepetition '}' 
| CLASS IDENTIFIER '{' VarDeclaretionRepetition MethodDeclarationRepetition '}' 
| CLASS IDENTIFIER EXTENDS IDENTIFIER '{' MethodDeclarationRepetition '}' 
| CLASS IDENTIFIER '{' MethodDeclarationRepetition '}' 
| CLASS IDENTIFIER EXTENDS IDENTIFIER '{' VarDeclaretionRepetition '}' 
| CLASS IDENTIFIER '{' VarDeclaretionRepetition '}' 
| CLASS IDENTIFIER EXTENDS IDENTIFIER '{' '}' 
| CLASS IDENTIFIER '{' '}' 
;

VarDeclaretionRepetition: VarDeclaretionRepetition VarDeclaretion
| VarDeclaretion
;

MethodDeclarationRepetition: MethodDeclarationRepetition MethodDeclaration
| MethodDeclaration
;

VarDeclaretion: Type IDENTIFIER ';'
;

MethodDeclaration: PUBLIC Type IDENTIFIER '(' DeclarationTypesRepetition ')' '{' VarDeclaretionRepetition StatementRepetition RETURN Expression ';' '}'
| PUBLIC Type IDENTIFIER '(' DeclarationTypesRepetition ')' '{' VarDeclaretionRepetition RETURN Expression ';' '}'
| PUBLIC Type IDENTIFIER '(' ')' '{' VarDeclaretionRepetition StatementRepetition RETURN Expression ';' '}'
| PUBLIC Type IDENTIFIER '(' ')' '{' VarDeclaretionRepetition RETURN Expression ';' '}'
| PUBLIC Type IDENTIFIER '(' DeclarationTypesRepetition ')' '{' StatementRepetition RETURN Expression ';' '}'
| PUBLIC Type IDENTIFIER '(' DeclarationTypesRepetition ')' '{' RETURN Expression ';' '}'
| PUBLIC Type IDENTIFIER '(' ')' '{' StatementRepetition RETURN Expression ';' '}'
| PUBLIC Type IDENTIFIER '(' ')' '{' RETURN Expression ';' '}'
;

DeclarationTypesRepetition: Type IDENTIFIER ',' DeclarationTypesRepetition
| Type IDENTIFIER
;

StatementRepetition: StatementRepetition Statement
| Statement
;

Type: INT '[' ']'
| BOOLEAN
| INT
| IDENTIFIER
;

Statement: '{' StatementRepetition '}'
| '{' '}'
| IF '(' Expression ')' Statement ELSE Statement
| WHILE '(' Expression ')' Statement
| PRINT '(' Expression ')' ';'
| IDENTIFIER '=' Expression ';'
| IDENTIFIER '[' Expression ']' '=' Expression ';'
;

Expression: Expression Operator Expression
| Expression '[' Expression ']'
| Expression '.' LENGTH
| Expression '.' IDENTIFIER '(' ')'
| Expression '.' IDENTIFIER '(' ExpressionRepetition ')'
| INTEGERLITERAL
| TRUE
| FALSE
| IDENTIFIER
| THIS
| NEW INT '[' Expression ']'
| NEW IDENTIFIER '(' ')'
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
