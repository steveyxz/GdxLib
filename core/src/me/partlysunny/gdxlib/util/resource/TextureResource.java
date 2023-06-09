package me.partlysunny.gdxlib.util.resource;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import me.partlysunny.gdxlib.util.Colors;
import me.partlysunny.gdxlib.util.ShapeTextureGenerator;

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
        this.texture = ShapeTextureGenerator.getRectangle(1, 1, Colors.toRGBAInt(color)).getTexture();
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
