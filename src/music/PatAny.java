package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class PatAny extends Pat {

    public PatAny() {
    }

    @Override
    public boolean isAny() {
        return true;
    }

    public boolean compareTo(ParsedObject p) {
        if (!p.isPat()) {
            return false;
        }
        Pat p1 = (Pat) p;
        if (!p1.isAny()) {
            return false;
        }
        return true;
    }

    public String print() {
        return "_";
    }
}
