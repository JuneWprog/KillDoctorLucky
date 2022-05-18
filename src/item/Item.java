package item;

/**
 * An item is a pick-able object in this game that can be used to kill the
 * target of the game.
 * 
 * @author komalshah
 *
 */
public interface Item {

  /**
   * Gets the name of the Item.
   * 
   * @return Name of the Item.
   */
  public String getName();

  /**
   * Gets the damage item can cause.
   * 
   * @return damage this item can cause.
   */
  public int getDamage();
}
