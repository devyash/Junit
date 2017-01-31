package cop5556sp17;

import static cop5556sp17.Scanner.Kind.SEMI;
import static cop5556sp17.Scanner.Kind.INT_LIT;
import static cop5556sp17.Scanner.Kind.KW_IF;
import static cop5556sp17.Scanner.Kind.OR;
import static cop5556sp17.Scanner.Kind.NOTEQUAL;
import static cop5556sp17.Scanner.Kind.MINUS;
import static cop5556sp17.Scanner.Kind.BARARROW;
import static cop5556sp17.Scanner.Kind.ARROW;
import static cop5556sp17.Scanner.Kind.GT;
import static cop5556sp17.Scanner.Kind.LE;
import static cop5556sp17.Scanner.Kind.GE;
import static cop5556sp17.Scanner.Kind.LT;
import static cop5556sp17.Scanner.Kind.ASSIGN;
import static cop5556sp17.Scanner.Kind.EQUAL;
import static cop5556sp17.Scanner.Kind.AND;
import static cop5556sp17.Scanner.Kind.PLUS;
import static cop5556sp17.Scanner.Kind.TIMES;
import static cop5556sp17.Scanner.Kind.MOD;
import static cop5556sp17.Scanner.Kind.DIV;
import static cop5556sp17.Scanner.Kind.NOT;
import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cop5556sp17.Scanner.IllegalCharException;
import cop5556sp17.Scanner.IllegalNumberException;

public class ScannerTest {

	@Rule
    public ExpectedException thrown = ExpectedException.none();


	
	@Test
	public void testEmpty() throws IllegalCharException, IllegalNumberException {
		String input = "";
		Scanner scanner = new Scanner(input);
		scanner.scan();
	}

	@Test
	public void testSemiConcat() throws IllegalCharException, IllegalNumberException {
		//input string
		String input = ";;;";
		//create and initialize the scanner
		Scanner scanner = new Scanner(input);
		scanner.scan();
		//get the first token and check its kind, position, and contents
		Scanner.Token token = scanner.nextToken();
		assertEquals(SEMI, token.kind);
		assertEquals(0, token.pos);
		String text = SEMI.getText();
		assertEquals(text.length(), token.length);
		assertEquals(text, token.getText());
		//get the next token and check its kind, position, and contents
		Scanner.Token token1 = scanner.nextToken();
		assertEquals(SEMI, token1.kind);
		assertEquals(1, token1.pos);
		assertEquals(text.length(), token1.length);
		assertEquals(text, token1.getText());
		Scanner.Token token2 = scanner.nextToken();
		assertEquals(SEMI, token2.kind);
		assertEquals(2, token2.pos);
		assertEquals(text.length(), token2.length);
		assertEquals(text, token2.getText());
		//check that the scanner has inserted an EOF token at the end
		Scanner.Token token3 = scanner.nextToken();
		assertEquals(Scanner.Kind.EOF,token3.kind);
	}

