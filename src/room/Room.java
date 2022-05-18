package room;

import item.Item;
import java.util.Map;

/**
 * A room represents a space in the Mansion. The space can have items and
 * players in it.
 * 
 * @author komalshah
 *
 */
public interface Room {

  /**
   * Gets the name of the Room.
   * 
   * @return the name of the Room.
   */
  public String getName();

  /**
   * Gets the Location coordinates in form of { topLeftx, topLefty, bottomRightx,
   * bottomRighty }.
   * 
   * @return an array with Location coordinates.
   */
  public int[] getLocation();

  /**
   * Gets the map of items with their damage from the room.
   * 
   * @return map of items with damage.
   */
  public Map<String, Integer> getItemDetails();

  /**
   * Adds non-null Item to the Room.
   * 
   * @param item An Item Object.
   * @throws IllegalArgumentException when item is already part of room.
   * @throws NullPointerException     when Item is NULL.
   */
  public void addItemToRoom(Item item) throws IllegalArgumentException, NullPointerException;

  /**
   * Gets an item from the room.
   * 
   * @param itemName name of the item.
   * @return the item.
   */
  public Item getItem(String itemName);

  /**
   * Removes the item from the room.
   * 
   * @param item the item to be removed.
   * @throws IllegalArgumentException when item is not part of the room.
   */
  public void removeItem(Item item) throws IllegalArgumentException;

}
