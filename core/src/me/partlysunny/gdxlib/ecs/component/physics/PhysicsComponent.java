package me.partlysunny.gdxlib.ecs.component.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public interface PhysicsComponent extends Component {

    /**
     * Gets the linear velocity of the entity
     * @return The linear velocity in m/s
     */
    Vector2 getLinearVelocity();

    void setLinearVelocity(Vector2 velocity);

    float getAngularVelocity();

    void setAngularVelocity(float velocity);

    Vector2 getPosition();

    float getRotation();

}
