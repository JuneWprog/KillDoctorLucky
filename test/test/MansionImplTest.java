package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import mansion.Mansion;
import mansion.MansionBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test class for testing Mansion Implementation.
 * 
 * @author komalshah
 *
 */
public class MansionImplTest {
  private Mansion mansion;

  /**
   * Setting up Mansion object for testing.
   */
  @Before
  public void setUp() {
    mansion = new MansionBuilder().readConfigFile(new StringReader(Config.VALIDFILECONTENTS))
        .setNumberOfTurns(3).setMaximumNumberOfPlayers(4).build();
  }

  /**
   * Test case to test when size of row is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRowsNegative() {
    new MansionBuilder().readConfigFile(new StringReader(Config.INVALIDROWSIZE));
  }

  /**
   * Test case to test when size of column is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testColumnsNegative() {
    new MansionBuilder().readConfigFile(new StringReader(Config.INVALIDCOLUMNSIZE));
  }

  /**
   * Test case to test when total number of rooms is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTotalRoomsNegative() {
    new MansionBuilder().readConfigFile(new StringReader(Config.INVALIDTOTALROOMS));
  }

  /**
   * Test case to test when total number of items is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTotalItemsNegative() {
    new MansionBuilder().readConfigFile(new StringReader(Config.INVALIDTOTALITEMS));
  }

  /**
   * Test case to test when number of turns is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNumberOfTurnsNegative() {
    new MansionBuilder().setNumberOfTurns(-1);
  }

  /**
   * Test case to test when max number of players is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMaxNumberOfPlayers() {
    new MansionBuilder().setMaximumNumberOfPlayers(-1);
  }

  /**
   * Test case to create the Image.
   * 
   * @throws IOException when file operation fail.
   */
  @Test
  public void testCreateGraphicalRepresentation() throws IOException {
    BufferedImage bufferedImage = mansion.createGraphicalRepresentation();
    File file = new File("testImage1.png");
    ImageIO.write(bufferedImage, "png", file);
  }

  /**
   * Test case to get neighbors when room name is valid.
   */
  @Test
  public void testGetNeighborForRoom() {
    List<String> neighbors = new ArrayList<>();
    neighbors.add("Hedge Maze");
    assertEquals(neighbors, mansion.getNeighborForRoom("Green House"));
  }

  /**
   * Test case to get neighbors when room name is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetNeighborForRoomInvalid() {
    mansion.getNeighborForRoom("Green1 House");
  }

  /**
   * Test case to get details of room when room name is valid.
   */
  @Test
  public void testGetDetailsOfRoom() {
    assertEquals(
        "Detail of Room: \n" + "Room Name = Green House \n"
            + "List of Items in the Room with their capacity = [Revolver : 3]\n"
            + "Players in the room = []\n" + "Neighbors= [Hedge Maze]\n"
            + "Target Doctor Lucky is present in this room.\n"
            + "Target Pet Fortune the Cat is present in this room.",
        mansion.getDetailsOfRoom("Green House"));
  }

  /**
   * Test case to get details of room when room name is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetDetailsOfRoomInvalid() {
    mansion.getDetailsOfRoom("Green1 House");
  }

  /**
   * Test case to get Mansion's name.
   */
  @Test
  public void testGetMansionName() {
    assertEquals("Doctor Lucky's Mansion", mansion.getMansionName());
  }

  /**
   * Test case to test adding room when room already exists.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRoomAlreadyExists() {
    new MansionBuilder().readConfigFile(new StringReader(Config.ROOMALREADYEXISTS));
  }

  /**
   * Test case to test adding item when item already exists.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testItemAlreadyExists() {
    new MansionBuilder().readConfigFile(new StringReader(Config.ITEMALREADYEXISTS));
  }

  /**
   * Test case to test adding item to the room that does not exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddItemWhenRoomUnavailable() {
    new MansionBuilder().readConfigFile(new StringReader(Config.ROOMNUMBERINVALIDFORITEM));
  }

  /**
   * Test case to test invalid room size.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRoomSize() {
    new MansionBuilder().readConfigFile(new StringReader(Config.INVALIDROOMSIZE));
  }

  /**
   * Test case to test adding player with capacity limit.
   */
  @Test
  public void testAddPlayerWithCapacity() {
    mansion.addPlayer("Komal", "Green House", 3, true);
    assertEquals("Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [] \n" + "Item picking capacity = 3 \n"
        + "Player Type = Human\n" + "Current Location = Green House",
        mansion.getDetailsOfPlayer("Komal"));
  }

