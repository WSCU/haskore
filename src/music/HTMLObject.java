package music;

import java.util.ArrayList;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public abstract class HTMLObject {

    private static ArrayList<Token> toks;
    private static String parsedHTML;
    private static String parsedHTMLclose;

    public static String generate(TokenStream ts) {
        parsedHTML = "<html><body>";
        parsedHTMLclose = "</body></html>";

        toks = ts.tokens;
        int linenum = toks.get(0).place.getLine();

        for (int i = 0; i < toks.size(); i++) {

            if (linenum != toks.get(i).place.getLine()) {
                linenum++;
                parsedHTML = parsedHTML + "<br /";
            }

            if (toks.get(i).sameToken(Token.eof)) {
                return parsedHTML + parsedHTMLclose;
            } else if (toks.get(i).isType(TokenType.whiteToken)) {
                parsedHTML = parsedHTML + HTMLComment.generate(toks.get(i));
            } else if (toks.get(i).isType(TokenType.varToken)) {
                if (toks.get(i).isMusic()) {
                    parsedHTML = parsedHTML + HTMLMusic.generate(toks.get(i));
                } else {
                    parsedHTML = parsedHTML + HTMLVar.generate(toks.get(i));
                }
            } else if (toks.get(i).isType(TokenType.puncToken)) {
                parsedHTML = parsedHTML + HTMLKey.generate(toks.get(i));
            } else if (toks.get(i).isMusic()) {
                parsedHTML = parsedHTML + HTMLMusic.generate(toks.get(i));
            } else {
                parsedHTML = parsedHTML + toks.get(i).body;
            }
        }
        return parsedHTML + parsedHTMLclose;
    }
}
