package me.partlysunny.gdxlib.ecs.component.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public interface PhysicsComponent extends Component, Pool.Poolable {

    /**
     * Gets the linear velocity of the entity
     *
     * @return The linear velocity in m/s
     */
    Vector2 getLinearVelocity();

    void setLinearVelocity(Vector2 velocity);

    void applyLinearImpulse(Vector2 impulse);

    void applyForceToCenter(Vector2 force);

    float getAngularVelocity();

    void setAngularVelocity(float velocity);

    void applyAngularImpulse(float impulse);

    void applyTorque(float torque);

    /**
     * Position in the physics world
     * Will be in world coordinates (metres not pixels)
     *
     * @return The position in the physics world
     */
    Vector2 getPosition();

    /**
     * Center of mass of the entity
     *
     * @return THe center of mass in meters
     */
    Vector2 getWorldCenter();

    /**
     * Rotation in degrees
     *
     * @return The rotation in degrees, not radians
     */
    float getRotation();

    void setRotation(float orientation);

    /**
     * Rotation in radians
     *
     * @return The rotation in radians, not degrees
     */
    float getRotationRads();

    void setRotationRads(float orientation);

    Object getUserData();

    void setUserData(Object obj);

}
