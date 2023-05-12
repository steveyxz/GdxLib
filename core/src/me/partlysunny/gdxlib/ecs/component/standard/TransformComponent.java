package me.partlysunny.gdxlib.ecs.component.standard;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Represents the position and the rotation of an object in render space
 * The main purpose of this component is to be used by the renderer system
 * If the object has a body attached, the position will simply sync to the body's position,
 * so it would be useless to change the properties of this component
 */
public class TransformComponent implements Component, Pool.Poolable {

    private Vector2 position;
    /**
     * The center position of the object in pixel space
     */
    private Vector2 center;
    private float rotation;

    public TransformComponent() {
        this.position = new Vector2();
        this.center = new Vector2();
        this.rotation = 0;
    }

    public TransformComponent(Vector2 position, Vector2 center, float rotation) {
        this.position = position;
        this.center = center;
        this.rotation = rotation;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getCenter() {
        return center;
    }

    public void setCenter(Vector2 center) {
        this.center = center;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        rotation = rotation % 360;
        this.rotation = rotation;
    }

    @Override
    public void reset() {
        position.set(0, 0);
        center.set(0, 0);
        rotation = 0;
    }
}
