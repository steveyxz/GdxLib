package me.partlysunny.gdxlib.util;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public final class TextureModifier {

    public static Texture flipTexture(Texture texture) {
        return flipTexture(texture, true, false);
    }

    public static Texture flipTexture(Texture texture, boolean flipX, boolean flipY) {
        texture.getTextureData().prepare();
        Pixmap map = texture.getTextureData().consumePixmap();
        Pixmap flipped = new Pixmap(map.getWidth(), map.getHeight(), map.getFormat());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                int color = map.getPixel(x, y);
                int x1 = flipX ? map.getWidth() - x - 1 : x;
                int y1 = flipY ? map.getHeight() - y - 1 : y;
                flipped.drawPixel(x1, y1, color);
            }
        }
        Texture flippedTexture = new Texture(flipped);
        map.dispose();
        flipped.dispose();
        return flippedTexture;
    }

}
