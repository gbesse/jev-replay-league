package org.jev.replayleague;
import java.util.*;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
final class LeagueCoreTest { private final LeagueCore.Observation observation=new LeagueCore.Observation("r",1,4,"finish",Map.of("x",1),List.of(new LeagueCore.Action("go","Go",1,.1),new LeagueCore.Action("wait","Wait",0,0)));
@Test void fixtureUsesCodeOwnedLookahead(){assertEquals("go",LeagueCore.fixtureDecision(observation).actionId());}
@Test void staleDecisionNeverExecutes(){assertFalse(LeagueCore.executable(observation,LeagueCore.fixtureDecision(observation),5,.7));}
@Test void inventedActionNeverExecutes(){assertFalse(LeagueCore.executable(observation,new LeagueCore.Decision("hack",1,4),4,.7));}
@Test void rankingIsDeterministic(){assertTrue(LeagueCore.leagueScore(new LeagueCore.Score(2,0,2,0))>LeagueCore.leagueScore(new LeagueCore.Score(2,1,2,0)));}
@Test void stateHashIsStable(){assertEquals(LeagueCore.stateHash(observation),LeagueCore.stateHash(observation));}}
