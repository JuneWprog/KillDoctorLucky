package mansion;

import item.Item;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import player.Player;
import player.PlayerImpl;
import room.Room;
import target.Target;
import target.TargetPet;
import utils.CommonUtils;
import utils.Constants;

/**
 * Implementation of interface Mansion that represents the World on which the
 * game is played.
 * 
 * @author komalshah
 */
public class MansionImpl implements Mansion {

  private final int numberOfRows;
  private final int numberOfColumns;
  private final String mansionName;
  private final int numberOfRooms;
  private final List<Room> listOfRooms;
  private final Target target;
  private final Map<Integer, Set<Integer>> mansionMap;
  private final List<Player> listOfPlayers;
  private int currentPlayer;
  private TargetPet targetPet;
  private List<String> evidences;
  private String winner;
  private final Set<Integer> visited;
  private final Stack<Integer> stack;
  private int numberOfTurns;
  private int maxNumberOfPlayers;

  /**
   * Creates an object for Mansion with fields Mansion Name, size of mansion,
   * number and list of Rooms in the mansion and the Target Character.
   * 
   * @param rows         Total Width of the Mansion.
   * @param columns      Total Length of the Mansion.
   * @param name         Name of the Mansion.
   * @param rooms        Total number of Rooms in the Mansion.
   * @param roomList     List of all the Rooms in the Mansion.
   * @param targetObject Target Character of the Mansion.
   * @param pet          Target Character's Pet in the Mansion.
   * @param map          Map of the Mansion specifying neighbors of the Room.
   * @param turns        number of turns.
   * @param maxPlayers   maximum number of players allowed.
   * @throws IllegalArgumentException
   *                                  <ul>
   *                                  <li>when size of Mansion is negative or
   *                                  zero.</li>
   *                                  <li>when number of rooms in Mansion are
   *                                  negative or zero.</li>
   *                                  <li>when number of items in Mansion are
   *                                  negative or zero.</li>
   *                                  <li>when size of list of rooms is not equal
   *                                  to number of rooms.</li>
   *                                  </ul>
   * @throws NullPointerException     when Name of Mansion, List of Rooms, Target
   *                                  and Mansion map is NULL.
   */
  public MansionImpl(int rows, int columns, String name, int rooms, List<Room> roomList,
      Target targetObject, TargetPet pet, Map<Integer, Set<Integer>> map, int turns, int maxPlayers)
      throws IllegalArgumentException, NullPointerException {
    validateMansion(rows, columns, name, rooms, roomList, targetObject, pet, map, turns,
        maxPlayers);
    this.numberOfRows = rows;
    this.numberOfColumns = columns;
    this.mansionName = name;
    this.numberOfRooms = rooms;
    this.listOfRooms = roomList;
    this.target = targetObject;
    this.targetPet = pet;
    this.mansionMap = map;
    this.listOfPlayers = new ArrayList<>();
    this.currentPlayer = 0;
    this.evidences = new ArrayList<>();
    this.winner = null;
    this.visited = new HashSet<>();
    this.stack = new Stack<>();
    this.numberOfTurns = turns;
    this.maxNumberOfPlayers = maxPlayers;
  }

  private void validateMansion(int rows, int columns, String name, int rooms, List<Room> roomsList,
      Target targetObject, TargetPet pet, Map<Integer, Set<Integer>> map, int turns, int maxPlayers)
      throws IllegalArgumentException, NullPointerException {
    if (rows <= 0 || columns <= 0) {
      throw new IllegalArgumentException("Size of Mansion cannot be Negative or Zero.");
    }
    if (rooms <= 0) {
      throw new IllegalArgumentException("Number of Rooms in Mansion cannot be Negative or Zero.");
    }
    CommonUtils.stringIsEmpty(name, "Mansion name cannot be NULL or empty.");
    Objects.requireNonNull(roomsList);
    if (roomsList.size() != rooms) {
      throw new IllegalArgumentException("List of Rooms do not match number of Rooms.");
    }
    Objects.requireNonNull(targetObject);
    Objects.requireNonNull(pet);
    Objects.requireNonNull(map);
    if (turns <= 0) {
      throw new IllegalArgumentException("Number of Turns cannot be negative or zero.");
    }
    if (maxPlayers <= 0) {
      throw new IllegalArgumentException("Number of Turns cannot be negative or zero.");
    }
  }

