package me.partlysunny.gdxlib.ecs.systems.render;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BatchSet {

    private final SpriteBatch spriteBatch;
    private final PolygonSpriteBatch polygonSpriteBatch;

    public BatchSet(SpriteBatch spriteBatch, PolygonSpriteBatch polygonSpriteBatch) {
        this.spriteBatch = spriteBatch;
        this.polygonSpriteBatch = polygonSpriteBatch;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public PolygonSpriteBatch getPolygonSpriteBatch() {
        return polygonSpriteBatch;
    }

    public void begin() {
        spriteBatch.begin();
        polygonSpriteBatch.begin();
    }

    public void end() {
        spriteBatch.end();
        polygonSpriteBatch.end();
    }

    public void dispose() {
        spriteBatch.dispose();
        polygonSpriteBatch.dispose();
    }
}
