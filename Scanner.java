package cop5556sp17;

import java.util.ArrayList;

public class Scanner {
	/**
	 * Kind enum
	 */
	
	public static enum Kind {
		IDENT(""), INT_LIT(""), KW_INTEGER("integer"), KW_BOOLEAN("boolean"), 
		KW_IMAGE("image"), KW_URL("url"), KW_FILE("file"), KW_FRAME("frame"), 
		KW_WHILE("while"), KW_IF("if"), KW_TRUE("true"), KW_FALSE("false"), 
		SEMI(";"), COMMA(","), LPAREN("("), RPAREN(")"), LBRACE("{"), 
		RBRACE("}"), ARROW("->"), BARARROW("|->"), OR("|"), AND("&"), 
		EQUAL("=="), NOTEQUAL("!="), LT("<"), GT(">"), LE("<="), GE(">="), 
		PLUS("+"), MINUS("-"), TIMES("*"), DIV("/"), MOD("%"), NOT("!"), 
		ASSIGN("<-"), OP_BLUR("blur"), OP_GRAY("gray"), OP_CONVOLVE("convolve"), 
		KW_SCREENHEIGHT("screenheight"), KW_SCREENWIDTH("screenwidth"), 
		OP_WIDTH("width"), OP_HEIGHT("height"), KW_XLOC("xloc"), KW_YLOC("yloc"), 
		KW_HIDE("hide"), KW_SHOW("show"), KW_MOVE("move"), OP_SLEEP("sleep"), 
		KW_SCALE("scale"), EOF("eof");

		Kind(String text) {
			this.text = text;
		}

		final String text;

		String getText() {
			return text;
		}
	}
/**
 * Thrown by Scanner when an illegal character is encountered
 */
	@SuppressWarnings("serial")
	public static class IllegalCharException extends Exception {
		public IllegalCharException(String message) {
			super(message);
		}
	}
	
	/**
	 * Thrown by Scanner when an int literal is not a value that can be represented by an int.
	 */
	@SuppressWarnings("serial")
	public static class IllegalNumberException extends Exception {
	public IllegalNumberException(String message){
		super(message);
		}
	}
	

	/**
	 * Holds the line and position in the line of a token.
	 */
	static class LinePos {
		public final int line;
		public final int posInLine;
		
		public LinePos(int line, int posInLine) {
			super();
			this.line = line;
			this.posInLine = posInLine;
		}

		@Override
		public String toString() {
			return "LinePos [line=" + line + ", posInLine=" + posInLine + "]";
		}
	}

	

	public class Token {
		public final Kind kind;
		public final int pos;  //position in input array
		public final int length;  

		//returns the text of this Token
		public String getText() {
			return chars.substring(pos, pos+length);
		}
		
		//returns a LinePos object representing the line and column of this Token
		LinePos getLinePos(){
			int i;
			for(i=0; i<lines.size(); i++){
				if(lines.get(i)>pos)
					break;
			}
			return new LinePos(i,pos - lines.get(i-1) - 1);
		}

		Token(Kind kind, int pos, int length) {
			this.kind = kind;
			this.pos = pos;
			this.length = length;
		}

		/** 
		 * Precondition:  kind = Kind.INT_LIT,  the text can be represented with a Java int.
		 * Note that the validity of the input should have been checked when the Token was created.
		 * So the exception should never be thrown.
		 * 
		 * @return  int value of this token, which should represent an INT_LIT
		 * @throws NumberFormatException
		 */
		public int intVal() throws NumberFormatException{
			//TODO IMPLEMENT THIS
			return Integer.parseInt(getText());
		}
		
	}

	 
	private static enum State{
		START, IN_IKL, IN_INT;
	}	 


