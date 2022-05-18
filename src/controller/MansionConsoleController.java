package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import mansion.Mansion;
import mansion.MansionBuilder;
import utils.Action;
import utils.Constants;
import view.MansionView;

/**
 * Input and Output for Console Controller of the Mansion.
 * 
 * @author komalshah
 *
 */
public class MansionConsoleController extends AbstractMansionController {
  private final Scanner scan;
  private final MansionView view;

  /**
   * Creates an object for Mansion Console Controller with fields Readable, Model
   * and View.
   * 
   * @param in      the source to read from.
   * @param model   the model for Mansion to play the game.
   * @param build   the builder for Mansion to play the game.
   * @param viewObj the view for console.
   * @throws NullPointerException when in, model, builder, viewObj is NULL.
   */
  public MansionConsoleController(Readable in, Mansion model, MansionBuilder build,
      MansionView viewObj) throws NullPointerException {
    super(model, build);
    Objects.requireNonNull(in);
    Objects.requireNonNull(viewObj);
    this.scan = new Scanner(in);
    this.view = viewObj;
  }

  private void getDetailsOfRoom() throws IllegalStateException, NoSuchElementException {
    try {
      this.view.displayInfo("Available Rooms = " + this.mansion.getRoomNames() + "\n");
      this.view.displayInfo("\nEnter name of the room to get it's details:");
      String roomName = this.scan.nextLine().trim();
      this.view.displayInfo(this.execute(Action.DISPLAY_ROOM, roomName));
    } catch (IllegalArgumentException | NullPointerException exception) {
      this.view.displayError(exception.getMessage());
      this.view.displayInfo("Please enter correct room name.");
      this.getDetailsOfRoom();
    }
  }

  private void createGraphicalRepresentation() throws IllegalStateException {
    this.view.displayInfo("\nSaving the World Map as myImage.png at the root level...");
    this.view.displayInfo(this.execute(Action.CREATE_IMAGE));
  }

  private void addPlayer(boolean isHuman) throws IllegalStateException, NoSuchElementException {
    try {
      String playerName = null;
      this.view.displayInfo("\nEnter Player Name:");
      playerName = scan.nextLine().trim();
      this.view.displayInfo("\nList of Rooms to Add Player: " + this.mansion.getRoomNames());
      this.view.displayInfo("Enter Player Starting Location:");
      String roomName = scan.nextLine().trim();
      String capacity = null;
      this.view.displayInfo(
          "\nDoes this player has limited capacity to carry items (yes/any other key: no):");
      String capacityPrompt = scan.nextLine().trim();
      if (Constants.YES.equalsIgnoreCase(capacityPrompt)
          || Constants.CONST_Y.equalsIgnoreCase(capacityPrompt)) {
        this.view.displayInfo("\nEnter capacity for this player:");
        capacity = scan.nextLine().trim();
      } else {
        capacity = "-1";
      }
      this.execute(Action.ADD_PLAYER, playerName, roomName, capacity, String.valueOf(isHuman));
      this.view.addPlayerScreen(this.mansion.getRoomNames());
    } catch (NumberFormatException e) {
      this.view.displayError("Capacity should be a number.");
      this.view.displayError("Please enter correct player details.");
      this.addPlayer(isHuman);
    } catch (IllegalArgumentException | NullPointerException exception) {
      this.view.displayError(exception.getMessage());
      this.view.displayError("Please enter correct player details.");
      this.addPlayer(isHuman);
    }
  }

