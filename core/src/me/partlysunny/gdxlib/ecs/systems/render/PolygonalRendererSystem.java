package me.partlysunny.gdxlib.ecs.systems.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonBatch;
import me.partlysunny.gdxlib.ecs.component.render.PolygonalTextureComponent;
import me.partlysunny.gdxlib.ecs.component.standard.ScaleComponent;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;
import org.jetbrains.annotations.Nullable;

public class PolygonalRendererSystem extends RendererSystem<PolygonalTextureComponent> {
    public PolygonalRendererSystem(Batch batch) {
        super(PolygonalTextureComponent.class, batch);
    }

    @Override
    protected void render(Entity entity, TransformComponent position, @Nullable ScaleComponent scale, PolygonalTextureComponent renderComponent, float deltaTime) {
        if (batch instanceof PolygonBatch) {
            PolygonBatch polygonBatch = (PolygonBatch) batch;
            if (scale == null) drawWithoutScale(polygonBatch, renderComponent, position);
            else drawWithScale(polygonBatch, renderComponent, position, scale);
        }
    }

    private void drawWithScale(PolygonBatch polygonBatch, PolygonalTextureComponent renderComponent, TransformComponent position, ScaleComponent scale) {
        float x = position.getPosition().x;
        float y = position.getPosition().y;
        float rotation = position.getRotation();
        float scaleX = scale.getScaleFactor().x;
        float scaleY = scale.getScaleFactor().y;
        float originX = renderComponent.getPolygonSprite().getOriginX();
        float originY = renderComponent.getPolygonSprite().getOriginY();
        polygonBatch.draw(renderComponent.getPolygonSprite().getRegion(), x, y, originX, originY, 1, 1, scaleX, scaleY, rotation);
    }

    private void drawWithoutScale(PolygonBatch polygonBatch, PolygonalTextureComponent renderComponent, TransformComponent position) {
        float x = position.getPosition().x;
        float y = position.getPosition().y;
        float rotation = position.getRotation();
        polygonBatch.draw(renderComponent.getPolygonSprite().getRegion(), x, y, 0, 0, renderComponent.getPolygonSprite().getWidth(), renderComponent.getPolygonSprite().getHeight(), 1, 1, rotation);
    }
}
