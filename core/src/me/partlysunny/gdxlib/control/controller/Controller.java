package me.partlysunny.gdxlib.control.controller;

/**
 * Represents a controller that handles actions.
 * You must register the controller with the ControllerManager to receive actions. See {@link ControllerHandler#addController(Controller, String)}.
 */
public interface Controller {
    /**
     * Updates the controller with the given action.
     * The action will only be called into this update method if the controller is registered with the ControllerManager with this specific action
     * @param action the action to update with
     */
    void update(String action);
}
