package music;

import java.util.ArrayList;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class ExpList extends Exp{
    public ArrayList<Exp> body;
    
    public ExpList(ArrayList<Exp> body) {
        this.body = body;
    }
    
    @Override
    public boolean isList(){
        return true;
    }
    
    public boolean compareTo(ParsedObject p) {
        if(!p.isExp()) return false;
        Exp exp = (Exp) p;
        if(!exp.isList()) return false;
        ExpList list = (ExpList) exp;
        if(body.size()!=list.body.size()) return false;
        for (int i = 0; i<body.size(); i++){
            if (!body.get(i).compareTo(list.body.get(i)))
                return false;
        }
        return true;
    }
    
    public String print() {
        String content = "[";
        for (Exp temp : body){
            content += temp.print() + ", ";
        }
        return content + "]";
    }

}
