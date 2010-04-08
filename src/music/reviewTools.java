/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package music;

/**
 *
 * @author austin
 */
public class reviewTools {

    public static void HtmlRender(javax.swing.JEditorPane pane, TokenStream toks)
    {
       String body = "";
       for(Token i : toks.tokens)
       {
            body.concat(i.body);
       }
       pane.setText(body);
    }
}
