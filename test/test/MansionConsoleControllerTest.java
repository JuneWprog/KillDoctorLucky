package test;

import static org.junit.Assert.assertEquals;

import controller.MansionConsoleController;
import controller.MansionController;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import mansion.Mansion;
import mansion.MansionBuilder;
import org.junit.Test;
import view.MansionConsoleView;

/**
 * Junit class for MansionConsoleController.
 * 
 * @author komalshah
 *
 */
public class MansionConsoleControllerTest {

  /**
   * Test case to test failing appendable.
   */
  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    Appendable gameLog = new FailingAppendable();
    Mansion m = new MockModel(gameLog);
    StringReader input = new StringReader("");
    MansionController c = new MansionConsoleController(input, m, new MansionBuilder(),
        new MansionConsoleView(gameLog));
    c.playGame();
  }

  /**
   * Test case to test when readable object is null.
   */
  @Test(expected = NullPointerException.class)
  public void testReadableNull() {
    Appendable output = System.out;
    new MansionConsoleController(null, new MockModel(output), new MansionBuilder(),
        new MansionConsoleView(output));
  }

  /**
   * Test case to test when appendable object is null.
   */
  @Test(expected = NullPointerException.class)
  public void testAppendableNull() {
    Appendable output = System.out;
    new MansionConsoleController(new InputStreamReader(System.in), new MockModel(output),
        new MansionBuilder(), new MansionConsoleView(null));
  }

  /**
   * Test case to test when model object is null.
   */
  @Test(expected = NullPointerException.class)
  public void testModelNull() {
    Appendable output = System.out;
    new MansionConsoleController(new InputStreamReader(System.in), null, new MansionBuilder(),
        new MansionConsoleView(output));
  }

  /**
   * Test case to test when builder object is null.
   */
  @Test(expected = NullPointerException.class)
  public void testBuilderNull() {
    Appendable output = System.out;
    new MansionConsoleController(new InputStreamReader(System.in), new MockModel(output), null,
        new MansionConsoleView(output));
  }

  /**
   * Test case to test when view object is null.
   */
  @Test(expected = NullPointerException.class)
  public void testViewNull() {
    Appendable output = System.out;
    new MansionConsoleController(new InputStreamReader(System.in), new MockModel(output),
        new MansionBuilder(), null);
  }

  /**
   * Test case to test when invalid string choice is made.
   */
  @Test
  public void testInvalidStringChoice() {
    Appendable out = setupMock("invalid\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "Incorrect input.\n" + "Enter Choice as Number from 1 to 10.\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test when invalid number choice is made.
   */
  @Test
  public void testInvalidNumberChoice() {
    Appendable out = setupMock("11\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "Enter Choice as Number from 1 to 10.\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test when choice to get room details is executed.
   */
  @Test
  public void testChoice1() {
    Appendable out = setupMock("1\n Green House\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "Available Rooms = [Room names]\n" + "\n" + "\n"
        + "Enter name of the room to get it's details:\n" + "Room Details for Green House\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test when choice to get room details is executed with invalid
   * room name.
   */
  @Test
  public void testChoice1Invalid() {
    Appendable out = setupMansion("1\n Green House1\n Green House\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "Available Rooms = [Green House, Hedge Maze, Piazza]\n" + "\n" + "\n"
        + "Enter name of the room to get it's details:\n"
        + "Room name does not exist in the Mansion.\n" + "Please enter correct room name.\n"
        + "Available Rooms = [Green House, Hedge Maze, Piazza]\n" + "\n" + "\n"
        + "Enter name of the room to get it's details:\n" + "Detail of Room: \n"
        + "Room Name = Green House \n"
        + "List of Items in the Room with their capacity = [Revolver : 3]\n"
        + "Players in the room = []\n" + "Neighbors= [Hedge Maze]\n"
        + "Target Doctor Lucky is present in this room.\n"
        + "Target Pet Fortune the Cat is present in this room.\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test when choice to create graphical representation.
   */
  @Test
  public void testChoice2() {
    Appendable out = setupMock("2\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Saving the World Map as myImage.png at the root level...\n"
        + "Image Saved Successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test when choice to add human player with no capacity is
   * executed.
   */
  @Test
  public void testAddHumanNoCapacity() {
    Appendable out = setupMock("3\n Komal\n Green House\n no\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Room names]\n" + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player added [name=Komal, location=Green House, isHuman=true]\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test when choice to add human player with capacity is executed.
   */
  @Test
  public void testAddHumanCapacity() {
    Appendable out = setupMock("3\n Komal\n Green House\n yes\n 10\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Room names]\n" + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n" + "\n"
        + "Enter capacity for this player:\n"
        + "Player added [name=Komal, location=Green House, capacity=10, isHuman=true]\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test when choice to add computer player with no capacity is
   * executed.
   */
  @Test
  public void testAddComputerNoCapacity() {
    Appendable out = setupMock("4\n Komal\n Green House\n no\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Room names]\n" + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player added [name=Komal, location=Green House, isHuman=false]\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test when choice to add computer player with capacity is
   * executed.
   */
  @Test
  public void testAddComputerCapacity() {
    Appendable out = setupMock("4\n Komal\n Green House\n yes\n 10\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Room names]\n" + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n" + "\n"
        + "Enter capacity for this player:\n"
        + "Player added [name=Komal, location=Green House, capacity=10, isHuman=false]\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test adding player that already exists.
   */
  @Test
  public void testAddPlayerInvalidName() {
    Appendable out = setupMansion(
        "4\n Komal\n Green House\n no\n 4\n Komal\n Green House\n no\n K\n Green House\n no\n 10");
    assertEquals(
        "\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "\n" + "Enter Player Name:\n"
            + "\n" + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
            + "Enter Player Starting Location:\n" + "\n"
            + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
            + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
            + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "\n" + "Enter Player Name:\n"
            + "\n" + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
            + "Enter Player Starting Location:\n" + "\n"
            + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
            + "This player already exists.\n" + "Please enter correct player details.\n" + "\n"
            + "Enter Player Name:\n" + "\n"
            + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
            + "Enter Player Starting Location:\n" + "\n"
            + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
            + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
            + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "System Terminated.\n",
        out.toString());
  }

  /**
   * Test case to test adding player that when room does not exist.
   */
  @Test
  public void testAddPlayerInvalidLocation() {
    Appendable out = setupMansion("3\n Komal\n GreenHouse\n no\n Komal\n Green House\n no\n 10");
    assertEquals(
        "\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "\n" + "Enter Player Name:\n"
            + "\n" + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
            + "Enter Player Starting Location:\n" + "\n"
            + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
            + "Room name does not exist in the Mansion.\n"
            + "Please enter correct player details.\n" + "\n" + "Enter Player Name:\n" + "\n"
            + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
            + "Enter Player Starting Location:\n" + "\n"
            + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
            + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
            + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "System Terminated.\n",
        out.toString());
  }

  /**
   * Test case to test adding player that has capacity in string.
   */
  @Test
  public void testAddPlayerInvalidStringCapacity() {
    Appendable out = setupMansion(
        "4\n Komal\n Green House\n yes\n invalid\n Komal\n Green House\n yes\n 10\n 10");
    assertEquals(
        "\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "\n" + "Enter Player Name:\n"
            + "\n" + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
            + "Enter Player Starting Location:\n" + "\n"
            + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
            + "\n" + "Enter capacity for this player:\n" + "Capacity should be a number.\n"
            + "Please enter correct player details.\n" + "\n" + "Enter Player Name:\n" + "\n"
            + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
            + "Enter Player Starting Location:\n" + "\n"
            + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
            + "\n" + "Enter capacity for this player:\n" + "Player Added successfully.\n" + "\n"
            + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "System Terminated.\n",
        out.toString());
  }

  /**
   * Test case to test adding player that has capacity in negative.
   */
  @Test
  public void testAddPlayerInvalidNumberCapacity() {
    Appendable out = setupMansion(
        "3\n Komal\n Green House\n yes\n -2\n Komal\n Green House\n yes\n 10\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n" + "\n"
        + "Enter capacity for this player:\n" + "Starting capacity cannot be negative or zero.\n"
        + "Please enter correct player details.\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n" + "\n"
        + "Enter capacity for this player:\n" + "Player Added successfully.\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test get player details.
   */
  @Test
  public void testGetPlayerDetails() {
    Appendable out = setupMock("6\n Komal\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "Available Players = [Players]\n" + "\n" + "\n"
        + "Enter name of the player to get it's details:\n" + "Player details for Komal\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test get player details when no players exist.
   */
  @Test
  public void testGetPlayerDetailsInvalid() {
    Appendable out = setupMansion("6\n 10");
    assertEquals(
        "\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "No player exist.\n" + "\n"
            + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "System Terminated.\n",
        out.toString());
  }

  /**
   * Test case to test get player details when player does not exist.
   */
  @Test
  public void testGetPlayerDetailsInvalid1() {
    Appendable out = setupMansion("4\n Komal\n Green House\n no\n 6\n Komal1\n Komal\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "Available Players = [Komal]\n" + "\n" + "\n"
        + "Enter name of the player to get it's details:\n" + "This player does not exist.\n"
        + "Please enter correct player name.\n" + "Available Players = [Komal]\n" + "\n" + "\n"
        + "Enter name of the player to get it's details:\n" + "Details of Player: \n"
        + "Player Name = Komal \n" + "List of Items with Player = [] \n"
        + "Item picking capacity = Unlimited \n" + "Player Type = Computer\n"
        + "Current Location = Green House\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test system exit.
   */
  @Test
  public void testChoice7() {
    Appendable out = setupMansion("10\n");
    assertEquals(
        "\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "System Terminated.\n",
        out.toString());
  }

  /**
   * Test case to test game play till end of turns.
   */
  @Test
  public void testPlayTillEnd() {
    Appendable out = setupMock("5\n look\n look\n look\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details\n" + "\n" + "Player details for Name\n" + "\n"
        + "Number of Turns left: 3\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n" + "Looked Around.\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details\n" + "\n"
        + "Player details for Name\n" + "\n" + "Number of Turns left: 2\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n" + "Looked Around.\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details\n" + "\n"
        + "Player details for Name\n" + "\n" + "Number of Turns left: 1\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n" + "Looked Around.\n" + "\n"
        + "Game Over. Target Escaped. Nobody wins!\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n" + "", out.toString());
  }

  /**
   * Test case to test to return to Main Menu.
   */
  @Test
  public void testReturnMain() {
    Appendable out = setupMock("5\n BACK\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details\n" + "\n" + "Player details for Name\n" + "\n"
        + "Number of Turns left: 3\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test when invalid string choice is made.
   */
  @Test
  public void testInvalidStringChoiceSubMenu() {
    Appendable out = setupMock("5\n invalid\n BACK\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details\n" + "\n" + "Player details for Name\n" + "\n"
        + "Number of Turns left: 3\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "Incorrect input.\n"
        + "Enter Choice from the Menu.\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details\n" + "\n"
        + "Player details for Name\n" + "\n" + "Number of Turns left: 3\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test when invalid number choice is made.
   */
  @Test
  public void testInvalidNumberChoiceSubMenu() {
    Appendable out = setupMock("3\n Komal\n Green House\n yes\n 10\n 5\n 5\n BACK\n 10");

    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Room names]\n" + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n" + "\n"
        + "Enter capacity for this player:\n"
        + "Player added [name=Komal, location=Green House, capacity=10, isHuman=true]\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details\n" + "\n" + "Player details for Name\n" + "\n"
        + "Number of Turns left: 3\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "Incorrect input.\n"
        + "Enter Choice from the Menu.\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details\n" + "\n"
        + "Player details for Name\n" + "\n" + "Number of Turns left: 3\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test action move.
   */
  @Test
  public void testActionMove() {
    Appendable out = setupMock("5\n move\n Hedge Maze\n back\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details\n" + "\n" + "Player details for Name\n" + "\n"
        + "Number of Turns left: 3\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n"
        + "List of Rooms available to Move: [Neighbors]\n" + "\n"
        + "Enter name of the room to move:\n" + "Player Moved to Hedge Maze\n"
        + "Player Moved Successfully.\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details\n" + "\n"
        + "Player details for Name\n" + "\n" + "Number of Turns left: 2\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n" + "", out.toString());
  }

  /**
   * Test case to test action pick.
   */
  @Test
  public void testActionPick() {
    Appendable out = setupMock("5\n pick\n Pen\n back\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details\n" + "\n" + "Player details for Name\n" + "\n"
        + "Number of Turns left: 3\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n"
        + "List of Items Available with Damage = {Item=5}\n" + "\n"
        + "Enter item from the room to pick:\n" + "Item picked Pen\n"
        + "Item Picked Successfully.\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details\n" + "\n"
        + "Player details for Name\n" + "\n" + "Number of Turns left: 2\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test action look.
   */
  @Test
  public void testActionLook() {
    Appendable out = setupMock("5\n LOOk\n back\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details\n" + "\n" + "Player details for Name\n" + "\n"
        + "Number of Turns left: 3\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n" + "Looked Around.\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details\n" + "\n"
        + "Player details for Name\n" + "\n" + "Number of Turns left: 2\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test when no player exists.
   */
  @Test
  public void testPlayWhenNoPlayer() {
    Appendable out = setupMansion("5\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "Please add players to play the game.\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test action move when room name is invalid.
   */
  @Test
  public void testActionMoveInvalid() {
    Appendable out = setupMansion(
        "3\n Komal\n Green House\n no\n 5\n MOVE\n Green\n Hedge Maze\n BACK\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House\n" + "\n" + "Details of Player: \n"
        + "Player Name = Komal \n" + "List of Items with Player = [] \n"
        + "Item picking capacity = Unlimited \n" + "Player Type = Human\n"
        + "Current Location = Green House\n" + "\n" + "Number of Turns left: 3\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n"
        + "List of Rooms available to Move: [Hedge Maze]\n" + "\n"
        + "Enter name of the room to move:\n"
        + "Cannot move to Green. Please ensure that room name entered is a neighbor.\n" + "\n"
        + "List of Rooms available to Move: [Hedge Maze]\n" + "\n"
        + "Enter name of the room to move:\n" + "Player Moved Successfully.\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details: \n"
        + "Target Name = Doctor Lucky \n" + "Health Points = 3\n" + "Target Location = Hedge Maze\n"
        + "\n" + "Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [] \n" + "Item picking capacity = Unlimited \n"
        + "Player Type = Human\n" + "Current Location = Hedge Maze\n" + "\n"
        + "Number of Turns left: 2\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test action pick when item name is invalid.
   */
  @Test
  public void testActionPickInvalid() {
    Appendable out = setupMansion(
        "3\n Komal\n Green House\n no\n 5\n PICK\n Green\n Revolver\n BACK\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House\n" + "\n" + "Details of Player: \n"
        + "Player Name = Komal \n" + "List of Items with Player = [] \n"
        + "Item picking capacity = Unlimited \n" + "Player Type = Human\n"
        + "Current Location = Green House\n" + "\n" + "Number of Turns left: 3\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n"
        + "List of Items Available with Damage = {Revolver=3}\n" + "\n"
        + "Enter item from the room to pick:\n"
        + "Cannot pickup item Green. Please ensure that item name entered is in the same room.\n"
        + "\n" + "List of Items Available with Damage = {Revolver=3}\n" + "\n"
        + "Enter item from the room to pick:\n" + "Item Picked Successfully.\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details: \n"
        + "Target Name = Doctor Lucky \n" + "Health Points = 3\n" + "Target Location = Hedge Maze\n"
        + "\n" + "Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [Revolver : 3] \n" + "Item picking capacity = Unlimited \n"
        + "Player Type = Human\n" + "Current Location = Green House\n" + "\n"
        + "Number of Turns left: 2\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test action pick when room has no item.
   */
  @Test
  public void testActionPickWhenNoItem() {
    Appendable out = setupMansion("3\n Komal\n Piazza\n no\n 5\n PICK\n BACK\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House\n" + "\n" + "Details of Player: \n"
        + "Player Name = Komal \n" + "List of Items with Player = [] \n"
        + "Item picking capacity = Unlimited \n" + "Player Type = Human\n"
        + "Current Location = Piazza\n" + "\n" + "Number of Turns left: 3\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n" + "\n"
        + "No Items to pick up.\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House\n" + "\n" + "Details of Player: \n"
        + "Player Name = Komal \n" + "List of Items with Player = [] \n"
        + "Item picking capacity = Unlimited \n" + "Player Type = Human\n"
        + "Current Location = Piazza\n" + "\n" + "Number of Turns left: 3\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test multiple players and change of turn.
   */
  @Test
  public void testMultiPlayer() {
    Appendable out = setupMansion("3\n Komal\n Green House\n no\n" + "3\n K\n Green House\n no\n "
        + "5\n LOOK\n LOOK\n LOOK\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House\n" + "\n" + "Details of Player: \n"
        + "Player Name = Komal \n" + "List of Items with Player = [] \n"
        + "Item picking capacity = Unlimited \n" + "Player Type = Human\n"
        + "Current Location = Green House\n" + "\n" + "Number of Turns left: 3\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n" + "Detail of Room: \n"
        + "Room Name = Green House \n"
        + "List of Items in the Room with their capacity = [Revolver : 3]\n"
        + "Players in the room = [Komal, K]\n" + "Neighbors= [Hedge Maze]\n"
        + "Target Doctor Lucky is present in this room.\n"
        + "Target Pet Fortune the Cat is present in this room.\n" + "\n"
        + "Details for Neighbor Hedge Maze\n" + "Detail of Room: \n" + "Room Name = Hedge Maze \n"
        + "List of Items in the Room with their capacity = [Civil War Cannon : 3, Cannon : 3]\n"
        + "Players in the room = []\n" + "Neighbors= [Piazza]\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details: \n"
        + "Target Name = Doctor Lucky \n" + "Health Points = 3\n" + "Target Location = Hedge Maze\n"
        + "\n" + "Details of Player: \n" + "Player Name = K \n"
        + "List of Items with Player = [] \n" + "Item picking capacity = Unlimited \n"
        + "Player Type = Human\n" + "Current Location = Green House\n" + "\n"
        + "Number of Turns left: 2\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n" + "\n"
        + "Detail of Room: \n" + "Room Name = Green House \n"
        + "List of Items in the Room with their capacity = [Revolver : 3]\n"
        + "Players in the room = [Komal, K]\n" + "Neighbors= []\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details: \n"
        + "Target Name = Doctor Lucky \n" + "Health Points = 3\n" + "Target Location = Piazza\n"
        + "\n" + "Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [] \n" + "Item picking capacity = Unlimited \n"
        + "Player Type = Human\n" + "Current Location = Green House\n" + "\n"
        + "Number of Turns left: 1\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n" + "\n"
        + "Detail of Room: \n" + "Room Name = Green House \n"
        + "List of Items in the Room with their capacity = [Revolver : 3]\n"
        + "Players in the room = [Komal, K]\n" + "Neighbors= [Hedge Maze]\n" + "\n"
        + "Details for Neighbor Hedge Maze\n" + "Detail of Room: \n" + "Room Name = Hedge Maze \n"
        + "List of Items in the Room with their capacity = [Civil War Cannon : 3, Cannon : 3]\n"
        + "Players in the room = []\n" + "Neighbors= [Green House]\n" + "\n"
        + "Game Over. Target Escaped. Nobody wins!\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test action move pet.
   */
  @Test
  public void testActionMovePet() {
    Appendable out = setupMock("5\n movepet\n Hedge Maze\n back\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details\n" + "\n" + "Player details for Name\n" + "\n"
        + "Number of Turns left: 3\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n"
        + "List of Rooms available to Move the Pet: [Room names]\n" + "\n"
        + "Enter name of the room to move the pet:\n" + "Target's pet moved to Hedge Maze\n"
        + "Target's Pet Moved Successfully.\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details\n" + "\n"
        + "Player details for Name\n" + "\n" + "Number of Turns left: 2\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test action move pet when room name is invalid.
   */
  @Test
  public void testActionMovePetRoomInvalid() {
    Appendable out = setupMansion(
        "3\n Komal\n Green House\n no\n 5\n movepet\n Hedge\n Hedge Maze\n back\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House\n" + "\n" + "Details of Player: \n"
        + "Player Name = Komal \n" + "List of Items with Player = [] \n"
        + "Item picking capacity = Unlimited \n" + "Player Type = Human\n"
        + "Current Location = Green House\n" + "\n" + "Number of Turns left: 3\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n"
        + "List of Rooms available to Move the Pet: [Green House, Hedge Maze, Piazza]\n" + "\n"
        + "Enter name of the room to move the pet:\n" + "Room name does not exist in the Mansion."
        + " Please ensure that room name entered is present in Mansion.\n" + "\n"
        + "List of Rooms available to Move the Pet: [Green House, Hedge Maze, Piazza]\n" + "\n"
        + "Enter name of the room to move the pet:\n" + "Target's Pet Moved Successfully.\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details: \n"
        + "Target Name = Doctor Lucky \n" + "Health Points = 3\n" + "Target Location = Hedge Maze\n"
        + "\n" + "Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [] \n" + "Item picking capacity = Unlimited \n"
        + "Player Type = Human\n" + "Current Location = Green House\n" + "\n"
        + "Number of Turns left: 2\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test action attempt kill.
   */
  @Test
  public void testAttemptKill() {
    Appendable out = setupMock("5\n kill\n Pen\n back\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details\n" + "\n" + "Player details for Name\n" + "\n"
        + "Number of Turns left: 3\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n"
        + "List of Items Available with Damage = {}\n" + "\n"
        + "Enter item name to be used to kill the Target:\n" + "Attempt to kill using Pen\n"
        + "Attempt to kill is Successful.\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details\n" + "\n"
        + "Player details for Name\n" + "\n" + "Number of Turns left: 2\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test action attempt kill with invalid item.
   */
  @Test
  public void testAttemptKillInvalidItem() {
    Appendable out = setupMansion(
        "3\n Komal\n Green House\n no\n 5\n kill\n Hedge\n Poking\n back\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House\n" + "\n" + "Details of Player: \n"
        + "Player Name = Komal \n" + "List of Items with Player = [] \n"
        + "Item picking capacity = Unlimited \n" + "Player Type = Human\n"
        + "Current Location = Green House\n" + "\n" + "Number of Turns left: 3\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n"
        + "List of Items Available with Damage = {Poking=1}\n" + "\n"
        + "Enter item name to be used to kill the Target:\n"
        + "Cannot use item Hedge to kill the target. "
        + "Please ensure that item name entered is with you.\n" + "\n"
        + "List of Items Available with Damage = {Poking=1}\n" + "\n"
        + "Enter item name to be used to kill the Target:\n" + "Attempt to kill is Successful.\n"
        + "\n" + "--------------------------------------------------\n" + "Target Details: \n"
        + "Target Name = Doctor Lucky \n" + "Health Points = 2\n" + "Target Location = Hedge Maze\n"
        + "\n" + "Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [] \n" + "Item picking capacity = Unlimited \n"
        + "Player Type = Human\n" + "Current Location = Green House\n" + "\n"
        + "Number of Turns left: 2\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n" + "", out.toString());
  }

  /**
   * Test case to test action attempt kill when seen by someone.
   */
  @Test
  public void testAttemptKillSeen() {
    Appendable out = setupMansion(
        "3\n Komal\n Green House\n no\n 3\n K\n Green House\n no\n 5\n kill\n Poking\n back\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House\n" + "\n" + "Details of Player: \n"
        + "Player Name = Komal \n" + "List of Items with Player = [] \n"
        + "Item picking capacity = Unlimited \n" + "Player Type = Human\n"
        + "Current Location = Green House\n" + "\n" + "Number of Turns left: 3\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n"
        + "List of Items Available with Damage = {Poking=1}\n" + "\n"
        + "Enter item name to be used to kill the Target:\n"
        + "Attempt to kill is not Successful.\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details: \n"
        + "Target Name = Doctor Lucky \n" + "Health Points = 3\n" + "Target Location = Hedge Maze\n"
        + "\n" + "Details of Player: \n" + "Player Name = K \n"
        + "List of Items with Player = [] \n" + "Item picking capacity = Unlimited \n"
        + "Player Type = Human\n" + "Current Location = Green House\n" + "\n"
        + "Number of Turns left: 2\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n" + "", out.toString());
  }

  /**
   * Test case to test can see.
   */
  @Test
  public void testCanSee() {
    Appendable out = setupMock("7\n K\n T\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "Available Players = [Players]\n" + "\n" + "Enter Player A Name:\n" + "\n"
        + "Enter Player B Name:\n" + "Player A cannot see Player B.\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test can see when a player does not exist.
   */
  @Test
  public void testCanSeeWhenPlayerDoesNotExist() {
    Appendable out = setupMansion(
        "3\n Komal\n Green House\n no\n 3\n K\n Green House\n no\n 7\n Komal\n T\n Komal\n K\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "Available Players = [Komal, K]\n" + "\n" + "Enter Player A Name:\n" + "\n"
        + "Enter Player B Name:\n" + "Players do not exist.\n"
        + "Please enter correct player name.\n" + "Available Players = [Komal, K]\n" + "\n"
        + "Enter Player A Name:\n" + "\n" + "Enter Player B Name:\n"
        + "Player A can see Player B.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test game is over when target's healthpoint is zero.
   */
  @Test
  public void testGameOver() {
    Appendable out = setupMansion(
        "3\n Komal\n Hedge Maze\n no\n 5\n pick\n Cannon\n kill\n Cannon\n 5\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Enter Player Name:\n" + "\n"
        + "List of Rooms to Add Player: [Green House, Hedge Maze, Piazza]\n"
        + "Enter Player Starting Location:\n" + "\n"
        + "Does this player has limited capacity to carry items (yes/any other key: no):\n"
        + "Player Added successfully.\n" + "\n" + "Select the operation to perform:\n"
        + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "--------------------------------------------------\n"
        + "Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House\n" + "\n" + "Details of Player: \n"
        + "Player Name = Komal \n" + "List of Items with Player = [] \n"
        + "Item picking capacity = Unlimited \n" + "Player Type = Human\n"
        + "Current Location = Hedge Maze\n" + "\n" + "Number of Turns left: 3\n"
        + "--------------------------------------------------\n" + "\n"
        + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n" + "\n"
        + "List of Items Available with Damage = {Cannon=3, Civil War Cannon=3}\n" + "\n"
        + "Enter item from the room to pick:\n" + "Item Picked Successfully.\n" + "\n"
        + "--------------------------------------------------\n" + "Target Details: \n"
        + "Target Name = Doctor Lucky \n" + "Health Points = 3\n" + "Target Location = Hedge Maze\n"
        + "\n" + "Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [Cannon : 3] \n" + "Item picking capacity = Unlimited \n"
        + "Player Type = Human\n" + "Current Location = Hedge Maze\n" + "\n"
        + "Number of Turns left: 2\n" + "--------------------------------------------------\n"
        + "\n" + "Select the operation to perform:\n" + "BACK - Return to Main Menu\n"
        + "MOVE - Move to a neighboring Room\n" + "PICK - Pick an Item from the Room\n"
        + "LOOK - Look around the Room\n" + "MOVEPET - Move the Target's Pet\n"
        + "KILL - Attempt to kill the Target\n" + "\n"
        + "List of Items Available with Damage = {Cannon=3, Poking=1}\n" + "\n"
        + "Enter item name to be used to kill the Target:\n" + "Attempt to kill is Successful.\n"
        + "\n" + "Game Over. Target Killed.\n" + "Komal killed the Target and Won the Game!\n"
        + "\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Game Over. Target Killed.\n"
        + "Komal killed the Target and Won the Game!\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n" + "", out.toString());
  }

  /**
   * Test case to test restart.
   */
  @Test
  public void testRestartY() {
    Appendable out = setupMansion("8\n y\n 10");
    assertEquals(
        "\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "\n"
            + "Are you sure you want to restart? (y/yes):\n" + "Restart Successful.\n" + "\n"
            + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "System Terminated.\n",
        out.toString());
  }

  /**
   * Test case to test restart cancel.
   */
  @Test
  public void testRestartN() {
    Appendable out = setupMansion("8\n n\n 10");
    assertEquals(
        "\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "\n"
            + "Are you sure you want to restart? (y/yes):\n" + "Restart Game Cancelled.\n" + "\n"
            + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "System Terminated.\n",
        out.toString());
  }

  /**
   * Test case to test new game.
   */
  @Test
  public void testNewGameY() {
    Appendable out = setupMansion(
        "1\n Piazza\n 9\n y\n res/netflixMansion.txt\n 2\n 2\n 1\n GOT\n 10");
    assertEquals("\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "Available Rooms = [Green House, Hedge Maze, Piazza]\n" + "\n" + "\n"
        + "Enter name of the room to get it's details:\n" + "Detail of Room: \n"
        + "Room Name = Piazza \n" + "List of Items in the Room with their capacity = []\n"
        + "Players in the room = []\n" + "Neighbors= [Hedge Maze]\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "\n" + "Are you sure you want to start a new game? (y/yes):\n" + "\n"
        + "Enter the New Configuration File Path:\n" + "Enter Max Number of Turns for the Game:\n"
        + "Enter Max Number of Players for the Game:\n" + "New Game Created.\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n"
        + "Available Rooms = [Friends, GOT, The Office, Flash, "
        + "Squid Game, Riverdale, Suits, Euphoria, Gotham, Young Sheldon, "
        + "Vikings, Money Heist, Big Bang Theory, How I Met Your Mother, Shameless, Witcher, "
        + "Justice League, Sherlock, MurderVille, 2 Broke Girls, Two and Half Men, Brooklyn99, "
        + "Stranger Things]\n"
        + "\n" + "\n" + "Enter name of the room to get it's details:\n" + "Detail of Room: \n"
        + "Room Name = GOT \n" + "List of Items in the Room with their capacity = [Dragons : 2]\n"
        + "Players in the room = []\n" + "Neighbors= [MurderVille, Shameless]\n" + "\n"
        + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
        + "2. Create Graphical Representation for the World/Mansion\n" + "3. Add a Human Player\n"
        + "4. Add a Computer Player\n" + "5. Play Game\n" + "6. Display Information of a Player\n"
        + "7. Determine if Player A can see Player B\n" + "8. Restart Game\n" + "9. New Game\n"
        + "10. Quit\n" + "System Terminated.\n", out.toString());
  }

  /**
   * Test case to test new game cancel.
   */
  @Test
  public void testNewGameN() {
    Appendable out = setupMansion("9\n n\n 10");
    assertEquals(
        "\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "\n"
            + "Are you sure you want to start a new game? (y/yes):\n" + "New Game Cancelled.\n"
            + "\n" + "Select the operation to perform:\n" + "1. Display Information of a Room\n"
            + "2. Create Graphical Representation for the World/Mansion\n"
            + "3. Add a Human Player\n" + "4. Add a Computer Player\n" + "5. Play Game\n"
            + "6. Display Information of a Player\n" + "7. Determine if Player A can see Player B\n"
            + "8. Restart Game\n" + "9. New Game\n" + "10. Quit\n" + "System Terminated.\n",
        out.toString());
  }

  private Appendable setupMock(String input) {
    Appendable out = new StringWriter();
    Mansion m = new MockModel(out);
    StringReader in = new StringReader(input);
    MansionController c = new MansionConsoleController(in, m, new MansionBuilder(),
        new MansionConsoleView(out));
    c.playGame();
    return out;
  }

  private Appendable setupMansion(String input) {
    Appendable out = new StringWriter();
    MansionBuilder b = new MansionBuilder();
    Mansion m = b.readConfigFile(new StringReader(Config.VALIDFILECONTENTS)).setNumberOfTurns(3)
        .setMaximumNumberOfPlayers(10).build();
    StringReader in = new StringReader(input);
    MansionController c = new MansionConsoleController(in, m, b, new MansionConsoleView(out));
    c.playGame();
    return out;
  }
}
