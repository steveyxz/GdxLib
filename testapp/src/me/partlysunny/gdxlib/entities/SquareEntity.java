package me.partlysunny.gdxlib.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.render.providers.RendererProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.ShapeTextureComponentProvider;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.util.ShapeBuilder;

public class SquareEntity extends SimpleEntityProvider {
    public SquareEntity(GameWorld world) {
        super(world);
    }

    @Override
    protected RendererProvider getRendererProvider() {
        return new ShapeTextureComponentProvider(Color.BROWN, ShapeBuilder.nSidedPolygon(new Vector2(0, 0), 8, 50));
    }

    @Override
    protected void addExtraComponents(Entity e) {
    }
}
