package listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Mouse Click Listener Implementation for the GUI.
 * 
 * @author komalshah
 *
 */
public class MouseClickListener extends MouseAdapter {
  private Map<Integer, Consumer<String>> map;

  /**
   * Creates object for MouseClickListener.
   */
  public MouseClickListener() {
    this.map = new HashMap<>();
  }

  /**
   * Set the map for mouse click events. Mouse click events in Java Swing are
   * referred using String component names.
   * 
   * @param actionMap the actions for mouse click.
   */
  public void setMouseClickActionMap(Map<Integer, Consumer<String>> actionMap) {
    this.map = actionMap;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    map.get(e.getButton()).accept(e.getComponent().getName());
  }
}
