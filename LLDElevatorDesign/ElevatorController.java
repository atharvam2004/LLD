package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

import java.util.PriorityQueue;

public class ElevatorController {

    public PriorityQueue<Integer> upMinPQ;
    public PriorityQueue<Integer> downMaxPQ;
    public ElevatorCar elevatorCar;

    public ElevatorController(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
        this.elevatorCar.elevatorController = this;
        this.upMinPQ = new PriorityQueue<>();
        this.downMaxPQ = new PriorityQueue<>((a, b) -> b - a);
    }

    public void submitExternalRequest(int floor, Direction direction) {
        if (direction == Direction.DOWN) {
            downMaxPQ.offer(floor);
        } else {
            upMinPQ.offer(floor);
        }
    }

    public void submitInternalRequest(int floor) {
        if (floor >= elevatorCar.currentFloor) {
            upMinPQ.offer(floor);
        } else {
            downMaxPQ.offer(floor);
        }
    }

    public void controlElevator() {
        while (!upMinPQ.isEmpty() || !downMaxPQ.isEmpty()) {
            if (elevatorCar.elevatorDirection == Direction.UP) {
                while (!upMinPQ.isEmpty()) {
                    int nextFloor = upMinPQ.poll();
                    elevatorCar.moveElevator(Direction.UP, nextFloor);
                }
                if (!downMaxPQ.isEmpty()) {
                    elevatorCar.elevatorDirection = Direction.DOWN;
                }
            } else {
                while (!downMaxPQ.isEmpty()) {
                    int nextFloor = downMaxPQ.poll();
                    elevatorCar.moveElevator(Direction.DOWN, nextFloor);
                }
                if (!upMinPQ.isEmpty()) {
                    elevatorCar.elevatorDirection = Direction.UP;
                }
            }
        }
        elevatorCar.elevatorState = ElevatorState.IDLE;
    }
}
