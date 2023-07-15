package me.partlysunny.gdxlib.flappyBird;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.physics.providers.Box2DPhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.physics.providers.PhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.RendererProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.SimpleTextureComponentProvider;
import me.partlysunny.gdxlib.ecs.component.standard.ScaleComponent;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.util.resource.ResourceManager;

public class BackgroundEntity extends SimpleEntityProvider {
    public BackgroundEntity(GameWorld world) {
        super(world);
    }

    @Override
    protected RendererProvider getRendererProvider() {
        return new SimpleTextureComponentProvider(ResourceManager.getInstance().getTexture("background_day"));
    }

    @Override
    protected PhysicsProvider getPhysicsProvider(Vector2 originPosition) {
        return null;
    }

    @Override
    protected void addExtraComponents(Entity e) {
        ScaleComponent scaleComponent = world.getEntityWorld().createComponent(ScaleComponent.class);
        scaleComponent.setScale(new Vector2(288, 512));
        scaleComponent.setScaleFactor(new Vector2(1.3f, 1.3f));
        e.add(scaleComponent);
    }
}
