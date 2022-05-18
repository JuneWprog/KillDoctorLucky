package command;

import mansion.Mansion;
import utils.CommonUtils;

/**
 * Command Pattern Implementation for Action Attempt to Kill Target by using an
 * item with the player.
 * 
 * @author komalshah
 *
 */
public class AttemptKillCommand implements MansionCommand {
  private String item;

  /**
   * Command Pattern Implementation for Action Attempt to Kill Target by using an
   * item with the player.
   * 
   * @param itemName item name to be use to kill.
   * @throws NullPointerException     when item name is NULL.
   * @throws IllegalArgumentException when item name is empty.
   */
  public AttemptKillCommand(String itemName) throws NullPointerException, IllegalArgumentException {
    CommonUtils.stringIsEmpty(itemName, "Item name cannot be NULL or Empty.");
    this.item = itemName;
  }

  @Override
  public String execute(Mansion model) {
    boolean isSuccess;
    try {
      isSuccess = model.actionAttemptKill(item);
    } catch (IllegalAccessException e) {
      return e.getMessage();
    }
    if (isSuccess) {
      return "Attempt to kill is Successful.";
    } else {
      return "Attempt to kill is not Successful.";
    }
  }
}
