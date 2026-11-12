package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

public class InternalButtons {

    private InternalDispatcher dispatcher;
    private int[] availableButtons = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private int buttonSelected;

    public InternalButtons() {
        this.dispatcher = new InternalDispatcher();
    }

    public InternalButtons(InternalDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void setDispatcher(InternalDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void pressButton(int destination, ElevatorCar elevatorCar) {
        if (dispatcher != null) {
            dispatcher.submitInternalRequest(destination, elevatorCar);
        }
    }
}
