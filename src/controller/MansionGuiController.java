package controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import listener.ButtonListener;
import listener.KeyboardListener;
import listener.MouseClickListener;
import mansion.Mansion;
import mansion.MansionBuilder;
import utils.Action;
import utils.Constants;
import view.MansionView;

/**
 * Input and Output for GUI Controller of the Mansion.
 * 
 * @author komalshah
 *
 */
public class MansionGuiController extends AbstractMansionController {
  private final MansionView view;
  private final MouseClickListener mouseListener;
  private List<String> logger;
  private Timer timer;

  /**
   * Creates an object for Mansion GUI Controller with fields Mansion object,
   * MansionBuilder object and View object.
   * 
   * @param model      the model for Mansion to play the game.
   * @param build      the builder for Mansion to play the game.
   * @param viewObject the GUI view to play the game.
   * @throws NullPointerException when model or build is NULL.
   */
  public MansionGuiController(Mansion model, MansionBuilder build, MansionView viewObject)
      throws NullPointerException {
    super(model, build);
    Objects.requireNonNull(viewObject);
    this.view = viewObject;
    this.mouseListener = new MouseClickListener();
    this.logger = new ArrayList<>();
    this.timer = new Timer(Constants.TIMER_DELAY, e -> {
      this.computerAction();
    });
    configureButtonListener();
    configureKeyboardListener();
    configureMouseClickListener();
  }

  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<>();

    buttonClickedMap.put(Constants.QUIT_GAME, () -> {
      this.quitGame();
    });

    buttonClickedMap.put(Constants.HOW_TO_PLAY_GAME, () -> {
      this.help();
    });

    buttonClickedMap.put(Constants.ABOUT_GAME, () -> {
      this.about();
    });

    buttonClickedMap.put(Constants.NEW_GAME, () -> {
      this.createNewGame();
    });

    buttonClickedMap.put(Constants.RESTART_GAME, () -> {
      this.restartGame();
    });

    buttonClickedMap.put(Constants.CREATE_NEW_GAME, () -> {
      this.newGame();
    });

    buttonClickedMap.put(Constants.ADD_PLAYER, () -> {
      this.addPlayerScreen();
    });

    buttonClickedMap.put(Constants.ADD, () -> {
      this.addPlayer();
    });

    buttonClickedMap.put(Constants.PLAY_GAME, () -> {
      timer.start();
      this.displayBoard("Play Game");
    });

