package me.partlysunny.gdxlib.flappyBird.entites;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.control.ControllerComponent;
import me.partlysunny.gdxlib.ecs.component.physics.providers.Box2DPhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.physics.providers.PhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.render.ZComponent;
import me.partlysunny.gdxlib.ecs.component.render.providers.RendererProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.SimpleTextureComponentProvider;
import me.partlysunny.gdxlib.ecs.component.standard.ScaleComponent;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.flappyBird.components.BirdComponent;
import me.partlysunny.gdxlib.flappyBird.components.FlapController;
import me.partlysunny.gdxlib.util.BodyBuilder;
import me.partlysunny.gdxlib.util.Physics;
import me.partlysunny.gdxlib.util.ShapeBuilder;
import me.partlysunny.gdxlib.util.resource.ResourceManager;

public class BirdEntity extends SimpleEntityProvider {

    public BirdEntity(GameWorld world) {
        super(world);
    }

    @Override
    protected RendererProvider getRendererProvider() {
        return new SimpleTextureComponentProvider(ResourceManager.getInstance().getTexture("bird_midflap"));
    }

    @Override
    protected PhysicsProvider getPhysicsProvider(Vector2 originPosition) {
        return new Box2DPhysicsProvider(BodyBuilder.create(originPosition).bodyType(BodyDef.BodyType.DynamicBody).addShape(ShapeBuilder.circle(Vector2.Zero, Physics.toMeters(14))).build(), 1);
    }

    @Override
    protected void addExtraComponents(Entity e) {
        ScaleComponent scale = world.getEntityWorld().createComponent(ScaleComponent.class);
        scale.setScale(new Vector2(34, 24));
        e.add(scale);
        ZComponent z = world.getEntityWorld().createComponent(ZComponent.class);
        z.setZIndex(3);
        e.add(z);
        ControllerComponent flap = world.getEntityWorld().createComponent(ControllerComponent.class);
        flap.setController(new FlapController(e, "flap"));
        e.add(flap);
        BirdComponent bird = world.getEntityWorld().createComponent(BirdComponent.class);
        e.add(bird);
    }
}