  private int getRoomNumberFromRoomName(String roomName)
      throws NullPointerException, IllegalArgumentException {
    CommonUtils.stringIsEmpty(roomName, "Room name cannot be NULL or empty.");
    try {
      return IntStream.range(0, listOfRooms.size())
          .filter(room -> listOfRooms.get(room).getName().equals(roomName)).findFirst().getAsInt();
    } catch (NoSuchElementException exception) {
      throw new IllegalArgumentException("Room name does not exist in the Mansion.");
    }
  }

  private int getPlayerNumberFromPlayerName(String playerName) throws NullPointerException {
    CommonUtils.stringIsEmpty(playerName, "Player name cannot be NULL or empty.");
    try {
      return IntStream.range(0, listOfPlayers.size())
          .filter(player -> listOfPlayers.get(player).getName().equals(playerName)).findFirst()
          .getAsInt();
    } catch (NoSuchElementException exception) {
      return -1;
    }
  }

  private void updateTurn() {
    int numberOfPlayers = this.listOfPlayers.size();
    if (numberOfPlayers == 0) {
      return;
    }

    if (isGameOver()) {
      this.winner = this.listOfPlayers.get(currentPlayer).getName();
    }

    this.currentPlayer += 1;
    this.currentPlayer %= numberOfPlayers;
    moveTarget();
    this.numberOfTurns -= 1;
  }

  private String getNeighborDetails(String roomName)
      throws NullPointerException, IllegalArgumentException {
    List<String> neighbours = getNeighborForRoom(roomName);
    StringBuilder neighbourDetails = new StringBuilder();
    for (String neighbour : neighbours) {
      neighbourDetails.append("\n\nDetails for Neighbor " + neighbour + "\n");
      neighbourDetails.append(getDetailsOfRoom(neighbour));
    }
    return neighbourDetails.toString();
  }

  private void movePetDfs() {
    if (this.visited.size() == this.listOfRooms.size()) {
      this.visited.clear();
    }
    if (this.stack.isEmpty()) {
      this.stack.push(this.targetPet.getTargetPetLocation());
      this.visited.add(this.targetPet.getTargetPetLocation());
    } else if (this.stack.peek() != this.targetPet.getTargetPetLocation()) {
      this.stack.pop();
      this.stack.push(this.targetPet.getTargetPetLocation());
      this.visited.add(this.targetPet.getTargetPetLocation());
    }
    int current = this.stack.pop();
    Set<Integer> choices = new HashSet<>(this.mansionMap.get(current));
    choices.removeAll(visited);
    for (Integer c : choices) {
      if (!this.stack.contains(c)) {
        this.stack.push(c);
      }
    }
    this.targetPet.updateTargetPetLocation(this.stack.peek());
    this.visited.add(this.stack.peek());
  }

  @Override
  public BufferedImage createGraphicalRepresentation() {
    int scale = 30;
    int padding = 20;
    BufferedImage bufferedImage = new BufferedImage((this.numberOfColumns + 2) * scale,
        (this.numberOfRows + 2) * scale, BufferedImage.TYPE_INT_RGB);
    Graphics graphics = bufferedImage.createGraphics();
    for (Room room : listOfRooms) {
      int[] location = room.getLocation();
      int y = location[0] * scale + padding;
      int x = location[1] * scale + padding;
      int height = (location[2] - location[0] + 1) * scale;
      int width = (location[3] - location[1] + 1) * scale;
      graphics.drawString(room.getName(), x + scale, y + scale);
      graphics.drawRect(x, y, width, height);
    }
    graphics.dispose();
    return bufferedImage;
  }

