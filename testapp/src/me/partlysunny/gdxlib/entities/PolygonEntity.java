package me.partlysunny.gdxlib.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.physics.providers.Box2DPhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.physics.providers.PhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.RendererProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.PolygonalTextureComponentProvider;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.util.BodyBuilder;
import me.partlysunny.gdxlib.util.Physics;
import me.partlysunny.gdxlib.util.ShapeBuilder;

public class PolygonEntity extends SimpleEntityProvider {
    public PolygonEntity(GameWorld world) {
        super(world);
    }

    @Override
    protected RendererProvider getRendererProvider() {
        return new PolygonalTextureComponentProvider(Color.CYAN, ShapeBuilder.nSidedPolygon(new Vector2(), 5, 25));
    }

    @Override
    protected PhysicsProvider getPhysicsProvider(Vector2 originPosition) {
        return new Box2DPhysicsProvider(BodyBuilder.createWithPixelPosition(originPosition).bodyType(BodyDef.BodyType.DynamicBody).addShape(ShapeBuilder.nSidedPolygon(new Vector2(), 5, Physics.toMeters(25))).build(), 0.95f);
    }

    @Override
    protected void addExtraComponents(Entity e) {
    }
}
