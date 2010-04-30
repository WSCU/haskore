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
    final int Height = 70;
    final int Width = 20;

    public WhiteKey(int x1, int y1)
    {
        startX = x1;
        startY = y1;
    }

    @Override
    public void paint(Graphics g, int octave)
    {
        g.setColor(Color.white);
        g.fillRect(startX, startY, Width, Height);
        g.setColor(Color.black);
        g.drawRect(startX, startY, Width, Height);
    }
}