// Generated from C:/Users/fcspa/de/dsos/compiler/parser/src/main/antlr\OxidizerLexer.g4 by ANTLR 4.8
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
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		KW_PRIVATE=1, KW_DEF=2, KW_FOR=3, KW_IN=4, KW_WHILE=5, KW_IF=6, KW_ELIF=7, 
		KW_ELSE=8, KW_CLASS=9, KW_IMPORT=10, KW_THEN=11, KW_RETURN=12, KW_CASE=13, 
		KW_SWITCH=14, KW_BREAK=15, KW_CONTINUE=16, KW_SPAWN=17, KW_LISTEN=18, 
		KW_SEND=19, KW_THROW=20, KW_CATCH=21, KW_TRY=22, KW_FINALLY=23, KW_NEW=24, 
		OP_LSHIFT=25, OP_RSHIFT=26, OP_RSHIFT_UNSIGNED=27, COMP_EQ=28, COMP_GE=29, 
		COMP_LE=30, COMP_NE=31, COMP_G=32, COMP_L=33, OP_AND=34, OP_OR=35, OP_NOT=36, 
		OP_INC=37, OP_DEC=38, OP_POW=39, OP_PLUS=40, OP_MINUS=41, OP_MUL=42, OP_DIV=43, 
		OP_MOD=44, OP_BAND=45, OP_BOR=46, OP_XOR=47, OP_COMPLEMENT=48, OP_ASSIGN=49, 
		SEMI=50, COLON=51, LPAREN=52, RPAREN=53, LSQUARE=54, RSQUARE=55, COMMA=56, 
		ENTER_STRING=57, LBRACE=58, RBRACE=59, INTEGER=60, FLOAT=61, NAME=62, 
		WS=63, ENTER_STR_EXPR=64, END_STRING=65, TEXT=66;
	public static final int
		STRING=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "STRING"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"UPPERCASE", "LOWERCASE", "DIGIT", "KW_PRIVATE", "KW_DEF", "KW_FOR", 
			"KW_IN", "KW_WHILE", "KW_IF", "KW_ELIF", "KW_ELSE", "KW_CLASS", "KW_IMPORT", 
			"KW_THEN", "KW_RETURN", "KW_CASE", "KW_SWITCH", "KW_BREAK", "KW_CONTINUE", 
			"KW_SPAWN", "KW_LISTEN", "KW_SEND", "KW_THROW", "KW_CATCH", "KW_TRY", 
			"KW_FINALLY", "KW_NEW", "OP_LSHIFT", "OP_RSHIFT", "OP_RSHIFT_UNSIGNED", 
			"COMP_EQ", "COMP_GE", "COMP_LE", "COMP_NE", "COMP_G", "COMP_L", "OP_AND", 
			"OP_OR", "OP_NOT", "OP_INC", "OP_DEC", "OP_POW", "OP_PLUS", "OP_MINUS", 
			"OP_MUL", "OP_DIV", "OP_MOD", "OP_BAND", "OP_BOR", "OP_XOR", "OP_COMPLEMENT", 
			"OP_ASSIGN", "SEMI", "COLON", "LPAREN", "RPAREN", "LSQUARE", "RSQUARE", 
			"COMMA", "ENTER_STRING", "LBRACE", "RBRACE", "INTEGER", "FLOAT", "NAME", 
			"WS", "ENTER_STR_EXPR", "END_STRING", "TEXT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'private'", "'def'", "'for'", "'in'", "'while'", "'if'", "'elif'", 
			"'else'", "'class'", "'import'", "'then'", "'return'", "'case'", "'switch'", 
			"'break'", "'continue'", "'spwan'", "'listen'", "'send'", "'throw'", 
			"'catch'", "'try'", "'finally'", "'new'", "'<<'", "'>>'", "'>>>'", "'=='", 
			"'>='", "'<='", "'!='", "'>'", "'<'", "'and'", "'or'", "'not'", "'++'", 
			"'--'", "'^^'", "'+'", "'-'", "'*'", "'/'", "'%'", "'&'", "'|'", "'^'", 
			"'~'", "'='", "';'", "':'", "'('", "')'", "'['", "']'", "','", null, 
			"'{'", "'}'", null, null, null, null, "'${'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "KW_PRIVATE", "KW_DEF", "KW_FOR", "KW_IN", "KW_WHILE", "KW_IF", 
			"KW_ELIF", "KW_ELSE", "KW_CLASS", "KW_IMPORT", "KW_THEN", "KW_RETURN", 
			"KW_CASE", "KW_SWITCH", "KW_BREAK", "KW_CONTINUE", "KW_SPAWN", "KW_LISTEN", 
			"KW_SEND", "KW_THROW", "KW_CATCH", "KW_TRY", "KW_FINALLY", "KW_NEW", 
			"OP_LSHIFT", "OP_RSHIFT", "OP_RSHIFT_UNSIGNED", "COMP_EQ", "COMP_GE", 
			"COMP_LE", "COMP_NE", "COMP_G", "COMP_L", "OP_AND", "OP_OR", "OP_NOT", 
			"OP_INC", "OP_DEC", "OP_POW", "OP_PLUS", "OP_MINUS", "OP_MUL", "OP_DIV", 
			"OP_MOD", "OP_BAND", "OP_BOR", "OP_XOR", "OP_COMPLEMENT", "OP_ASSIGN", 
			"SEMI", "COLON", "LPAREN", "RPAREN", "LSQUARE", "RSQUARE", "COMMA", "ENTER_STRING", 
			"LBRACE", "RBRACE", "INTEGER", "FLOAT", "NAME", "WS", "ENTER_STR_EXPR", 
			"END_STRING", "TEXT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2D\u01ae\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3"+
		"\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3%\3%\3&\3&\3&\3&\3\'"+
		"\3\'\3\'\3(\3(\3(\3(\3)\3)\3)\3*\3*\3*\3+\3+\3+\3,\3,\3-\3-\3.\3.\3/\3"+
		"/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66"+
		"\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3=\3=\3>\3>\3>\3>\3?\3"+
		"?\3?\3?\3@\6@\u017a\n@\r@\16@\u017b\3A\6A\u017f\nA\rA\16A\u0180\3A\3A"+
		"\7A\u0185\nA\fA\16A\u0188\13A\3A\5A\u018b\nA\3A\5A\u018e\nA\3B\3B\5B\u0192"+
		"\nB\3B\7B\u0195\nB\fB\16B\u0198\13B\3C\6C\u019b\nC\rC\16C\u019c\3C\3C"+
		"\3D\3D\3D\3D\3D\3E\3E\3E\3E\3F\6F\u01ab\nF\rF\16F\u01ac\2\2G\4\2\6\2\b"+
		"\2\n\3\f\4\16\5\20\6\22\7\24\b\26\t\30\n\32\13\34\f\36\r \16\"\17$\20"+
		"&\21(\22*\23,\24.\25\60\26\62\27\64\30\66\318\32:\33<\34>\35@\36B\37D"+
		" F!H\"J#L$N%P&R\'T(V)X*Z+\\,^-`.b/d\60f\61h\62j\63l\64n\65p\66r\67t8v"+
		"9x:z;|<~=\u0080>\u0082?\u0084@\u0086A\u0088B\u008aC\u008cD\4\2\3\b\3\2"+
		"C\\\3\2c|\3\2\62;\7\2\60\60\62;C\\aac|\5\2\13\f\17\17\"\"\5\2\f\f$$&&"+
		"\2\u01b2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2"+
		"\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32\3\2\2\2\2\34\3\2\2\2\2"+
		"\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\2&\3\2\2\2\2(\3\2\2\2\2"+
		"*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2\2\2\62\3\2\2\2\2\64\3\2\2\2"+
		"\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\2<\3\2\2\2\2>\3\2\2\2\2@\3\2\2\2\2"+
		"B\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\2H\3\2\2\2\2J\3\2\2\2\2L\3\2\2\2\2N\3"+
		"\2\2\2\2P\3\2\2\2\2R\3\2\2\2\2T\3\2\2\2\2V\3\2\2\2\2X\3\2\2\2\2Z\3\2\2"+
		"\2\2\\\3\2\2\2\2^\3\2\2\2\2`\3\2\2\2\2b\3\2\2\2\2d\3\2\2\2\2f\3\2\2\2"+
		"\2h\3\2\2\2\2j\3\2\2\2\2l\3\2\2\2\2n\3\2\2\2\2p\3\2\2\2\2r\3\2\2\2\2t"+
		"\3\2\2\2\2v\3\2\2\2\2x\3\2\2\2\2z\3\2\2\2\2|\3\2\2\2\2~\3\2\2\2\2\u0080"+
		"\3\2\2\2\2\u0082\3\2\2\2\2\u0084\3\2\2\2\2\u0086\3\2\2\2\3\u0088\3\2\2"+
		"\2\3\u008a\3\2\2\2\3\u008c\3\2\2\2\4\u008e\3\2\2\2\6\u0090\3\2\2\2\b\u0092"+
		"\3\2\2\2\n\u0094\3\2\2\2\f\u009c\3\2\2\2\16\u00a0\3\2\2\2\20\u00a4\3\2"+
		"\2\2\22\u00a7\3\2\2\2\24\u00ad\3\2\2\2\26\u00b0\3\2\2\2\30\u00b5\3\2\2"+
		"\2\32\u00ba\3\2\2\2\34\u00c0\3\2\2\2\36\u00c7\3\2\2\2 \u00cc\3\2\2\2\""+
		"\u00d3\3\2\2\2$\u00d8\3\2\2\2&\u00df\3\2\2\2(\u00e5\3\2\2\2*\u00ee\3\2"+
		"\2\2,\u00f4\3\2\2\2.\u00fb\3\2\2\2\60\u0100\3\2\2\2\62\u0106\3\2\2\2\64"+
		"\u010c\3\2\2\2\66\u0110\3\2\2\28\u0118\3\2\2\2:\u011c\3\2\2\2<\u011f\3"+
		"\2\2\2>\u0122\3\2\2\2@\u0126\3\2\2\2B\u0129\3\2\2\2D\u012c\3\2\2\2F\u012f"+
		"\3\2\2\2H\u0132\3\2\2\2J\u0134\3\2\2\2L\u0136\3\2\2\2N\u013a\3\2\2\2P"+
		"\u013d\3\2\2\2R\u0141\3\2\2\2T\u0144\3\2\2\2V\u0147\3\2\2\2X\u014a\3\2"+
		"\2\2Z\u014c\3\2\2\2\\\u014e\3\2\2\2^\u0150\3\2\2\2`\u0152\3\2\2\2b\u0154"+
		"\3\2\2\2d\u0156\3\2\2\2f\u0158\3\2\2\2h\u015a\3\2\2\2j\u015c\3\2\2\2l"+
		"\u015e\3\2\2\2n\u0160\3\2\2\2p\u0162\3\2\2\2r\u0164\3\2\2\2t\u0166\3\2"+
		"\2\2v\u0168\3\2\2\2x\u016a\3\2\2\2z\u016c\3\2\2\2|\u0170\3\2\2\2~\u0174"+
		"\3\2\2\2\u0080\u0179\3\2\2\2\u0082\u017e\3\2\2\2\u0084\u0191\3\2\2\2\u0086"+
		"\u019a\3\2\2\2\u0088\u01a0\3\2\2\2\u008a\u01a5\3\2\2\2\u008c\u01aa\3\2"+
		"\2\2\u008e\u008f\t\2\2\2\u008f\5\3\2\2\2\u0090\u0091\t\3\2\2\u0091\7\3"+
		"\2\2\2\u0092\u0093\t\4\2\2\u0093\t\3\2\2\2\u0094\u0095\7r\2\2\u0095\u0096"+
		"\7t\2\2\u0096\u0097\7k\2\2\u0097\u0098\7x\2\2\u0098\u0099\7c\2\2\u0099"+
		"\u009a\7v\2\2\u009a\u009b\7g\2\2\u009b\13\3\2\2\2\u009c\u009d\7f\2\2\u009d"+
		"\u009e\7g\2\2\u009e\u009f\7h\2\2\u009f\r\3\2\2\2\u00a0\u00a1\7h\2\2\u00a1"+
		"\u00a2\7q\2\2\u00a2\u00a3\7t\2\2\u00a3\17\3\2\2\2\u00a4\u00a5\7k\2\2\u00a5"+
		"\u00a6\7p\2\2\u00a6\21\3\2\2\2\u00a7\u00a8\7y\2\2\u00a8\u00a9\7j\2\2\u00a9"+
		"\u00aa\7k\2\2\u00aa\u00ab\7n\2\2\u00ab\u00ac\7g\2\2\u00ac\23\3\2\2\2\u00ad"+
		"\u00ae\7k\2\2\u00ae\u00af\7h\2\2\u00af\25\3\2\2\2\u00b0\u00b1\7g\2\2\u00b1"+
		"\u00b2\7n\2\2\u00b2\u00b3\7k\2\2\u00b3\u00b4\7h\2\2\u00b4\27\3\2\2\2\u00b5"+
		"\u00b6\7g\2\2\u00b6\u00b7\7n\2\2\u00b7\u00b8\7u\2\2\u00b8\u00b9\7g\2\2"+
		"\u00b9\31\3\2\2\2\u00ba\u00bb\7e\2\2\u00bb\u00bc\7n\2\2\u00bc\u00bd\7"+
		"c\2\2\u00bd\u00be\7u\2\2\u00be\u00bf\7u\2\2\u00bf\33\3\2\2\2\u00c0\u00c1"+
		"\7k\2\2\u00c1\u00c2\7o\2\2\u00c2\u00c3\7r\2\2\u00c3\u00c4\7q\2\2\u00c4"+
		"\u00c5\7t\2\2\u00c5\u00c6\7v\2\2\u00c6\35\3\2\2\2\u00c7\u00c8\7v\2\2\u00c8"+
		"\u00c9\7j\2\2\u00c9\u00ca\7g\2\2\u00ca\u00cb\7p\2\2\u00cb\37\3\2\2\2\u00cc"+
		"\u00cd\7t\2\2\u00cd\u00ce\7g\2\2\u00ce\u00cf\7v\2\2\u00cf\u00d0\7w\2\2"+
		"\u00d0\u00d1\7t\2\2\u00d1\u00d2\7p\2\2\u00d2!\3\2\2\2\u00d3\u00d4\7e\2"+
		"\2\u00d4\u00d5\7c\2\2\u00d5\u00d6\7u\2\2\u00d6\u00d7\7g\2\2\u00d7#\3\2"+
		"\2\2\u00d8\u00d9\7u\2\2\u00d9\u00da\7y\2\2\u00da\u00db\7k\2\2\u00db\u00dc"+
		"\7v\2\2\u00dc\u00dd\7e\2\2\u00dd\u00de\7j\2\2\u00de%\3\2\2\2\u00df\u00e0"+
		"\7d\2\2\u00e0\u00e1\7t\2\2\u00e1\u00e2\7g\2\2\u00e2\u00e3\7c\2\2\u00e3"+
		"\u00e4\7m\2\2\u00e4\'\3\2\2\2\u00e5\u00e6\7e\2\2\u00e6\u00e7\7q\2\2\u00e7"+
		"\u00e8\7p\2\2\u00e8\u00e9\7v\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7p\2\2"+
		"\u00eb\u00ec\7w\2\2\u00ec\u00ed\7g\2\2\u00ed)\3\2\2\2\u00ee\u00ef\7u\2"+
		"\2\u00ef\u00f0\7r\2\2\u00f0\u00f1\7y\2\2\u00f1\u00f2\7c\2\2\u00f2\u00f3"+
		"\7p\2\2\u00f3+\3\2\2\2\u00f4\u00f5\7n\2\2\u00f5\u00f6\7k\2\2\u00f6\u00f7"+
		"\7u\2\2\u00f7\u00f8\7v\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7p\2\2\u00fa"+
		"-\3\2\2\2\u00fb\u00fc\7u\2\2\u00fc\u00fd\7g\2\2\u00fd\u00fe\7p\2\2\u00fe"+
		"\u00ff\7f\2\2\u00ff/\3\2\2\2\u0100\u0101\7v\2\2\u0101\u0102\7j\2\2\u0102"+
		"\u0103\7t\2\2\u0103\u0104\7q\2\2\u0104\u0105\7y\2\2\u0105\61\3\2\2\2\u0106"+
		"\u0107\7e\2\2\u0107\u0108\7c\2\2\u0108\u0109\7v\2\2\u0109\u010a\7e\2\2"+
		"\u010a\u010b\7j\2\2\u010b\63\3\2\2\2\u010c\u010d\7v\2\2\u010d\u010e\7"+
		"t\2\2\u010e\u010f\7{\2\2\u010f\65\3\2\2\2\u0110\u0111\7h\2\2\u0111\u0112"+
		"\7k\2\2\u0112\u0113\7p\2\2\u0113\u0114\7c\2\2\u0114\u0115\7n\2\2\u0115"+
		"\u0116\7n\2\2\u0116\u0117\7{\2\2\u0117\67\3\2\2\2\u0118\u0119\7p\2\2\u0119"+
		"\u011a\7g\2\2\u011a\u011b\7y\2\2\u011b9\3\2\2\2\u011c\u011d\7>\2\2\u011d"+
		"\u011e\7>\2\2\u011e;\3\2\2\2\u011f\u0120\7@\2\2\u0120\u0121\7@\2\2\u0121"+
		"=\3\2\2\2\u0122\u0123\7@\2\2\u0123\u0124\7@\2\2\u0124\u0125\7@\2\2\u0125"+
		"?\3\2\2\2\u0126\u0127\7?\2\2\u0127\u0128\7?\2\2\u0128A\3\2\2\2\u0129\u012a"+
		"\7@\2\2\u012a\u012b\7?\2\2\u012bC\3\2\2\2\u012c\u012d\7>\2\2\u012d\u012e"+
		"\7?\2\2\u012eE\3\2\2\2\u012f\u0130\7#\2\2\u0130\u0131\7?\2\2\u0131G\3"+
		"\2\2\2\u0132\u0133\7@\2\2\u0133I\3\2\2\2\u0134\u0135\7>\2\2\u0135K\3\2"+
		"\2\2\u0136\u0137\7c\2\2\u0137\u0138\7p\2\2\u0138\u0139\7f\2\2\u0139M\3"+
		"\2\2\2\u013a\u013b\7q\2\2\u013b\u013c\7t\2\2\u013cO\3\2\2\2\u013d\u013e"+
		"\7p\2\2\u013e\u013f\7q\2\2\u013f\u0140\7v\2\2\u0140Q\3\2\2\2\u0141\u0142"+
		"\7-\2\2\u0142\u0143\7-\2\2\u0143S\3\2\2\2\u0144\u0145\7/\2\2\u0145\u0146"+
		"\7/\2\2\u0146U\3\2\2\2\u0147\u0148\7`\2\2\u0148\u0149\7`\2\2\u0149W\3"+
		"\2\2\2\u014a\u014b\7-\2\2\u014bY\3\2\2\2\u014c\u014d\7/\2\2\u014d[\3\2"+
		"\2\2\u014e\u014f\7,\2\2\u014f]\3\2\2\2\u0150\u0151\7\61\2\2\u0151_\3\2"+
		"\2\2\u0152\u0153\7\'\2\2\u0153a\3\2\2\2\u0154\u0155\7(\2\2\u0155c\3\2"+
		"\2\2\u0156\u0157\7~\2\2\u0157e\3\2\2\2\u0158\u0159\7`\2\2\u0159g\3\2\2"+
		"\2\u015a\u015b\7\u0080\2\2\u015bi\3\2\2\2\u015c\u015d\7?\2\2\u015dk\3"+
		"\2\2\2\u015e\u015f\7=\2\2\u015fm\3\2\2\2\u0160\u0161\7<\2\2\u0161o\3\2"+
		"\2\2\u0162\u0163\7*\2\2\u0163q\3\2\2\2\u0164\u0165\7+\2\2\u0165s\3\2\2"+
		"\2\u0166\u0167\7]\2\2\u0167u\3\2\2\2\u0168\u0169\7_\2\2\u0169w\3\2\2\2"+
		"\u016a\u016b\7.\2\2\u016by\3\2\2\2\u016c\u016d\7$\2\2\u016d\u016e\3\2"+
		"\2\2\u016e\u016f\b=\2\2\u016f{\3\2\2\2\u0170\u0171\7}\2\2\u0171\u0172"+
		"\3\2\2\2\u0172\u0173\b>\3\2\u0173}\3\2\2\2\u0174\u0175\7\177\2\2\u0175"+
		"\u0176\3\2\2\2\u0176\u0177\b?\4\2\u0177\177\3\2\2\2\u0178\u017a\5\b\4"+
		"\2\u0179\u0178\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u0179\3\2\2\2\u017b\u017c"+
		"\3\2\2\2\u017c\u0081\3\2\2\2\u017d\u017f\5\b\4\2\u017e\u017d\3\2\2\2\u017f"+
		"\u0180\3\2\2\2\u0180\u017e\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u018d\3\2"+
		"\2\2\u0182\u0186\7\60\2\2\u0183\u0185\5\b\4\2\u0184\u0183\3\2\2\2\u0185"+
		"\u0188\3\2\2\2\u0186\u0184\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u018a\3\2"+
		"\2\2\u0188\u0186\3\2\2\2\u0189\u018b\7h\2\2\u018a\u0189\3\2\2\2\u018a"+
		"\u018b\3\2\2\2\u018b\u018e\3\2\2\2\u018c\u018e\7h\2\2\u018d\u0182\3\2"+
		"\2\2\u018d\u018c\3\2\2\2\u018e\u0083\3\2\2\2\u018f\u0192\5\6\3\2\u0190"+
		"\u0192\5\4\2\2\u0191\u018f\3\2\2\2\u0191\u0190\3\2\2\2\u0192\u0196\3\2"+
		"\2\2\u0193\u0195\t\5\2\2\u0194\u0193\3\2\2\2\u0195\u0198\3\2\2\2\u0196"+
		"\u0194\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0085\3\2\2\2\u0198\u0196\3\2"+
		"\2\2\u0199\u019b\t\6\2\2\u019a\u0199\3\2\2\2\u019b\u019c\3\2\2\2\u019c"+
		"\u019a\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u019f\bC"+
		"\5\2\u019f\u0087\3\2\2\2\u01a0\u01a1\7&\2\2\u01a1\u01a2\7}\2\2\u01a2\u01a3"+
		"\3\2\2\2\u01a3\u01a4\bD\3\2\u01a4\u0089\3\2\2\2\u01a5\u01a6\7$\2\2\u01a6"+
		"\u01a7\3\2\2\2\u01a7\u01a8\bE\4\2\u01a8\u008b\3\2\2\2\u01a9\u01ab\n\7"+
		"\2\2\u01aa\u01a9\3\2\2\2\u01ab\u01ac\3\2\2\2\u01ac\u01aa\3\2\2\2\u01ac"+
		"\u01ad\3\2\2\2\u01ad\u008d\3\2\2\2\r\2\3\u017b\u0180\u0186\u018a\u018d"+
		"\u0191\u0196\u019c\u01ac\6\7\3\2\7\2\2\6\2\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}