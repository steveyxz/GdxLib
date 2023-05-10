package me.partlysunny.gdxlib.ecs.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.render.providers.RendererProvider;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;

public abstract class SimpleEntityProvider implements EntityProvider {

    public static Entity createSingular(Class<? extends SimpleEntityProvider> clazz, GameWorld world, Vector2 originPosition) {
        try {
            return clazz.getConstructor(GameWorld.class).newInstance(world).createEntity(originPosition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected final GameWorld world;

    public SimpleEntityProvider(GameWorld world) {
        this.world = world;
    }

    @Override
    public Entity createEntity(Vector2 originPosition) {
        Entity entity = world.getEntityWorld().createEntity();
        addPosition(entity, originPosition);
        addRenderer(entity);
        if (autoAdd()) {
            world.getEntityWorld().addEntity(entity);
        }
        return entity;
    }

    private void addPosition(Entity e, Vector2 position) {
        TransformComponent component = world.getEntityWorld().createComponent(TransformComponent.class);
        component.setPosition(position);
        e.add(component);
    }

    protected boolean autoAdd() {
        return true;
    }

    protected void addRenderer(Entity e) {
        e.add(getRendererProvider().createRenderer(world));
    }

    protected abstract RendererProvider getRendererProvider();
    protected abstract void addExtraComponents(Entity e);
}
