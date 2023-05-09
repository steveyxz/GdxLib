package me.partlysunny.gdxlib.util.camera.scale;

import com.badlogic.gdx.math.Vector2;

public interface Scaler {

    Vector2 calculateProjection(Vector2 maximumProjection, int width, int height);

}
