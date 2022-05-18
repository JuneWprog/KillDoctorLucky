package view;

import java.util.List;
import java.util.Map;
import listener.ButtonListener;
import listener.KeyboardListener;
import listener.MouseClickListener;

/**
 * Interface to represent the user interface of the game.
 * 
 * @author komalshah
 *
 */
public interface MansionView {

  /**
   * Displays the list of items with their damages available for use.
   * 
   * @param items list of items with their damages.
   * @return selected item name.
   */
  String displayItems(Map<String, Integer> items);

  /**
   * Displays the message as information to the user.
   * 
   * @param message information message.
   */
  void displayInfo(String message);

  /**
   * Displays the message as error to the user.
   * 
   * @param message error message.
   */
  void displayError(String message);

  /**
   * Restarts the game.
   * 
   * @param roomNames list of rooms in the current game.
   */
  void restartGame(List<String> roomNames);

  /**
   * Displays the screen to add players in the game.
   * 
   * @param roomNames list of rooms in the current game.
   */
  void addPlayerScreen(List<String> roomNames);

  /**
   * Displays the screen to setup a new game with a new world specification.
   */
  void setupScreen();

  /**
   * Refreshes board to display the current state of the game.
   * 
   * @param rooms          Map of Room Names with their location.
   * @param players        Map of Players with their location.
   * @param petLocation    location of Target's Pet.
   * @param targetLocation location of Target.
   * @param currentPlayer  current player of the game.
   * @param targetDetails  details of Target.
   * @param numberOfTurns  Number of turns left in the game.
   * @param message        Result of the previous action.
   * @param listener       Listener to execute the actions.
   */
  void refreshBoard(Map<String, int[]> rooms, Map<String, String> players, String petLocation,
      String targetLocation, String currentPlayer, String targetDetails, int numberOfTurns,
      String message, MouseClickListener listener);

  /**
   * Displays the welcome screen.
   */
  void welcomeScreen();

  /**
   * Quits the game.
   */
  void quit();

  /**
   * Adds button listener to the components in the game.
   * 
   * @param buttonListener button listener object.
   */
  void addActionListener(ButtonListener buttonListener);

  /**
   * Adds keyboard listener to the components in the game.
   * 
   * @param keyboardListener keyboard listener object.
   */
  void addActionListener(KeyboardListener keyboardListener);

  /**
   * Asks for confirmation from the user.
   * 
   * @param message String asking confirmation.
   * @return integer value of selected option same as JOptionPane.
   */
  int confirmScreen(String message);

  /**
   * Gets the input to create player.
   * 
   * @return String array with name of the player, starting location of the
   *         player, item picking capacity and if the player is human or not, in
   *         the same order.
   */
  String[] getPlayerInput();

  /**
   * Sets player's color representation against their name.
   * 
   * @param roomNames list of rooms in the current game.
   */
  void setPlayerColor(List<String> roomNames);

  /**
   * Gets the input to create new game.
   * 
   * @return String array with file path of the new configuration file, number of
   *         turns for new game and maximum number of players for the new game, in
   *         the same order.
   */
  String[] getSetupInput();

  /**
   * Displays the results of the game when game ends.
   * 
   * @param result result of the game.
   */
  void gameEndScreen(String result);
}
