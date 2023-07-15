package me.partlysunny.gdxlib.ecs.component.control;

import com.badlogic.ashley.core.Entity;
import me.partlysunny.gdxlib.GdxGame;
import me.partlysunny.gdxlib.control.controller.Controller;

/**
 * Class to connect ashley ECS with the controller system
 * Should only be used in conjunction with the controller component, otherwise you
 * may find yourself with redundant controllers being created, as this class automatically
 * adds itself to the controller handler.
 */
public abstract class EntityController implements Controller {

    private final Entity entity;

    public EntityController(Entity entity, String... actions) {
        this.entity = entity;
        GdxGame.getInstance().getCurrentScene().getControlHub().getControllerHandler().addControllers(this, actions);
    }

    @Override
    public void update(String action) {
        update(action, entity);
    }

    protected abstract void update(String action, Entity entity);
}
