package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Button Listener Implementation for the GUI.
 * 
 * @author komalshah
 *
 */
public class ButtonListener implements ActionListener {
  private Map<String, Runnable> map;
  
  /**
   * Creates object for ButtonListener.
   */
  public ButtonListener() {
    this.map = new HashMap<>();
  }
  
  /**
   * Set the map for button click events. Button click events in Java Swing are
   * referred using String action commands.
   * 
   * @param actionMap the actions for button click.
   */
  public void setButtonClickedActionMap(Map<String, Runnable> actionMap) {
    this.map = actionMap;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (map.containsKey(e.getActionCommand())) {
      map.get(e.getActionCommand()).run();
    }
  }
}
