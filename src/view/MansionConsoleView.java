package view;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import listener.ButtonListener;
import listener.KeyboardListener;
import listener.MouseClickListener;

/**
 * Console View Implementation for Mansion.
 * 
 * @author komalshah
 *
 */
public class MansionConsoleView implements MansionView {
  private final Appendable out;

  /**
   * Creates an object for Mansion Console View with fields Appendable.
   * 
   * @param output the target to print to.
   * @throws NullPointerException when output is NULL.
   */
  public MansionConsoleView(Appendable output) throws NullPointerException {
    Objects.requireNonNull(output);
    this.out = output;
  }

  private void println(String message) throws IllegalStateException {
    try {
      this.out.append(message + "\n");
    } catch (IOException exception) {
      throw new IllegalStateException("Append failed", exception);
    }
  }

  @Override
  public String displayItems(Map<String, Integer> items) {
    println("\nList of Items Available with Damage = " + items.toString());
    return null;
  }

  @Override
  public void displayInfo(String message) {
    println(message);
  }

  @Override
  public void displayError(String message) {
    println(message);
  }

  @Override
  public void restartGame(List<String> roomNames) {
    println("Restart Successful.");
  }

  @Override
  public void addPlayerScreen(List<String> roomNames) {
    println("Player Added successfully.");
  }

  @Override
  public void setupScreen() {
    throw new UnsupportedOperationException(
        "No new world specification can be setup in console view.");
  }

  @Override
  public void refreshBoard(Map<String, int[]> rooms, Map<String, String> players,
      String petLocation, String targetLocation, String currentPlayer, String targetDetails,
      int numberOfTurns, String message, MouseClickListener listener) {
    throw new UnsupportedOperationException("There is no refresh needed in console view.");
  }

  @Override
  public void welcomeScreen() {
    println("\nSelect the operation to perform:");
    println("1. Display Information of a Room");
    println("2. Create Graphical Representation for the World/Mansion");
    println("3. Add a Human Player");
    println("4. Add a Computer Player");
    println("5. Play Game");
    println("6. Display Information of a Player");
    println("7. Determine if Player A can see Player B");
    println("8. Restart Game");
    println("9. New Game");
    println("10. Quit");
  }

  @Override
  public void quit() {
    println("System Terminated.");
    return;
  }

  @Override
  public void addActionListener(ButtonListener buttonListener) {
    throw new UnsupportedOperationException("Listeners cannot be added on console view.");
  }

  @Override
  public void addActionListener(KeyboardListener keyboardListener) {
    throw new UnsupportedOperationException("Listeners cannot be added on console view.");
  }

  @Override
  public int confirmScreen(String message) {
    throw new UnsupportedOperationException("There is no confirmation screen in Text based View.");
  }

  @Override
  public String[] getPlayerInput() {
    throw new UnsupportedOperationException("Input is handled using Scanner.");
  }

  @Override
  public void setPlayerColor(List<String> roomNames) {
    throw new UnsupportedOperationException(
        "Players do not have color representation in Text based View.");
  }

  @Override
  public String[] getSetupInput() {
    throw new UnsupportedOperationException("Input is handled using Scanner.");
  }

  @Override
  public void gameEndScreen(String result) {
    println(result);
  }

}
