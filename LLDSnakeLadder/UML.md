# Snake and Ladder Game - Low Level Design & Architecture

## 1. Overview
Multiplayer board game where players roll dice and advance through numbered cells containing snakes and ladders.

---

## 2. Design Patterns Used
1. **Facade Pattern**:
   - `Game` acts as a facade managing dice rolling, turn rotations, jump checks, and victory validation.
2. **Unified Abstraction (`Jump`)**:
   - Eliminates need for separate `Snake` and `Ladder` classes by using a single `Jump` class with `start` and `end` indices.

---

## 3. Relationships Summary (IS-A & HAS-A)

### IS-A (Inheritance / Realization)
- Standalone POJO domain model without inheritance.

### HAS-A (Composition & Association)
- `Game` **HAS-A** `Board` (`board`)
- `Game` **HAS-A** `Dice` (`dice`)
- `Game` **HAS-A** `Deque<Player>` (`playersList`)
- `Game` **HAS-A** `Player` (`winner`)
- `Board` **HAS-A** `Cell[][]` (`cells`)
- `Cell` **HAS-A** `Jump` (`jump`)
- `Jump` has `start` and `end` cell integer positions.

---

## 4. Mermaid UML Class Diagram

```mermaid
classDiagram
    direction TB

    class Game {
        -Board board
        -Dice dice
        -Deque~Player~ playersList
        -Player winner
        +Game()
        +startGame() void
        -findPlayerTurn() Player
        -jumpCheck(int) int
    }

    class Board {
        ~Cell[][] cells
        +Board(int, int, int)
        ~getCell(int) Cell
    }

    class Cell {
        ~Jump jump
    }

    class Jump {
        ~int start
        ~int end
    }

    class Dice {
        ~int diceCount
        +rollDice() int
    }

    class Player {
        ~String id
        ~int currentPosition
    }

    %% HAS-A Relationships
    Game *-- Board : HAS-A
    Game *-- Dice : HAS-A
    Game o-- Player : HAS-A (playersList)
    Game --> Player : HAS-A (winner)
    Board *-- Cell : HAS-A (cells grid)
    Cell o-- Jump : HAS-A
```
