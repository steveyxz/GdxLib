package me.partlysunny.gdxlib.ecs.component.render;

import com.badlogic.gdx.graphics.Texture;

public class SimpleTextureComponent implements RendererComponent {

    private Texture texture;

    public SimpleTextureComponent(Texture texture) {
        this.texture = texture;
    }

    public SimpleTextureComponent() {
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