	@Test
	public void testOperator() throws IllegalCharException, IllegalNumberException {
		//input string
		String input = ";;;!= | - > |-> -> <= >= <-\n==\r& < + * / % !";
		//create and initialize the scanner
		Scanner scanner = new Scanner(input);
		scanner.scan();
		//get the first token and check its kind, position, and contents
		Scanner.Token token = scanner.nextToken();
		assertEquals(SEMI, token.kind);
		assertEquals(0, token.pos);
		String text = SEMI.getText();
		assertEquals(text.length(), token.length);
		assertEquals(text, token.getText());
		//get the next token and check its kind, position, and contents
		Scanner.Token token1 = scanner.nextToken();
		assertEquals(SEMI, token1.kind);
		assertEquals(1, token1.pos);
		assertEquals(text.length(), token1.length);
		assertEquals(text, token1.getText());
		Scanner.Token token2 = scanner.nextToken();
		assertEquals(SEMI, token2.kind);
		assertEquals(2, token2.pos);
		assertEquals(text.length(), token2.length);
		assertEquals(text, token2.getText());
		Scanner.Token token4 = scanner.nextToken();
		assertEquals(NOTEQUAL, token4.kind);
		assertEquals(3, token4.pos);
		text = NOTEQUAL.getText();
		assertEquals(text.length(), token4.length);
		assertEquals(text, token4.getText());
		Scanner.Token token5 = scanner.nextToken();
		assertEquals(OR, token5.kind);
		assertEquals(6, token5.pos);
		text = OR.getText();
		assertEquals(text.length(), token5.length);
		assertEquals(text, token5.getText());
		Scanner.Token token6 = scanner.nextToken();
		assertEquals(MINUS, token6.kind);
		assertEquals(8, token6.pos);
		text = MINUS.getText();
		assertEquals(text.length(), token6.length);
		assertEquals(text, token6.getText());
		Scanner.Token token7 = scanner.nextToken();
		assertEquals(GT, token7.kind);
		assertEquals(10, token7.pos);
		text = GT.getText();
		assertEquals(text.length(), token7.length);
		assertEquals(text, token7.getText());
		Scanner.Token token8 = scanner.nextToken();
		assertEquals(BARARROW, token8.kind);
		assertEquals(12, token8.pos);
		text = BARARROW.getText();
		assertEquals(text.length(), token8.length);
		assertEquals(text, token8.getText());
		Scanner.Token token9 = scanner.nextToken();
		assertEquals(ARROW, token9.kind);
		assertEquals(16, token9.pos);
		text = ARROW.getText();
		assertEquals(text.length(), token9.length);
		assertEquals(text, token9.getText());
		Scanner.Token token10 = scanner.nextToken();
		assertEquals(LE, token10.kind);
		assertEquals(19, token10.pos);
		text = LE.getText();
		assertEquals(text.length(), token10.length);
		assertEquals(text, token10.getText());
		Scanner.Token token11 = scanner.nextToken();
		assertEquals(GE, token11.kind);
		assertEquals(22, token11.pos);
		text = GE.getText();
		assertEquals(text.length(), token11.length);
		assertEquals(text, token11.getText());
		Scanner.Token token12 = scanner.nextToken();
		assertEquals(ASSIGN, token12.kind);
		assertEquals(25, token12.pos);
		text = ASSIGN.getText();
		assertEquals(text.length(), token12.length);
		assertEquals(text, token12.getText());
		Scanner.Token token13 = scanner.nextToken();
		assertEquals(EQUAL, token13.kind);
		assertEquals(28, token13.pos);
		text = EQUAL.getText();
		assertEquals(text.length(), token13.length);
		assertEquals(text, token13.getText());
		Scanner.Token token14 = scanner.nextToken();
		assertEquals(AND, token14.kind);
		assertEquals(31, token14.pos);
		text = AND.getText();
		assertEquals(text.length(), token14.length);
		assertEquals(text, token14.getText());
		Scanner.LinePos lp = scanner.getLinePos(token14);
		assertEquals(lp.toString(), "LinePos [line=" + 1 + ", posInLine=" + 3 + "]");
		Scanner.Token token15 = scanner.nextToken();
		assertEquals(LT, token15.kind);
		assertEquals(33, token15.pos);
		text = LT.getText();
		assertEquals(text.length(), token15.length);
		assertEquals(text, token15.getText());
		Scanner.Token token16 = scanner.nextToken();
		assertEquals(PLUS, token16.kind);
		assertEquals(35, token16.pos);
		text = PLUS.getText();
		assertEquals(text.length(), token16.length);
		assertEquals(text, token16.getText());
		Scanner.Token token17 = scanner.nextToken();
		assertEquals(TIMES, token17.kind);
		assertEquals(37, token17.pos);
		text = TIMES.getText();
		assertEquals(text.length(), token17.length);
		assertEquals(text, token17.getText());
		Scanner.Token token18 = scanner.nextToken();
		assertEquals(DIV, token18.kind);
		assertEquals(39, token18.pos);
		text = DIV.getText();
		assertEquals(text.length(), token18.length);
		assertEquals(text, token18.getText());
		Scanner.Token token19 = scanner.nextToken();
		assertEquals(MOD, token19.kind);
		assertEquals(41, token19.pos);
		text = MOD.getText();
		assertEquals(text.length(), token19.length);
		assertEquals(text, token19.getText());
		Scanner.Token token20 = scanner.nextToken();
		assertEquals(NOT, token20.kind);
		assertEquals(43, token20.pos);
		text = NOT.getText();
		assertEquals(text.length(), token20.length);
		assertEquals(text, token20.getText());
		//check that the scanner has inserted an EOF token at the end
		Scanner.Token token3 = scanner.nextToken();
		assertEquals(Scanner.Kind.EOF,token3.kind);
	}
	
	
	/**
	 * This test illustrates how to check that the Scanner detects errors properly. 
	 * In this test, the input contains an int literal with a value that exceeds the range of an int.
	 * The scanner should detect this and throw and IllegalNumberException.
	 * 
	 * @throws IllegalCharException
	 * @throws IllegalNumberException
	 */
	@Test
	public void testIntOverflowError() throws IllegalCharException, IllegalNumberException{
		String input = "99999999999999999";
		Scanner scanner = new Scanner(input);
		thrown.expect(IllegalNumberException.class);
		scanner.scan();		
	}
	@Test
	public void testIllCharError() throws IllegalCharException, IllegalNumberException{
		String input = "99#999999999999999";
		Scanner scanner = new Scanner(input);
		thrown.expect(IllegalCharException.class);
		scanner.scan();		
	}
	@Test
	public void testOpenComent() throws IllegalCharException, IllegalNumberException{
		String input = "99999999/*999999999";
		Scanner scanner = new Scanner(input);
		thrown.expect(IllegalCharException.class);
		scanner.scan();		
	}

