package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import item.Item;
import item.ItemImpl;
import java.util.Objects;
import org.junit.Before;
import org.junit.Test;
import room.Room;
import room.RoomImpl;

/**
 * JUnit tests for Room class.
 * 
 * @author komalshah
 *
 */
public class RoomTest {
  private Room room;

  /**
   * Setting up Room object for Testing.
   */
  @Before
  public void setUp() {
    room = new RoomImpl("Kitchen", 0, 0, 10, 20);
  }

  /**
   * Test case to test when top left x is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLeftXnegative() {
    new RoomImpl("Kitchen", -10, 0, 10, 20);
  }

  /**
   * Test case to test when top left y is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLeftYnegative() {
    new RoomImpl("Kitchen", 0, -10, 10, 20);
  }

  /**
   * Test case to test when bottom right x is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRightXnegative() {
    new RoomImpl("Kitchen", 0, 0, -10, 20);
  }

  /**
   * Test case to test when bottom right y is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRightYnegative() {
    new RoomImpl("Kitchen", 0, 0, 10, -20);
  }

  /**
   * Test case to test negative width.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth() {
    new RoomImpl("Kitchen", 20, 0, 10, 20);
  }

  /**
   * Test case to test negative length.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeLength() {
    new RoomImpl("Kitchen", 0, 40, 10, 20);
  }

  /**
   * Test case to get room name.
   */
  @Test
  public void testGetRoomName() {
    assertEquals("Kitchen", room.getName());
  }

  /**
   * Test case to get list of items in the room.
   */
  @Test
  public void testGetItems() {
    Item item = new ItemImpl("Pen", 10);
    room.addItemToRoom(item);
    assertEquals(item, room.getItem("Pen"));
    assertEquals(null, room.getItem("Pencil"));
  }

  /**
   * Test case to get room location.
   */
  @Test
  public void testGetLocation() {
    assertArrayEquals(new int[] { 0, 0, 10, 20 }, room.getLocation());
  }

  /**
   * Test case to add item in the room.
   */
  @Test
  public void testAddItemToRoom() {
    Item item = new ItemImpl("Pen", 10);
    room.addItemToRoom(item);
    assertTrue(Objects.nonNull(room.getItem("Pen")));
  }

  /**
   * Test case to test string implementation.
   */
  @Test
  public void testToString() {
    assertEquals("Detail of Room: \n"
        + "Room Name = Kitchen \n"
        + "List of Items in the Room with their capacity = []",
        room.toString());
  }
  
  /**
   * Test case to get list of item names in the room.
   */
  @Test
  public void testListOfItemNames() {
    room.addItemToRoom(new ItemImpl("Pen", 10));
    room.addItemToRoom(new ItemImpl("Pencil", 10));
    assertEquals("{Pen=10, Pencil=10}", room.getItemDetails().toString());
  }
  
  /**
   * Test case to test exception when adding already existing item in the room.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddItemAlreadyExists() {
    room.addItemToRoom(new ItemImpl("Pen", 10));
    room.addItemToRoom(new ItemImpl("Pen", 10));
  }
  
  /**
   * Test case to test exception when adding NULL item in the room.
   */
  @Test(expected = NullPointerException.class)
  public void testAddItemNull() {
    room.addItemToRoom(null);
  }
  
  /**
   * Test case to test removing item from the room.
   */
  @Test
  public void testRemoveItem() {
    Item item = new ItemImpl("Pen", 10);
    room.addItemToRoom(item);
    room.removeItem(item);
    assertEquals("{}", room.getItemDetails().toString());
  }
  
  /**
   * Test case to test exception while removing the non existent item in the room.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveItemDoesnotExist() {
    room.removeItem(new ItemImpl("Pen", 10));
  }
  
  /**
   * Test case to test exception when removing NULL item in the room.
   */
  @Test(expected = NullPointerException.class)
  public void testRemoveItemNull() {
    room.removeItem(null);
  }
  
  /**
   * Test case to test equals implementation.
   */
  @Test
  public void testEquals() {
    Room room1 = new RoomImpl("Kitchen", 0, 0, 10, 20);
    assertTrue(room.equals(room1));
    assertTrue(room.equals(room));
    assertFalse(room.equals(new RoomImpl("Dining", 20, 10, 40, 50)));
  }
  
  /**
   * Test case to test hashcode implementation.
   */
  @Test
  public void testHashCode() {
    Room room1 = new RoomImpl("Kitchen", 0, 0, 10, 20);
    assertTrue(room.hashCode() == room1.hashCode());
    assertTrue(room.hashCode() == room.hashCode());
    assertFalse(room.hashCode() == new RoomImpl("Dining", 20, 10, 40, 50).hashCode());
  }
  
  /**
   * Test case to test equals when two objects are different.
   */
  @Test
  public void testDiffObjects() {
    assertFalse(room.equals(new Object()));
  }
}
