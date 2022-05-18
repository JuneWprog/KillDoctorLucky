package controller;

import command.AddPlayerCommand;
import command.AttemptKillCommand;
import command.CanSeeCommand;
import command.GraphicalRepresentationCommand;
import command.LookCommand;
import command.MansionCommand;
import command.MoveCommand;
import command.MovePetCommand;
import command.PickCommand;
import command.PlayerDetailsCommand;
import command.RoomDetailsCommand;
import command.TargetDetailsCommand;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import mansion.Mansion;
import mansion.MansionBuilder;
import utils.Action;

/**
 * AbstractMansionController gets input and executes the command by making a
 * call to Model. This is implementation of Command Design Pattern for the game
 * where each action represents a command.
 * 
 * @author komalshah
 *
 */
public abstract class AbstractMansionController implements MansionController {
  protected Mansion mansion;
  protected MansionBuilder mansionBuilder;
  private final Map<Action, Function<String[], MansionCommand>> operations;

  /**
   * Creates object for AbstractMansionController with Model.
   * 
   * @param model Mansion Model.
   * @param build the builder for Mansion to play the game.
   * @throws NullPointerException when Model and builder object is NULL.
   */
  protected AbstractMansionController(Mansion model, MansionBuilder build)
      throws NullPointerException {
    Objects.requireNonNull(model);
    Objects.requireNonNull(build);
    this.mansion = model;
    this.mansionBuilder = build;
    this.operations = new HashMap<>();
    this.operations.put(Action.DISPLAY_ROOM, s -> new RoomDetailsCommand(s[0]));
    this.operations.put(Action.CREATE_IMAGE, s -> new GraphicalRepresentationCommand());
    this.operations.put(Action.ADD_PLAYER,
        s -> new AddPlayerCommand(s[0], s[1], Integer.parseInt(s[2]), Boolean.parseBoolean(s[3])));
    this.operations.put(Action.MOVE, s -> new MoveCommand(s[0]));
    this.operations.put(Action.PICK, s -> new PickCommand(s[0]));
    this.operations.put(Action.LOOK, s -> new LookCommand());
    this.operations.put(Action.MOVEPET, s -> new MovePetCommand(s[0]));
    this.operations.put(Action.KILL, s -> new AttemptKillCommand(s[0]));
    this.operations.put(Action.DISPLAY_PLAYER, s -> new PlayerDetailsCommand(s[0]));
    this.operations.put(Action.CAN_SEE, s -> new CanSeeCommand(s[0], s[1]));
    this.operations.put(Action.DISPLAY_TARGET, s -> new TargetDetailsCommand());
  }

  @Override
  public String execute(Action action, String... varargs)
      throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(action);
    Objects.requireNonNull(varargs);
    return operations.get(action).apply(varargs).execute(mansion);
  }

}
