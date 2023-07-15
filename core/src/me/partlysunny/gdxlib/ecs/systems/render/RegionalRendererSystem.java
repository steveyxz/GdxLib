package me.partlysunny.gdxlib.ecs.systems.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import me.partlysunny.gdxlib.ecs.component.render.RegionalTextureComponent;
import me.partlysunny.gdxlib.ecs.component.standard.ScaleComponent;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;
import org.jetbrains.annotations.Nullable;

public class RegionalRendererSystem extends RendererSystem<RegionalTextureComponent> {
    public RegionalRendererSystem(Batch batch) {
        super(RegionalTextureComponent.class, batch);
    }

    @Override
    protected void render(Entity entity, TransformComponent position, @Nullable ScaleComponent scale, RegionalTextureComponent renderComponent, float deltaTime) {
        if (scale == null)
            batch.draw(renderComponent.getTextureRegion(), position.getPosition().x, position.getPosition().y, 0, 0, renderComponent.getTextureRegion().getRegionWidth(), renderComponent.getTextureRegion().getRegionHeight(), 1, 1, position.getRotation());
        else
            batch.draw(renderComponent.getTextureRegion(), position.getPosition().x, position.getPosition().y, 0, 0, scale.getScale().x, scale.getScale().y, scale.getScaleFactor().x, scale.getScaleFactor().y, position.getRotation());
    }
}
