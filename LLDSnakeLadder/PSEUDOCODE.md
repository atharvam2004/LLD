# Snake and Ladder Game - Invocation & Flow Pseudocode

## Design Patterns in Code
- **Facade Pattern**: `Game` facade manages the dice, player queue, and board state.
- **Round-Robin Queue**: Uses `Deque<Player>` to cycle through player turns.

---

## 1. Game Setup & Initialization

```java
// [PATTERN: FACADE INITIALIZATION]
Game game = new Game();

/* Inside Game constructor:
   1. Board board = new Board(10, 5, 4); // 10x10 board with 5 snakes, 4 ladders
      - Board HAS-A Cell[][] grid
      - Cell HAS-A Jump
   2. Dice dice = new Dice(1);
   3. Game HAS-A Deque<Player> playersList (Player 1, Player 2)
*/
```

---

## 2. Game Loop Execution Flow

```java
// Execute Game
game.startGame();

/* Inside startGame():
   while (winner == null) {
       // 1. Pop player from Deque head, re-insert at tail (Round-Robin)
       Player current = findPlayerTurn();
       
       // 2. Roll dice
       int roll = dice.rollDice();
       int newPos = current.currentPosition + roll;

       // 3. Jump Check (handles both Snake bite and Ladder climb)
       newPos = jumpCheck(newPos);
       current.currentPosition = newPos;

       // 4. Win condition
       if (newPos >= 99) {
           winner = current;
           System.out.println("Winner: " + winner.id);
       }
   }
*/
```
