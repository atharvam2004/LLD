# Complete Knowledge Transfer (KT): Low Level Design (LLD) Project & Antigravity Setup

> **Document Purpose**: This Knowledge Transfer (KT) document compiles all context, architectural breakdowns, UML structures (IS-A & HAS-A), design patterns, invocation flows, file organization, and IDE setup from this session. You can open and reference this document in any new chat window in Antigravity.

---

## Table of Contents
1. [Project Overview & Origin](#1-project-overview--origin)
2. [Workspace Setup & IDE Configuration](#2-workspace-setup--ide-configuration)
3. [File Organization & Directory Layout](#3-file-organization--directory-layout)
4. [Master Summary of All 13 LLD Systems](#4-master-summary-of-all-13-lld-systems)
5. [Deep Dive per LLD: Patterns, IS-A / HAS-A, and Invocation](#5-deep-dive-per-lld-patterns-is-a--has-a-and-invocation)
   - 5.1 [DesignATM](#51-designatm)
   - 5.2 [DesignBookMyShow](#52-designbookmyshow)
   - 5.3 [DesignVendingMachine](#53-designvendingmachine)
   - 5.4 [LLDCarRentalSystem](#54-lldcarrentalsystem)
   - 5.5 [LLDElevatorDesign](#55-lldelevatordesign)
   - 5.6 [DesignSplitwise](#56-designsplitwise)
   - 5.7 [DesignOrderManagementSystem](#57-designordermanagementsystem)
   - 5.8 [LLDCricbuzz](#58-lldcricbuzz)
   - 5.9 [LLDSnakeLadder](#59-lldsnakeladder)
   - 5.10 [LLDTicTacToe](#510-lldtictactoe)
   - 5.11 [HandleNullObject](#511-handlenullobject)
   - 5.12 [DesignDataStructures (MyHashMap)](#512-designdatastructures-myhashmap)
   - 5.13 [DesignPatterns (GoF 5 Patterns)](#513-designpatterns-gof-5-patterns)
6. [Troubleshooting & Handy Tips](#6-troubleshooting--handy-tips)

---

## 1. Project Overview & Origin

- **Source Git Repository**: `https://gitlab.com/shrayansh8/interviewcodingpractise/-/tree/main/src/LowLevelDesign`
- **Topic**: Classic Low Level Design (LLD) interview problems implemented in Java.
- **Root Package**: `com.conceptandcoding.LowLevelDesign`

---

## 2. Workspace Setup & IDE Configuration

### A. Active Workspace Root
The workspace is located at:
```
c:\Users\meher\OneDrive\Desktop
```
And mirrored at:
```
c:\Users\meher\Desktop
```

### B. Project Structure Placement
To enable build tools, language indexing, and cross-file references:
- **`pom.xml`**: Placed directly at `c:\Users\meher\OneDrive\Desktop\pom.xml`
- **Java source files**: Placed at `c:\Users\meher\OneDrive\Desktop\src\main\java\com\conceptandcoding\LowLevelDesign`
- **Original cloned folder**: Also maintained at `c:\Users\meher\OneDrive\Desktop\interviewcodingpractise`

### C. Antigravity IDE 3-Pane Layout
To achieve the standard developer layout (**File Explorer on Left | Code in Middle | Chat on Right**):
1. **Chat on the Right**: Click the split icon `◨` in the top header or press `Ctrl + Alt + B` / `Ctrl + L`.
2. **Code in the Center**: Click on any `.java` or `.md` file to open it in an editor tab.
3. **Files on the Left**: Press `Ctrl + Shift + E` (or `Ctrl + B`) to display the file tree.

### D. Enabling `Ctrl + Click` (Go to Definition)
- When opening Java files, ensure the **Java Language Server** indexes the project.
- The project has `pom.xml` at the workspace root, which allows the Language Server to recognize all packages and classes.
- Ensure **Language Support for Java (Red Hat)** or **Extension Pack for Java** is installed if running in standard VS Code engine.

---

## 3. File Organization & Directory Layout

Every individual LLD package has its own dedicated markdown study material placed directly inside its folder alongside the Java source code:

```
src/main/java/com/conceptandcoding/LowLevelDesign/
│
├── DesignATM/
│   ├── ATM.java, Card.java, User.java, etc.
│   ├── UML.md               <-- Complete Mermaid UML with IS-A and HAS-A
│   └── PSEUDOCODE.md        <-- Setup & invocation flows with design patterns labeled
│
├── DesignBookMyShow/
│   ├── BookMyShow.java, MovieController.java, etc.
│   ├── UML.md
│   └── PSEUDOCODE.md
│
├── DesignVendingMachine/
│   ├── VendingMachine.java, State.java, etc.
│   ├── UML.md
│   └── PSEUDOCODE.md
│
├── LLDCarRentalSystem/
│   ├── VehicleRentalSystem.java, Vehicle.java, etc.
│   ├── UML.md
│   └── PSEUDOCODE.md
│
├── LLDElevatorDesign/
│   ├── Building.java, ElevatorCar.java, ElevatorController.java, etc.
│   ├── UML.md
│   └── PSEUDOCODE.md
│
├── DesignSplitwise/
│   ├── Splitwise.java, ExpenseController.java, etc.
│   ├── UML.md
│   └── PSEUDOCODE.md
│
├── DesignOrderManagementSystem/
│   ├── ProductDeliverySystem.java, Warehouse.java, etc.
│   ├── UML.md
│   └── PSEUDOCODE.md
│
├── LLDCricbuzz/
│   ├── Match.java, BallDetails.java, etc.
│   ├── UML.md
│   └── PSEUDOCODE.md
│
├── LLDSnakeLadder/
│   ├── Game.java, Board.java, Cell.java, Jump.java, etc.
│   ├── UML.md
│   └── PSEUDOCODE.md
│
├── LLDTicTacToe/
│   ├── TicTacToeGame.java, Board.java, PlayingPiece.java, etc.
│   ├── UML.md
│   └── PSEUDOCODE.md
│
├── HandleNullObject/
│   ├── Vehicle.java, Car.java, NullVehicle.java, VehicleFactory.java, etc.
│   ├── UML.md
│   └── PSEUDOCODE.md
│
├── DesignDataStructures/
│   ├── MyHashMap.java
│   ├── UML.md
│   └── PSEUDOCODE.md
│
└── DesignPatterns/
    ├── AdapterDesignPattern/
    ├── BuilderDesignPattern/
    ├── CompositeDesignPattern/
    ├── FlyWeightPattern/
    ├── LLDChainResponsibilityDesignPattern/
    ├── UML.md
    └── PSEUDOCODE.md
```

---

## 4. Master Summary of All 13 LLD Systems

| # | Folder Name | Primary Design Pattern(s) | Core Technical Highlights |
|---|---|---|---|
| 1 | **DesignATM** | **State**, **Chain of Responsibility**, **Singleton** | Transitions through ATM states; uses Chain of Processors ($2000 \rightarrow 500 \rightarrow 100$) for note dispensing. |
| 2 | **DesignBookMyShow** | **Controller / Repository**, **Facade** | Multilevel hierarchy (City $\rightarrow$ Theatres $\rightarrow$ Screens $\rightarrow$ Shows $\rightarrow$ Seats). Tracks booked seat IDs to prevent race conditions. |
| 3 | **DesignVendingMachine** | **State Pattern** | Transitions: `IdleState` $\rightarrow$ `HasMoneyState` $\rightarrow$ `SelectionState` $\rightarrow$ `DispenseState`. Handles coin values & refunds. |
| 4 | **LLDCarRentalSystem** | **Polymorphic Hierarchy**, **Facade** | Abstract `Vehicle` extended by `Car` and `Bike`. `Store` delegates to `VehicleInventoryManagement`. |
| 5 | **LLDElevatorDesign** | **Strategy Pattern**, **Dispatcher Pattern**, **Min/Max Heaps** | `ElevatorSelectionStrategy` (Odd-Even, Nearest); `ExternalDispatcher` handles outside hall calls; `InternalDispatcher` handles inside buttons; `ElevatorController` uses `upMinPQ` & `downMaxPQ`. |
| 6 | **DesignSplitwise** | **Strategy**, **Factory**, **Facade** | Dynamic split calculation (`EqualExpenseSplit`, `UnequalExpenseSplit`, `PercentageExpenseSplit`) with double-entry balance sheets (`amountOwe` vs `amountGetBack`). |
| 7 | **DesignOrderManagementSystem** | **Facade**, **Strategy (Routing & Payments)** | `ProductDeliverySystem` coordinates `UserController`, `WarehouseController`, `OrderController`. Pluggable warehouse selection & payment modes (Card, UPI). |
| 8 | **LLDCricbuzz** | **Observer**, **Strategy** | `BallDetails` (Subject) notifies `BattingScoreUpdater` and `BowlingScoreUpdater` (Observers) after every delivery. `MatchType` strategy (T20, ODI). |
| 9 | **LLDSnakeLadder** | **Round-Robin Queue**, **Facade** | Unified `Jump` entity (start > end = Snake, start < end = Ladder). Rotates players using `Deque<Player>`. |
| 10 | **LLDTicTacToe** | **Polymorphism**, **FIFO Queue** | `PlayingPiece` extended by `PlayingPieceX` and `PlayingPieceO`. $O(N)$ winner check across row, column, and diagonals. |
| 11 | **HandleNullObject** | **Null Object Pattern**, **Factory** | `NullVehicle` implements `Vehicle` returning default 0 values. Eliminates defensive `if (vehicle != null)` checks. |
| 12 | **DesignDataStructures** | **Separate Chaining (Custom Map)** | Custom generic `MyHashMap<K, V>` with power-of-two capacity sizing and bucket array of linked list `Entry` nodes. |
| 13 | **DesignPatterns** | **GoF: 5 Core Patterns** | • **Adapter**: Weight machine Lbs $\rightarrow$ Kg.<br>• **Builder**: Student construction via Director.<br>• **Composite**: File system & Arithmetic expressions.<br>• **Flyweight**: Robot sprites & Word processor letters.<br>• **Chain of Responsibility**: LogProcessor (Info $\rightarrow$ Debug $\rightarrow$ Error). |

---

## 5. Deep Dive per LLD: Patterns, IS-A / HAS-A, and Invocation

### 5.1 DesignATM
- **Patterns**: State Pattern, Chain of Responsibility, Singleton.
- **IS-A**:
  - `IdleState`, `HasCardState`, `SelectOperationState`, `CashWithdrawalState`, `CheckBalanceState` **IS-A** `ATMState`
  - `TwoThousandWithdrawProcessor`, `FiveHundredWithdrawProcessor`, `OneHundredWithdrawProcessor` **IS-A** `CashWithdrawProcessor`
- **HAS-A**:
  - `ATM` **HAS-A** `ATMState` (`currentATMState`)
  - `User` **HAS-A** `Card` & `UserBankAccount`
  - `Card` **HAS-A** `UserBankAccount`
  - `CashWithdrawProcessor` **HAS-A** `CashWithdrawProcessor` (`nextCashWithdrawalProcessor`)
- **Invocation**:
  ```java
  ATM atm = ATM.getATMObject();
  atm.setAtmBalance(3500, 1, 2, 5);
  atm.getCurrentATMState().insertCard(atm, card);
  atm.getCurrentATMState().authenticatePin(atm, card, 112211);
  atm.getCurrentATMState().selectOperation(atm, card, TransactionType.CASH_WITHDRAWAL);
  atm.getCurrentATMState().cashWithdrawal(atm, card, 2700); // 1x2000, 1x500, 2x100
  ```

---

### 5.2 DesignBookMyShow
- **Patterns**: Controller / Repository Pattern, Facade Pattern.
- **IS-A**: Enums `City` (`Bangalore`, `Delhi`), `SeatCategory` (`SILVER`, `GOLD`, `PLATINUM`).
- **HAS-A**:
  - `BookMyShow` **HAS-A** `MovieController` & `TheatreController`
  - `Theatre` **HAS-A** `List<Screen>` & `List<Show>`
  - `Screen` **HAS-A** `List<Seat>`
  - `Show` **HAS-A** `Movie`, `Screen`, & `List<Integer>` (`bookedSeatIds`)
  - `Booking` **HAS-A** `Show`, `List<Seat>`, & `Payment`
- **Invocation**:
  ```java
  BookMyShow bms = new BookMyShow();
  bms.initialize();
  bms.createBooking(City.Bangalore, "BAAHUBALI");
  ```

---

### 5.3 DesignVendingMachine
- **Patterns**: State Pattern.
- **IS-A**:
  - `IdleState`, `HasMoneyState`, `SelectionState`, `DispenseState` **IS-A** `State`
- **HAS-A**:
  - `VendingMachine` **HAS-A** `State`, `Inventory`, & `List<Coin>`
  - `Inventory` **HAS-A** `ItemShelf[]`
  - `ItemShelf` **HAS-A** `Item`
  - `Item` **HAS-A** `ItemType`
- **Invocation**:
  ```java
  VendingMachine machine = new VendingMachine();
  machine.getVendingMachineState().clickOnInsertCoinButton(machine); // -> HasMoneyState
  machine.getVendingMachineState().insertCoin(machine, Coin.QUARTER);
  machine.getVendingMachineState().clickOnStartProductSelectionButton(machine); // -> SelectionState
  machine.getVendingMachineState().chooseProduct(machine, 102); // -> Dispenses & resets to IdleState
  ```

---

### 5.4 LLDCarRentalSystem
- **Patterns**: Polymorphism (IS-A), Facade Pattern.
- **IS-A**:
  - `Car` **IS-A** `Vehicle`
  - `Bike` **IS-A** `Vehicle`
- **HAS-A**:
  - `VehicleRentalSystem` **HAS-A** `List<Store>` & `List<User>`
  - `Store` **HAS-A** `VehicleInventoryManagement`, `Location`, & `List<Reservation>`
  - `Reservation` **HAS-A** `User`, `Vehicle`, & `Location`
  - `Bill` **HAS-A** `Reservation`
- **Invocation**:
  ```java
  VehicleRentalSystem system = new VehicleRentalSystem(stores, users);
  Store store = system.getStore(location);
  List<Vehicle> cars = store.getVehicles(VehicleType.CAR);
  Reservation reservation = store.createReservation(cars.get(0), user);
  Bill bill = new Bill(reservation);
  new Payment().payBill(bill);
  store.completeReservation(reservation.reservationId);
  ```

---

### 5.5 LLDElevatorDesign
- **Patterns**: Strategy Pattern (`ElevatorSelectionStrategy`), Dispatcher Pattern, Min/Max Heap Scheduling (LOOK/SCAN), Controller Pattern.
- **IS-A**:
  - `OddEvenSelectionStrategy`, `NearestElevatorSelectionStrategy` **IS-A** `ElevatorSelectionStrategy` (`implements`)
  - Enums `Direction` (`UP`, `DOWN`), `ElevatorState` (`MOVING`, `IDLE`).
- **HAS-A**:
  - `Building` **HAS-A** `List<Floor>`
  - `Floor` **HAS-A** `ExternalDispatcher`
  - `ExternalDispatcher` **HAS-A** `ElevatorSelectionStrategy` & `List<ElevatorController>`
  - `InternalDispatcher` **HAS-A** `List<ElevatorController>`
  - `ElevatorCar` **HAS-A** `ElevatorDisplay`, `InternalButtons`, `ElevatorDoor`, `Direction`, & `ElevatorState`
  - `InternalButtons` **HAS-A** `InternalDispatcher`
  - `ElevatorController` **HAS-A** `ElevatorCar`, `upMinPQ`, & `downMaxPQ`
- **Invocation**:
  ```java
  // Hall Call from floor 3
  Floor floor3 = building.getAllFloorList().get(2);
  floor3.pressButton(Direction.UP); // ExternalDispatcher routes via Strategy to controller 1

  // Inside Elevator 1, passenger picks floor 5
  ElevatorCar car = ElevatorCreator.elevatorControllerList.get(0).elevatorCar;
  car.pressButton(5); // InternalDispatcher queues into upMinPQ
  ElevatorCreator.elevatorControllerList.get(0).controlElevator();
  ```

---

### 5.6 DesignSplitwise
- **Patterns**: Strategy Pattern (`ExpenseSplit`), Factory Pattern (`SplitFactory`), Facade Pattern (`Splitwise`).
- **IS-A**:
  - `EqualExpenseSplit`, `UnequalExpenseSplit`, `PercentageExpenseSplit` **IS-A** `ExpenseSplit`
- **HAS-A**:
  - `Splitwise` **HAS-A** `UserController`, `GroupController`, & `BalanceSheetController`
  - `Group` **HAS-A** `List<User>`, `List<Expense>`, & `ExpenseController`
  - `Expense` **HAS-A** `User` (`paidByUser`) & `List<Split>`
  - `User` **HAS-A** `UserExpenseBalanceSheet`
  - `UserExpenseBalanceSheet` **HAS-A** `Map<String, Balance>`
- **Invocation**:
  ```java
  Splitwise app = new Splitwise();
  Group group = app.getGroupController().createNewGroup("G1", "Trip", user1);
  group.addMember(user2);
  group.addMember(user3);

  List<Split> splits = List.of(new Split(user1, 300), new Split(user2, 300), new Split(user3, 300));
  group.createExpense("EXP1", "Dinner", 900.0, splits, ExpenseSplitType.EQUAL, user1);
  app.getBalanceSheetController().showBalanceSheetOfUser(user1);
  ```

---

### 5.7 DesignOrderManagementSystem
- **Patterns**: Facade Pattern, Strategy Pattern (Warehouse selection & Payment mode).
- **IS-A**:
  - `NearestWarehouseSelectionStrategy` **IS-A** `WarehouseSelectionStrategy`
  - `CardPaymentMode`, `UPIPaymentMode` **IS-A** `PaymentMode`
- **HAS-A**:
  - `ProductDeliverySystem` **HAS-A** `UserController`, `WarehouseController`, & `OrderController`
  - `Warehouse` **HAS-A** `Inventory` & `Address`
  - `Inventory` **HAS-A** `List<ProductCategory>`
  - `ProductCategory` **HAS-A** `List<Product>`
  - `User` **HAS-A** `Cart`
  - `Order` **HAS-A** `User`, `Warehouse`, `Invoice`, & `Payment`
- **Invocation**:
  ```java
  ProductDeliverySystem system = new ProductDeliverySystem(users, warehouses);
  User user = system.getUser(1);
  Warehouse warehouse = system.getWarehouse(new NearestWarehouseSelectionStrategy());
  system.addProductToCart(user, category, 2);
  Order order = system.placeOrder(user, warehouse);
  system.checkout(order); // Deducts inventory, executes UPI payment, clears cart
  ```

---

### 5.8 LLDCricbuzz
- **Patterns**: Observer Pattern (`BallDetails` $\rightarrow$ `ScoreUpdaterObserver`), Strategy Pattern (`MatchType`).
- **IS-A**:
  - `T20MatchType`, `OneDayMatchType` **IS-A** `MatchType`
  - `BattingScoreUpdater`, `BowlingScoreUpdater` **IS-A** `ScoreUpdaterObserver`
- **HAS-A**:
  - `Match` **HAS-A** `Team`, `MatchType`, & `InningDetails[2]`
  - `InningDetails` **HAS-A** `List<OverDetails>`
  - `OverDetails` **HAS-A** `List<BallDetails>` & `PlayerDetails` (`bowledBy`)
  - `BallDetails` **HAS-A** `List<ScoreUpdaterObserver>`
  - `Team` **HAS-A** `PlayerBattingController` & `PlayerBowlingController`
  - `PlayerDetails` **HAS-A** `BattingScoreCard` & `BowlingScoreCard`
- **Invocation**:
  ```java
  Team india = addTeam("India");
  Team sriLanka = addTeam("SriLanka");
  Match match = new Match(india, sriLanka, new Date(), "Wankhede", new T20MatchType());
  match.startMatch(); // Toss -> Innings 1 -> Innings 2 -> Winner Announcement
  ```

---

### 5.9 LLDSnakeLadder
- **Patterns**: Round-Robin Queue, Facade Pattern.
- **IS-A**: Concrete domain entities.
- **HAS-A**:
  - `Game` **HAS-A** `Board`, `Dice`, `Deque<Player>`, & `winner`
  - `Board` **HAS-A** `Cell[][]`
  - `Cell` **HAS-A** `Jump` (unifies Snake: start > end, Ladder: start < end)
- **Invocation**:
  ```java
  Game game = new Game(); // Builds 10x10 board, 5 snakes, 4 ladders, 2 players
  game.startGame();       // Plays until player reaches cell 99
  ```

---

### 5.10 LLDTicTacToe
- **Patterns**: Polymorphic Token Hierarchy, Round-Robin Queue.
- **IS-A**:
  - `PlayingPieceX`, `PlayingPieceO` **IS-A** `PlayingPiece`
- **HAS-A**:
  - `TicTacToeGame` **HAS-A** `Board` & `Deque<Player>`
  - `Board` **HAS-A** `PlayingPiece[][]`
  - `Player` **HAS-A** `PlayingPiece`
  - `PlayingPiece` **HAS-A** `PieceType` (`X`, `O`)
- **Invocation**:
  ```java
  TicTacToeGame game = new TicTacToeGame();
  String winner = game.startGame(); // Console moves with O(N) winner check
  ```

---

### 5.11 HandleNullObject
- **Patterns**: Null Object Pattern, Simple Factory Pattern.
- **IS-A**:
  - `Car`, `NullVehicle` **IS-A** `Vehicle`
- **HAS-A**:
  - `VehicleFactory` creates and returns `Vehicle`
- **Invocation**:
  ```java
  Vehicle v = VehicleFactory.getVehicleObject("Bike"); // Returns NullVehicle
  System.out.println(v.getTankCapacity()); // Prints 0 (no NPE, zero null checks)
  ```

---

### 5.12 DesignDataStructures (MyHashMap)
- **Patterns**: Custom Data Structure / Separate Chaining.
- **IS-A**: Standalone generic container.
- **HAS-A**:
  - `MyHashMap` **HAS-A** `Entry[]` (`hashTable` bucket array)
  - `Entry` **HAS-A** `Entry` (`next` pointer forming a singly-linked list)
- **Invocation**:
  ```java
  MyHashMap<Integer, String> map = new MyHashMap<>(7); // rounds up to 8
  map.put(1, "Java");
  map.put(9, "LLD"); // 9 % 8 == 1 -> chains to linked list at bucket 1
  String val = map.get(9);
  ```

---

### 5.13 DesignPatterns (GoF 5 Patterns)
1. **Adapter Pattern**:
   - `WeightMachineForBabies IS-A WeightMachine` (adaptee)
   - `WeightMachineAdapterImpl IS-A WeightMachineAdapter` (HAS-A `WeightMachine`)
   - `double kg = adapter.getWeightInKg();` (converts lbs * 0.45)
2. **Builder Pattern**:
   - `EngineeringStudentBuilder IS-A StudentBuilder`
   - `Director HAS-A StudentBuilder`
   - `Student s = director.createEngineeringStudent();`
3. **Composite Pattern**:
   - `File` and `Directory` **IS-A** `FileSystem` (`Directory HAS-A List<FileSystem>`)
   - `Expression` and `Number` **IS-A** `ArithmeticExpression`
4. **Flyweight Pattern**:
   - `HumanoidRobot IS-A IRobot` (HAS-A shared `Sprites` bitmap)
   - `RoboticFactory` caches instances: `IRobot r = RoboticFactory.createRobot("HUMANOID"); r.display(x, y);`
5. **Chain of Responsibility Pattern**:
   - `InfoLogProcessor`, `DebugLogProcessor`, `ErrorLogProcessor` **IS-A** `LogProcessor`
   - `LogProcessor HAS-A LogProcessor` (`nextLoggerProcessor` chain link)
   - `logChain.log(LogProcessor.ERROR, "Crash");`

---

## 6. Troubleshooting & Handy Tips

1. **Junctions & Desktops**:
   - In this Windows setup, `C:\Users\meher\Desktop` is linked as a junction to `C:\Users\meher\OneDrive\Desktop`. Both paths refer to the exact same files on disk.
2. **Where to Find the Code**:
   - All Java source packages are located in:
     `c:\Users\meher\OneDrive\Desktop\src\main\java\com\conceptandcoding\LowLevelDesign`
3. **Opening this in Another Antigravity Chat**:
   - In your new chat, you can refer the model directly to this file:
     `c:\Users\meher\OneDrive\Desktop\src\main\java\com\conceptandcoding\LowLevelDesign\KT_PROJECT_OVERVIEW.md`
   - The model can read this single KT file and have 100% of the project background, patterns, UML details, and code conventions ready immediately.
