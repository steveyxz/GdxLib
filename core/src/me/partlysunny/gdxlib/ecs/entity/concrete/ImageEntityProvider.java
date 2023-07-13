package me.partlysunny.gdxlib.ecs.entity.concrete;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.physics.providers.PhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.RendererProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.SimpleTextureComponentProvider;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.util.resource.ResourceManager;
import org.jetbrains.annotations.Nullable;

public class ImageEntityProvider extends SimpleEntityProvider {
    private final Texture texture;

    public ImageEntityProvider(GameWorld world, String texture) {
        super(world);
        this.texture = ResourceManager.getInstance().getTexture(texture);
    }

    @Nullable
    @Override
    protected RendererProvider getRendererProvider() {
        return new SimpleTextureComponentProvider(texture);
    }

    @Nullable
    @Override
    protected PhysicsProvider getPhysicsProvider(Vector2 originPosition) {
        return null;
    }

    @Override
    protected void addExtraComponents(Entity e) {
    }
}
