
package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class PatConst extends Pat {
    public Token body;
    
    public PatConst(Token body) {
        this.body = body;
    }
    
    @Override
    public boolean isConst(){
       return true;
    }
    public boolean compareTo(ParsedObject p) {
        if (!p.isPat()) return false;
        Pat p1 = (Pat)p;
        if(!p1.isConst()) return false;
        PatConst p2 = (PatConst)p1;
        return p2.body.symbol == body.symbol;
    }

    public String print() {
        return body.toString();
    }
}