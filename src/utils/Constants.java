package utils;

/**
 * Constants class.
 * 
 * @author komalshah
 *
 */
public class Constants {
  public static final String CONST_Y = "Y";
  public static final String YES = "Yes";
  public static final String POKING = "Poking";
  public static final String NULL = "NULL";
  public static final String GUI = "GUI";
  public static final String TEXT = "TEXT";
  public static final String NEW_GAME = "New Game";
  public static final String CREATE_NEW_GAME = "Create New Game";
  public static final String RESTART_GAME = "Restart Game";
  public static final String QUIT_GAME = "Quit Game";
  public static final String ABOUT_GAME = "About Game";
  public static final String HOW_TO_PLAY_GAME = "How to Play Game";
  public static final String ADD_PLAYER = "Add Player";
  public static final String ADD = "Add";
  public static final String PLAY_GAME = "Play Game";
  public static final int TIMER_DELAY = 5000;

  public static final String ABOUT_STRING = "<html>"
      + "The world of our project consists of several non-overlapping spaces"
      + " that are laid out on a 2D grid.<br/> The details of the world are "
      + "specified in a simple text file that consists of three sections: <br/>"
      + "<br/>1. world description including the size, the name, the target "
      + "character, and the target character's pet.<br/> 2. a detailed list of "
      + "all the spaces or rooms that make up the world<br/> 3. a detailed list "
      + "of all the items that can be found in the world<br/> "
      + "<br/>This game has two types of players: Human and Computer Players. "
      + "Each player should be identified by their name. <br/> They enter the world "
      + "in a space of their choice. With multiple players, each player gets a turn "
      + "in the order in which <br/>they were added to the game. The options for "
      + "actions that player can take in a single turn are:<br/><br/> 1. Moving "
      + "to a neighboring space.<br/> 2. Picking up an item from the space they "
      + "are currently occupying.<br/> 3. Look around the space they are currently "
      + "occupying. <br/> 4. Moving Target Pet to a space. <br/> 5. Attempt to kill "
      + "a Target using the Items they have. <br/>"
      + "</html>";

  public static final String HELP_TIPS = "<html>"
      + "How To Play<br/>"
      + "<br/>"
      + "Menu<br/>"
      + "New Game allows users to change the settings of the game.<br/>"
      + "Restart Game allows users to start the game with the previous settings.<br/>"
      + "Add Player allow users to add a player to the game.<br/>"
      + "Quit Game allow users to quit the game.<br/>"
      + "<br/>"
      + "<b>Keys</b> <br/>"
      + "Press l  display information of neighbors. <br/>"
      + "Press k  player can attempt an attack with one item.<br/>"
      + "Press p  player can pick up an item from the room. <br/>"
      + "<br/>"
      + "<b>Mouse Click</b> <br/>"
      + "Click on a player's icon displays player's description.<br/>"
      + "Click on a neighbor room, the current player moves to that room. <br/> "
      + "Right Click on a room, the target pet moves to that room. <br/> "
      + "</html>";
}
