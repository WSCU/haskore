package music;import java.util.ArrayList;/** * The Functional Music project * @author Western State College, CIS412 class */public class MusMod extends Music {    public Modifier modifier;    public Music music;    public MusMod(){    }    public MusMod(Music music, Modifier modifier){        this.music = music;        this.modifier = modifier;    }    public Performance perform(double time, Modifier mod) {        return music.perform(time, modifier.combine(mod));    }    public String toString() {        return "MusMod("+this.hashCode()+"): Modifier "+modifier.hashCode()+"(Music "                +music.hashCode()+")";    }    public String prettyPrint() {        return "MusMod("+this.hashCode()+"): "+modifier.prettyPrint()+"\non"+music.prettyPrint();    }}