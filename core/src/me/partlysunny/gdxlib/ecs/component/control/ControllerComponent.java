package me.partlysunny.gdxlib.ecs.component.control;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class ControllerComponent implements Component, Pool.Poolable {

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

    @Override
    public void reset() {
        controller = null;
    }
}
