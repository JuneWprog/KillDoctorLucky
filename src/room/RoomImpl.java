package room;

import item.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import utils.CommonUtils;

/**
 * A class to represent a Room or a Space in a Mansion.
 *
 * @author komalshah
 */
public class RoomImpl implements Room {
  private final String roomName;
  private final int topLeftx;
  private final int topLefty;
  private final int bottomRightx;
  private final int bottomRighty;
  private final List<Item> listOfItems;

  /**
   * Creates constructor for Room with fields Room Name, its coordinates for top
   * left and bottom right.
   * 
   * @param name   Name of the Room.
   * @param leftX  X coordinate of Top Left corner.
   * @param leftY  Y coordinate of Top Left corner.
   * @param rightX X coordinate of Bottom Right corner.
   * @param rightY Y coordinate of Bottom Right corner.
   * @throws IllegalArgumentException when X and Y coordinates are incorrect.
   * @throws NullPointerException     when room name is NULL.
   */
  public RoomImpl(String name, int leftX, int leftY, int rightX, int rightY)
      throws IllegalArgumentException, NullPointerException {
    validateRoom(name, leftX, leftY, rightX, rightY);
    this.roomName = name;
    this.topLeftx = leftX;
    this.topLefty = leftY;
    this.bottomRightx = rightX;
    this.bottomRighty = rightY;
    this.listOfItems = new ArrayList<>();
  }

  private void validateRoom(String name, int leftX, int leftY, int rightX, int rightY)
      throws IllegalArgumentException, NullPointerException {
    if (leftX < 0 || leftY < 0 || rightX < 0 || rightY < 0) {
      throw new IllegalArgumentException(
          "X and Y coordinates for Top Left and Bottom Right corners of Room cannot be negative.");
    }
    if (leftX >= rightX) {
      throw new IllegalArgumentException(
          "Left X coordinate should be smaller than Right X coordinate");
    }
    if (leftY >= rightY) {
      throw new IllegalArgumentException(
          "Left Y coordinate should be smaller than Right Y coordinate");
    }
    CommonUtils.stringIsEmpty(name, "Room name cannot be NULL or empty.");
  }

  @Override
  public String getName() {
    return roomName;
  }

  @Override
  public int[] getLocation() {
    return new int[] { topLeftx, topLefty, bottomRightx, bottomRighty };
  }

  @Override
  public Map<String, Integer> getItemDetails() {
    return this.listOfItems.stream()
        .collect(Collectors.toMap(r -> r.getName(), r -> r.getDamage()));
  }

  @Override
  public void addItemToRoom(Item item) throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(item);
    if (!this.listOfItems.contains(item)) {
      this.listOfItems.add(item);
    } else {
      throw new IllegalArgumentException(
          String.format("%s is already part of %s.", item.getName(), this.getName()));
    }
  }

  @Override
  public String toString() {
    return String.format(
        "Detail of Room: \nRoom Name = %s \nList of Items in the Room with their capacity = %s",
        roomName, listOfItems);
  }

  @Override
  public Item getItem(String itemName) {
    return this.listOfItems.stream().filter(item -> item.getName().equals(itemName)).findFirst()
        .orElse(null);
  }

  @Override
  public void removeItem(Item item) throws IllegalArgumentException {
    if (this.listOfItems.contains(item)) {
      this.listOfItems.remove(item);
    } else {
      throw new IllegalArgumentException(String.format(
          "%s is not part of %s. Hence cannot remove this item.", item.getName(), this.getName()));
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(roomName);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Room)) {
      return false;
    }
    Room other = (Room) obj;
    return Objects.equals(roomName, other.getName());
  }
}
