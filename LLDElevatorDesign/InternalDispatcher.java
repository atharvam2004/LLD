package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

import java.util.ArrayList;
import java.util.List;

public class InternalDispatcher {

    public List<ElevatorController> elevatorControllerList;

    public InternalDispatcher() {
        this.elevatorControllerList = new ArrayList<>();
    }

    public InternalDispatcher(List<ElevatorController> elevatorControllerList) {
        this.elevatorControllerList = elevatorControllerList != null ? elevatorControllerList : new ArrayList<>();
    }

    public void setElevatorControllerList(List<ElevatorController> elevatorControllerList) {
        this.elevatorControllerList = elevatorControllerList;
    }

    public List<ElevatorController> getElevatorControllerList() {
        return elevatorControllerList;
    }

    public void submitInternalRequest(int floor, ElevatorCar elevatorCar) {
        if (elevatorCar != null && elevatorCar.elevatorController != null) {
            elevatorCar.elevatorController.submitInternalRequest(floor);
            return;
        }
        if (elevatorControllerList != null) {
            for (ElevatorController controller : elevatorControllerList) {
                if (controller.elevatorCar != null && controller.elevatorCar.id == elevatorCar.id) {
                    controller.submitInternalRequest(floor);
                    break;
                }
            }
        }
    }
}
