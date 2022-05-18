package command;

import mansion.Mansion;

/**
 * Command Design Pattern for the Game.
 * 
 * @author komalshah
 *
 */
public interface MansionCommand {
  /**
   * Executes the command using the model passed.
   * 
   * @param model model object.
   * @return result of the execution.
   */
  String execute(Mansion model);
}
