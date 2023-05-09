package me.partlysunny.gdxlib.ecs.component.physics;

import com.badlogic.gdx.math.Vector2;

public class CustomPhysicsComponent implements PhysicsComponent {

    private Vector2 linearVelocity;
    private float angularVelocity;

    @Override
    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

    @Override
    public void setLinearVelocity(Vector2 velocity) {
        linearVelocity = velocity;
    }

    @Override
    public float getAngularVelocity() {
        return angularVelocity;
    }

    @Override
    public void setAngularVelocity(float velocity) {
        angularVelocity = velocity;
    }
}
