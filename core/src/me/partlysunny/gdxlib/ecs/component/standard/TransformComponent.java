package me.partlysunny.gdxlib.ecs.component.standard;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents the position and the rotation of an object in render space
 * The main purpose of this component is to be used by the renderer system
 * If the object has a body attached, the position will simply sync to the body's position,
 * so it would be useless to change the properties of this component
 */
public class TransformComponent implements Component {

    private Vector2 position;
    private float rotation;

    public TransformComponent() {
        this.position = new Vector2();
        this.rotation = 0;
    }

    public TransformComponent(Vector2 position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        rotation = rotation % 360;
        this.rotation = rotation;
    }
}
