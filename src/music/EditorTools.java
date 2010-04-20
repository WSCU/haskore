/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package music;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author austin
 */
public class EditorTools
{
    public static void smrtAddTxt(javax.swing.JEditorPane pane, String sample) {
        String sel = pane.getSelectedText();
        if (sel != null && !sel.equals("") && !sel.matches("\\s*")) {
            pane.replaceSelection(sample);
            return;
        }
        int pos = pane.getCaretPosition();
        String txt = pane.getText();
        if (txt.isEmpty()) {
            pane.setText(sample);
            return;
        }
        if (txt.length() - 1 != pos) {
            String rep = txt.substring(0, pos) + sample + txt.substring(pos, txt.length());
            pane.setText(rep);
        }
    }

}