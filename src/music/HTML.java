package music;

import java.util.ArrayList;
import music.Token;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class HTML {

    public static String tokenstreamHTML(TokenStream t, ArrayList<Decl> decls, EnvHash env) {
        String generatedHTML = "<html><body>";
        boolean func = false;
        for (Token t1 : t.tokens) {
            
            switch (t1.type) {
                case varToken:
                   if (func) {
                        int i = t.tokens.indexOf(t1) + 1;
                        if (i < t.tokens.size()) {
                            if (!t.tokens.get(i).isType(TokenType.whiteToken) && t.tokens.get(i).isOp()) {
                                func = false;
                            }
                        }
                        generatedHTML += HTMLNum(t1);
                        break;
                    }
                    for (Decl d : decls) {
                        int i = 0;
                        String funcName = d.LHS.asVar().getBody();
                        System.out.println(d.LHS.isPat() + " " + ((PatVar) d.LHS).asVar());
                        if (funcName.equals(t1.body) && (i < decls.size() && decls.get(i).RHS.isLambda())) {                         
                            func = true;
                            break;
                        }
                        i++;
                    }
                    generatedHTML += func ? HTMLFunc(t1) : HTMLVar(t1);
                    break;
                case semiToken:
                    generatedHTML += "<br/>";
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
        //make non music vals non a
        String color = "#228B22"; //green
        if (t.isMusic()) {
            color = "#FF00FF";//purple
        }
        if (t.tokVal != null ) {
            if(t.tokVal.isMusic() || t.isMusic())
                return "<a href='#' style='color:" + color + ";'>" + t.body + "</a>";
            
        }
        return "<span style='color:" + color + ";'>" + t.body + "</span>";
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
        for (int i = 0; i < t.body.length(); i++) {
            if (t.body.charAt(i) == "&".charAt(0)) {
                punc += "&amp;";
            } else {
                punc += t.body.charAt(i);
            }
        }
        String color = "#cc12EE";
        return "<span style=\"color:" + color + ";\">" + punc + "</span>";

    }

    private static String HTMLWhite(Token t) {
        String whiteSpace = "";
        for (int i = 0; i < t.body.length(); i++) {
            if (t.body.charAt(i) == " ".charAt(0)) {
                whiteSpace += "&nbsp;";
            } else if (t.body.charAt(i) == "\n".charAt(0)) {
                whiteSpace += "< br/>";
            }
        }
        return whiteSpace;
    }

    private static String HTMLError(Token t) {
        String color = "#ff0000";
        return "<span style=\"color:" + color + ";text-decoration:underline;\">" + t.body + "</span>";
    }
}