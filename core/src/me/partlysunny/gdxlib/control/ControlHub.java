package me.partlysunny.gdxlib.control;

import me.partlysunny.gdxlib.control.action.ActionDispatcher;
import me.partlysunny.gdxlib.control.action.ActionMap;
import me.partlysunny.gdxlib.control.action.GdxContext;
import me.partlysunny.gdxlib.control.controller.ControllerHandler;

/**
 * The ControlHub is the central hub for all control related activities. It is responsible for
 * managing the controller handler, action dispatcher, and action map.
 */
public class ControlHub {

    private final ControllerHandler controllerHandler;
    private final ActionDispatcher actionDispatcher;
    private final ActionMap actionMap;

    public ControlHub() {
        controllerHandler = new ControllerHandler();
        actionMap = new ActionMap();
        actionDispatcher = new ActionDispatcher(actionMap, new GdxContext(), controllerHandler);
    }

    public void update() {
        actionDispatcher.update();
    }

    public ControllerHandler getControllerHandler() {
        return controllerHandler;
    }

    public ActionDispatcher getActionDispatcher() {
        return actionDispatcher;
    }

    public ActionMap getActionMap() {
        return actionMap;
    }
}
