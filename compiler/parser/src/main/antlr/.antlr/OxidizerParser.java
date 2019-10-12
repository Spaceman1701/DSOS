// Generated from c:\Users\fcspa\de\dsos\compiler\parser\src\main\antlr\OxidizerParser.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class OxidizerParser extends Parser {
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
		RULE_module = 0, RULE_decl = 1, RULE_block = 2, RULE_stmt = 3, RULE_loop = 4, 
		RULE_branch = 5, RULE_compare_op = 6, RULE_string = 7, RULE_stringPart = 8, 
		RULE_literall = 9, RULE_expr = 10, RULE_comprehension = 11, RULE_ident = 12, 
		RULE_typedecl = 13, RULE_innerclass = 14, RULE_funcdecl = 15, RULE_importdecl = 16;
	public static final String[] ruleNames = {
		"module", "decl", "block", "stmt", "loop", "branch", "compare_op", "string", 
		"stringPart", "literall", "expr", "comprehension", "ident", "typedecl", 
		"innerclass", "funcdecl", "importdecl"
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

	@Override
	public String getGrammarFileName() { return "OxidizerParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public OxidizerParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ModuleContext extends ParserRuleContext {
		public List<ImportdeclContext> importdecl() {
			return getRuleContexts(ImportdeclContext.class);
		}
		public ImportdeclContext importdecl(int i) {
			return getRuleContext(ImportdeclContext.class,i);
		}
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_module);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KW_IMPORT) {
				{
				{
				setState(34);
				importdecl();
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				decl();
				}
				}
				setState(43); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KW_PRIVATE) | (1L << KW_DEF) | (1L << KW_CLASS))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public TypedeclContext typedecl() {
			return getRuleContext(TypedeclContext.class,0);
		}
		public FuncdeclContext funcdecl() {
			return getRuleContext(FuncdeclContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		try {
			setState(47);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KW_CLASS:
				enterOuterAlt(_localctx, 1);
				{
				setState(45);
				typedecl();
				}
				break;
			case KW_PRIVATE:
			case KW_DEF:
				enterOuterAlt(_localctx, 2);
				{
				setState(46);
				funcdecl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(OxidizerParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(OxidizerParser.RBRACE, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(LBRACE);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KW_FOR) | (1L << KW_WHILE) | (1L << KW_IF) | (1L << OP_NOT) | (1L << OP_MINUS) | (1L << OP_COMPLEMENT) | (1L << LPAREN) | (1L << LSQUARE) | (1L << INTEGER) | (1L << NAME))) != 0)) {
				{
				{
				setState(50);
				stmt();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(OxidizerParser.SEMI, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode OP_ASSIGN() { return getToken(OxidizerParser.OP_ASSIGN, 0); }
		public LoopContext loop() {
			return getRuleContext(LoopContext.class,0);
		}
		public BranchContext branch() {
			return getRuleContext(BranchContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_stmt);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				expr(0);
				setState(59);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				ident();
				setState(62);
				match(OP_ASSIGN);
				setState(63);
				expr(0);
				setState(64);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(66);
				loop();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(67);
				branch();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopContext extends ParserRuleContext {
		public TerminalNode KW_FOR() { return getToken(OxidizerParser.KW_FOR, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode KW_IN() { return getToken(OxidizerParser.KW_IN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(OxidizerParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(OxidizerParser.RBRACE, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode KW_WHILE() { return getToken(OxidizerParser.KW_WHILE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public LoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop; }
	}

	public final LoopContext loop() throws RecognitionException {
		LoopContext _localctx = new LoopContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_loop);
		int _la;
		try {
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KW_FOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				match(KW_FOR);
				setState(71);
				ident();
				setState(72);
				match(KW_IN);
				setState(73);
				expr(0);
				setState(74);
				match(LBRACE);
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KW_FOR) | (1L << KW_WHILE) | (1L << KW_IF) | (1L << OP_NOT) | (1L << OP_MINUS) | (1L << OP_COMPLEMENT) | (1L << LPAREN) | (1L << LSQUARE) | (1L << INTEGER) | (1L << NAME))) != 0)) {
					{
					{
					setState(75);
					stmt();
					}
					}
					setState(80);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(81);
				match(RBRACE);
				}
				break;
			case KW_WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				match(KW_WHILE);
				setState(84);
				expr(0);
				setState(85);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BranchContext extends ParserRuleContext {
		public TerminalNode KW_IF() { return getToken(OxidizerParser.KW_IF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<TerminalNode> KW_ELIF() { return getTokens(OxidizerParser.KW_ELIF); }
		public TerminalNode KW_ELIF(int i) {
			return getToken(OxidizerParser.KW_ELIF, i);
		}
		public TerminalNode KW_ELSE() { return getToken(OxidizerParser.KW_ELSE, 0); }
		public BranchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_branch; }
	}

	public final BranchContext branch() throws RecognitionException {
		BranchContext _localctx = new BranchContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_branch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(KW_IF);
			setState(90);
			expr(0);
			setState(91);
			block();
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KW_ELIF) {
				{
				{
				setState(92);
				match(KW_ELIF);
				setState(93);
				expr(0);
				setState(94);
				block();
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_ELSE) {
				{
				setState(101);
				match(KW_ELSE);
				setState(102);
				block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Compare_opContext extends ParserRuleContext {
		public TerminalNode COMP_G() { return getToken(OxidizerParser.COMP_G, 0); }
		public TerminalNode COMP_GE() { return getToken(OxidizerParser.COMP_GE, 0); }
		public TerminalNode COMP_L() { return getToken(OxidizerParser.COMP_L, 0); }
		public TerminalNode COMP_LE() { return getToken(OxidizerParser.COMP_LE, 0); }
		public Compare_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compare_op; }
	}

	public final Compare_opContext compare_op() throws RecognitionException {
		Compare_opContext _localctx = new Compare_opContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_compare_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMP_GE) | (1L << COMP_LE) | (1L << COMP_G) | (1L << COMP_L))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode ENTER_STRING() { return getToken(OxidizerParser.ENTER_STRING, 0); }
		public TerminalNode END_STRING() { return getToken(OxidizerParser.END_STRING, 0); }
		public List<StringPartContext> stringPart() {
			return getRuleContexts(StringPartContext.class);
		}
		public StringPartContext stringPart(int i) {
			return getRuleContext(StringPartContext.class,i);
		}
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(ENTER_STRING);
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ENTER_STR_EXPR || _la==TEXT) {
				{
				{
				setState(108);
				stringPart();
				}
				}
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(114);
			match(END_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringPartContext extends ParserRuleContext {
		public StringPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringPart; }
	 
		public StringPartContext() { }
		public void copyFrom(StringPartContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EmbeddedExprContext extends StringPartContext {
		public TerminalNode ENTER_STR_EXPR() { return getToken(OxidizerParser.ENTER_STR_EXPR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(OxidizerParser.RBRACE, 0); }
		public EmbeddedExprContext(StringPartContext ctx) { copyFrom(ctx); }
	}
	public static class TextContext extends StringPartContext {
		public TerminalNode TEXT() { return getToken(OxidizerParser.TEXT, 0); }
		public TextContext(StringPartContext ctx) { copyFrom(ctx); }
	}

	public final StringPartContext stringPart() throws RecognitionException {
		StringPartContext _localctx = new StringPartContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_stringPart);
		try {
			setState(121);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXT:
				_localctx = new TextContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				match(TEXT);
				}
				break;
			case ENTER_STR_EXPR:
				_localctx = new EmbeddedExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				match(ENTER_STR_EXPR);
				setState(118);
				expr(0);
				setState(119);
				match(RBRACE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiterallContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(OxidizerParser.INTEGER, 0); }
		public LiterallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literall; }
	}

	public final LiterallContext literall() throws RecognitionException {
		LiterallContext _localctx = new LiterallContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_literall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ShiftContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OP_LSHIFT() { return getToken(OxidizerParser.OP_LSHIFT, 0); }
		public TerminalNode OP_RSHIFT() { return getToken(OxidizerParser.OP_RSHIFT, 0); }
		public ShiftContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class DecContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode OP_DEC() { return getToken(OxidizerParser.OP_DEC, 0); }
		public DecContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class BorContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OP_BOR() { return getToken(OxidizerParser.OP_BOR, 0); }
		public BorContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class TernaryContext extends ExprContext {
		public TerminalNode KW_IF() { return getToken(OxidizerParser.KW_IF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode KW_THEN() { return getToken(OxidizerParser.KW_THEN, 0); }
		public TerminalNode KW_ELSE() { return getToken(OxidizerParser.KW_ELSE, 0); }
		public TernaryContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class AddSubContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OP_PLUS() { return getToken(OxidizerParser.OP_PLUS, 0); }
		public TerminalNode OP_MINUS() { return getToken(OxidizerParser.OP_MINUS, 0); }
		public AddSubContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class EQTestContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COMP_EQ() { return getToken(OxidizerParser.COMP_EQ, 0); }
		public TerminalNode COMP_NE() { return getToken(OxidizerParser.COMP_NE, 0); }
		public EQTestContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class VarContext extends ExprContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public VarContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class ParensContext extends ExprContext {
		public TerminalNode LPAREN() { return getToken(OxidizerParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(OxidizerParser.RPAREN, 0); }
		public ParensContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class LAndContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OP_AND() { return getToken(OxidizerParser.OP_AND, 0); }
		public LAndContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class ListCompContext extends ExprContext {
		public ComprehensionContext comprehension() {
			return getRuleContext(ComprehensionContext.class,0);
		}
		public ListCompContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class BAndContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OP_BAND() { return getToken(OxidizerParser.OP_BAND, 0); }
		public BAndContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class LorContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OP_OR() { return getToken(OxidizerParser.OP_OR, 0); }
		public LorContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class ConcatContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OP_INC() { return getToken(OxidizerParser.OP_INC, 0); }
		public ConcatContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class NegContext extends ExprContext {
		public TerminalNode OP_MINUS() { return getToken(OxidizerParser.OP_MINUS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NegContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class NotContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode OP_NOT() { return getToken(OxidizerParser.OP_NOT, 0); }
		public TerminalNode OP_COMPLEMENT() { return getToken(OxidizerParser.OP_COMPLEMENT, 0); }
		public NotContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class FunCallContext extends ExprContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(OxidizerParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(OxidizerParser.RPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(OxidizerParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(OxidizerParser.COMMA, i);
		}
		public FunCallContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class LitContext extends ExprContext {
		public LiterallContext literall() {
			return getRuleContext(LiterallContext.class,0);
		}
		public LitContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class PowContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OP_POW() { return getToken(OxidizerParser.OP_POW, 0); }
		public PowContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class CompareContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Compare_opContext compare_op() {
			return getRuleContext(Compare_opContext.class,0);
		}
		public CompareContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class XorContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OP_XOR() { return getToken(OxidizerParser.OP_XOR, 0); }
		public XorContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class MulModDivContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OP_MUL() { return getToken(OxidizerParser.OP_MUL, 0); }
		public TerminalNode OP_DIV() { return getToken(OxidizerParser.OP_DIV, 0); }
		public TerminalNode OP_MOD() { return getToken(OxidizerParser.OP_MOD, 0); }
		public MulModDivContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class IncContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode OP_INC() { return getToken(OxidizerParser.OP_INC, 0); }
		public IncContext(ExprContext ctx) { copyFrom(ctx); }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				_localctx = new VarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(126);
				ident();
				}
				break;
			case 2:
				{
				_localctx = new LitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(127);
				literall();
				}
				break;
			case 3:
				{
				_localctx = new FunCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				ident();
				setState(129);
				match(LPAREN);
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KW_IF) | (1L << OP_NOT) | (1L << OP_MINUS) | (1L << OP_COMPLEMENT) | (1L << LPAREN) | (1L << LSQUARE) | (1L << INTEGER) | (1L << NAME))) != 0)) {
					{
					setState(130);
					expr(0);
					setState(135);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(131);
						match(COMMA);
						setState(132);
						expr(0);
						}
						}
						setState(137);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(140);
				match(RPAREN);
				}
				break;
			case 4:
				{
				_localctx = new ParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(142);
				match(LPAREN);
				setState(143);
				expr(0);
				setState(144);
				match(RPAREN);
				}
				break;
			case 5:
				{
				_localctx = new ListCompContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(146);
				comprehension();
				}
				break;
			case 6:
				{
				_localctx = new NegContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(147);
				match(OP_MINUS);
				setState(148);
				expr(15);
				}
				break;
			case 7:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(149);
				_la = _input.LA(1);
				if ( !(_la==OP_NOT || _la==OP_COMPLEMENT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(150);
				expr(14);
				}
				break;
			case 8:
				{
				_localctx = new TernaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(151);
				match(KW_IF);
				setState(152);
				expr(0);
				setState(153);
				match(KW_THEN);
				setState(154);
				expr(0);
				setState(155);
				match(KW_ELSE);
				setState(156);
				expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(203);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(201);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new PowContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(160);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(161);
						match(OP_POW);
						setState(162);
						expr(13);
						}
						break;
					case 2:
						{
						_localctx = new MulModDivContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(163);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(164);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OP_MUL) | (1L << OP_DIV) | (1L << OP_MOD))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(165);
						expr(13);
						}
						break;
					case 3:
						{
						_localctx = new AddSubContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(166);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(167);
						_la = _input.LA(1);
						if ( !(_la==OP_PLUS || _la==OP_MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(168);
						expr(12);
						}
						break;
					case 4:
						{
						_localctx = new CompareContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(169);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(170);
						compare_op();
						setState(171);
						expr(11);
						}
						break;
					case 5:
						{
						_localctx = new EQTestContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(173);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(174);
						_la = _input.LA(1);
						if ( !(_la==COMP_EQ || _la==COMP_NE) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(175);
						expr(10);
						}
						break;
					case 6:
						{
						_localctx = new ShiftContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(176);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(177);
						_la = _input.LA(1);
						if ( !(_la==OP_LSHIFT || _la==OP_RSHIFT) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(178);
						expr(9);
						}
						break;
					case 7:
						{
						_localctx = new BAndContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(179);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(180);
						match(OP_BAND);
						setState(181);
						expr(8);
						}
						break;
					case 8:
						{
						_localctx = new XorContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(182);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(183);
						match(OP_XOR);
						setState(184);
						expr(7);
						}
						break;
					case 9:
						{
						_localctx = new BorContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(185);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(186);
						match(OP_BOR);
						setState(187);
						expr(6);
						}
						break;
					case 10:
						{
						_localctx = new LAndContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(188);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(189);
						match(OP_AND);
						setState(190);
						expr(5);
						}
						break;
					case 11:
						{
						_localctx = new LorContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(191);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(192);
						match(OP_OR);
						setState(193);
						expr(4);
						}
						break;
					case 12:
						{
						_localctx = new ConcatContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(194);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(195);
						match(OP_INC);
						setState(196);
						expr(3);
						}
						break;
					case 13:
						{
						_localctx = new IncContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(197);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(198);
						match(OP_INC);
						}
						break;
					case 14:
						{
						_localctx = new DecContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(199);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(200);
						match(OP_DEC);
						}
						break;
					}
					} 
				}
				setState(205);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ComprehensionContext extends ParserRuleContext {
		public ComprehensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comprehension; }
	 
		public ComprehensionContext() { }
		public void copyFrom(ComprehensionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EmptyListContext extends ComprehensionContext {
		public TerminalNode LSQUARE() { return getToken(OxidizerParser.LSQUARE, 0); }
		public TerminalNode RSQUARE() { return getToken(OxidizerParser.RSQUARE, 0); }
		public EmptyListContext(ComprehensionContext ctx) { copyFrom(ctx); }
	}
	public static class LiteralListContext extends ComprehensionContext {
		public TerminalNode LSQUARE() { return getToken(OxidizerParser.LSQUARE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode RSQUARE() { return getToken(OxidizerParser.RSQUARE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(OxidizerParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(OxidizerParser.COMMA, i);
		}
		public LiteralListContext(ComprehensionContext ctx) { copyFrom(ctx); }
	}
	public static class ListSliceContext extends ComprehensionContext {
		public TerminalNode LSQUARE() { return getToken(OxidizerParser.LSQUARE, 0); }
		public List<TerminalNode> COLON() { return getTokens(OxidizerParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(OxidizerParser.COLON, i);
		}
		public TerminalNode RSQUARE() { return getToken(OxidizerParser.RSQUARE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ListSliceContext(ComprehensionContext ctx) { copyFrom(ctx); }
	}
	public static class ForCompContext extends ComprehensionContext {
		public TerminalNode LSQUARE() { return getToken(OxidizerParser.LSQUARE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode KW_FOR() { return getToken(OxidizerParser.KW_FOR, 0); }
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public TerminalNode KW_IN() { return getToken(OxidizerParser.KW_IN, 0); }
		public TerminalNode RSQUARE() { return getToken(OxidizerParser.RSQUARE, 0); }
		public TerminalNode KW_IF() { return getToken(OxidizerParser.KW_IF, 0); }
		public ForCompContext(ComprehensionContext ctx) { copyFrom(ctx); }
	}

	public final ComprehensionContext comprehension() throws RecognitionException {
		ComprehensionContext _localctx = new ComprehensionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_comprehension);
		int _la;
		try {
			setState(246);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				_localctx = new EmptyListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(206);
				match(LSQUARE);
				setState(207);
				match(RSQUARE);
				}
				break;
			case 2:
				_localctx = new LiteralListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(208);
				match(LSQUARE);
				setState(209);
				expr(0);
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(210);
					match(COMMA);
					setState(211);
					expr(0);
					}
					}
					setState(216);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(217);
				match(RSQUARE);
				}
				break;
			case 3:
				_localctx = new ForCompContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(219);
				match(LSQUARE);
				setState(220);
				expr(0);
				setState(221);
				match(KW_FOR);
				setState(222);
				ident();
				setState(223);
				match(KW_IN);
				setState(224);
				expr(0);
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==KW_IF) {
					{
					setState(225);
					match(KW_IF);
					setState(226);
					expr(0);
					}
				}

				setState(229);
				match(RSQUARE);
				}
				break;
			case 4:
				_localctx = new ListSliceContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(231);
				match(LSQUARE);
				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KW_IF) | (1L << OP_NOT) | (1L << OP_MINUS) | (1L << OP_COMPLEMENT) | (1L << LPAREN) | (1L << LSQUARE) | (1L << INTEGER) | (1L << NAME))) != 0)) {
					{
					setState(232);
					expr(0);
					}
				}

				setState(235);
				match(COLON);
				setState(237);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(236);
					expr(0);
					}
					break;
				}
				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(239);
					match(COLON);
					}
				}

				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KW_IF) | (1L << OP_NOT) | (1L << OP_MINUS) | (1L << OP_COMPLEMENT) | (1L << LPAREN) | (1L << LSQUARE) | (1L << INTEGER) | (1L << NAME))) != 0)) {
					{
					setState(242);
					expr(0);
					}
				}

				setState(245);
				match(RSQUARE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(OxidizerParser.NAME, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypedeclContext extends ParserRuleContext {
		public TerminalNode KW_CLASS() { return getToken(OxidizerParser.KW_CLASS, 0); }
		public TerminalNode NAME() { return getToken(OxidizerParser.NAME, 0); }
		public TerminalNode LBRACE() { return getToken(OxidizerParser.LBRACE, 0); }
		public InnerclassContext innerclass() {
			return getRuleContext(InnerclassContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(OxidizerParser.RBRACE, 0); }
		public TypedeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typedecl; }
	}

	public final TypedeclContext typedecl() throws RecognitionException {
		TypedeclContext _localctx = new TypedeclContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_typedecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			match(KW_CLASS);
			setState(251);
			match(NAME);
			setState(252);
			match(LBRACE);
			setState(253);
			innerclass();
			setState(254);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InnerclassContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public InnerclassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_innerclass; }
	}

	public final InnerclassContext innerclass() throws RecognitionException {
		InnerclassContext _localctx = new InnerclassContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_innerclass);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncdeclContext extends ParserRuleContext {
		public TerminalNode KW_DEF() { return getToken(OxidizerParser.KW_DEF, 0); }
		public List<TerminalNode> NAME() { return getTokens(OxidizerParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(OxidizerParser.NAME, i);
		}
		public TerminalNode LPAREN() { return getToken(OxidizerParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(OxidizerParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode KW_PRIVATE() { return getToken(OxidizerParser.KW_PRIVATE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(OxidizerParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(OxidizerParser.COMMA, i);
		}
		public FuncdeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcdecl; }
	}

	public final FuncdeclContext funcdecl() throws RecognitionException {
		FuncdeclContext _localctx = new FuncdeclContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_funcdecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_PRIVATE) {
				{
				setState(258);
				match(KW_PRIVATE);
				}
			}

			setState(261);
			match(KW_DEF);
			setState(262);
			match(NAME);
			setState(263);
			match(LPAREN);
			setState(272);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAME) {
				{
				setState(264);
				match(NAME);
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(265);
					match(COMMA);
					setState(266);
					match(NAME);
					}
					}
					setState(271);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(274);
			match(RPAREN);
			setState(275);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportdeclContext extends ParserRuleContext {
		public TerminalNode KW_IMPORT() { return getToken(OxidizerParser.KW_IMPORT, 0); }
		public ImportdeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importdecl; }
	}

	public final ImportdeclContext importdecl() throws RecognitionException {
		ImportdeclContext _localctx = new ImportdeclContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_importdecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(KW_IMPORT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 8);
		case 6:
			return precpred(_ctx, 7);
		case 7:
			return precpred(_ctx, 6);
		case 8:
			return precpred(_ctx, 5);
		case 9:
			return precpred(_ctx, 4);
		case 10:
			return precpred(_ctx, 3);
		case 11:
			return precpred(_ctx, 2);
		case 12:
			return precpred(_ctx, 17);
		case 13:
			return precpred(_ctx, 16);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\65\u011a\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\7\2&\n\2\f\2\16\2)\13\2\3\2\6\2,\n\2\r\2\16\2-\3\3\3\3\5\3\62\n\3"+
		"\3\4\3\4\7\4\66\n\4\f\4\16\49\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\5\5G\n\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6O\n\6\f\6\16\6R\13\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\5\6Z\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7c\n\7"+
		"\f\7\16\7f\13\7\3\7\3\7\5\7j\n\7\3\b\3\b\3\t\3\t\7\tp\n\t\f\t\16\ts\13"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\5\n|\n\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\7\f\u0088\n\f\f\f\16\f\u008b\13\f\5\f\u008d\n\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00a1"+
		"\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00cc\n\f\f\f\16\f\u00cf\13\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\7\r\u00d7\n\r\f\r\16\r\u00da\13\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\5\r\u00e6\n\r\3\r\3\r\3\r\3\r\5\r\u00ec\n\r\3\r\3"+
		"\r\5\r\u00f0\n\r\3\r\5\r\u00f3\n\r\3\r\5\r\u00f6\n\r\3\r\5\r\u00f9\n\r"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\5\21\u0106\n\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u010e\n\21\f\21\16\21\u0111\13\21"+
		"\5\21\u0113\n\21\3\21\3\21\3\21\3\22\3\22\3\22\2\3\26\23\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"\2\b\4\2\21\22\24\25\4\2\30\30$$\3\2\36 \3"+
		"\2\34\35\4\2\20\20\23\23\3\2\16\17\2\u0138\2\'\3\2\2\2\4\61\3\2\2\2\6"+
		"\63\3\2\2\2\bF\3\2\2\2\nY\3\2\2\2\f[\3\2\2\2\16k\3\2\2\2\20m\3\2\2\2\22"+
		"{\3\2\2\2\24}\3\2\2\2\26\u00a0\3\2\2\2\30\u00f8\3\2\2\2\32\u00fa\3\2\2"+
		"\2\34\u00fc\3\2\2\2\36\u0102\3\2\2\2 \u0105\3\2\2\2\"\u0117\3\2\2\2$&"+
		"\5\"\22\2%$\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(+\3\2\2\2)\'\3\2\2"+
		"\2*,\5\4\3\2+*\3\2\2\2,-\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\3\3\2\2\2/\62\5"+
		"\34\17\2\60\62\5 \21\2\61/\3\2\2\2\61\60\3\2\2\2\62\5\3\2\2\2\63\67\7"+
		".\2\2\64\66\5\b\5\2\65\64\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2"+
		"\28:\3\2\2\29\67\3\2\2\2:;\7/\2\2;\7\3\2\2\2<=\5\26\f\2=>\7&\2\2>G\3\2"+
		"\2\2?@\5\32\16\2@A\7%\2\2AB\5\26\f\2BC\7&\2\2CG\3\2\2\2DG\5\n\6\2EG\5"+
		"\f\7\2F<\3\2\2\2F?\3\2\2\2FD\3\2\2\2FE\3\2\2\2G\t\3\2\2\2HI\7\5\2\2IJ"+
		"\5\32\16\2JK\7\6\2\2KL\5\26\f\2LP\7.\2\2MO\5\b\5\2NM\3\2\2\2OR\3\2\2\2"+
		"PN\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RP\3\2\2\2ST\7/\2\2TZ\3\2\2\2UV\7\7\2\2"+
		"VW\5\26\f\2WX\5\6\4\2XZ\3\2\2\2YH\3\2\2\2YU\3\2\2\2Z\13\3\2\2\2[\\\7\b"+
		"\2\2\\]\5\26\f\2]d\5\6\4\2^_\7\t\2\2_`\5\26\f\2`a\5\6\4\2ac\3\2\2\2b^"+
		"\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2ei\3\2\2\2fd\3\2\2\2gh\7\n\2\2h"+
		"j\5\6\4\2ig\3\2\2\2ij\3\2\2\2j\r\3\2\2\2kl\t\2\2\2l\17\3\2\2\2mq\7-\2"+
		"\2np\5\22\n\2on\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3\2"+
		"\2\2tu\7\64\2\2u\21\3\2\2\2v|\7\65\2\2wx\7\63\2\2xy\5\26\f\2yz\7/\2\2"+
		"z|\3\2\2\2{v\3\2\2\2{w\3\2\2\2|\23\3\2\2\2}~\7\60\2\2~\25\3\2\2\2\177"+
		"\u0080\b\f\1\2\u0080\u00a1\5\32\16\2\u0081\u00a1\5\24\13\2\u0082\u0083"+
		"\5\32\16\2\u0083\u008c\7(\2\2\u0084\u0089\5\26\f\2\u0085\u0086\7,\2\2"+
		"\u0086\u0088\5\26\f\2\u0087\u0085\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087"+
		"\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008c"+
		"\u0084\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\7)"+
		"\2\2\u008f\u00a1\3\2\2\2\u0090\u0091\7(\2\2\u0091\u0092\5\26\f\2\u0092"+
		"\u0093\7)\2\2\u0093\u00a1\3\2\2\2\u0094\u00a1\5\30\r\2\u0095\u0096\7\35"+
		"\2\2\u0096\u00a1\5\26\f\21\u0097\u0098\t\3\2\2\u0098\u00a1\5\26\f\20\u0099"+
		"\u009a\7\b\2\2\u009a\u009b\5\26\f\2\u009b\u009c\7\r\2\2\u009c\u009d\5"+
		"\26\f\2\u009d\u009e\7\n\2\2\u009e\u009f\5\26\f\3\u009f\u00a1\3\2\2\2\u00a0"+
		"\177\3\2\2\2\u00a0\u0081\3\2\2\2\u00a0\u0082\3\2\2\2\u00a0\u0090\3\2\2"+
		"\2\u00a0\u0094\3\2\2\2\u00a0\u0095\3\2\2\2\u00a0\u0097\3\2\2\2\u00a0\u0099"+
		"\3\2\2\2\u00a1\u00cd\3\2\2\2\u00a2\u00a3\f\17\2\2\u00a3\u00a4\7\33\2\2"+
		"\u00a4\u00cc\5\26\f\17\u00a5\u00a6\f\16\2\2\u00a6\u00a7\t\4\2\2\u00a7"+
		"\u00cc\5\26\f\17\u00a8\u00a9\f\r\2\2\u00a9\u00aa\t\5\2\2\u00aa\u00cc\5"+
		"\26\f\16\u00ab\u00ac\f\f\2\2\u00ac\u00ad\5\16\b\2\u00ad\u00ae\5\26\f\r"+
		"\u00ae\u00cc\3\2\2\2\u00af\u00b0\f\13\2\2\u00b0\u00b1\t\6\2\2\u00b1\u00cc"+
		"\5\26\f\f\u00b2\u00b3\f\n\2\2\u00b3\u00b4\t\7\2\2\u00b4\u00cc\5\26\f\13"+
		"\u00b5\u00b6\f\t\2\2\u00b6\u00b7\7!\2\2\u00b7\u00cc\5\26\f\n\u00b8\u00b9"+
		"\f\b\2\2\u00b9\u00ba\7#\2\2\u00ba\u00cc\5\26\f\t\u00bb\u00bc\f\7\2\2\u00bc"+
		"\u00bd\7\"\2\2\u00bd\u00cc\5\26\f\b\u00be\u00bf\f\6\2\2\u00bf\u00c0\7"+
		"\26\2\2\u00c0\u00cc\5\26\f\7\u00c1\u00c2\f\5\2\2\u00c2\u00c3\7\27\2\2"+
		"\u00c3\u00cc\5\26\f\6\u00c4\u00c5\f\4\2\2\u00c5\u00c6\7\31\2\2\u00c6\u00cc"+
		"\5\26\f\5\u00c7\u00c8\f\23\2\2\u00c8\u00cc\7\31\2\2\u00c9\u00ca\f\22\2"+
		"\2\u00ca\u00cc\7\32\2\2\u00cb\u00a2\3\2\2\2\u00cb\u00a5\3\2\2\2\u00cb"+
		"\u00a8\3\2\2\2\u00cb\u00ab\3\2\2\2\u00cb\u00af\3\2\2\2\u00cb\u00b2\3\2"+
		"\2\2\u00cb\u00b5\3\2\2\2\u00cb\u00b8\3\2\2\2\u00cb\u00bb\3\2\2\2\u00cb"+
		"\u00be\3\2\2\2\u00cb\u00c1\3\2\2\2\u00cb\u00c4\3\2\2\2\u00cb\u00c7\3\2"+
		"\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd"+
		"\u00ce\3\2\2\2\u00ce\27\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d1\7*\2\2"+
		"\u00d1\u00f9\7+\2\2\u00d2\u00d3\7*\2\2\u00d3\u00d8\5\26\f\2\u00d4\u00d5"+
		"\7,\2\2\u00d5\u00d7\5\26\f\2\u00d6\u00d4\3\2\2\2\u00d7\u00da\3\2\2\2\u00d8"+
		"\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00db\3\2\2\2\u00da\u00d8\3\2"+
		"\2\2\u00db\u00dc\7+\2\2\u00dc\u00f9\3\2\2\2\u00dd\u00de\7*\2\2\u00de\u00df"+
		"\5\26\f\2\u00df\u00e0\7\5\2\2\u00e0\u00e1\5\32\16\2\u00e1\u00e2\7\6\2"+
		"\2\u00e2\u00e5\5\26\f\2\u00e3\u00e4\7\b\2\2\u00e4\u00e6\5\26\f\2\u00e5"+
		"\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\7+"+
		"\2\2\u00e8\u00f9\3\2\2\2\u00e9\u00eb\7*\2\2\u00ea\u00ec\5\26\f\2\u00eb"+
		"\u00ea\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ef\7\'"+
		"\2\2\u00ee\u00f0\5\26\f\2\u00ef\u00ee\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0"+
		"\u00f2\3\2\2\2\u00f1\u00f3\7\'\2\2\u00f2\u00f1\3\2\2\2\u00f2\u00f3\3\2"+
		"\2\2\u00f3\u00f5\3\2\2\2\u00f4\u00f6\5\26\f\2\u00f5\u00f4\3\2\2\2\u00f5"+
		"\u00f6\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f9\7+\2\2\u00f8\u00d0\3\2"+
		"\2\2\u00f8\u00d2\3\2\2\2\u00f8\u00dd\3\2\2\2\u00f8\u00e9\3\2\2\2\u00f9"+
		"\31\3\2\2\2\u00fa\u00fb\7\61\2\2\u00fb\33\3\2\2\2\u00fc\u00fd\7\13\2\2"+
		"\u00fd\u00fe\7\61\2\2\u00fe\u00ff\7.\2\2\u00ff\u0100\5\36\20\2\u0100\u0101"+
		"\7/\2\2\u0101\35\3\2\2\2\u0102\u0103\5\26\f\2\u0103\37\3\2\2\2\u0104\u0106"+
		"\7\3\2\2\u0105\u0104\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\3\2\2\2\u0107"+
		"\u0108\7\4\2\2\u0108\u0109\7\61\2\2\u0109\u0112\7(\2\2\u010a\u010f\7\61"+
		"\2\2\u010b\u010c\7,\2\2\u010c\u010e\7\61\2\2\u010d\u010b\3\2\2\2\u010e"+
		"\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0113\3\2"+
		"\2\2\u0111\u010f\3\2\2\2\u0112\u010a\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u0114\3\2\2\2\u0114\u0115\7)\2\2\u0115\u0116\5\6\4\2\u0116!\3\2\2\2\u0117"+
		"\u0118\7\f\2\2\u0118#\3\2\2\2\34\'-\61\67FPYdiq{\u0089\u008c\u00a0\u00cb"+
		"\u00cd\u00d8\u00e5\u00eb\u00ef\u00f2\u00f5\u00f8\u0105\u010f\u0112";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}