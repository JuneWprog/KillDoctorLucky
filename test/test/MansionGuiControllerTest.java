package test;

import static org.junit.Assert.assertEquals;

import controller.MansionGuiController;
import java.io.StringWriter;
import mansion.MansionBuilder;
import org.junit.Before;
import org.junit.Test;
import utils.Action;
import view.MansionGuiView;

/**
 * Junit class for MansionGuiController.
 * 
 * @author komalshah
 *
 */
public class MansionGuiControllerTest {
  private MansionGuiController controller;
  private Appendable out;

  /**
   * Setup object to test.
   */
  @Before
  public void setUp() {
    out = new StringWriter();
    controller = new MansionGuiController(new MockModel(out), new MansionBuilder(),
        new MockView(out));
  }

  /**
   * Test when output is null.
   */
  @Test(expected = NullPointerException.class)
  public void testAppendableNull() {
    new MansionGuiController(new MockModel(null), new MansionBuilder(), new MansionGuiView("Game"));
  }

  /**
   * Test when model is null.
   */
  @Test(expected = NullPointerException.class)
  public void testModelNull() {
    new MansionGuiController(null, new MansionBuilder(), new MansionGuiView("Game"));
  }

  /**
   * test when mansion builder is null.
   */
  @Test(expected = NullPointerException.class)
  public void testBuilderNull() {
    new MansionGuiController(new MockModel(out), null, new MansionGuiView("Game"));
  }

  /**
   * test when view is null.
   */
  @Test(expected = NullPointerException.class)
  public void testViewNull() {
    new MansionGuiController(new MockModel(out), new MansionBuilder(), null);
  }

  /**
   * Test play game.
   */
  @Test
  public void testPlayGame() {
    controller.playGame();
    assertEquals("Listener Added.\n" + "Listener Added.\n" + "Game Started. Waiting for Events.\n",
        out.toString());
  }

  /**
   * Test action pick.
   */
  @Test
  public void testPick() {
    assertEquals("Item Picked Successfully.", controller.execute(Action.PICK, "Pen"));
    assertEquals("Listener Added.\n" + "Listener Added.\n" + "Item picked Pen\n", out.toString());
  }

  /**
   * Test action pick null object.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPickNull() {
    controller.execute(Action.PICK, "null");
  }

  /**
   * Test action look around.
   */
  @Test
  public void testLookAround() {
    assertEquals("Looked Around.", controller.execute(Action.LOOK));
    assertEquals("Listener Added.\n" + "Listener Added.\n", out.toString());
  }

  /**
   * Test action move to neighbor room.
   */
  @Test
  public void testMove() {
    assertEquals("Player Moved Successfully.", controller.execute(Action.MOVE, "Library"));
    assertEquals("Listener Added.\n" + "Listener Added.\n" + "Player Moved to Library\n",
        out.toString());
  }

  /**
   * Test action attempt to kill target.
   */
  @Test
  public void testKill() {
    assertEquals("Attempt to kill is Successful.", controller.execute(Action.KILL, "knife"));
    assertEquals("Listener Added.\n" + "Listener Added.\n" + "Attempt to kill using knife\n",
        out.toString());
  }

  /**
   * Test display a room's detail.
   */
  @Test
  public void testDisplayRoom() {
    assertEquals("Room Details for Armory", controller.execute(Action.DISPLAY_ROOM, "Armory"));
    assertEquals("Listener Added.\n" + "Listener Added.\n", out.toString());
  }

  /**
   * Test display a player's detail.
   */
  @Test
  public void testDisplayPlayerDetails() {
    assertEquals("Player details for Frank", controller.execute(Action.DISPLAY_PLAYER, "Frank"));
    assertEquals("Listener Added.\n" + "Listener Added.\n", out.toString());
  }

  /**
   * Test display a target's detail.
   */
  @Test
  public void testDisplayTargetDetails() {
    assertEquals("Target Details", controller.execute(Action.DISPLAY_TARGET));
    assertEquals("Listener Added.\n" + "Listener Added.\n", out.toString());
  }

  /**
   * Test display action move pet to a room.
   */
  @Test
  public void testMovePet() {
    assertEquals("Target's Pet Moved Successfully.", controller.execute(Action.MOVEPET, "Armory"));
    assertEquals("Listener Added.\n" + "Listener Added.\n" + "Target's pet moved to Armory\n",
        out.toString());
  }

  /**
   * Test action add player to the game.
   */
  @Test
  public void testAddPlayer() {
    assertEquals("Player Added successfully.",
        controller.execute(Action.ADD_PLAYER, "Frank", "Armory", "12", "false"));
    assertEquals(
        "Listener Added.\n" + "Listener Added.\n"
            + "Player added [name=Frank, location=Armory, capacity=12, isHuman=false]\n",
        out.toString());
  }

  /**
   * Test quit game.
   */
  @Test
  public void testQuit() {
    controller.quitGame();
    assertEquals("Listener Added.\n" + "Listener Added.\n" + "System Terminated.\n",
        out.toString());
  }

  /**
   * Test Restart game.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRestartGame() {
    controller.restartGame();
  }

  /**
   * Test start a new game with new settings.
   */
  @Test
  public void testNewGame() {
    controller.newGame();
    assertEquals("Listener Added.\n" + "Listener Added.\n" + "New Game Created.\n"
        + "Player Added successfully.\n", out.toString());
  }

}
