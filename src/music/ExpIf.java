package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class ExpIf extends Exp {

    public Exp testExp;
    public Exp elseExp;
    public Exp thenExp;
    /**
     * @param testExp assigns the initial expression to test
     * @param thenExp assigns the expression to execute
     * @param elseExp assigns the expression to test should the initial expression fail
     */
    public ExpIf(Exp testExp, Exp thenExp, Exp elseExp) {
        this.testExp = testExp; // this is technically an if expression.
        this.elseExp = elseExp;
        this.thenExp = thenExp;
    }

    @Override // added the @override because the compiler wanted it.
    public boolean isIf() {
        return true;
    }
    /**
     * @param p compares the given ParsedObject to see if it is an if expression
     * @return gives results of the test, returns true if the Expressions are equal, false if it is not.
     */
    public boolean compareTo(ParsedObject p) {
        if (!p.isExp()) {
            return false;
        }
        Exp p1 = (Exp) p;
        if (!p1.isIf()) {
            return false;
        }
        ExpIf p2 = (ExpIf) p1;
        return p2.testExp.compareTo(testExp) && p2.elseExp.compareTo(elseExp) && p2.thenExp.compareTo(thenExp);
    }

    public String print() {
        return "if " + testExp.print() + " then " + thenExp.print() + " else " + elseExp.print();
    }

    @Override
    public Value eval(Env e)
    {
        Value result;
        Value test = testExp.eval(e);
        if(!test.isBool())
            throw new ExecutionError("Expected a boolean in if statement test, but didn't get one.");
        ValBool b = (ValBool)test;
        if(b.val)
            result = thenExp.eval(e);
        else
            result = elseExp.eval(e);
        return result;
    }
}
