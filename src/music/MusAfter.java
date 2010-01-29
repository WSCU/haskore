package music;import java.util.ArrayList;/** * The Functional Music project * @author Western State College, CIS412 class */public class MusAfter extends Music {    public Music before;    public Music after;    public MusAfter(){    }    public MusAfter(Music before){        this.before = before;    }    public MusAfter(Music before, Music after){        this.before = before;        this.after = after;    }     public Performance perform(double time, Modifier modifier){        Performance result = before.perform(time, modifier);        ArrayList<MusNote> notes = new ArrayList<MusNote>();        notes.addAll(after.perform(time+result.absoluteTime, modifier).notes);        for (MusNote musNote : notes) {            result.add(musNote);        }        return result;    }    public String toString() {        return "MusAfter("+this.hashCode()+"): "+before.hashCode()+" & "+after.hashCode();    }    public String prettyPrint() {        return "MusAfter("+this.hashCode()+"): "+before.prettyPrint()+" & "+after.prettyPrint();    }}