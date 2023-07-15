package me.partlysunny.gdxlib.ecs.component.render;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Represents the Z index of an entity. Entities with higher Z indices are rendered on top of entities with lower Z indices.
 */
public class ZComponent implements Component, Pool.Poolable {

    private int zIndex;

    public ZComponent() {
        this(0);
    }

    public ZComponent(int zIndex) {
        this.zIndex = zIndex;
    }

    public int getZIndex() {
        return zIndex;
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    @Override
    public void reset() {
        zIndex = 0;
    }
}
