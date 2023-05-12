package me.partlysunny.gdxlib.ecs.component.render;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RegionalTextureComponent implements RendererComponent {

    private TextureRegion textureRegion;

    public RegionalTextureComponent(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public RegionalTextureComponent() {
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    @Override
    public void reset() {
        textureRegion = null;
    }
}
