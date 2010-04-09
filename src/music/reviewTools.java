/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package music;

import java.util.ArrayList;

/**
 *
 * @author austin
 */
public class reviewTools {

    public static void HtmlRender(javax.swing.JEditorPane pane, TokenStream toks, ArrayList<Decl> d)
    {
       
       pane.setText(HTML.tokenstreamHTML(toks,d));
    }
}