  @Override
  public List<String> getNeighborForRoom(String roomName)
      throws NullPointerException, IllegalArgumentException {
    int roomNumber = getRoomNumberFromRoomName(roomName);
    Set<Integer> set = new HashSet<>(mansionMap.get(roomNumber));
    set.remove(this.targetPet.getTargetPetLocation());
    return set.stream().map(r -> listOfRooms.get(r).getName()).collect(Collectors.toList());
  }

  @Override
  public String getDetailsOfRoom(String roomName)
      throws NullPointerException, IllegalArgumentException {
    int roomNumber = getRoomNumberFromRoomName(roomName);
    Room room = listOfRooms.get(roomNumber);

    // Room name and items in the room.
    StringBuilder roomDetails = new StringBuilder(room.toString());

    // Players in the room.
    roomDetails.append("\nPlayers in the room = ");
    List<String> players = this.listOfPlayers.stream().filter(p -> p.getLocation() == roomNumber)
        .map(p -> p.getName()).collect(Collectors.toList());
    roomDetails.append(players);

    // Neighbors of the room.
    roomDetails.append("\nNeighbors= ");
    roomDetails.append(getNeighborForRoom(roomName));

    // Target details if it is in the room.
    if (this.target.getTargetLocation() == roomNumber) {
      roomDetails.append("\nTarget " + this.target.getName() + " is present in this room.");
    }

    // Target Pet details if it is in the room.
    if (this.targetPet.getTargetPetLocation() == roomNumber) {
      roomDetails.append("\nTarget Pet " + this.targetPet.getName() + " is present in this room.");
    }

    return roomDetails.toString();
  }

  @Override
  public void moveTarget() throws IllegalArgumentException {
    this.target.updateTargetLocation(this.numberOfRooms);
  }

  @Override
  public String getMansionName() {
    return this.mansionName;
  }

  @Override
  public void addPlayer(String name, String location, int capacity, boolean isHuman)
      throws NullPointerException, IllegalArgumentException {
    if (this.listOfPlayers.size() >= this.maxNumberOfPlayers) {
      throw new IllegalArgumentException("Maximum number of players limit reached.");
    }
    if (getPlayerNumberFromPlayerName(name) != -1) {
      throw new IllegalArgumentException("This player already exists.");
    }
    int playerlocation = getRoomNumberFromRoomName(location);
    Player player = new PlayerImpl(name, playerlocation, capacity, isHuman);
    this.listOfPlayers.add(player);
  }

  @Override
  public void addPlayer(String name, String location, boolean isHuman)
      throws NullPointerException, IllegalArgumentException {
    addPlayer(name, location, -1, isHuman);
  }

