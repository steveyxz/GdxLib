package me.partlysunny.gdxlib.util.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.util.camera.scale.FillScaler;
import me.partlysunny.gdxlib.util.camera.scale.Scaler;

public class OrthoCameraHandler implements CameraHandler {

    private final Vector2 maximumProjection;

    public OrthoCameraHandler(Vector2 maximumProjection) {
        this.maximumProjection = maximumProjection;
    }

    @Override
    public Camera createCamera() {
        return new OrthographicCamera();
    }

    @Override
    public void updateViewport(Camera camera, int width, int height) {
        OrthographicCamera orthographic = (OrthographicCamera) camera;
        Vector2 scaledProjection = getScaleOption().calculateProjection(maximumProjection, width, height);
        orthographic.setToOrtho(false, scaledProjection.x, scaledProjection.y);
    }

    @Override
    public Vector2 maximumProjection() {
        return maximumProjection;
    }

    @Override
    public Scaler getScaleOption() {
        return new FillScaler();
    }
}
