package mansion;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

/**
 * A mansion model of our project consists of a number of non-overlapping spaces
 * that are laid out on a 2D grid. Mansion has its name and has a Target
 * Character. Mansion contains a detailed list of all of the rooms that make up
 * the Mansion.
 *
 * @author komalshah
 */
public interface Mansion {
  /**
   * Creates a graphical representation of the Mansion in the form of a
   * BufferedImage.
   * 
   * @return Image representation of Mansion.
   */
  BufferedImage createGraphicalRepresentation();

  /**
   * Determines the neighbors of any room.
   * 
   * @param roomName the name of the room for which Neighbors need to be
   *                 determined.
   * @return the list of neighbors.
   * @throws NullPointerException     when room name is NULL.
   * @throws IllegalArgumentException when room does not exist.
   */
  List<String> getNeighborForRoom(String roomName)
      throws NullPointerException, IllegalArgumentException;

  /**
   * Provides details of room such as its Name, Items in the room and the Rooms
   * that can be seen from this room.
   * 
   * @param roomName the name of the room for which details need to be fetched.
   * @return details of room in String format.
   * @throws NullPointerException     when room name is NULL.
   * @throws IllegalArgumentException when room does not exist.
   */
  String getDetailsOfRoom(String roomName) throws NullPointerException, IllegalArgumentException;

  /**
   * Move the target character around the Mansion. The target character starts in
   * room 0 and moves from room to room in order using the ordered, 0-indexed list
   * of room found in the Mansion.
   * 
   * @throws IllegalArgumentException when total number of rooms are negative or
   *                                  zero.
   */
  void moveTarget() throws IllegalArgumentException;

  /**
   * To get the name of the Mansion.
   * 
   * @return the name of the Mansion.
   */
  String getMansionName();

  /**
   * Adding player with the item picking capacity.
   * 
   * @param name     player name.
   * @param location player location.
   * @param capacity player item picking capacity.
   * @param isHuman  if player is human.
   * @throws NullPointerException     when player name or player location is NULL.
   * @throws IllegalArgumentException when player already exists or when player
   *                                  location does not exist or when capacity is
   *                                  not valid.
   */
  void addPlayer(String name, String location, int capacity, boolean isHuman)
      throws NullPointerException, IllegalArgumentException;

  /**
   * Adding player with no item picking capacity.
   * 
   * @param name     player name.
   * @param location player location.
   * @param isHuman  if player is human.
   * @throws NullPointerException     when player name or player location is NULL.
   * @throws IllegalArgumentException when player already exists or when player
   *                                  location does not exist.
   */
  void addPlayer(String name, String location, boolean isHuman)
      throws NullPointerException, IllegalArgumentException;

