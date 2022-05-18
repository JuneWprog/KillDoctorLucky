package view;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import listener.MouseClickListener;

/**
 * Game play panel Implementation for the Game.
 *
 * @author komalshah
 *
 */
public final class GamePlayPanel extends JScrollPane {

  private static final long serialVersionUID = 1771899666426673157L;
  private Map<String, JPanel> roomPanels;
  private final int scale = 25;
  private final int padding = 20;
  private final int offset = 100;

  /**
   * Creates an object for game play panel.
   */
  public GamePlayPanel() {
    roomPanels = new HashMap<>();
    this.setLayout(null);
    this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
        BorderFactory.createLoweredBevelBorder()));
  }

  /**
   * Displays rooms, each room is represented as a JPanel.
   *
   * @param rooms       Map of Room Names with their location.
   * @param petLocation location of Target's Pet.
   * @param message     Result of the previous action.
   * @param listener    Listener to execute the actions.
   */
  public void displayRooms(Map<String, int[]> rooms, String petLocation, String message,
      MouseClickListener listener) {
    this.removeAll();

    for (Map.Entry<String, int[]> room : rooms.entrySet()) {
      JPanel roomPanel = new JPanel();
      // Location = { topLeftx, topLefty, bottomRightx, bottomRighty }
      int[] location = room.getValue();

      int height = (location[2] - location[0] + 1) * scale;
      int width = (location[3] - location[1] + 1) * scale;
      roomPanel.setName(room.getKey());
      roomPanel.addMouseListener(listener);
      roomPanel.setSize(new Dimension(width, height));
      roomPanel.setLocation(location[1] * scale + padding, location[0] * scale + padding + offset);
      roomPanel.setBorder(BorderFactory.createCompoundBorder(
          BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
      roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

      // if not visible(pet in room)
      if (room.getKey().equals(petLocation)) {
        roomPanel.setBackground(Color.BLACK);
      }
      JLabel roomInfo = new JLabel(room.getKey());
      roomPanel.add(roomInfo);
      roomPanels.put(room.getKey(), roomPanel);
      this.add(roomPanel);
    }
    this.revalidate();
    this.repaint();
  }

  /**
   * Displays players on the game board. Players are represented by icons.
   *
   * @param players      list of player
   * @param playerColors icon color for player
   * @param listener     Listener to execute the actions
   */
  public void displayPlayers(Map<String, String> players, Map<String, Color> playerColors,
      MouseClickListener listener) {

    for (Map.Entry<String, String> player : players.entrySet()) {
      JLabel playerLabel = new JLabel();
      Color playerColor = playerColors.get(player.getKey());
      Icon img = new Icon(new File("res/images/player-30p.png"));
      ImageIcon playerIcon = img.setIconColor(playerColor);
      playerLabel.setIcon(playerIcon);
      playerLabel.setName(player.getKey());
      playerLabel.addMouseListener(listener);
      roomPanels.get(player.getValue()).add(playerLabel);
    }
  }

  /**
   * Displays target icon on the game board.
   *
   * @param targetLocation target's location
   * @param petLocation    pet's location
   * @param listener       Listener to execute the actions
   */
  public void displayTarget(String targetLocation, String petLocation,
      MouseClickListener listener) {
    if (!targetLocation.equals(petLocation)) {
      JLabel targetLabel = new JLabel();
      ImageIcon targetIcon = new ImageIcon("res/images/target-30p.png");
      targetLabel.setIcon(targetIcon);
      targetLabel.addMouseListener(listener);
      roomPanels.get(targetLocation).add(targetLabel);
    }
  }
}
