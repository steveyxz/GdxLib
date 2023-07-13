package me.partlysunny.gdxlib.control.action;

import me.partlysunny.gdxlib.control.controller.ControllerHandler;

/**
 * Dispatches actions to controllers.
 */
public class ActionDispatcher {

    private final ActionMap actionMap;
    private final ActionContext actionContext;
    private final ControllerHandler controllerHandler;

    public ActionDispatcher(ActionMap actionMap, ActionContext actionContext, ControllerHandler controllerHandler) {
        this.actionMap = actionMap;
        this.actionContext = actionContext;
        this.controllerHandler = controllerHandler;
    }

    /**
     * Goes through all actions in the action map and
     * updates the controller handler with the actions that are active.
     */
    public void update() {
        for (String action : actionMap) {
            if (actionMap.isActive(action, actionContext)) {
                controllerHandler.update(action);
            }
        }
    }


}
