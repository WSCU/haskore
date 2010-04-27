package music;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.text.BadLocationException;

/**
 *
 * @author austin
 */
public class ReviewTools {

    private static TokenStream world;

    public static Env compile(javax.swing.JEditorPane editPane,
            javax.swing.JEditorPane browsePane) {

        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        //Pay attention to lex errors and to parse errors.
        TokenStream str = Lexer.lexString(editPane.getText());
        ArrayList<Decl> binds = Parser.parseDecls(str);
        for (Decl d : binds) {
            userEnv.add(d.LHS.asVar(), new Thunk(userEnv, d.RHS));
        }
        try {
            //assumes that every left hand side is a sple variable.
            for (Decl d : binds) {
                Value result = userEnv.eval(d.LHS.asVar());
                if(d.LHS.isPat()){
                    PatVar pv = (PatVar)d.LHS;
                    pv.body.addValue(result);
                }
                System.out.println(d.RHS.isLambda()+" "+d.RHS.toString());
                System.out.println("SymThunk: "+d.LHS.asVar()+" v: "+result);
            }
        } catch (ExecutionError e) {
            System.out.println("Execution Error: " + e.msg);
        }
        HtmlRender(browsePane, str, binds, userEnv);
        world = str;
        return userEnv;
    }

    public static void HtmlRender(javax.swing.JEditorPane pane,
            TokenStream toks,
            ArrayList<Decl> dec, EnvHash env) {
        pane.setText(HTML.tokenstreamHTML(toks, dec, env));
    }

    public static void action(javax.swing.event.HyperlinkEvent evt, javax.swing.JEditorPane pane) {
        int i = evt.getSourceElement().getStartOffset();
        int e = evt.getSourceElement().getEndOffset();
        System.out.println(i + " " + e);
        try {
            if (evt.getEventType() == EventType.ENTERED) {

                Token var = world.findToken(pane.getText(i, e - i));
                System.out.println("Token Body: " + var.body + " v: " + var.tokVal + " type: " + var.type);
            }
            if (evt.getEventType() == EventType.ACTIVATED) {
                Token var = world.findToken(pane.getText(i, e - i));

                if(var.tokVal!=null && var.tokVal.isMusic())
                {
                    ValMusic tv = (ValMusic)var.tokVal;
                    Performance result = new Performance(tv.val);
                    result.perform();
                }
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(ReviewTools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}