package view;

import java.util.Objects;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import listener.ButtonListener;
import utils.Constants;

/**
 * Menu Implementation for the Game.
 *
 * @author komalshah
 *
 */
public final class GameMenu extends JMenuBar {

  private static final long serialVersionUID = -2177760489781214520L;

  private JMenu menu;
  private JMenu help;
  private JMenuItem newGame;
  private JMenuItem restartGame;
  private JMenuItem addPlayer;
  private JMenuItem quitGame;
  private JMenuItem aboutGame;
  private JMenuItem howToPlay;

  /**
   * Creates an object for the Menu of Game.
   */
  public GameMenu() {
    this.menu = new JMenu("Game");

    this.newGame = new JMenuItem(Constants.NEW_GAME);
    this.newGame.setActionCommand(Constants.NEW_GAME);

    this.restartGame = new JMenuItem(Constants.RESTART_GAME);
    this.restartGame.setActionCommand(Constants.RESTART_GAME);

    this.quitGame = new JMenuItem(Constants.QUIT_GAME);
    this.quitGame.setActionCommand(Constants.QUIT_GAME);

    this.addPlayer = new JMenuItem(Constants.ADD_PLAYER);
    this.addPlayer.setActionCommand(Constants.ADD_PLAYER);

    this.menu.add(this.newGame);
    this.menu.add(this.restartGame);
    this.menu.add(this.addPlayer);
    this.menu.add(this.quitGame);

    this.help = new JMenu("Help");

    this.aboutGame = new JMenuItem(Constants.ABOUT_GAME);
    this.aboutGame.setActionCommand(Constants.ABOUT_GAME);

    this.howToPlay = new JMenuItem(Constants.HOW_TO_PLAY_GAME);
    this.howToPlay.setActionCommand(Constants.HOW_TO_PLAY_GAME);

    this.help.add(this.aboutGame);
    this.help.add(this.howToPlay);

    this.add(this.menu);
    this.add(this.help);
  }

  /**
   * Adds button action listener on the menu.
   *
   * @param buttonListener button listener.
   * @throws NullPointerException when button listener object is NULL.
   */
  public void addActionListener(ButtonListener buttonListener) {
    Objects.requireNonNull(buttonListener);
    this.newGame.addActionListener(buttonListener);
    this.restartGame.addActionListener(buttonListener);
    this.addPlayer.addActionListener(buttonListener);
    this.quitGame.addActionListener(buttonListener);
    this.aboutGame.addActionListener(buttonListener);
    this.howToPlay.addActionListener(buttonListener);
  }
}
