package music;

import java.awt.Color;
import java.awt.Graphics;


/**
 * @author Christopher Potter
 */
public class WhiteKey extends Key
{
    int startX;
    int startY;
    int endX;
    final int endY = 70;

    public WhiteKey(int x1, int y1)
    {
        startX = x1;
        startY = y1;
        endX = x1 + 15;
    }

    @Override
    public void paint(Graphics g, int octave)
    {
        g.setColor(Color.white);
        g.fillRect(startX, startY, endX, endY);
        g.setColor(Color.black);
        g.drawRect(startX, startY, endX, endY);
    }
}
