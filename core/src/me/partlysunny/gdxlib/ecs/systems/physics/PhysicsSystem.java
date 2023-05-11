package me.partlysunny.gdxlib.ecs.systems.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.physics.Box2DPhysicsComponent;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;

public class PhysicsSystem extends IteratingSystem {

    public PhysicsSystem() {
        super(Family.all(Box2DPhysicsComponent.class, TransformComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = Mappers.get(TransformComponent.class, entity);
        Box2DPhysicsComponent physicsComponent = Mappers.get(Box2DPhysicsComponent.class, entity);
        Body attachedBody = physicsComponent.getLinkedBody();
        attachedBody.setLinearVelocity(attachedBody.getLinearVelocity().scl(physicsComponent.getDecelerationRate()));
    }
}
