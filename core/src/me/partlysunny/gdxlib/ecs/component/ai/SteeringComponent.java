package me.partlysunny.gdxlib.ecs.component.ai;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import me.partlysunny.gdxlib.GdxGame;
import me.partlysunny.gdxlib.ecs.component.ai.behaviours.BehaviourProvider;
import me.partlysunny.gdxlib.ecs.component.physics.PhysicsComponent;
import me.partlysunny.gdxlib.util.VectorMaths;

public class SteeringComponent implements Component, Pool.Poolable, Steerable<Vector2> {

    protected PhysicsComponent physicsBinding;
    protected boolean tagged;
    protected float maxLinearSpeed, maxLinearAcceleration;
    protected float maxAngularSpeed, maxAngularAcceleration;
    protected float speedMultiplier;
    protected float zeroLinearSpeedThreshold;
    protected SteeringBehavior<Vector2> behavior;
    protected SteeringAcceleration<Vector2> steeringOutput;
    protected float viewRange;
    protected Entity target;
    private float boundingRadius;

    public SteeringComponent() {
        this(null, null, 0, 0, null);
    }

    public SteeringComponent(Entity target, PhysicsComponent physicsBinding, float viewRange, float boundingRadius, BehaviourProvider behaviourProvider) {
        init(target, physicsBinding, viewRange, boundingRadius, behaviourProvider);
    }

    public void init(Entity target, PhysicsComponent physicsBinding, float viewRange, float boundingRadius, BehaviourProvider behaviourProvider) {
        this.target = target;
        this.physicsBinding = physicsBinding;
        this.boundingRadius = boundingRadius;

        this.maxLinearSpeed = 10;
        this.maxLinearAcceleration = 100;
        this.maxAngularSpeed = 30;
        this.maxAngularAcceleration = 5;
        this.zeroLinearSpeedThreshold = 0.1f;

        this.tagged = false;

        this.steeringOutput = new SteeringAcceleration<>(new Vector2());
        if (physicsBinding != null) this.physicsBinding.setUserData(this);

        this.viewRange = viewRange;
        if (behaviourProvider != null) this.behavior = behaviourProvider.getBehaviour(this);
    }

    public float getSpeedMultiplier() {
        return speedMultiplier;
    }

    public void setSpeedMultiplier(float speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    @Override
    public Vector2 getLinearVelocity() {
        return physicsBinding.getLinearVelocity();
    }

    @Override
    public float getAngularVelocity() {
        return physicsBinding.getAngularVelocity();
    }

    @Override
    public float getBoundingRadius() {
        return boundingRadius;
    }

    public void setBoundingRadius(float boundingRadius) {
        this.boundingRadius = boundingRadius;
    }

    @Override
    public boolean isTagged() {
        return tagged;
    }

    @Override
    public void setTagged(boolean tagged) {
        this.tagged = tagged;
    }

    @Override
    public float getZeroLinearSpeedThreshold() {
        return zeroLinearSpeedThreshold;
    }

    @Override
    public void setZeroLinearSpeedThreshold(float value) {
        this.zeroLinearSpeedThreshold = value;
    }

    @Override
    public float getMaxLinearSpeed() {
        return maxLinearSpeed;
    }

    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {
        this.maxLinearSpeed = maxLinearSpeed;
    }

    @Override
    public float getMaxLinearAcceleration() {
        return maxLinearAcceleration;
    }

    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {
        this.maxLinearAcceleration = maxLinearAcceleration;
    }

    @Override
    public float getMaxAngularSpeed() {
        return maxAngularSpeed;
    }

    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {
        this.maxAngularSpeed = maxAngularSpeed;
    }

    @Override
    public float getMaxAngularAcceleration() {
        return maxAngularAcceleration;
    }

    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {
        this.maxAngularAcceleration = maxAngularAcceleration;
    }

    @Override
    public Vector2 getPosition() {
        return physicsBinding.getWorldCenter();
    }

    @Override
    public float getOrientation() {
        return physicsBinding.getRotationRads();
    }

    @Override
    public void setOrientation(float orientation) {
        physicsBinding.setRotationRads(orientation);
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
    public Location<Vector2> newLocation() {
        if (GdxGame.getInstance().getCurrentScene() != null)
            return GdxGame.getInstance().getCurrentScene().getGameWorld().getEntityWorld().createComponent(SteeringComponent.class);
        return new SteeringComponent();
    }

    public PhysicsComponent getPhysicsBinding() {
        return physicsBinding;
    }

    public void setPhysicsBinding(PhysicsComponent physicsBinding) {
        this.physicsBinding = physicsBinding;
    }

    public SteeringAcceleration<Vector2> getSteeringOutput() {
        return steeringOutput;
    }

    public void setSteeringOutput(SteeringAcceleration<Vector2> steeringOutput) {
        this.steeringOutput = steeringOutput;
    }

    public float getViewRange() {
        return viewRange;
    }

    public void setViewRange(float viewRange) {
        this.viewRange = viewRange;
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public SteeringBehavior<Vector2> getBehavior() {
        return behavior;
    }

    public void setBehavior(SteeringBehavior<Vector2> behavior) {
        this.behavior = behavior;
    }

    @Override
    public void reset() {
        physicsBinding = null;
        boundingRadius = 0;
        tagged = false;
        maxLinearSpeed = 0;
        maxLinearAcceleration = 0;
        maxAngularSpeed = 0;
        maxAngularAcceleration = 0;
        zeroLinearSpeedThreshold = 0;
        behavior = null;
        steeringOutput = null;
        viewRange = 0;
    }
}
