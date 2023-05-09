package me.partlysunny.gdxlib.util.camera.scale;

import com.badlogic.gdx.math.Vector2;

public class FillScaler implements Scaler {
    @Override
    public Vector2 calculateProjection(Vector2 maximumProjection, int width, int height) {
        float aspectRatio = (float) width / (float) height;
        if (aspectRatio > maximumProjection.x / maximumProjection.y) {
            float w = maximumProjection.x;
            float h = w / aspectRatio;
            return new Vector2(w, h);
        }
        float h = maximumProjection.y;
        float w = h * aspectRatio;
        return new Vector2(w, h);
    }
}
