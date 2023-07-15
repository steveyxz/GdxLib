package me.partlysunny.gdxlib.ecs.component.ai;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.steer.SteerableAdapter;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import me.partlysunny.gdxlib.GdxGame;
import me.partlysunny.gdxlib.ecs.component.physics.PhysicsComponent;
import me.partlysunny.gdxlib.util.VectorMaths;

/**
 * Represents an object in the steerable world
 * This is used to allow AI pathfinders to work with objects
 * Add this to things you need AIs to detect in proximity checks and target
 */
public class SteerableObjectComponent extends SteerableAdapter<Vector2> implements Component, Pool.Poolable {

    private PhysicsComponent attachedBody;
    private float boundingRadius;

    public SteerableObjectComponent() {
    }

    public void init(PhysicsComponent body, float boundingRadius) {
        attachedBody = body;
        this.boundingRadius = boundingRadius;
        attachedBody.setUserData(this);
    }

    @Override
    public Vector2 getPosition() {
        return attachedBody.getWorldCenter();
    }

    @Override
    public float getOrientation() {
        return attachedBody.getRotationRads();
    }

    @Override
    public Vector2 getLinearVelocity() {
        return attachedBody.getLinearVelocity();
    }

    @Override
    public float getAngularVelocity() {
        return attachedBody.getAngularVelocity();
    }

    @Override
    public float getBoundingRadius() {
        return boundingRadius;
    }

    @Override
    public Location<Vector2> newLocation() {
        if (GdxGame.getInstance().getCurrentScene() != null)
            return GdxGame.getInstance().getCurrentScene().getGameWorld().getEntityWorld().createComponent(SteerableObjectComponent.class);
        return new SteerableObjectComponent();
    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return VectorMaths.vectorToAngle(vector);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        return VectorMaths.angleToVector(outVector, angle);
    }

    @Override
    public void reset() {
        attachedBody = null;
        boundingRadius = 0;
    }
}
