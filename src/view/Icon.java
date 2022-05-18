package view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Icon class represents an image icon.
 */
public class Icon extends ImageIcon {
  private static final long serialVersionUID = 1L;
  private File fileObj;

  /**
   * Constructor for an icon.
   *
   * @param file the original image file.
   */
  public Icon(File file) {
    this.fileObj = file;
  }

  /**
   * Toggle color for players' icons.
   *
   * @param newColor the color for player.
   * @return ImageIcon a new ImageIcon in the given color.
   */
  public ImageIcon setIconColor(Color newColor) {
    // read image
    BufferedImage image = null;
    try {
      image = ImageIO.read(this.fileObj);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // write to new image
    BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(),
        BufferedImage.TYPE_INT_ARGB);
    // write each pixel
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        int pixelColor = image.getRGB(x, y);

        // pixels are not transparent, change color
        if (pixelColor < 0) {
          // int color = new Color(100, 100, 100).getRGB();
          int color = newColor.getRGB();
          newImage.setRGB(x, y, color);
        }
      }
    }
    return new ImageIcon(newImage);
  }

}
