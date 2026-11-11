# Tic Tac Toe - Invocation & Flow Pseudocode

## Design Patterns in Code
- **Polymorphism (IS-A)**: `PlayingPiece` subclassed into `PlayingPieceX` and `PlayingPieceO`.
- **Round-Robin Queue**: `Deque<Player>` turn management.

---

## 1. Setup & Initialization

```java
// [PATTERN: POLYMORPHIC TOKENS & QUEUE INITIALIZATION]
TicTacToeGame game = new TicTacToeGame();

/* Inside constructor:
   - Board board = new Board(3);
   - PlayingPiece pieceX = new PlayingPieceX(); // IS-A PlayingPiece
   - PlayingPiece pieceO = new PlayingPieceO(); // IS-A PlayingPiece
   - Player p1 = new Player("Player 1", pieceX); // Player HAS-A PlayingPiece
   - Player p2 = new Player("Player 2", pieceO);
   - Deque<Player> players = new LinkedList<>(List.of(p1, p2));
*/
```

---

## 2. Interactive Game Execution Flow

```java
// Start game loop
String winner = game.startGame();

/* Inside startGame():
   while (noWinner) {
       // 1. Pop player from Deque head
       Player current = players.removeFirst();
       
       // 2. Read (row, col) from console input
       // 3. Board.addPiece(row, col, current.getPlayingPiece());
       
       // 4. Re-insert player at tail:
       players.addLast(current);

       // 5. O(N) winner check across row, col, and diagonals:
       if (isThereWinner(row, col, current.getPlayingPiece().pieceType)) {
           return current.getName();
       }
   }
*/
```