  /**
   * Moving player to another room.
   * 
   * @param roomName room name where player should be moved.
   * @throws NullPointerException     when room name is NULL.
   * @throws IllegalArgumentException when room name does not exist or is not a
   *                                  neighbor.
   * @throws IllegalAccessException   when there are no players in the game or
   *                                  game is over.
   */
  void actionMove(String roomName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException;

  /**
   * Picking up an item by player.
   * 
   * @param itemName item name which needs to be picked by the player.
   * @throws NullPointerException     when item name is NULL.
   * @throws IllegalArgumentException when item cannot be picked up.
   * @throws IllegalAccessException   when there are no players in the game or
   *                                  game is over.
   */
  void actionPick(String itemName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException;

  /**
   * Looking around in a room by player.
   * 
   * @return room details of the room the player is in.
   * @throws IllegalAccessException when there are no players in the game or game
   *                                is over.
   */
  String actionLook() throws IllegalAccessException;

  /**
   * Displaying details of the player.
   * 
   * @param playerName player name to show details.
   * @return details of the player.
   * @throws NullPointerException     when player name is NULL.
   * @throws IllegalArgumentException when player name does not exist.
   */
  String getDetailsOfPlayer(String playerName)
      throws NullPointerException, IllegalArgumentException;

  /**
   * Get the current player who's turn it is.
   * 
   * @return the current player.
   * @throws IllegalAccessException when there are no players in the game.
   */
  String getCurrentPlayerDetails() throws IllegalAccessException;

  /**
   * Gets the list of neighbor rooms player can move in.
   * 
   * @return list of the neighbor rooms.
   * @throws IllegalAccessException when there are no players in the game.
   */
  List<String> getNeighboursToMove() throws IllegalAccessException;

  /**
   * Gets the map of items with their damage that can be picked up.
   * 
   * @return map of the items with their damage that can be picked up.
   * @throws IllegalAccessException when there are no players in the game.
   */
  Map<String, Integer> getItemsToPick() throws IllegalAccessException;

  /**
   * Checks if the current player is Human.
   * 
   * @return true if the player is human.
   * @throws IllegalAccessException when there are no players in the game.
   */
  boolean isHuman() throws IllegalAccessException;

  /**
   * Returns total number of players.
   * 
   * @return total number of players.
   */
  int getNumberOfPlayers();

  /**
   * Moving the target character's pet to the room specified by the player.
   * 
   * @param roomName room name where target's pet should be moved.
   * @throws NullPointerException     when room name is NULL.
   * @throws IllegalArgumentException when room name does not exist.
   * @throws IllegalAccessException   when there are no players in the game or
   *                                  game is over.
   */
  void actionMovePet(String roomName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException;

  /**
   * Trying to kill the target with the item of their choice or by poking target
   * in the eye.
   * 
   * @param itemName item used to kill or "poking" with finger.
   * @return boolean true when attempt to kill is successful, false otherwise.
   * @throws NullPointerException     when item name is NULL.
   * @throws IllegalArgumentException when player does not have that item.
   * @throws IllegalAccessException   when there are no players in the game or
   *                                  game is over.
   */
  boolean actionAttemptKill(String itemName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException;

  /**
   * Checks if Player A can see Player B.
   * 
   * @param a Name of player A.
   * @param b Name of player B.
   * @return true if Player A can see B, false otherwise.
   * @throws NullPointerException     when name of player A or B is NULL.
   * @throws IllegalArgumentException when player is not part of the game.
   */
  boolean canSee(String a, String b) throws NullPointerException, IllegalArgumentException;

  /**
   * Checks if current player is seen by anyone while trying to attack.
   * 
   * @return true if other players can see current player, false otherwise.
   */
  boolean isCurrentPlayerSeen();

  /**
   * Checks if the game is over.
   * 
   * @return true if the game is over, false otherwise.
   */
  boolean isGameOver();

  /**
   * Gets the list of room names.
   * 
   * @return list of the room names.
   */
  List<String> getRoomNames();

  /**
   * Gets the map of items with damage that can be used to kill doctor lucky.
   * 
   * @return map of the items with damage that can be used to kill doctor lucky.
   * @throws IllegalAccessException when there are no players in the game.
   */
  Map<String, Integer> getItemsToKill() throws IllegalAccessException;

  /**
   * Checks if the Target Character is in the same room as current player.
   * 
   * @return true if the Target Character is in the same room as current player,
   *         false otherwise.
   * @throws IllegalAccessException when player is not part of the game.
   */
  boolean isTargetPresent() throws IllegalAccessException;

  /**
   * Gets the Target Location in the Mansion.
   * 
   * @return target location.
   */
  String getTargetDetails();

  /**
   * Gets the winner of the game.
   * 
   * @return winner name.
   */
  String getWinner();

  /**
   * Gets the list of player names.
   * 
   * @return list of the player names.
   */
  List<String> getPlayerNames();

  /**
   * Returns number of turns left in the game.
   * 
   * @return number of turns left in the game.
   */
  int getNumberOfTurns();
  
  /**
   * Returns the name of current player.
   * 
   * @return name of the current player.
   * @throws IllegalAccessException   when there are no players in the game.
   */
  String getCurrentPlayerName() throws IllegalAccessException;
  
  /**
   * Returns Target Pet Location.
   * 
   * @return location of the Target Pet.
   */
  String getTargetPetLocation();
  
  /**
   * Returns Target Location.
   * 
   * @return location of the Target.
   */
  String getTargetLocation();
  
  /**
   * Returns Map of Room Name with their coordinates.
   * 
   * @return Map of Room Name with their coordinates.
   */
  Map<String, int[]> getRoomLocation();
  
  /**
   * Returns Map of Players with their location.
   * 
   * @return Map of Players with their location.
   */
  Map<String, String> getPlayerLocations();
}
