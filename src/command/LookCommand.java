package command;

import mansion.Mansion;

/**
 * Command Pattern Implementation for Action Look for looking around in the
 * current room.
 * 
 * @author komalshah
 *
 */
public class LookCommand implements MansionCommand {

  @Override
  public String execute(Mansion model) {
    try {
      return model.actionLook();
    } catch (IllegalAccessException e) {
      return e.getMessage();
    }
  }
}
