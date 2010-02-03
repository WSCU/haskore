package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class EnvSingle extends Env {

    public Env parent = null;
    public Symbol name;
    public Thunk val;

    public EnvSingle(Env parent, Symbol name, Thunk val) {
        this.parent = parent;
        this.name = name;
        this.val = val;
    }

    @Override
    public Value eval(Symbol s) {
        if (s == name) {
            return val.eval();
        }
        if (parent == null) {
            return null;
        }
        return parent.eval(s);
    }

    @Override
    public String toString() {
        return "Env: " + name + " = " + val;
    }
}

    
