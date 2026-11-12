package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

/**
 * InternalButtons — the physical keypad inside the elevator car.
 *
 * Physical analogy: the brass/plastic number buttons on the cabin wall.
 * - Holds the list of valid floor buttons (1-10).
 * - Validates the pressed floor.
 * - Directly tells the car's own ElevatorController about the destination.
 *   No dispatcher middleman needed: the car always knows its own controller.
 *
 * Flow: Passenger presses button → InternalButtons.pressButton()
 *       → elevatorCar.getElevatorController().submitInternalRequest(floor)
 */
public class InternalButtons {

    int[] availableButtons = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int buttonSelected;

    public void pressButton(int destination, ElevatorCar elevatorCar) {
        this.buttonSelected = destination;
        // Direct call: button → car's controller (no dispatcher needed)
        if (elevatorCar != null && elevatorCar.getElevatorController() != null) {
            elevatorCar.getElevatorController().submitInternalRequest(destination);
        } else {
            System.err.println("Elevator controller not available for internal request.");
        }
    }
}
