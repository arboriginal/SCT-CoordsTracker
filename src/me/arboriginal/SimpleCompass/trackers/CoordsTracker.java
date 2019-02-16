package me.arboriginal.SimpleCompass.trackers;

import java.util.List;
import org.bukkit.entity.Player;
import com.google.common.collect.ImmutableMap;
import me.arboriginal.SimpleCompass.plugin.AbstractTracker;
import me.arboriginal.SimpleCompass.plugin.SimpleCompass;

public class CoordsTracker extends AbstractTracker {
  // ----------------------------------------------------------------------------------------------
  // Constructor methods
  // ----------------------------------------------------------------------------------------------

  public CoordsTracker(SimpleCompass plugin) {
    super(plugin);
  }

  // ----------------------------------------------------------------------------------------------
  // Tracker methods
  // ----------------------------------------------------------------------------------------------

  @Override
  public String github() {
    return "arboriginal/SCT-CoordsTracker";
  }

  @Override
  public String trackerID() {
    return "COORDS";
  }

  @Override
  public String version() {
    return "4";
  }

  // ----------------------------------------------------------------------------------------------
  // Actions methods
  // ----------------------------------------------------------------------------------------------

  @Override
  public List<TrackingActions> getActionsAvailable(Player player, boolean keepUnavailable) {
    List<TrackingActions> list = super.getActionsAvailable(player, keepUnavailable);

    list.add(TrackingActions.ADD);

    if (keepUnavailable || !list(player, null, "").isEmpty()) {
      list.add(TrackingActions.DEL);
      list.add(TrackingActions.START);
      list.add(TrackingActions.STOP);
    }

    return list;
  }

  @Override
  public TargetSelector requireTarget(TrackingActions action) {
    return action.equals(TrackingActions.ADD) ? TargetSelector.NEWCOORDS : super.requireTarget(action);
  }

  // ----------------------------------------------------------------------------------------------
  // Command methods
  // ----------------------------------------------------------------------------------------------

  @Override
  public boolean perform(Player player, String command, TrackingActions action, String target, String[] args) {
    if (target == null && !action.equals(TrackingActions.ADD)) {
      sendMessage(player, "missing_target");
      return true;
    }

    switch (action) {
      case ADD:
        if (args.length < 3)
          sendMessage(player, "missing_target");
        else if (set(player, args[2], getCoords(player, args))) {
          sendMessage(player, "ADD", ImmutableMap.of("target", args[2]));
          if (settings.getBoolean("settings.auto_activated")) activate(player, args[2], false);
        }
        else
          sendMessage(player, "target_exists", ImmutableMap.of("target", args[2]));
        break;

      case DEL:
        sendMessage(player, del(player, args[2]) ? "DEL" : "target_not_found", ImmutableMap.of("target", args[2]));
        break;

      case START:
        if (!activate(player, args[2], true)) break;
        sendMessage(player, "START", ImmutableMap.of("target", args[2]));
        break;

      case STOP:
        disable(player, args[2]);
        sendMessage(player, "STOP", ImmutableMap.of("target", args[2]));
        break;

      default:
        return false;
    }

    return true;
  }
}
