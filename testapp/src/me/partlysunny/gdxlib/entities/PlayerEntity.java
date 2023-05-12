package me.partlysunny.gdxlib.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.control.ControllerComponent;
import me.partlysunny.gdxlib.ecs.component.physics.providers.Box2DPhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.physics.providers.PhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.RegionalTextureComponentProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.RendererProvider;
import me.partlysunny.gdxlib.ecs.component.standard.CameraFollowComponent;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.util.BodyBuilder;
import me.partlysunny.gdxlib.util.Physics;
import me.partlysunny.gdxlib.util.ShapeBuilder;
import me.partlysunny.gdxlib.util.ShapeTextureGenerator;

public class PlayerEntity extends SimpleEntityProvider {
    public PlayerEntity(GameWorld world) {
        super(world);
    }

    @Override
    protected RendererProvider getRendererProvider() {
        return new RegionalTextureComponentProvider(ShapeTextureGenerator.getCircle(50, Color.WHITE.toIntBits()));
    }

    @Override
    protected PhysicsProvider getPhysicsProvider(Vector2 originPosition) {
        return new Box2DPhysicsProvider(BodyBuilder.createWithPixelPosition(originPosition).addShape(ShapeBuilder.circle(Vector2.Zero, Physics.toMeters(50))).build(), 0.9f);
    }

    @Override
    protected void addExtraComponents(Entity e) {
        e.add(world.getEntityWorld().createComponent(CameraFollowComponent.class));
        ControllerComponent component = world.getEntityWorld().createComponent(ControllerComponent.class);
        component.setController(new MovementController(e));
        e.add(component);
    }
}
