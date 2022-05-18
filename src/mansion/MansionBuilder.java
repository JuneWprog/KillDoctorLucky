package mansion;

import item.Item;
import item.ItemImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import room.Room;
import room.RoomImpl;
import target.Target;
import target.TargetImpl;
import target.TargetPet;
import target.TargetPetImpl;
import utils.CommonUtils;

/**
 * Builder class for Mansion object.
 *
 * @author komalshah
 */
public class MansionBuilder {
  private int numberOfRows;
  private int numberOfColumns;
  private String mansionName;
  private int numberOfRooms;
  private int numberOfItems;
  private final List<Room> listOfRooms;
  private final List<String> lisOfItemsNames;
  private Target target;
  private TargetPet targetPet;
  private final Map<Integer, Set<Integer>> mansionMap;
  private Integer[][] roomArray;
  private int numberOfTurns;
  private int maxNumberOfPlayers;

  /**
   * Creates object for Mansion Builder.
   */
  public MansionBuilder() {
    this.listOfRooms = new ArrayList<>();
    this.mansionMap = new HashMap<>();
    this.lisOfItemsNames = new ArrayList<>();
    this.numberOfTurns = 0;
    this.maxNumberOfPlayers = 0;
  }

  private void setNumberOfRows(int rows) throws IllegalArgumentException {
    if (rows <= 0) {
      throw new IllegalArgumentException("Row Size of Mansion cannot be Negative or Zero.");
    }
    this.numberOfRows = rows;
  }

  private void setNumberOfColumns(int columns) throws IllegalArgumentException {
    if (columns <= 0) {
      throw new IllegalArgumentException("Column Size of Mansion cannot be Negative or Zero.");
    }
    this.numberOfColumns = columns;
  }

  private void setMansionName(String name) throws NullPointerException {
    CommonUtils.stringIsEmpty(name, "Mansion name cannot be NULL or empty.");
    this.mansionName = name;
  }

  private void setNumberOfRooms(int rooms) throws IllegalArgumentException {
    if (rooms <= 0) {
      throw new IllegalArgumentException("Number of Rooms in Mansion cannot be Negative or Zero.");
    }
    this.numberOfRooms = rooms;
  }

  private void setNumberOfItems(int items) throws IllegalArgumentException {
    if (items <= 0) {
      throw new IllegalArgumentException("Number of Items in Mansion cannot be Negative or Zero.");
    }
    this.numberOfItems = items;
  }

  private boolean validateRoomName(String roomName) {
    Room foundRoom = this.listOfRooms.stream().filter(room -> room.getName().equals(roomName))
        .findAny().orElse(null);
    return Objects.isNull(foundRoom);
  }

  private boolean validateItemName(String itemName) {
    return this.lisOfItemsNames.contains(itemName);
  }

  private void validateRoom(int topX, int topY, int bottomX, int bottomY, String name)
      throws IllegalArgumentException, NullPointerException {
    CommonUtils.stringIsEmpty(name, "Room name cannot be NULL or empty.");
    if (!validateRoomName(name)) {
      throw new IllegalArgumentException(String.format("%s room already exists.", name));
    }
    if (topX > this.numberOfRows || bottomX > this.numberOfRows || topY > this.numberOfColumns
        || bottomY > this.numberOfColumns) {
      throw new IllegalArgumentException(
          String.format("Size of the room %s is bigger than Mansion Bounds.", name));
    }
  }

  private void createRooms(int topX, int topY, int bottomX, int bottomY, String name)
      throws IllegalArgumentException, NullPointerException {
    validateRoom(topX, topY, bottomX, bottomY, name);
    Room room = new RoomImpl(name, topX, topY, bottomX, bottomY);
    this.listOfRooms.add(room);
    if (Objects.isNull(roomArray)) {
      this.roomArray = new Integer[this.numberOfRows][this.numberOfColumns];
    }
    int currentIndex = this.listOfRooms.size() - 1;
    for (int i = topX; i <= bottomX; i++) {
      for (int j = topY; j <= bottomY; j++) {
        if (Objects.nonNull(roomArray[i][j])) {
          throw new IllegalArgumentException("Rooms are overlapping.");
        }
        roomArray[i][j] = currentIndex;
      }
    }
    mansionMap.put(currentIndex, new HashSet<>());
  }

  private Item createItem(int damage, String name)
      throws IllegalArgumentException, NullPointerException {
    CommonUtils.stringIsEmpty(name, "Item name cannot be NULL or empty.");
    if (validateItemName(name)) {
      throw new IllegalArgumentException("This item already exists.");
    }
    return new ItemImpl(name, damage);
  }

