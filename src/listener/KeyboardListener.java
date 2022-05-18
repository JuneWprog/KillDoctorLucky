package listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Key Listener Implementation for the GUI.
 * 
 * @author komalshah
 *
 */
public class KeyboardListener extends KeyAdapter {
  private Map<Integer, Runnable> map;
  
  /**
   * Creates an object for Keyboard Listener.
   */
  public KeyboardListener() {
    this.map = new HashMap<>();
  }
  
  /**
   * Set the map for key press events. Key press events in Java Swing are
   * integer codes.
   * 
   * @param actionMap the actions for key press.
   */
  public void setKeyboardActionMap(Map<Integer, Runnable> actionMap) {
    this.map = actionMap;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (map.containsKey(e.getKeyCode())) {
      map.get(e.getKeyCode()).run();
    }
  }

}
