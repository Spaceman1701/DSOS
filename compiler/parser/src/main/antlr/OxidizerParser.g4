parser grammar OxidizerParser;

options {tokenVocab = OxidizerLexer;}

// Parser
program : module EOF;


module
    : importdecl* decl* # ModuleDef//imports must be first
    ;

decl
    : KW_CLASS NAME LBRACE innerclass RBRACE #TypeDecl
    | funcdef #FuncDecl
    ;


block : LBRACE stmt* RBRACE;

stmt
    : expr SEMI #ExprStmt
    | ident OP_ASSIGN expr SEMI #AssignStmt
    | loop #LoopStmt
    | branch #BranchStmt
    | KW_RETURN expr SEMI #ReturnStmt
    ;

loop
    : KW_FOR ident KW_IN expr LBRACE stmt* RBRACE #ForLoop
    | KW_WHILE expr block #WhileLoop
    ;

branch
    : KW_IF expr ifBody=block (elif)* (KW_ELSE elseBody=block)? #IfBranch
    | KW_SWITCH expr RBRACE (switch_case)+ LBRACE #SwitchBranch
    ;

switch_case: KW_CASE (ident | literall) block;
elif: KW_ELIF expr block;

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
    | LSQUARE ele=expr KW_FOR ident KW_IN inExpr=expr (KW_IF cond=expr)? RSQUARE # ForComp
    | LSQUARE (start=expr)? COLON (end=expr)? (COLON)? (step=expr)? RSQUARE # ListSlice
    ;

ident : NAME ;

funcdef: (KW_PRIVATE)? KW_DEF NAME LPAREN (NAME (COMMA NAME)*)? RPAREN block;

innerclass : funcdef*;

importdecl : KW_IMPORT NAME SEMI;