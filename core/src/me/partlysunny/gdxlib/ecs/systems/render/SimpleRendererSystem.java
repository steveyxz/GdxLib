package me.partlysunny.gdxlib.ecs.systems.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.partlysunny.gdxlib.ecs.component.render.SimpleTextureComponent;
import me.partlysunny.gdxlib.ecs.component.standard.ScaleComponent;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;
import org.jetbrains.annotations.Nullable;

public class SimpleRendererSystem extends RendererSystem<SimpleTextureComponent> {
    public SimpleRendererSystem(Batch batch) {
        super(SimpleTextureComponent.class, batch);
    }

    @Override
    protected void render(Entity entity, TransformComponent position, @Nullable ScaleComponent scale, SimpleTextureComponent renderComponent, float deltaTime) {
        if (scale == null) batch.draw(new TextureRegion(renderComponent.getTexture()), position.getPosition().x, position.getPosition().y, 0, 0, renderComponent.getTexture().getWidth(), renderComponent.getTexture().getHeight(), 1, 1, position.getRotation());
        else batch.draw(new TextureRegion(renderComponent.getTexture()), position.getPosition().x, position.getPosition().y, 0, 0, scale.getScale().x, scale.getScale().y, scale.getScaleFactor().x, scale.getScaleFactor().y, position.getRotation());
    }
}
