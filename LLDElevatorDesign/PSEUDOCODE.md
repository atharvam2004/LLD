# Elevator Design System - Invocation & Flow Pseudocode

## Design Patterns in Code
- **Strategy Pattern**: `ElevatorSelectionStrategy` (`OddEvenSelectionStrategy`, `NearestElevatorSelectionStrategy`) dynamically determines which elevator services an external hall call.
- **Dispatcher Pattern**: `ExternalDispatcher` handles outside hall calls; `InternalDispatcher` handles car button selections.
- **Priority Queue Scheduling (SCAN/LOOK Algorithm)**: Segregates destinations into ascending (`upMinPQ`) and descending (`downMaxPQ`) heaps.
- **Controller Pattern**: `ElevatorController` manages state, door opening, display, and motion loops for an `ElevatorCar`.

---

## 1. System Setup & Initialization

```java
// 1. Elevator cars and controllers are pooled via ElevatorCreator
/* ElevatorCreator static initializer creates:
   - ElevatorCar 1 -> wrapped in ElevatorController 1
   - ElevatorCar 2 -> wrapped in ElevatorController 2
   - Stored in ElevatorCreator.elevatorControllerList
*/

// 2. Setup Building Floors (Floor HAS-A ExternalDispatcher)
List<Floor> floors = List.of(new Floor(1), new Floor(2), new Floor(3), new Floor(4), new Floor(5));
Building building = new Building(floors);
```

---

## 2. Dispatching Calls Flow

```java
// [PATTERN: STRATEGY & DISPATCHER PATTERN - HALL CALL]
// Passenger at Floor 3 presses "UP":
Floor floor3 = floors.get(2);
floor3.pressButton(Direction.UP);

/* Inside pressButton(Direction.UP):
   1. floor3 delegates to ExternalDispatcher:
      externalDispatcher.submitExternalRequest(3, Direction.UP);
   
   2. ExternalDispatcher delegates to ElevatorSelectionStrategy:
      ElevatorController controller = selectionStrategy.selectElevator(3, Direction.UP, elevatorControllerList);
      // OddEvenSelectionStrategy picks ElevatorCar 1 (both floor 3 and car 1 are ODD)
   
   3. Controller pushes request into PriorityQueue:
      controller.upMinPQ.offer(3); // Queued for upward travel
*/

// Process the call:
controller.controlElevator(); // Elevator 1 travels to floor 3

// [PATTERN: DISPATCHER PATTERN - INTERNAL CAR BUTTON]
// Passenger boards Elevator 1 and selects Floor 5:
ElevatorCar car1 = controller.elevatorCar;
car1.pressButton(5);

/* Inside car1.pressButton(5):
   1. Car delegates to InternalButtons:
      internalButtons.pressButton(5, car1);
   2. InternalButtons delegates to InternalDispatcher:
      internalDispatcher.submitInternalRequest(5, car1);
   3. Routes to controller 1, which queues 5 into upMinPQ:
      controller.upMinPQ.offer(5);
*/

// Controller processes motion loop:
controller.controlElevator(); // Moves car1 up to floor 5, updates display, arrives at destination!
```
