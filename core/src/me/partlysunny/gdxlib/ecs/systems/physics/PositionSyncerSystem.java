package me.partlysunny.gdxlib.ecs.systems.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.physics.Box2DPhysicsComponent;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;
import me.partlysunny.gdxlib.util.Physics;

public class PositionSyncerSystem extends IteratingSystem {
    public PositionSyncerSystem() {
        super(Family.all(TransformComponent.class).one(Box2DPhysicsComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = Mappers.get(TransformComponent.class, entity);
        Box2DPhysicsComponent physicsComponent = Mappers.get(Box2DPhysicsComponent.class, entity);
        syncBox2D(transformComponent, physicsComponent);
    }

    private void syncBox2D(TransformComponent transformComponent, Box2DPhysicsComponent physicsComponent) {
        transformComponent.setPosition(Physics.toPixels(physicsComponent.getPosition()));
        transformComponent.setRotation(physicsComponent.getRotation());
    }
}