	@Test
	public void testConcated() throws IllegalCharException, IllegalNumberException {
		//input string
		String input = ";9999999if";
		//create and initialize the scanner
		Scanner scanner = new Scanner(input);
		scanner.scan();
		//get the first token and check its kind, position, and contents
		Scanner.Token token = scanner.nextToken();
		assertEquals(SEMI, token.kind);
		assertEquals(0, token.pos);
		String text = SEMI.getText();
		assertEquals(text.length(), token.length);
		assertEquals(text, token.getText());
		//get the next token and check its kind, position, and contents
		Scanner.Token token1 = scanner.nextToken();
		assertEquals(INT_LIT, token1.kind);
		assertEquals(1, token1.pos);
		assertEquals(7, token1.length);
		assertEquals("9999999", token1.getText());
		Scanner.Token token2 = scanner.nextToken();
		assertEquals(KW_IF, token2.kind);
		assertEquals(8, token2.pos);
		text = KW_IF.getText();
		assertEquals(text.length(), token2.length);
		assertEquals(text, token2.getText());
		//check that the scanner has inserted an EOF token at the end
		Scanner.Token token3 = scanner.nextToken();
		assertEquals(Scanner.Kind.EOF,token3.kind);
	}
	
	@Test
	public void testWhiteSpaced() throws IllegalCharException, IllegalNumberException {
		//input string
		String input = ";\n9999999 if";
		//create and initialize the scanner
		Scanner scanner = new Scanner(input);
		scanner.scan();
		//get the first token and check its kind, position, and contents
		Scanner.Token token = scanner.nextToken();
		assertEquals(SEMI, token.kind);
		assertEquals(0, token.pos);
		String text = SEMI.getText();
		assertEquals(text.length(), token.length);
		assertEquals(text, token.getText());
		//get the next token and check its kind, position, and contents
		Scanner.Token token1 = scanner.nextToken();
		Scanner.LinePos lp = scanner.getLinePos(token1);
		assertEquals(lp.toString(), "LinePos [line=" + 1 + ", posInLine=" + 0 + "]");
		assertEquals(INT_LIT, token1.kind);
		assertEquals(2, token1.pos);
		assertEquals(7, token1.length);
		assertEquals("9999999", token1.getText());
		Scanner.Token token2 = scanner.nextToken();
		assertEquals(KW_IF, token2.kind);
		assertEquals(10, token2.pos);
		text = KW_IF.getText();
		assertEquals(text.length(), token2.length);
		assertEquals(text, token2.getText());
		//check that the scanner has inserted an EOF token at the end
		Scanner.Token token3 = scanner.nextToken();
		assertEquals(Scanner.Kind.EOF,token3.kind);
	}
	@Test
	public void testComment() throws IllegalCharException, IllegalNumberException {
		//input string
		String input = ";999/*99###99*/if";
		//create and initialize the scanner
		Scanner scanner = new Scanner(input);
		scanner.scan();
		//get the first token and check its kind, position, and contents
		Scanner.Token token = scanner.nextToken();
		assertEquals(SEMI, token.kind);
		assertEquals(0, token.pos);
		String text = SEMI.getText();
		assertEquals(text.length(), token.length);
		assertEquals(text, token.getText());
		Scanner.Token token1 = scanner.nextToken();
		assertEquals(INT_LIT, token1.kind);
		assertEquals(1, token1.pos);
		assertEquals(3, token1.length);
		assertEquals("999", token1.getText());
		Scanner.Token token2 = scanner.nextToken();
		assertEquals(KW_IF, token2.kind);
		assertEquals(15, token2.pos);
		text = KW_IF.getText();
		assertEquals(text.length(), token2.length);
		assertEquals(text, token2.getText());
		//check that the scanner has inserted an EOF token at the end
		Scanner.Token token3 = scanner.nextToken();
		assertEquals(Scanner.Kind.EOF,token3.kind);
	}
	@Test
	public void testSeparated() throws IllegalCharException, IllegalNumberException {
		//input string
		String input = ";9999999;if";
		//create and initialize the scanner
		Scanner scanner = new Scanner(input);
		scanner.scan();
		//get the first token and check its kind, position, and contents
		Scanner.Token token = scanner.nextToken();
		assertEquals(SEMI, token.kind);
		assertEquals(0, token.pos);
		String text = SEMI.getText();
		assertEquals(text.length(), token.length);
		assertEquals(text, token.getText());
		//get the next token and check its kind, position, and contents
		Scanner.Token token1 = scanner.nextToken();
		assertEquals(INT_LIT, token1.kind);
		assertEquals(1, token1.pos);
		assertEquals(7, token1.length);
		assertEquals("9999999", token1.getText());
		Scanner.Token token2 = scanner.nextToken();
		assertEquals(SEMI, token2.kind);
		assertEquals(8, token2.pos);
		text = SEMI.getText();
		assertEquals(text.length(), token2.length);
		assertEquals(text, token2.getText());
		Scanner.Token token3 = scanner.nextToken();
		assertEquals(KW_IF, token3.kind);
		assertEquals(9, token3.pos);
		text = KW_IF.getText();
		assertEquals(text.length(), token3.length);
		assertEquals(text, token3.getText());
		//check that the scanner has inserted an EOF token at the end
		Scanner.Token token4 = scanner.nextToken();
		assertEquals(Scanner.Kind.EOF,token4.kind);
	}
	
}
