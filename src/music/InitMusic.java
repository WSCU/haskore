package music;
import static music.Symbol.toSymbol;
import java.util.ArrayList;

public class InitMusic {

    public static EnvHash topEnv = new EnvHash(300, null);
    public static ArrayList notePlaces = new ArrayList();
    
    public static void addNotes() {
        
        //keep track of the relative position of each note in an arraylist
        //i'm not sure if i should be using "toSymbol" of the note name
        notePlaces.add(0, toSymbol("c"));
        notePlaces.add(2, toSymbol("d"));
        notePlaces.add(4, toSymbol("e"));
        notePlaces.add(5, toSymbol("f"));
        notePlaces.add(7, toSymbol("g"));
        notePlaces.add(9, toSymbol("a"));
        notePlaces.add(11, toSymbol("b"));

       /* 
        //it keeps complaining that these for loops are ints instead of booleans--maybe i did something stupid that i'm not seeing
 
        //the problem is you need to swap the i++ with the i<8 the boolean needs to be in the middle
        
        for(int i=0; i++; i<8) {//loop through the 8 octaves
            for(int j=0; j++; j<7) {//loop through the 7 notes
                //create the note, the flat, and the sharp
                
                //retrieve the note in the jth position
                //i'm not sure if this is right...
                Symbol note = notePlaces.get(j);
                
                //add the note and pitch to the environment
                //the note is: octave*12 + the note's relative position (i think)
                //flat and sharp is -1 and +1 of the note
                topEnv.add(toSymbol("" + note + i), new ValMusic(new Note(j*12+i)));
            }
        }
        */
    }
}