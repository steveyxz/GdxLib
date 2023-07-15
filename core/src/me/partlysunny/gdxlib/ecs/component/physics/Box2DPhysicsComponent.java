package me.partlysunny.gdxlib.ecs.component.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import me.partlysunny.gdxlib.util.FastTrig;

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

    public void setLinkedBody(Body linkedBody) {
        this.linkedBody = linkedBody;
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
    public void applyLinearImpulse(Vector2 impulse) {
        linkedBody.applyLinearImpulse(impulse, linkedBody.getWorldCenter(), true);
    }

    @Override
    public void applyForceToCenter(Vector2 force) {
        linkedBody.applyForceToCenter(force, true);
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
    public void applyAngularImpulse(float impulse) {
        linkedBody.applyAngularImpulse(impulse, true);
    }

    @Override
    public void applyTorque(float torque) {
        linkedBody.applyTorque(torque, true);
    }

    @Override
    public Vector2 getPosition() {
        return linkedBody.getPosition();
    }

    @Override
    public Vector2 getWorldCenter() {
        return linkedBody.getWorldCenter();
    }

    @Override
    public float getRotation() {
        return (float) FastTrig.toDegrees(linkedBody.getAngle());
    }

    @Override
    public float getRotationRads() {
        return linkedBody.getAngle();
    }

    @Override
    public void setUserData(Object obj) {
        linkedBody.setUserData(obj);
    }

    @Override
    public Object getUserData() {
        return linkedBody.getUserData();
    }

    @Override
    public void setRotationRads(float orientation) {
        linkedBody.setTransform(linkedBody.getPosition(), orientation);
    }

    @Override
    public void setRotation(float orientation) {
        linkedBody.setTransform(linkedBody.getPosition(), (float) FastTrig.toRadians(orientation));
    }

    public float getDecelerationRate() {
        return decelerationRate;
    }

    public void setDecelerationRate(float decelerationRate) {
        this.decelerationRate = decelerationRate;
    }

    @Override
    public void reset() {
        linkedBody = null;
        decelerationRate = 0;
    }
}
