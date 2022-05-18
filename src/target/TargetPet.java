package target;

/**
 * TargetPet represents target character's pet. The pet enters the game in the
 * same space as the target character.
 * 
 * @author komalshah
 *
 */
public interface TargetPet {
  
  /**
   * Gets the name of the Target Pet.
   * 
   * @return the name of the Target Pet.
   */
  public String getName();
  
  /**
   * Gets the location (or room number) of the Target Pet.
   * 
   * @return location of the Target Pet.
   */
  public int getTargetPetLocation();

  /**
   * Updates the Target Pet Location.
   * 
   * @param roomNumber The room number to move the pet in.
   * @throws IllegalArgumentException when room number is negative.
   */
  public void updateTargetPetLocation(int roomNumber) throws IllegalArgumentException;

}
