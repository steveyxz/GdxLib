package me.partlysunny.gdxlib.ecs.systems.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.component.render.AnimatedTextureComponent;

public class AnimatedRendererSystem extends RendererSystem<AnimatedTextureComponent> {
    public AnimatedRendererSystem(Batch batch) {
        super(AnimatedTextureComponent.class, batch);
    }

    @Override
    protected void render(Entity entity, Vector2 position, AnimatedTextureComponent renderComponent, float deltaTime) {
        Texture currentStateTimeTexture = renderComponent.getAnimation().getKeyFrame(renderComponent.getStateTime()).getTexture();
        batch.draw(currentStateTimeTexture, position.x, position.y);
        renderComponent.tickStateTime(deltaTime);
    }
}
