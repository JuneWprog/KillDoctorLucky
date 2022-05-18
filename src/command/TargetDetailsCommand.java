package command;

import mansion.Mansion;

/**
 * Command Pattern Implementation for Fetching Target details.
 * 
 * @author komalshah
 *
 */
public class TargetDetailsCommand implements MansionCommand {

  @Override
  public String execute(Mansion model) {
    return model.getTargetDetails();
  }
}
