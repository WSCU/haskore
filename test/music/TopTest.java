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

    /**
     * This method takes the ArrayList of Pair(key, value) that comes from 
     * running top.evaluateProgram(program). The String LHS is the variable name
     * from the program, and the String RHS is the expected value of that
     * variable once the program has been evaluated.
     * 
     * @param list This is a list of Pair(key, value) to search through for the
     * presence of the param LHS
     * @param LHS This is the LHS to test the value of
     * @param RHS The expected value. The strings 1 and 1.0 are the same.
     */
    private void testEvaluatedDecls(ArrayList<Pair<String, Value>> list, String LHS, String RHS) {
        boolean varFound = false;
        for (Pair p1 : list) {
            if (p1.first.equals(LHS)) {
                varFound = true;
                System.out.println("Expected(" + LHS + " = " + RHS + ")   Actual(" + LHS + " = " + p1.second + ")");
                try {
                    assertEquals(RHS, p1.second.toString());
                } catch (NumberFormatException e) {
                    assertEquals(RHS.toString(), p1.second.toString());
                }
            }
        }
        if (!varFound) {
            fail(LHS + "was not found in " + list);
        }
    }
    private void testMusicPresence(ArrayList<Pair<String, Value>> list, String musicName, String note, String time){
        for(Pair p: list){
            if(p.first.equals(musicName) && ((Value)p.second).isMusic()){
                ValMusic vm = (ValMusic)p.second;
                Music music = vm.val;
                Performance perf1 = new Performance(music);

                char letter = note.charAt(0);
                int octave = Integer.parseInt(""+note.charAt(1));
                if (note.length() == 3){/*do something for sharps and flats*/}
                int pitch = 0;
                switch (letter) {
                    case 'a': pitch = 10 ; break;
                    case 'b': pitch = 12 ; break;
                    case 'c': pitch = 1 ; break;
                    case 'd': pitch = 3 ; break;
                    case 'e': pitch = 5 ; break;
                    case 'f': pitch = 6 ; break;
                    case 'g': pitch = 8 ; break;
                }
                switch(octave) {
                    case 2: pitch += 12; break;
                    case 3: pitch += 24; break;
                    case 4: pitch += 36; break;
                    case 5: pitch += 48; break;
                    case 6: pitch += 60; break;
                    case 7: pitch += 72; break;
                    case 8: pitch += 84; break;
                    case 9: pitch += 96; break;
                }
                MusNote ex1 = new MusNote(pitch);
                ex1.absolute = new BigRational(time);
                if(!perf1.isIn(ex1)){
                    fail(note + " was not found in the performance at time " + time);
                }
            }
        }
    }
   @Test
    public void testMusic() {
        String program = "f 1 = 0;f x = 2; y = f 1; z = f 3;";
        System.out.println("\nTesting  evaluateProgram(" + program + ")");
        ArrayList result = Top.evaluateProgram(program);
        testEvaluatedDecls(result, "z", "2");
        testEvaluatedDecls(result, "y", "0");
   }

