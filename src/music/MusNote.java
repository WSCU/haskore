package music;import java.io.File;import java.io.IOException;import java.util.ArrayList;import java.util.logging.Level;import java.util.logging.Logger;import javax.sound.midi.*;/** * The Functional Music project * @author Western State College, CIS412 class */public class MusNote extends Music {    //Duration and Velocity may become Rationals, but they will stay in doubles    //for now.    public static ArrayList<String> instruments = new ArrayList<String>();    public int pitch;    public BigRational duration;    public String instrument;    public int velocity;    public BigRational absolute;    public MusNote(int pitch, int velocity, String instrument,BigRational duration) {        this.pitch = pitch;        this.duration = duration;        this.velocity = velocity;        this.instrument = instrument;        this.absolute = BigRational.ZERO;    }    public static MusNote empty() {        return new MusNote(0, 0, "piano", BigRational.ZERO  );    }    public MusNote(int pitch) {        this.pitch = pitch;        this.duration = BigRational.ONE;        this.velocity = 1;        this.instrument = "piano";    }    public MusNote(int pitch, BigRational duration, int velocity, String instrument, BigRational absolute) {        this.pitch = pitch;        this.duration = duration;        this.velocity = velocity;        this.instrument = instrument;        this.absolute = absolute;    }    public MusNote(MusNote note) {        this.pitch = note.pitch;        this.duration = note.duration;        this.velocity = note.velocity;        this.instrument = note.instrument;        this.absolute = note.absolute;    }    public BigRational perform(BigRational time, Modifier modifier, ArrayList<MusNote> notes)    {        MusNote resNote = new MusNote(this);        resNote.absolute = time;        modifier.applyMod(resNote);        notes.add(resNote);        return resNote.absolute.plus(resNote.duration);    }    public static Soundbank getMySoundbank(File b) throws InvalidMidiDataException, IOException {        //File bank = new File("S:\\music\\soundbak-mid.gm");        Soundbank s = MidiSystem.getSoundbank(b);        return s;    }        public ArrayList<Integer> getInstruments() throws InvalidMidiDataException, IOException {                ArrayList<Integer> result = new ArrayList<Integer>();        result.add(instruments.indexOf(instrument));        if (instruments.indexOf(instrument) < 0) {            throw new Error("Instrument not found!");        }        return result;    }    public String toString() {        return "Note (" + this.hashCode() + ")";    }    public String prettyPrint() {        return "Note of pitch " + pitch + ", on a " + instrument +                " for duration of " + duration + " with velocity " + velocity +                ".  Absolute time of " + absolute;    }    /**     * Compares the Absolute, Duration and Pitch of this and x for debugging     * purposes.     *     * @param x The MusNote to be compared.     * @return true if the absolute, duration and pitch are the same, false otherwise.     */    public boolean compare(MusNote x) {        boolean same = true;        if (x == null) {            System.out.println("The note is null");            return false;        }        if (x.absolute != absolute) {            same = false;            System.out.println("Absolutes are not equal");        }        if (x.duration != duration) {            same = false;            System.out.println("Durations are not equal");        }        if (x.pitch != pitch) {            same = false;            System.out.println("Pitches are not equal");        }        if (x.velocity != velocity) {            same = false;            System.out.println("Velocities are not equal");        }        if (!x.instrument.equals(instrument)) {            same = false;            System.out.println("Instruments are not equal");        }        return same;    }}