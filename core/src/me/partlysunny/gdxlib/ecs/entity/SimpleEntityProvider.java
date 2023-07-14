package me.partlysunny.gdxlib.ecs.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.physics.providers.PhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.RendererProvider;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;
import me.partlysunny.gdxlib.util.Physics;
import org.jetbrains.annotations.Nullable;

public abstract class SimpleEntityProvider implements EntityProvider {

    protected final GameWorld world;

    public SimpleEntityProvider(GameWorld world) {
        this.world = world;
    }

    /**
     * Creates a singular entity of the given class at the given position.
     * @param clazz The class of the entity to create.
     * @param world The world to create the entity in.
     * @param originPosition The position to create the entity at in pixels.
     * @return The created entity.
     */
    public static Entity createSingular(Class<? extends SimpleEntityProvider> clazz, GameWorld world, Vector2 originPosition) {
        try {
            return clazz.getConstructor(GameWorld.class).newInstance(world).createEntity(originPosition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Entity createEntity(Vector2 originPosition) {
        Entity entity = world.getEntityWorld().createEntity();
        addPosition(entity, originPosition);
        addRenderer(entity);
        addPhysics(entity, Physics.toMeters(originPosition));
        addScale(entity);
        addExtraComponents(entity);
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
        RendererProvider rendererProvider = getRendererProvider();
        if (rendererProvider != null) {
            e.add(rendererProvider.createRenderer(world));
        }
    }

    protected void addPhysics(Entity e, Vector2 originPosition) {
        PhysicsProvider physicsProvider = getPhysicsProvider(originPosition);
        if (physicsProvider != null) {
            e.add(physicsProvider.createPhysics(world));
        }
    }

    protected void addScale(Entity e) {}

    @Nullable
    protected abstract RendererProvider getRendererProvider();

    @Nullable
    protected abstract PhysicsProvider getPhysicsProvider(Vector2 originPosition);

    protected abstract void addExtraComponents(Entity e);
}
