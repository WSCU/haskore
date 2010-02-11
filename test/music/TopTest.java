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
        System.out.println("\nTesting Program1(" + program + ")");
        ArrayList<Pair<String, String>> expResult = new ArrayList<Pair<String, String>>();
        expResult.add(new Pair("x", "1.0"));
        expResult.add(new Pair("y", "2.0"));
        expResult.add(new Pair("z", "3.0"));
        ArrayList result = Top.evaluateProgram(program);
        comparePairLists(expResult, result);
    }

    @Test
    public void testEvaluateProgram2() {
        String program = "a = 10; b = 2; c = a - b;";
        System.out.println("\nTesting Program2(" + program + ")");
        ArrayList<Pair<String, String>> expResult = new ArrayList<Pair<String, String>>();
        expResult.add(new Pair("a", "10.0"));
        expResult.add(new Pair("b", "2.0"));
        expResult.add(new Pair("c", "8.0"));
        ArrayList result = Top.evaluateProgram(program);
        comparePairLists(expResult, result);
    }

    @Test
    public void testEvaluateProgram3() {
        String program = "a = 10; b = 2; c = a - b; d = (c + a) - b; e = a - d;";
        System.out.println("\nTesting Program3(" + program + ")");
        ArrayList<Pair<String, String>> expResult = new ArrayList<Pair<String, String>>();
        expResult.add(new Pair("a", "10.0"));
        expResult.add(new Pair("b", "2.0"));
        expResult.add(new Pair("c", "8.0"));
        expResult.add(new Pair("d", "16.0"));
        expResult.add(new Pair("e", "-6.0"));
        ArrayList result = Top.evaluateProgram(program);
        comparePairLists(expResult, result);
    }

    private void comparePairLists(ArrayList<Pair<String, String>> expected, ArrayList<Pair<String, String>> actual) {
        for (Pair p1 : expected) {
            boolean p1Found = false;
            for (Pair p2 : actual) {
                if (p1.first.equals(p2.first)) {
                    System.out.println("Exp(" + p1.first + " = " + p1.second + ")   Act(" + p2.first + " = " + p2.second + ")");
                    assertTrue(p1.second.equals(p2.second));
                    p1Found = true;
                }
            }
            if (!p1Found) {
                fail("A value for " + p1.first + " was expected but not found");
            }
        }
    }
}
