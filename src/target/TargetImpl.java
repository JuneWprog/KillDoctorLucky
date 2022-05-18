package target;

import utils.CommonUtils;

/**
 * Class to represent the Target Character of the Game.
 *
 * @author komalshah
 */
public class TargetImpl implements Target {
  private final String targetName;
  private int healthPoint;
  private int targetLocation;

  /**
   * Creates an object for Target class with fields Target Name, their Health
   * Point and their Location.
   * 
   * @param name Name of the Target.
   * @param hp   Health point of the Target.
   * @throws IllegalArgumentException when Health Point for the Target is Negative
   *                                  or Zero.
   * @throws NullPointerException     when Target name is NULL.
   */
  public TargetImpl(String name, int hp) throws IllegalArgumentException, NullPointerException {
    validateTarget(name, hp);
    this.targetName = name;
    this.healthPoint = hp;
    this.targetLocation = 0;
  }

  private void validateTarget(String name, int hp)
      throws IllegalArgumentException, NullPointerException {
    if (hp <= 0) {
      throw new IllegalArgumentException(
          "Health point for Target Character should not be less than or equal to zero.");
    }
    CommonUtils.stringIsEmpty(name, "Target name cannot be NULL or empty.");
  }

  @Override
  public String getName() {
    return targetName;
  }

  @Override
  public int getHealthPoint() {
    return healthPoint;
  }

  @Override
  public void updateHealthPoint(int damage) throws IllegalArgumentException {
    if (damage < 1) {
      throw new IllegalArgumentException("Damage cannot be negative or zero.");
    }
    this.healthPoint -= damage;
  }

  @Override
  public int getTargetLocation() {
    return targetLocation;
  }

  @Override
  public void updateTargetLocation(int totalRooms) throws IllegalArgumentException {
    if (totalRooms <= 0) {
      throw new IllegalArgumentException("Total Rooms cannot be zero or negative.");
    }
    this.targetLocation += 1;
    this.targetLocation %= totalRooms;
  }

  @Override
  public String toString() {
    return String.format(
        "Target Details: \nTarget Name = %s \nHealth Points = %s", targetName,
        healthPoint);
  }

}
