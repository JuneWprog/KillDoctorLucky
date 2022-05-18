package player;

import item.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import utils.CommonUtils;

/**
 * An Class for Player. It implements the all the functionalities of Human and
 * Computer Player.
 * 
 * @author komalshah
 *
 */
public class PlayerImpl implements Player {
  private final String playerName;
  private int playerLocation;
  private final List<Item> listOfItems;
  private int capacity;
  private final boolean isHuman;

  /**
   * Creates an object for Player of this game with fields player name, it's
   * location, item picking capacity and if it is human.
   * 
   * @param name             Player Name.
   * @param location         Player Location.
   * @param startingCapacity Player item picking capacity.
   * @param human            If Player is human.
   * @throws IllegalArgumentException when any of the parameters is invalid.
   * @throws NullPointerException     when any of the parameters is NULL.
   */
  public PlayerImpl(String name, int location, int startingCapacity, boolean human)
      throws IllegalArgumentException, NullPointerException {
    validatePlayer(name, location, startingCapacity);
    this.playerName = name;
    this.playerLocation = location;
    this.capacity = startingCapacity;
    this.listOfItems = new ArrayList<>();
    this.isHuman = human;
  }

  private void validatePlayer(String name, int location, int startingCapacity)
      throws IllegalArgumentException, NullPointerException {
    CommonUtils.stringIsEmpty(name, "Player name cannot be NULL or empty.");
    if (location < 0) {
      throw new IllegalArgumentException("Location cannot be negative.");
    }
    if (startingCapacity < 1 && startingCapacity != -1) {
      throw new IllegalArgumentException("Starting capacity cannot be negative or zero.");
    }
  }

  @Override
  public String getName() {
    return playerName;
  }

  @Override
  public int getLocation() {
    return this.playerLocation;
  }

  @Override
  public void movePlayer(int location) throws IllegalArgumentException {
    if (location < 0) {
      throw new IllegalArgumentException("Location cannot be negative.");
    }
    this.playerLocation = location;
  }

  @Override
  public void addItem(Item item)
      throws IllegalArgumentException, NullPointerException, IllegalStateException {
    Objects.requireNonNull(item);
    if (this.capacity == -1 || this.capacity > this.listOfItems.size()) {
      if (this.listOfItems.contains(item)) {
        throw new IllegalArgumentException(
            item.getName() + "is already picked up by " + this.getName());
      }
      this.listOfItems.add(item);
    } else {
      throw new IllegalStateException("No capacity to add item for player " + this.getName() + ".");
    }
  }

  @Override
  public String toString() {
    return String.format(
        "Details of Player: \nPlayer Name = %s \nList of Items with Player = %s "
            + "\nItem picking capacity = %s \nPlayer Type = %s",
        playerName, listOfItems,
        this.capacity == -1 ? "Unlimited" : this.capacity - this.listOfItems.size(),
        isHuman ? "Human" : "Computer");
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerName);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof PlayerImpl)) {
      return false;
    }
    PlayerImpl other = (PlayerImpl) obj;
    return Objects.equals(playerName, other.playerName);
  }

  @Override
  public boolean isHuman() {
    return this.isHuman;
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
      throw new IllegalArgumentException(item.getName() + " is not part of " + this.getName()
          + ". Hence cannot remove this item.");
    }
  }

  @Override
  public Map<String, Integer> getItemDetails() {
    return this.listOfItems.stream()
        .collect(Collectors.toMap(r -> r.getName(), r -> r.getDamage()));
  }
}