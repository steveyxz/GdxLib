package me.partlysunny.gdxlib.ecs.component.render.providers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.render.RegionalTextureComponent;
import me.partlysunny.gdxlib.ecs.component.render.RendererComponent;

public class RegionalTextureComponentProvider implements RendererProvider {

    private final TextureRegion region;

    public RegionalTextureComponentProvider(TextureRegion region) {
        this.region = region;
    }

    public RegionalTextureComponentProvider(TextureRegion region, float width, float height) {
        this.region = new TextureRegion(region);
        this.region.setRegionWidth((int) width);
        this.region.setRegionHeight((int) height);
    }

    public RegionalTextureComponentProvider(TextureRegion region, float width, float height, float originX, float originY) {
        this(region, width, height);
        this.region.setRegionX((int) originX);
        this.region.setRegionY((int) originY);
    }

    @Override
    public RendererComponent createRenderer(GameWorld world) {
        RegionalTextureComponent component = world.getEntityWorld().createComponent(RegionalTextureComponent.class);
        component.setTextureRegion(region);
        return component;
    }
}
