package item;

import java.util.Objects;
import utils.CommonUtils;

/**
 * Class to represent Items that can be used to kill the Target Character.
 *
 * @author komalshah
 */
public class ItemImpl implements Item {
  private final String itemName;
  private final int damage;

  /**
   * Creates an object for Item class with fields Item Name and the damage it can
   * cause.
   * 
   * @param name        Name of the Item.
   * @param damageValue Damage the item can cause.
   * @throws IllegalArgumentException when damage caused by an Item is negative.
   * @throws NullPointerException     when Item name is NULL.
   */
  public ItemImpl(String name, int damageValue)
      throws IllegalArgumentException, NullPointerException {
    validateItem(name, damageValue);
    this.itemName = name;
    this.damage = damageValue;
  }

  private void validateItem(String name, int damageValue)
      throws IllegalArgumentException, NullPointerException {
    if (damageValue < 0) {
      throw new IllegalArgumentException("Damage for an Item cannot be negative.");
    }
    CommonUtils.stringIsEmpty(name, "Item name cannot be NULL or empty.");
  }

  @Override
  public String getName() {
    return itemName;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public String toString() {
    return String.format("%s : %s", itemName, damage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemName);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Item)) {
      return false;
    }
    Item other = (Item) obj;
    return Objects.equals(itemName, other.getName());
  }
}
