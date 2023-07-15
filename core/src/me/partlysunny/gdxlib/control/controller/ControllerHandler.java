package me.partlysunny.gdxlib.control.controller;

import me.partlysunny.gdxlib.ecs.component.control.EntityController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles controllers.
 * Use this class to add controllers to actions and update them.
 */
public class ControllerHandler {

    private final Map<String, List<Controller>> controllers = new HashMap<>();

    /**
     * Subscribes a controller to an action.
     *
     * @param controller The controller to subscribe.
     * @param action     The action to subscribe to.
     */
    public void addController(Controller controller, String action) {
        List<Controller> list = controllers.computeIfAbsent(action, k -> new ArrayList<>());
        list.add(controller);
    }

    /**
     * Subscribes a controller to multiple actions.
     *
     * @param controller The controller to subscribe.
     * @param actions    The actions to subscribe to.
     */
    public void addControllers(Controller controller, String... actions) {
        for (String action : actions) {
            addController(controller, action);
        }
    }

    /**
     * Updates all controllers subscribed to the given action.
     *
     * @param action The action to update.
     */
    public void update(String action) {
        List<Controller> list = controllers.get(action);
        if (list != null) {
            for (Controller controller : list) {
                controller.update(action);
            }
        }
    }

    /**
     * Removes a controller from all actions.
     *
     * @param controller The controller to remove.
     */
    public void removeController(EntityController controller) {
        for (List<Controller> list : controllers.values()) {
            list.remove(controller);
        }
    }
}
