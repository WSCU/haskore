package music;

import java.util.ArrayList;

/**
 * @author Christopher Potter
 */
public class Keyboard
{
    public ArrayList<Key> keys = new ArrayList<Key>();
        WhiteKey c1  = new WhiteKey(0, 0);
        BlackKey c1s = new BlackKey(7, 0);
        WhiteKey d1 = new WhiteKey(15, 0);
        BlackKey d1s = new BlackKey(29, 0);
        WhiteKey e1 = new WhiteKey(30, 0);
        WhiteKey f1 = new WhiteKey(45, 0);
        BlackKey f1s = new BlackKey(51, 0);
        WhiteKey g1 = new WhiteKey(60, 0);
        BlackKey g1s = new BlackKey(73, 0);
        WhiteKey a2 = new WhiteKey(75, 0);
        BlackKey a2s = new BlackKey(95, 0);
        WhiteKey b2 = new WhiteKey(90, 0);
        WhiteKey c2 = new WhiteKey(105, 0);
        BlackKey c2s = new BlackKey(128, 0);
        WhiteKey d2 = new WhiteKey(120, 0);
        BlackKey d2s = new BlackKey(150, 0);
        WhiteKey e2 = new WhiteKey(135, 0);
        WhiteKey f2 = new WhiteKey(150, 0);
        BlackKey f2s = new BlackKey(183, 0);
        WhiteKey g2 = new WhiteKey(165, 0);
        BlackKey g2s = new BlackKey(205, 0);
        WhiteKey a3 = new WhiteKey(180, 0);
        BlackKey a3s = new BlackKey(227,0);
        WhiteKey b3 = new WhiteKey(195, 0);
        WhiteKey c3 = new WhiteKey(210, 0);
        public Keyboard()
        {
            keys.add(c1);
            keys.add(c1s);
            keys.add(d1);
            keys.add(d1s);
            keys.add(e1);
            keys.add(f1);
            keys.add(f1s);
            keys.add(g1);
            keys.add(g1s);
            keys.add(a2);
            keys.add(a2s);
            keys.add(b2);
            keys.add(c2);
            keys.add(c2s);
            keys.add(d2);
            keys.add(d2s);
            keys.add(e2);
            keys.add(f2);
            keys.add(f2s);
            keys.add(g2);
            keys.add(g2s);
            keys.add(a3);
            keys.add(a3s);
            keys.add(b3);
            keys.add(c3);
        }
}
