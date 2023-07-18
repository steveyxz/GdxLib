package me.partlysunny.gdxlib.flappyBird.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class LevelComponent implements Component, Pool.Poolable {

    public enum Side {
        TOP,
        BOTTOM;

        public static Side random() {
            return Math.random() > 0.5 ? TOP : BOTTOM;
        }
    }

    private Side side;

    public LevelComponent() {

    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    @Override
    public void reset() {
        side = null;
    }
}
