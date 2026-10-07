package org.jev.replayleague;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

public final class LeagueCore {
  private LeagueCore() {}
  public record Action(String id, String label, double simulatedProgress, double risk) {}
  public record Observation(String runId, int step, long revision, String goal, Map<String,Object> state, List<Action> legalActions) {}
  public record Decision(String actionId, double confidence, long revision) {}
  public record Score(int progress, int loops, int calls, int interventions) {}

  public static Observation validate(Observation value) {
    if (value == null || value.runId().isBlank() || value.goal().isBlank() || value.legalActions().isEmpty() || value.legalActions().size() > 255) throw new IllegalArgumentException("Invalid observation");
    if (value.legalActions().stream().map(Action::id).distinct().count() != value.legalActions().size()) throw new IllegalArgumentException("Action ids must be unique");
    return value;
  }

  public static Decision fixtureDecision(Observation observation) {
    validate(observation); Action best = observation.legalActions().stream().max(Comparator.comparingDouble(Action::simulatedProgress).thenComparingDouble(a -> -a.risk())).orElseThrow();
    return new Decision(best.id(), .9, observation.revision());
  }

  public static boolean executable(Observation observation, Decision decision, long currentRevision, double threshold) {
    return observation.revision() == currentRevision && decision.revision() == currentRevision && decision.confidence() >= threshold && observation.legalActions().stream().anyMatch(action -> action.id().equals(decision.actionId()));
  }

  public static double leagueScore(Score score) { return score.progress() * 100.0 - score.loops() * 20.0 - score.calls() * .5 - score.interventions() * 10.0; }

  public static String stateHash(Observation value) {
    try { var digest=MessageDigest.getInstance("SHA-256").digest((value.goal()+value.state()+value.legalActions().stream().map(Action::id).toList()).getBytes(StandardCharsets.UTF_8)); return HexFormat.of().formatHex(digest,0,8); }
    catch(Exception error){ throw new IllegalStateException(error); }
  }
}