    ButtonListener buttonListener = new ButtonListener();
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }

  private void configureKeyboardListener() {
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    keyPresses.put(KeyEvent.VK_P, () -> {
      try {
        if (this.mansion.isHuman()) {
          this.actionPick();
        }
      } catch (IllegalAccessException e) {
        this.view.displayError(e.getMessage());
      }
    });
    keyPresses.put(KeyEvent.VK_L, () -> {
      try {
        if (this.mansion.isHuman()) {
          this.actionLook();
        }
      } catch (IllegalAccessException e) {
        this.view.displayError(e.getMessage());
      }
    });
    keyPresses.put(KeyEvent.VK_K, () -> {
      try {
        if (this.mansion.isHuman()) {
          this.actionAttemptKill();
        }
      } catch (IllegalAccessException e) {
        this.view.displayError(e.getMessage());
      }
    });

    KeyboardListener keyListener = new KeyboardListener();
    keyListener.setKeyboardActionMap(keyPresses);
    this.view.addActionListener(keyListener);
  }

  private void configureMouseClickListener() {
    Map<Integer, Consumer<String>> mouseClickedMap = new HashMap<>();
    mouseClickedMap.put(MouseEvent.BUTTON1, (input) -> {
      if (this.mansion.getRoomNames().contains(input)) {
        try {
          if (this.mansion.isHuman()) {
            this.takeAction(Action.MOVE, input);
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      } else if (this.mansion.getPlayerNames().contains(input)) {
        this.getDetailOfPlayer(input);
      } else {
        this.getTargetDetails();
      }
    });

    mouseClickedMap.put(MouseEvent.BUTTON3, (input) -> {
      if (this.mansion.getRoomNames().contains(input)) {
        try {
          if (this.mansion.isHuman()) {
            this.takeAction(Action.MOVEPET, input);
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    });
    mouseListener.setMouseClickActionMap(mouseClickedMap);
  }

  private void help() {
    this.view.displayInfo(Constants.HELP_TIPS);
  }

  private void about() {
    this.view.displayInfo(Constants.ABOUT_STRING);
  }

  private void createNewGame() {
    int choice = this.view.confirmScreen("Are you sure you want to start a new game?");
    if (choice == JOptionPane.YES_OPTION) {
      timer.stop();
      this.view.setupScreen();
    }
  }
  
  private void displayBoard(String message) {
    logger.add(message);
    try {
      if (this.mansion.isGameOver()) {
        timer.stop();
        if (mansion.getNumberOfTurns() < 1) {
          logger.add("Game Over. Target Escaped. Nobody wins!");
          this.view.gameEndScreen("Game Over. Target Escaped. Nobody wins!");
        } else {
          logger.add(this.mansion.getWinner() + " killed the Target and Won the Game!");
          this.view
              .gameEndScreen(this.mansion.getWinner() + " killed the Target and Won the Game!");
        }
      } else {
        StringBuilder buildMessage = new StringBuilder();
        buildMessage.append("<html>");
        int start = this.logger.size() - 200 < 0 ? 0 : this.logger.size() - 200;
        for (int i = start; i < this.logger.size(); i++) {
          buildMessage.append(this.logger.get(i) + "<br>");
        }
        buildMessage.append("</html>");

        this.view.refreshBoard(this.mansion.getRoomLocation(), this.mansion.getPlayerLocations(),
            this.mansion.getTargetPetLocation(), this.mansion.getTargetLocation(),
            this.mansion.getCurrentPlayerName(), this.execute(Action.DISPLAY_TARGET),
            this.mansion.getNumberOfTurns(), buildMessage.toString(), mouseListener);
      }
    } catch (IllegalAccessException e) {
      timer.stop();
      this.view.displayError(e.getMessage());
    }
  }

  private void computerAction() {
    try {
      if (!this.mansion.isHuman()) {
        Action action = ComputerPlayerInput.chooseAction(this.mansion.isCurrentPlayerSeen(),
            this.mansion.isTargetPresent());
        switch (action) {
          case MOVE:
            try {
              takeAction(action,
                  ComputerPlayerInput.getRandomString(this.mansion.getNeighboursToMove()));
            } catch (IllegalArgumentException | NullPointerException
                | IllegalStateException exception) {
              computerAction();
            }
            break;
          case PICK:
            try {
              takeAction(action, ComputerPlayerInput.pickItemToKill(this.mansion.getItemsToPick()));
            } catch (IllegalArgumentException | NullPointerException
                | IllegalStateException exception) {
              computerAction();
            }
            break;
          case LOOK:
            try {
              String message = this.mansion.getCurrentPlayerName()
                  + " tried to look around. Looked around Successfully.";
              this.execute(Action.LOOK);
              this.displayBoard(message);
            } catch (IllegalAccessException e) {
              this.view.displayError(e.getMessage());
            }
            break;
          case MOVEPET:
            takeAction(action, ComputerPlayerInput.getRandomString(this.mansion.getRoomNames()));
            break;
          case KILL:
            takeAction(action, ComputerPlayerInput.pickItemToKill(this.mansion.getItemsToKill()));
            break;
          default:
            break;
        }
      }
    } catch (IllegalAccessException e) {
      this.view.displayError(e.getMessage());
    }
  }

  private void addPlayerScreen() {
    timer.stop();
    if (!this.mansion.isGameOver()) {
      this.view.addPlayerScreen(this.mansion.getRoomNames());
    } else {
      this.view.displayError("Game is Over!");
    }
  }

  private void takeAction(Action action, String input) {
    try {
      String message = this.mansion.getCurrentPlayerName() + " tried to " + action.toString() + " "
          + input + ".";
      String result = this.execute(action, input);
      this.displayBoard(message + " " + result);
    } catch (IllegalArgumentException | IllegalAccessException | NullPointerException exception) {
      this.view.displayError(exception.getMessage());
    }
  }

  private void actionPick() {
    try {
      String itemName = this.view.displayItems(mansion.getItemsToPick());
      if (Objects.nonNull(itemName)) {
        this.takeAction(Action.PICK, itemName);
      }
    } catch (IllegalStateException | IllegalAccessException | NullPointerException e) {
      this.view.displayError(e.getMessage());
    }
  }

  private void actionLook() {
    try {
      String message = this.mansion.getCurrentPlayerName()
          + " tried to look around. Looked around Successfully.";
      this.view.displayInfo(this.execute(Action.LOOK));
      this.displayBoard(message);
    } catch (IllegalAccessException e) {
      this.view.displayError(e.getMessage());
    }
  }

  private void actionAttemptKill() {
    try {
      if (this.mansion.isTargetPresent()) {
        String itemName = this.view.displayItems(mansion.getItemsToKill());
        if (Objects.nonNull(itemName)) {
          this.takeAction(Action.KILL, itemName);
        }
      }
    } catch (IllegalAccessException | NullPointerException e) {
      this.view.displayError(e.getMessage());
    }
  }

  private void addPlayer() {
    String[] inputs = this.view.getPlayerInput();
    if (Objects.nonNull(inputs)) {
      try {
        this.execute(Action.ADD_PLAYER, inputs);
        this.view.setPlayerColor(this.mansion.getRoomNames());
      } catch (IllegalArgumentException | NullPointerException exception) {
        this.view.displayError(exception.getMessage());
      }
    }
  }

  private void getDetailOfPlayer(String playerName) {
    this.view.displayInfo(this.execute(Action.DISPLAY_PLAYER, playerName));
  }
  
  private void getTargetDetails() {
    this.view.displayInfo(this.execute(Action.DISPLAY_TARGET));
  }

  @Override
  public void playGame() {
    this.view.welcomeScreen();
  }

  @Override
  public void restartGame() {
    int choice = this.view
        .confirmScreen("Are you sure you want to restart the game with the same settings?");
    if (choice == JOptionPane.YES_OPTION) {
      timer.stop();
      this.mansion = this.mansionBuilder.build();
      this.logger = new ArrayList<>();
      this.view.restartGame(this.mansion.getRoomNames());
    }
  }

  @Override
  public void newGame() {
    String[] inputs = this.view.getSetupInput();
    if (Objects.nonNull(inputs)) {
      try {
        MansionBuilder mb = new MansionBuilder();
        mb.readConfigFile(new FileReader(inputs[0])).setNumberOfTurns(Integer.parseInt(inputs[1]))
            .setMaximumNumberOfPlayers(Integer.parseInt(inputs[2]));
        this.mansion = mb.build();
        this.mansionBuilder = mb;
        this.logger = new ArrayList<>();
        this.view.displayInfo("New Game Created.");
        this.view.addPlayerScreen(this.mansion.getRoomNames());
      } catch (FileNotFoundException e) {
        this.view.displayError("File is not present.");
      } catch (NumberFormatException e) {
        this.view.displayError("Invalid File.");
      } catch (IllegalArgumentException | NullPointerException exception) {
        this.view.displayError(exception.getMessage());
      }
    }
  }

  @Override
  public void quitGame() {
    int choice = this.view.confirmScreen("Are you sure you want to quit?");
    if (choice == JOptionPane.YES_OPTION) {
      this.view.quit();
    }
  }
}
