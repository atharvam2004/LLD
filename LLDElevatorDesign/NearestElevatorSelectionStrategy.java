package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

import java.util.List;

public class NearestElevatorSelectionStrategy implements ElevatorSelectionStrategy {

    @Override
    public ElevatorController selectElevator(int floor, Direction direction, List<ElevatorController> elevatorControllerList) {
        ElevatorController selected = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorController controller : elevatorControllerList) {
            int distance = Math.abs(controller.elevatorCar.currentFloor - floor);
            if (distance < minDistance) {
                minDistance = distance;
                selected = controller;
            }
        }
        return selected != null ? selected : (elevatorControllerList.isEmpty() ? null : elevatorControllerList.get(0));
    }
}

