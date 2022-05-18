package utils;

/**
 * Enum to represent Actions that a user of the game can take.
 * 
 * @author komalshah
 */
public enum Action {

  /**
   * Represents an action for Moving the Player to a Room.
   */
  MOVE(true) {
    @Override
    public String toString() {
      return "move to";
    }
  },

  /**
   * Represents an action for Picking an Item by the Player from a Room.
   */
  PICK(true) {
    @Override
    public String toString() {
      return "pick up";
    }
  },

  /**
   * Represents an action for Looking around in a Room by the Player.
   */
  LOOK(true),

  /**
   * Represents an action for Moving the Target's Pet to a Room by the Player.
   */
  MOVEPET(true) {
    @Override
    public String toString() {
      return "move the pet to";
    }
  },

  /**
   * Represents an action for Attempt to kill the Target by the Player.
   */
  KILL(true) {
    @Override
    public String toString() {
      return "kill the target with";
    }
  },

  /**
   * Represents an action for displaying details of a Room.
   */
  DISPLAY_ROOM(false),

  /**
   * Represents an action for creating image representation of the Mansion.
   */
  CREATE_IMAGE(false),

  /**
   * Represents an action for adding a player to the game.
   */
  ADD_PLAYER(false),

  /**
   * Represents an action for displaying details of a Player.
   */
  DISPLAY_PLAYER(false),

  /**
   * Represents an action to check if two players can see each other.
   */
  CAN_SEE(false),

  /**
   * Represents an action to go back in the game.
   */
  BACK(false),
  
  /**
   * Represents an action for displaying details of a Player.
   */
  DISPLAY_TARGET(false),;

  private final boolean isGamePlayAction;

  /**
   * Parameter i represents if the action is a game play action or not.
   * 
   * @param i true if it is a game play action, false otherwise.
   */
  Action(boolean i) {
    this.isGamePlayAction = i;
  }

  /**
   * Returns if the current action is a game play action.
   * 
   * @return true, if the current action is a game play action, false otherwise.
   */
  public boolean isGamePlayAction() {
    return this.isGamePlayAction;
  }
}
