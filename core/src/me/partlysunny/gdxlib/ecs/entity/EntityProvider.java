package me.partlysunny.gdxlib.ecs.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public interface EntityProvider {
    Entity createEntity(Vector2 originPosition);
}
