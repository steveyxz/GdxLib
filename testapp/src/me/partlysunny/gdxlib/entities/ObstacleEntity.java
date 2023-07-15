package me.partlysunny.gdxlib.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.ai.SteerableObjectComponent;
import me.partlysunny.gdxlib.ecs.component.physics.Box2DPhysicsComponent;
import me.partlysunny.gdxlib.ecs.entity.ShapeEntityProvider;
import me.partlysunny.gdxlib.util.Physics;
import me.partlysunny.gdxlib.util.ShapeBuilder;

public class ObstacleEntity extends ShapeEntityProvider {
    public ObstacleEntity(GameWorld world) {
        super(world);
    }

    @Override
    protected float getDecelerateRate() {
        return 0.95f;
    }

    @Override
    protected BodyDef.BodyType getBodyType() {
        return BodyDef.BodyType.KinematicBody;
    }

    @Override
    protected Shape getShape() {
        return ShapeBuilder.nSidedPolygon(Vector2.Zero, 3, Physics.toMeters(100));
    }

    @Override
    protected Color getColor() {
        return Color.GOLD;
    }

    @Override
    protected void addExtraComponents(Entity e) {
        SteerableObjectComponent steerableObjectComponent = world.getEntityWorld().createComponent(SteerableObjectComponent.class);
        steerableObjectComponent.init(Mappers.get(Box2DPhysicsComponent.class, e), Physics.toMeters(100));
    }
}