	Scanner(String chars) {
		this.chars = chars;
		tokens = new ArrayList<Token>();
		lines = new ArrayList<Integer>();

	}


	
	/**
	 * Initializes Scanner object by traversing chars and adding tokens to tokens list.
	 * 
	 * @return this scanner
	 * @throws IllegalCharException
	 * @throws IllegalNumberException
	 */
	public Scanner scan() throws IllegalCharException, IllegalNumberException {
		int pos = 0; 
		int length = 0;
		int end = chars.length();
		char ch;
		State state = State.START;
		boolean inComment = false;
		String tokenString;
		while(pos<end){
			ch = chars.charAt(pos);
			if(Character.isWhitespace(ch)){
				if(ch == '\n'){
					lines.add(pos);
					pos++;
					continue;
				}
				else{
					//TODO This part needs to be corrected.
					switch(state){
					case START: pos++;
								break;
					case IN_IKL: tokenString = chars.substring(pos-length, pos);			
					 			 getValidIKL(tokenString, pos, length);
					 			 state = State.START;
					 			 break;
					case IN_INT: tokenString = chars.substring(pos-length, pos);			
					 			 getValidIntLit(tokenString, pos, length);
					 			 state = State.START;
					 			 break;
					}
					continue;
								
				}
			}
			else if(inComment){
				if(chars.charAt(pos)=='/' && chars.charAt(pos-1)=='*'){
					inComment = false;
				}
				pos++;
				continue;
			}
			else if(pos+1 < end && chars.charAt(pos+1)=='*' && chars.charAt(pos)=='/'){
				inComment = true;
				switch(state){
				case START: pos++;
							break;
				case IN_IKL: tokenString = chars.substring(pos-length, pos);			
				 			 getValidIKL(tokenString, pos, length);
				 			 state = State.START;
				 			 break;
				case IN_INT: tokenString = chars.substring(pos-length, pos);			
				 			 getValidIntLit(tokenString, pos, length);
				 			 state = State.START;
				 			 break;
				}
				continue;
			}
			else{
				switch(state){
					case START:
						length = 1;
						if(Character.isJavaIdentifierStart(ch)){
							state = State.IN_IKL;
							pos++;
							continue;
						} else if(ch == '0'){
							tokens.add(new Token(Kind.INT_LIT, pos, length));
						} else if(Character.isDigit(ch)){
							state = State.IN_INT;
							pos++;
							continue;
						} else{
							switch(ch){
							case ';':
								tokens.add(new Token(Kind.SEMI, pos, length));
								pos++;
								break;
							case ',':
								tokens.add(new Token(Kind.COMMA, pos, length));
								pos++;
								break;
							case '(':
								tokens.add(new Token(Kind.LPAREN, pos, length));
								pos++;
								break;
							case ')':
								tokens.add(new Token(Kind.RPAREN, pos, length));
								pos++;
								break;
							case '{':
								tokens.add(new Token(Kind.LBRACE, pos, length));
								pos++;
								break;
							case '}':
								tokens.add(new Token(Kind.RBRACE, pos, length));
								pos++;
								break;
							case '-':
								if(pos+1<end && chars.charAt(pos+1)=='>'){
									tokens.add(new Token(Kind.ARROW, pos, 2));
									pos += 2;
								}else{
									tokens.add(new Token(Kind.MINUS, pos, length));
									pos++;
								}
								break;
							case '|':
								if(pos+2<end && chars.charAt(pos+2)=='>' && chars.charAt(pos+1)=='-'){
									tokens.add(new Token(Kind.BARARROW, pos, 3));
									pos += 3;
								}else{
									tokens.add(new Token(Kind.OR, pos, length));
									pos++;
								}
								break;
							case '&':
								tokens.add(new Token(Kind.AND, pos, length));
								pos++;
								break;
							case '=':
								if(pos+1<end && chars.charAt(pos+1)=='='){
									tokens.add(new Token(Kind.EQUAL, pos, 2));
									pos+=2;									
								}
								else
									throw new IllegalCharException("= is not a valid character.");
								break;
							case '!':
								if(pos+1<end && chars.charAt(pos+1)=='='){
									tokens.add(new Token(Kind.NOTEQUAL, pos, 2));
									pos+=2;														
								}else{
									tokens.add(new Token(Kind.NOT, pos, length));
									pos++;
								}
								break;
							case '<':
								if(pos+1<end && chars.charAt(pos+1)=='='){
									tokens.add(new Token(Kind.LE, pos, 2));
									pos+=2;														
								}else if(pos+1<end && chars.charAt(pos+1)=='-'){
									tokens.add(new Token(Kind.ASSIGN, pos, 2));
									pos+=2;														
								}else{
									tokens.add(new Token(Kind.LT, pos, length));
									pos++;
								}
								break;
							case '>':
								if(pos+1<end && chars.charAt(pos+1)=='='){
									tokens.add(new Token(Kind.GE, pos, 2));
									pos+=2;														
								}else{
									tokens.add(new Token(Kind.GT, pos, length));
									pos++;
								}
								break;
							case '+':
								tokens.add(new Token(Kind.PLUS, pos, length));
								pos++;
								break;
							case '/':
								tokens.add(new Token(Kind.DIV, pos, length));
								pos++;
								break;
							case '*':
								tokens.add(new Token(Kind.TIMES, pos, length));
								pos++;
								break;
							case '%':
								tokens.add(new Token(Kind.MOD, pos, length));
								pos++;
								break;
							default:
								throw new IllegalCharException(ch + " is not a valid character.");
							}
							
						}
						break;

					case IN_IKL:
						if(!Character.isJavaIdentifierPart(ch)){
							tokenString = chars.substring(pos-length+1, pos);
							getValidIKL(tokenString, pos, length);
							state = State.START;
						}
						else{
							length++;
							pos++;
						}
						break;
					case IN_INT:
						if(Character.isDigit(ch)){
							length++;
							pos++;			
						}
						else{
							tokenString = chars.substring(pos-length, pos);
							getValidIntLit(tokenString,pos,length);
							state = State.START;
						}
						break;
					
				}
			}
		}
		if(inComment){
			throw new IllegalCharException("Comment has not been closed.");
		}
		else if(state == State.IN_INT){
			tokenString = chars.substring(pos-length, pos);
			getValidIntLit(tokenString, pos, length);
		} 
		else if(state == State.IN_IKL){
			tokenString = chars.substring(pos-length, pos);			
			getValidIKL(tokenString, pos, length);
		}
		tokens.add(new Token(Kind.EOF,pos,length));
		return this;  
	}

