package music;
/**
 * 
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public abstract class Decl extends ParsedObject{
    public Exp RHS;
    public Pat LHS;        
    
    @Override
    public boolean isDecl()
    {
        return true;
    }
    public boolean isVal()
    {
        return false;
    }
    
    public boolean isType()
    {
        return false;
    }

}