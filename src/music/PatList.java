
package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
import java.util.ArrayList;
public class PatList extends Pat{
    public ArrayList<Pat> body;
    
    public PatList(ArrayList<Pat> body) {
        this.body = body;
    }
    
    @Override
    public boolean isList(){
        return true;
    }

    public boolean compareTo(ParsedObject p) {
        if(!p.isPat()) return false;
        Pat pat = (Pat) p;
        if(!pat.isList()) return false;
        PatList list = (PatList) pat;
        if(body.size()!=list.body.size()) return false;
        for (int i = 0; i<body.size(); i++){
            if (!body.get(i).compareTo(list.body.get(i)))
                return false;
        }
        return true;
    }

    public String print() {
        String content = "[";
        for (Pat temp : body){
            content += temp.print() + ", ";
        }
        return content + "]";
    }

}