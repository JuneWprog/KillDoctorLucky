package command;

import mansion.Mansion;
import utils.CommonUtils;

/**
 * Command Pattern Implementation for Fetching specific Player details.
 * 
 * @author komalshah
 *
 */
public class PlayerDetailsCommand implements MansionCommand {
  private String player;

  /**
   * Command Pattern Implementation for Fetching specific Room details.
   * 
   * @param playerName player name to fetch the details.
   * @throws NullPointerException     when player name is NULL.
   * @throws IllegalArgumentException when player name is empty.
   */
  public PlayerDetailsCommand(String playerName)
      throws NullPointerException, IllegalArgumentException {
    CommonUtils.stringIsEmpty(playerName, "Player name cannot be NULL or Empty.");
    this.player = playerName;
  }

  @Override
  public String execute(Mansion model) {
    return model.getDetailsOfPlayer(player);
  }
}
