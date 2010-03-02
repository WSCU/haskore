package music;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.midi.*;

/**
 * The Functional Music project
 * 
 * Midi control class for keeping track of MusNotes and writing them to a sequence
 * and subsequently, to a file.
 * @author Western State College, CIS412 class
 */
public class Performance {

    public static ArrayList<MusNote> notes;
    //public BigRational absoluteTime;
    //Not to be confused with the instrument ArrayList in the MusNote class,
    //this one refers to the midi-numbers for instruments, not strings.
    public ArrayList<Integer> instruments;

    /*public Performance() {
        notes = new ArrayList<MusNote>();
        instruments = new ArrayList<Integer>();
    }

    public Performance(ArrayList<MusNote> notes, BigRational time) {
        this.notes = notes;
//        absoluteTime = time;
        instruments = new ArrayList<Integer>();
    }*/
    public static void perform(Music myMusic)
    {
        notes = new ArrayList<MusNote>();
    }

    public static boolean compare(Performance a, Performance b) {
        ArrayList<MusNote> x = new ArrayList<MusNote>(a.notes);
        ArrayList<MusNote> y = new ArrayList<MusNote>(b.notes);
        for (int i = 0; i < x.size(); i++) {
            boolean isIn = false;
            for (int j = 0; j < y.size(); j++) {
                if (x.get(i).compare(y.get(j))) {
                    isIn = true;
                    y.remove(j);
                    break;
                }
            }
            if (!isIn) {
                return false;
            }
            isIn = false;
        }
        return true;
    }

    public void add(MusNote note) {
 //       note.absolute = absoluteTime;
        notes.add(note);
    }

    public void addAbs(MusNote note) {
        int index = 0;
        for (int i = 0; i < notes.size(); i++) {
            //BigRational compare a >
            if (note.absolute.compareTo(notes.get(i).absolute) == 1) {
                index = i - 1;
                break;
            }
        }
        //ensures non-zero array position.
        index = Math.max(index, 0);
        notes.add(index, note);
    }

