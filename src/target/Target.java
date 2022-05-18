package target;

/**
 * Target represents the Target Character to be killed in the game.
 * 
 * @author komalshah
 *
 */
public interface Target {
  
  /**
   * Gets the name of the Target.
   * 
   * @return the name of the Target.
   */
  public String getName();

  /**
   * Gets the health point of the Target.
   * 
   * @return health point of the Target.
   */
  public int getHealthPoint();

  /**
   * Gets the location (or room number) of the Target.
   * 
   * @return location of the Target.
   */
  public int getTargetLocation();
  
  /**
   * Updates the Target Location.
   * 
   * @param totalRooms The total number of rooms.
   * @throws IllegalArgumentException when total rooms are negative or zero.
   */
  public void updateTargetLocation(int totalRooms) throws IllegalArgumentException;
  
  /**
   * Updates the health point of Target.
   * 
   * @param damage damage to the Target that reduces health point.
   * @throws IllegalArgumentException when damage is negative or zero.
   */
  void updateHealthPoint(int damage) throws IllegalArgumentException;

}
