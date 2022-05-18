package command;

import mansion.Mansion;
import utils.CommonUtils;

/**
 * Command Design Pattern implementation for Adding Player in the game.
 * 
 * @author komalshah
 *
 */
public class AddPlayerCommand implements MansionCommand {

  private String name;
  private String room;
  private int pickCapacity;
  private boolean human;

  /**
   * Command Design Pattern implementation for Adding Player with limiting item
   * picking capacity in the game.
   * 
   * @param playerName name of the player.
   * @param roomName   name of the room where player is added.
   * @param capacity   item picking capacity.
   * @param isHuman    true if player added is human, false otherwise.
   * @throws NullPointerException     when player or room name is NULL.
   * @throws IllegalArgumentException when player or room name is empty and item
   *                                  picking capacity is less than 1.
   */
  public AddPlayerCommand(String playerName, String roomName, int capacity, boolean isHuman)
      throws NullPointerException, IllegalArgumentException {
    CommonUtils.stringIsEmpty(playerName, "Player name cannot be NULL or Empty.");
    CommonUtils.stringIsEmpty(roomName, "Room name cannot be NULL or Empty.");
    this.name = playerName;
    this.room = roomName;
    this.human = isHuman;
    this.pickCapacity = capacity;
  }

  @Override
  public String execute(Mansion model) {
    if (pickCapacity == -1) {
      model.addPlayer(name, room, human);
    } else {
      model.addPlayer(name, room, pickCapacity, human);
    }
    return "Player Added successfully.";
  }

}
