package me.partlysunny.gdxlib.ecs.component.render.providers;

import com.badlogic.gdx.graphics.Texture;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.render.RendererComponent;
import me.partlysunny.gdxlib.ecs.component.render.SimpleTextureComponent;

public class SimpleTextureComponentProvider implements RendererProvider {

    private final Texture texture;

    public SimpleTextureComponentProvider(Texture texture) {
        this.texture = texture;
    }

    @Override
    public RendererComponent createRenderer(GameWorld world) {
        return new SimpleTextureComponent(texture);
    }
}
