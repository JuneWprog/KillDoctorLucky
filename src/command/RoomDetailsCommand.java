package command;

import mansion.Mansion;
import utils.CommonUtils;

/**
 * Command Pattern Implementation for Fetching specific Room details.
 * 
 * @author komalshah
 *
 */
public class RoomDetailsCommand implements MansionCommand {
  private String room;

  /**
   * Command Pattern Implementation for Fetching specific Room details.
   * 
   * @param roomName room name to fetch the details.
   * @throws NullPointerException     when room name is NULL.
   * @throws IllegalArgumentException when room name is empty.
   */
  public RoomDetailsCommand(String roomName) throws NullPointerException, IllegalArgumentException {
    CommonUtils.stringIsEmpty(roomName, "Room name cannot be NULL or Empty.");
    this.room = roomName;
  }

  @Override
  public String execute(Mansion model) {
    return model.getDetailsOfRoom(room);
  }
}
