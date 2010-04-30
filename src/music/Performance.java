package music;

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

    public ArrayList<MusNote> notes =null;
    //public BigRational absoluteTime;


    public Performance(Music n) {
        this.notes =  new ArrayList<MusNote>();
        n.perform(BigRational.ZERO, new Modifier(), notes);
    }

    public boolean isIn(MusNote n)
    {
        for(MusNote i : this.notes)
        {
            boolean p = i.pitch == n.pitch;
            boolean t = i.absolute.equals(n.absolute);
            boolean d = i.duration.equals(n.duration);
            if(p && t && d)return true;
        }
        return false;
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
            if(MainGui.MUTE)sequencer.stop();
            System.out.println("MIDI SEQ "+sequencer.isRunning());
            sequencer.start();
            while (true) {
                if (sequencer.isRunning()) {
                    try {
                        if(MainGui.MUTE)sequencer.stop();
                        Thread.sleep(10); // Check every second
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
//            for(Instrument i : avail){
//                System.out.println(i.getName());
//            }
            Sequence sequence = new Sequence(Sequence.SMPTE_30, 2);
            Track track = sequence.createTrack();
            String prev = "";
            BigRational duration_multiplier = new BigRational(120);
            for (MusNote n : notes) {
                int du = n.duration.times(duration_multiplier).toInt();
                //int du = n.duration.toInt();
                int s = n.absolute.times(duration_multiplier).toInt();
                //System.out.println("MAGIC NUMBER "+ s +"--->"+du);
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

    public void add(MusNote note) {
        //       note.absolute = absoluteTime;
        notes.add(note);
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
        String inst1 ="Music Box";
        String inst2 ="Pizzicato Strings";
        String inst3 = "Music Box";
        //MusNote note1 = new MusNote(60, 70, inst1, new BigRational("1/4"));
        //MusNote note2 = new MusNote(20, 70, inst1, new BigRational("1/2"));
        //MusNote note3 = new MusNote(30, 70, inst1, new BigRational("2/2"));
        //MusNote note4 = new MusNote(40, 70, inst1, new BigRational("1/16"));
        MusNote note5 = new MusNote(50, 80, inst2, new BigRational("1"));
        MusNote note6 = new MusNote(60, 70, inst1, new BigRational("4/4"));
        MusNote c = Music.note("c3", new BigRational("1"), 80, inst3);
        MusAfter tog0 = new MusAfter(c , Music.note("a3", new BigRational("1"), 80, inst3)
                );
        MusAfter tog1 = new MusAfter(tog0, Music.up(tog0, 12));
        
        //MusAfter tog1 = new MusAfter(note4, tog0);
        //MusAfter tog2 = new MusAfter(note3, tog1);
        //MusAfter tog3 = new MusAfter(note2, tog2);
        //MusAfter tog4 = new MusAfter(note1, tog3);
        Performance result = new Performance(tog1);
        result.perform();
    }
}
