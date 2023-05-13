package me.partlysunny.gdxlib.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.entity.ShapeEntityProvider;
import me.partlysunny.gdxlib.util.ShapeBuilder;

public class RaindropEntity extends ShapeEntityProvider {
    public RaindropEntity(GameWorld world) {
        super(world);
    }

    @Override
    protected float getDecelerateRate() {
        return 1;
    }

    @Override
    protected BodyDef.BodyType getBodyType() {
        return BodyDef.BodyType.DynamicBody;
    }

    @Override
    protected Shape getShape() {
        return ShapeBuilder.nSidedPolygon(Vector2.Zero, 3, 5);
    }

    @Override
    protected Color getColor() {
        return Color.SKY;
    }

    @Override
    protected void addExtraComponents(Entity e) {

    }
}
