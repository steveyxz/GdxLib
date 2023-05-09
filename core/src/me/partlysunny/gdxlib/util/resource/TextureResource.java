package me.partlysunny.gdxlib.util.resource;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class TextureResource implements Resource<Texture> {

    private final Texture texture;

    public TextureResource(Texture texture) {
        this.texture = texture;
    }

    public TextureResource(FileHandle fileHandle) {
        this.texture = new Texture(fileHandle);
    }

    public TextureResource(String path) {
        this.texture = new Texture(path);
    }

    public TextureResource(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        this.texture = new Texture(pixmap, false);
    }

    @Override
    public Texture getInternal() {
        return texture;
    }

    @Override
    public Class<Texture> getType() {
        return Texture.class;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
