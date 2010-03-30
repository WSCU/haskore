package music;

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
 * @author CIS412 Class
 */
public class ParserTest {

    public ParserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        music.Symbol.init();
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

//    @Test
//    public void testParselhs() {
//        System.out.println("parselhs");
//        String program = "x=5";
//        lexProgram(program);
//        ArrayList<Pat> result = Parser.parselhs();
//        Pat e = result.get(0);
//        compareParsedObjects(e, new PatVar(new Token(Symbol.toSymbol("x"), "x", new Place("testParsePat", 0, 1), TokenType.varToken)));
//    }
//
//    /**
//     * Test of parselhs method with program "f x=x+1"
//     */
//    @Test
//    public void testParselhs2() {
//        System.out.println("parselhs2");
//        String program = "f x=x+1";
//        lexProgram(program);
//        ArrayList<Pat> result = Parser.parselhs();
//        Pat e = result.get(0);
//        compareParsedObjects(new PatVar(new Token(Symbol.toSymbol("f"), "f", new Place("testParsePat", 0, 1), TokenType.varToken)), e);
//        e = result.get(1);
//        compareParsedObjects(new PatVar(new Token(Symbol.toSymbol("x"), "x", new Place("testParsePat", 0, 3), TokenType.varToken)), e);
//    }
//
//    /**
//     * Test of parsePat method, with a numberToken.
//     */
//    @Test
//    public void testParsePat() {
//        System.out.println("Testing parsePat");
//        Pat expResult = new PatConst(new Token(Symbol.toSymbol("5"), "5", new Place("testParsePat", 0, 1), TokenType.numberToken));
//        TokenStream tokens = new TokenStream();
//        tokens.addToken(new Token(Symbol.toSymbol("5"), "5", new Place("testParsePat", 0, 1), TokenType.numberToken));
//        Parser.setToks(tokens);
//        Pat result = Parser.parsePat();
//        compareParsedObjects(expResult, result);
//    }
//
//    /**
//     * The parseAPat method calls the next() method on its TokenStream toks.
//     * Since our token is in column 0 the next method returns a Token.semicolon
//     * The semicolon causes the ParsePat method to hit its else statement and
//     * generate a ParseError. This is believed to be the correct behavior.
//     */
//    @Test
//    public void testParsePat2() {
//        System.out.println("Testing parsePat2");
//        Pat expResult = new PatConst(new Token(Symbol.toSymbol("5"), "5", new Place("testParsePat", 0, 0), TokenType.numberToken));
//        TokenStream tokens = new TokenStream();
//        tokens.addToken(new Token(Symbol.toSymbol("5"), "5", new Place("testParsePat", 0, 0), TokenType.numberToken));
//        Parser.setToks(tokens);
//        try {
//            Parser.parseAPat();
//        } catch (ParseError e) {
//            return;
//        }
//        fail("The program should have generated an exception");
//    }
//
//    /**
//     * Tests the parsePat method with a varToken
//     */
//    @Test
//    public void testParsePat3() {
//        System.out.println("Testing parsePat3");
//        Pat expResult = new PatVar(new Token(Symbol.toSymbol("a"), "a", new Place("testParsePat", 0, 1), TokenType.varToken));
//        TokenStream tokens = new TokenStream();
//        tokens.addToken(new Token(Symbol.toSymbol("a"), "a", new Place("testParsePat", 0, 1), TokenType.varToken));
//        Parser.setToks(tokens);
//        Pat result = Parser.parsePat();
//        compareParsedObjects(expResult, result);
//
//    }
//
//    /**
//     * Tests the parsePat method with an underscore token
//     */
//    @Test
//    public void testParsePat4() {
//        System.out.println("Testing parsePat4");
//        Pat expResult = new PatAny();
//        TokenStream tokens = new TokenStream();
//        tokens.addToken(new Token(Symbol.toSymbol("_"), "_", new Place("testParsePat4", 0, 1), TokenType.puncToken));
//        Parser.setToks(tokens);
//        Pat result = Parser.parsePat();
//        compareParsedObjects(expResult, result);
//
//    }
//
//    /**
//     * Test parsePat with token stream containing an var surrounded in parens
//     */
//    @Test
//    public void testParsePat5() {
//        System.out.println("Testing parsePat5");
//        String program = " (f)";
//        Pat expResult = new PatVar(new Token(Symbol.toSymbol("f"), "f", new Place("testParsePat", 0, 2), TokenType.varToken));
//        lexProgram(program);
//        Pat result = Parser.parsePat();
//        compareParsedObjects(expResult, result);
//    }
//
//    /**
//     * Test parsePat with token stream containing two vars surrounded in parens
//     */
//    @Test
//    public void testParsePat6() {
//        System.out.println("Testing parsePat6");
//        ArrayList<Pat> expPats = new ArrayList<Pat>();
//        expPats.add(new PatVar(new Token(Symbol.toSymbol("f"), "f", new Place("testParsePat6", 0, 2), TokenType.varToken)));
//        expPats.add(new PatConst(new Token(Symbol.toSymbol("4"), "4", new Place("testParsePat6", 0, 4), TokenType.numberToken)));
//        PatTuple expResult = new PatTuple(expPats);
//        String program = " (f,4);";
//        lexProgram(program);
//        Pat result = Parser.parsePat();
//        compareParsedObjects(expResult, result);
//    }
//
//    /**
//     * Test parsePat with token stream containing two vars surrounded in brackets
//     */
//    @Test
//    public void testParsePat7() {
//        System.out.println("Testing parsePat7");
//        ArrayList<Pat> expPats = new ArrayList<Pat>();
//        expPats.add(new PatVar(new Token(Symbol.toSymbol("f"), "f", new Place("testParsePat7", 0, 2), TokenType.varToken)));
//        expPats.add(new PatConst(new Token(Symbol.toSymbol("4"), "4", new Place("testParsePat7", 0, 4), TokenType.numberToken)));
//        PatList expResult = new PatList(expPats);
//        String program = " [f,4]";
//        lexProgram(program);
//        Pat result = Parser.parsePat();
//        compareParsedObjects(expResult, result);
//    }
//
//    /**
//     * Test of parseAExp method, using a numberToken.
//     */
//    @Test
//    public void testParseAExp() {
//        System.out.println("parseAExp");
//        String program = " 42";
//        lexProgram(program);
//        Exp expResult = new ExpConst(Lexer.tokens.tokens.get(1));
//        Exp result = Parser.parseAExp();
//        compareParsedObjects(expResult, result);
//    }
//
//    /**
//     * Tests parseAExp method with a varToken
//     */
//    @Test
//    public void testParseAExp2() {
//        System.out.println("parseAExp2");
//        String program = " asdf";
//        lexProgram(program);
//        Exp expResult = new ExpVar(Lexer.tokens.tokens.get(1));
//        Exp result = Parser.parseAExp();
//        compareParsedObjects(expResult, result);
//    }
//
//    //Tests a stream like (4), should get just a 4 Token.
//    @Test
//    public void testParseAExp3() {
//        System.out.println("parseAExp3");
//        String program = " (4)";
//        lexProgram(program);
//        Exp expResult = new ExpConst(new Token(Symbol.toSymbol("4"), "4", new Place("testParseAExp", 0, 2), TokenType.numberToken));
//        Exp result = Parser.parseAExp();
//        System.out.println(result.print());
//        System.out.println(expResult.print());
//        compareParsedObjects(expResult, result);
//    }
//    //Test a tuple like (4,5)
//
//    @Test
//    public void testParseAExp4() {
//        System.out.println("parseAExp4");
//        String program = " (4,5)";
//        lexProgram(program);
//        Exp e1 = new ExpConst(new Token(Symbol.toSymbol("4"), "4", new Place("testParseAExp", 0, 2), TokenType.numberToken));
//        Exp e2 = new ExpConst(new Token(Symbol.toSymbol("5"), "5", new Place("testParseAExp", 0, 4), TokenType.numberToken));
//        ArrayList exp = new ArrayList(2);
//        exp.add(e1);
//        exp.add(e2);
//        Exp expResult = new ExpTuple(exp);
//        Exp result = Parser.parseAExp();
//        compareParsedObjects(expResult, result);
//    }
//    //Test a tuple like (4,variable)
//
//    @Test
//    public void testParseAExp4_cont() {
//        System.out.println("parseAExp4_cont");
//        String program = " (4,f)";
//        lexProgram(program);
//        ArrayList exp = new ArrayList(2);
//        exp.add(new ExpConst(Lexer.tokens.tokens.get(2)));
//        exp.add(new ExpConst(Lexer.tokens.tokens.get(4)));
//        Exp expResult = new ExpTuple(exp);
//        Exp result = Parser.parseAExp();
//        compareParsedObjects(expResult, result);
//    }
//    //Tests a list like [8,9,10,11,12,13]
//
//    @Test
//    public void testParseAExp5() {
//        System.out.println("parseAExp5");
//        String program = " [9,10,11,12,13]";
//        lexProgram(program);
//        ArrayList exp = new ArrayList();
//        exp.add(new ExpConst(Lexer.tokens.tokens.get(2)));
//        exp.add(new ExpConst(Lexer.tokens.tokens.get(4)));
//        exp.add(new ExpConst(Lexer.tokens.tokens.get(6)));
//        exp.add(new ExpConst(Lexer.tokens.tokens.get(8)));
//        exp.add(new ExpConst(Lexer.tokens.tokens.get(10)));
//        Exp expResult = new ExpList(exp);
//        Exp result = Parser.parseAExp();
//        System.out.println("Expected: " + expResult.print());
//        System.out.println("Actual: " + result.print());
//        compareParsedObjects(expResult, result);
//    }
//
//    /**
//     * Test of parseFexp method, of class Parser.
//     */
//    @Test
//    public void testParseFexp() {
//        System.out.println("parseFexp");
//        String program = " f x = x+3;";
//        lexProgram(program);
//        Exp result = Parser.parseFexp();
//        ExpCall expResult = new ExpCall(new ExpVar(Parser.toks.tokens.get(1)), new ExpVar(Parser.toks.tokens.get(3)));
//        compareParsedObjects(expResult, result);
//    }
//
//    @Test
//    public void testParseFexp2() {
//        System.out.println("parseFexp2");
//        String program = " x = 5;";
//        lexProgram(program);
//        Exp result = Parser.parseFexp();
//        ExpVar expResult = new ExpVar(Parser.toks.tokens.get(1));
//        compareParsedObjects(expResult, result);
//    }
//
//    @Test
//    public void testRequirePunct() {
//        String program = "mm=34.56+33;";
//        String fname = "ParserTest3";
//        ArrayList errors = Lexer.lexString(program, fname);
//        Symbol s = Symbol.equals;
//        Parser.setToks(Lexer.tokens);
//        System.out.println(Parser.toks.next());
//        System.out.println(Parser.toks.next());
//        Parser.requirePunct(s);
//    }

//    @Test
//    public void testRequirePunct() {
//        String program = "mm=34.56+33;";
//        String fname = "ParserTest3";
//        ArrayList errors = Lexer.lexString(program, fname);
//        Symbol s = Symbol.equals;
//        Parser.setToks(Lexer.tokens);
//        System.out.println(Parser.toks.next());
//        System.out.println(Parser.toks.next());
//        Parser.requirePunct(s);
//    }


