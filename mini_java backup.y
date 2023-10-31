%{
import java.io.*;
%}

%token AND, CLASS, PUBLIC, STATIC, VOID, MAIN, STRING, EXTENDS, RETURN, BOOLEAN, INT, IF, ELSE, WHILE, PRINT, IDENT, NUM, LENGTH, TRUE, FALSE, NEW, THIS

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

Goal: MainClass ClassDeclaretionRepetition
| MainClass
;

MainClass: CLASS IDENT '{'PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENT ')' '{' Statement '}' '}'
;

ClassDeclaretionRepetition: ClassDeclaretionRepetition ClassDeclaretion
| ClassDeclaretion
;

ClassDeclaretion: CLASS IDENT EXTENDS IDENT '{' VarDeclaretionRepetition MethodDeclarationRepetition '}' 
| CLASS IDENT '{' VarDeclaretionRepetition MethodDeclarationRepetition '}' 
| CLASS IDENT EXTENDS IDENT '{' MethodDeclarationRepetition '}' 
| CLASS IDENT '{' MethodDeclarationRepetition '}' 
| CLASS IDENT EXTENDS IDENT '{' VarDeclaretionRepetition '}' 
| CLASS IDENT '{' VarDeclaretionRepetition '}' 
| CLASS IDENT EXTENDS IDENT '{' '}' 
| CLASS IDENT '{' '}' 
;

VarDeclaretionRepetition: VarDeclaretionRepetition VarDeclaretion
| VarDeclaretion
;

MethodDeclarationRepetition: MethodDeclarationRepetition MethodDeclaration
| MethodDeclaration
;

VarDeclaretion: Type IDENT ';'
;

MethodDeclaration: PUBLIC Type IDENT '(' DeclarationTypesRepetition ')' '{' VarDeclaretionRepetition StatementRepetition RETURN Expression ';' '}'
| PUBLIC Type IDENT '(' DeclarationTypesRepetition ')' '{' VarDeclaretionRepetition RETURN Expression ';' '}'
| PUBLIC Type IDENT '(' ')' '{' VarDeclaretionRepetition StatementRepetition RETURN Expression ';' '}'
| PUBLIC Type IDENT '(' ')' '{' VarDeclaretionRepetition RETURN Expression ';' '}'
| PUBLIC Type IDENT '(' DeclarationTypesRepetition ')' '{' StatementRepetition RETURN Expression ';' '}'
| PUBLIC Type IDENT '(' DeclarationTypesRepetition ')' '{' RETURN Expression ';' '}'
| PUBLIC Type IDENT '(' ')' '{' StatementRepetition RETURN Expression ';' '}'
| PUBLIC Type IDENT '(' ')' '{' RETURN Expression ';' '}'
;

DeclarationTypesRepetition: DeclarationTypesRepetition ',' Type IDENT 
| Type IDENT
;

StatementRepetition: StatementRepetition Statement
| Statement
;

Type: INT '[' ']'
| BOOLEAN
| INT
| IDENT
;

Statement: '{' StatementRepetition '}'
| '{' '}'
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
