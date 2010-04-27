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
        Decl dkeep = null;
        for (Token t1 : t.tokens) {
           
            switch (t1.type) {
                case varToken:
                    if (t1.symbol.isMusic()) {
                        generatedHTML += HTMLMFunc(t1);
                        break;
                    }
                    if (func) {
                        if (dkeep != null) {
                            String c = dkeep.RHS.print();
                            
                            if (!c.contains(t1.body)) {
                                func = false;
                                dkeep = null;
                            }
                            generatedHTML += HTMLNum(t1);
                            break;
                        }
                    }
                   
                    for (Decl d : decls) {
                       
                        String funcName = d.LHS.asVar().getBody();
                        if (funcName.equals(t1.body) && d.RHS.isLambda()) {
                            func = true;
                            dkeep = d;
                            break;
                        }
                    }
                    generatedHTML += func ? HTMLFunc(t1) : HTMLVar(t1);
                    break;
                case semiToken:
                    func = false;
                    generatedHTML += "<br />";
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

    public static void tokenstreamHTML2(TokenStream t, ArrayList<Decl> decls, EnvHash env) {
        for (Decl d : decls) {
            if (d.isPat()) {
                System.out.println(d.firstToken);
                System.out.println(d.LHS);
            }
            if (d.isExp()) {
                System.out.println(d.RHS);
            }
        }

    }

    private static String HTMLVar(Token t) {
        //make non music vals non a
        String color = "#228B22"; //green
        if (t.isMusic()) {
            color = "#FF00FF";//purple
        }
        if (t.tokVal != null) {
            return "<a href='#' style='color:" + color + ";'>" + t.body + "</a>";
        }
        return "<span style='color:" + color + ";'>" + t.body + "</span>";
    }

    private static String HTMLFunc(Token t) {
        String color = "#00EEFF";
        return "<strong style=\"color:" + color + ";font-weight:bold;\">" + t.body + "</strong>";
    }
    private static String HTMLMFunc(Token t) {
        String color = "#00cc00";
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

        return "<span style=\"background-color:" + color + ";text-decoration:underline;\">" + t.symbol + ", " + t.body + "</span>";
    }
}