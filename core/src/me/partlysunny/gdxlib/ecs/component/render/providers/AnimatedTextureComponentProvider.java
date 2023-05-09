package me.partlysunny.gdxlib.ecs.component.render.providers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.render.AnimatedTextureComponent;
import me.partlysunny.gdxlib.ecs.component.render.RendererComponent;

public class AnimatedTextureComponentProvider implements RendererProvider {

    private final Animation<TextureRegion> animation;

    public AnimatedTextureComponentProvider(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    public AnimatedTextureComponentProvider(float frameDuration, TextureRegion... frames) {
        this.animation = new Animation<>(frameDuration, frames);
    }

    public AnimatedTextureComponentProvider(TextureRegion... frames) {
        this(1f, frames);
    }

    public AnimatedTextureComponentProvider(TextureAtlas atlas) {
        Array<TextureAtlas.AtlasRegion> regions = atlas.getRegions();
        Array<TextureRegion> textureRegions = new Array<>(regions.size);
        for (TextureAtlas.AtlasRegion region : regions) {
            textureRegions.add(region);
        }
        this.animation = new Animation<>(1f, textureRegions);
    }

    @Override
    public RendererComponent createRenderer(GameWorld world) {
        return new AnimatedTextureComponent(animation);
    }
}
