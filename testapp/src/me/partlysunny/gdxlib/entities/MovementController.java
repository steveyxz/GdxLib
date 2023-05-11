package me.partlysunny.gdxlib.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.control.EntityController;
import me.partlysunny.gdxlib.ecs.component.physics.Box2DPhysicsComponent;
import me.partlysunny.gdxlib.ecs.component.physics.PhysicsComponent;
import me.partlysunny.gdxlib.util.Debug;
import me.partlysunny.gdxlib.util.Physics;

public class MovementController extends EntityController {
    public MovementController(Entity entity) {
        super(entity, "up", "down", "left", "right");
    }

    @Override
    protected void update(String action, Entity entity) {
        PhysicsComponent physics = Mappers.get(Box2DPhysicsComponent.class, entity);
        float speed = Physics.toMeters(100);
        switch (action) {
            case "up":
                physics.setLinearVelocity(new Vector2(physics.getLinearVelocity().x, speed));
                break;
            case "down":
                physics.setLinearVelocity(new Vector2(physics.getLinearVelocity().x, -speed));
                break;
            case "left":
                physics.setLinearVelocity(new Vector2(-speed, physics.getLinearVelocity().y));
                break;
            case "right":
                physics.setLinearVelocity(new Vector2(speed, physics.getLinearVelocity().y));
                break;
            default:
                Debug.logDebug("Unknown action: " + action);
                break;
        }
    }
}
