package music;

import java.util.ArrayList;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;

/**
 *
 * @author austin
 */
public class ReviewTools {


    public static Env compile(javax.swing.JEditorPane editPane,
            javax.swing.JEditorPane browsePane)
    {

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
            }
        } catch (ExecutionError e) {
            System.out.println("Execution Error: " + e.msg);
        }
        HtmlRender(browsePane, str,binds,userEnv);
        return userEnv;
    }
    public static void HtmlRender(javax.swing.JEditorPane pane,
            TokenStream toks,
            ArrayList<Decl> dec, EnvHash env) {
        pane.setText(HTML.tokenstreamHTML(toks, dec, env));
    }
    public static void action(javax.swing.event.HyperlinkEvent evt)
    {
        if (evt.getEventType()==EventType.ENTERED)
        {
            System.out.println(evt.getDescription());
            System.out.println("enter");
        }
        if (evt.getEventType()==EventType.ACTIVATED)
        {
            System.out.println("click");
        }
    }
}