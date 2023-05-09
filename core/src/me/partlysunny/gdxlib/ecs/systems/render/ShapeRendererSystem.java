package me.partlysunny.gdxlib.ecs.systems.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonBatch;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.component.render.ShapeTextureComponent;

public class ShapeRendererSystem extends RendererSystem<ShapeTextureComponent> {
    public ShapeRendererSystem(Batch batch) {
        super(ShapeTextureComponent.class, batch);
    }

    @Override
    protected void render(Entity entity, Vector2 position, ShapeTextureComponent renderComponent, float deltaTime) {
        if (batch instanceof PolygonBatch) {
            PolygonBatch polygonBatch = (PolygonBatch) batch;
            polygonBatch.draw(renderComponent.getPolygonSprite().getRegion(), position.x, position.y);
        }
    }
}
