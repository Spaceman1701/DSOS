grammar Oxidizer;

// Parser

module
    : importdecl* decl+ //imports must be first
    ;

decl
    : typedecl
    | funcdecl
    ;


block : '{' stmt* '}';

stmt
    : expr ';'
    | ident '=' expr ';'
    | loop
    | branch
    ;

loop
    : KW_FOR ident KW_IN expr '{' stmt* '}'
    | KW_WHILE expr block
    ;

branch
    : KW_IF expr block (KW_ELIF expr block)* (KW_ELSE block)?
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
    | '[' expr KW_FOR ident KW_IN expr KW_IF expr ']' //for comprehension
    | '[' expr ':' (expr)? (':' expr)? ']' //slice
    ;

ident : NAME ;

typedecl : KW_CLASS NAME '{' innerclass '}';

innerclass : 'nothing';

funcdecl : (KW_PRIVATE)? KW_DEF NAME '(' (NAME (',' NAME)*)? ')' block;

importdecl : KW_IMPORT;


fragment UPPERCASE : [A-Z];
fragment LOWERCASE : [a-z];
fragment DIGIT : [0-9];
// Lexer

KW_PRIVATE : 'private';
KW_DEF : 'def';
KW_FOR : 'for';
KW_IN: 'in';
KW_WHILE: 'while';
KW_IF: 'if';
KW_ELIF: 'elif';
KW_ELSE: 'else';
KW_CLASS: 'class';
KW_IMPORT: 'import';

INTEGER : DIGIT+;
NAME : (LOWERCASE | UPPERCASE) [a-zA-Z0-9_]*;

WS : [ \t\r\n]+ -> skip ; //skip whitespace