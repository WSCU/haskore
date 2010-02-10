package music;

import java.util.ArrayList;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class ExpTuple extends Exp {

    public ArrayList<Exp> body;

    public ExpTuple(ArrayList<Exp> body){
        if(body.size() > 2){
            throw new ParseError("Tuple can only have two elements, use Exp list.",body.get(2).firstToken);
        }
        this.body = body;
    }

    @Override
    public boolean isTuple() {
        return true;
    }

    public boolean compareTo(ParsedObject p) {
        if (!p.isExp()) {
            return false;
        }
        Exp exp = (Exp) p;
        if (!exp.isTuple()) {
            return false;
        }
        ExpTuple tuple = (ExpTuple) exp;
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
        for (Exp temp : body) {
            content += temp.print() + ", ";
        }
        return content + ")";
    }
}
