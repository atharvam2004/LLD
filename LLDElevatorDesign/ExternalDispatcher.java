package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

import java.util.List;

public class ExternalDispatcher {

    private ElevatorSelectionStrategy selectionStrategy;
    private List<ElevatorController> elevatorControllerList = ElevatorCreator.elevatorControllerList;

    public ExternalDispatcher() {
        this.selectionStrategy = new OddEvenSelectionStrategy();
    }

    public ExternalDispatcher(ElevatorSelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public void setSelectionStrategy(ElevatorSelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public void submitExternalRequest(int floor, Direction direction) {
        ElevatorController selectedController = selectionStrategy.selectElevator(floor, direction, elevatorControllerList);
        if (selectedController != null) {
            selectedController.submitExternalRequest(floor, direction);
        }
    }
}
