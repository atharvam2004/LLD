package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

import java.util.ArrayList;
import java.util.List;

public class ElevatorCreator {

    // Factory method to instantiate and wire an elevator bank of size count
    public static List<ElevatorController> createElevatorControllers(int count) {
        List<ElevatorController> controllers = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            ElevatorCar car = new ElevatorCar();
            car.id = i;
            ElevatorController controller = new ElevatorController(car);
            controllers.add(controller);
        }
        return controllers;
    }

    // Default 2-elevator bank
    public static List<ElevatorController> elevatorControllerList = createElevatorControllers(2);
}
