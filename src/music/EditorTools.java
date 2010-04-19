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

//    public static class Keyboard extends javax.swing.JPanel {
//
//        @Override
//        public void paintComponent(Graphics g) {
//            makeKeys(this,g);
//        }
//
//        public void makeKeys(Keyboard keyboard, Graphics g) {
//            ArrayList<PianoKey> whiteKeys = new ArrayList<PianoKey>();
//            ArrayList<PianoKey> blackKeys = new ArrayList<PianoKey>();
//
//
//            for (int i = 0; i < 7; i++) {
//                int width = keyboard.getWidth() / 7;
//                int height = keyboard.getHeight() - 2;
//                PianoKey temp = new PianoKeyWhite(i, width * i, 0, width, height);
//                whiteKeys.add(temp);
//            }
//            //create the 5 black keys
//
//            for (int i = 0; i < 5; i++) {
//                int width = (int) (keyboard.getWidth() / 7 * .75);
//                int height = keyboard.getHeight() / 2;
//                int placement = i;
//
//                int position = (keyboard.getWidth() / 7) + placement;
//                PianoKey temp = new PianoKeyBlack(i, position * (i + 1), 0, width, height);
//                blackKeys.add(temp);
//            }
//            for (PianoKey key : whiteKeys) {
//                key.draw(g);
//            }
//            for (PianoKey key : blackKeys) {
//                key.draw(g);
//            }
//        }
//    }
}