lexer grammar OxidizerLexer;


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
KW_THEN: 'then';
KW_RETURN: 'return';
KW_CASE: 'case';
KW_SWITCH: 'switch';
KW_BREAK: 'break';
KW_CONTINUE: 'continue';
KW_SPAWN: 'spwan';
KW_LISTEN: 'listen';
KW_SEND: 'send';
KW_THROW: 'throw';
KW_CATCH: 'catch';
KW_TRY: 'try';
KW_FINALLY: 'finally';


OP_LSHIFT: '<<';
OP_RSHIFT: '>>';
OP_RSHIFT_UNSIGNED: '>>>';

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
OP_ASSIGN: '=';

SEMI: ';';
COLON: ':';

LPAREN: '(';
RPAREN: ')';

LSQUARE: '[';
RSQUARE: ']';

COMMA: ',';

ENTER_STRING: '"' -> pushMode(STRING);

LBRACE: '{' -> pushMode(DEFAULT_MODE);
RBRACE: '}' -> popMode;

INTEGER : DIGIT+;
FLOAT: DIGIT+ ('.' DIGIT* 'f'? | 'f');
NAME : (LOWERCASE | UPPERCASE) [a-zA-Z0-9_.]*;

WS : [ \t\r\n]+ -> skip ; //skip whitespace

mode STRING;

ENTER_STR_EXPR: '${' -> pushMode(DEFAULT_MODE);
END_STRING: '"' -> popMode;
TEXT: ~('$' | '"' | '\n')+ ; //TODO: escape sequences... maybe another lexer mode?
