package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

import java.util.ArrayList;
import java.util.List;

public class ExternalDispatcher {

    private ElevatorSelectionStrategy selectionStrategy;
    List<ElevatorController> elevatorControllerList = ElevatorCreator.elevatorControllerList;

    public ExternalDispatcher(List<ElevatorController> elevatorControllerList) {
        this(elevatorControllerList, new OddEvenSelectionStrategy());
    }

    public ExternalDispatcher(List<ElevatorController> elevatorControllerList, ElevatorSelectionStrategy selectionStrategy) {
        this.elevatorControllerList = elevatorControllerList != null ? elevatorControllerList : ElevatorCreator.elevatorControllerList;
        this.selectionStrategy = selectionStrategy != null ? selectionStrategy : new OddEvenSelectionStrategy();
    }

    public ExternalDispatcher() {
        this(ElevatorCreator.elevatorControllerList, new OddEvenSelectionStrategy());
    }

    public void setSelectionStrategy(ElevatorSelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public ElevatorSelectionStrategy getSelectionStrategy() {
        return selectionStrategy;
    }

    public void setElevatorControllerList(List<ElevatorController> elevatorControllerList) {
        this.elevatorControllerList = elevatorControllerList;
    }

    public List<ElevatorController> getElevatorControllerList() {
        return elevatorControllerList;
    }

    public void submitExternalRequest(int floor, Direction direction) {
        ElevatorController selectedController = selectionStrategy.selectElevator(floor, direction, elevatorControllerList);
        if (selectedController != null) {
            selectedController.submitExternalRequest(floor, direction);
        }
    }
}
