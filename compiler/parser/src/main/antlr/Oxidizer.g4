grammar Oxidizer;

// Parser

module
    : decl+
    ;

decl
    : typedecl
    | funcdecl
    | importdecl
    ;

stmt
    : expr ';'
    | ident '=' expr ';'
    | loop
    ;

expr
    : ident
    | expr '+' expr //begin: bin operators
    | expr '-' expr
    | expr '*' expr
    | expr '/' expr
    | expr '%' expr
    | expr '^' expr
    | expr '^^' expr //begin: una ops
    | '-' expr
    | '~' expr
    | '!' expr
    | expr '++'
    | expr '--'
    | ident '(' (expr (',' expr)*)? ')' //function call
    ;

ident : NAME ;

typedecl : 'class' ;

funcdecl : 'def' ;

importdecl : 'import';


fragment UPPERCASE : [A-Z];
fragment LOWERCASE : [a-z];
fragment DIGIT : [0-9];
// Lexer

INTEGER : DIGIT+;
NAME : (LOWERCASE | UPPERCASE) [a-zA-Z0-9_]*;

WS : [ \t\r\n]+ -> skip ; //skip whitespace