package org.jev.replayleague;

import java.util.*;
import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class JevReplayLeaguePlugin extends JavaPlugin implements CommandExecutor {
  @Override public void onEnable() { Objects.requireNonNull(getCommand("jevleague")).setExecutor(this); getLogger().info("Jev Replay League enabled in fixture-safe mode"); }
  @Override public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission("jevleague.admin")) return true;
    var observation = new LeagueCore.Observation("paper-demo", 1, 1, "Reach the beacon", Map.of("world","demo"), List.of(new LeagueCore.Action("north","Move north",.9,.1), new LeagueCore.Action("wait","Wait",0,0)));
    var decision = LeagueCore.fixtureDecision(observation);
    sender.sendMessage("Jev fixture chose " + decision.actionId() + " at " + Math.round(decision.confidence()*100) + "% — executable=" + LeagueCore.executable(observation,decision,1,.75));
    return true;
  }
}
