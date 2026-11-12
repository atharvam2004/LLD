# Elevator Design System - Invocation & Flow Pseudocode

## Design Patterns in Code
- **Building as Aggregate Root (Clean Architecture)**: `Building` directly owns both `List<Floor>` and `List<ElevatorController>`. It injects the single shared `ExternalDispatcher` into all floors.
- **Strategy Pattern**: `ElevatorSelectionStrategy` (`OddEvenSelectionStrategy`, `NearestElevatorSelectionStrategy`) dynamically determines which elevator services an external hall call.
- **Dispatcher Pattern**: `ExternalDispatcher` handles outside hall calls; `InternalDispatcher` handles car button selections.
- **Priority Queue Scheduling (SCAN/LOOK Algorithm)**: Segregates destinations into ascending (`upMinPQ`) and descending (`downMaxPQ`) heaps.
- **Controller Pattern**: `ElevatorController` manages state, door opening, display, and motion loops for an `ElevatorCar`.

---

## 1. System Setup & Initialization (Clean Building Architecture)

```java
// 1. Create Building Floors
List<Floor> floors = List.of(new Floor(1), new Floor(2), new Floor(3), new Floor(4), new Floor(5));

// 2. Instantiate Elevator Bank (Cars & Controllers)
List<ElevatorController> elevatorControllers = ElevatorCreator.createElevatorControllers(2);

// 3. Building acts as the Aggregate Root
// Building owns the floors, owns the elevator controllers, and wires the shared ExternalDispatcher
Building building = new Building(floors, elevatorControllers, new NearestElevatorSelectionStrategy());

/* Inside Building constructor:
   - Sets this.elevatorControllerList = elevatorControllers;
   - Initializes shared externalDispatcher:
       this.externalDispatcher = new ExternalDispatcher(this.elevatorControllerList, strategy);
   - Injects this.externalDispatcher into every Floor:
       for (Floor f : floors) { f.setExternalDispatcher(this.externalDispatcher); }
   All floors now share the EXACT same elevator controllers and dispatcher!
*/
```

---

## 2. Dispatching Calls Flow

```java
// [PATTERN: DISPATCHER & STRATEGY PATTERN - HALL CALL]
// Passenger at Floor 3 presses "UP":
Floor floor3 = building.getAllFloorList().get(2);
floor3.pressButton(Direction.UP);

/* Inside pressButton(Direction.UP):
   1. floor3 delegates to the shared ExternalDispatcher injected by Building:
      externalDispatcher.submitExternalRequest(3, Direction.UP);
   
   2. ExternalDispatcher delegates to ElevatorSelectionStrategy:
      ElevatorController controller = selectionStrategy.selectElevator(3, Direction.UP, elevatorControllerList);
      // NearestElevatorSelectionStrategy picks the closest available car
   
   3. Selected Controller pushes request into its PriorityQueue:
      controller.upMinPQ.offer(3); // Queued for upward travel
*/

// Controller executes SCAN motion stops:
controller.controlElevator(); // Elevator travels to Floor 3, opens/closes doors

// [PATTERN: DISPATCHER PATTERN - INTERNAL CAR BUTTON]
// Passenger steps inside Elevator Car 1 and selects Floor 5:
ElevatorCar car1 = controller.elevatorCar;
car1.pressButton(5);

/* Inside car1.pressButton(5):
   1. Car delegates to InternalButtons:
      internalButtons.pressButton(5, car1);
   2. InternalButtons delegates to InternalDispatcher:
      internalDispatcher.submitInternalRequest(5, car1);
   3. Directly queues destination 5 into controller 1's upMinPQ:
      controller.upMinPQ.offer(5);
*/

// Controller moves car to destination:
controller.controlElevator(); // Car moves up to Floor 5, updates display, arrives!
```
