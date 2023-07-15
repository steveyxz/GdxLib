package me.partlysunny.gdxlib.flappyBird;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.physics.providers.Box2DPhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.physics.providers.PhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.render.ZComponent;
import me.partlysunny.gdxlib.ecs.component.render.providers.RendererProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.SimpleTextureComponentProvider;
import me.partlysunny.gdxlib.ecs.component.standard.ScaleComponent;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.util.BodyBuilder;
import me.partlysunny.gdxlib.util.ShapeBuilder;
import me.partlysunny.gdxlib.util.resource.ResourceManager;

public class BaseEntity extends SimpleEntityProvider {
    public BaseEntity(GameWorld world) {
        super(world);
    }

    @Override
    protected RendererProvider getRendererProvider() {
        return new SimpleTextureComponentProvider(ResourceManager.getInstance().getTexture("base"));
    }

    @Override
    protected PhysicsProvider getPhysicsProvider(Vector2 originPosition) {
        return new Box2DPhysicsProvider(BodyBuilder.create(originPosition).bodyType(BodyDef.BodyType.StaticBody).addShape(ShapeBuilder.radialRect(Vector2.Zero, new Vector2(1200, 400))).build(), 0);
    }

    @Override
    protected void addExtraComponents(Entity e) {
        ScaleComponent scaleComponent = world.getEntityWorld().createComponent(ScaleComponent.class);
        scaleComponent.setScale(new Vector2(600, 200));
        e.add(scaleComponent);
        ZComponent zComponent = world.getEntityWorld().createComponent(ZComponent.class);
        zComponent.setZIndex(1);
        e.add(zComponent);
    }

}
