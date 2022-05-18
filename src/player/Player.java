package player;

import item.Item;
import java.util.Map;

/**
 * A player is used to play this game. Each player should be identified by their
 * name. They enter the world in a room of their choice. There are two types of
 * players in this game: Human and Computer.
 * 
 * @author komalshah
 *
 */
public interface Player {
  /**
   * Gets the name of the Player.
   * 
   * @return the name of the player.
   */
  String getName();

  /**
   * Gets the location of the Player.
   * 
   * @return the location of the player.
   */
  int getLocation();

  /**
   * Moves the player to the given location.
   * 
   * @param location the location to move player.
   * @throws IllegalArgumentException when location is negative.
   */
  void movePlayer(int location) throws IllegalArgumentException;

  /**
   * Adds Item for the Player.
   * 
   * @param item the item to be added for player.
   * @throws IllegalArgumentException when item already exists.
   * @throws NullPointerException     when item is NULL.
   * @throws IllegalStateException   when capacity for player is full
   */
  void addItem(Item item)
      throws IllegalArgumentException, NullPointerException, IllegalStateException;

  /**
   * Checks if the player is Human.
   * 
   * @return true if the player is human.
   */
  boolean isHuman();

  /**
   * Gets an item from the player.
   * 
   * @param itemName name of the item.
   * @return the item.
   */
  Item getItem(String itemName);

  /**
   * Removes the item from the player.
   * 
   * @param item the item to be removed.
   * @throws IllegalArgumentException when item is not with player.
   */
  void removeItem(Item item) throws IllegalArgumentException;

  /**
   * Gets the map of items names with damage that player has.
   * 
   * @return map of items names with damage that player has.
   */
  public Map<String, Integer> getItemDetails();
}
