package me.partlysunny.gdxlib.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.ai.SteerableObjectComponent;
import me.partlysunny.gdxlib.ecs.component.ai.SteeringComponent;
import me.partlysunny.gdxlib.ecs.component.ai.behaviours.BasicPursueBehaviour;
import me.partlysunny.gdxlib.ecs.component.ai.behaviours.BasicWanderBehaviour;
import me.partlysunny.gdxlib.ecs.component.ai.behaviours.RaycastAvoidanceSettings;
import me.partlysunny.gdxlib.ecs.component.physics.Box2DPhysicsComponent;
import me.partlysunny.gdxlib.ecs.entity.ShapeEntityProvider;
import me.partlysunny.gdxlib.util.Physics;
import me.partlysunny.gdxlib.util.ShapeBuilder;

public class EnemyEntity extends ShapeEntityProvider {

    private final Entity player;

    private EnemyEntity(GameWorld world) {
        super(world);
        throw new IllegalStateException("You probably used SimpleEntityProvider.createSingular(). Don't use that for this entity");
    }

    public EnemyEntity(GameWorld world, Entity player) {
        super(world);
        this.player = player;
    }

    @Override
    protected float getDecelerateRate() {
        return 0.95f;
    }

    @Override
    protected BodyDef.BodyType getBodyType() {
        return BodyDef.BodyType.DynamicBody;
    }

    @Override
    protected Shape getShape() {
//        return ShapeBuilder.radialSquare(Vector2.Zero, Physics.toMeters(50));
        return ShapeBuilder.circle(Vector2.Zero, Physics.toMeters(50));
    }

    @Override
    protected Color getColor() {
        return Color.YELLOW;
    }

    @Override
    protected void addExtraComponents(Entity e) {
        SteeringComponent steeringComponent = world.getEntityWorld().createComponent(SteeringComponent.class);
        RaycastAvoidanceSettings raycastAvoidanceSettings = new RaycastAvoidanceSettings(
                Physics.toMeters(200),
                Physics.toMeters(80),
                35 * MathUtils.degreesToRadians,
                Physics.toMeters(1000)
        );
        BasicWanderBehaviour pursueBehaviour = new BasicWanderBehaviour(
                Physics.toMeters(70),
                Mappers.get(SteerableObjectComponent.class, player),
                raycastAvoidanceSettings);
        steeringComponent.init(player,
                Mappers.get(Box2DPhysicsComponent.class, e),
                2000,
                Physics.toMeters(50),
                pursueBehaviour);
        steeringComponent.setSpeedMultiplier(1);
        steeringComponent.setMaxLinearSpeed(Physics.toMeters(200));
        steeringComponent.setMaxLinearAcceleration(Physics.toMeters(200));
        steeringComponent.setMaxAngularSpeed(MathUtils.degreesToRadians * 90);
        steeringComponent.setMaxAngularAcceleration(MathUtils.degreesToRadians * 90);
        e.add(steeringComponent);
    }
}
