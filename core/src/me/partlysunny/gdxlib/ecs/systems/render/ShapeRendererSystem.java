package me.partlysunny.gdxlib.ecs.systems.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonBatch;
import me.partlysunny.gdxlib.ecs.component.render.ShapeTextureComponent;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;

public class ShapeRendererSystem extends RendererSystem<ShapeTextureComponent> {
    public ShapeRendererSystem(Batch batch) {
        super(ShapeTextureComponent.class, batch);
    }

    @Override
    protected void render(Entity entity, TransformComponent position, ShapeTextureComponent renderComponent, float deltaTime) {
        if (batch instanceof PolygonBatch) {
            PolygonBatch polygonBatch = (PolygonBatch) batch;
            draw(polygonBatch, renderComponent, position);
        }
    }

    private void draw(PolygonBatch polygonBatch, ShapeTextureComponent renderComponent, TransformComponent position) {
        float x = position.getPosition().x;
        float y = position.getPosition().y;
        float rotation = position.getRotation();
        polygonBatch.draw(renderComponent.getPolygonSprite().getRegion(), x, y, 0, 0, renderComponent.getPolygonSprite().getWidth(), renderComponent.getPolygonSprite().getHeight(), 1, 1, rotation);
    }
}
