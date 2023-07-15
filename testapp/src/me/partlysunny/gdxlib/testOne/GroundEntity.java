package me.partlysunny.gdxlib.testOne;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.entity.ShapeEntityProvider;
import me.partlysunny.gdxlib.util.Physics;
import me.partlysunny.gdxlib.util.ShapeBuilder;

public class GroundEntity extends ShapeEntityProvider {
    public GroundEntity(GameWorld world) {
        super(world);
    }

    @Override
    protected float getDecelerateRate() {
        return 0;
    }

    @Override
    protected BodyDef.BodyType getBodyType() {
        return BodyDef.BodyType.StaticBody;
    }

    @Override
    protected Shape getShape() {
        return ShapeBuilder.radialRect(Vector2.Zero, Physics.toMeters(new Vector2(5000, 50)));
    }

    @Override
    protected Color getColor() {
        return Color.LIGHT_GRAY;
    }


    @Override
    protected void addExtraComponents(Entity e) {
    }
}
