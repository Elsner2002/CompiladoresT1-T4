%{
import java.io.*;
%}

%token IF, WHILE, ELSE, INT, DOUBLE, BOOLEAN, AND, IDENT, NUM

%right '='
%nonassoc '>'
%left AND
%left '-' '+'
%left '/' '*'

%%

Prog: Decl Bloco
;

Decl: Tipo LId ';' Decl
|
;

Tipo: INT
| DOUBLE
| BOOLEAN
;

LId: LId ',' IDENT
| IDENT
;

Bloco: '{' LCmd '}'
;

LCmd: LCmd Cmd
|
;

Cmd: Bloco
| IF '(' E ')' Cmd
| IF '(' E ')' Cmd ELSE Cmd
| WHILE '(' E ')' Cmd
| E ';'
;

E: E '=' E
| E '+' E
| E '*' E
| E '/' E
| E '>' E
| E AND E
| NUM
| IDENT
| '(' E ')'
;