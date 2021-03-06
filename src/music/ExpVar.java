package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class ExpVar extends Exp {

    public Token body;

    public ExpVar(Token body) {
        this.body = body;
    }

    @Override
    public boolean isVar() {
        return true;
    }

    public boolean compareTo(ParsedObject p) {
        if (!p.isExp()) {
            return false;
        }
        Exp p1 = (Exp) p;
        if (!p1.isVar()) {
            return false;
        }
        ExpVar p2 = (ExpVar) p1;
        return p2.body.sameToken(body.symbol);
    }

    @Override
    public Value eval(Env e) {
        Value v = e.eval(body.symbol);
        System.out.println(body.symbol + " : "+ v);
        this.body.addValue(v);
        return v;
    }

    public String print() {
        return body.toString();
    }
}
