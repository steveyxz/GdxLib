package me.partlysunny.gdxlib.flappyBird.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import me.partlysunny.gdxlib.util.RandomUtils;

import java.util.Random;

public class LevelComponent implements Component, Pool.Poolable {

    public enum Side {
        TOP,
        BOTTOM;

        public static Side random() {
            return Math.random() > 0.5 ? TOP : BOTTOM;
        }
    }

    private Side side;
    private int id;

    public LevelComponent() {

    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void reset() {
        side = null;
        id = 0;
    }
}
