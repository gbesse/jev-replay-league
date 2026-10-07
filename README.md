# Jev Replay League for Minecraft Paper

A Paper server plugin and reproducible scoring core for comparing finite DecisionPacks on the same Minecraft scenario. This is a league harness, not another free-roaming Minecraft agent.

Every run records a versioned observation, legal action IDs, code-simulated progress/risk, the selected action, confidence, loops, calls and interventions. Code computes the league score. A stale or invented action never crosses the execution boundary.

```bash
gradle test jar
```

Place the resulting JAR in a Paper 1.21.11 server's `plugins/` directory and run `/jevleague demo` as an operator. Version 0.1.0 exposes an offline fixture command and the deterministic league core; it does not yet move a player or NPC.

The plugin must never contain a provider key. Live packs belong behind an authenticated server-side gateway. World simulation and pathfinding remain code-owned; Jev evaluates supplied candidates only.

Independent experimental community project. Not affiliated with Mojang, Microsoft, PaperMC or TypeSafe. MIT licensed.
