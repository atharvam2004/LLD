# Cricbuzz Match & Live Scoring - Low Level Design & Architecture

## 1. Overview
Cricket match simulator with ball-by-ball updates, live scorecard listeners, and batting/bowling rotation controllers.

---

## 2. Design Patterns Used
1. **Observer Pattern**:
   - `BallDetails` (Subject) maintains a list of `ScoreUpdaterObserver` instances.
   - `BattingScoreUpdater` and `BowlingScoreUpdater` (Observers) receive delivery events and update respective statistics.
2. **Strategy Pattern**:
   - `MatchType` interface defines overs and bowler limits (`T20MatchType`, `OneDayMatchType`).
3. **Controller Pattern**:
   - `PlayerBattingController` and `PlayerBowlingController` manage lineup queues and rotation constraints.

---

## 3. Relationships Summary (IS-A & HAS-A)

### IS-A (Inheritance / Realization)
- `T20MatchType` **IS-A** `MatchType` (`implements MatchType`)
- `OneDayMatchType` **IS-A** `MatchType` (`implements MatchType`)
- `BattingScoreUpdater` **IS-A** `ScoreUpdaterObserver` (`implements ScoreUpdaterObserver`)
- `BowlingScoreUpdater` **IS-A** `ScoreUpdaterObserver` (`implements ScoreUpdaterObserver`)

### HAS-A (Composition & Association)
- `Match` **HAS-A** `Team` (`teamA`, `teamB`)
- `Match` **HAS-A** `MatchType` (`matchType`)
- `Match` **HAS-A** `InningDetails[2]` (`innings`)
- `InningDetails` **HAS-A** `List<OverDetails>` (`overs`)
- `OverDetails` **HAS-A** `List<BallDetails>` (`balls`)
- `OverDetails` **HAS-A** `PlayerDetails` (`bowledBy`)
- `BallDetails` **HAS-A** `List<ScoreUpdaterObserver>` (`scoreUpdaterObserverList`)
- `BallDetails` **HAS-A** `PlayerDetails` (`playedBy`, `bowledBy`)
- `BallDetails` **HAS-A** `Wicket` (`wicket`)
- `Team` **HAS-A** `PlayerBattingController` (`battingController`)
- `Team` **HAS-A** `PlayerBowlingController` (`bowlingController`)
- `Team` **HAS-A** `Queue<PlayerDetails>` (`playing11`)
- `PlayerDetails` **HAS-A** `BattingScoreCard` (`battingScoreCard`)
- `PlayerDetails` **HAS-A** `BowlingScoreCard` (`bowlingScoreCard`)

---

## 4. Mermaid UML Class Diagram

```mermaid
classDiagram
    direction TB

    class Match {
        -Team teamA
        -Team teamB
        -MatchType matchType
        -InningDetails[2] innings
        +startMatch() void
    }

    class MatchType {
        <<interface>>
        +noOfOvers() int
        +maxOverCountBowlers() int
    }
    class T20MatchType { +noOfOvers() int }
    class OneDayMatchType { +noOfOvers() int }

    %% IS-A Relationships
    MatchType <|.. T20MatchType : IS-A
    MatchType <|.. OneDayMatchType : IS-A

    class InningDetails {
        -Team battingTeam
        -Team bowlingTeam
        -List~OverDetails~ overs
        +start(int runsToWin) void
    }

    class OverDetails {
        -int overNumber
        -List~BallDetails~ balls
        -PlayerDetails bowledBy
    }

    class BallDetails {
        -int ballNumber
        -PlayerDetails playedBy
        -PlayerDetails bowledBy
        -List~ScoreUpdaterObserver~ scoreUpdaterObserverList
        +notifyUpdaters(BallDetails) void
    }

    class ScoreUpdaterObserver {
        <<interface>>
        +update(BallDetails) void
    }
    class BattingScoreUpdater { +update(BallDetails) void }
    class BowlingScoreUpdater { +update(BallDetails) void }

    %% IS-A Relationships
    ScoreUpdaterObserver <|.. BattingScoreUpdater : IS-A
    ScoreUpdaterObserver <|.. BowlingScoreUpdater : IS-A

    class Team {
        -PlayerBattingController battingController
        -PlayerBowlingController bowlingController
    }

    class PlayerDetails {
        -BattingScoreCard battingScoreCard
        -BowlingScoreCard bowlingScoreCard
    }

    %% HAS-A Relationships
    Match o-- MatchType : HAS-A
    Match *-- InningDetails : HAS-A (1..2)
    InningDetails *-- OverDetails : HAS-A (1..*)
    OverDetails *-- BallDetails : HAS-A (1..6)
    BallDetails o-- ScoreUpdaterObserver : HAS-A (observers)
    Team *-- PlayerBattingController : HAS-A
    Team *-- PlayerBowlingController : HAS-A
    Team *-- PlayerDetails : HAS-A (11)
    PlayerDetails *-- BattingScoreCard : HAS-A
    PlayerDetails *-- BowlingScoreCard : HAS-A
```
