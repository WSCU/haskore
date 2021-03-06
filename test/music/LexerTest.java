package music;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import music.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author austin
 */
public class LexerTest {
    

    public LexerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
         
    }

    @After
    public void tearDown() {
       
    }

    @Test
    public void testLexString(){
        music.Symbol.init();
        String program = "m = 34.56.func";
        String fname = "LexerTest3";
        TokenStream expResult = new TokenStream();
        expResult.addToken(new Token(Symbol.toSymbol("m"), "m", new Place( 0, 0), TokenType.varToken));
        expResult.addToken(new Token(Symbol.toSymbol(""), " ", new Place( 0, 1), TokenType.whiteToken));
        expResult.addToken(new Token(Symbol.toSymbol("="), "=", new Place( 0, 2), TokenType.opToken));
        expResult.addToken(new Token(Symbol.toSymbol(""), " ", new Place( 0, 3), TokenType.whiteToken));
        expResult.addToken(new Token(Symbol.toSymbol("34.56"), "34.56", new Place( 0, 4), TokenType.numberToken));
        expResult.addToken(new Token(Symbol.toSymbol("."), ".", new Place( 0, 9), TokenType.opToken));
        expResult.addToken(new Token(Symbol.toSymbol("func"), "func", new Place( 0, 10), TokenType.varToken));

        expResult.addToken(Token.eof);
        System.out.println(expResult);
        System.out.println(Lexer.lexString(program));
        compareTokenStreams(expResult, Lexer.lexString(program));
    }

    @Test
    public void testLexString1() {
        String program = "    ";
        String fname = "LexerTest1";
        TokenStream expResult = new TokenStream();
        expResult.addToken(new Token(Symbol.toSymbol(""), "    ", new Place( 0, 0), TokenType.whiteToken));
        expResult.addToken(Token.eof);
        System.out.println(expResult);
        System.out.println(Lexer.lexString(program));
        compareTokenStreams(expResult, Lexer.lexString(program));
    }

    /**
     * Test of lexString method, of class Lexer.
     * Input is "a b"
     */
    @Test
    public void testLexString2() {
        String program = "a b";
        String fname = "LexerTest2";
        TokenStream expResult = new TokenStream();
        expResult.addToken(new Token(Symbol.toSymbol("a"), "a", new Place( 0, 0), TokenType.varToken));
        expResult.addToken(new Token(Symbol.toSymbol(""), " ", new Place( 0, 1), TokenType.whiteToken));
        expResult.addToken(new Token(Symbol.toSymbol("b"), "b", new Place( 0, 2), TokenType.varToken));
        expResult.addToken(Token.eof);
        System.out.println(expResult);
        System.out.println(Lexer.lexString(program));
        compareTokenStreams(expResult, Lexer.lexString(program));
    }

    /*
     * This test is getting 3.1 as two number tokens and an error token for the "."
     */
    @Test
    public void testLexString3() {
        String program = "3.1";
        String fname = "LexerTest3";
        TokenStream expResult = new TokenStream();
        expResult.addToken(new Token(Symbol.toSymbol("3.1"), "3.1", new Place( 0, 0), TokenType.numberToken));
        expResult.addToken(Token.eof);
        System.out.println(expResult);
        System.out.println(Lexer.lexString(program));
        compareTokenStreams(expResult, Lexer.lexString(program));
    }

    @Test
    public void testLexString4() {
        String program = "c3! (2+h)";
        String fname = "LexerTest4";
        TokenStream expResult = new TokenStream();
        expResult.addToken(new Token(Symbol.toSymbol("c3"), "c3", new Place( 0, 0), TokenType.varToken));
        expResult.addToken(new Token(Symbol.toSymbol("!"), "!", new Place( 0, 2), TokenType.opToken));
        expResult.addToken(new Token(Symbol.toSymbol(""), " ", new Place( 0, 3), TokenType.whiteToken));
        expResult.addToken(new Token(Symbol.toSymbol("("), "(", new Place( 0, 4), TokenType.puncToken));
        expResult.addToken(new Token(Symbol.toSymbol("2"), "2", new Place( 0, 5), TokenType.numberToken));
        expResult.addToken(new Token(Symbol.toSymbol("+"), "+", new Place( 0, 6), TokenType.opToken));
        expResult.addToken(new Token(Symbol.toSymbol("h"), "h", new Place( 0, 7), TokenType.varToken));
        expResult.addToken(new Token(Symbol.toSymbol(")"), ")", new Place( 0, 8), TokenType.puncToken));
        expResult.addToken(Token.eof);
        System.out.println(expResult);
        System.out.println(Lexer.lexString(program));
        compareTokenStreams(expResult, Lexer.lexString(program));
    }

    @Test
    public void testLexString5() {
        String program = "[](){,}";
        String fname = "LexerTest5";
        TokenStream expResult = new TokenStream();
        for (int i = 0; i < program.length(); i++) {
            expResult.addToken(new Token(Symbol.toSymbol("" + program.charAt(i)), "" + program.charAt(i), new Place( 0, i), TokenType.puncToken));
        }
        expResult.addToken(Token.eof);
        System.out.println(expResult);
        System.out.println(Lexer.lexString(program));
        compareTokenStreams(expResult, Lexer.lexString(program));
    }

    @Test
    public void testLexString6() {
        String program = "c&d --comment!@*$(@+_\n--more comment\n389384";
        String fname = "LexerTest6";
        TokenStream expResult = new TokenStream();
        expResult.addToken(new Token(Symbol.toSymbol("c"), "c", new Place( 0, 0), TokenType.varToken));
        expResult.addToken(new Token(Symbol.toSymbol("&"), "&", new Place( 0, 1), TokenType.opToken));
        expResult.addToken(new Token(Symbol.toSymbol("d"), "d", new Place( 0, 2), TokenType.varToken));
        expResult.addToken(new Token(Symbol.toSymbol(""), " ", new Place( 0, 3), TokenType.whiteToken));
        expResult.addToken(new Token(null, "--comment!@*$(@+_", new Place( 0, 4), TokenType.whiteToken));
        expResult.addToken(new Token(null, "--more comment", new Place( 1, 0), TokenType.whiteToken));
        expResult.addToken(new Token(Symbol.toSymbol("389384"), "389384", new Place( 2, 0), TokenType.numberToken));
        expResult.addToken(Token.eof);
        System.out.println(expResult);
        System.out.println(Lexer.lexString(program));
        compareTokenStreams(expResult, Lexer.lexString(program));
    }

    @Test
    public void testLexString7() {
        String program = "a\nb◄34";
        String fname = "LexerTest7";
        TokenStream expResult = new TokenStream();
        expResult.addToken(new Token(Symbol.toSymbol("a"), "a", new Place( 0, 0), TokenType.varToken));
        expResult.addToken(new Token(Symbol.toSymbol("b"), "b", new Place( 1, 0), TokenType.varToken));
        expResult.addToken(new Token(Symbol.toSymbol("◄"), "◄", new Place( 1, 1), TokenType.errorToken));
        expResult.addToken(new Token(Symbol.toSymbol("34"), "34", new Place( 1, 2), TokenType.numberToken));
        expResult.addToken(Token.eof);
        System.out.println(expResult);
        System.out.println(Lexer.lexString(program));
        compareTokenStreams(expResult, Lexer.lexString(program));
    }



   
    
   

private void compareTokenStreams(TokenStream expected, TokenStream actual) {
        if(actual.tokens.size() != expected.tokens.size()){
            fail("Number of tokens in Expected and Actual TokenStreams are different");
        }
        for (int i = 0; i < actual.tokens.size(); i++) {

            Token exp = expected.tokens.get(i);
            Token act = actual.tokens.get(i);
            System.out.println(exp.type);
            System.out.println(act.type);
            assertEquals("body", exp.body, act.body);
            assertEquals("symbol", exp.symbol, act.symbol);
            assertEquals("type", exp.type, act.type);
            assertEquals("place.col", exp.place.getCol(), act.place.getCol());
            assertEquals("place.line", exp.place.getLine(), act.place.getLine());
            
        }
    }

}