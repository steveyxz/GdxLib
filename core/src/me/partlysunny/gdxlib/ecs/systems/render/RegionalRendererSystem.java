package me.partlysunny.gdxlib.ecs.systems.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import me.partlysunny.gdxlib.ecs.component.render.RegionalTextureComponent;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;

public class RegionalRendererSystem extends RendererSystem<RegionalTextureComponent> {
    public RegionalRendererSystem(Batch batch) {
        super(RegionalTextureComponent.class, batch);
    }

    @Override
    protected void render(Entity entity, TransformComponent position, RegionalTextureComponent renderComponent, float deltaTime) {
        batch.draw(renderComponent.getTextureRegion(), position.getPosition().x, position.getPosition().y, 0, 0, renderComponent.getTextureRegion().getRegionWidth(), renderComponent.getTextureRegion().getRegionHeight(), 1, 1, position.getRotation());
    }
}
