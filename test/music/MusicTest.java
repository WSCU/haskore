/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package music;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adam
 */
public class MusicTest {

    public MusicTest() {
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

    /**
     * Test of up method, of class Music.
     */
    @Test
    public void testUp() {
        System.out.println("up");
        MusNote testNote = new MusNote(5,2,5,"Piano");
        MusNote expectedNote = new MusNote(7,2,5,"Piano");
        Music music = testNote;
        double n = 2.0;
        Music expResult = expectedNote;
        Music result = Music.up(music, n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of down method, of class Music.
     */
    @Test
    public void testDown() {
        System.out.println("down");
        Music music = null;
        double n = 0.0;
        Music expResult = null;
        Music result = Music.down(music, n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of withInstrument method, of class Music.
     */
    @Test
    public void testWithInstrument() {
        System.out.println("withInstrument");
        Music music = null;
        String n = "";
        Music expResult = null;
        Music result = Music.withInstrument(music, n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of withPitch method, of class Music.
     */
    @Test
    public void testWithPitch() {
        System.out.println("withPitch");
        Music music = null;
        double n = 0.0;
        Music expResult = null;
        Music result = Music.withPitch(music, n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of withVelocity method, of class Music.
     */
    @Test
    public void testWithVelocity() {
        System.out.println("withVelocity");
        Music music = null;
        double n = 0.0;
        Music expResult = null;
        Music result = Music.withVelocity(music, n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeVelocity method, of class Music.
     */
    @Test
    public void testChangeVelocity() {
        System.out.println("changeVelocity");
        Music music = null;
        double n = 0.0;
        Music expResult = null;
        Music result = Music.changeVelocity(music, n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeDuration method, of class Music.
     */
    @Test
    public void testChangeDuration() {
        System.out.println("changeDuration");
        Music music = null;
        double n = 0.0;
        Music expResult = null;
        Music result = Music.changeDuration(music, n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of note method, of class Music.
     */
    @Test
    public void testNote() {
        System.out.println("note");
        String noteName = "";
        double duration = 0.0;
        double velocity = 0.0;
        String instrument = "";
        MusNote expResult = null;
        MusNote result = Music.note(noteName, duration, velocity, instrument);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNote method, of class Music.
     */
    @Test
    public void testIsNote() {
        System.out.println("isNote");
        Music instance = new MusicImpl();
        boolean expResult = false;
        boolean result = instance.isNote();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAfter method, of class Music.
     */
    @Test
    public void testIsAfter() {
        System.out.println("isAfter");
        Music instance = new MusicImpl();
        boolean expResult = false;
        boolean result = instance.isAfter();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTogether method, of class Music.
     */
    @Test
    public void testIsTogether() {
        System.out.println("isTogether");
        Music instance = new MusicImpl();
        boolean expResult = false;
        boolean result = instance.isTogether();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMod method, of class Music.
     */
    @Test
    public void testIsMod() {
        System.out.println("isMod");
        Music instance = new MusicImpl();
        boolean expResult = false;
        boolean result = instance.isMod();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of perform method, of class Music.
     */
    @Test
    public void testPerform() {
        System.out.println("perform");
        double time = 0.0;
        Modifier modifier = null;
        Music instance = new MusicImpl();
        Performance expResult = null;
        Performance result = instance.perform(time, modifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Music.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Music instance = new MusicImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of prettyPrint method, of class Music.
     */
    @Test
    public void testPrettyPrint() {
        System.out.println("prettyPrint");
        Music instance = new MusicImpl();
        String expResult = "";
        String result = instance.prettyPrint();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class MusicImpl extends Music {

        public Performance perform(double time, Modifier modifier) {
            return null;
        }

        public String toString() {
            return "";
        }

        public String prettyPrint() {
            return "";
        }
    }

}