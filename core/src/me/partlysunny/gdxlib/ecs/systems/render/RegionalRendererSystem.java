package me.partlysunny.gdxlib.ecs.systems.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.component.render.RegionalTextureComponent;

public class RegionalRendererSystem extends RendererSystem<RegionalTextureComponent> {
    public RegionalRendererSystem(Batch batch) {
        super(RegionalTextureComponent.class, batch);
    }

    @Override
    protected void render(Entity entity, Vector2 position, RegionalTextureComponent renderComponent, float deltaTime) {
        batch.draw(renderComponent.getTextureRegion(), position.x, position.y);
    }
}
