package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

import java.util.ArrayList;
import java.util.List;

public class Building {

    private List<Floor> floorList;
    private List<ElevatorController> elevatorControllerList;
    private ExternalDispatcher externalDispatcher;

    public Building(List<Floor> floors, List<ElevatorController> controllers) {
        this(floors, controllers, new OddEvenSelectionStrategy());
    }

    public Building(List<Floor> floors, List<ElevatorController> controllers, ElevatorSelectionStrategy strategy) {
        this.floorList = floors != null ? floors : new ArrayList<>();
        this.elevatorControllerList = controllers != null ? controllers : new ArrayList<>();
        this.externalDispatcher = new ExternalDispatcher(this.elevatorControllerList, strategy);

        // Dependency Injection: inject shared building dispatcher into all floors
        for (Floor floor : this.floorList) {
            floor.setExternalDispatcher(this.externalDispatcher);
        }
    }

    public Building(List<Floor> floors) {
        this(floors, ElevatorCreator.createElevatorControllers(2));
    }

    public void addFloor(Floor newFloor) {
        if (newFloor != null) {
            newFloor.setExternalDispatcher(this.externalDispatcher);
            floorList.add(newFloor);
        }
    }

    public void removeFloor(Floor removeFloor) {
        floorList.remove(removeFloor);
    }

    public List<Floor> getAllFloorList() {
        return floorList;
    }

    public List<ElevatorController> getElevatorControllerList() {
        return elevatorControllerList;
    }

    public ExternalDispatcher getExternalDispatcher() {
        return externalDispatcher;
    }
}
