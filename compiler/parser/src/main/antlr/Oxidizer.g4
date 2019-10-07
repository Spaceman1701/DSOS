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
    | comprehension
    ;

comprehension
    : '[' ']' //empty
    | '[' expr (',' expr)* ']' //literal list
    | '[' expr KW_FOR ident KW_IN expr KW_IF expr ']' //for comprehension
    | '[' expr ':' (expr)? (':' expr)? ']' //slice
    ;

ident : NAME ;

typedecl : KW_CLASS ;

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