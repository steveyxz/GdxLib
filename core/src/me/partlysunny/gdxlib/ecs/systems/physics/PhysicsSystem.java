package me.partlysunny.gdxlib.ecs.systems.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.physics.Box2DPhysicsComponent;
import me.partlysunny.gdxlib.ecs.component.physics.CustomPhysicsComponent;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;

public class PhysicsSystem extends IteratingSystem {
    public PhysicsSystem() {
        super(Family.all(CustomPhysicsComponent.class, TransformComponent.class).exclude(Box2DPhysicsComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CustomPhysicsComponent physicsComponent = Mappers.get(CustomPhysicsComponent.class, entity);
        TransformComponent transformComponent = Mappers.get(TransformComponent.class, entity);
        transformComponent.setPosition(transformComponent.getPosition().add(physicsComponent.getLinearVelocity().scl(deltaTime)));
        transformComponent.setRotation(transformComponent.getRotation() + physicsComponent.getAngularVelocity() * deltaTime);
    }
}