//// a3 was a 34
//// a2 was 22
//// a1 was 10, b1 was 12, c1 was 1, d1 was 3, e1 was 5, f1 was 6, g1 was 8
//// b2 was 24, c2 was 13, d2 was be 15, e2 was be 17, f2 was 18, g2 was 20
//// a1s was 334
//// a1f was 178
//    @Test
//    public void testMusic() {
//        String program = "m = a3 ! a2;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testMusicPresence(result, "m", "a2", "0");
//    }
//    @Test
//    public void testMusic2() {
//        String program = "m = a3 & a2;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testMusicPresence(result, "m", "a2", "1");
//    }
//
//
//    @Test
//    public void testEvaluateProgram() {
//        String program = "x = 1/2;\ny = 1.5;\nz = y-x;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "x", "1/2");
//        testEvaluatedDecls(result, "y", "3/2");
//        testEvaluatedDecls(result, "z", "1");
//    }
//
//    @Test
//    public void testEvaluateProgram2() {
//        String program = "a = 10; b = 2; c = a - b;";
//        System.out.println("\nTesting evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "a", "10");
//        testEvaluatedDecls(result, "b", "2");
//        testEvaluatedDecls(result, "c", "8");
//    }
//
//    @Test
//    public void testEvaluateProgram3() {
//        String program = "a = 10; b = 2; c = a - b; d = (c + a) - b; e = a - d;";
//        System.out.println("\nTesting evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "a", "10");
//        testEvaluatedDecls(result, "b", "2");
//        testEvaluatedDecls(result, "c", "8");
//        testEvaluatedDecls(result, "d", "16");
//        testEvaluatedDecls(result, "e", "-6");
//    }
//
//    @Test
//    public void testEval() {
//        String program = "x = 1;\ny = 2;\nz = x + y;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "z", "3");
//    }
//
//    @Test
//    public void testEval2() {
//        String program = "x = 2+2;\ny = 2;\nz = x + y;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "z", "6");
//    }
//
//    @Test
//    public void testEval3() {
//        String program = "f x = x+1;\nz = f 4;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "z", "5");
//    }
//
//    @Test
//    public void testEval4() {
//        String program = "f x y = x+y;\nz = f 4 4;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "z", "8");
//    }
//
//// Factorial function
////    @Test
////    public void testEval5() {
////        String program = "f 0 = 0;\nf x = x + (f (x-1));\nz = f 3;";
////        System.out.println("\nTesting  evaluateProgram(" + program + ")");
////        ArrayList result = Top.evaluateProgram(program);
////        testEvaluatedDecls(result, "z", "6");
////    }
//
//    @Test
//    public void testEval5if() {
//        String program = "f x = if(x==0) then 0 else (x-1);\na = f 2;\nb = f 0;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "a", "1");
//        testEvaluatedDecls(result, "b", "0");
//
//    }
//
//    @Test
//    public void testEval5if2() {
//        String program = "f x = if(x==0) then (x-1) else 0; z = f 3;";
//        System.out.println("\nTesting evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "z", "0");
//    }
//
//    @Test
//    public void testEval6() {
//        String program = "f x 0 = 1;\nf x y = x+y;\nz = f 0 0;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "z", "1");
//    }
//
//    @Test
//    public void testEvalLong() {
//        String program = "f x y z = x+y+z;\n" +
//                "f 3 4 5 = 8;\n" +
//                "y = f 6 1 20;\n" +
//                "z = f 3 4 5;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "y", "27");
//        testEvaluatedDecls(result, "z", "8");
//    }
//
//    @Test
//    public void testEval7() {
//        String program = "f 0 = 1;\nf x y = x+y;\nz = f 0;\ny = (f z) -3;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "z", "1");
//        testEvaluatedDecls(result, "y", "-2");
//    }
//
//    @Test
//    public void testEval8() {
//        String program = "x = 2;\nm = if (x == 1) then 2 else 1;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "m", "1");
//    }
//
//    @Test
//    @SuppressWarnings("unchecked")
//    public void testEval9() {
//        //What is going on with this test?
//        String program = "m = c3 ! a3 & (c4 ! e4 ! g4)";
//        //String program = " m = c3; k = m & d2;";
//        ArrayList result = Top.evaluateProgram(program);
//        testMusicPresence(result, "m", "", "");
//        //fail("can't test music yet");
//    }
//
//    @Test
//    public void testEvalmul() {
//        String program = "x = 1;\ny = 2;\nz = x * y;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "z", "2");
//    }
//
//    @Test
//    public void testEvaldiv() {
//        String program = "x = 4/2;\ny = 2/4;\nz = x / y;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "z", "4");
//    }
//
//
//    @Test
//    public void testEvalPrec() {
//        String program = "x = 3;\ny = 2;\nz = 4;\na = 8;\nb = a*2*x+z/y;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "b", "50");
//    }
//
//    @Test
//    public void testBools0() {
//        String program = "t = True; f = False;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "t", "true");
//        testEvaluatedDecls(result, "f", "false");
//
//    }
//
//    @Test
//    public void testBools1() {
//        String program = "t = True; f = False; a = if(f == f) then 0 else 5;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "a", "0");
//        testEvaluatedDecls(result, "f", "false");
//        testEvaluatedDecls(result, "t", "true");
//    }
//
//    @Test
//    public void testBools2() {
//        String program = "t = True; f = False; a = if(f == f) then 0 else 5; b = if(t == f) then 0 else 5;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "a", "0");
//        testEvaluatedDecls(result, "b", "5");
//    }
//
//    @Test
//    public void testBools3() {
//        String program = "a = if(True == True) then 0 else 5; b = if(True == False) then 0 else 5;";
//        System.out.println("\nTesting  evaluateProgram(" + program + ")");
//        ArrayList result = Top.evaluateProgram(program);
//        testEvaluatedDecls(result, "a", "0");
//        testEvaluatedDecls(result, "b", "5");
//    }
}