  private void actionMove(boolean isHuman) throws IllegalStateException, NoSuchElementException {
    try {
      List<String> roomNames = this.mansion.getNeighboursToMove();
      if (roomNames.size() == 0) {
        if (!isHuman) {
          this.view.displayInfo("\nComputer chose to Move.");
        }
        this.view.displayInfo("\nNo Neighbors to move to.");
        return;
      }
      String roomName = null;
      if (isHuman) {
        this.view.displayInfo("\nList of Rooms available to Move: " + roomNames);
        this.view.displayInfo("\nEnter name of the room to move:");
        roomName = this.scan.nextLine().trim();
      } else {
        roomName = ComputerPlayerInput.getRandomString(roomNames);
        this.view.displayInfo(String.format("\nComputer chose to Move to Room: %s", roomName));
      }
      this.view.displayInfo(this.execute(Action.MOVE, roomName));
    } catch (IllegalArgumentException | NullPointerException exception) {
      this.view.displayError(String.format(
          "%s. Please ensure that room name entered is a neighbor.", exception.getMessage()));
      this.actionMove(isHuman);
    } catch (IllegalAccessException exception) {
      this.view.displayError(exception.getMessage());
    }
  }

  private void actionPick(boolean isHuman) throws IllegalStateException, NoSuchElementException {
    try {
      Map<String, Integer> items = this.mansion.getItemsToPick();
      if (items.size() == 0) {
        if (!isHuman) {
          this.view.displayInfo("\nComputer chose to Pick.");
        }
        this.view.displayInfo("\nNo Items to pick up.");
        return;
      }
      String input = null;
      String output = null;
      if (isHuman) {
        this.view.displayItems(items);
        this.view.displayInfo("\nEnter item from the room to pick:");
        input = this.scan.nextLine().trim();
      } else {
        input = ComputerPlayerInput.pickItemToKill(items);
        this.view.displayInfo(String.format("\nComputer chose to Pick Item: %s", input));
      }
      try {
        output = this.execute(Action.PICK, input);
      } catch (IllegalStateException exception) {
        this.view.displayError(exception.getMessage());
        return;
      }
      this.view.displayInfo(output);
    } catch (IllegalArgumentException | NullPointerException exception) {
      this.view.displayError(String.format(
          "%s. Please ensure that item name entered is in the same room.", exception.getMessage()));
      this.actionPick(isHuman);
    } catch (IllegalAccessException exception) {
      this.view.displayError(exception.getMessage());
    }
  }

  private void actionLook(boolean isHuman) throws IllegalStateException {
    if (!isHuman) {
      this.view.displayInfo("Computer Player chose to Look around.");
    }
    this.view.displayInfo("");
    this.view.displayInfo(this.execute(Action.LOOK));
  }

  private void actionMovePet(boolean isHuman) throws IllegalStateException, NoSuchElementException {
    try {
      List<String> roomNames = this.mansion.getRoomNames();
      String input = null;
      if (isHuman) {
        this.view.displayInfo("\nList of Rooms available to Move the Pet: " + roomNames);
        this.view.displayInfo("\nEnter name of the room to move the pet:");
        input = this.scan.nextLine().trim();
      } else {
        input = ComputerPlayerInput.getRandomString(roomNames);
        this.view.displayInfo(String.format("\nComputer chose to Move the Pet to Room: %s", input));
      }
      this.view.displayInfo(this.execute(Action.MOVEPET, input));
    } catch (IllegalArgumentException | NullPointerException exception) {
      this.view.displayError(
          String.format("%s Please ensure that room name entered is present in Mansion.",
              exception.getMessage()));
      this.actionMovePet(isHuman);
    }
  }

  private void actionAttemptKill(boolean isHuman)
      throws IllegalStateException, NoSuchElementException {
    try {
      Map<String, Integer> itemNames = this.mansion.getItemsToKill();
      String input = null;
      if (isHuman) {
        this.view.displayItems(itemNames);
        this.view.displayInfo("\nEnter item name to be used to kill the Target:");
        input = this.scan.nextLine().trim();
      } else {
        input = ComputerPlayerInput.pickItemToKill(itemNames);
        this.view
            .displayInfo(String.format("\nComputer chose to Kill Target with item: %s", input));
      }
      this.view.displayInfo(this.execute(Action.KILL, input));
    } catch (IllegalArgumentException | NullPointerException exception) {
      this.view.displayInfo(String.format("%s Please ensure that item name entered is with you.",
          exception.getMessage()));
      this.actionAttemptKill(isHuman);
    } catch (IllegalAccessException exception) {
      this.view.displayInfo(exception.getMessage());
    }
  }

