package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public abstract class Exp extends ParsedObject {

    @Override
    public boolean isExp() {
        return true;
    }

    public boolean isVar() {
        return false;
    }
    public boolean isBool() {
        return false;
    }

    public boolean isConst() {
        return false;
    }

    public boolean isIf() {
        return false;
    }

    public boolean isCall() {
        return false;
    }

    public boolean isLambda() {
        return false;
    }

    public boolean isTuple() {
        return false;
    }

    public boolean isList() {
        return false;
    }

    public Value eval(Env e) {
        throw new ExecutionError("Missing eval method");
    }
}
