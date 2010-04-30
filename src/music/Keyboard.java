package music;

import java.util.ArrayList;

/**
 * @author Christopher Potter
 */
public class Keyboard
{
        int keyXLocale = 0;
        final int keyYLocale = 0;
        int bKeyXLocale = 12;
        public ArrayList<Key> keys = new ArrayList<Key>();

        public Keyboard()
        {
            for(int i=0; i<21; i++)
            {
                keys.add(new WhiteKey(keyXLocale, keyYLocale));
                keyXLocale += 20;
            }
            for (int i=0; i<21; i++)
            {
                keys.add(new BlackKey(bKeyXLocale, keyYLocale));
                bKeyXLocale += 20;
            }
            keys.remove(23);
            keys.remove(26);
            keys.remove(28);
            keys.remove(31);
            keys.remove(33);
            keys.remove(36);
        }
}