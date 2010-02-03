package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class ExpConst extends Exp {

    public Token body;
    public ValNum value;  // Should be 
    //potential second instance variable: actual value

    public ExpConst(Token body) {
        this.body = body;
        this.value = new ValNum(Double.parseDouble(body.body));
    }

    @Override
    public boolean isConst() {
        return true;
    }

    public boolean compareTo(ParsedObject p) {
        if (!p.isExp()) {
            return false;
        }
        Exp p1 = (Exp) p;
        if (!p1.isConst()) {
            return false;
        }
        ExpConst p2 = (ExpConst) p1;
        return p2.body.sameToken(body.symbol);
    }

    public String print() {
        return body.toString();
    }

    @Override
    public Value eval(Env e) {
        return value;
    }
}