    private void compareParsedObjects(ParsedObject expected, ParsedObject actual) {
        assertTrue(expected.compareTo(actual));
    }

    private void lexProgram(String program) {
        ArrayList<LexerError> errs = Lexer.lexString(program, "ParserTest.java");
        if (errs.size() > 0) {
            System.out.println(errs.size() + " errors while lexing " + program);
            for (LexerError e : errs) {
                System.out.println("Expected: " + e.expected + "\nRecieved: " + e.recieved);
            }
        }

        Parser p = new Parser(Lexer.lexString(program));
    }
//    @Test
//    public void testParseExp() {
//        System.out.println("parseExp");
//        Exp expResult = null;
//        Exp result = Parser.parseExp();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    /**
//     * Test of prattReduce method, of class Parser.
//     */
//    @Test
//    public void testPrattReduce() {
//        System.out.println("prattReduce");
//        ArrayList<Exp> exps = null;
//        ArrayList<Token> ops = null;
//        Token op = null;
//        Parser.prattReduce(exps, ops, op);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }    
//    @Test
//    public void testGetPrec() {
//        System.out.println("getPrec");
//        Symbol op = null;
//        String expResult = "";
//        String result = Parser.getPrec(op);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of parseDecls method, of class Parser.
     */
//    @Test
//    public void testParseDecls_TokenStream() {
//        System.out.println("parseDecls");
//        TokenStream t = null;
//        ArrayList<Decl> expResult = null;
//        ArrayList<Decl> result = Parser.parseDecls(t);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    /**
//     * Test of getPrec method, of class Parser.
//     */
//    @Test
//    public void testGetPrec_Symbol() {
//        System.out.println("getPrec");
//        Symbol op = null;
//        String expResult = "";
//        String result = Parser.getPrec(op);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}

