package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]) {

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

        Building building = new Building(floorList);

        System.out.println("--- 1. Hall Call: Passenger at Floor 3 presses UP ---");
        floor3.pressButton(Direction.UP);

        // Process request with controller
        ElevatorController controller1 = ElevatorCreator.elevatorControllerList.get(0);
        controller1.controlElevator();

        System.out.println("--- 2. Inside Elevator Car 1: Passenger selects Floor 5 ---");
        ElevatorCar car1 = controller1.elevatorCar;
        car1.pressButton(5);
        controller1.controlElevator();

        System.out.println("--- Elevator service complete at Floor " + car1.currentFloor + " ---");
    }
}
