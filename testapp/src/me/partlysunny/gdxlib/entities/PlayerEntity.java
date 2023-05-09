package me.partlysunny.gdxlib.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.render.providers.RendererProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.ShapeTextureComponentProvider;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.util.ShapeBuilder;

public class PlayerEntity extends SimpleEntityProvider {
    public PlayerEntity(GameWorld world) {
        super(world);
    }

    @Override
    protected RendererProvider getRendererProvider() {
        return new ShapeTextureComponentProvider(Color.BLUE, ShapeBuilder.radialSquare(new Vector2(0, 0), 10));
    }

    @Override
    protected void addExtraComponents(Entity e) {
    }
}
