package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import target.Target;
import target.TargetImpl;

/**
 * JUnit test class for Target.
 * 
 * @author komalshah
 *
 */
public class TargetTest {
  private Target target;
  
  /**
   * Setting up Target Object for testing.
   */
  @Before
  public void setUp() {
    target = new TargetImpl("Professor", 50);
  }
  
  /**
   * Test case to test when Health Point is Negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testHpNegative() {
    new TargetImpl("Professor", -50);
  }
  
  /**
   * Test case to test when Health Point is Zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testHpZero() {
    new TargetImpl("Professor", 0);
  }
  
  /**
   * Test case to get Target Name.
   */
  @Test
  public void testGetTargetName() {
    assertEquals("Professor", target.getName());
  }
  
  /**
   * Test case to get Health Point.
   */
  @Test
  public void testGetHealthPoint() {
    assertEquals(50, target.getHealthPoint());
  }
  
  /**
   * Test case to get Target Location.
   */
  @Test
  public void testGetTargetLocation() {
    assertEquals(0, target.getTargetLocation());
  }
  
  /**
   * Test case to test update of location.
   */
  @Test
  public void testUpdateTargetLocation() {
    assertEquals(0, target.getTargetLocation());
    target.updateTargetLocation(2);
    assertEquals(1, target.getTargetLocation());
    target.updateTargetLocation(2);
    assertEquals(0, target.getTargetLocation());
  }
  
  /**
   * Test case to test when total number of rooms are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateTragetLocationInvalid() {
    assertEquals(0, target.getTargetLocation());
    target.updateTargetLocation(0);
  }
  
  /**
   * Test update target health point valid.
   */
  @Test
  public void testUpdateHp() {
    assertEquals(50, target.getHealthPoint());
    target.updateHealthPoint(5);
    assertEquals(45, target.getHealthPoint());
  }
  
  /**
   * Test update target health point invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateHpInvalid() {
    assertEquals(50, target.getHealthPoint());
    target.updateHealthPoint(-1);
  }
  
  /**
   * Test string implementation.
   */
  @Test
  public void testToString() {
    assertEquals("Target Details: \n"
        + "Target Name = Professor \n"
        + "Health Points = 50", target.toString());
  }
}
