package me.partlysunny.gdxlib.ecs.component.ai.behaviours;

public class RaycastAvoidanceSettings {

    private final float mainWhiskerLength;
    private final float sideWhiskerLength;
    private final float sideWhiskerAngle;
    private final float minDistanceToAvoid;

    public RaycastAvoidanceSettings(float mainWhiskerLength, float sideWhiskerLength, float sideWhiskerAngle, float minDistanceToAvoid) {
        this.mainWhiskerLength = mainWhiskerLength;
        this.sideWhiskerLength = sideWhiskerLength;
        this.sideWhiskerAngle = sideWhiskerAngle;
        this.minDistanceToAvoid = minDistanceToAvoid;
    }

    public float getMainWhiskerLength() {
        return mainWhiskerLength;
    }

    public float getSideWhiskerLength() {
        return sideWhiskerLength;
    }

    public float getSideWhiskerAngle() {
        return sideWhiskerAngle;
    }

    public float getMinDistanceToAvoid() {
        return minDistanceToAvoid;
    }
}
