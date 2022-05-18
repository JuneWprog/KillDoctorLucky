package controller;

import utils.Action;

/**
 * A mansion controller of our project to control how the game is played.
 * 
 * @author komalshah
 *
 */
public interface MansionController {

  /**
   * Executes the function indicated by Action parameter using the arguments.
   * 
   * <table border="1">
   * <tr>
   * <th>Action</th>
   * <th>Action Description</th>
   * <th>Argument Description</th>
   * </tr>
   * <tr>
   * <td>DISPLAY_ROOM</td>
   * <td>Fetches details of the room.</td>
   * <td>Name of the room to get details of.</td>
   * </tr>
   * <tr>
   * <td>CREATE_IMAGE</td>
   * <td>Creates graphical representation of Mansion.</td>
   * <td>NA</td>
   * </tr>
   * <tr>
   * <td>ADD_HUMAN_PLAYER</td>
   * <td>Adds Human Player to the Game.</td>
   * <td>Name of the Player, Name of the Room to add player in, Item picking
   * capacity for player.</td>
   * </tr>
   * <tr>
   * <td>ADD_COMPUTER_PLAYER</td>
   * <td>Adds Human Player to the Game.</td>
   * <td>Name of the Player, Name of the Room to add player in, Item picking
   * capacity for player.</td>
   * </tr>
   * <tr>
   * <td>MOVE</td>
   * <td>Represents Move action to move the player in a different room.</td>
   * <td>Name of the room to move the player.</td>
   * </tr>
   * <tr>
   * <td>PICK</td>
   * <td>Represents Pick action to pick an item for the player.</td>
   * <td>Name of the item to pick by the player.</td>
   * </tr>
   * <tr>
   * <td>LOOK</td>
   * <td>Represents Look action for the player.</td>
   * <td>NA</td>
   * </tr>
   * <tr>
   * <td>MOVEPET</td>
   * <td>Represents Move pet action to move the target's pet in a different
   * room.</td>
   * <td>Name of the room to move the target's pet.</td>
   * </tr>
   * <tr>
   * <td>KILL</td>
   * <td>Represents Kill action to attempt attack on the Target.</td>
   * <td>Name of the item to attack with.</td>
   * </tr>
   * <tr>
   * <td>DISPLAY_PLAYER</td>
   * <td>Fetches details of the player.</td>
   * <td>Name of the player to get details of.</td>
   * </tr>
   * </table>
   * 
   * @param action  the action to take.
   * @param varargs Variable arguments list as required by each action.
   * @return the result of the action.
   * @throws IllegalArgumentException when invalid arguments are passed.
   * @throws NullPointerException     when NULL arguments are passed.
   * @throws IllegalAccessException   when there are no players in the game.
   */
  String execute(Action action, String... varargs)
      throws IllegalArgumentException, NullPointerException, IllegalAccessException;

  /**
   * Method to play a game.
   */
  void playGame();
    
  /**
   * Method to restart the game.
   */
  void restartGame();
  
  /**
   * Method to start a new game.
   */
  void newGame();
  
  /**
   * Method to terminate the game.
   */
  void quitGame();
}
