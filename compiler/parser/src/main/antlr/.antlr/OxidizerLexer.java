// Generated from c:\Users\fcspa\de\dsos\compiler\parser\src\main\antlr/OxidizerLexer.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class OxidizerLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		KW_PRIVATE=1, KW_DEF=2, KW_FOR=3, KW_IN=4, KW_WHILE=5, KW_IF=6, KW_ELIF=7, 
		KW_ELSE=8, KW_CLASS=9, KW_IMPORT=10, KW_THEN=11, OP_LSHIFT=12, OP_RSHIFT=13, 
		COMP_EQ=14, COMP_GE=15, COMP_LE=16, COMP_NE=17, COMP_G=18, COMP_L=19, 
		OP_AND=20, OP_OR=21, OP_NOT=22, OP_INC=23, OP_DEC=24, OP_POW=25, OP_PLUS=26, 
		OP_MINUS=27, OP_MUL=28, OP_DIV=29, OP_MOD=30, OP_BAND=31, OP_BOR=32, OP_XOR=33, 
		OP_COMPLEMENT=34, OP_ASSIGN=35, SEMI=36, COLON=37, LPAREN=38, RPAREN=39, 
		LSQUARE=40, RSQUARE=41, COMMA=42, ENTER_STRING=43, LBRACE=44, RBRACE=45, 
		INTEGER=46, NAME=47, WS=48, ENTER_STR_EXPR=49, END_STRING=50, TEXT=51;
	public static final int
		STRING=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "STRING"
	};

	public static final String[] ruleNames = {
		"UPPERCASE", "LOWERCASE", "DIGIT", "KW_PRIVATE", "KW_DEF", "KW_FOR", "KW_IN", 
		"KW_WHILE", "KW_IF", "KW_ELIF", "KW_ELSE", "KW_CLASS", "KW_IMPORT", "KW_THEN", 
		"OP_LSHIFT", "OP_RSHIFT", "COMP_EQ", "COMP_GE", "COMP_LE", "COMP_NE", 
		"COMP_G", "COMP_L", "OP_AND", "OP_OR", "OP_NOT", "OP_INC", "OP_DEC", "OP_POW", 
		"OP_PLUS", "OP_MINUS", "OP_MUL", "OP_DIV", "OP_MOD", "OP_BAND", "OP_BOR", 
		"OP_XOR", "OP_COMPLEMENT", "OP_ASSIGN", "SEMI", "COLON", "LPAREN", "RPAREN", 
		"LSQUARE", "RSQUARE", "COMMA", "ENTER_STRING", "LBRACE", "RBRACE", "INTEGER", 
		"NAME", "WS", "ENTER_STR_EXPR", "END_STRING", "TEXT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'private'", "'def'", "'for'", "'in'", "'while'", "'if'", "'elif'", 
		"'else'", "'class'", "'import'", "'then'", "'<<'", "'>>'", "'=='", "'>='", 
		"'<='", "'!='", "'>'", "'<'", "'and'", "'or'", "'not'", "'++'", "'--'", 
		"'^^'", "'+'", "'-'", "'*'", "'/'", "'%'", "'&'", "'|'", "'^'", "'~'", 
		"'='", "';'", "':'", "'('", "')'", "'['", "']'", "','", null, "'{'", "'}'", 
		null, null, null, "'${'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "KW_PRIVATE", "KW_DEF", "KW_FOR", "KW_IN", "KW_WHILE", "KW_IF", 
		"KW_ELIF", "KW_ELSE", "KW_CLASS", "KW_IMPORT", "KW_THEN", "OP_LSHIFT", 
		"OP_RSHIFT", "COMP_EQ", "COMP_GE", "COMP_LE", "COMP_NE", "COMP_G", "COMP_L", 
		"OP_AND", "OP_OR", "OP_NOT", "OP_INC", "OP_DEC", "OP_POW", "OP_PLUS", 
		"OP_MINUS", "OP_MUL", "OP_DIV", "OP_MOD", "OP_BAND", "OP_BOR", "OP_XOR", 
		"OP_COMPLEMENT", "OP_ASSIGN", "SEMI", "COLON", "LPAREN", "RPAREN", "LSQUARE", 
		"RSQUARE", "COMMA", "ENTER_STRING", "LBRACE", "RBRACE", "INTEGER", "NAME", 
		"WS", "ENTER_STR_EXPR", "END_STRING", "TEXT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public OxidizerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "OxidizerLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\65\u012f\b\1\b\1"+
		"\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
		"\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
		"\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4"+
		"\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4"+
		" \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4"+
		"+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4"+
		"\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3"+
		"#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3."+
		"\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\62\6\62\u0108\n"+
		"\62\r\62\16\62\u0109\3\63\3\63\5\63\u010e\n\63\3\63\7\63\u0111\n\63\f"+
		"\63\16\63\u0114\13\63\3\64\6\64\u0117\n\64\r\64\16\64\u0118\3\64\3\64"+
		"\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\67\6\67\u0127\n\67\r\67"+
		"\16\67\u0128\3\67\3\67\3\67\5\67\u012e\n\67\3\u0128\28\4\2\6\2\b\2\n\3"+
		"\f\4\16\5\20\6\22\7\24\b\26\t\30\n\32\13\34\f\36\r \16\"\17$\20&\21(\22"+
		"*\23,\24.\25\60\26\62\27\64\30\66\318\32:\33<\34>\35@\36B\37D F!H\"J#"+
		"L$N%P&R\'T(V)X*Z+\\,^-`.b/d\60f\61h\62j\63l\64n\65\4\2\3\7\3\2C\\\3\2"+
		"c|\3\2\62;\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\2\u0130\2\n\3\2\2\2\2\f\3"+
		"\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2"+
		"\2\2\30\3\2\2\2\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\""+
		"\3\2\2\2\2$\3\2\2\2\2&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2"+
		"\2\2\2\60\3\2\2\2\2\62\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2"+
		":\3\2\2\2\2<\3\2\2\2\2>\3\2\2\2\2@\3\2\2\2\2B\3\2\2\2\2D\3\2\2\2\2F\3"+
		"\2\2\2\2H\3\2\2\2\2J\3\2\2\2\2L\3\2\2\2\2N\3\2\2\2\2P\3\2\2\2\2R\3\2\2"+
		"\2\2T\3\2\2\2\2V\3\2\2\2\2X\3\2\2\2\2Z\3\2\2\2\2\\\3\2\2\2\2^\3\2\2\2"+
		"\2`\3\2\2\2\2b\3\2\2\2\2d\3\2\2\2\2f\3\2\2\2\2h\3\2\2\2\3j\3\2\2\2\3l"+
		"\3\2\2\2\3n\3\2\2\2\4p\3\2\2\2\6r\3\2\2\2\bt\3\2\2\2\nv\3\2\2\2\f~\3\2"+
		"\2\2\16\u0082\3\2\2\2\20\u0086\3\2\2\2\22\u0089\3\2\2\2\24\u008f\3\2\2"+
		"\2\26\u0092\3\2\2\2\30\u0097\3\2\2\2\32\u009c\3\2\2\2\34\u00a2\3\2\2\2"+
		"\36\u00a9\3\2\2\2 \u00ae\3\2\2\2\"\u00b1\3\2\2\2$\u00b4\3\2\2\2&\u00b7"+
		"\3\2\2\2(\u00ba\3\2\2\2*\u00bd\3\2\2\2,\u00c0\3\2\2\2.\u00c2\3\2\2\2\60"+
		"\u00c4\3\2\2\2\62\u00c8\3\2\2\2\64\u00cb\3\2\2\2\66\u00cf\3\2\2\28\u00d2"+
		"\3\2\2\2:\u00d5\3\2\2\2<\u00d8\3\2\2\2>\u00da\3\2\2\2@\u00dc\3\2\2\2B"+
		"\u00de\3\2\2\2D\u00e0\3\2\2\2F\u00e2\3\2\2\2H\u00e4\3\2\2\2J\u00e6\3\2"+
		"\2\2L\u00e8\3\2\2\2N\u00ea\3\2\2\2P\u00ec\3\2\2\2R\u00ee\3\2\2\2T\u00f0"+
		"\3\2\2\2V\u00f2\3\2\2\2X\u00f4\3\2\2\2Z\u00f6\3\2\2\2\\\u00f8\3\2\2\2"+
		"^\u00fa\3\2\2\2`\u00fe\3\2\2\2b\u0102\3\2\2\2d\u0107\3\2\2\2f\u010d\3"+
		"\2\2\2h\u0116\3\2\2\2j\u011c\3\2\2\2l\u0121\3\2\2\2n\u0126\3\2\2\2pq\t"+
		"\2\2\2q\5\3\2\2\2rs\t\3\2\2s\7\3\2\2\2tu\t\4\2\2u\t\3\2\2\2vw\7r\2\2w"+
		"x\7t\2\2xy\7k\2\2yz\7x\2\2z{\7c\2\2{|\7v\2\2|}\7g\2\2}\13\3\2\2\2~\177"+
		"\7f\2\2\177\u0080\7g\2\2\u0080\u0081\7h\2\2\u0081\r\3\2\2\2\u0082\u0083"+
		"\7h\2\2\u0083\u0084\7q\2\2\u0084\u0085\7t\2\2\u0085\17\3\2\2\2\u0086\u0087"+
		"\7k\2\2\u0087\u0088\7p\2\2\u0088\21\3\2\2\2\u0089\u008a\7y\2\2\u008a\u008b"+
		"\7j\2\2\u008b\u008c\7k\2\2\u008c\u008d\7n\2\2\u008d\u008e\7g\2\2\u008e"+
		"\23\3\2\2\2\u008f\u0090\7k\2\2\u0090\u0091\7h\2\2\u0091\25\3\2\2\2\u0092"+
		"\u0093\7g\2\2\u0093\u0094\7n\2\2\u0094\u0095\7k\2\2\u0095\u0096\7h\2\2"+
		"\u0096\27\3\2\2\2\u0097\u0098\7g\2\2\u0098\u0099\7n\2\2\u0099\u009a\7"+
		"u\2\2\u009a\u009b\7g\2\2\u009b\31\3\2\2\2\u009c\u009d\7e\2\2\u009d\u009e"+
		"\7n\2\2\u009e\u009f\7c\2\2\u009f\u00a0\7u\2\2\u00a0\u00a1\7u\2\2\u00a1"+
		"\33\3\2\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4\7o\2\2\u00a4\u00a5\7r\2\2\u00a5"+
		"\u00a6\7q\2\2\u00a6\u00a7\7t\2\2\u00a7\u00a8\7v\2\2\u00a8\35\3\2\2\2\u00a9"+
		"\u00aa\7v\2\2\u00aa\u00ab\7j\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad\7p\2\2"+
		"\u00ad\37\3\2\2\2\u00ae\u00af\7>\2\2\u00af\u00b0\7>\2\2\u00b0!\3\2\2\2"+
		"\u00b1\u00b2\7@\2\2\u00b2\u00b3\7@\2\2\u00b3#\3\2\2\2\u00b4\u00b5\7?\2"+
		"\2\u00b5\u00b6\7?\2\2\u00b6%\3\2\2\2\u00b7\u00b8\7@\2\2\u00b8\u00b9\7"+
		"?\2\2\u00b9\'\3\2\2\2\u00ba\u00bb\7>\2\2\u00bb\u00bc\7?\2\2\u00bc)\3\2"+
		"\2\2\u00bd\u00be\7#\2\2\u00be\u00bf\7?\2\2\u00bf+\3\2\2\2\u00c0\u00c1"+
		"\7@\2\2\u00c1-\3\2\2\2\u00c2\u00c3\7>\2\2\u00c3/\3\2\2\2\u00c4\u00c5\7"+
		"c\2\2\u00c5\u00c6\7p\2\2\u00c6\u00c7\7f\2\2\u00c7\61\3\2\2\2\u00c8\u00c9"+
		"\7q\2\2\u00c9\u00ca\7t\2\2\u00ca\63\3\2\2\2\u00cb\u00cc\7p\2\2\u00cc\u00cd"+
		"\7q\2\2\u00cd\u00ce\7v\2\2\u00ce\65\3\2\2\2\u00cf\u00d0\7-\2\2\u00d0\u00d1"+
		"\7-\2\2\u00d1\67\3\2\2\2\u00d2\u00d3\7/\2\2\u00d3\u00d4\7/\2\2\u00d49"+
		"\3\2\2\2\u00d5\u00d6\7`\2\2\u00d6\u00d7\7`\2\2\u00d7;\3\2\2\2\u00d8\u00d9"+
		"\7-\2\2\u00d9=\3\2\2\2\u00da\u00db\7/\2\2\u00db?\3\2\2\2\u00dc\u00dd\7"+
		",\2\2\u00ddA\3\2\2\2\u00de\u00df\7\61\2\2\u00dfC\3\2\2\2\u00e0\u00e1\7"+
		"\'\2\2\u00e1E\3\2\2\2\u00e2\u00e3\7(\2\2\u00e3G\3\2\2\2\u00e4\u00e5\7"+
		"~\2\2\u00e5I\3\2\2\2\u00e6\u00e7\7`\2\2\u00e7K\3\2\2\2\u00e8\u00e9\7\u0080"+
		"\2\2\u00e9M\3\2\2\2\u00ea\u00eb\7?\2\2\u00ebO\3\2\2\2\u00ec\u00ed\7=\2"+
		"\2\u00edQ\3\2\2\2\u00ee\u00ef\7<\2\2\u00efS\3\2\2\2\u00f0\u00f1\7*\2\2"+
		"\u00f1U\3\2\2\2\u00f2\u00f3\7+\2\2\u00f3W\3\2\2\2\u00f4\u00f5\7]\2\2\u00f5"+
		"Y\3\2\2\2\u00f6\u00f7\7_\2\2\u00f7[\3\2\2\2\u00f8\u00f9\7.\2\2\u00f9]"+
		"\3\2\2\2\u00fa\u00fb\7$\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fd\b/\2\2\u00fd"+
		"_\3\2\2\2\u00fe\u00ff\7}\2\2\u00ff\u0100\3\2\2\2\u0100\u0101\b\60\3\2"+
		"\u0101a\3\2\2\2\u0102\u0103\7\177\2\2\u0103\u0104\3\2\2\2\u0104\u0105"+
		"\b\61\4\2\u0105c\3\2\2\2\u0106\u0108\5\b\4\2\u0107\u0106\3\2\2\2\u0108"+
		"\u0109\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010ae\3\2\2\2"+
		"\u010b\u010e\5\6\3\2\u010c\u010e\5\4\2\2\u010d\u010b\3\2\2\2\u010d\u010c"+
		"\3\2\2\2\u010e\u0112\3\2\2\2\u010f\u0111\t\5\2\2\u0110\u010f\3\2\2\2\u0111"+
		"\u0114\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113g\3\2\2\2"+
		"\u0114\u0112\3\2\2\2\u0115\u0117\t\6\2\2\u0116\u0115\3\2\2\2\u0117\u0118"+
		"\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\3\2\2\2\u011a"+
		"\u011b\b\64\5\2\u011bi\3\2\2\2\u011c\u011d\7&\2\2\u011d\u011e\7}\2\2\u011e"+
		"\u011f\3\2\2\2\u011f\u0120\b\65\3\2\u0120k\3\2\2\2\u0121\u0122\7$\2\2"+
		"\u0122\u0123\3\2\2\2\u0123\u0124\b\66\4\2\u0124m\3\2\2\2\u0125\u0127\13"+
		"\2\2\2\u0126\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0129\3\2\2\2\u0128"+
		"\u0126\3\2\2\2\u0129\u012d\3\2\2\2\u012a\u012b\7&\2\2\u012b\u012e\7}\2"+
		"\2\u012c\u012e\7$\2\2\u012d\u012a\3\2\2\2\u012d\u012c\3\2\2\2\u012eo\3"+
		"\2\2\2\n\2\3\u0109\u010d\u0112\u0118\u0128\u012d\6\7\3\2\7\2\2\6\2\2\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}