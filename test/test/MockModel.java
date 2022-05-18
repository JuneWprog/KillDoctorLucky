package test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import mansion.Mansion;

/**
 * Mock Model to test Controller.
 * 
 * @author komalshah
 *
 */
public class MockModel implements Mansion {
  private final Appendable out;
  private int numberOfTurns;

  /**
   * Object creation for Mock Model.
   * 
   * @param append Appendable object.
   */
  public MockModel(Appendable append) {
    Objects.requireNonNull(append);
    this.out = append;
    this.numberOfTurns = 3;
  }

  @Override
  public BufferedImage createGraphicalRepresentation() {
    return new BufferedImage(10, 10, 1);
  }

  @Override
  public List<String> getNeighborForRoom(String roomName)
      throws NullPointerException, IllegalArgumentException {
    return new ArrayList<>(List.of("Neighbors"));
  }

  @Override
  public String getDetailsOfRoom(String roomName)
      throws NullPointerException, IllegalArgumentException {
    return "Room Details for " + roomName;
  }

  @Override
  public void moveTarget() throws IllegalArgumentException {
    println("Target Moved.");
  }

  @Override
  public String getMansionName() {
    return "Mansion.";
  }

  @Override
  public void addPlayer(String name, String location, int capacity, boolean isHuman)
      throws NullPointerException, IllegalArgumentException {
    println(String.format("Player added [name=%s, location=%s, capacity=%d, isHuman=%b]", name,
        location, capacity, isHuman));
  }

  @Override
  public void addPlayer(String name, String location, boolean isHuman)
      throws NullPointerException, IllegalArgumentException {
    println(
        String.format("Player added [name=%s, location=%s, isHuman=%b]", name, location, isHuman));
  }

  @Override
  public void actionMove(String roomName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException {
    println("Player Moved to " + roomName);
    this.numberOfTurns -= 1;
  }

  @Override
  public void actionPick(String itemName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException {
    println("Item picked " + itemName);
    this.numberOfTurns -= 1;
  }

  @Override
  public String actionLook() throws IllegalAccessException {
    this.numberOfTurns -= 1;
    return "Looked Around.";
  }

  @Override
  public String getDetailsOfPlayer(String playerName)
      throws NullPointerException, IllegalArgumentException {
    return "Player details for " + playerName;
  }

  @Override
  public String getCurrentPlayerDetails() throws IllegalAccessException {
    return "Current Player";
  }

  @Override
  public List<String> getNeighboursToMove() throws IllegalAccessException {
    return new ArrayList<>(List.of("Neighbors"));
  }

  @Override
  public Map<String, Integer> getItemsToPick() throws IllegalAccessException {
    HashMap<String, Integer> map = new HashMap<>();
    map.put("Item", 5);
    return map;
  }

  @Override
  public boolean isHuman() throws IllegalAccessException {
    return true;
  }

  @Override
  public int getNumberOfPlayers() {
    return 10;
  }

  private void println(String message) throws IllegalStateException {
    try {
      this.out.append(message + "\n");
    } catch (IOException exception) {
      throw new IllegalStateException("Append failed", exception);
    }
  }

  @Override
  public void actionMovePet(String roomName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException {
    println("Target's pet moved to " + roomName);
    this.numberOfTurns -= 1;
  }

  @Override
  public boolean actionAttemptKill(String itemName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException {
    println("Attempt to kill using " + itemName);
    this.numberOfTurns -= 1;
    return true;
  }

  @Override
  public boolean canSee(String a, String b) throws NullPointerException, IllegalArgumentException {
    return false;
  }

  @Override
  public boolean isCurrentPlayerSeen() {
    return false;
  }

  @Override
  public boolean isGameOver() {
    return this.numberOfTurns < 1;
  }

  @Override
  public List<String> getRoomNames() {
    return new ArrayList<>(List.of("Room names"));
  }

  @Override
  public Map<String, Integer> getItemsToKill() throws IllegalAccessException {
    return new HashMap<>();
  }

  @Override
  public boolean isTargetPresent() throws IllegalAccessException {
    return true;
  }

  @Override
  public String getTargetDetails() {
    return "Target Details";
  }

  @Override
  public String getWinner() {
    return "Winnner";
  }

  @Override
  public List<String> getPlayerNames() {
    return new ArrayList<>(List.of("Players"));
  }

  @Override
  public int getNumberOfTurns() {
    return this.numberOfTurns;
  }

  @Override
  public String getCurrentPlayerName() throws IllegalAccessException {
    return "Name";
  }

  @Override
  public String getTargetPetLocation() {
    return "Armory";
  }

  @Override
  public String getTargetLocation() {
    return "Armory";
  }

  @Override
  public Map<String, int[]> getRoomLocation() {
    return new HashMap<>();
  }

  @Override
  public Map<String, String> getPlayerLocations() {
    return new HashMap<>();
  }

}
