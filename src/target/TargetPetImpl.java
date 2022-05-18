package target;

import utils.CommonUtils;

/**
 * Class to represent the Target Character's Pet in the Game.
 *
 * @author komalshah
 */
public class TargetPetImpl implements TargetPet {

  private final String targetPetName;
  private int targetPetLocation;

  /**
   * Creates an object for TargetPet class with fields Target Pet Name and their
   * Location.
   * 
   * @param name Name of the Target Pet.
   * @throws NullPointerException when Target Pet name is NULL.
   */
  public TargetPetImpl(String name) throws NullPointerException {
    validateTargetPet(name);
    this.targetPetName = name;
    this.targetPetLocation = 0;
  }

  private void validateTargetPet(String name) throws NullPointerException {
    CommonUtils.stringIsEmpty(name, "Target's Pet name cannot be NULL or empty.");
  }

  @Override
  public String getName() {
    return this.targetPetName;
  }

  @Override
  public int getTargetPetLocation() {
    return this.targetPetLocation;
  }

  @Override
  public void updateTargetPetLocation(int roomNumber) throws IllegalArgumentException {
    if (roomNumber < 0) {
      throw new IllegalArgumentException("Target Pet cannot move to negative room number");
    }
    this.targetPetLocation = roomNumber;
  }

}
