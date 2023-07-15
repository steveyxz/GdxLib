package me.partlysunny.gdxlib.ecs.component.standard;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class DestroyComponent implements Component, Pool.Poolable {

    private float timeToLive = 0;

    public DestroyComponent() {
    }

    public DestroyComponent(float timeToLive) {
        this.timeToLive = timeToLive;
    }

    public float getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(float timeToLive) {
        this.timeToLive = timeToLive;
    }

    @Override
    public void reset() {
        timeToLive = 0;
    }
}
