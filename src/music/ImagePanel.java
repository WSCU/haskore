/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package music;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author austin
 */
class ImagePanel extends JPanel {


    BufferedImage image;
    public ImagePanel(String path)
    {
        try {
            File x = new File(path);
            System.out.println(x.canRead());
          image = ImageIO.read(x);
       } catch (IOException ex) {
            System.out.println(ex.getMessage()+" Chris write your own code");
       }

        
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (image != null) {

            g.drawImage(image, 0, 0, this);

        }

    }
}