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

    float getAngularVelocity();

    void setAngularVelocity(float velocity);

    Vector2 getPosition();

    /**
     * Rotation in degrees
     *
     * @return The rotation in degrees, not radians
     */
    float getRotation();

    /**
     * Rotation in radians
     *
     * @return The rotation in radians, not degrees
     */
    float getRotationRads();

}
