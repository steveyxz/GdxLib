package me.partlysunny.gdxlib.ecs.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public interface EntityProvider {
    /**
     * Create an entity at the given position
     * Note that this method does not add the entity to the engine necessarily
     * Implementations may provide a method to do so.
     * @param originPosition The position to create the entity at
     * @return The created entity
     */
    Entity createEntity(Vector2 originPosition);
}
