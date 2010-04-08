/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package music;

/**
 *
 * @author austin
 */
public class editorTools {

    public static void smrtAddTxt(javax.swing.JEditorPane jEditorPane1, String sample) {
        String sel = jEditorPane1.getSelectedText();
        if (sel != null && !sel.equals("") && !sel.matches("\\s*")) {
            jEditorPane1.replaceSelection(sample);
            return;
        }
        int pos = jEditorPane1.getCaretPosition();
        String txt = jEditorPane1.getText();
        if (txt.isEmpty()) {
            jEditorPane1.setText(sample);
            return;
        }
        if (txt.length() - 1 != pos) {
            String rep = txt.substring(0, pos) + sample + txt.substring(pos, txt.length());
            jEditorPane1.setText(rep);
        }
    }

}
