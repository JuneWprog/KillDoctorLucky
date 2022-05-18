package gameplay;

import controller.MansionConsoleController;
import controller.MansionGuiController;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import mansion.Mansion;
import mansion.MansionBuilder;
import utils.Constants;
import view.MansionConsoleView;
import view.MansionGuiView;

/**
 * Driver class to play games.
 * 
 * @author komalshah
 *
 */
public class GamePlay {

  /**
   * Driver Method to play game.
   * 
   * @param args file name containing the world specification and the total number
   *             of turns for the game.
   */
  public static void main(String[] args) {
    try {
      if (args.length != 4) {
        throw new IllegalArgumentException(
            "Incorrect number of arguments passed. Validate arguments against README.");
      }
      FileReader fileReader = new FileReader(args[0]);
      int numberOfTurns = Integer.parseInt(args[1]);
      int maxPlayers = Integer.parseInt(args[2]);
      String gameMode = args[3];

      BufferedReader br = new BufferedReader(fileReader);

      MansionBuilder builder = new MansionBuilder().readConfigFile(br)
          .setMaximumNumberOfPlayers(maxPlayers).setNumberOfTurns(numberOfTurns);
      Mansion mansion = builder.build();

      if (Constants.GUI.equalsIgnoreCase(gameMode)) {
        new MansionGuiController(mansion, builder, new MansionGuiView("Kill Doctor Lucky"))
            .playGame();
      } else if (Constants.TEXT.equalsIgnoreCase(gameMode)) {
        Readable input = new InputStreamReader(System.in);
        Appendable output = System.out;
        new MansionConsoleController(input, mansion, builder, new MansionConsoleView(output))
            .playGame();
      } else {
        throw new IllegalArgumentException("Invalid Game Mode.");
      }

    } catch (IllegalArgumentException | IOException | NullPointerException | IllegalStateException
        | NoSuchElementException exception) {
      System.out.println(exception.getMessage());
    }
  }
}
