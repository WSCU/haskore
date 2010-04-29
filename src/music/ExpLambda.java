//Question: what is the point of the typecheck on line 22 in .eval?//`-16-2009 Kendricpackage music;/** * The Functional Music project * @author Western State College, CIS412 class */public class ExpLambda extends Exp {    Pat LHS;    Exp RHS;    public ExpLambda(Pat LHS, Exp RHS) {        this.LHS = LHS;        this.RHS = RHS;    }    @Override    public boolean isLambda() {        return true;    }    @Override    public Value eval(Env env) {        //Why are we checking to see if LHS is a pat when it is of type pat?                if (!LHS.isPat()) {            //PatConst v = (PatConst) LHS;            //return new ValFuncLambda(env, RHS, v.body.symbol);            throw new ExecutionError("Can't handle pats in lambda yet");        }        else {            PatVar v = (PatVar) LHS;            return new ValFuncLambda(env, RHS, v.body.symbol);        }    }    public boolean compareTo(ParsedObject p) {        if (!p.isExp()) {            return false;        }        Exp p1 = (Exp) p;        if (!p1.isLambda()) {            return false;        }        ExpLambda p2 = (ExpLambda) p1;        return LHS.compareTo(p2.LHS) && RHS.compareTo(p2.RHS);    }    public String print() {        return LHS.print() +" " +RHS.print();    }}