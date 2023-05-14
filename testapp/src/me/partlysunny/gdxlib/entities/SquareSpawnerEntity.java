package me.partlysunny.gdxlib.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.physics.providers.PhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.RendererProvider;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;

public class SquareSpawnerEntity extends SimpleEntityProvider {
    public SquareSpawnerEntity(GameWorld world) {
        super(world);
    }

    @Override
    protected RendererProvider getRendererProvider() {
        return null;
    }

    @Override
    protected PhysicsProvider getPhysicsProvider(Vector2 originPosition) {
        return null;
    }

    @Override
    protected void addExtraComponents(Entity e) {

    }
}
