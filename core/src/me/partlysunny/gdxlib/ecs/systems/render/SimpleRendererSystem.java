package me.partlysunny.gdxlib.ecs.systems.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.component.render.SimpleTextureComponent;

public class SimpleRendererSystem extends RendererSystem<SimpleTextureComponent> {
    public SimpleRendererSystem(Batch batch) {
        super(SimpleTextureComponent.class, batch);
    }

    @Override
    protected void render(Entity entity, Vector2 position, SimpleTextureComponent renderComponent, float deltaTime) {
        batch.draw(renderComponent.getTexture(), position.x, position.y);
    }
}
