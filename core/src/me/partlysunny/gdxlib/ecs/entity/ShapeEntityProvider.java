package me.partlysunny.gdxlib.ecs.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.physics.providers.Box2DPhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.physics.providers.PhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.PolygonalTextureComponentProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.RegionalTextureComponentProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.RendererProvider;
import me.partlysunny.gdxlib.ecs.component.standard.ScaleComponent;
import me.partlysunny.gdxlib.util.*;
import org.jetbrains.annotations.Nullable;

public abstract class ShapeEntityProvider extends SimpleEntityProvider {
    public ShapeEntityProvider(GameWorld world) {
        super(world);
    }

    @Override
    protected RendererProvider getRendererProvider() {
        Shape shape = getShape();
        if (shape instanceof CircleShape) {
            return new RegionalTextureComponentProvider(ShapeTextureGenerator.getCircle(getTextureRadius(), Colors.toRGBAInt(getColor())));
        } else if (shape instanceof PolygonShape) {
            PolygonShape polygonShape = (PolygonShape) shape;
            polygonShape = PolygonHelper.scale(polygonShape, Physics.PPM);
            return new PolygonalTextureComponentProvider(getColor(), polygonShape);
        }
        throw new IllegalArgumentException("Shape must be a circle or polygon");
    }

    @Nullable
    @Override
    protected PhysicsProvider getPhysicsProvider(Vector2 originPosition) {
        return new Box2DPhysicsProvider(BodyBuilder.create(originPosition).bodyType(getBodyType()).addShape(getShape()).build(), getDecelerateRate());
    }

    /**
     * Get the radius of the texture to generate, only applicable if your shape is a circle.
     * This is used to generate a texture for the circle. Higher values means higher quality circle.
     * The default value is 1000.
     */
    protected int getTextureRadius() {
        return 1000;
    }

    protected abstract float getDecelerateRate();

    protected abstract BodyDef.BodyType getBodyType();

    /**
     * Gets the shape of the entity.
     * Measurements should be in meters and position should be based around (0, 0)
     *
     * @return The shape.
     */
    protected abstract Shape getShape();

    protected abstract Color getColor();

    @Override
    protected void addScale(Entity e) {
        Shape shape = getShape();
        ScaleComponent scale = world.getEntityWorld().createComponent(ScaleComponent.class);
        if (shape instanceof CircleShape) {
            CircleShape circleShape = (CircleShape) shape;
            scale.setScale(new Vector2(Physics.toPixels(circleShape.getRadius()) * 2, Physics.toPixels(circleShape.getRadius()) * 2));
            e.add(scale);
        } else if (shape instanceof PolygonShape) {
            PolygonShape polygonShape = (PolygonShape) shape;
            scale.setScale(new Vector2(Physics.toPixels(PolygonHelper.getWidth(polygonShape)), Physics.toPixels(PolygonHelper.getHeight(polygonShape))));
            e.add(scale);
        } else {
            throw new IllegalArgumentException("Shape must be a circle or polygon");
        }
    }
}
