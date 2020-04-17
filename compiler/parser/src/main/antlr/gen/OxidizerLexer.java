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
		KW_TRUE=25, KW_FALSE=26, OP_LSHIFT=27, OP_RSHIFT=28, OP_RSHIFT_UNSIGNED=29, 
		COMP_EQ=30, COMP_GE=31, COMP_LE=32, COMP_NE=33, COMP_G=34, COMP_L=35, 
		OP_AND=36, OP_OR=37, OP_NOT=38, OP_INC=39, OP_DEC=40, OP_POW=41, OP_PLUS=42, 
		OP_MINUS=43, OP_MUL=44, OP_DIV=45, OP_MOD=46, OP_BAND=47, OP_BOR=48, OP_XOR=49, 
		OP_COMPLEMENT=50, OP_ASSIGN=51, SEMI=52, COLON=53, LPAREN=54, RPAREN=55, 
		LSQUARE=56, RSQUARE=57, COMMA=58, ENTER_STRING=59, LBRACE=60, RBRACE=61, 
		INTEGER=62, FLOAT=63, NAME=64, COMMENT_LINE=65, WS=66, ENTER_STR_EXPR=67, 
		END_STRING=68, TEXT=69;
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
			"KW_FINALLY", "KW_NEW", "KW_TRUE", "KW_FALSE", "OP_LSHIFT", "OP_RSHIFT", 
			"OP_RSHIFT_UNSIGNED", "COMP_EQ", "COMP_GE", "COMP_LE", "COMP_NE", "COMP_G", 
			"COMP_L", "OP_AND", "OP_OR", "OP_NOT", "OP_INC", "OP_DEC", "OP_POW", 
			"OP_PLUS", "OP_MINUS", "OP_MUL", "OP_DIV", "OP_MOD", "OP_BAND", "OP_BOR", 
			"OP_XOR", "OP_COMPLEMENT", "OP_ASSIGN", "SEMI", "COLON", "LPAREN", "RPAREN", 
			"LSQUARE", "RSQUARE", "COMMA", "ENTER_STRING", "LBRACE", "RBRACE", "INTEGER", 
			"FLOAT", "NAME", "COMMENT_LINE", "WS", "ENTER_STR_EXPR", "END_STRING", 
			"TEXT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'private'", "'def'", "'for'", "'in'", "'while'", "'if'", "'elif'", 
			"'else'", "'class'", "'import'", "'then'", "'return'", "'case'", "'switch'", 
			"'break'", "'continue'", "'spawn'", "'listen'", "'send'", "'throw'", 
			"'catch'", "'try'", "'finally'", "'new'", "'true'", "'false'", "'<<'", 
			"'>>'", "'>>>'", "'=='", "'>='", "'<='", "'!='", "'>'", "'<'", "'and'", 
			"'or'", "'not'", "'++'", "'--'", "'^^'", "'+'", "'-'", "'*'", "'/'", 
			"'%'", "'&'", "'|'", "'^'", "'~'", "'='", "';'", "':'", "'('", "')'", 
			"'['", "']'", "','", null, "'{'", "'}'", null, null, null, null, null, 
			"'${'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "KW_PRIVATE", "KW_DEF", "KW_FOR", "KW_IN", "KW_WHILE", "KW_IF", 
			"KW_ELIF", "KW_ELSE", "KW_CLASS", "KW_IMPORT", "KW_THEN", "KW_RETURN", 
			"KW_CASE", "KW_SWITCH", "KW_BREAK", "KW_CONTINUE", "KW_SPAWN", "KW_LISTEN", 
			"KW_SEND", "KW_THROW", "KW_CATCH", "KW_TRY", "KW_FINALLY", "KW_NEW", 
			"KW_TRUE", "KW_FALSE", "OP_LSHIFT", "OP_RSHIFT", "OP_RSHIFT_UNSIGNED", 
			"COMP_EQ", "COMP_GE", "COMP_LE", "COMP_NE", "COMP_G", "COMP_L", "OP_AND", 
			"OP_OR", "OP_NOT", "OP_INC", "OP_DEC", "OP_POW", "OP_PLUS", "OP_MINUS", 
			"OP_MUL", "OP_DIV", "OP_MOD", "OP_BAND", "OP_BOR", "OP_XOR", "OP_COMPLEMENT", 
			"OP_ASSIGN", "SEMI", "COLON", "LPAREN", "RPAREN", "LSQUARE", "RSQUARE", 
			"COMMA", "ENTER_STRING", "LBRACE", "RBRACE", "INTEGER", "FLOAT", "NAME", 
			"COMMENT_LINE", "WS", "ENTER_STR_EXPR", "END_STRING", "TEXT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2G\u01ca\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4"+
		"I\tI\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3!\3\"\3\""+
		"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3(\3)\3)\3)\3*"+
		"\3*\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3"+
		"\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\3"+
		"9\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3?\3?\3@\3@\3@\3@\3A\3A\3A\3A\3"+
		"B\6B\u018b\nB\rB\16B\u018c\3C\6C\u0190\nC\rC\16C\u0191\3C\3C\7C\u0196"+
		"\nC\fC\16C\u0199\13C\3C\5C\u019c\nC\3C\5C\u019f\nC\3D\3D\5D\u01a3\nD\3"+
		"D\7D\u01a6\nD\fD\16D\u01a9\13D\3E\3E\3E\3E\7E\u01af\nE\fE\16E\u01b2\13"+
		"E\3E\3E\3F\6F\u01b7\nF\rF\16F\u01b8\3F\3F\3G\3G\3G\3G\3G\3H\3H\3H\3H\3"+
		"I\6I\u01c7\nI\rI\16I\u01c8\2\2J\4\2\6\2\b\2\n\3\f\4\16\5\20\6\22\7\24"+
		"\b\26\t\30\n\32\13\34\f\36\r \16\"\17$\20&\21(\22*\23,\24.\25\60\26\62"+
		"\27\64\30\66\318\32:\33<\34>\35@\36B\37D F!H\"J#L$N%P&R\'T(V)X*Z+\\,^"+
		"-`.b/d\60f\61h\62j\63l\64n\65p\66r\67t8v9x:z;|<~=\u0080>\u0082?\u0084"+
		"@\u0086A\u0088B\u008aC\u008cD\u008eE\u0090F\u0092G\4\2\3\t\3\2C\\\3\2"+
		"c|\3\2\62;\7\2\60\60\62;C\\aac|\4\2\f\f\17\17\5\2\13\f\17\17\"\"\5\2\f"+
		"\f$$&&\2\u01cf\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22"+
		"\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32\3\2\2\2\2\34\3\2"+
		"\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\2&\3\2\2\2\2(\3\2"+
		"\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2\2\2\62\3\2\2\2\2\64\3"+
		"\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\2<\3\2\2\2\2>\3\2\2\2\2@\3\2"+
		"\2\2\2B\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\2H\3\2\2\2\2J\3\2\2\2\2L\3\2\2\2"+
		"\2N\3\2\2\2\2P\3\2\2\2\2R\3\2\2\2\2T\3\2\2\2\2V\3\2\2\2\2X\3\2\2\2\2Z"+
		"\3\2\2\2\2\\\3\2\2\2\2^\3\2\2\2\2`\3\2\2\2\2b\3\2\2\2\2d\3\2\2\2\2f\3"+
		"\2\2\2\2h\3\2\2\2\2j\3\2\2\2\2l\3\2\2\2\2n\3\2\2\2\2p\3\2\2\2\2r\3\2\2"+
		"\2\2t\3\2\2\2\2v\3\2\2\2\2x\3\2\2\2\2z\3\2\2\2\2|\3\2\2\2\2~\3\2\2\2\2"+
		"\u0080\3\2\2\2\2\u0082\3\2\2\2\2\u0084\3\2\2\2\2\u0086\3\2\2\2\2\u0088"+
		"\3\2\2\2\2\u008a\3\2\2\2\2\u008c\3\2\2\2\3\u008e\3\2\2\2\3\u0090\3\2\2"+
		"\2\3\u0092\3\2\2\2\4\u0094\3\2\2\2\6\u0096\3\2\2\2\b\u0098\3\2\2\2\n\u009a"+
		"\3\2\2\2\f\u00a2\3\2\2\2\16\u00a6\3\2\2\2\20\u00aa\3\2\2\2\22\u00ad\3"+
		"\2\2\2\24\u00b3\3\2\2\2\26\u00b6\3\2\2\2\30\u00bb\3\2\2\2\32\u00c0\3\2"+
		"\2\2\34\u00c6\3\2\2\2\36\u00cd\3\2\2\2 \u00d2\3\2\2\2\"\u00d9\3\2\2\2"+
		"$\u00de\3\2\2\2&\u00e5\3\2\2\2(\u00eb\3\2\2\2*\u00f4\3\2\2\2,\u00fa\3"+
		"\2\2\2.\u0101\3\2\2\2\60\u0106\3\2\2\2\62\u010c\3\2\2\2\64\u0112\3\2\2"+
		"\2\66\u0116\3\2\2\28\u011e\3\2\2\2:\u0122\3\2\2\2<\u0127\3\2\2\2>\u012d"+
		"\3\2\2\2@\u0130\3\2\2\2B\u0133\3\2\2\2D\u0137\3\2\2\2F\u013a\3\2\2\2H"+
		"\u013d\3\2\2\2J\u0140\3\2\2\2L\u0143\3\2\2\2N\u0145\3\2\2\2P\u0147\3\2"+
		"\2\2R\u014b\3\2\2\2T\u014e\3\2\2\2V\u0152\3\2\2\2X\u0155\3\2\2\2Z\u0158"+
		"\3\2\2\2\\\u015b\3\2\2\2^\u015d\3\2\2\2`\u015f\3\2\2\2b\u0161\3\2\2\2"+
		"d\u0163\3\2\2\2f\u0165\3\2\2\2h\u0167\3\2\2\2j\u0169\3\2\2\2l\u016b\3"+
		"\2\2\2n\u016d\3\2\2\2p\u016f\3\2\2\2r\u0171\3\2\2\2t\u0173\3\2\2\2v\u0175"+
		"\3\2\2\2x\u0177\3\2\2\2z\u0179\3\2\2\2|\u017b\3\2\2\2~\u017d\3\2\2\2\u0080"+
		"\u0181\3\2\2\2\u0082\u0185\3\2\2\2\u0084\u018a\3\2\2\2\u0086\u018f\3\2"+
		"\2\2\u0088\u01a2\3\2\2\2\u008a\u01aa\3\2\2\2\u008c\u01b6\3\2\2\2\u008e"+
		"\u01bc\3\2\2\2\u0090\u01c1\3\2\2\2\u0092\u01c6\3\2\2\2\u0094\u0095\t\2"+
		"\2\2\u0095\5\3\2\2\2\u0096\u0097\t\3\2\2\u0097\7\3\2\2\2\u0098\u0099\t"+
		"\4\2\2\u0099\t\3\2\2\2\u009a\u009b\7r\2\2\u009b\u009c\7t\2\2\u009c\u009d"+
		"\7k\2\2\u009d\u009e\7x\2\2\u009e\u009f\7c\2\2\u009f\u00a0\7v\2\2\u00a0"+
		"\u00a1\7g\2\2\u00a1\13\3\2\2\2\u00a2\u00a3\7f\2\2\u00a3\u00a4\7g\2\2\u00a4"+
		"\u00a5\7h\2\2\u00a5\r\3\2\2\2\u00a6\u00a7\7h\2\2\u00a7\u00a8\7q\2\2\u00a8"+
		"\u00a9\7t\2\2\u00a9\17\3\2\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7p\2\2\u00ac"+
		"\21\3\2\2\2\u00ad\u00ae\7y\2\2\u00ae\u00af\7j\2\2\u00af\u00b0\7k\2\2\u00b0"+
		"\u00b1\7n\2\2\u00b1\u00b2\7g\2\2\u00b2\23\3\2\2\2\u00b3\u00b4\7k\2\2\u00b4"+
		"\u00b5\7h\2\2\u00b5\25\3\2\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7n\2\2\u00b8"+
		"\u00b9\7k\2\2\u00b9\u00ba\7h\2\2\u00ba\27\3\2\2\2\u00bb\u00bc\7g\2\2\u00bc"+
		"\u00bd\7n\2\2\u00bd\u00be\7u\2\2\u00be\u00bf\7g\2\2\u00bf\31\3\2\2\2\u00c0"+
		"\u00c1\7e\2\2\u00c1\u00c2\7n\2\2\u00c2\u00c3\7c\2\2\u00c3\u00c4\7u\2\2"+
		"\u00c4\u00c5\7u\2\2\u00c5\33\3\2\2\2\u00c6\u00c7\7k\2\2\u00c7\u00c8\7"+
		"o\2\2\u00c8\u00c9\7r\2\2\u00c9\u00ca\7q\2\2\u00ca\u00cb\7t\2\2\u00cb\u00cc"+
		"\7v\2\2\u00cc\35\3\2\2\2\u00cd\u00ce\7v\2\2\u00ce\u00cf\7j\2\2\u00cf\u00d0"+
		"\7g\2\2\u00d0\u00d1\7p\2\2\u00d1\37\3\2\2\2\u00d2\u00d3\7t\2\2\u00d3\u00d4"+
		"\7g\2\2\u00d4\u00d5\7v\2\2\u00d5\u00d6\7w\2\2\u00d6\u00d7\7t\2\2\u00d7"+
		"\u00d8\7p\2\2\u00d8!\3\2\2\2\u00d9\u00da\7e\2\2\u00da\u00db\7c\2\2\u00db"+
		"\u00dc\7u\2\2\u00dc\u00dd\7g\2\2\u00dd#\3\2\2\2\u00de\u00df\7u\2\2\u00df"+
		"\u00e0\7y\2\2\u00e0\u00e1\7k\2\2\u00e1\u00e2\7v\2\2\u00e2\u00e3\7e\2\2"+
		"\u00e3\u00e4\7j\2\2\u00e4%\3\2\2\2\u00e5\u00e6\7d\2\2\u00e6\u00e7\7t\2"+
		"\2\u00e7\u00e8\7g\2\2\u00e8\u00e9\7c\2\2\u00e9\u00ea\7m\2\2\u00ea\'\3"+
		"\2\2\2\u00eb\u00ec\7e\2\2\u00ec\u00ed\7q\2\2\u00ed\u00ee\7p\2\2\u00ee"+
		"\u00ef\7v\2\2\u00ef\u00f0\7k\2\2\u00f0\u00f1\7p\2\2\u00f1\u00f2\7w\2\2"+
		"\u00f2\u00f3\7g\2\2\u00f3)\3\2\2\2\u00f4\u00f5\7u\2\2\u00f5\u00f6\7r\2"+
		"\2\u00f6\u00f7\7c\2\2\u00f7\u00f8\7y\2\2\u00f8\u00f9\7p\2\2\u00f9+\3\2"+
		"\2\2\u00fa\u00fb\7n\2\2\u00fb\u00fc\7k\2\2\u00fc\u00fd\7u\2\2\u00fd\u00fe"+
		"\7v\2\2\u00fe\u00ff\7g\2\2\u00ff\u0100\7p\2\2\u0100-\3\2\2\2\u0101\u0102"+
		"\7u\2\2\u0102\u0103\7g\2\2\u0103\u0104\7p\2\2\u0104\u0105\7f\2\2\u0105"+
		"/\3\2\2\2\u0106\u0107\7v\2\2\u0107\u0108\7j\2\2\u0108\u0109\7t\2\2\u0109"+
		"\u010a\7q\2\2\u010a\u010b\7y\2\2\u010b\61\3\2\2\2\u010c\u010d\7e\2\2\u010d"+
		"\u010e\7c\2\2\u010e\u010f\7v\2\2\u010f\u0110\7e\2\2\u0110\u0111\7j\2\2"+
		"\u0111\63\3\2\2\2\u0112\u0113\7v\2\2\u0113\u0114\7t\2\2\u0114\u0115\7"+
		"{\2\2\u0115\65\3\2\2\2\u0116\u0117\7h\2\2\u0117\u0118\7k\2\2\u0118\u0119"+
		"\7p\2\2\u0119\u011a\7c\2\2\u011a\u011b\7n\2\2\u011b\u011c\7n\2\2\u011c"+
		"\u011d\7{\2\2\u011d\67\3\2\2\2\u011e\u011f\7p\2\2\u011f\u0120\7g\2\2\u0120"+
		"\u0121\7y\2\2\u01219\3\2\2\2\u0122\u0123\7v\2\2\u0123\u0124\7t\2\2\u0124"+
		"\u0125\7w\2\2\u0125\u0126\7g\2\2\u0126;\3\2\2\2\u0127\u0128\7h\2\2\u0128"+
		"\u0129\7c\2\2\u0129\u012a\7n\2\2\u012a\u012b\7u\2\2\u012b\u012c\7g\2\2"+
		"\u012c=\3\2\2\2\u012d\u012e\7>\2\2\u012e\u012f\7>\2\2\u012f?\3\2\2\2\u0130"+
		"\u0131\7@\2\2\u0131\u0132\7@\2\2\u0132A\3\2\2\2\u0133\u0134\7@\2\2\u0134"+
		"\u0135\7@\2\2\u0135\u0136\7@\2\2\u0136C\3\2\2\2\u0137\u0138\7?\2\2\u0138"+
		"\u0139\7?\2\2\u0139E\3\2\2\2\u013a\u013b\7@\2\2\u013b\u013c\7?\2\2\u013c"+
		"G\3\2\2\2\u013d\u013e\7>\2\2\u013e\u013f\7?\2\2\u013fI\3\2\2\2\u0140\u0141"+
		"\7#\2\2\u0141\u0142\7?\2\2\u0142K\3\2\2\2\u0143\u0144\7@\2\2\u0144M\3"+
		"\2\2\2\u0145\u0146\7>\2\2\u0146O\3\2\2\2\u0147\u0148\7c\2\2\u0148\u0149"+
		"\7p\2\2\u0149\u014a\7f\2\2\u014aQ\3\2\2\2\u014b\u014c\7q\2\2\u014c\u014d"+
		"\7t\2\2\u014dS\3\2\2\2\u014e\u014f\7p\2\2\u014f\u0150\7q\2\2\u0150\u0151"+
		"\7v\2\2\u0151U\3\2\2\2\u0152\u0153\7-\2\2\u0153\u0154\7-\2\2\u0154W\3"+
		"\2\2\2\u0155\u0156\7/\2\2\u0156\u0157\7/\2\2\u0157Y\3\2\2\2\u0158\u0159"+
		"\7`\2\2\u0159\u015a\7`\2\2\u015a[\3\2\2\2\u015b\u015c\7-\2\2\u015c]\3"+
		"\2\2\2\u015d\u015e\7/\2\2\u015e_\3\2\2\2\u015f\u0160\7,\2\2\u0160a\3\2"+
		"\2\2\u0161\u0162\7\61\2\2\u0162c\3\2\2\2\u0163\u0164\7\'\2\2\u0164e\3"+
		"\2\2\2\u0165\u0166\7(\2\2\u0166g\3\2\2\2\u0167\u0168\7~\2\2\u0168i\3\2"+
		"\2\2\u0169\u016a\7`\2\2\u016ak\3\2\2\2\u016b\u016c\7\u0080\2\2\u016cm"+
		"\3\2\2\2\u016d\u016e\7?\2\2\u016eo\3\2\2\2\u016f\u0170\7=\2\2\u0170q\3"+
		"\2\2\2\u0171\u0172\7<\2\2\u0172s\3\2\2\2\u0173\u0174\7*\2\2\u0174u\3\2"+
		"\2\2\u0175\u0176\7+\2\2\u0176w\3\2\2\2\u0177\u0178\7]\2\2\u0178y\3\2\2"+
		"\2\u0179\u017a\7_\2\2\u017a{\3\2\2\2\u017b\u017c\7.\2\2\u017c}\3\2\2\2"+
		"\u017d\u017e\7$\2\2\u017e\u017f\3\2\2\2\u017f\u0180\b?\2\2\u0180\177\3"+
		"\2\2\2\u0181\u0182\7}\2\2\u0182\u0183\3\2\2\2\u0183\u0184\b@\3\2\u0184"+
		"\u0081\3\2\2\2\u0185\u0186\7\177\2\2\u0186\u0187\3\2\2\2\u0187\u0188\b"+
		"A\4\2\u0188\u0083\3\2\2\2\u0189\u018b\5\b\4\2\u018a\u0189\3\2\2\2\u018b"+
		"\u018c\3\2\2\2\u018c\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u0085\3\2"+
		"\2\2\u018e\u0190\5\b\4\2\u018f\u018e\3\2\2\2\u0190\u0191\3\2\2\2\u0191"+
		"\u018f\3\2\2\2\u0191\u0192\3\2\2\2\u0192\u019e\3\2\2\2\u0193\u0197\7\60"+
		"\2\2\u0194\u0196\5\b\4\2\u0195\u0194\3\2\2\2\u0196\u0199\3\2\2\2\u0197"+
		"\u0195\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u019b\3\2\2\2\u0199\u0197\3\2"+
		"\2\2\u019a\u019c\7h\2\2\u019b\u019a\3\2\2\2\u019b\u019c\3\2\2\2\u019c"+
		"\u019f\3\2\2\2\u019d\u019f\7h\2\2\u019e\u0193\3\2\2\2\u019e\u019d\3\2"+
		"\2\2\u019f\u0087\3\2\2\2\u01a0\u01a3\5\6\3\2\u01a1\u01a3\5\4\2\2\u01a2"+
		"\u01a0\3\2\2\2\u01a2\u01a1\3\2\2\2\u01a3\u01a7\3\2\2\2\u01a4\u01a6\t\5"+
		"\2\2\u01a5\u01a4\3\2\2\2\u01a6\u01a9\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a7"+
		"\u01a8\3\2\2\2\u01a8\u0089\3\2\2\2\u01a9\u01a7\3\2\2\2\u01aa\u01ab\7\61"+
		"\2\2\u01ab\u01ac\7\61\2\2\u01ac\u01b0\3\2\2\2\u01ad\u01af\n\6\2\2\u01ae"+
		"\u01ad\3\2\2\2\u01af\u01b2\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b0\u01b1\3\2"+
		"\2\2\u01b1\u01b3\3\2\2\2\u01b2\u01b0\3\2\2\2\u01b3\u01b4\bE\5\2\u01b4"+
		"\u008b\3\2\2\2\u01b5\u01b7\t\7\2\2\u01b6\u01b5\3\2\2\2\u01b7\u01b8\3\2"+
		"\2\2\u01b8\u01b6\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01ba\3\2\2\2\u01ba"+
		"\u01bb\bF\5\2\u01bb\u008d\3\2\2\2\u01bc\u01bd\7&\2\2\u01bd\u01be\7}\2"+
		"\2\u01be\u01bf\3\2\2\2\u01bf\u01c0\bG\3\2\u01c0\u008f\3\2\2\2\u01c1\u01c2"+
		"\7$\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c4\bH\4\2\u01c4\u0091\3\2\2\2\u01c5"+
		"\u01c7\n\b\2\2\u01c6\u01c5\3\2\2\2\u01c7\u01c8\3\2\2\2\u01c8\u01c6\3\2"+
		"\2\2\u01c8\u01c9\3\2\2\2\u01c9\u0093\3\2\2\2\16\2\3\u018c\u0191\u0197"+
		"\u019b\u019e\u01a2\u01a7\u01b0\u01b8\u01c8\6\7\3\2\7\2\2\6\2\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}