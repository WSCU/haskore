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
 * @author austin
 */
public class EnvTest {

    public EnvTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        music.Symbol.init();
        
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of eval method, of class Env.
     */
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
        String prog = "f c m = if (c == 5) m (f (c + 1) (m & up m 2)); \n m = f 0 c3;";
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
        printParse(binds);
        Value expVal = new ValNum(1.0);
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