package me.partlysunny.gdxlib.ecs.component.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Box2DPhysicsComponent implements PhysicsComponent {

    private Body linkedBody;
    private float decelerationRate;

    public Box2DPhysicsComponent(Body linkedBody, float decelerationRate) {
        this.linkedBody = linkedBody;
        this.decelerationRate = decelerationRate;
    }

    public Box2DPhysicsComponent() {
    }

    public Body getLinkedBody() {
        return linkedBody;
    }

    @Override
    public Vector2 getLinearVelocity() {
        return linkedBody.getLinearVelocity();
    }

    @Override
    public void setLinearVelocity(Vector2 velocity) {
        linkedBody.setLinearVelocity(velocity);
    }

    @Override
    public float getAngularVelocity() {
        return linkedBody.getAngularVelocity();
    }

    @Override
    public void setAngularVelocity(float velocity) {
        linkedBody.setAngularVelocity(velocity);
    }

    @Override
    public Vector2 getPosition() {
        return linkedBody.getPosition();
    }

    @Override
    public float getRotation() {
        return linkedBody.getAngle();
    }

    public float getDecelerationRate() {
        return decelerationRate;
    }

    public void setDecelerationRate(float decelerationRate) {
        this.decelerationRate = decelerationRate;
    }
}
