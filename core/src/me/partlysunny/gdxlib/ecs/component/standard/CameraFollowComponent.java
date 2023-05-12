package me.partlysunny.gdxlib.ecs.component.standard;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

import java.util.function.BiFunction;
import java.util.function.Function;

public class CameraFollowComponent implements Component, Pool.Poolable {

    public static final Function<Float, Float> SLOWER_AS_CLOSER = (a) -> a;
    public static final Function<Float, Float> FASTER_AS_CLOSER = (a) -> 1 / a;

    private float followSpeed = 1f;
    //This function returns a multiplier for the speed based on the distance
    private Function<Float, Float> followSpeedFunction = SLOWER_AS_CLOSER;
    private float followOffsetX = 0;
    private float followOffsetY = 0;

    public CameraFollowComponent(float followSpeed, float followOffsetX, float followOffsetY) {
        this.followSpeed = followSpeed;
        this.followOffsetX = followOffsetX;
        this.followOffsetY = followOffsetY;
    }

    public CameraFollowComponent(float followSpeed, float followOffsetX, float followOffsetY, Function<Float, Float> followSpeedFunction) {
        this.followSpeed = followSpeed;
        this.followOffsetX = followOffsetX;
        this.followOffsetY = followOffsetY;
        this.followSpeedFunction = followSpeedFunction;
    }

    public CameraFollowComponent() {
    }

    public float getFollowSpeed() {
        return followSpeed;
    }

    public void setFollowSpeed(float followSpeed) {
        this.followSpeed = followSpeed;
    }

    public float getFollowOffsetX() {
        return followOffsetX;
    }

    public void setFollowOffsetX(float followOffsetX) {
        this.followOffsetX = followOffsetX;
    }

    public float getFollowOffsetY() {
        return followOffsetY;
    }

    public void setFollowOffsetY(float followOffsetY) {
        this.followOffsetY = followOffsetY;
    }

    public Function<Float, Float> getFollowSpeedFunction() {
        return followSpeedFunction;
    }

    public void setFollowSpeedFunction(Function<Float, Float> followSpeedFunction) {
        this.followSpeedFunction = followSpeedFunction;
    }

    @Override
    public void reset() {
        followSpeed = 1f;
        followOffsetX = 0;
        followOffsetY = 0;
        followSpeedFunction = FASTER_AS_CLOSER;
    }
}
