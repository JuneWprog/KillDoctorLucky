package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import item.Item;
import item.ItemImpl;
import java.util.Objects;
import org.junit.Before;
import org.junit.Test;
import player.Player;
import player.PlayerImpl;

/**
 * JUnit test class for PlayerImpl class.
 * 
 * @author komalshah
 *
 */
public class PlayerImplTest {

  private Player humanPlayer;
  private Player computerPlayer;

  /**
   * Setting up Player Object for Testing.
   */
  @Before
  public void setUp() {
    humanPlayer = new PlayerImpl("Komal", 0, 5, true);
    computerPlayer = new PlayerImpl("K", 0, 10, false);
  }

  /**
   * Test case to test when player name is NULL.
   */
  @Test(expected = NullPointerException.class)
  public void testPlayerNameNull() {
    new PlayerImpl(null, 0, 2, false);
  }

  /**
   * Test case to test when player location is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerLocationInvalid() {
    new PlayerImpl("Komal", -1, 2, false);
  }

  /**
   * Test case to add player without capacity.
   */
  @Test
  public void testPlayerWithoutCapacity() {
    new PlayerImpl("Komal", 0, -1, false);
  }

  /**
   * Test case to test when player capacity is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerCapacityInvalid() {
    new PlayerImpl("Komal", 1, 0, false);
  }

  /**
   * Test case to get Player name.
   */
  @Test
  public void testGetName() {
    assertEquals("Komal", humanPlayer.getName());
    assertEquals("K", computerPlayer.getName());
  }

  /**
   * Test case to test player location.
   */
  @Test
  public void testGetLocation() {
    assertEquals(0, humanPlayer.getLocation());
    assertEquals(0, computerPlayer.getLocation());
  }

  /**
   * Test case to test move player to a different location.
   */
  @Test
  public void testMovePlayer() {
    humanPlayer.movePlayer(1);
    assertEquals(1, humanPlayer.getLocation());
    computerPlayer.movePlayer(2);
    assertEquals(2, computerPlayer.getLocation());
  }

  /**
   * Test case to test move player to a different location when location is
   * incorrect.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMovePlayerIncorrectLocation() {
    humanPlayer.movePlayer(-1);
  }

  /**
   * Test case to test adding item to the player.
   */
  @Test
  public void testAddItem() {
    Item item = new ItemImpl("Pen", 10);
    humanPlayer.addItem(item);
    assertEquals("Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [Pen : 10] \n" + "Item picking capacity = 4 \n"
        + "Player Type = Human", humanPlayer.toString());
    computerPlayer.addItem(item);
    assertEquals(
        "Details of Player: \n" + "Player Name = K \n" + "List of Items with Player = [Pen : 10] \n"
            + "Item picking capacity = 9 \n" + "Player Type = Computer",
        computerPlayer.toString());
  }

  /**
   * Test case to test adding item that already exists with the player.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddItemAlreadyExists() {
    Item item = new ItemImpl("Pen", 10);
    humanPlayer.addItem(item);
    humanPlayer.addItem(item);
  }

  /**
   * Test case to test adding item that already exists with the player having no
   * limit on item picking capacity.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddItemAlreadyExistsNoLimit() {
    Player p = new PlayerImpl("Komal", 0, -1, true);
    Item item = new ItemImpl("Pen", 10);
    p.addItem(item);
    p.addItem(item);
  }

  /**
   * Test case to test adding null item.
   */
  @Test(expected = NullPointerException.class)
  public void testAddItemNull() {
    humanPlayer.addItem(null);
  }

  /**
   * Test case to test adding item to the player when they have no capacity.
   */
  @Test(expected = IllegalStateException.class)
  public void testAddItemWhenNoCapacity() {
    Player player = new PlayerImpl("Komal", 0, 1, false);
    Item item = new ItemImpl("Pen", 10);
    Item item1 = new ItemImpl("Pencil", 10);
    player.addItem(item);
    player.addItem(item1);
  }

  /**
   * Test case to test String implementation for Player.
   */
  @Test
  public void testToString() {
    assertEquals(
        "Details of Player: \n" + "Player Name = Komal \n" + "List of Items with Player = [] \n"
            + "Item picking capacity = 5 \n" + "Player Type = Human",
        humanPlayer.toString());
    assertEquals(
        "Details of Player: \n" + "Player Name = K \n" + "List of Items with Player = [] \n"
            + "Item picking capacity = 10 \n" + "Player Type = Computer",
        computerPlayer.toString());
  }

  /**
   * Test case to test equals implementation.
   */
  @Test
  public void testEqualsObject() {
    assertFalse(humanPlayer.equals(computerPlayer));
    assertTrue(humanPlayer.equals(humanPlayer));
    assertTrue(humanPlayer.equals(new PlayerImpl("Komal", 2, 2, true)));
  }

  /**
   * Test case to test hashcode implementation.
   */
  @Test
  public void testHashCode() {
    Player player1 = new PlayerImpl("Komal", 0, 5, false);
    assertTrue(humanPlayer.hashCode() == player1.hashCode());
    assertFalse(humanPlayer.hashCode() == computerPlayer.hashCode());
    assertTrue(humanPlayer.hashCode() == humanPlayer.hashCode());
  }