  private void getDetailsOfPlayer() throws IllegalStateException, NoSuchElementException {
    try {
      this.view.displayInfo("Available Players = " + this.mansion.getPlayerNames());
      this.view.displayInfo("\n\nEnter name of the player to get it's details:");
      String playerName = scan.nextLine().trim();
      this.view.displayInfo(this.execute(Action.DISPLAY_PLAYER, playerName));
    } catch (IllegalArgumentException | NullPointerException exception) {
      this.view.displayError(exception.getMessage());
      this.view.displayError("Please enter correct player name.");
      this.getDetailsOfPlayer();
    }
  }

  private void canSee() throws IllegalStateException, NoSuchElementException {
    try {
      this.view.displayInfo("Available Players = " + this.mansion.getPlayerNames());
      this.view.displayInfo("\nEnter Player A Name:");
      String playerNameA = scan.nextLine().trim();
      this.view.displayInfo("\nEnter Player B Name:");
      String playerNameB = scan.nextLine().trim();
      this.view.displayInfo(this.execute(Action.CAN_SEE, playerNameA, playerNameB));
    } catch (IllegalArgumentException | NullPointerException exception) {
      this.view.displayError(exception.getMessage());
      this.view.displayError("Please enter correct player name.");
      this.canSee();
    }
  }

  private void displayDetailsBeforeTurn() throws IllegalStateException {
    try {
      this.view.displayInfo("\n--------------------------------------------------");
      this.view.displayInfo(this.execute(Action.DISPLAY_TARGET) + "\n");
      this.view
          .displayInfo(this.execute(Action.DISPLAY_PLAYER, this.mansion.getCurrentPlayerName()));
      this.view.displayInfo("\nNumber of Turns left: " + mansion.getNumberOfTurns());
      this.view.displayInfo("--------------------------------------------------");
    } catch (IllegalAccessException e) {
      this.view.displayError(e.getMessage());
    }
  }