	void getValidIntLit(String tokenString, int pos, int length) throws IllegalNumberException{
		try{
		int ui = Integer.parseUnsignedInt(tokenString);
		if(Integer.compareUnsigned(ui, Integer.MAX_VALUE)==1)
			throw new IllegalNumberException("" + Long.toString(ui & 0xFFFFFFFFL) + " is not a valid integer.");
		else
			tokens.add(new Token(Kind.INT_LIT, pos-length, length));
		}
		catch(NumberFormatException e) {
			throw new IllegalNumberException(tokenString + " is not a valid integer.");
		}
	}
	
	void getValidIKL(String tokenString, int pos, int length){
		switch(tokenString){
		case "true":
			tokens.add(new Token(Kind.KW_TRUE, pos-length, length));
			break;
		case "false":
			tokens.add(new Token(Kind.KW_FALSE, pos-length, length));
			break;
		case "integer":
			tokens.add(new Token(Kind.KW_INTEGER, pos-length, length));
			break;
		case "boolean":
			tokens.add(new Token(Kind.KW_BOOLEAN, pos-length, length));
			break;
		case "image":
			tokens.add(new Token(Kind.KW_IMAGE, pos-length, length));
			break;
		case "url":
			tokens.add(new Token(Kind.KW_URL, pos-length, length));
			break;
		case "file":
			tokens.add(new Token(Kind.KW_FILE, pos-length, length));
			break;
		case "frame":
			tokens.add(new Token(Kind.KW_FRAME, pos-length, length));
			break;
		case "while":
			tokens.add(new Token(Kind.KW_WHILE, pos-length, length));
			break;
		case "if":
			tokens.add(new Token(Kind.KW_IF, pos-length, length));
			break;
		case "blur":
			tokens.add(new Token(Kind.OP_BLUR, pos-length, length));
			break;
		case "gray":
			tokens.add(new Token(Kind.OP_GRAY, pos-length, length));
			break;
		case "convolve":
			tokens.add(new Token(Kind.OP_CONVOLVE, pos-length, length));
			break;
		case "screenheight":
			tokens.add(new Token(Kind.KW_SCREENHEIGHT, pos-length, length));
			break;
		case "screenwidth":
			tokens.add(new Token(Kind.KW_SCREENWIDTH, pos-length, length));
			break;
		case "width":
			tokens.add(new Token(Kind.OP_WIDTH, pos-length, length));
			break;
		case "height":
			tokens.add(new Token(Kind.OP_HEIGHT, pos-length, length));
			break;
		case "xloc":
			tokens.add(new Token(Kind.KW_XLOC, pos-length, length));
			break;
		case "yloc":
			tokens.add(new Token(Kind.KW_YLOC, pos-length, length));
			break;
		case "hide":
			tokens.add(new Token(Kind.KW_HIDE, pos-length, length));
			break;
		case "show":
			tokens.add(new Token(Kind.KW_SHOW, pos-length, length));
			break;
		case "move":
			tokens.add(new Token(Kind.KW_MOVE, pos-length, length));
			break;
		case "sleep":
			tokens.add(new Token(Kind.OP_SLEEP, pos-length, length));
			break;
		case "scale":
			tokens.add(new Token(Kind.KW_SCALE, pos-length, length));
			break;
		case "eof":
			tokens.add(new Token(Kind.EOF, pos-length, 0));
			break;
		default:
			tokens.add(new Token(Kind.IDENT, pos-length, length));
			break;
		}
	}

	final ArrayList<Token> tokens;
	final String chars;
	final ArrayList<Integer> lines;
	int tokenNum;

	/*
	 * Return the next token in the token list and update the state so that
	 * the next call will return the Token..  
	 */
	public Token nextToken() {
		if (tokenNum >= tokens.size())
			return null;
		return tokens.get(tokenNum++);
	}
	
	/*
	 * Return the next token in the token list without updating the state.
	 * (So the following call to next will return the same token.)
	 */
	public Token peek(){
		if (tokenNum >= tokens.size())
			return null;
		return tokens.get(tokenNum+1);		
	}

	

	/**
	 * Returns a LinePos object containing the line and position in line of the 
	 * given token.  
	 * 
	 * Line numbers start counting at 0
	 * 
	 * @param t
	 * @return
	 */
	public LinePos getLinePos(Token t) {
		return t.getLinePos();
	}


}
