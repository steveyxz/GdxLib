package me.partlysunny.gdxlib.ecs.component.ai;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Component to attach to entities to signify that any AIs present in the world should not avoid this entity.
 * Useful to attach to entities like projectiles, which should not be avoided by enemies.
 * Also can be used on things like triggers and background objects.
 */
public class DoNotDodgeComponent implements Component, Pool.Poolable {
    @Override
    public void reset() {
    }
}
