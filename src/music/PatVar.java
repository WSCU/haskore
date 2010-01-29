
package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class PatVar extends Pat {
    public Token body;
    
    public PatVar(Token body) {
        this.body = body;
    }
     
    @Override
    public boolean isVar(){
       return true;
    }
    public boolean compareTo(ParsedObject p) {
        if (!p.isPat()) return false;
        Pat p1 = (Pat)p;
        if(!p1.isVar()) return false;
        PatVar p2 = (PatVar)p1;
        return p2.body.symbol == body.symbol;
    }

    public String print() {
        return body.toString();
    }
}
