package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

public class Floor {

    int floorNumber;
    ExternalDispatcher externalDispatcher;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Floor(int floorNumber, ExternalDispatcher externalDispatcher) {
        this.floorNumber = floorNumber;
        this.externalDispatcher = externalDispatcher;
    }

    public void setExternalDispatcher(ExternalDispatcher externalDispatcher) {
        this.externalDispatcher = externalDispatcher;
    }

    public ExternalDispatcher getExternalDispatcher() {
        return externalDispatcher;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void pressButton(Direction direction) {
        if (externalDispatcher != null) {
            externalDispatcher.submitExternalRequest(floorNumber, direction);
        } else {
            System.out.println("Floor " + floorNumber + ": No dispatcher connected to building elevators.");
        }
    }
}
