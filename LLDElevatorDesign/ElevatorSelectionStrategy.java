package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

import java.util.List;

public interface ElevatorSelectionStrategy {
    ElevatorController selectElevator(int floor, Direction direction, List<ElevatorController> elevatorControllerList);
}

