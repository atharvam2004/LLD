# Tic Tac Toe - Low Level Design & Architecture

## 1. Overview
$N \times N$ Tic Tac Toe board game modeling extensible token types, player turn management, and $O(N)$ winner checks.

---

## 2. Design Patterns Used
1. **Polymorphic Token Hierarchy (IS-A)**:
   - `PlayingPiece` abstract base class extended by `PlayingPieceX` and `PlayingPieceO`.
2. **Round-Robin Queue**:
   - `Deque<Player>` to rotate player moves in FIFO order.

---

## 3. Relationships Summary (IS-A & HAS-A)

### IS-A (Inheritance / Realization)
- `PlayingPieceX` **IS-A** `PlayingPiece` (`extends PlayingPiece`)
- `PlayingPieceO` **IS-A** `PlayingPiece` (`extends PlayingPiece`)

### HAS-A (Composition & Association)
- `TicTacToeGame` **HAS-A** `Board` (`gameBoard`)
- `TicTacToeGame` **HAS-A** `Deque<Player>` (`players`)
- `Board` **HAS-A** `PlayingPiece[][]` (`board`)
- `Player` **HAS-A** `PlayingPiece` (`playingPiece`)
- `PlayingPiece` **HAS-A** `PieceType` (`pieceType`)

---

## 4. Mermaid UML Class Diagram

```mermaid
classDiagram
    direction TB

    class TicTacToeGame {
        ~Deque~Player~ players
        ~Board gameBoard
        +startGame() String
        +isThereWinner(int, int, PieceType) boolean
    }

    class Board {
        +int size
        +PlayingPiece[][] board
        +addPiece(int, int, PlayingPiece) boolean
        +getFreeCells() List~Pair~
        +printBoard() void
    }

    class Player {
        -String name
        -PlayingPiece playingPiece
    }

    class PlayingPiece {
        +PieceType pieceType
    }

    class PlayingPieceX
    class PlayingPieceO

    %% IS-A Relationships
    PlayingPiece <|-- PlayingPieceX : IS-A
    PlayingPiece <|-- PlayingPieceO : IS-A

    class PieceType {
        <<enum>>
        X
        O
    }

    %% HAS-A Relationships
    TicTacToeGame *-- Board : HAS-A
    TicTacToeGame o-- Player : HAS-A (players)
    Player *-- PlayingPiece : HAS-A
    PlayingPiece --> PieceType : HAS-A
    Board *-- PlayingPiece : HAS-A (grid cells)
```
