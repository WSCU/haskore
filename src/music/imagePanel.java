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
class imagePanel extends JPanel {


    BufferedImage image;
    public imagePanel(String path)
    {
        try {
          image = ImageIO.read(new File(path));
       } catch (IOException ex) {
            System.out.println("Chris write your own code");
       }

        
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (image != null) {

            g.drawImage(image, 0, 0, this);

        }

    }
}