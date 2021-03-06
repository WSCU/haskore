package music;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.*;
import javax.sound.midi.spi.MidiFileWriter;

/**
 * The Functional Music project
 * 
 * Midi control class for keeping track of MusNotes and writing them to a sequence
 * and subsequently, to a file.
 * @author Western State College, CIS412 class
 */
public class Performance {

    public ArrayList<MusNote> notes = null;
    //public BigRational absoluteTime;

    public Performance(Music n) {
        this.notes = new ArrayList<MusNote>();
        n.perform(BigRational.ZERO, new Modifier(), notes);
    }
    

    public boolean isIn(MusNote n) {
        for (MusNote i : this.notes) {
            boolean p = i.pitch == n.pitch;
            boolean t = i.absolute.equals(n.absolute);
            boolean d = i.duration.equals(n.duration);
            if (p && t && d) {
                return true;
            }
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
            Sequencer sequencer = MidiSystem.getSequencer();//testing commi
            sequencer.open();
            sequencer.setSequence(makeMusic());
            sequencer.setTempoInBPM(120);
            if (MainGui.MUTE)sequencer.close();
            if (!MainGui.MUTE) {
                sequencer.start();
                while (true) {
                    if (sequencer.isRunning()) {
                        try {
                            if(MainGui.MUTE)sequencer.stop();
                            Thread.sleep(0, 100); // Check every second
                            }
                        catch (InterruptedException ignore) {
                                break;
                            }
                    }
                    else break;
                }
            sequencer.stop();
            }
            
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
            Sequence sequence = new Sequence(Sequence.PPQ,30);
            return fillSequence(synth, sequence);   
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new Error("Midi Instrument error!");
        }
    }

    public Sequence fillSequence(Synthesizer synth ,Sequence sequence)
    {
            Track track = sequence.createTrack();
            String prev = "";
            Instrument[] avail = synth.getAvailableInstruments();
            BigRational duration_multiplier = new BigRational(120);
            for (MusNote n : notes) {
                int du = n.duration.times(duration_multiplier).toInt();
                //int du = n.duration.toInt();
                int s = n.absolute.times(duration_multiplier).toInt();
                //System.out.println("MAGIC NUMBER "+ s +"--->"+du);
                int dInst = 0;
                int chan = 0;
                for (Instrument ti : avail) {
                    if (n.instrument.equals(ti.getName())) {
                        if (!(n.instrument.equals(prev))) {
                            dInst++;
                        }
                        if (dInst < 17) {
                            //synth.loadInstrument(ti);
                            chan = dInst;
                            track.add(createNoteEvent(ShortMessage.PROGRAM_CHANGE,
                                    chan, ti.getPatch().getProgram(), n.velocity, s));
                            System.out.println(chan + " " + ti.getPatch().getProgram());
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
    }

    public void writeToFile(String filename){
        try {
            MidiSystem.write(makeMusic(), 0, new File(filename + ".mid"));
        } catch (IOException ex) {
            System.out.println(filename+": "+ex.getMessage());
        }
    }
    public void writeTofile(Sequence seq, String filename) {
        try {
            //Sequencer boiler-plate
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            //Magical voodo number 
            Sequence newseq = new Sequence(Sequence.PPQ,30);
            
            sequencer.setSequence(newseq);
            
            //Multi-track recording is possible, but I believe it would only
            //make things more confusing at this level.  It is also among the
            //more bewildering and scary aspects of Midi.
            Track track = newseq.createTrack();
            //enables all recording from every channel.
            // We need this because we're using multi-channels for instruments.
            sequencer.recordEnable(track, -1);
            sequencer.startRecording();
            for(int i=0;i<seq.getTracks()[0].size();i++){
                track.add(seq.getTracks()[0].get(i));
            }
           
            //Inexplicable and strange behavior occurs if you don't stop,
            //close and disable everything.  I don't think the order counts,
            //just make sure it all gets closed beforing writing to a file.
            sequencer.stopRecording();
            sequencer.close();
            sequencer.recordDisable(track);
            MidiSystem.write(newseq, 1, new File(filename+".mid"));
            MidiSystem.write(seq, 0, new File(filename+"2.mid"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
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

    private MidiEvent createStop() {
        return createNoteEvent(ShortMessage.STOP, 0,
                0,
                0,
                1);
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
        String inst1 = "Music Box";
        String inst2 = "Pizzicato Strings";
        String inst3 = "Music Box";
        //MusNote note1 = new MusNote(60, 70, inst1, new BigRational("1/4"));
        //MusNote note2 = new MusNote(20, 70, inst1, new BigRational("1/2"));
        //MusNote note3 = new MusNote(30, 70, inst1, new BigRational("2/2"));
        //MusNote note4 = new MusNote(40, 70, inst1, new BigRational("1/16"));
        MusNote note5 = new MusNote(50, 80, inst2, new BigRational("1"));
        MusNote note6 = new MusNote(60, 70, inst1, new BigRational("4/4"));
        MusNote c = Music.note("c3", new BigRational("1"), 80, inst3);
        MusAfter tog0 = new MusAfter(c, Music.note("a3", new BigRational("1"), 80, inst3));
        MusAfter tog1 = new MusAfter(tog0, Music.up(tog0, 12));


        //MusAfter tog1 = new MusAfter(note4, tog0);
        //MusAfter tog2 = new MusAfter(note3, tog1);
        //MusAfter tog3 = new MusAfter(note2, tog2);
        //MusAfter tog4 = new MusAfter(note1, tog3);
        Performance result = new Performance(Music.withVelocity(tog1, 5));
        result.writeToFile("midin");
    }
}
