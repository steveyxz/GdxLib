package me.partlysunny.gdxlib.ecs.systems.ai;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.ai.SteeringComponent;
import me.partlysunny.gdxlib.ecs.component.physics.Box2DPhysicsComponent;
import me.partlysunny.gdxlib.ecs.component.physics.PhysicsComponent;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;
import me.partlysunny.gdxlib.util.VectorMaths;

public class SteeringMovementSystem extends IteratingSystem {

    public SteeringMovementSystem() {
        super(Family.all(SteeringComponent.class, Box2DPhysicsComponent.class, TransformComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SteeringComponent entitySteering = Mappers.get(SteeringComponent.class, entity);
        TransformComponent entityTransform = Mappers.get(TransformComponent.class, entity);

        Entity target = entitySteering.getTarget();
        TransformComponent playerTransform = Mappers.get(TransformComponent.class, target);

        float dist = VectorMaths.distance(playerTransform.getPosition(), entityTransform.getPosition());
        if (dist <= entitySteering.getViewRange()) {
            if (entitySteering.getBehavior() != null) {
                entitySteering.getBehavior().calculateSteering(entitySteering.getSteeringOutput());
                applySteering(entitySteering, entity, deltaTime);
            }
        } else {
            // TODO Wander behaviour support
        }

    }

    private void applySteering(SteeringComponent steering, Entity e, float deltaTime) {
        PhysicsComponent body = Mappers.get(Box2DPhysicsComponent.class, e);
        boolean accelerations = false;

        // If there is any movement, apply the linear velocity scaled by delta time
        if (!steering.getSteeringOutput().isZero()) {
            Vector2 force = steering.getSteeringOutput().linear.scl(steering.getSpeedMultiplier());
            body.applyForceToCenter(force);
            accelerations = true;
        }

        // If there is any rotation, apply the angular velocity scaled by delta time
        if (steering.getSteeringOutput().angular != 0) {
            body.setAngularVelocity(steering.getSteeringOutput().angular);
            accelerations = true;
        } else {
            // Otherwise we face the direction the linear velocity is going if applicable
            /*Vector2 linVel = body.getLinearVelocity();
            if (!linVel.isZero()) {
                float newOrientation = steering.vectorToAngle(linVel);
                body.setAngularVelocity((newOrientation - body.getAngularVelocity()) * deltaTime);
                body.setRotationRads(newOrientation);
            }*/
        }

        // If we moved, we clamp the speed to max speed
        if (accelerations) {
            //Linear
            Vector2 velocity = body.getLinearVelocity();
            float currentSpeedSquare = velocity.len2();
            if (currentSpeedSquare > steering.getMaxLinearSpeed() * steering.getMaxLinearSpeed()) {
                Vector2 scl = velocity.scl(steering.getMaxLinearSpeed() / (float) Math.sqrt(currentSpeedSquare));
                body.setLinearVelocity(scl);
            }

            //Angular
            if (body.getAngularVelocity() > steering.getMaxAngularSpeed()) {
                body.setAngularVelocity(steering.getMaxAngularSpeed());
            }
        }
    }
}
