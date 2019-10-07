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

loop
    : 'for' ident 'in' expr '{' stmt* '}'
    | 'while' expr '{' stmt* '}'
    ;

expr
    : ident # Var
    | expr '+' expr # Add
    | expr '-' expr # Sub
    | expr '*' expr # Mul
    | expr '/' expr # Div
    | expr '%' expr # Mod
    | expr '^' expr # Xor
    | expr '&' expr # BAnd
    | expr '|' expr # Bor
    | expr '^^' expr # Pow
    | expr 'and' expr # LAnd
    | expr 'or' expr # Bor
    | '-' expr # Neg
    | '~' expr # Comp
    | '!' expr # Inv
    | expr '++' # Inc
    | expr '--' # Dec
    | ident '(' (expr (',' expr)*)? ')' # FunCall
    | comprehension # ListComp
    ;

comprehension
    : '[' ']' //empty
    | '[' expr (',' expr)* ']' //literal list
    | '[' expr 'for' ident 'in' expr 'if' expr ']' //for comprehension
    | '[' expr ':' (expr)? (':' expr)? ']' //slice
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