package music;

/**
 *
 * @author CIS412 Class
 */
public class ExpBool extends Exp{
    public Token body;
    public ValBool value;

    public ExpBool(Token body) {
        this.body = body;
        this.value = new ValBool(body.symbol == Symbol.trueKeyword);
    }

    @Override
    public boolean isBool() {
        return true;
    }

    public boolean compareTo(ParsedObject p) {
        if (!p.isExp()) {
            return false;
        }
        Exp p1 = (Exp) p;
        if (!p1.isBool()) {
            return false;
        }
        ExpBool p2 = (ExpBool) p1;
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