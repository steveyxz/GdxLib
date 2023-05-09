package me.partlysunny.gdxlib.util.camera.scale;

import com.badlogic.gdx.math.Vector2;

public class FitScaler implements Scaler {
    @Override
    public Vector2 calculateProjection(Vector2 maximumProjection, int width, int height) {
        return new Vector2(maximumProjection.x, maximumProjection.y);
    }
}
