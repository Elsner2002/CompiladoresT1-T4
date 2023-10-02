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
| Expression '.' Identifier '(' ExpressionDeclaration ')'
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

ExpressionDeclaration: Expression ExpressionRepetition
|
;

ExpressionRepetition: Expression ',' ExpressionRepetition
|
;

Operator: '&&'
| '<'
| '+'
| '-'
| '*'
;

Identifier: IDENTIFIER
;