  private void gamePlay() throws IllegalStateException, NoSuchElementException {
    while (true) {
      if (this.mansion.isGameOver()) {
        if (mansion.getNumberOfTurns() < 1) {
          this.view.gameEndScreen("\nGame Over. Target Escaped. Nobody wins!");
          return;
        }
        this.view.gameEndScreen("\nGame Over. Target Killed.");
        this.view.gameEndScreen(this.mansion.getWinner() + " killed the Target and Won the Game!");
        return;
      }
      boolean isHuman = false;
      boolean targetPresent = false;
      try {
        isHuman = this.mansion.isHuman();
        targetPresent = this.mansion.isTargetPresent();
      } catch (IllegalAccessException e) {
        this.view.displayInfo(e.getMessage());
      }

      displayDetailsBeforeTurn();

      this.view.displayInfo("\nSelect the operation to perform:");
      this.view.displayInfo("BACK - Return to Main Menu");
      this.view.displayInfo("MOVE - Move to a neighboring Room");
      this.view.displayInfo("PICK - Pick an Item from the Room");
      this.view.displayInfo("LOOK - Look around the Room");
      this.view.displayInfo("MOVEPET - Move the Target's Pet");
      if (targetPresent) {
        this.view.displayInfo("KILL - Attempt to kill the Target");
      }

      Action choice = Action.DISPLAY_ROOM;
      if (isHuman) {
        try {
          choice = Action.valueOf(scan.nextLine().trim().toUpperCase());
        } catch (IllegalArgumentException exception) {
          this.view.displayInfo("Incorrect input.");
        }
      } else {
        choice = ComputerPlayerInput.chooseAction(this.mansion.isCurrentPlayerSeen(),
            targetPresent);
      }

      switch (choice) {
        case BACK:
          return;
        case MOVE:
          actionMove(isHuman);
          break;
        case PICK:
          actionPick(isHuman);
          break;
        case LOOK:
          actionLook(isHuman);
          break;
        case MOVEPET:
          actionMovePet(isHuman);
          break;
        case KILL:
          if (targetPresent) {
            actionAttemptKill(isHuman);
          } else {
            this.view.displayInfo("Enter Choice from the Menu.");
          }
          break;
        default:
          this.view.displayInfo("Enter Choice from the Menu.");
      }
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        this.view.displayInfo(e.getMessage());
      }
    }
  }

  @Override
  public void playGame() throws IllegalStateException, NoSuchElementException {
    while (true) {
      this.view.welcomeScreen();
      int choice = 0;
      try {
        choice = Integer.parseInt(scan.nextLine().trim());
      } catch (NumberFormatException exception) {
        this.view.displayInfo("Incorrect input.");
      }

      switch (choice) {
        case 1:
          getDetailsOfRoom();
          break;
        case 2:
          createGraphicalRepresentation();
          break;
        case 3:
          addPlayer(true);
          break;
        case 4:
          addPlayer(false);
          break;
        case 5:
          if (mansion.getNumberOfPlayers() != 0) {
            gamePlay();
          } else {
            this.view.displayInfo("Please add players to play the game.");
          }
          break;
        case 6:
          if (mansion.getNumberOfPlayers() != 0) {
            getDetailsOfPlayer();
          } else {
            this.view.displayInfo("No player exist.");
          }
          break;
        case 7:
          if (mansion.getNumberOfPlayers() > 1) {
            canSee();
          } else {
            this.view.displayInfo("No player exists.");
          }
          break;
        case 8:
          this.restartGame();
          break;
        case 9:
          this.newGame();
          break;
        case 10:
          this.quitGame();
          return;
        default:
          this.view.displayInfo("Enter Choice as Number from 1 to 10.");
      }
    }
  }

  @Override
  public void restartGame() {
    this.view.displayInfo("\nAre you sure you want to restart? (y/yes):");
    String input = scan.nextLine().trim();
    if (Constants.YES.equalsIgnoreCase(input) || Constants.CONST_Y.equalsIgnoreCase(input)) {
      this.mansion = this.mansionBuilder.build();
      this.view.restartGame(this.mansion.getRoomNames());
    } else {
      this.view.displayInfo("Restart Game Cancelled.");
    }
  }

  @Override
  public void newGame() {
    this.view.displayInfo("\nAre you sure you want to start a new game? (y/yes):");
    String input = scan.nextLine().trim();
    if (Constants.YES.equalsIgnoreCase(input) || Constants.CONST_Y.equalsIgnoreCase(input)) {
      try {
        this.view.displayInfo("\nEnter the New Configuration File Path:");
        String filePath = scan.nextLine().trim();
        this.view.displayInfo("Enter Max Number of Turns for the Game:");
        int turns = Integer.parseInt(scan.nextLine().trim());
        this.view.displayInfo("Enter Max Number of Players for the Game:");
        int maxPlayers = Integer.parseInt(scan.nextLine().trim());

        MansionBuilder mb = new MansionBuilder();
        mb.readConfigFile(new FileReader(filePath)).setNumberOfTurns(turns)
            .setMaximumNumberOfPlayers(maxPlayers);
        this.mansion = mb.build();
        this.mansionBuilder = mb;
        this.view.displayInfo("New Game Created.");
      } catch (NumberFormatException e) {
        this.view.displayError("Turns and Max number Players should be Number");
      } catch (FileNotFoundException e) {
        this.view.displayError("Incorrect File path");
      } catch (IllegalArgumentException | NullPointerException e) {
        this.view.displayError("New Game not created. Invalid input file.");
      }
    } else {
      this.view.displayInfo("New Game Cancelled.");
    }
  }

  @Override
  public void quitGame() {
    scan.close();
    this.view.quit();
  }
}
