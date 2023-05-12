package me.partlysunny.gdxlib.control.controller;

import me.partlysunny.gdxlib.ecs.component.control.EntityController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerHandler {

    private final Map<String, List<Controller>> controllers = new HashMap<>();

    public void addController(Controller controller, String action) {
        List<Controller> list = controllers.computeIfAbsent(action, k -> new ArrayList<>());
        list.add(controller);
    }

    public void addControllers(Controller controller, String... actions) {
        for (String action : actions) {
            addController(controller, action);
        }
    }

    public void update(String action) {
        List<Controller> list = controllers.get(action);
        if (list != null) {
            for (Controller controller : list) {
                controller.update(action);
            }
        }
    }

    public void removeController(EntityController controller) {
        for (List<Controller> list : controllers.values()) {
            list.remove(controller);
        }
    }
}
