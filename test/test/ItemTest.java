package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import item.Item;
import item.ItemImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for Item class.
 * 
 * @author komalshah
 *
 */
public class ItemTest {
  private Item item;

  /**
   * Setting up Item Object for Testing.
   */
  @Before
  public void setUp() {
    item = new ItemImpl("Pen", 10);
  }

  /**
   * Test case to test when Damage in Negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWhenDamageNegative() {
    new ItemImpl("Pen", -10);
  }

  /**
   * Test case to get Item name.
   */
  @Test
  public void testGetItemName() {
    assertEquals("Pen", item.getName());
  }

  /**
   * Test case to get Item damage.
   */
  @Test
  public void testGetDamage() {
    assertEquals(10, item.getDamage());
  }

  /**
   * Test case to test string implementation for Item.
   */
  @Test
  public void testToString() {
    assertEquals("Pen : 10", item.toString());
  }

  /**
   * Test case to test equals implementation.
   */
  @Test
  public void testEquals() {
    assertTrue(item.equals(new ItemImpl("Pen", 10)));
    assertTrue(item.equals(item));
    assertFalse(item.equals(new ItemImpl("Pencil", 10)));
  }
  
  /**
   * Test case to test hashcode implementation.
   */
  @Test
  public void testHashCode() {
    Item item1 = new ItemImpl("Pen", 10);
    assertTrue(item.hashCode() == item1.hashCode());
    assertTrue(item.hashCode() == item.hashCode());
    assertFalse(item.hashCode() == new ItemImpl("Pencil", 10).hashCode());
  }
  
  /**
   * Test case to test equals when two objects are different.
   */
  @Test
  public void testDiffObjects() {
    assertFalse(item.equals(new Object()));
  }
}
