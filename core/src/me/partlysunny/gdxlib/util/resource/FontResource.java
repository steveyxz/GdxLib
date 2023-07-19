package me.partlysunny.gdxlib.util.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontResource implements Resource<BitmapFont> {

    public enum FontType {
        TTF,
        BMF
    }

    private final BitmapFont font;

    public FontResource(BitmapFont font) {
        this.font = font;
    }

    public FontResource(FileHandle resource, FontType type, int size) {
        switch (type) {
            case TTF:
                FreeTypeFontGenerator generator = new FreeTypeFontGenerator(resource);
                FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter.size = size;
                BitmapFont font12 = generator.generateFont(parameter);
                generator.dispose();
                this.font = font12;
                break;
            case BMF:
                this.font = new BitmapFont(resource);
                break;
            default:
                throw new IllegalArgumentException("Unknown font type: " + type);
        }
    }

    public FontResource(String filePath, FontType type, int size) {
        this(Gdx.files.internal(filePath), type, size);
    }

    @Override
    public BitmapFont getInternal() {
        return font;
    }

    @Override
    public Class<BitmapFont> getType() {
        return BitmapFont.class;
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
