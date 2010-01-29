package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class DeclVal extends Decl{
    
    public DeclVal(Token firstToken, Pat LHS, Exp RHS) {
        this.firstToken = firstToken;
        this.LHS = LHS;
        this.RHS = RHS;
    }    
    
/**
 * Compares the LHS and RHS of this and ParsedObject p after typecasting it into
 * a DeclVal.
 * @param p the ParsedObject to be compared to, will only pass if it is a DeclVal
 * @return true if the RHS and LHS of both objects, false if they are different
 * or if p isn't a DeclVal
 */
    public boolean compareTo(ParsedObject p) {
        if (!p.isDecl())
            return false;
        Decl p1 = (Decl)p;
        if(!p1.isVal())
            return false;
        DeclVal p2 = (DeclVal)p1;
        return RHS.compareTo(p2.RHS) && LHS.compareTo(p2.LHS);
    }

    /**
     * Appends the output from the LHS.print to the RHS.print with = inbetween
     * @return String containing the print methods from the LHS and RHS.
     */
    public String print() {
        return LHS.print()+"="+RHS.print();
    }
    
    

}
