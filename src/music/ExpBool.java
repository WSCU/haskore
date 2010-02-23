/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package music;

/**
 *
 * @author chief
 */
public class ExpBool extends Exp{
    public Token body;
    public ValBool value;  // Should be
    //potential second instance variable: actual value

    public ExpBool(Token body) {
        this.body = body;
        this.value = new ValBool(body.body);
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
