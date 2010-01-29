package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class ExpIf extends Exp {
    public Exp testExp;
    public Exp elseExp;
    public Exp thenExp;
    public ExpIf(Exp testExp, Exp thenExp, Exp elseExp){
        this.testExp = testExp;
        this.elseExp = elseExp;
        this.thenExp = thenExp;
    }
    
    public boolean isIf(){
        return true;
    }
    
    public boolean compareTo(ParsedObject p) {
        if (!p.isExp())
            return false;
        Exp p1 = (Exp)p;
        if(!p1.isIf())
            return false;
        ExpIf p2 = (ExpIf)p1;
        return p2.testExp.compareTo(testExp)&&p2.elseExp.compareTo(elseExp)&&p2.thenExp.compareTo(thenExp);
    }

    public String print() {
        return "if "+testExp.print()+" then "+thenExp.print()+" else "+elseExp.print();
    }
}
