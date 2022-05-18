package command;

import mansion.Mansion;
import utils.CommonUtils;

/**
 * Command Pattern Implementation to check if Player A can see Player B.
 * 
 * @author komalshah
 *
 */
public class CanSeeCommand implements MansionCommand {
  private String player1;
  private String player2;

  /**
   * Command Pattern Implementation to check if Player A can see Player B.
   * 
   * @param playerA player A.
   * @param playerB player B.
   * @throws NullPointerException     when player name is NULL.
   * @throws IllegalArgumentException when player name is empty.
   */
  public CanSeeCommand(String playerA, String playerB)
      throws NullPointerException, IllegalArgumentException {
    CommonUtils.stringIsEmpty(playerA, "Player A name cannot be NULL or Empty.");
    CommonUtils.stringIsEmpty(playerB, "Player B name cannot be NULL or Empty.");
    this.player1 = playerA;
    this.player2 = playerB;
  }

  @Override
  public String execute(Mansion model) {
    boolean canSee = model.canSee(player1, player2);
    return canSee ? "Player A can see Player B." : "Player A cannot see Player B.";
  }

}
