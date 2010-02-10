package music;

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
    /**
     * Test of parseDecls method, of class Parser.
     */
    @Test
    public void testParseDecls() {        
        String program = "mm = 34.56+(33-7);\nbb = 34.56+(33+7);";
        String fname = "pTest3";
        ArrayList errors = Lexer.lexString(program, fname);
//        System.out.println(Lexer.tokens);
//        System.out.println(errors);
        TokenStream t = Lexer.tokens;
        
        ArrayList<Decl> expResult = null;
        ArrayList<Decl> result = Parser.parseDecls(t);
        
        for (Decl i : result) {
            System.out.println(i.LHS.print());
            System.out.println(i.RHS.print());
        }
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testParseDecls2() {
        music.Symbol.init();
        String program = "mm = c3!c3&c4&(c2!c4);";
        String fname = "pTest3";
        ArrayList errors = Lexer.lexString(program, fname);
        System.out.println(Lexer.tokens);
        System.out.println(errors);
        TokenStream t = Lexer.tokens;
        ArrayList<Decl> expResult = null;
        ArrayList<Decl> result = Parser.parseDecls(t);
        for(Decl i : result)
        {
            System.out.println(i.print());
        }

        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseDecl method, of class Parser.
     */
    @Test
    public void testParseDecl() {
        System.out.println("parseDecl");
        music.Symbol.init();
        String program = "mm = 34.56+(33-7);\nbb = 34.56+(33+7);";
        String fname = "pTest3";
        ArrayList errors = Lexer.lexString(program, fname);
//        System.out.println(Lexer.tokens);
//        System.out.println(errors);
        Parser.setToks(Lexer.tokens);

        Decl expResult = null;
        Decl result = Parser.parseDecl();

        System.out.println(result.print());
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parselhs method with program "x=5"
     */
    @Test
    public void testParselhs() {
        System.out.println("parselhs");
        TokenStream tokens = new TokenStream();
        tokens.addToken(new Token(Symbol.toSymbol("x"), "x", new Place("testParsePat", 0, 1), TokenType.varToken));
        tokens.addToken(new Token(Symbol.toSymbol("="), "=", new Place("testParsePat", 0, 2), TokenType.opToken));
        tokens.addToken(new Token(Symbol.toSymbol("5"), "5", new Place("testParsePat", 0, 3), TokenType.numberToken));
        Parser.setToks(tokens);
        ArrayList<Pat> result = Parser.parselhs();
        Pat e = result.get(0);
        compareParsedObjects(e, new PatVar(new Token(Symbol.toSymbol("x"), "x", new Place("testParsePat", 0, 1), TokenType.varToken)));
    }

    /**
     * Test of parselhs method with program "f x=x+1"
     */
    @Test
    public void testParselhs2(){
        System.out.println("parselhs2");
        TokenStream tokens = new TokenStream();
        tokens.addToken(new Token(Symbol.toSymbol("f"), "f", new Place("testParselhs", 0, 1), TokenType.varToken));
        tokens.addToken(new Token(Symbol.toSymbol(" "), " ", new Place("testParslhs", 0, 2), TokenType.whiteToken));
        tokens.addToken(new Token(Symbol.toSymbol("x"), "x", new Place("testParsePat", 0, 3), TokenType.varToken));
        tokens.addToken(new Token(Symbol.toSymbol("="), "=", new Place("testParsePat", 0, 4), TokenType.opToken));
        tokens.addToken(new Token(Symbol.toSymbol("x"), "x", new Place("testParsePat", 0, 5), TokenType.varToken));
        tokens.addToken(new Token(Symbol.toSymbol("+"), "+", new Place("testParsePat", 0, 6), TokenType.opToken));
        tokens.addToken(new Token(Symbol.toSymbol("1"), "1", new Place("testParsePat", 0, 7), TokenType.numberToken));
        Parser.setToks(tokens);
        ArrayList<Pat> result = Parser.parselhs();
        Pat e = result.get(0);
        compareParsedObjects(new PatVar(new Token(Symbol.toSymbol("f"), "f", new Place("testParsePat", 0, 1), TokenType.varToken)), e);
        e = result.get(1);
        compareParsedObjects(new PatVar(new Token(Symbol.toSymbol("x"), "x", new Place("testParsePat", 0, 3), TokenType.varToken)), e);
    }

    /**
     * Test of parsePat method, with a numberToken.
     */
    @Test
    public void testParsePat() {
        System.out.println("Testing parsePat");
        Pat expResult = new PatConst(new Token(Symbol.toSymbol("5"), "5", new Place("testParsePat", 0, 1), TokenType.numberToken));
        TokenStream tokens = new TokenStream();
        tokens.addToken(new Token(Symbol.toSymbol("5"), "5", new Place("testParsePat", 0, 1), TokenType.numberToken));
        Parser.setToks(tokens);
        Pat result = Parser.parsePat();
        compareParsedObjects(expResult, result);
    }

    /**
     * The parseAPat method calls the next() method on its TokenStream toks.
     * Since our token is in column 0 the next method returns a Token.semicolon
     * The semicolon causes the ParsePat method to hit its else statement and
     * generate a ParseError. This is believed to be the correct behavior.
     */
    @Test
    public void testParsePat2() {
        System.out.println("Testing parsePat2");
        Pat expResult = new PatConst(new Token(Symbol.toSymbol("5"), "5", new Place("testParsePat", 0, 0), TokenType.numberToken));
        TokenStream tokens = new TokenStream();
        tokens.addToken(new Token(Symbol.toSymbol("5"), "5", new Place("testParsePat", 0, 0), TokenType.numberToken));
        Parser.setToks(tokens);
        try{
            Parser.parseAPat();
        }
        catch (ParseError e){
            return;
        }
        fail("The program should have generated an exception");
    }

    /**
     * Tests the parsePat method with a varToken
     */
    @Test
    public void testParsePat3() {
        System.out.println("Testing parsePat3");
        Pat expResult = new PatVar(new Token(Symbol.toSymbol("a"), "a", new Place("testParsePat", 0, 1), TokenType.varToken));
        TokenStream tokens = new TokenStream();
        tokens.addToken(new Token(Symbol.toSymbol("a"), "a", new Place("testParsePat", 0, 1), TokenType.varToken));
        Parser.setToks(tokens);
        Pat result = Parser.parsePat();
        compareParsedObjects(expResult, result);

    }

    /**
     * Tests the parsePat method with an underscore token
     */
    @Test
    public void testParsePat4() {
        System.out.println("Testing parsePat4");
        Pat expResult = new PatAny();
        TokenStream tokens = new TokenStream();
        tokens.addToken(new Token(Symbol.toSymbol("_"), "_", new Place("testParsePat4", 0, 1), TokenType.puncToken));
        Parser.setToks(tokens);
        Pat result = Parser.parsePat();
        compareParsedObjects(expResult, result);

    }

    /**
     * Test parsePat with token stream containing an var surrounded in parens
     */
    @Test
    public void testParsePat5() {
        System.out.println("Testing parsePat5");
        Pat expResult = new PatVar(new Token(Symbol.toSymbol("f"), "f", new Place("testParsePat", 0, 2), TokenType.varToken));
        TokenStream tokens = new TokenStream();
        tokens.addToken(new Token(Symbol.toSymbol("("), "(", new Place("testParsePat5", 0, 1), TokenType.puncToken));
        tokens.addToken(new Token(Symbol.toSymbol("f"), "f", new Place("testParsePat5", 0, 2), TokenType.varToken));
        tokens.addToken(new Token(Symbol.toSymbol(")"), ")", new Place("testParsePat5", 0, 3), TokenType.puncToken));
        Parser.setToks(tokens);
        Pat result = Parser.parsePat();
        compareParsedObjects(expResult, result);
    }

    /**
     * Test parsePat with token stream containing two vars surrounded in parens
     */
    @Test
    public void testParsePat6() {
        System.out.println("Testing parsePat6");
        ArrayList<Pat> expPats = new ArrayList<Pat>();
        expPats.add(new PatVar(new Token(Symbol.toSymbol("f"), "f", new Place("testParsePat6", 0, 2), TokenType.varToken)));
        expPats.add(new PatConst(new Token(Symbol.toSymbol("4"), "4", new Place("testParsePat6", 0, 4), TokenType.numberToken)));
        PatTuple expResult = new PatTuple(expPats);
        TokenStream tokens = new TokenStream();
        tokens.addToken(new Token(Symbol.toSymbol("("), "(", new Place("testParsePat6", 0, 1), TokenType.puncToken));
        tokens.addToken(new Token(Symbol.toSymbol("f"), "f", new Place("testParsePat6", 0, 2), TokenType.varToken));
        tokens.addToken(new Token(Symbol.toSymbol(","), ",", new Place("testParsePat6", 0, 3), TokenType.puncToken));
        tokens.addToken(new Token(Symbol.toSymbol("4"), "4", new Place("testParsePat6", 0, 4), TokenType.numberToken));
        tokens.addToken(new Token(Symbol.toSymbol(")"), ")", new Place("testParsePat6", 0, 5), TokenType.puncToken));
        Parser.setToks(tokens);
        Pat result = Parser.parsePat();
        compareParsedObjects(expResult, result);
    }

    /**
     * Test parsePat with token stream containing two vars surrounded in brackets
     */
    @Test
    public void testParsePat7() {
        System.out.println("Testing parsePat7");
        ArrayList<Pat> expPats = new ArrayList<Pat>();
        expPats.add(new PatVar(new Token(Symbol.toSymbol("f"), "f", new Place("testParsePat7", 0, 2), TokenType.varToken)));
        expPats.add(new PatConst(new Token(Symbol.toSymbol("4"), "4", new Place("testParsePat7", 0, 4), TokenType.numberToken)));
        PatList expResult = new PatList(expPats);
        TokenStream tokens = new TokenStream();
        tokens.addToken(new Token(Symbol.toSymbol("["), "[", new Place("testParsePat7", 0, 1), TokenType.puncToken));
        tokens.addToken(new Token(Symbol.toSymbol("f"), "f", new Place("testParsePat7", 0, 2), TokenType.varToken));
        tokens.addToken(new Token(Symbol.toSymbol(","), ",", new Place("testParsePat7", 0, 3), TokenType.puncToken));
        tokens.addToken(new Token(Symbol.toSymbol("4"), "4", new Place("testParsePat7", 0, 4), TokenType.numberToken));
        tokens.addToken(new Token(Symbol.toSymbol("]"), "]", new Place("testParsePat7", 0, 5), TokenType.puncToken));
        Parser.setToks(tokens);
        Pat result = Parser.parsePat();Token t1 = new Token(Symbol.toSymbol("("), "(", new Place("testParseAExp", 0, 1), TokenType.varToken);
        compareParsedObjects(expResult, result);
    }

    /**
     * Test of parseAExp method, using a numberToken.
     */
    @Test
    public void testParseAExp() {
        System.out.println("parseAExp");
        Token t1 = new Token(Symbol.toSymbol("42"), "42", new Place("testParseAExp", 0, 1), TokenType.numberToken);
        TokenStream tokens = new TokenStream();
        tokens.addToken(t1);
        Parser.setToks(tokens);
        Exp expResult = new ExpConst(t1);
        Exp result = Parser.parseAExp();
        compareParsedObjects(expResult, result);
    }

    /**
     * Tests parseAExp method with a varToken
     */
    @Test
    public void testParseAExp2(){
        System.out.println("parseAExp2");
        Token t1 = new Token(Symbol.toSymbol("asdf"), "asdf", new Place("testParseAExp", 0, 1), TokenType.varToken);
        TokenStream tokens = new TokenStream();
        tokens.addToken(t1);
        Parser.setToks(tokens);
        Exp expResult = new ExpVar(t1);
        Exp result = Parser.parseAExp();
        compareParsedObjects(expResult, result);
    }
    //Tests a stream like (4), should get just a 4 Token.
    @Test
    public void testParseAExp3(){
        System.out.println("parseAExp3");
        TokenStream tokens = new TokenStream();
        tokens.addToken(new Token(Symbol.toSymbol("("), "(", new Place("testParseAExp", 0, 1), TokenType.puncToken));
        Token t1 = new Token(Symbol.toSymbol("4"), "4", new Place("testParseAExp", 0, 2), TokenType.numberToken);
        tokens.addToken(t1);
        tokens.addToken(new Token(Symbol.toSymbol(")"), ")", new Place("testParseAExp", 0, 3), TokenType.puncToken));
        Parser.setToks(tokens);
        Exp expResult = new ExpConst(t1);
        Exp result = Parser.parseAExp();
        System.out.println(result.print());
        System.out.println(expResult.print());
        compareParsedObjects(expResult, result);
    }
    //Test a tuple like (4,5)
    @Test
    public void testParseAExp4(){
        System.out.println("parseAExp4");
        TokenStream tokens = new TokenStream();
        tokens.addToken(new Token(Symbol.toSymbol("("), "(", new Place("testParseAExp", 0, 1), TokenType.puncToken));
        Token t1 = new Token(Symbol.toSymbol("4"), "4", new Place("testParseAExp", 0, 2), TokenType.numberToken);
        tokens.addToken(t1);
        tokens.addToken(new Token(Symbol.toSymbol(","), ",", new Place("testParseAExp", 0, 3), TokenType.puncToken));
        Token t2 = new Token(Symbol.toSymbol("5"), "5", new Place("testParseAExp", 0, 4), TokenType.numberToken);
        tokens.addToken(t2);
        tokens.addToken(new Token(Symbol.toSymbol(")"), ")", new Place("testParseAExp", 0, 5), TokenType.puncToken));
        Parser.setToks(tokens);
        Exp e1 = new ExpConst(t1);
        Exp e2 = new ExpConst(t2);
        ArrayList exp = new ArrayList(2);
        exp.add(e1);
        exp.add(e2);
        Exp expResult = new ExpTuple(exp);
        Exp result = Parser.parseAExp();
        compareParsedObjects(expResult, result);
    }
     //Test a tuple like (4,variable)
    @Test
    public void testParseAExp4_cont(){
        System.out.println("parseAExp4");
        TokenStream tokens = new TokenStream();
        tokens.addToken(new Token(Symbol.toSymbol("("), "(", new Place("testParseAExp", 0, 1), TokenType.puncToken));
        Token t1 = new Token(Symbol.toSymbol("4"), "4", new Place("testParseAExp", 0, 2), TokenType.numberToken);
        tokens.addToken(t1);
        tokens.addToken(new Token(Symbol.toSymbol(","), ",", new Place("testParseAExp", 0, 3), TokenType.puncToken));
        Token t2 = new Token(Symbol.toSymbol("f"), "f", new Place("testParseAExp", 0, 4), TokenType.varToken);
        tokens.addToken(t2);
        tokens.addToken(new Token(Symbol.toSymbol(")"), ")", new Place("testParseAExp", 0, 5), TokenType.puncToken));
        Parser.setToks(tokens);
        Exp e1 = new ExpConst(t1);
        Exp e2 = new ExpVar(t2);
        ArrayList exp = new ArrayList(2);
        exp.add(e1);
        exp.add(e2);
        Exp expResult = new ExpTuple(exp);
        Exp result = Parser.parseAExp();
        compareParsedObjects(expResult, result);
    }
    //Tests a list like [8,9,10,11,12,13]
    @Test
    public void testParseAExp5(){
        System.out.println("parseAExp5");
        TokenStream tokens = new TokenStream();
        Token t1 = new Token(Symbol.toSymbol("9"), "9", new Place("testParseAExp", 0, 2), TokenType.numberToken);
        Token t2 = new Token(Symbol.toSymbol("10"), "10", new Place("testParseAExp", 0, 4), TokenType.numberToken);
        Token t3 = new Token(Symbol.toSymbol("11"), "11", new Place("testParseAExp", 0, 6), TokenType.numberToken);
        Token t4 = new Token(Symbol.toSymbol("12"), "12", new Place("testParseAExp", 0, 8), TokenType.numberToken);
        Token t5 = new Token(Symbol.toSymbol("13"), "13", new Place("testParseAExp", 0, 10), TokenType.numberToken);
        tokens.addToken(new Token(Symbol.toSymbol("["), "[", new Place("testParseAExp", 0, 1), TokenType.puncToken));
        tokens.addToken(t1);
        tokens.addToken(new Token(Symbol.toSymbol(","), ",", new Place("testParseAExp", 0, 3), TokenType.puncToken));
        tokens.addToken(t2);
        tokens.addToken(new Token(Symbol.toSymbol(","), ",", new Place("testParseAExp", 0, 5), TokenType.puncToken));
        tokens.addToken(t3);
        tokens.addToken(new Token(Symbol.toSymbol(","), ",", new Place("testParseAExp", 0, 7), TokenType.puncToken));
        tokens.addToken(t4);
        tokens.addToken(new Token(Symbol.toSymbol(","), ",", new Place("testParseAExp", 0, 9), TokenType.puncToken));
        tokens.addToken(t5);
        tokens.addToken(new Token(Symbol.toSymbol("]"), "]", new Place("testParseAExp", 0, 11), TokenType.puncToken));
        Parser.setToks(tokens);
        Exp e1 = new ExpConst(t1);
        Exp e2 = new ExpConst(t2);
        Exp e3 = new ExpConst(t3);
        Exp e4 = new ExpConst(t4);
        Exp e5 = new ExpConst(t5);
        ArrayList exp = new ArrayList();
        exp.add(e1);
        exp.add(e2);
        exp.add(e3);
        exp.add(e4);
        exp.add(e5);
        Exp expResult = new ExpList(exp);
        Exp result = Parser.parseAExp();
        System.out.println(result.print());
        System.out.println(expResult.print());
        compareParsedObjects(expResult, result);
    }
    /**
     * Test of prattReduce method, of class Parser.
     */
    @Test
    public void testPrattReduce() {
        System.out.println("prattReduce");
        ArrayList<Exp> exps = null;
        ArrayList<Token> ops = null;
        Token op = null;
        Parser.prattReduce(exps, ops, op);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseFexp method, of class Parser.
     */
    @Test
    public void testParseFexp() {
        System.out.println("parseFexp");
        Exp expResult = null;
        Exp result = Parser.parseFexp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testParseExp() {
        System.out.println("parseExp");
        Exp expResult = null;
        Exp result = Parser.parseExp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testRequirePunct() {        
        String program = "mm=34.56+33;";
        String fname = "ParserTest3";
        ArrayList errors = Lexer.lexString(program, fname);
        Symbol s = Symbol.equals;
        Parser.setToks(Lexer.tokens);
        System.out.println(Parser.toks.next());
        System.out.println(Parser.toks.next());
        Parser.requirePunct(s);
    }


    @Test
    public void testGetPrec() {
        System.out.println("getPrec");
        Symbol op = null;
        String expResult = "";
        String result = Parser.getPrec(op);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setToks method, of class Parser.
     */
    @Test
    public void testSetToks_TokenStream() {
        System.out.println("setToks");
        TokenStream t = null;
        Parser.setToks(t);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseDecls method, of class Parser.
     */
    @Test
    public void testParseDecls_TokenStream() {
        System.out.println("parseDecls");
        TokenStream t = null;
        ArrayList<Decl> expResult = null;
        ArrayList<Decl> result = Parser.parseDecls(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseDecl method, of class Parser.
     */
    @Test
    public void testParseDecl1() {
        System.out.println("parseDecl");
        Decl expResult = null;
        Decl result = Parser.parseDecl();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parselhs method, of class Parser.
     */
    @Test
    public void testParselhs1() {
        System.out.println("parselhs");
        ArrayList<Pat> expResult = null;
        ArrayList<Pat> result = Parser.parselhs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseAPat method, of class Parser.
     */
    @Test
    public void testParseAPat1() {
        System.out.println("parseAPat");
        Pat expResult = null;
        Pat result = Parser.parseAPat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseExp method, of class Parser.
     */
    @Test
    public void testParseExp1() {
        System.out.println("parseExp");
        Exp expResult = null;
        Exp result = Parser.parseExp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prattReduce method, of class Parser.
     */
    @Test
    public void testPrattReduce_3args() {
        System.out.println("prattReduce");
        ArrayList<Exp> exps = null;
        ArrayList<Token> ops = null;
        Token op = null;
        Parser.prattReduce(exps, ops, op);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseFexp method, of class Parser.
     */
    @Test
    public void testParseFexp1() {
        System.out.println("parseFexp");
        Exp expResult = null;
        Exp result = Parser.parseFexp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseAExp method, of class Parser.
     */
    @Test
    public void testParseAExp1() {
        System.out.println("parseAExp");
        Exp expResult = null;
        Exp result = Parser.parseAExp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of requirePunct method, of class Parser.
     */
    @Test
    public void testRequirePunct_Symbol() {
        System.out.println("requirePunct");
        Symbol s = null;
        Parser.requirePunct(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrec method, of class Parser.
     */
    @Test
    public void testGetPrec_Symbol() {
        System.out.println("getPrec");
        Symbol op = null;
        String expResult = "";
        String result = Parser.getPrec(op);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private void compareParsedObjects(ParsedObject expected, ParsedObject actual) {
        assertTrue(expected.compareTo(actual));
    }
}

