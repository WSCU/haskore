package music;

import java.util.ArrayList;
import music.Token;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class HTML {

    public static String tokenstreamHTML(TokenStream t) {
        String generatedHTML = "<html><body>";
        for (Token t1 : t.tokens) {
            switch (t1.type) {
                case varToken:
                    generatedHTML += HTMLVar(t1);
                    break;
                case semiToken:
                    generatedHTML += "< \br>";
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
        return punc;
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
}