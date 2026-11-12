package com.conceptandcoding.LowLevelDesign.LLDElevatorDesign;

/**
 * InternalDispatcher — REMOVED / empty placeholder.
 *
 * Previously this class routed internal button presses from the cabin
 * to the ElevatorController. That indirection is no longer needed:
 * InternalButtons now calls elevatorCar.getElevatorController().submitInternalRequest()
 * directly, since a car always knows its own controller (1:1 relationship).
 *
 * This file is kept only so that any stale references in the IDE don't break
 * immediately; it can be deleted once confirmed nothing else uses it.
 */
public class InternalDispatcher {
    // Intentionally empty — see InternalButtons for the new direct flow.
}
