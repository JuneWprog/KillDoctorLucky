package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;


/**
 * Side panel Implementation for the Game.
 * 
 * @author komalshah
 *
 */
public final class SidePanel extends JPanel {

  private static final long serialVersionUID = -6069712620217509908L;
  private JPanel playersPanel;
  private JPanel targetPanel;
  private JPanel logPanel;
  private JPanel turnPanel;
  private JScrollPane scrollPlayers;
  private JScrollPane scrollTarget;
  private JScrollPane scrollLog;
  private JScrollPane scrollTurn;
  private GridBagConstraints constraint;


  /**
   * Creates an object for side panel of the game.
   */
  public SidePanel() {
    this.constraint = new GridBagConstraints();
    this.constraint.gridwidth = GridBagConstraints.REMAINDER;
    this.setLayout(new BorderLayout());

    this.turnPanel = new JPanel();
    this.scrollTurn = new JScrollPane(this.turnPanel);

    this.playersPanel = new JPanel();
    this.playersPanel.setLayout(new GridBagLayout());
    this.scrollPlayers = new JScrollPane(this.playersPanel);
    this.scrollPlayers.setPreferredSize(new Dimension(100, 450));

    this.targetPanel = new JPanel();
    this.targetPanel.setLayout(new GridBagLayout());
    this.scrollTarget = new JScrollPane(this.targetPanel);
    this.scrollTarget.setPreferredSize(new Dimension(100, 100));


    this.logPanel = new JPanel();
    this.logPanel.setLayout(new GridBagLayout());
    this.scrollLog = new JScrollPane(this.logPanel);
    this.scrollLog.setPreferredSize(new Dimension(100, 60));


    JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollTurn, scrollPlayers);
    sp.setEnabled(false);
    sp.setDividerSize(0);

    JSplitPane sp2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sp, scrollTarget);
    sp2.setEnabled(false);
    sp2.setDividerSize(0);

    JSplitPane sp3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sp2, scrollLog);
    sp2.setEnabled(false);
    sp2.setDividerSize(0);

    this.add(sp3);
    this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
        BorderFactory.createLoweredBevelBorder()));
  }

  /**
   * Displays turns left.
   * @param numberOfTurns turns left.
   */
  public void displayTurnsLeft(int numberOfTurns) {
    turnPanel.removeAll();
    JLabel turns = new JLabel("Turns Left: " + numberOfTurns);
    turns.setFont(new Font(turns.getFont().getFontName(), Font.BOLD, 18));
    turns.setForeground(Color.RED);
    turnPanel.add(turns);
    turnPanel.revalidate();
    turnPanel.repaint();
  }

  /**
   * Displays the list of players in the game and highlights the current player.
   *
   * @param currentPlayer Current Player
   * @param playersColor  Map of Player names with their color representation.
   */
  public void displayListOfPlayers(String currentPlayer, Map<String, Color> playersColor) {
    this.playersPanel.removeAll();

    JLabel head = new JLabel("List of Players");
    head.setFont(new Font(head.getFont().getFontName(), Font.BOLD, head.getFont().getSize()));
    this.playersPanel.add(head, constraint);
    this.playersPanel.add(Box.createVerticalStrut(50));
    for (String player : playersColor.keySet()) {
      JLabel p = new JLabel(player);
      Icon img = new Icon(new File("res/images/player-30p.png"));
      ImageIcon playerIcon = img.setIconColor(playersColor.get(player));
      p.setIcon(playerIcon);
      p.setHorizontalAlignment(JLabel.LEFT);
      p.setVerticalAlignment(JLabel.TOP);
      p.setPreferredSize(new Dimension(200, 35));
      this.playersPanel.add(p, constraint);
      this.playersPanel.add(Box.createVerticalStrut(40));
      if (player.equals(currentPlayer)) {
        p.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
      }
    }
    this.playersPanel.revalidate();
    this.playersPanel.repaint();
  }

  /**
   * Displays target details in the side panel.
   *
   * @param targetDetails details of Target.
   */
  public void displayTargetDetails(String targetDetails) {
    this.targetPanel.removeAll();
    JLabel head = new JLabel("Target Details");
    head.setFont(new Font(head.getFont().getFontName(), Font.BOLD, head.getFont().getSize()));
    ImageIcon img = new ImageIcon("res/images/target-30p.png");
    String target = "<html>"
        + targetDetails.replaceAll("\n", "<br/>")
        .replaceAll("Target Details:", "") + "</html>";
    JLabel targetLabel = new JLabel(target);
    targetLabel.setIcon(img);
    this.targetPanel.add(head, constraint);
    this.targetPanel.add(targetLabel, constraint);
    this.targetPanel.revalidate();
    this.targetPanel.repaint();
  }

  /**
   * Displays the game log on the game board.
   *
   * @param message information of game status.
   */

  public void displayLog(String message) {
    logPanel.removeAll();
    JLabel logLabel = new JLabel(message);
    logPanel.add(logLabel);
    this.logPanel.revalidate();
    this.logPanel.repaint();

  }
}
