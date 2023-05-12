package me.partlysunny.gdxlib.control.action;

import me.partlysunny.gdxlib.control.controller.ControllerHandler;

public class ActionDispatcher {

    private final ActionMap actionMap;
    private final ActionContext actionContext;
    private final ControllerHandler controllerHandler;

    public ActionDispatcher(ActionMap actionMap, ActionContext actionContext, ControllerHandler controllerHandler) {
        this.actionMap = actionMap;
        this.actionContext = actionContext;
        this.controllerHandler = controllerHandler;
    }

    public void update() {
        for (String action : actionMap) {
            if (actionMap.isActive(action, actionContext)) {
                controllerHandler.update(action);
            }
        }
    }


}