  private void addItemsToRoom(int roomNumber, Item item)
      throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(item);
    if (roomNumber < 0 || roomNumber > this.numberOfRooms - 1) {
      throw new IllegalArgumentException("Room number is out of bounds for item.");
    }
    this.lisOfItemsNames.add(item.getName());
    this.listOfRooms.get(roomNumber).addItemToRoom(item);
  }

  private void setTarget(int hp, String name)
      throws IllegalArgumentException, NullPointerException {
    this.target = new TargetImpl(name, hp);
  }

  private void setTargetPet(String name) throws NullPointerException {
    this.targetPet = new TargetPetImpl(name);
  }

  private void createMap() {
    for (int i = 0; i < this.numberOfRows; i++) {
      for (int j = 0; j < this.numberOfColumns; j++) {
        if (Objects.isNull(this.roomArray[i][j])) {
          continue;
        }
        // Checking room on the right.
        if (j + 1 < this.numberOfColumns && Objects.nonNull(this.roomArray[i][j + 1])
            && this.roomArray[i][j] != this.roomArray[i][j + 1]) {
          updateMansionMap(this.roomArray[i][j], this.roomArray[i][j + 1]);
          updateMansionMap(this.roomArray[i][j + 1], this.roomArray[i][j]);
        }
        // Checking room to the bottom.
        if (i + 1 < this.numberOfRows && Objects.nonNull(this.roomArray[i + 1][j])
            && this.roomArray[i][j] != this.roomArray[i + 1][j]) {
          updateMansionMap(this.roomArray[i][j], this.roomArray[i + 1][j]);
          updateMansionMap(this.roomArray[i + 1][j], this.roomArray[i][j]);
        }
      }
    }
  }

  private void updateMansionMap(int currentRoom, int neighbor) {
    Set<Integer> set = this.mansionMap.get(currentRoom);
    set.add(neighbor);
  }
  
  private void validateMansion() throws IllegalArgumentException, NullPointerException {
    if (this.numberOfTurns <= 0) {
      throw new IllegalArgumentException("Number of Turns cannot be negative or zero.");
    }
    if (this.maxNumberOfPlayers <= 0) {
      throw new IllegalArgumentException("Maximum number of players cannot be negative or zero.");
    }
    if (this.numberOfRows <= 0 || this.numberOfColumns <= 0) {
      throw new IllegalArgumentException("Size of Mansion cannot be Negative or Zero.");
    }
    if (this.numberOfRooms <= 0) {
      throw new IllegalArgumentException("Number of Rooms in Mansion cannot be Negative or Zero.");
    }
    CommonUtils.stringIsEmpty(this.mansionName, "Mansion name cannot be NULL or empty.");
    Objects.requireNonNull(this.listOfRooms);
    if (this.listOfRooms.size() != this.numberOfRooms) {
      throw new IllegalArgumentException("List of Rooms do not match number of Rooms.");
    }
    Objects.requireNonNull(this.target);
    Objects.requireNonNull(this.targetPet);
    Objects.requireNonNull(this.mansionMap);
  }

  /**
   * Sets number of turns for the game.
   * 
   * @param turns number of turns.
   * @return MansionBuilder The Mansion Builder Object.
   * @throws IllegalArgumentException when number of turns is negative or zero.
   */
  public MansionBuilder setNumberOfTurns(int turns) throws IllegalArgumentException {
    if (turns <= 0) {
      throw new IllegalArgumentException("Number of Turns cannot be negative or zero.");
    }
    this.numberOfTurns = turns;
    return this;
  }
  
  /**
   * Sets maximum number of players for the game.
   * 
   * @param maxPlayers maximum number of players allowed.
   * @return MansionBuilder The Mansion Builder Object.
   * @throws IllegalArgumentException when maximum number of players is negative or zero.
   */
  public MansionBuilder setMaximumNumberOfPlayers(int maxPlayers) throws IllegalArgumentException {
    if (maxPlayers <= 0) {
      throw new IllegalArgumentException("Number of Turns cannot be negative or zero.");
    }
    this.maxNumberOfPlayers = maxPlayers;
    return this;
  }

  /**
   * Reading the world specification and creating mansion map.
   * 
   * @param readable Stream containing world configuration.
   * @return MansionBuilder The Mansion Builder Object.
   * @throws IllegalArgumentException when invalid input is passed.
   * @throws NullPointerException     when objects are NULL.
   * @throws NumberFormatException    when error occurs in parsing string to
   *                                  integer
   */
  public MansionBuilder readConfigFile(Readable readable)
      throws IllegalArgumentException, NullPointerException, NumberFormatException {
    Scanner in = new Scanner(readable);
    // Reading Mansion size and Mansion Name
    this.setNumberOfRows(Integer.parseInt(in.next()));
    this.setNumberOfColumns(Integer.parseInt(in.next()));
    this.setMansionName(in.nextLine().trim());

    // Reading Target details
    this.setTarget(Integer.parseInt(in.next()), in.nextLine().trim());

    // Reading Target Pet details
    this.setTargetPet(in.nextLine().trim());

    // Reading number of rooms
    this.setNumberOfRooms(Integer.parseInt(in.next()));

    // Reading list of rooms
    for (int i = 0; i < this.numberOfRooms; i++) {
      createRooms(Integer.parseInt(in.next()), Integer.parseInt(in.next()),
          Integer.parseInt(in.next()), Integer.parseInt(in.next()), in.nextLine().trim());
    }

    // Reading number of items
    this.setNumberOfItems(Integer.parseInt(in.next()));

    // Reading list of items
    for (int i = 0; i < this.numberOfItems; i++) {
      addItemsToRoom(Integer.parseInt(in.next()),
          createItem(Integer.parseInt(in.next()), in.nextLine().trim()));
    }
    in.close();

    createMap();
    return this;
  }
  
  /**
   * Builds the Mansion object for the game.
   * 
   * @return the Mansion Object.
   * @throws IllegalArgumentException when invalid input is passed.
   * @throws NullPointerException     when objects are NULL.
   */
  public Mansion build() throws IllegalArgumentException, NullPointerException {
    validateMansion();
    String name = new String(this.mansionName);
    List<Room> roomList = new ArrayList<>(this.listOfRooms);
    Target t = new TargetImpl(this.target.getName(), this.target.getHealthPoint());
    TargetPet tp = new TargetPetImpl(this.targetPet.getName());
    Map<Integer, Set<Integer>> map = new HashMap<>(this.mansionMap);
    
    
    return new MansionImpl(this.numberOfRows, this.numberOfColumns, name,
        this.numberOfRooms, roomList, t, tp, map,
        this.numberOfTurns, this.maxNumberOfPlayers);
  }

}
