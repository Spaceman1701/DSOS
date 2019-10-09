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


compare_op : COMP_G | COMP_GE | COMP_L | COMP_LE;


expr
    //Precedence 1
    : ident # Var
    | ident '(' (expr (',' expr)*)? ')' # FunCall
    | '(' expr ')' # Parens
    | comprehension # ListComp
    | expr OP_INC # Inc
    | expr OP_DEC # Dec
    //Precedence 2
    | OP_MINUS expr # Neg
    | (OP_NOT | OP_COMPLEMENT) expr # Not
    | <assoc=right> expr OP_POW expr # Pow
    //Precedence 3
    | expr (OP_MUL | OP_DIV | OP_MOD) expr # MulModDiv
    //Precedence 4
    | expr (OP_PLUS | OP_MINUS) expr # AddSub
    //Precedence 5
    | expr compare_op expr # Compare
    | expr (COMP_EQ | COMP_NE) expr # EQTest
    //Precedence 6
    | expr (OP_LSHIFT | OP_RSHIFT) expr # Shift
    | expr OP_BAND expr # BAnd
    | expr OP_XOR expr # Xor
    | expr OP_BOR expr # Bor
    | expr OP_AND expr # LAnd
    | expr OP_OR expr # Lor
    | expr OP_INC expr # Concat
    ;

comprehension
    : '[' ']' # EmptyList
    | '[' expr (',' expr)* ']' # LiteralList
    | '[' expr KW_FOR ident KW_IN expr KW_IF expr ']' # ForComp
    | '[' expr ':' (expr)? (':' expr)? ']' # ListSlice
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


OP_LSHIFT: '<<';
OP_RSHIFT: '>>';

COMP_EQ: '==';
COMP_GE: '>=';
COMP_LE: '<=';
COMP_NE: '!=';
COMP_G: '>';
COMP_L: '<';

OP_AND: 'and';
OP_OR: 'or';
OP_NOT: 'not';
OP_INC: '++';
OP_DEC: '--';
OP_POW: '^^';
OP_PLUS: '+';
OP_MINUS: '-';
OP_MUL: '*';
OP_DIV: '/';
OP_MOD: '%';
OP_BAND: '&';
OP_BOR: '|';
OP_XOR: '^';
OP_COMPLEMENT: '~';

LPAREN: '(';
RPAREN: ')';

LBRACE: '{';
RBRACE: '}';

INTEGER : DIGIT+;
NAME : (LOWERCASE | UPPERCASE) [a-zA-Z0-9_]*;

WS : [ \t\r\n]+ -> skip ; //skip whitespace