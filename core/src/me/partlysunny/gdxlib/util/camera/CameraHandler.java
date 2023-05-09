package me.partlysunny.gdxlib.util.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.util.camera.scale.Scaler;

public interface CameraHandler {

    Camera createCamera();

    void updateViewport(Camera camera, int width, int height);

    Vector2 maximumProjection();

    Scaler getScaleOption();

}
