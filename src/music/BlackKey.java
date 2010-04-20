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
    final int Width = 15;
    final int Height = 40;
    int octave;

    public BlackKey(int x1, int y1)
    {
        startX = x1;
        startY = y1;
    }

    @Override
    public void paint(Graphics g, int octave)
    {
        g.setColor(Color.black);
        g.fillRect(startX, startY, Width, Height);
    }
}
