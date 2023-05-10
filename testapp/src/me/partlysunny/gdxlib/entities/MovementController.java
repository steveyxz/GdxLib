package me.partlysunny.gdxlib.entities;

import com.badlogic.ashley.core.Entity;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.control.EntityController;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;
import me.partlysunny.gdxlib.util.Debug;

public class MovementController extends EntityController {
    public MovementController(Entity entity) {
        super(entity, "up", "down", "left", "right");
    }

    @Override
    protected void update(String action, Entity entity) {
        TransformComponent transformComponent = Mappers.get(TransformComponent.class, entity);
        float speed = 10;
        switch (action) {
            case "up":
                transformComponent.getPosition().y += speed;
                break;
            case "down":
                transformComponent.getPosition().y -= speed;
                break;
            case "left":
                transformComponent.getPosition().x -= speed;
                break;
            case "right":
                transformComponent.getPosition().x += speed;
                break;
        }
    }
}