  /**
   * Test case to test adding player without capacity limit.
   */
  @Test
  public void testAddPlayerWithoutCapacity() {
    mansion.addPlayer("Komal", "Green House", true);
    assertEquals("Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [] \n" + "Item picking capacity = Unlimited \n"
        + "Player Type = Human\n" + "Current Location = Green House",
        mansion.getDetailsOfPlayer("Komal"));
  }

  /**
   * Test case to test adding existing player.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddExistingPlayer() {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.addPlayer("Komal", "Green House", true);
  }

  /**
   * Test case to test adding player to an invalid room.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddPlayerInvalidLocation() {
    mansion.addPlayer("Komal", "Green House2", true);
  }

  /**
   * Test case to test action look when there is no player in the game.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void testActionLookWhenNoPlayerInGame() throws IllegalAccessException {
    mansion.actionLook();
  }

  /**
   * Test case to test action look.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test
  public void testActionLook() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    assertEquals("Detail of Room: \n" + "Room Name = Green House \n"
        + "List of Items in the Room with their capacity = [Revolver : 3]\n"
        + "Players in the room = [Komal]\n" + "Neighbors= [Hedge Maze]\n"
        + "Target Doctor Lucky is present in this room.\n"
        + "Target Pet Fortune the Cat is present in this room.\n" + "\n"
        + "Details for Neighbor Hedge Maze\n" + "Detail of Room: \n" + "Room Name = Hedge Maze \n"
        + "List of Items in the Room with their capacity = [Civil War Cannon : 3, Cannon : 3]\n"
        + "Players in the room = []\n" + "Neighbors= [Piazza]", mansion.actionLook());
  }

  /**
   * Test case to test action pick when there is no player in the game.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void testActionPickWhenNoPlayerInGame() throws IllegalAccessException {
    mansion.actionPick("Revolver");
  }

  /**
   * Test case to test action pick.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test
  public void testActionPick() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.actionPick("Revolver");
    assertEquals("Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [Revolver : 3] \n" + "Item picking capacity = Unlimited \n"
        + "Player Type = Human\n" + "Current Location = Green House",
        mansion.getDetailsOfPlayer("Komal"));
    assertEquals("Detail of Room: \n" + "Room Name = Green House \n"
        + "List of Items in the Room with their capacity = []\n" + "Players in the room = [Komal]\n"
        + "Neighbors= []", mansion.actionLook());
  }

  /**
   * Test case to test action pick when item is not in the room.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testActionPickInvalidItem() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.actionPick("Pen");
  }

  /**
   * Test case to test action move when there is no player in the game.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void testActionMoveWhenNoPlayerInGame() throws IllegalAccessException {
    mansion.actionMove("Green House");
  }

  /**
   * Test case to test action move.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test
  public void testActionMove() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.actionMove("Hedge Maze");
    assertEquals(
        "Detail of Room: \n" + "Room Name = Green House \n"
            + "List of Items in the Room with their capacity = [Revolver : 3]\n"
            + "Players in the room = []\n" + "Neighbors= []",
        mansion.getDetailsOfRoom("Green House"));
    assertEquals(
        "Detail of Room: \n" + "Room Name = Hedge Maze \n"
            + "List of Items in the Room with their capacity = [Civil War Cannon : 3, Cannon : 3]\n"
            + "Players in the room = [Komal]\n" + "Neighbors= [Green House, Piazza]\n"
            + "Target Doctor Lucky is present in this room.\n"
            + "Target Pet Fortune the Cat is present in this room.",
        mansion.getDetailsOfRoom("Hedge Maze"));
  }

  /**
   * Test case to test action move when room is invalid.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testActionMoveWhenRoomInvalid() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.actionMove("Dining Hall");
  }

  /**
   * Test case to test if player details displayed correctly.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test
  public void testPlayerDetails() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.actionPick("Revolver");
    assertEquals("Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [Revolver : 3] \n" + "Item picking capacity = Unlimited \n"
        + "Player Type = Human\n" + "Current Location = Green House",
        mansion.getDetailsOfPlayer("Komal"));
  }

  /**
   * Test case to test display player details for invalid player.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayerDetails() {
    mansion.getDetailsOfPlayer("Komal");
  }

  /**
   * Test case to test current player.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test
  public void testGetCurrentPlayer() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    assertEquals("Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [] \n" + "Item picking capacity = Unlimited \n"
        + "Player Type = Human\n" + "Current Location = Green House",
        mansion.getCurrentPlayerDetails());
  }

  /**
   * Test case to test get the current player details there is no player in the
   * game.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void testCurrentPlayerWhenNoPlayerInGame() throws IllegalAccessException {
    mansion.getCurrentPlayerDetails();
  }

  /**
   * Test case to test get if current player is human when there is no player in
   * the game.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void testIsHumanWhenNoPlayerInGame() throws IllegalAccessException {
    mansion.isHuman();
  }

  /**
   * Test case to test get if current player is human.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test
  public void testIsHuman() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    assertTrue(mansion.isHuman());
    mansion.addPlayer("K", "Green House", false);
    mansion.actionLook();
    assertFalse(mansion.isHuman());
  }

  /**
   * Test case to test get items to pick when there is no player in the game.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void testItemsToPickWhenNoPlayerInGame() throws IllegalAccessException {
    mansion.getItemsToPick();
  }

  /**
   * Test case to test get items to pick.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test
  public void testGetItemsToPick() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    assertEquals("{Revolver=3}", mansion.getItemsToPick().toString());
  }

  /**
   * Test case to test get neighbors to move when there is no player in the game.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void testNeighborsToMoveWhenNoPlayerInGame() throws IllegalAccessException {
    mansion.getNeighboursToMove();
  }

  /**
   * Test case to test get neighbors to move.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test
  public void testGetNeighboursToMove() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    assertEquals("[Hedge Maze]", mansion.getNeighboursToMove().toString());
  }

  /**
   * Test case to test getting number of players.
   */
  @Test
  public void testGetNumberOfPlayers() {
    mansion.addPlayer("Komal", "Green House", true);
    assertEquals(1, mansion.getNumberOfPlayers());
  }

