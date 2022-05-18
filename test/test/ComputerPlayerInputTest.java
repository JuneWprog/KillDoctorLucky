package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.ComputerPlayerInput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import utils.Action;


/**
 * Junit test class for ComputerPlayerInputTest.
 * 
 * @author komalshah
 *
 */
public class ComputerPlayerInputTest {
  
  /**
   * Test case to test for random integer choice.
   */
  @Test
  public void testGetRandomInteger() {
    Action action = ComputerPlayerInput.chooseAction(false, true);
    assertEquals(Action.KILL, action);
    action = ComputerPlayerInput.chooseAction(false, false);
    assertTrue(action != Action.KILL && action.isGamePlayAction());
    action = ComputerPlayerInput.chooseAction(true, false);
    assertTrue(action != Action.KILL && action.isGamePlayAction());
    action = ComputerPlayerInput.chooseAction(true, true);
    assertTrue(action != Action.KILL && action.isGamePlayAction());
  }
  
  /**
   * Test case to test for random value from list when list is null.
   */
  @Test(expected = NullPointerException.class)
  public void testGetRandomStringNull() {
    ComputerPlayerInput.getRandomString(null);
  }
  
  /**
   * Test case to test for random value from list when list is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetRandomStringEmpty() {
    ComputerPlayerInput.getRandomString(new ArrayList<>());
  }
  
  /**
   * Test case to test for random value from list.
   */
  @Test
  public void testGetRandomString() {
    List<String> strings = new ArrayList<>(List.of("Pen", "Pineapple", "Apple", "Pen"));
    String s = ComputerPlayerInput
        .getRandomString(strings);
    assertTrue(strings.contains(s));
  }
  
  /**
   * Test case to test for random value from map when map is null.
   */
  @Test(expected = NullPointerException.class)
  public void testPickItemNull() {
    ComputerPlayerInput.pickItemToKill(null);
  }
  
  /**
   * Test case to test for random value from map when map is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPickItemEmpty() {
    ComputerPlayerInput.pickItemToKill(new HashMap<>());
  }
  
  /**
   * Test case to test getting item with highest damage from the map.
   */
  @Test
  public void testPickItem() {
    Map<String, Integer> itemsMap = new HashMap<>();
    itemsMap.put("Pen", 10);
    itemsMap.put("Pencil", 5);
    itemsMap.put("Poking", 1);
    assertEquals("Pen", ComputerPlayerInput.pickItemToKill(itemsMap));
  }
}
