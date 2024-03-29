package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import listener.ButtonListener;
import listener.KeyboardListener;
import listener.MouseClickListener;
import utils.CommonUtils;
import utils.Constants;

/**
 * GUI View Implementation for Mansion.
 *
 * @author komalshah
 */
public class MansionGuiView extends JFrame implements MansionView {
  private static final long serialVersionUID = -8525234848408899475L;
  private GameMenu menu;
  private SidePanel sidePanel;
  private GamePlayPanel gamePanel;
  private JPanel welcomePanel;
  private JPanel addPlayerPanel;
  private JPanel newGamePanel;
  private JButton button;
  private JSplitPane sp;
  private Map<String, Color> playersColor;
  private JTextField name;
  private JComboBox<String> location;
  private JTextField capacity;
  private JRadioButton computer;
  private JRadioButton human;
  private Color chosenColor;
  private JButton playGame;
  private String filePath;
  private JTextField maxPlayer;
  private JTextField turns;

  /**
   * Creates an object for the GUI of game with header.
   *
   * @param head header of the game.
   */
  public MansionGuiView(String head) {
    super(head);
    ImageIcon icon = new ImageIcon("res/images/logo.png");
    this.setIconImage(icon.getImage());
    this.setLayout(new BorderLayout());
    this.setSize(1000, 1000);
    this.setLocation(10, 10);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.playersColor = new HashMap<>();
    this.filePath = null;
    this.playGame = new JButton();

    // welcome page
    welcomePanel = new JPanel();
    button = new JButton("PLAY GAME");

    // add Player page
    addPlayerPanel = new JPanel();

    // new Game page
    newGamePanel = new JPanel();
    // menu
    this.menu = new GameMenu();
    this.setJMenuBar(this.menu);

    sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    sp.setResizeWeight(0.75);
    sp.setEnabled(false);
    sp.setDividerSize(0);

    this.gamePanel = new GamePlayPanel();
    this.gamePanel.setPreferredSize(new Dimension(1000, 1500));
    sp.add(new JScrollPane(this.gamePanel));

    this.sidePanel = new SidePanel();
    sp.add(new JScrollPane(this.sidePanel));
    this.add(sp);
  }

  private void refresh() {
    this.revalidate();
    this.repaint();
    this.setVisible(true);
  }

  @Override
  public String displayItems(Map<String, Integer> items) {
    if (items.isEmpty()) {
      this.displayInfo("No Items to Pick.");
      return null;
    }
    // convert map to string array
    String[] choices = new String[items.size()];
    int index = 0;
    for (String key : items.keySet()) {
      Integer value = items.get(key);
      choices[index] = String.format("%s: Damage %s", key, value.toString());
      ++index;
    }

    // choose an item, return item name
    String chosen = JOptionPane.showInputDialog(null, "Select One Item to Pick Up",
        "PICKUP", JOptionPane.QUESTION_MESSAGE, null, choices, null).toString();
    return Objects.nonNull(chosen) ? chosen.split(":")[0] : chosen;
  }