  /**
   * Test case to test if the player is human.
   */
  @Test
  public void testIsHuman() {
    assertTrue(humanPlayer.isHuman());
    assertFalse(computerPlayer.isHuman());
  }

  /**
   * Test case to get item.
   */
  @Test
  public void testGetItem() {
    Item item = new ItemImpl("Pen", 10);
    humanPlayer.addItem(item);
    assertEquals("Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [Pen : 10] \n" + "Item picking capacity = 4 \n"
        + "Player Type = Human", humanPlayer.toString());
    Item itemGot = humanPlayer.getItem("Pen");
    assertTrue(item.equals(itemGot));

    computerPlayer.addItem(item);
    assertEquals(
        "Details of Player: \n" + "Player Name = K \n" + "List of Items with Player = [Pen : 10] \n"
            + "Item picking capacity = 9 \n" + "Player Type = Computer",
        computerPlayer.toString());
    itemGot = humanPlayer.getItem("Pen");
    assertTrue(item.equals(itemGot));
  }

  /**
   * Test case to get item when item does not exist.
   */
  @Test
  public void testGetItemInvalid() {
    Item item = new ItemImpl("Pen", 10);
    humanPlayer.addItem(item);
    assertEquals("Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [Pen : 10] \n" + "Item picking capacity = 4 \n"
        + "Player Type = Human", humanPlayer.toString());
    Item itemGot = humanPlayer.getItem("Pencil");
    assertTrue(Objects.isNull(itemGot));

    computerPlayer.addItem(item);
    assertEquals(
        "Details of Player: \n" + "Player Name = K \n" + "List of Items with Player = [Pen : 10] \n"
            + "Item picking capacity = 9 \n" + "Player Type = Computer",
        computerPlayer.toString());
    itemGot = humanPlayer.getItem("Pencil");
    assertTrue(Objects.isNull(itemGot));
  }

  /**
   * Test case to remove item.
   */
  @Test
  public void testRemoveItem() {
    Item item = new ItemImpl("Pen", 10);
    humanPlayer.addItem(item);
    assertEquals("Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [Pen : 10] \n" + "Item picking capacity = 4 \n"
        + "Player Type = Human", humanPlayer.toString());
    humanPlayer.removeItem(item);
    assertEquals(
        "Details of Player: \n" + "Player Name = Komal \n" + "List of Items with Player = [] \n"
            + "Item picking capacity = 5 \n" + "Player Type = Human",
        humanPlayer.toString());

    computerPlayer.addItem(item);
    assertEquals(
        "Details of Player: \n" + "Player Name = K \n" + "List of Items with Player = [Pen : 10] \n"
            + "Item picking capacity = 9 \n" + "Player Type = Computer",
        computerPlayer.toString());
    computerPlayer.removeItem(item);
    assertEquals(
        "Details of Player: \n" + "Player Name = K \n" + "List of Items with Player = [] \n"
            + "Item picking capacity = 10 \n" + "Player Type = Computer",
        computerPlayer.toString());
  }

  /**
   * Test case to remove item when item does not exist for human player.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveItemHumanInvalid() {
    Item item = new ItemImpl("Pen", 10);
    humanPlayer.addItem(item);
    assertEquals("Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [Pen : 10] \n" + "Item picking capacity = 4 \n"
        + "Player Type = Human", humanPlayer.toString());
    humanPlayer.removeItem(item);
    assertEquals(
        "Details of Player: \n" + "Player Name = Komal \n" + "List of Items with Player = [] \n"
            + "Item picking capacity = 5 \n" + "Player Type = Human",
        humanPlayer.toString());
    humanPlayer.removeItem(item);
  }

  /**
   * Test case to remove item when item does not exist for computer player.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveItemComputerInvalid() {
    Item item = new ItemImpl("Pen", 10);
    computerPlayer.addItem(item);
    assertEquals(
        "Details of Player: \n" + "Player Name = K \n" + "List of Items with Player = [Pen : 10] \n"
            + "Item picking capacity = 9 \n" + "Player Type = Computer",
        computerPlayer.toString());
    computerPlayer.removeItem(item);
    assertEquals(
        "Details of Player: \n" + "Player Name = K \n" + "List of Items with Player = [] \n"
            + "Item picking capacity = 10 \n" + "Player Type = Computer",
        computerPlayer.toString());
    computerPlayer.removeItem(item);
  }

  /**
   * Test case to get details of items player holds.
   */
  @Test
  public void testGetItemDetails() {
    Item item = new ItemImpl("Pen", 10);
    humanPlayer.addItem(item);
    computerPlayer.addItem(item);
    assertEquals("{Pen=10}", humanPlayer.getItemDetails().toString());
    assertEquals("{Pen=10}", computerPlayer.getItemDetails().toString());
  }

  /**
   * Test case to get details of player with no item picking capacity limit.
   */
  @Test
  public void testToStringWithNoLimit() {
    Player p = new PlayerImpl("Komal", 0, -1, true);
    assertEquals(
        "Details of Player: \n" + "Player Name = Komal \n" + "List of Items with Player = [] \n"
            + "Item picking capacity = Unlimited \n" + "Player Type = Human",
        p.toString());
  }
  
  /**
   * Test case to test equals when two objects are different.
   */
  @Test
  public void testDiffObjects() {
    assertFalse(humanPlayer.equals(new Object()));
  }
}
