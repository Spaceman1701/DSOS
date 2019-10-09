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
    | expr OP_POW expr # Pow
    | expr (OP_PLUS | OP_MINUS) expr # AddSub
    | expr (OP_MUL | OP_DIV) expr # MulDiv
    | expr OP_MOD expr # Mod
    | expr OP_XOR expr # Xor
    | expr OP_BAND expr # BAnd
    | expr OP_BOR expr # Bor
    | expr OP_LAND expr # LAnd
    | expr OP_LOR expr # Bor
    | OP_MINUS expr # Neg
    | OP_COMP expr # Comp
    | OP_NOT expr # Inv
    | expr OP_INC # Inc
    | expr OP_DEC # Dec
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

OP_INC: '++';
OP_DEC: '--';
OP_LAND: 'and';
OP_LOR: 'or';
OP_NOT: 'not';
OP_POW: '^^';
OP_PLUS: '+';
OP_MINUS: '-';
OP_MUL: '*';
OP_DIV: '/';
OP_MOD: '%';
OP_BAND: '&';
OP_BOR: '|';
OP_XOR: '^';
OP_COMP: '~';

LPAREN: '(';
RPAREN: ')';

LBRACE: '{';
RBRACE: '}';

INTEGER : DIGIT+;
NAME : (LOWERCASE | UPPERCASE) [a-zA-Z0-9_]*;

WS : [ \t\r\n]+ -> skip ; //skip whitespace