  /**
   * Test case to test action move pet when there is no player in the game.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void testActionMovePetWhenNoPlayerInGame() throws IllegalAccessException {
    mansion.actionMovePet("Green House");
  }

  /**
   * Test case to test action move pet.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test
  public void testActionMovePet() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    assertEquals(
        "Detail of Room: \n" + "Room Name = Green House \n"
            + "List of Items in the Room with their capacity = [Revolver : 3]\n"
            + "Players in the room = [Komal]\n" + "Neighbors= [Hedge Maze]\n"
            + "Target Doctor Lucky is present in this room.\n"
            + "Target Pet Fortune the Cat is present in this room.",
        mansion.getDetailsOfRoom("Green House"));
    assertEquals(
        "Detail of Room: \n" + "Room Name = Hedge Maze \n"
            + "List of Items in the Room with their capacity = [Civil War Cannon : 3, Cannon : 3]\n"
            + "Players in the room = []\n" + "Neighbors= [Piazza]",
        mansion.getDetailsOfRoom("Hedge Maze"));

    mansion.actionMovePet("Hedge Maze");

    assertEquals(
        "Detail of Room: \n" + "Room Name = Green House \n"
            + "List of Items in the Room with their capacity = [Revolver : 3]\n"
            + "Players in the room = [Komal]\n" + "Neighbors= []",
        mansion.getDetailsOfRoom("Green House"));
    assertEquals(
        "Detail of Room: \n" + "Room Name = Hedge Maze \n"
            + "List of Items in the Room with their capacity = [Civil War Cannon : 3, Cannon : 3]\n"
            + "Players in the room = []\n" + "Neighbors= [Green House, Piazza]\n"
            + "Target Doctor Lucky is present in this room.\n"
            + "Target Pet Fortune the Cat is present in this room.",
        mansion.getDetailsOfRoom("Hedge Maze"));
  }

  /**
   * Test case to test action move pet when room is invalid.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testActionMovePetWhenRoomInvalid() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.actionMove("Dining Hall");
  }

  /**
   * Test case to test can player see other player when both the players are same.
   */
  @Test
  public void testCanSeeSamePlayers() {
    mansion.addPlayer("Komal", "Green House", true);
    assertFalse(mansion.canSee("Komal", "Komal"));
  }

