package me.partlysunny.gdxlib.util;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class ShapeTextureGenerator {

    private ShapeTextureGenerator() {
    }

    public static TextureRegion getCircle(int radius, int color) {
        return getCircle(radius, color, false);
    }

    public static TextureRegion getCircle(int radius, int color, boolean withMipMaps) {
        Pixmap pixmap = new Pixmap(radius * 2, radius * 2, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillCircle(radius, radius, radius);
        Texture texture = new Texture(pixmap, withMipMaps);
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        return new TextureRegion(texture);
    }

    public static TextureRegion getRectangle(int width, int height, int color) {
        return getRectangle(width, height, color, false);
    }

    public static TextureRegion getRectangle(int width, int height, int color, boolean withMipMaps) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        Texture texture = new Texture(pixmap, withMipMaps);
        return new TextureRegion(texture);
    }

}
