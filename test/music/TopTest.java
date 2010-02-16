/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author denny blankenbueler
 * Testing in the evaluation mechanisms.
 */
public class TopTest {

    public TopTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Symbol.init();
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
    public void testEvaluateProgram() {
        String program = "x = 1;\ny = 2;\nz = x + y;";
        System.out.println("\nTesting  evaluateProgram(" + program + ")");        
        ArrayList result = Top.evaluateProgram(program);
        testExpectedValues(result, "x", "1");
        testExpectedValues(result, "y", "2");
        testExpectedValues(result, "z", "3");
    }

    @Test
    public void testEvaluateProgram2() {
        String program = "a = 10; b = 2; c = a - b;";
        System.out.println("\nTesting evaluateProgram(" + program + ")");
        ArrayList result = Top.evaluateProgram(program);
        testExpectedValues(result, "a", "10");
        testExpectedValues(result, "b", "2");
        testExpectedValues(result, "c", "8");
    }

    @Test
    public void testEvaluateProgram3() {
        String program = "a = 10; b = 2; c = a - b; d = (c + a) - b; e = a - d;";
        System.out.println("\nTesting evaluateProgram(" + program + ")");                
        ArrayList result = Top.evaluateProgram(program);
        testExpectedValues(result, "a", "10");
        testExpectedValues(result, "b", "2");
        testExpectedValues(result, "c", "8");
        testExpectedValues(result, "d", "16");
        testExpectedValues(result, "e", "-6");
    }

    @Test
    public void testEvaluateProgram4() {
        //String program = "f x = if(x==0) 0 x-1;\nz = f 0;";
        String program = "abcd1234 = 3";
        System.out.println("\nTesting evaluateProgram(" + program + ")");
        ArrayList result = Top.evaluateProgram(program);
        //testExpectedValues(result, "a", "0");
        testExpectedValues(result, "abcd1234", "3");
    }

    /**
     * Searches through the list for the presenve
     * @param list This is a list of (key, value) pairs to search through
     * @param variable This is the variable to test the value of
     * @param expectedValue The expected value. The strings 1 and 1.0 are the same.
     */
    private void testExpectedValues(ArrayList<Pair<String, String>> list,  String variable, String expectedValue){
        boolean varFound = false;
        for (Pair p1 : list) {
            if(p1.first.equals(variable)){
                varFound = true;
                System.out.println("Expected(" + variable + " = " +expectedValue + ")   Actual(" + variable + " = " + p1.second + ")");
                assertEquals(Double.parseDouble(expectedValue), Double.parseDouble(p1.second.toString()));
            }
        }
        if(!varFound){
            fail(variable + "was not found in " + list );
        }
    }

    @Test
    public void testEval() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "x = 1;\ny = 2;\nz = x + y;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(3.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }
    @Test
    public void testEval2() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "x = 2+2;\ny = 2;\nz = x + y;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(6.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }
    @Test
    public void testEval3() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "f x = x+1;\nz = f 4;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(5.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }
    @Test
    public void testEval4() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "f x y = x+y;\nz = f 4 4;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(8.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }
    @Test
    public void testEval5() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "f 0 = 0;\nf x = x + f (x-1);\nz = f 3;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(1.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }
    @Test
    public void testEval5if() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "f x = if(x==0) 0 x-1;\nz = f 2;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(1.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }
    @Test
    public void testEval5if2() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "f x = if(x==0) 0 x-1;\nz = f 0;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(0.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }

    @Test
    public void testEval6() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "f x 0 = 1;\nf x y = x+y;\nz = f 0 0;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(1.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }
     @Test
    public void testEvalLong() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "f x y z = x+y+z;\nf 3 4 5 = 8;\nz = f 3 4 5;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(12.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }
    @Test
    public void testEval7() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "f 0 = 1;\nf x y = x+y;\nz = f 0;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(1.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }
    @Test
    public void testEval8() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "x = 2;\nm = if (x == 1) 2 1;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(1.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }
    @Test
    public void testEval9() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "f c m = if (c == 5) m (f (c + 1) (m ! up m 2)); \n m = f 0 c3;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(1.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }
     @Test
    public void testEvalmul() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "x = 1;\ny = 2;\nz = x * y;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(2.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }
     @Test
    public void testEvaldiv() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "x = 4;\ny = 2;\nz = x / y;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(2.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }
      @Test
    public void testEvalpow() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "x = 6;\ny = 2;\nz = x ^ y;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(36.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }

        @Test
    public void testEvalmod() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "x = 3;\ny = 2;\nz = x % y;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(1.0);
        Value resVal = eval(binds,userEnv);
        assertEquals(expVal.toString(),resVal.toString());
    }


        @Test
    public void testEvalPrec() {
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();
        String prog = "x = 3;\ny = 2;z = 4; a = 8;\nb = a^2*x+z/y;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(194.0);
        Value resVal = eval(binds,userEnv);
        
        assertEquals(expVal.toString(),resVal.toString());
    }
    public void printParse(ArrayList<Decl> li)
    {
        for ( Decl d : li){
            System.out.println(d.LHS.asVar().getBody() + " = " + d.RHS.print());
        }
    }

    public Value eval(ArrayList<Decl> li, EnvHash user)
    {
        for (Decl d : li) {
           user.add(d.LHS.asVar(), new Thunk(user, d.RHS));
        }
        // Need to remember the names on the lhs of the decls
        // Get first declaraction and find rhs
        try {
            Value endresult = null;
            for (Decl d : li) {
                Value result = user.eval(d.LHS.asVar());
                // if result is valMusic write a midi file other wise skip over
                if (result.isMusic()) {
                    //file name should be the same as the variable name in the ENV preformer
                    ValMusic temp = ((ValMusic) result);
                    Performance performer = temp.val.perform(0, new Modifier());
                    performer.writeToFile( d.LHS.asVar().getBody() + ".midi");
                    System.out.println("Music: " + d.LHS.asVar().getBody() + "\n " + temp.val.prettyPrint());
//                  performer.writeToFile("c:\\" + d.LHS.asVar().getBody() + ".midi");

                } else {
                    //skip over to next piece of result
                    System.out.println(result.toString());

                }
                endresult = result;
            }
            return endresult;

        } catch (ExecutionError e) {
            System.out.println("Execution Error: " + e.msg);
        }
        return null;
    }
}
