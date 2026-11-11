package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

import java.util.List;

public class OddEvenSelectionStrategy implements ElevatorSelectionStrategy {

    @Override
    public ElevatorController selectElevator(int floor, Direction direction, List<ElevatorController> elevatorControllerList) {
        for (ElevatorController elevatorController : elevatorControllerList) {
            int elevatorID = elevatorController.elevatorCar.id;
            if (elevatorID % 2 == 1 && floor % 2 == 1) {
                return elevatorController;
            } else if (elevatorID % 2 == 0 && floor % 2 == 0) {
                return elevatorController;
            }
        }
        return elevatorControllerList.isEmpty() ? null : elevatorControllerList.get(0);
    }
}