  /**
   * Test case to test can player see other player when both are in same room.
   */
  @Test
  public void testCanSeePlayersInSameRoom() {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.addPlayer("K", "Green House", true);
    assertTrue(mansion.canSee("Komal", "K"));
  }

  /**
   * Test case to test can player see other player when they are in neighboring
   * room.
   */
  @Test
  public void testCanSeePlayersInNeighbor() {
    mansion.addPlayer("Komal", "Piazza", true);
    mansion.addPlayer("K", "Hedge Maze", true);
    assertTrue(mansion.canSee("Komal", "K"));
    assertTrue(mansion.canSee("K", "Komal"));
  }

  /**
   * Test case to test can player see other player when they are in neighboring
   * room and they have pet in it.
   */
  @Test
  public void testCanSeePlayersInNeighborPet() {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.addPlayer("K", "Hedge Maze", true);
    assertTrue(mansion.canSee("Komal", "K"));
    assertFalse(mansion.canSee("K", "Komal"));
  }

  /**
   * Test case to test can player see other player when they are in not in the
   * same or neighboring room.
   */
  @Test
  public void testCanSeePlayersInDiffRoom() {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.addPlayer("K", "Piazza", true);
    assertFalse(mansion.canSee("Komal", "K"));
  }

  /**
   * Test case to test can player a see player b when player b is not part of
   * game.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCanSeePlayersNotInGame() {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.canSee("Komal", "K");
  }

  /**
   * Test case to test can player a see player b when player a is not part of
   * game.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCanSeePlayersAnotInGame() {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.canSee("K", "Komal");
  }

  /**
   * Test case to test if current player is seen when no players in the game.
   */
  @Test
  public void testIsCurrentPlayerSeenWhenNoPlayers() {
    assertFalse(mansion.isCurrentPlayerSeen());
  }

  /**
   * Test case to test if current player is seen.
   */
  @Test
  public void testIsCurrentPlayerSeen() {
    mansion.addPlayer("Komal", "Green House", true);
    assertFalse(mansion.isCurrentPlayerSeen());
  }

  /**
   * Test case to test if current player is seen when pet and player are in same
   * room.
   */
  @Test
  public void testIsCurrentPlayerSeenPet() {
    mansion.addPlayer("Komal", "Green House", true);
    assertEquals(
        "Detail of Room: \n" + "Room Name = Green House \n"
            + "List of Items in the Room with their capacity = [Revolver : 3]\n"
            + "Players in the room = [Komal]\n" + "Neighbors= [Hedge Maze]\n"
            + "Target Doctor Lucky is present in this room.\n"
            + "Target Pet Fortune the Cat is present in this room.",
        mansion.getDetailsOfRoom("Green House"));
    mansion.addPlayer("K", "Hedge Maze", true);
    assertFalse(mansion.isCurrentPlayerSeen());
  }

  /**
   * Test case to test if current player is seen when pet and player are not in
   * same room.
   */
  @Test
  public void testIsCurrentPlayerSeenTrue() {
    mansion.addPlayer("Komal", "Piazza", true);
    mansion.addPlayer("K", "Hedge Maze", true);
    assertEquals(
        "Detail of Room: \n" + "Room Name = Green House \n"
            + "List of Items in the Room with their capacity = [Revolver : 3]\n"
            + "Players in the room = []\n" + "Neighbors= [Hedge Maze]\n"
            + "Target Doctor Lucky is present in this room.\n"
            + "Target Pet Fortune the Cat is present in this room.",
        mansion.getDetailsOfRoom("Green House"));
    assertTrue(mansion.isCurrentPlayerSeen());
  }

