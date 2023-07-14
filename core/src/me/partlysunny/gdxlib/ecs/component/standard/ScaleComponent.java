package me.partlysunny.gdxlib.ecs.component.standard;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Defines a size for the rendering process of an entity
 * Measurements are in pixels
 */
public class ScaleComponent implements Component, Pool.Poolable {

    /**
     * The size of the entity rendered in pixels
     */
    private Vector2 scale = new Vector2(0, 0);
    /**
     * The scale factor of the entity
     * This will be multiplied with the scale
     */
    private Vector2 scaleFactor = new Vector2(1, 1);

    public ScaleComponent() {
    }

    public ScaleComponent(float x, float y) {
        scale.set(x, y);
    }

    public ScaleComponent(float x, float y, float scaleX, float scaleY) {
        scale.set(x, y);
        scaleFactor.set(scaleX, scaleY);
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public Vector2 getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(Vector2 scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    @Override
    public void reset() {
        scale.set(0, 0);
        scaleFactor.set(1, 1);
    }
}
