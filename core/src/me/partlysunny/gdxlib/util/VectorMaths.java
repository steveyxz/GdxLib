package me.partlysunny.gdxlib.util;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public final class VectorMaths {

    public static Vector2 angleToVector(Vector2 outVector, float angle) {
        outVector.set(MathUtils.cos(angle), MathUtils.sin(angle));
        return outVector;
    }

    public static float vectorToAngle(Vector2 angle) {
        return MathUtils.atan2(angle.y, angle.x);
    }

    public static float distance(Vector2 a, Vector2 b) {
        return a.dst(b);
    }

    public static float distanceSquared(Vector2 a, Vector2 b) {
        return a.dst2(b);
    }

}
