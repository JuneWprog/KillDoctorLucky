package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import target.TargetPet;
import target.TargetPetImpl;


/**
 * JUnit test class for TargetPet.
 * 
 * @author komalshah
 *
 */
public class TargetPetImplTest {
  private TargetPet targetPet;
  
  /**
   * Setting up Target Object for testing.
   */
  @Before
  public void setUp() {
    targetPet = new TargetPetImpl("Dog");
  }
  
  /**
   * Test case to test when name is NULL.
   */
  @Test(expected = NullPointerException.class)
  public void testNameNull() {
    new TargetPetImpl(null);
  }
  
  /**
   * Test case to test when name is blank.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNameBlank() {
    new TargetPetImpl(" ");
  }
  
  /**
   * Test case to get TargetPet Name.
   */
  @Test
  public void testGetTargetPetName() {
    assertEquals("Dog", targetPet.getName());
  }
  
  /**
   * Test case to get Target pet Location.
   */
  @Test
  public void testGetTargetLocation() {
    assertEquals(0, targetPet.getTargetPetLocation());
  }
  
  /**
   * Test case to test update of location.
   */
  @Test
  public void testUpdateTargetLocation() {
    assertEquals(0, targetPet.getTargetPetLocation());
    targetPet.updateTargetPetLocation(5);
    assertEquals(5, targetPet.getTargetPetLocation());
    targetPet.updateTargetPetLocation(2);
    assertEquals(2, targetPet.getTargetPetLocation());
  }
  
  /**
   * Test case to test when room number is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateTragetLocationInvalid() {
    assertEquals(0, targetPet.getTargetPetLocation());
    targetPet.updateTargetPetLocation(-1);
  }

}
