package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

import java.util.List;

public class InternalDispatcher {

    List<ElevatorController> elevatorControllerList = ElevatorCreator.elevatorControllerList;

    public InternalDispatcher() {}

    public InternalDispatcher(List<ElevatorController> elevatorControllerList) {
        this.elevatorControllerList = elevatorControllerList != null ? elevatorControllerList : ElevatorCreator.elevatorControllerList;
    }

    public void setElevatorControllerList(List<ElevatorController> elevatorControllerList) {
        this.elevatorControllerList = elevatorControllerList;
    }

    public void submitInternalRequest(int floor, ElevatorCar elevatorCar) {
        if (elevatorCar.elevatorController != null) {
            elevatorCar.elevatorController.submitInternalRequest(floor);
            return;
        }
        if (elevatorControllerList != null) {
            for (ElevatorController controller : elevatorControllerList) {
                if (controller.elevatorCar.id == elevatorCar.id) {
                    controller.submitInternalRequest(floor);
                    break;
                }
            }
        }
    }
}
