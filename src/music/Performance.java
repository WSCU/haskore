package music;

import java.io.File;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.util.ArrayList;
import javax.sound.midi.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Functional Music project
 * 
 * Midi control class for keeping track of MusNotes and writing them to a sequence
 * and subsequently, to a file.
 * @author Western State College, CIS412 class
 */
public class Performance {

    public ArrayList<MusNote> notes =null;
    //public BigRational absoluteTime;


    public Performance(ArrayList<MusNote> n) {
        notes = n;
    }
    /*
    public Performance(ArrayList<MusNote> notes, BigRational time) {
    this.notes = notes;
    //        absoluteTime = time;
    instruments = new ArrayList<Integer>();
    }*/
    public void perform() {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();//testing commit
            sequencer.setSequence(makeMusic());
            sequencer.open();
            sequencer.start();
            while (true) {
                if (sequencer.isRunning()) {
                    try {
                        Thread.sleep(1000); // Check every second
                    } catch (InterruptedException ignore) {
                        break;
                    }
                } else {
                    break;
                }
            }
            sequencer.stop();
            sequencer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Fills the instruments variable with all of the available instruments.
    //Until a design decision is reached regarding instrumentation limitations,
    //this works.
    public Sequence makeMusic() {
        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            Instrument[] avail = synth.getAvailableInstruments();
            for(Instrument i : avail){
                System.out.println(i.getName());
            }
            Sequence sequence = new Sequence(Sequence.PPQ, 2);
            Track track = sequence.createTrack();
            String prev = "";
            for (MusNote n : notes) {
                int du = n.duration.toInt();
                int s = n.absolute.toInt();
                int dInst = 0;
                int chan = 0;
                    for (Instrument ti : avail) {
                        if (n.instrument.equals(ti.getName()) ) {
                            if(!(n.instrument.equals(prev)))dInst++;
                            if (dInst < 17) {
                                //synth.loadInstrument(ti);
                                chan = dInst;
                                track.add(createNoteEvent(ShortMessage.PROGRAM_CHANGE,
                                        chan,ti.getPatch().getProgram(),n.velocity,s));
                                System.out.println(chan +" "+ ti.getPatch().getProgram());
                            } else {
                                break;//need to make new track and put notes in it. no way.
                            }
                        }
                    }
                    prev = n.instrument;
                    track.add(createNoteOnEvent(n.pitch, chan, s, n.velocity));
                    track.add(createNoteOffEvent(n.pitch, chan, s + du));
                }
            return sequence;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new Error("Midi Instrument error!");
        }
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

    private MidiEvent createNoteOnEvent(int nKey, int inst, long lTick, int vel) {
        return createNoteEvent(ShortMessage.NOTE_ON, inst,
                nKey,
                vel,
                lTick);
    }

    private MidiEvent createNoteOffEvent(int nKey, int inst, long lTick) {
        return createNoteEvent(ShortMessage.NOTE_OFF, inst,
                nKey,
                0,
                lTick);
    }

    private static MidiEvent createNoteEvent(int nCommand, int inst,
            int nKey,
            int nVelocity,
            long lTick) {
        
        ShortMessage message = new ShortMessage();
        try {
            message.setMessage(nCommand,
                    inst, // always on channel 1
                    nKey,
                    nVelocity);

        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
            System.exit(1);
        }

        MidiEvent event = new MidiEvent(message,
                lTick);



        return event;

    }

    public static void main(String[] args) {
        String inst1 ="Piano";
        String inst2 ="Pizzicato Strings";
        String inst3 = "Xylophone";
        MusNote note1 = new MusNote(60, 70, inst3, new BigRational("10"));
        MusNote note2 = new MusNote(20, 70, inst1, new BigRational("1/2"));
        MusNote note3 = new MusNote(30, 70, inst1, new BigRational("1/2"));
        MusNote note4 = new MusNote(40, 70, inst1, new BigRational("1/2"));
        MusNote note5 = new MusNote(50, 70, inst1, new BigRational("1/2"));
        MusNote note6 = new MusNote(60, 70, inst1, new BigRational("1"));
        MusAfter tog0 = new MusAfter(note5, note6);
        MusAfter tog1 = new MusAfter(note4, tog0);
        MusAfter tog2 = new MusAfter(note3, tog1);
        MusAfter tog3 = new MusAfter(note2, tog2);
        MusAfter tog4 = new MusAfter(note1, tog3);

        MusNote nbc1 = new MusNote(35, 70, inst1, new BigRational("1"));
        MusNote nbc2 = new MusNote(22, 70, inst1, new BigRational("1"));
        MusNote nbc3 = new MusNote(46, 50, inst1, new BigRational("1"));


        MusMod mo = (MusMod) Music.up(tog4, 20);
        
        //result.perform(after);
        ArrayList<MusNote> notes = new ArrayList<MusNote>();
        Performance result = new Performance(notes);
        BigRational finish = mo.perform(BigRational.ZERO, new Modifier(), notes);
        for (int i = 0; i < 20; i++) {
            int f = (int) Math.floor(Math.random() * 50)+20;
            MusNote note8 = new MusNote(f, 70, inst3, new BigRational("1"));
            note8.perform(finish, new Modifier(), notes);
            finish = finish.plus(note8.duration);
        }


        result.perform();
    }
}
