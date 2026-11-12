package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

public class ElevatorCar {

    public int id;
    public ElevatorDisplay display;
    public InternalButtons internalButtons;
    public ElevatorState elevatorState;
    public int currentFloor;
    public Direction elevatorDirection;
    public ElevatorDoor elevatorDoor;
    public ElevatorController elevatorController;

    public ElevatorCar() {
        display = new ElevatorDisplay();
        internalButtons = new InternalButtons();
        elevatorState = ElevatorState.IDLE;
        currentFloor = 0;
        elevatorDirection = Direction.UP;
        elevatorDoor = new ElevatorDoor();
    }

    public void showDisplay() {
        display.showDisplay();
    }

    public void pressButton(int destination) {
        internalButtons.pressButton(destination, this);
    }

    public void setDisplay() {
        this.display.setDisplay(currentFloor, elevatorDirection);
    }

    public boolean moveElevator(Direction dir, int destinationFloor) {
        int startFloor = currentFloor;
        this.elevatorDirection = dir;
        this.elevatorState = ElevatorState.MOVING;

        if (dir == Direction.UP) {
            for (int i = startFloor; i <= destinationFloor; i++) {
                this.currentFloor = i;
                setDisplay();
                showDisplay();
                if (i == destinationFloor) {
                    this.elevatorDoor.openDoor();
                    this.elevatorDoor.closeDoor();
                    return true;
                }
            }
        }

        if (dir == Direction.DOWN) {
            for (int i = startFloor; i >= destinationFloor; i--) {
                this.currentFloor = i;
                setDisplay();
                showDisplay();
                if (i == destinationFloor) {
                    this.elevatorDoor.openDoor();
                    this.elevatorDoor.closeDoor();
                    return true;
                }
            }
        }
        return false;
    }
}
