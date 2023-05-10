package me.partlysunny.gdxlib.ecs.component.control;

import com.badlogic.ashley.core.Component;
import me.partlysunny.gdxlib.control.controller.Controller;

public class ControllerComponent implements Component {

    private EntityController controller;

    public ControllerComponent() {
    }

    public ControllerComponent(EntityController controller) {
        this.controller = controller;
    }

    public EntityController getController() {
        return controller;
    }

    public void setController(EntityController controller) {
        this.controller = controller;
    }
}
