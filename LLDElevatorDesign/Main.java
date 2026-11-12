package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]) {

        // 1. Setup Building Floors
        List<Floor> floorList = new ArrayList<>();
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        Floor floor3 = new Floor(3);
        Floor floor4 = new Floor(4);
        Floor floor5 = new Floor(5);
        floorList.add(floor1);
        floorList.add(floor2);
        floorList.add(floor3);
        floorList.add(floor4);
        floorList.add(floor5);

        // 2. Setup Elevator Bank (Cars & Controllers)
        List<ElevatorController> elevatorControllers = ElevatorCreator.createElevatorControllers(2);

        // 3. Clean Architecture: Building acts as Aggregate Root
        // Building owns the floors, the elevator bank, and injects the shared
        // ExternalDispatcher
        Building building = new Building(floorList, elevatorControllers, new NearestElevatorSelectionStrategy());

        System.out.println("=== Clean Architecture: Elevators Belong to the Building ===");
        int totalFloors = building.getAllFloorList().size();
        int totalElevators = building.getElevatorControllerList().size();
        System.out.println("Building initialized with " + totalFloors + " floors and " + totalElevators + " elevator controllers.");

        // 4. Passenger at Floor 3 calls an elevator going UP
        System.out.println("\n--- 1. Hall Call: Passenger at Floor 3 presses UP ---");
        floor3.pressButton(Direction.UP);

        // 5. Controller executes SCAN stops
        ElevatorController controller1 = building.getElevatorControllerList().get(0);
        controller1.controlElevator();

        // 6. Passenger steps inside Elevator Car 1 and selects Floor 5
        System.out.println("\n--- 2. Inside Elevator Car 1: Passenger selects Floor 5 ---");
        ElevatorCar car1 = controller1.elevatorCar;
        car1.pressButton(5);
        controller1.controlElevator();

        System.out.println("\n--- Service Complete: Elevator arrived at Floor " + car1.currentFloor + " ---");
    }
}
