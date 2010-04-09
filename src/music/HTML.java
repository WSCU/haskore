package music;

import java.util.ArrayList;
import music.Token;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class HTML {

    public static String tokenstreamHTML(TokenStream t, ArrayList<Decl> decls) {
        String generatedHTML = "<html><body>";
        
        for (Token t1 : t.tokens) {
            
            System.out.println(t1.type+" - "+t1.body);
            switch (t1.type) {
                case varToken:
                    boolean func = false;
                    for(Decl d : decls)
                    {
                        String funcName = d.LHS.asVar().toString();
                        System.out.println("function name!!!!!="+funcName+" token "+t1.body);
                        if(funcName.equals(t1.body)){
                            func=!func;
                            break;
                        }
                    }
                    generatedHTML += func?HTMLFunc(t1):HTMLVar(t1);
                    break;
                case semiToken:
                    generatedHTML += "<\br>";
                    break;
                case numberToken:
                    generatedHTML += HTMLNum(t1);
                    break;
                case opToken:
                    generatedHTML += HTMLOp(t1);
                    break;
                case puncToken:
                    generatedHTML += HTMLPunc(t1);
                    break;
                case whiteToken:
                    
                    generatedHTML += HTMLWhite(t1);
                    break;
                case eofToken:
                    generatedHTML += "</body></html>";
                    break;
                case errorToken:
                    generatedHTML += HTMLError(t1);
                    break;
            }
        }
        return generatedHTML;
    }

    private static String HTMLVar(Token t) {
        String color = "#228B22"; //green
        if (t.isMusic()) {
            color = "#FF00FF";//purple
        }
        return "<span style=\"color:" + color + ";\">" + t.body + "</span>";
    }
    private static String HTMLFunc(Token t) {
        String color = "#00EEFF";
        return "<strong style=\"color:" + color + ";font-weight:bold;\">" + t.body + "</strong>";
    }

    private static String HTMLNum(Token t) {
        String color = "#FF7F24"; //orange
        return "<span style=\"color:" + color + ";\">" + t.body + "</span>";
    }

    private static String HTMLOp(Token t) {
        String color = "#0000FF"; //blue
        return "<span style=\"color:" + color + ";\">" + t.body + "</span>";
    }

    private static String HTMLPunc(Token t) {
        String punc = "";
        for(int i=0; i<t.body.length(); i++){
            if(t.body.charAt(i) == "&".charAt(0)){
                punc += "&amp;";
            }
            else {
                punc += t.body.charAt(i);
            }
        }
        String color = "#cc12EE";
        return "<span style=\"color:" + color + ";\">" + punc + "</span>";
        
    }

    private static String HTMLWhite(Token t) {
        String whiteSpace = "";
        for(int i=0; i<t.body.length(); i++){
            if(t.body.charAt(i) == " ".charAt(0)){
                whiteSpace += "&nbsp;";
            }
            else if(t.body.charAt(i) == "\n".charAt(0)){
                whiteSpace += "< br/>";
            }
        }
        return whiteSpace;
    }
    private static String HTMLError(Token t) {
        String color = "#EE0000"; //orange
        return "<span style=\"color:" + color + ";\">" + t.body + "</span>";
    }
}