  @Override
  public void displayInfo(String message) {
    ImageIcon icon = new ImageIcon("res/images/logo.png");
    JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE, icon);
  }

  @Override
  public void displayError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void restartGame(List<String> roomNames) {
    this.getContentPane().removeAll();
    if (addPlayerPanel.getComponents().length == 0) {
      this.addPlayerScreen(roomNames);
    }
    this.playersColor.clear();
    this.add(addPlayerPanel);
    this.refresh();
  }

  @Override
  public void addPlayerScreen(List<String> roomNames) {
    addPlayerPanel.removeAll();
    // layout
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(0, 5, 0, 5);
    GridBagLayout grid = new GridBagLayout();
    addPlayerPanel.setLayout(grid);

    JLabel top = new JLabel("Add a Player to Game", SwingConstants.CENTER);
    top.setFont(new Font("Courier", Font.PLAIN, 25));
    top.setForeground(Color.BLUE);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.ipadx = 200;
    gbc.ipady = 20;
    addPlayerPanel.add(top, gbc);

    JLabel inputName = new JLabel("Player Name:");
    gbc.gridx = 0;
    gbc.gridy = 1;
    addPlayerPanel.add(inputName, gbc);

    name = new JTextField();
    gbc.gridx = 0;
    gbc.gridy = 2;
    addPlayerPanel.add(name, gbc);

    JLabel inputLocation = new JLabel("Start Room:");
    gbc.gridx = 0;
    gbc.gridy = 3;
    addPlayerPanel.add(inputLocation, gbc);

    location = new JComboBox<>(roomNames.toArray(new String[0]));
    gbc.gridx = 0;
    gbc.gridy = 4;
    addPlayerPanel.add(location, gbc);

    JLabel inputCapacity = new JLabel("Capacity of Player's Bag:");
    gbc.gridx = 0;
    gbc.gridy = 5;
    addPlayerPanel.add(inputCapacity, gbc);

    capacity = new JTextField();
    gbc.gridx = 0;
    gbc.gridy = 6;
    addPlayerPanel.add(capacity, gbc);

    JLabel inputType = new JLabel(" Choose type of player:");
    gbc.gridx = 0;
    gbc.gridy = 7;
    addPlayerPanel.add(inputType, gbc);

    computer = new JRadioButton("Computer Player");
    human = new JRadioButton("Human Player");
    human.setSelected(true);
    ButtonGroup group = new ButtonGroup();
    group.add(computer);
    group.add(human);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 8;
    addPlayerPanel.add(computer, gbc);
    gbc.gridx = 0;
    gbc.gridy = 9;
    addPlayerPanel.add(human, gbc);

    JButton color = new JButton("Choose Color");
    color.setFocusPainted(false);
    gbc.gridx = 0;
    gbc.gridy = 10;
    gbc.insets = new Insets(0, 0, 15, 0);
    addPlayerPanel.add(color, gbc);
    chosenColor = null;
    color.addActionListener((ActionEvent e) -> {
      chosenColor = JColorChooser.showDialog(null, "Select a color for player", Color.BLACK);
    });

    button.setText(Constants.ADD);
    button.setActionCommand(Constants.ADD);
    button.setFocusPainted(false);
    button.setBackground(null);
    button.setForeground(null);
    button.setFont(null);
    gbc.gridx = 0;
    gbc.gridy = 11;
    addPlayerPanel.add(button, gbc);

    playGame.setText(Constants.PLAY_GAME);
    playGame.setFocusPainted(false);
    gbc.gridx = 0;
    gbc.gridy = 12;
    addPlayerPanel.add(playGame, gbc);

    addPlayerPanel.setSize(500, 450);
    addPlayerPanel.setVisible(true);
    addPlayerPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    this.getContentPane().removeAll();
    this.add(addPlayerPanel);
    this.refresh();
  }

  @Override
  public String[] getPlayerInput() {
    try {
      CommonUtils.stringIsEmpty(name.getText(), "Name cannot be Empty.");
      if (capacity.getText().isBlank()) {
        capacity.setText("-1");
      } else {
        Integer.parseInt(capacity.getText());
      }
      Objects.requireNonNull(chosenColor, "Please choose a color.");
      if (playersColor.containsValue(chosenColor)) {
        throw new IllegalArgumentException("This color is already taken by other player.");
      }
      return new String[] { name.getText(), location.getSelectedItem().toString(),
          capacity.getText(), String.valueOf(human.isSelected()) };
    } catch (NumberFormatException exception) {
      this.displayError("Capacity has to be a number");
    } catch (NullPointerException | IllegalArgumentException exception) {
      this.displayError(exception.getMessage());
    }
    return null;
  }

  @Override
  public void setPlayerColor(List<String> roomNames) {
    this.playersColor.put(name.getText(), chosenColor);
    this.displayInfo("Player Added.");
    this.addPlayerScreen(roomNames);
  }

  @Override
  public void setupScreen() {
    this.playersColor.clear();
    newGamePanel.removeAll();
    this.setTitle("New Game");

    // layout
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(0, 5, 0, 5);
    GridBagLayout grid = new GridBagLayout();
    newGamePanel.setLayout(grid);

    JLabel top = new JLabel("Set Up New Game", SwingConstants.CENTER);
    top.setFont(new Font("Courier", Font.PLAIN, 25));
    top.setForeground(Color.BLUE);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.ipadx = 200;
    gbc.ipady = 20;
    newGamePanel.add(top, gbc);

    JButton inputFile = new JButton("Choose World File");
    gbc.gridx = 0;
    gbc.gridy = 1;
    inputFile.setFocusPainted(false);
    newGamePanel.add(inputFile, gbc);

    inputFile.addActionListener((ActionEvent e) -> {
      File workingDirectory = new File(System.getProperty("user.dir"));
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setFileFilter(filter);
      fileChooser.setCurrentDirectory(workingDirectory);
      fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      int returnVal = fileChooser.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        filePath = file.getPath();
      }
    });

    JLabel inputTurns = new JLabel("Number of Turns:");
    gbc.gridx = 0;
    gbc.gridy = 2;
    newGamePanel.add(inputTurns, gbc);

    turns = new JTextField();
    gbc.gridx = 0;
    gbc.gridy = 3;
    newGamePanel.add(turns, gbc);

    JLabel inputMaxPlayer = new JLabel("Maximum of Players:");
    gbc.gridx = 0;
    gbc.gridy = 4;
    newGamePanel.add(inputMaxPlayer, gbc);

    maxPlayer = new JTextField();
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.insets = new Insets(0, 0, 15, 0);
    newGamePanel.add(maxPlayer, gbc);

    button.setText(Constants.CREATE_NEW_GAME);
    button.setFocusPainted(false);
    button.setActionCommand(Constants.CREATE_NEW_GAME);
    gbc.gridx = 0;
    gbc.gridy = 6;
    newGamePanel.add(button, gbc);

    newGamePanel.setSize(500, 450);
    newGamePanel.setVisible(true);
    newGamePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    this.getContentPane().removeAll();
    this.add(newGamePanel);
    this.refresh();
  }

  @Override
  public String[] getSetupInput() {
    try {
      Objects.requireNonNull(filePath, "Please choose a file.");

      // validating turns
      if (turns.getText().isBlank()) {
        throw new IllegalArgumentException("Please input number of Turns.");
      } else {
        Integer.parseInt(turns.getText());
      }

      // validating max players
      if (maxPlayer.getText().isBlank()) {
        throw new IllegalArgumentException("Please input max number of Players.");
      } else {
        Integer.parseInt(turns.getText());
      }

      return new String[] { filePath, turns.getText(), maxPlayer.getText() };
    } catch (NumberFormatException exception) {
      this.displayError("Turns and Max number of players should be Number.");
    } catch (NullPointerException | IllegalArgumentException exception) {
      this.displayError(exception.getMessage());
    }
    return null;
  }

  @Override
  public void welcomeScreen() {
    // background
    welcomePanel.setBackground(new Color(241, 241, 241));

    // logo and style
    JLabel logo = new JLabel();
    logo.setVisible(true);
    ImageIcon icon = new ImageIcon("res/images/kill-logo.png");
    logo.setIcon(icon);
    logo.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    // button and style
    button.setBackground(new Color(241, 241, 241));
    button.setForeground(new Color(166, 75, 42));
    button.setFocusPainted(false);
    button.setFont(new Font("Serif", Font.PLAIN, 25));
    button.setActionCommand(Constants.ADD_PLAYER);

    // authors and style
    JLabel authors = new JLabel(
        "<html>By Komal Shah & Jun Wang" + "<br>dept. Computer Science"
            + "<br>Northeastern University Vancouver, Canada" + "<br>April 2022</html>",
        SwingConstants.CENTER);
    authors.setFont(new Font("Courier", Font.PLAIN, 14));
    authors.setForeground(new Color(252, 79, 79));
    authors.setVerticalTextPosition(JLabel.BOTTOM);
    authors.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    // layout
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout grid = new GridBagLayout();
    welcomePanel.setLayout(grid);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    welcomePanel.add(logo, gbc);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 1;
    welcomePanel.add(button, gbc);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.ipady = 100;
    gbc.gridx = 0;
    gbc.gridy = 2;
    welcomePanel.add(authors, gbc);

    welcomePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    welcomePanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));

    this.add(welcomePanel);
    this.setVisible(true);
  }

  @Override
  public void quit() {
    System.exit(0);
  }

  @Override
  public void addActionListener(ButtonListener buttonListener) {
    this.menu.addActionListener(buttonListener);
    button.addActionListener(buttonListener);
    playGame.addActionListener(buttonListener);
  }

  @Override
  public void addActionListener(KeyboardListener keyboardListener) {
    this.addKeyListener(keyboardListener);
  }

  @Override
  public int confirmScreen(String message) {
    return JOptionPane.showConfirmDialog(this, message, "Choose an option",
        JOptionPane.YES_NO_OPTION);
  }

  @Override
  public void refreshBoard(Map<String, int[]> rooms, Map<String, String> players,
      String petLocation, String targetLocation, String currentPlayer, String targetDetails,
      int numberOfTurns, String message, MouseClickListener listener) {
    if (playersColor.isEmpty()) {
      displayError("Please add at least one player to play the game");
      return;
    }
    this.getContentPane().removeAll();
    this.add(sp);
    this.sidePanel.displayListOfPlayers(currentPlayer, playersColor);
    this.sidePanel.displayTargetDetails(targetDetails);
    this.sidePanel.displayLog(message);
    this.sidePanel.displayTurnsLeft(numberOfTurns);
    this.gamePanel.displayRooms(rooms, petLocation, message, listener);
    this.gamePanel.displayTarget(targetLocation, petLocation, listener);
    this.gamePanel.displayPlayers(players, playersColor, listener);
    this.requestFocus();
    this.refresh();
  }

  @Override
  public void gameEndScreen(String result) {
    this.remove(sp);
    this.welcomePanel.removeAll();
    welcomePanel.setBackground(new Color(241, 241, 241));

    // logo and style
    JLabel logo = new JLabel();
    logo.setVisible(true);
    ImageIcon icon = new ImageIcon("res/images/kill-logo.png");
    logo.setIcon(icon);
    logo.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    JLabel resultLabel = new JLabel(result, SwingConstants.CENTER);
    resultLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    resultLabel.setForeground(new Color(252, 79, 79));
    resultLabel.setVerticalTextPosition(JLabel.BOTTOM);
    resultLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    // layout
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout grid = new GridBagLayout();
    welcomePanel.setLayout(grid);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    welcomePanel.add(logo, gbc);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.ipady = 100;
    gbc.gridx = 0;
    gbc.gridy = 1;
    welcomePanel.add(resultLabel, gbc);

    welcomePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    welcomePanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));

    this.add(welcomePanel);
    this.revalidate();
    this.repaint();
    this.setVisible(true);
  }
}
