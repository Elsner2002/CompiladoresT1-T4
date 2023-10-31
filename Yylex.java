// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: mini_java.flex


// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
class Yylex {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0, 0
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\37\u0100\1\u0200\267\u0100\10\u0300\u1020\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\2\3\1\4\22\0\1\1\1\5"+
    "\2\0\1\6\1\0\1\7\1\0\2\5\1\10\4\5"+
    "\1\11\12\12\4\5\1\0\1\5\1\0\1\13\1\14"+
    "\1\15\1\14\1\16\1\17\6\14\1\20\1\21\1\22"+
    "\2\14\1\23\1\24\1\25\6\14\1\5\1\0\1\5"+
    "\1\0\1\26\1\0\1\27\1\30\1\31\1\32\1\33"+
    "\1\34\1\35\1\36\1\37\2\14\1\40\1\41\1\42"+
    "\1\43\1\44\1\14\1\45\1\46\1\47\1\50\1\51"+
    "\1\52\1\53\1\54\1\14\1\5\1\0\1\5\7\0"+
    "\1\3\u01a2\0\2\3\326\0\u0100\3";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1024];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\3\2\1\3\3\1\1\4\17\5\2\0"+
    "\1\6\1\0\1\2\7\5\1\7\12\5\3\0\7\5"+
    "\1\10\1\5\1\11\7\5\2\0\1\2\4\5\1\12"+
    "\2\5\1\13\3\5\1\14\1\15\1\16\1\5\2\0"+
    "\3\5\1\17\1\5\1\20\3\5\1\21\2\0\1\22"+
    "\3\5\1\23\1\24\1\25\3\0\1\5\1\26\1\27"+
    "\3\0\1\5\2\0\1\30\1\0\1\5\1\31\1\32"+
    "\1\0\1\5\1\0\1\5\1\0\1\5\1\0\1\5"+
    "\1\0\1\5\1\0\1\5\1\0\1\5\1\0\1\5"+
    "\2\33";

  private static int [] zzUnpackAction() {
    int [] result = new int[142];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\55\0\132\0\55\0\207\0\55\0\264\0\341"+
    "\0\u010e\0\u013b\0\u0168\0\u0195\0\u01c2\0\u01ef\0\u021c\0\u0249"+
    "\0\u0276\0\u02a3\0\u02d0\0\u02fd\0\u032a\0\u0357\0\u0384\0\u03b1"+
    "\0\u03de\0\u040b\0\u0438\0\55\0\u0465\0\u0492\0\u04bf\0\u04ec"+
    "\0\u0519\0\u0546\0\u0573\0\u05a0\0\u05cd\0\u0168\0\u05fa\0\u0627"+
    "\0\u0654\0\u0681\0\u06ae\0\u06db\0\u0708\0\u0735\0\u0762\0\u078f"+
    "\0\u07bc\0\u07e9\0\u0816\0\u0843\0\u0870\0\u089d\0\u08ca\0\u08f7"+
    "\0\u0924\0\u0951\0\u0168\0\u097e\0\u0168\0\u09ab\0\u09d8\0\u0a05"+
    "\0\u0a32\0\u0a5f\0\u0a8c\0\u0ab9\0\u0ae6\0\u0b13\0\u0465\0\u0b40"+
    "\0\u0b6d\0\u0b9a\0\u0bc7\0\u0168\0\u0bf4\0\u0c21\0\u0168\0\u0c4e"+
    "\0\u0c7b\0\u0ca8\0\u0168\0\u0168\0\u0168\0\u0cd5\0\u0d02\0\u0d2f"+
    "\0\u0d5c\0\u0d89\0\u0db6\0\u0168\0\u0de3\0\u0168\0\u0e10\0\u0e3d"+
    "\0\u0e6a\0\u0168\0\u0e97\0\u0ec4\0\u0168\0\u0ef1\0\u0f1e\0\u0f4b"+
    "\0\u0168\0\u0168\0\u0168\0\u0f78\0\u0fa5\0\u0fd2\0\u0fff\0\u0168"+
    "\0\u0168\0\u102c\0\u1059\0\u1086\0\u10b3\0\u10e0\0\u110d\0\55"+
    "\0\u113a\0\u1167\0\55\0\55\0\u1194\0\u11c1\0\u11ee\0\u121b"+
    "\0\u1248\0\u1275\0\u12a2\0\u12cf\0\u12fc\0\u1329\0\u1356\0\u1383"+
    "\0\u13b0\0\u13dd\0\u140a\0\u1437\0\55\0\u0168";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[142];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\0\1\5\1\6\1\7\1\10"+
    "\1\6\1\11\1\12\11\13\1\14\1\13\1\6\1\13"+
    "\1\15\1\16\1\13\1\17\1\20\2\13\1\21\1\13"+
    "\1\22\1\23\1\13\1\24\1\25\1\26\1\27\1\13"+
    "\1\30\1\31\2\13\56\0\1\3\55\0\1\4\72\0"+
    "\1\32\4\0\1\33\36\0\1\34\55\0\1\35\1\36"+
    "\55\0\1\12\54\0\43\13\12\0\35\13\1\37\4\13"+
    "\1\40\12\0\31\13\1\41\11\13\12\0\26\13\1\42"+
    "\14\13\12\0\26\13\1\43\12\13\1\44\1\13\12\0"+
    "\15\13\1\45\25\13\12\0\22\13\1\46\5\13\1\47"+
    "\12\13\12\0\15\13\1\50\25\13\12\0\21\13\1\51"+
    "\21\13\12\0\36\13\1\52\4\13\12\0\21\13\1\53"+
    "\21\13\12\0\35\13\1\54\5\13\12\0\24\13\1\55"+
    "\6\13\1\56\7\13\12\0\31\13\1\57\11\13\12\0"+
    "\24\13\1\60\16\13\22\0\1\61\55\0\1\62\31\0"+
    "\2\35\3\0\3\35\1\63\44\35\2\36\1\0\1\36"+
    "\1\0\50\36\12\0\33\13\1\64\7\13\12\0\34\13"+
    "\1\65\6\13\12\0\31\13\1\66\11\13\12\0\15\13"+
    "\1\67\25\13\12\0\34\13\1\70\6\13\12\0\35\13"+
    "\1\71\5\13\12\0\26\13\1\72\14\13\12\0\35\13"+
    "\1\73\5\13\12\0\25\13\1\74\15\13\12\0\40\13"+
    "\1\75\2\13\12\0\16\13\1\76\24\13\12\0\35\13"+
    "\1\77\5\13\12\0\15\13\1\100\25\13\12\0\25\13"+
    "\1\101\15\13\12\0\36\13\1\102\4\13\12\0\25\13"+
    "\1\103\15\13\12\0\25\13\1\104\15\13\24\0\1\105"+
    "\43\0\1\106\41\0\2\35\3\0\3\35\1\63\1\107"+
    "\43\35\12\0\25\13\1\110\15\13\12\0\35\13\1\111"+
    "\5\13\12\0\26\13\1\112\14\13\12\0\34\13\1\113"+
    "\6\13\12\0\21\13\1\114\21\13\12\0\21\13\1\115"+
    "\21\13\12\0\34\13\1\116\6\13\12\0\30\13\1\117"+
    "\12\13\12\0\26\13\1\120\14\13\12\0\36\13\1\121"+
    "\4\13\12\0\35\13\1\122\5\13\12\0\34\13\1\123"+
    "\6\13\12\0\21\13\1\124\21\13\12\0\20\13\1\125"+
    "\22\13\12\0\26\13\1\126\14\13\25\0\1\127\44\0"+
    "\1\130\51\0\30\13\1\131\12\13\12\0\21\13\1\132"+
    "\21\13\12\0\21\13\1\133\21\13\12\0\34\13\1\134"+
    "\6\13\12\0\30\13\1\135\12\13\12\0\21\13\1\136"+
    "\21\13\12\0\25\13\1\137\15\13\12\0\33\13\1\140"+
    "\7\13\12\0\25\13\1\141\15\13\12\0\21\13\1\142"+
    "\21\13\23\0\1\143\47\0\1\144\50\0\23\13\1\145"+
    "\17\13\12\0\27\13\1\146\13\13\12\0\15\13\1\147"+
    "\25\13\12\0\20\13\1\150\22\13\12\0\17\13\1\151"+
    "\23\13\12\0\30\13\1\152\12\13\12\0\17\13\1\153"+
    "\23\13\13\0\1\154\67\0\1\155\26\0\2\156\3\0"+
    "\5\156\43\157\12\0\30\13\1\160\12\13\12\0\34\13"+
    "\1\161\6\13\26\0\1\162\50\0\1\163\75\0\1\164"+
    "\23\0\31\13\1\165\11\13\25\0\1\166\46\0\1\167"+
    "\1\0\1\170\103\0\1\171\16\0\36\13\1\172\4\13"+
    "\24\0\1\173\47\0\1\174\104\0\1\175\17\0\35\13"+
    "\1\176\5\13\2\177\3\0\52\177\3\0\5\177\43\200"+
    "\44\0\1\201\22\0\32\13\1\202\10\13\45\0\1\203"+
    "\21\0\33\13\1\204\7\13\37\0\1\205\27\0\25\13"+
    "\1\206\15\13\42\0\1\207\24\0\30\13\1\210\12\13"+
    "\47\0\1\211\17\0\35\13\1\212\5\13\40\0\1\213"+
    "\26\0\26\13\1\214\14\13\42\0\1\215\24\0\30\13"+
    "\1\216\12\13";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5220];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\1\11\1\1\1\11\23\1\2\0"+
    "\1\11\1\0\23\1\3\0\21\1\2\0\20\1\2\0"+
    "\12\1\2\0\7\1\3\0\3\1\3\0\1\1\2\0"+
    "\1\11\1\0\1\1\2\11\1\0\1\1\1\0\1\1"+
    "\1\0\1\1\1\0\1\1\1\0\1\1\1\0\1\1"+
    "\1\0\1\1\1\0\1\1\1\11\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[142];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  @SuppressWarnings("unused")
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }

