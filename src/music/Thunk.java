//SEMI-FUNCTIONAL  see line 48 in .toString().// 1-28-2009 Kendricpackage music;/** * The Functional Music project * @author Western State College, CIS412 class */public class Thunk {    public static int thunkNum = 0;    public int id;    public Env e;    public Exp prog;    public boolean evaluated = false;    public boolean evaluating = false;    public Value v = null;    public Thunk(Env e, Exp prog) {        this.e = e;        this.prog = prog;        this.id = thunkNum;        thunkNum++;    }    public Thunk(Value v) {        this.v = v;        this.evaluated = true;        this.id = thunkNum;        thunkNum++;    }    public Value eval() {        if (evaluated) {            return v;        }        if (evaluating) {            throw new ExecutionError("infinite evaluation loop.");        }        evaluating = true;        v = prog.eval(e);        if (v == null) {            throw new ExecutionError("Unexpected null in " + prog + ".eval() in Thunk.java #39");        }        evaluating = false;        evaluated = true;        return v;    }    @Override    public String toString() {        if (evaluated) {            return "{@" + id + " " + v.toString() + "}";        }        String res = "{@" + id + " ";        if (evaluating) {            res = res + "* ";        }        //res = res + Printers.printExp(prog);        res = res + "}";        return res;    }    public ValNum asNum() {        eval();        if (v.isNum()) {            return ((ValNum) v);        }        throw new ExecutionError("Number expected");    }    //brett actuaally did this    public ValBool asBool() {        eval();        if (v.isBool()) {            return ((ValBool) v);        }        throw new ExecutionError("Number expected");    }    public ValMusic asMusic() {        eval();        if (v.isMusic()) {            return ((ValMusic) v);        }        throw new ExecutionError("Music expected");    }    public ValFunc asFunc() {        eval();        if (v.isFunc()) {            return ((ValFunc) v);        }        throw new ExecutionError("Function expected");    }}