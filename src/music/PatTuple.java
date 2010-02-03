package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
import java.util.ArrayList;

public class PatTuple extends Pat {

    public ArrayList<Pat> body;

    public PatTuple(ArrayList<Pat> body) {
        this.body = body;
    }

    @Override
    public boolean isTuple() {
        return true;
    }

    public boolean compareTo(ParsedObject p) {
        if (!p.isPat()) {
            return false;
        }
        Pat pat = (Pat) p;
        if (!pat.isTuple()) {
            return false;
        }
        PatTuple tuple = (PatTuple) pat;
        if (body.size() != tuple.body.size()) {
            return false;
        }
        for (int i = 0; i < body.size(); i++) {
            if (!body.get(i).compareTo(tuple.body.get(i))) {
                return false;
            }
        }
        return true;
    }

    public String print() {
        String content = "(";
        for (Pat temp : body) {
            content += temp.print() + ", ";
        }
        return content + ")";
    }
}