public static int IDENT = 257;
public static int NUM = 258;

public static int IF = 259; 
public static int ELSE = 260;
public static int PUBLIC = 261;
public static int PRIVATE = 262;
public static int CLASS = 263;
public static int EQUALS = 264;
public static int INT = 265;
public static int STATIC = 266;
public static int VOID = 267;
public static int MAIN = 268;
public static int WHILE = 269;
public static int TRUE = 270;
public static int FALSE = 271;
public static int THIS = 272;
public static int NEW = 273;
public static int BOOLEAN = 274;
public static int EXTENDS = 275;
public static int RETURN = 276;
public static int PRINT = 277;
public static int STRING = 278;
public static int COMMENT = 279;
public static int AND = 280;




  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Yylex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
  yyclose();    }
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          zzR = false;
          break;
        case '\r':
          yyline++;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
          }
          break;
        default:
          zzR = false;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return 0; }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { System.out.println(yyline+1 + ": caracter invalido: "+yytext());
            }
            // fall through
          case 28: break;
          case 2:
            { 
            }
            // fall through
          case 29: break;
          case 3:
            { return yytext().charAt(0);
            }
            // fall through
          case 30: break;
          case 4:
            { return Parser.NUM;
            }
            // fall through
          case 31: break;
          case 5:
            { yyparser.yylval = new ParserVal(yytext());
                                return Parser.IDENT;
            }
            // fall through
          case 32: break;
          case 6:
            { return Parser.AND;
            }
            // fall through
          case 33: break;
          case 7:
            { return Parser.IF;
            }
            // fall through
          case 34: break;
          case 8:
            { return Parser.INT;
            }
            // fall through
          case 35: break;
          case 9:
            { return Parser.NEW;
            }
            // fall through
          case 36: break;
          case 10:
            { return Parser.ELSE;
            }
            // fall through
          case 37: break;
          case 11:
            { return Parser.MAIN;
            }
            // fall through
          case 38: break;
          case 12:
            { return Parser.THIS;
            }
            // fall through
          case 39: break;
          case 13:
            { return Parser.TRUE;
            }
            // fall through
          case 40: break;
          case 14:
            { return Parser.VOID;
            }
            // fall through
          case 41: break;
          case 15:
            { return Parser.CLASS;
            }
            // fall through
          case 42: break;
          case 16:
            { return Parser.FALSE;
            }
            // fall through
          case 43: break;
          case 17:
            { return Parser.WHILE;
            }
            // fall through
          case 44: break;
          case 18:
            { return Parser.STRING;
            }
            // fall through
          case 45: break;
          case 19:
            { return Parser.PUBLIC;
            }
            // fall through
          case 46: break;
          case 20:
            { return Parser.RETURN;
            }
            // fall through
          case 47: break;
          case 21:
            { return Parser.STATIC;
            }
            // fall through
          case 48: break;
          case 22:
            { return Parser.BOOLEAN;
            }
            // fall through
          case 49: break;
          case 23:
            { return Parser.EXTENDS;
            }
            // fall through
          case 50: break;
          case 24:
            { yyparser.setDebug(true);
            }
            // fall through
          case 51: break;
          case 25:
            { yyparser.listarTS();
            }
            // fall through
          case 52: break;
          case 26:
            { yyparser.setDebug(false);
            }
            // fall through
          case 53: break;
          case 27:
            { return Parser.PRINT;
            }
            // fall through
          case 54: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
