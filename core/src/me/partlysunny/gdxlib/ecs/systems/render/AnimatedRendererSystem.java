package me.partlysunny.gdxlib.ecs.systems.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.partlysunny.gdxlib.ecs.component.render.AnimatedTextureComponent;
import me.partlysunny.gdxlib.ecs.component.standard.ScaleComponent;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;
import org.jetbrains.annotations.Nullable;

public class AnimatedRendererSystem extends RendererSystem<AnimatedTextureComponent> {
    public AnimatedRendererSystem(Batch batch) {
        super(AnimatedTextureComponent.class, batch);
    }

    @Override
    protected void render(Entity entity, TransformComponent position, @Nullable ScaleComponent scale, AnimatedTextureComponent renderComponent, float deltaTime) {
        Texture currentStateTimeTexture = renderComponent.getAnimation().getKeyFrame(renderComponent.getStateTime()).getTexture();
        if (scale == null)
            batch.draw(new TextureRegion(currentStateTimeTexture), position.getPosition().x, position.getPosition().y, 0, 0, currentStateTimeTexture.getWidth(), currentStateTimeTexture.getHeight(), 1, 1, position.getRotation());
        else
            batch.draw(new TextureRegion(currentStateTimeTexture), position.getPosition().x, position.getPosition().y, 0, 0, scale.getScale().x, scale.getScale().y, scale.getScaleFactor().x, scale.getScaleFactor().y, position.getRotation());
        renderComponent.tickStateTime(deltaTime);
    }
}