  @Override
  public void actionMove(String roomName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException {
    if (this.listOfPlayers.size() == 0) {
      throw new IllegalAccessException("No Players in Game.");
    }
    if (isGameOver()) {
      throw new IllegalAccessException("Game is Over!");
    }
    Player player = this.listOfPlayers.get(currentPlayer);
    Room room = this.listOfRooms.get(player.getLocation());
    if (getNeighborForRoom(room.getName()).contains(roomName)) {
      player.movePlayer(getRoomNumberFromRoomName(roomName));
      updateTurn();
      movePetDfs();
    } else {
      throw new IllegalArgumentException("Cannot move to " + roomName);
    }
  }

  @Override
  public void actionPick(String itemName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException {
    CommonUtils.stringIsEmpty(itemName, "Item name cannot be NULL or empty.");
    if (this.listOfPlayers.size() == 0) {
      throw new IllegalAccessException("No Players in Game.");
    }
    if (isGameOver()) {
      throw new IllegalAccessException("Game is Over!");
    }
    Player player = this.listOfPlayers.get(currentPlayer);
    Room room = this.listOfRooms.get(player.getLocation());
    Item item = room.getItem(itemName);
    if (Objects.nonNull(item)) {
      player.addItem(item);
      room.removeItem(item);
      updateTurn();
      movePetDfs();
    } else {
      throw new IllegalArgumentException("Cannot pickup item " + itemName);
    }
  }

  @Override
  public String actionLook() throws IllegalAccessException {
    if (this.listOfPlayers.size() == 0) {
      throw new IllegalAccessException("No Players in Game.");
    }
    if (isGameOver()) {
      throw new IllegalAccessException("Game is Over!");
    }
    Player player = this.listOfPlayers.get(currentPlayer);
    String roomName = this.listOfRooms.get(player.getLocation()).getName();
    String roomDetails = getDetailsOfRoom(roomName);
    String neighbourDetails = getNeighborDetails(roomName);
    updateTurn();
    movePetDfs();
    return roomDetails + neighbourDetails;
  }

  @Override
  public void actionMovePet(String roomName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException {
    if (this.listOfPlayers.size() == 0) {
      throw new IllegalAccessException("No Players in Game.");
    }
    if (isGameOver()) {
      throw new IllegalAccessException("Game is Over!");
    }
    int roomNumber = getRoomNumberFromRoomName(roomName);
    this.targetPet.updateTargetPetLocation(roomNumber);
    updateTurn();
  }

  @Override
  public boolean actionAttemptKill(String itemName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException {
    CommonUtils.stringIsEmpty(itemName, "Item name cannot be NULL or empty.");
    if (this.listOfPlayers.size() == 0) {
      throw new IllegalAccessException("No Players in Game.");
    }
    if (isGameOver()) {
      throw new IllegalAccessException("Game is Over!");
    }
    if (!this.isTargetPresent()) {
      throw new IllegalAccessException("Current Player and Target are not in the same room.");
    }

    Player player = this.listOfPlayers.get(this.currentPlayer);

    // Checking if the current player can be seen.
    if (isCurrentPlayerSeen()) {
      updateTurn();
      movePetDfs();
      return false;
    }

    Item item = player.getItem(itemName);
    if (Objects.nonNull(item)) {
      this.target.updateHealthPoint(item.getDamage());
      this.evidences.add(itemName);
      player.removeItem(item);
      updateTurn();
      movePetDfs();
      return true;
    } else if (Constants.POKING.equalsIgnoreCase(itemName)) {
      this.target.updateHealthPoint(1);
      updateTurn();
      movePetDfs();
      return true;
    } else {
      throw new IllegalArgumentException("Cannot use item " + itemName + " to kill the target.");
    }
  }

  @Override
  public String getDetailsOfPlayer(String playerName)
      throws NullPointerException, IllegalArgumentException {
    int playerNumber = getPlayerNumberFromPlayerName(playerName);
    if (playerNumber == -1) {
      throw new IllegalArgumentException("This player does not exist.");
    }
    Player player = listOfPlayers.get(playerNumber);
    StringBuilder playerDetails = new StringBuilder(player.toString());
    playerDetails.append("\nCurrent Location = ");
    playerDetails.append(this.listOfRooms.get(player.getLocation()).getName());
    return playerDetails.toString();
  }

  @Override
  public String getCurrentPlayerDetails() throws IllegalAccessException {
    if (this.listOfPlayers.size() == 0) {
      throw new IllegalAccessException("No Players in Game.");
    }
    return this.getDetailsOfPlayer(this.listOfPlayers.get(currentPlayer).getName());
  }

  @Override
  public boolean isHuman() throws IllegalAccessException {
    if (this.listOfPlayers.size() == 0) {
      throw new IllegalAccessException("No Players in Game.");
    }
    return this.listOfPlayers.get(currentPlayer).isHuman();
  }

  @Override
  public List<String> getNeighboursToMove() throws IllegalAccessException {
    if (this.listOfPlayers.size() == 0) {
      throw new IllegalAccessException("No Players in Game.");
    }
    int playerLocation = this.listOfPlayers.get(currentPlayer).getLocation();
    return getNeighborForRoom(this.listOfRooms.get(playerLocation).getName());
  }

  @Override
  public Map<String, Integer> getItemsToPick() throws IllegalAccessException {
    if (this.listOfPlayers.size() == 0) {
      throw new IllegalAccessException("No Players in Game.");
    }
    int playerLocation = this.listOfPlayers.get(currentPlayer).getLocation();
    return this.listOfRooms.get(playerLocation).getItemDetails();
  }

  @Override
  public int getNumberOfPlayers() {
    return this.listOfPlayers.size();
  }

  @Override
  public boolean isGameOver() {
    return this.target.getHealthPoint() <= 0 || this.numberOfTurns == 0;
  }

  @Override
  public boolean canSee(String a, String b) throws NullPointerException, IllegalArgumentException {

    // check if same player.
    if (a.equals(b)) {
      return false;
    }

    // get player's number.
    int playerA = getPlayerNumberFromPlayerName(a);
    int playerB = getPlayerNumberFromPlayerName(b);

    // checking if player does not exist.
    if (playerA == -1 || playerB == -1) {
      throw new IllegalArgumentException("Players do not exist.");
    }

    // get player's location.
    int playerLocationA = this.listOfPlayers.get(playerA).getLocation();
    int playerLocationB = this.listOfPlayers.get(playerB).getLocation();

    // checking if players are in the same room.
    if (playerLocationA == playerLocationB) {
      return true;
    }

    return getNeighborForRoom(this.listOfRooms.get(playerLocationA).getName())
        .contains(this.listOfRooms.get(playerLocationB).getName());

  }

  @Override
  public boolean isCurrentPlayerSeen() {
    return this.listOfPlayers.stream().anyMatch(
        x -> this.canSee(x.getName(), this.listOfPlayers.get(this.currentPlayer).getName()));
  }

  @Override
  public List<String> getRoomNames() {
    return this.listOfRooms.stream().map(x -> x.getName()).collect(Collectors.toList());
  }

  @Override
  public List<String> getPlayerNames() {
    return this.listOfPlayers.stream().map(x -> x.getName()).collect(Collectors.toList());
  }

  @Override
  public Map<String, Integer> getItemsToKill() throws IllegalAccessException {
    if (this.listOfPlayers.size() == 0) {
      throw new IllegalAccessException("No Players in Game.");
    }
    Map<String, Integer> items = this.listOfPlayers.get(currentPlayer).getItemDetails();
    items.put(Constants.POKING, 1);
    return items;
  }

  @Override
  public boolean isTargetPresent() throws IllegalAccessException {
    if (this.listOfPlayers.size() == 0) {
      throw new IllegalAccessException("No Players in Game.");
    }
    return this.listOfPlayers.get(currentPlayer).getLocation() == this.target.getTargetLocation();
  }

  @Override
  public String getTargetDetails() {
    StringBuilder targetDetails = new StringBuilder(this.target.toString());
    targetDetails.append("\nTarget Location = ");
    targetDetails.append(this.listOfRooms.get(this.target.getTargetLocation()).getName());
    return targetDetails.toString();
  }

  @Override
  public String getWinner() {
    return this.winner;
  }

  @Override
  public int getNumberOfTurns() {
    return this.numberOfTurns;
  }

  @Override
  public String getCurrentPlayerName() throws IllegalAccessException {
    if (this.listOfPlayers.size() == 0) {
      throw new IllegalAccessException("No Players in Game.");
    }
    return this.listOfPlayers.get(currentPlayer).getName();
  }

  @Override
  public Map<String, int[]> getRoomLocation() {
    Map<String, int[]> rooms = new HashMap<>();
    for (Room room : listOfRooms) {
      rooms.put(room.getName(), room.getLocation());
    }
    return rooms;
  }

  @Override
  public String getTargetLocation() {
    return this.listOfRooms.get(this.target.getTargetLocation()).getName();
  }

  @Override
  public String getTargetPetLocation() {
    return this.listOfRooms.get(this.targetPet.getTargetPetLocation()).getName();
  }

  @Override
  public Map<String, String> getPlayerLocations() {
    Map<String, String> players = new HashMap<>();
    for (Player player : listOfPlayers) {
      players.put(player.getName(), listOfRooms.get(player.getLocation()).getName());
    }
    return players;
  }
}
