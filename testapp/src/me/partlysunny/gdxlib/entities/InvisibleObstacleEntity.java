package me.partlysunny.gdxlib.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.ai.DoNotDodgeComponent;
import me.partlysunny.gdxlib.ecs.component.ai.SteerableObjectComponent;
import me.partlysunny.gdxlib.ecs.component.physics.Box2DPhysicsComponent;
import me.partlysunny.gdxlib.ecs.entity.ShapeEntityProvider;
import me.partlysunny.gdxlib.util.Physics;
import me.partlysunny.gdxlib.util.ShapeBuilder;

public class InvisibleObstacleEntity extends ShapeEntityProvider {
    public InvisibleObstacleEntity(GameWorld world) {
        super(world);
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
        return ShapeBuilder.nSidedPolygon(Vector2.Zero, 3, Physics.toMeters(50));
    }

    @Override
    protected Color getColor() {
        return Color.PURPLE;
    }

    @Override
    protected void addExtraComponents(Entity e) {
        DoNotDodgeComponent doNotDodgeComponent = world.getEntityWorld().createComponent(DoNotDodgeComponent.class);
        SteerableObjectComponent steerableObjectComponent = world.getEntityWorld().createComponent(SteerableObjectComponent.class);
        steerableObjectComponent.init(Mappers.get(Box2DPhysicsComponent.class, e), Physics.toMeters(50));
        e.add(doNotDodgeComponent);
    }
}
