# Cricbuzz Match & Live Scoring - Invocation & Flow Pseudocode

## Design Patterns in Code
- **Observer Pattern**: `BallDetails` (Subject) notifies `BattingScoreUpdater` and `BowlingScoreUpdater` (Observers) after every delivery.
- **Strategy Pattern**: `MatchType` interface (`T20MatchType`, `OneDayMatchType`) defines match rules.

---

## 1. Setup & Match Initialization Flow

```java
// Setup Teams (Team HAS-A 11 PlayerDetails)
Team teamIndia = addTeam("India");
Team teamSriLanka = addTeam("SriLanka");

// [PATTERN: STRATEGY PATTERN - MATCH FORMAT]
MatchType t20 = new T20MatchType();

// Initialize Match (Match HAS-A Teams & MatchType)
Match match = new Match(teamIndia, teamSriLanka, new Date(), "Eden Gardens", t20);
```

---

## 2. Match Execution & Observer Updates Flow

```java
// Start match
match.startMatch();

/* Inside match.startMatch():
   1. Toss is executed
   2. Starts InningDetails (InningDetails HAS-A List<OverDetails>)
   3. For each over, creates OverDetails (OverDetails HAS-A List<BallDetails>)
   4. For each ball:
      // [PATTERN: OBSERVER PATTERN SETUP]
      BallDetails ball = new BallDetails(ballNumber);
      ball.scoreUpdaterObserverList.add(new BattingScoreUpdater());
      ball.scoreUpdaterObserverList.add(new BowlingScoreUpdater());

      // Simulate delivery:
      ball.startBallDelivery(battingTeam, bowlingTeam, over);
      
      // [PATTERN: OBSERVER NOTIFICATION]
      ball.notifyUpdaters(ball);
      // -> BattingScoreUpdater.update() updates batsman runs and fours/sixes
      // -> BowlingScoreUpdater.update() updates bowler runs conceded and wickets
*/
```
