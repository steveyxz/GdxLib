package me.partlysunny.gdxlib.ecs.component.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public interface PhysicsComponent extends Component {

    Vector2 getLinearVelocity();

    void setLinearVelocity(Vector2 velocity);

    float getAngularVelocity();

    void setAngularVelocity(float velocity);

}
