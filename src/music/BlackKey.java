package music;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Christopher Potter
 */
public class BlackKey extends Key
{
    int startX;
    int startY;
    int endX;
    final int endY = 45;
    int octave;

    public BlackKey(int x1, int y1)
    {
        startX = x1;
        startY = y1;
        endX = x1 + 11;
    }

    @Override
    public void paint(Graphics g, int octave)
    {
        g.setColor(Color.black);
        g.fillRect(startX, startY, endX, endY);
    }
}
