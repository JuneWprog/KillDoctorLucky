package command;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import mansion.Mansion;

/**
 * Command Design Patter to save graphical representation of the Mansion.
 * 
 * @author komalshah
 *
 */
public class GraphicalRepresentationCommand implements MansionCommand {

  @Override
  public String execute(Mansion model) {
    BufferedImage bufferedImage = model.createGraphicalRepresentation();
    File file = new File("myimage.png");
    try {
      ImageIO.write(bufferedImage, "png", file);
    } catch (IOException e) {
      return "Error while writing image.";
    }
    return "Image Saved Successfully.";
  }

}
