package controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import utils.Action;

/**
 * Class to implement Computer Player Moves.
 * 
 * @author komalshah
 *
 */
public class ComputerPlayerInput {

  private static int getRandomInteger(int lowerBound, int upperBound) {
    return ThreadLocalRandom.current().nextInt(lowerBound, upperBound);
  }

  /**
   * Generates random string from the list of strings provided.
   * 
   * @param stringList list to generate string from.
   * @return random string generated.
   * @throws IllegalArgumentException when list is empty.
   * @throws NullPointerException     when list is NULL.
   */
  public static String getRandomString(List<String> stringList)
      throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(stringList);
    if (stringList.size() == 0) {
      throw new IllegalArgumentException("No options to choose from.");
    }
    int randomIndex = ComputerPlayerInput.getRandomInteger(0, stringList.size());
    return stringList.get(randomIndex);
  }

  /**
   * Picks an item with maximum damage.
   * 
   * @param items map of items with damage that can be used to kill.
   * @return item name with the maximum damage.
   * @throws NullPointerException when map is NULL.
   * @throws IllegalArgumentException when list is empty.
   */
  public static String pickItemToKill(Map<String, Integer> items) throws NullPointerException {
    Objects.requireNonNull(items);
    if (items.isEmpty()) {
      throw new IllegalArgumentException("No options to choose from.");
    }
    return Collections.max(items.entrySet(), Map.Entry.comparingByValue()).getKey();
  }

  /**
   * Chooses action for Computer Player. When player is not seen and target is
   * present in the same room as player then it always chooses to Kill and random
   * otherwise.
   * 
   * @param isSeen        if player is seen by other player.
   * @param targetPresent if target is present in the same room as player.
   * @return random action.
   */
  public static Action chooseAction(boolean isSeen, boolean targetPresent) {
    if (!isSeen && targetPresent) {
      return Action.KILL;
    } else {
      Action[] actions = Arrays.stream(Action.values())
          .filter(x -> x.isGamePlayAction() && !x.equals(Action.KILL)).toArray(Action[]::new);
      return actions[ComputerPlayerInput.getRandomInteger(0, actions.length)];
    }
  }
}