  /**
   * Test case to test if current player is seen.
   */
  @Test
  public void testIsCurrentPlayerSeenTrue1() {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.addPlayer("K", "Green House", true);
    assertTrue(mansion.isCurrentPlayerSeen());
  }

  /**
   * Test case to test get list of rooms in the Mansion.
   */
  @Test
  public void testListOfRooms() {
    assertEquals("[Green House, Hedge Maze, Piazza]", mansion.getRoomNames().toString());
  }

  /**
   * Test case to test get list of players in the Mansion.
   */
  @Test
  public void testListOfPlayers() {
    assertEquals("[]", mansion.getPlayerNames().toString());
    mansion.addPlayer("Komal", "Green House", true);
    assertEquals("[Komal]", mansion.getPlayerNames().toString());
  }

  /**
   * Test case to test get list of items that player can use to kill when no
   * players in the game.
   * 
   * @throws IllegalAccessException when no players in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void testListOfItemsToKillWhenNoPlayers() throws IllegalAccessException {
    mansion.getItemsToKill().toString();
  }

  /**
   * Test case to test get list of items that player can use to kill when player
   * has no items.
   * 
   * @throws IllegalAccessException when no players in the game.
   */
  @Test
  public void testListOfItemsToKillWhenNoItems() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    assertEquals("{Poking=1}", mansion.getItemsToKill().toString());
  }

  /**
   * Test case to test get list of items that player can use to kill.
   * 
   * @throws IllegalAccessException when no players in the game.
   */
  @Test
  public void testListOfItemsToKill() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.actionPick("Revolver");
    assertEquals("{Revolver=3, Poking=1}", mansion.getItemsToKill().toString());
  }

  /**
   * Test case to test if target is present in the same room as current player
   * when no players in the game.
   * 
   * @throws IllegalAccessException when no players in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void testIsTargetPresentWhenNoPlayers() throws IllegalAccessException {
    mansion.isTargetPresent();
  }

  /**
   * Test case to test if target is present in the same room as current player.
   * 
   * @throws IllegalAccessException when no players in the game.
   */
  @Test
  public void testIsTargetPresent() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    assertTrue(mansion.isTargetPresent());
    mansion.actionLook();
    assertFalse(mansion.isTargetPresent());
  }

  /**
   * Test case to test Target Details.
   */
  @Test
  public void testTargetDetails() {
    assertEquals("Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House", mansion.getTargetDetails());
  }

  /**
   * Test case to test game isGameOver and winner.
   * 
   * @throws IllegalAccessException when no player in the game.
   */
  @Test
  public void testGameOver() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Hedge Maze", true);
    assertFalse(mansion.isGameOver());
    assertEquals(null, mansion.getWinner());
    mansion.actionPick("Civil War Cannon");
    mansion.actionAttemptKill("Civil War Cannon");
    assertTrue(mansion.isGameOver());
    assertEquals("Komal", mansion.getWinner());
  }

  /**
   * Test case to test actionMove once the Target is killed.
   * 
   * @throws IllegalAccessException when no player in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void actionMoveGameOver() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Hedge Maze", true);
    assertFalse(mansion.isGameOver());
    assertEquals(null, mansion.getWinner());
    mansion.actionPick("Civil War Cannon");
    mansion.actionAttemptKill("Civil War Cannon");
    assertTrue(mansion.isGameOver());
    assertEquals("Komal", mansion.getWinner());
    mansion.actionMove("Green House");
  }

  /**
   * Test case to test actionPick once the Target is killed.
   * 
   * @throws IllegalAccessException when no player in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void actionPickGameOver() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Hedge Maze", true);
    assertFalse(mansion.isGameOver());
    assertEquals(null, mansion.getWinner());
    mansion.actionPick("Civil War Cannon");
    mansion.actionAttemptKill("Civil War Cannon");
    assertTrue(mansion.isGameOver());
    assertEquals("Komal", mansion.getWinner());
    mansion.actionPick("Cannon");
  }

  /**
   * Test case to test actionLook once the Target is killed.
   * 
   * @throws IllegalAccessException when no player in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void actionLookGameOver() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Hedge Maze", true);
    assertFalse(mansion.isGameOver());
    assertEquals(null, mansion.getWinner());
    mansion.actionPick("Civil War Cannon");
    mansion.actionAttemptKill("Civil War Cannon");
    assertTrue(mansion.isGameOver());
    assertEquals("Komal", mansion.getWinner());
    mansion.actionLook();
  }

  /**
   * Test case to test actionMoveTargetPet once the Target is killed.
   * 
   * @throws IllegalAccessException when no player in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void actionMovePetGameOver() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Hedge Maze", true);
    assertFalse(mansion.isGameOver());
    assertEquals(null, mansion.getWinner());
    mansion.actionPick("Civil War Cannon");
    mansion.actionAttemptKill("Civil War Cannon");
    assertTrue(mansion.isGameOver());
    assertEquals("Komal", mansion.getWinner());
    mansion.actionMovePet("Piazza");
  }

  /**
   * Test case to test actionAttemptKill once the Target is killed.
   * 
   * @throws IllegalAccessException when no player in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void actionKillGameOver() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Hedge Maze", true);
    assertFalse(mansion.isGameOver());
    assertEquals(null, mansion.getWinner());
    mansion.actionPick("Civil War Cannon");
    mansion.actionAttemptKill("Civil War Cannon");
    assertTrue(mansion.isGameOver());
    assertEquals("Komal", mansion.getWinner());
    mansion.actionAttemptKill("Poking");
  }

  /**
   * Test case to test action attempt to kill when no players in game.
   * 
   * @throws IllegalAccessException when no player in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void actionKillNoPlayers() throws IllegalAccessException {
    mansion.actionAttemptKill("Poking");
  }

  /**
   * Test case to test action attempt to kill when target and player are in same
   * room and player tries to kill with item it does not have.
   * 
   */
  @Test(expected = IllegalArgumentException.class)
  public void actionKillNoItems() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    assertEquals("Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House", mansion.getTargetDetails());
    mansion.actionAttemptKill("Civil War Cannon");
  }

  /**
   * Test case to test action attempt to kill when player and target are not in
   * the same room and player has item.
   * 
   */
  @Test(expected = IllegalAccessException.class)
  public void actionKillNoTarget() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    assertEquals("Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House", mansion.getTargetDetails());
    mansion.actionPick("Revolver");
    assertEquals("Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Hedge Maze", mansion.getTargetDetails());
    mansion.actionAttemptKill("Revolver");
  }

  /**
   * Test case to test action attempt to kill when target and player are in same
   * room and player tries to kill target with item it has.
   */
  @Test
  public void actionKill() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Hedge Maze", true);
    assertEquals("Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [] \n" + "Item picking capacity = Unlimited \n"
        + "Player Type = Human\n" + "Current Location = Hedge Maze",
        mansion.getDetailsOfPlayer("Komal"));
    assertEquals("Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House", mansion.getTargetDetails());
    mansion.actionPick("Civil War Cannon");
    assertEquals("Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [Civil War Cannon : 3] \n"
        + "Item picking capacity = Unlimited \n" + "Player Type = Human\n"
        + "Current Location = Hedge Maze", mansion.getDetailsOfPlayer("Komal"));
    assertEquals("Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Hedge Maze", mansion.getTargetDetails());
    mansion.actionAttemptKill("Civil War Cannon");
    assertEquals("Details of Player: \n" + "Player Name = Komal \n"
        + "List of Items with Player = [] \n" + "Item picking capacity = Unlimited \n"
        + "Player Type = Human\n" + "Current Location = Hedge Maze",
        mansion.getDetailsOfPlayer("Komal"));
    assertEquals("Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 0\n"
        + "Target Location = Piazza", mansion.getTargetDetails());
  }

  /**
   * Test case to test action attempt to kill when target and player are in same
   * room and player tries to kill target by poking.
   */
  @Test
  public void actionKillPoking() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    assertEquals("Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House", mansion.getTargetDetails());
    mansion.actionAttemptKill("Poking");
    assertEquals("Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 2\n"
        + "Target Location = Hedge Maze", mansion.getTargetDetails());
  }

  /**
   * Test case to test action attempt to kill when target and player are in same
   * room and player is seen by other player.
   */
  @Test
  public void actionKillPlayerSeen() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    mansion.addPlayer("K", "Green House", true);
    assertEquals("Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Green House", mansion.getTargetDetails());
    mansion.actionAttemptKill("Poking");
    assertEquals("Target Details: \n" + "Target Name = Doctor Lucky \n" + "Health Points = 3\n"
        + "Target Location = Hedge Maze", mansion.getTargetDetails());
  }

  /**
   * Test case to test pet gets added in the space as target.
   */
  @Test
  public void testPetLocationWhileAdding() {
    assertEquals(
        "Detail of Room: \n" + "Room Name = Green House \n"
            + "List of Items in the Room with their capacity = [Revolver : 3]\n"
            + "Players in the room = []\n" + "Neighbors= [Hedge Maze]\n"
            + "Target Doctor Lucky is present in this room.\n"
            + "Target Pet Fortune the Cat is present in this room.",
        mansion.getDetailsOfRoom("Green House"));
  }

  /**
   * Test case to test dfs implementation.
   */
  @Test
  public void testDfs() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Green House", true);
    assertEquals(
        "Detail of Room: \n" + "Room Name = Green House \n"
            + "List of Items in the Room with their capacity = [Revolver : 3]\n"
            + "Players in the room = [Komal]\n" + "Neighbors= [Hedge Maze]\n"
            + "Target Doctor Lucky is present in this room.\n"
            + "Target Pet Fortune the Cat is present in this room.",
        mansion.getDetailsOfRoom("Green House"));
    mansion.actionLook();
    assertEquals(
        "Detail of Room: \n" + "Room Name = Green House \n"
            + "List of Items in the Room with their capacity = [Revolver : 3]\n"
            + "Players in the room = [Komal]\n" + "Neighbors= []",
        mansion.getDetailsOfRoom("Green House"));
    assertEquals(
        "Detail of Room: \n" + "Room Name = Hedge Maze \n"
            + "List of Items in the Room with their capacity = [Civil War Cannon : 3, Cannon : 3]\n"
            + "Players in the room = []\n" + "Neighbors= [Green House, Piazza]\n"
            + "Target Doctor Lucky is present in this room.\n"
            + "Target Pet Fortune the Cat is present in this room.",
        mansion.getDetailsOfRoom("Hedge Maze"));
    mansion.actionLook();
    assertEquals(
        "Detail of Room: \n" + "Room Name = Hedge Maze \n"
            + "List of Items in the Room with their capacity = [Civil War Cannon : 3, Cannon : 3]\n"
            + "Players in the room = []\n" + "Neighbors= [Green House]",
        mansion.getDetailsOfRoom("Hedge Maze"));
    assertEquals(
        "Detail of Room: \n" + "Room Name = Piazza \n"
            + "List of Items in the Room with their capacity = []\n" + "Players in the room = []\n"
            + "Neighbors= [Hedge Maze]\n" + "Target Doctor Lucky is present in this room.\n"
            + "Target Pet Fortune the Cat is present in this room.",
        mansion.getDetailsOfRoom("Piazza"));
    mansion.actionLook();
    assertEquals(
        "Detail of Room: \n" + "Room Name = Hedge Maze \n"
            + "List of Items in the Room with their capacity = [Civil War Cannon : 3, Cannon : 3]\n"
            + "Players in the room = []\n" + "Neighbors= [Green House, Piazza]\n"
            + "Target Pet Fortune the Cat is present in this room.",
        mansion.getDetailsOfRoom("Hedge Maze"));
  }

  /**
   * Test case to test room is not visible when target pet is in it.
   */
  @Test
  public void testInvsibleNeighbor() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Hedge Maze", true);
    assertEquals(
        "Detail of Room: \n" + "Room Name = Green House \n"
            + "List of Items in the Room with their capacity = [Revolver : 3]\n"
            + "Players in the room = []\n" + "Neighbors= [Hedge Maze]\n"
            + "Target Doctor Lucky is present in this room.\n"
            + "Target Pet Fortune the Cat is present in this room.",
        mansion.getDetailsOfRoom("Green House"));
    assertEquals("Detail of Room: \n" + "Room Name = Hedge Maze \n"
        + "List of Items in the Room with their capacity = [Civil War Cannon : 3, Cannon : 3]\n"
        + "Players in the room = [Komal]\n" + "Neighbors= [Piazza]\n" + "\n"
        + "Details for Neighbor Piazza\n" + "Detail of Room: \n" + "Room Name = Piazza \n"
        + "List of Items in the Room with their capacity = []\n" + "Players in the room = []\n"
        + "Neighbors= [Hedge Maze]", mansion.actionLook());
  }

  /**
   * Test case to test number of turns is updated after every action.
   * 
   * @throws IllegalAccessException when no player in the game.
   */
  @Test
  public void testNumberOfTurns() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Hedge Maze", true);
    assertEquals(3, this.mansion.getNumberOfTurns());
    this.mansion.actionLook();
    assertEquals(2, this.mansion.getNumberOfTurns());
    this.mansion.actionLook();
    assertEquals(1, this.mansion.getNumberOfTurns());
    this.mansion.actionLook();
    assertEquals(0, this.mansion.getNumberOfTurns());
    assertTrue(this.mansion.isGameOver());
  }

  /**
   * Test case to test only 10 players can be added.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMaxPlayers() {
    mansion.addPlayer("Komal", "Hedge Maze", true);
    mansion.addPlayer("Komal1", "Hedge Maze", false);
    mansion.addPlayer("Komal2", "Hedge Maze", true);
    mansion.addPlayer("Komal3", "Hedge Maze", false);
    assertEquals(4, mansion.getNumberOfPlayers());
    mansion.addPlayer("Komal4", "Hedge Maze", false);
  }

  /**
   * Test case to test get current player name.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test
  public void testCurrentPlayerName() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Hedge Maze", true);
    mansion.addPlayer("Komal1", "Hedge Maze", false);
    assertEquals("Komal", mansion.getCurrentPlayerName());
    mansion.actionLook();
    assertEquals("Komal1", mansion.getCurrentPlayerName());
  }

  /**
   * Test case to test get current player when no player in game.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test(expected = IllegalAccessException.class)
  public void testCurrentPlayerNameWhenNoPlayer() throws IllegalAccessException {
    mansion.getCurrentPlayerName();
  }

  /**
   * Test case to test get room location.
   */
  @Test
  public void testGetRoomLocation() {
    Map<String, int[]> map = new HashMap<>();
    map.put("Piazza", new int[] { 28, 12, 35, 19 });
    map.put("Hedge Maze", new int[] { 30, 20, 35, 25 });
    map.put("Green House", new int[] { 28, 26, 35, 29 });
    assertTrue(map.keySet().equals(mansion.getRoomLocation().keySet()));
    for (String roomName : mansion.getRoomLocation().keySet()) {
      assertTrue(Arrays.equals(map.get(roomName), mansion.getRoomLocation().get(roomName)));
    }
  }

  /**
   * Test case to test get player, target and target pet location.
   * 
   * @throws IllegalAccessException when there are no players in the game.
   */
  @Test
  public void testLocation() throws IllegalAccessException {
    mansion.addPlayer("Komal", "Hedge Maze", true);
    Map<String, String> map = new HashMap<>();
    map.put("Komal", "Hedge Maze");
    assertEquals("Green House", mansion.getTargetLocation());
    assertEquals("Green House", mansion.getTargetPetLocation());
    mansion.actionMovePet("Piazza");
    assertEquals("Hedge Maze", mansion.getTargetLocation());
    assertEquals("Piazza", mansion.getTargetPetLocation());
    assertTrue(map.equals(mansion.getPlayerLocations()));
  }
}
