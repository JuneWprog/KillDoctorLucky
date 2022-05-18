package command;

import mansion.Mansion;
import utils.CommonUtils;

/**
 * Command Pattern Implementation for Action Move for moving the player to a
 * room.
 * 
 * @author komalshah
 *
 */
public class MoveCommand implements MansionCommand {
  private String room;

  /**
   * Command Pattern Implementation for Action Move for moving the player to a
   * room.
   * 
   * @param roomName room name to be moved into.
   * @throws NullPointerException     when room name is NULL.
   * @throws IllegalArgumentException when room name is empty.
   */
  public MoveCommand(String roomName) throws NullPointerException, IllegalArgumentException {
    CommonUtils.stringIsEmpty(roomName, "Room name cannot be NULL or Empty.");
    this.room = roomName;
  }

  @Override
  public String execute(Mansion model) {
    try {
      model.actionMove(room);
    } catch (IllegalAccessException e) {
      return e.getMessage();
    }
    return "Player Moved Successfully.";
  }
}
