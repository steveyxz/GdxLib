package me.partlysunny.gdxlib.ecs.systems.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.physics.Box2DPhysicsComponent;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;

public class PositionSyncerSystem extends IteratingSystem {
    public PositionSyncerSystem() {
        super(Family.all(TransformComponent.class, Box2DPhysicsComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = Mappers.get(TransformComponent.class, entity);
        Box2DPhysicsComponent physicsComponent = Mappers.get(Box2DPhysicsComponent.class, entity);

        transformComponent.setPosition(physicsComponent.getLinkedBody().getPosition());
        transformComponent.setRotation(physicsComponent.getLinkedBody().getAngle());
    }
}
