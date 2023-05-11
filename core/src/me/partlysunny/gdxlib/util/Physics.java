package me.partlysunny.gdxlib.util;

import com.badlogic.gdx.math.Vector2;

public class Physics {

    public static float PPM = 32f;

    public static float toMeters(float pixels) {
        return pixels / PPM;
    }

    public static float toPixels(float meters) {
        return meters * PPM;
    }

    public static Vector2 toMeters(Vector2 pixels) {
        return pixels.scl(1f / PPM);
    }

    public static Vector2 toPixels(Vector2 meters) {
        return meters.scl(PPM);
    }

}