    /**
     * This is the method that writes to a file.  
     * 
     * This uses the strangest and hardest parts of the Javax.sound.Midi library
     * to perform the darkest magic of writing the Midi to a file.  How it is 
     * is not explicable here.  If you really want to know, the code is well-
     * commented.
     * @param filename Just a string indicating the file-name.
     * @return true if it all works, false if something goes wrong along the way.
     */
    public boolean writeToFile(String filename) {
        try {
            //Sequencer boiler-plate
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            Sequence mySeq = sequencer.getSequence();
            //Sets the PPQ(Pulses Per Quarter).
            //This is some confusing Deep Midi Magic, and I'm not sure what 
            //the perfect setting would be here.  96 seems to be commonly used,
            //but we may want something different later.
            mySeq = new Sequence(Sequence.PPQ, 96);
            sequencer.setSequence(mySeq);
            //Multi-track recording is possible, but I believe it would only 
            //make things more confusing at this level.  It is also among the
            //more bewildering and scary aspects of Midi.
            Track track = mySeq.createTrack();
            //enables all recording from every channel. 
            // We need this because we're using multi-channels for instruments.
            sequencer.recordEnable(track, -1);
            sequencer.startRecording();
            ArrayList<MidiEvent> events = getEventList();
            for (MidiEvent midiEvent : events) {
                track.add(midiEvent);
            }
            //Inexplicable and strange behavior occurs if you don't stop,
            //close and disable everything.  I don't think the order counts,
            //just make sure it all gets closed beforing writing to a file.
            sequencer.stopRecording();
            sequencer.close();
            sequencer.recordDisable(track);
            MidiSystem.write(mySeq, 0, new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * utility class that dips into the sequence variable and gets all of the
     * instruments of the <b>MusNote</b>s, putting them into the instruments
     * variable and returning that as well.
     * @return an ArrayList of Integers that maps from channels to Instruments.
     */
    public ArrayList<Integer> getInstruments() throws InvalidMidiDataException, IOException {
        //checks all the instruments in everything within this sequence
        // and fills them into an ArrayList of integers.
        for (MusNote note : notes) {
            if (!instruments.contains(note.getInstruments())) {
                instruments.addAll(note.getInstruments());
            }
        }
        return instruments;
    }

    /**
     * ~~~~~~~~~~
     * I have decided to make this private in this class, as I can't think of 
     * any reason why you'd want to fiddle with this outside of writing to a
     * file.
     * ~~~~~~~~~~
     * 
     * Creates an ArrayList of MidiEvents based on the <b>MusNotes</b>s 
     * contained in the ArrayList of notes(notes) Also sorts them based on 
     * timestamp.
     * Will explode if given more than 16 instruments.
     * (Note: poor error handling on the instrument overloading.  Sorry)
     * @param timeStamp The timestamp(before applying the timeSignature) of the 
     * start of the sequence.
     * @return an ArrayList of MidiEvents ready to be stuffed into a sequence.
     */
    private ArrayList<MidiEvent> getEventList() throws InvalidMidiDataException, IOException {
        //fills the instruments variable with all of the intstruments.
        //throws away the returned array and just uses the instance variable.
        getInstruments();
        //Only 16 channels available for different instruments.
        if (instruments.size() > 16) {
            throw new Error("Too many instruments");
        }
        //List to hold the MidiEvents that the Notes generate.
        //(MidiEvents are the actual guts of the Midi file)
        ArrayList<MidiEvent> result = new ArrayList<MidiEvent>();
        for (int i = 0; i < instruments.size(); i++) {
            //adds the events for the instruments.
            result.add(instrEvent(i, 0));
        }
        for (MusNote note : notes) {
            //fetches the events from the music contained within the sequence
            // and sorts it so that the events line up in proper timing order.
            addSort(result, getNoteEvents(note));
        }
        return result;
    }

    /**
     * ~~~~~~~~~~
     * I have decided to make this private in this class, as I can't think of 
     * any reason why you'd want to fiddle with this outside of writing to a
     * file.
     * ~~~~~~~~~~
     * 
     * Utility method to simplify main logic for MidiEvent generation.
     * @param index Position in the array where the instrument is located (also 
     * corresponds to the channel in the resultant MidiEvent)
     * @param timeStamp Absolute time that the instrument change events should take
     * place.
     * @return The finished MidiEvent, with instrument, channel and time stamp.
     */
    private MidiEvent instrEvent(int index, int timeStamp) {
        try {
            ShortMessage message = new ShortMessage();
            message.setMessage(ShortMessage.PROGRAM_CHANGE, index,
                    instruments.get(index), instruments.get(index));
            return new MidiEvent(message, timeStamp);
        } catch (InvalidMidiDataException ex) {
            throw new Error("Invalid Midi Data");
        }
    }

    /**
     * ~~~~~~~~~~
     * I have decided to make this private in this class, as I can't think of 
     * any reason why you'd want to fiddle with this outside of writing to a
     * file.
     * ~~~~~~~~~~
     * 
     * Utility method to add two ArrayLists of MidiEvents and adds them together,
     * favoring the first one and sorting out the Ticks of the events so that 
     * everything goes smoothly.
     * @param result ArrayList that gets destructively altered to include the addend
     * @param addend Second ArrayList to be added into the result.
     */
    private void addSort(ArrayList<MidiEvent> result, ArrayList<MidiEvent> addend) {
        int index = 0;
        for (int i = 0; i < addend.size(); i++) {
            for (int j = 0; j < result.size(); j++) {
                if (result.get(j).getTick() > addend.get(i).getTick()) {
                    index = i - 1;
                    break;
                }
            }
            //ensures non-zero array position.
            index = Math.max(index, 0);
            result.add(index, addend.get(i));
        }
    }

    /**
     * ~~~~~~~~~~
     * I have decided to make this private in this class, as I can't think of 
     * any reason why you'd want to fiddle with this outside of writing to a
     * file.
     * ~~~~~~~~~~
     * 
     * Method to retrieve the events from the list.  Converts from 
     * <b>Music</b> to MidiEvents.
     * @param instrumentChannels The generated list of instrument channels that
     * contains the list of instruments indexed against the channel number.
     * @return
     */
    private ArrayList<MidiEvent> getNoteEvents(MusNote note) {
        ArrayList<MidiEvent> result = new ArrayList<MidiEvent>();
        try {
            //fetches the channel from the instrument list.
            //           int channel = instruments.indexOf(note.instrument));
            int channel = instruments.indexOf(MusNote.instruments.indexOf(note.instrument));
            //Invoke the Midi voodoo:
            ShortMessage startMessage = new ShortMessage();
            //ShortMessages are the main structure for music in Midi and
            //ShortMessage.NOTE_ON is a byte referring to a specific code in the 
            //midi structure which means to turn a note on.
            startMessage.setMessage(ShortMessage.NOTE_ON, channel, note.pitch, note.velocity);
            ShortMessage stopMessage = new ShortMessage();
            stopMessage.setMessage(ShortMessage.NOTE_OFF, channel, note.pitch, note.velocity);
            //Short messages are then enxapsulated in MidiEvents and stuffed 
            //into an ArrayList<MidiEvent>.  These are usually sorted afterwards.
            result.add(new MidiEvent(startMessage,(Music.timeSignature.times(note.absolute)).toInt()));
            result.add(new MidiEvent(stopMessage, ((note.absolute.plus(note.duration).times(Music.timeSignature))).toInt()));
        } catch (InvalidMidiDataException ex) {
            throw new Error();
        }
        return result;
    }
}
