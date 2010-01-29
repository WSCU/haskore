/**
 * 
 * The Functional Music project
 * @author Western State College, CIS412 class
 */

package music;

import java.awt.Color;
import java.awt.Graphics;


public class PianoKeyBlack extends PianoKey {
    
    public PianoKeyBlack(int note, int x, int y, int width, int height) {
        this.note = note;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect( x, y, width, height);        
    }

}
