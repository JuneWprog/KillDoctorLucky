package command;

import mansion.Mansion;
import utils.CommonUtils;

/**
 * Command Pattern Implementation for Action Pick for picking the item from the
 * current room.
 * 
 * @author komalshah
 *
 */
public class PickCommand implements MansionCommand {
  private String item;

  /**
   * Command Pattern Implementation for Action Pick for picking the item from the
   * current room.
   * 
   * @param itemName item name to be picked.
   * @throws NullPointerException when item name is NULL.
   * @throws IllegalArgumentException when item name is empty.
   */
  public PickCommand(String itemName) throws NullPointerException, IllegalArgumentException {
    CommonUtils.stringIsEmpty(itemName, "Item name cannot be NULL or Empty.");
    this.item = itemName;
  }

  @Override
  public String execute(Mansion model) {
    try {
      model.actionPick(item);
    } catch (IllegalAccessException e) {
      return e.getMessage();
    }
    return "Item Picked Successfully.";
  }
}
