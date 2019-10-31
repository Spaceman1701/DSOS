parser grammar OxidizerParser;

options {tokenVocab = OxidizerLexer;}

// Parser

module
    : importdecl* decl+ //imports must be first
    ;

decl
    : typedecl
    | funcdecl
    ;


block : LBRACE stmt* RBRACE;

stmt
    : expr SEMI
    | ident OP_ASSIGN expr SEMI
    | loop
    | branch
    | KW_RETURN expr SEMI
    ;

loop
    : KW_FOR ident KW_IN expr LBRACE stmt* RBRACE
    | KW_WHILE expr block
    ;

branch
    : KW_IF expr block (KW_ELIF expr block)* (KW_ELSE block)?
    ;

compare_op : COMP_G | COMP_GE | COMP_L | COMP_LE;

string: ENTER_STRING stringPart* END_STRING;

stringPart
    : TEXT # Text
    | ENTER_STR_EXPR expr RBRACE # EmbeddedExpr
    ;

literall
    : INTEGER # IntLit
    | FLOAT # FloatLit
    | string # StringLit
    ;

expr
    //Precedence 1
    : ident # Var
    | literall # Lit
    | ident LPAREN (expr (COMMA expr)*)? RPAREN # FunCall
    | LPAREN expr RPAREN # Parens
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
    | KW_IF expr KW_THEN expr KW_ELSE expr # Ternary
    ;

comprehension
    : LSQUARE RSQUARE # EmptyList
    | LSQUARE expr (COMMA expr)* RSQUARE # LiteralList
    | LSQUARE expr KW_FOR ident KW_IN expr (KW_IF expr)? RSQUARE # ForComp
    | LSQUARE (expr)? COLON (expr)? (COLON)? (expr)? RSQUARE # ListSlice
    ;

ident : NAME ;

typedecl : KW_CLASS NAME LBRACE innerclass RBRACE;

innerclass : funcdecl*;

funcdecl : (KW_PRIVATE)? KW_DEF NAME LPAREN (NAME (COMMA NAME)*)? RPAREN block;

importdecl : KW_IMPORT NAME;