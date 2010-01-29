/**
 * 
 * The Functional Music project
 * @author Western State College, CIS412 class
 */

package music;

import java.awt.Graphics;


public abstract class PianoKey {
    
    public int note;
    public boolean white;
    public int x;
    public int y;
    public int width;
    public int height;
    
    public boolean within(int x, int y) {
     return x > this.x && x < (this.x + this.width) && y > this.y && y < (this.y + this.height);   
    }
    
    public abstract void draw(Graphics g);

}
