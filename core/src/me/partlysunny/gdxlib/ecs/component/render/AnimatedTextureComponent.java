package me.partlysunny.gdxlib.ecs.component.render;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedTextureComponent implements RendererComponent {

    private Animation<TextureRegion> animation;
    private float stateTime = 0;

    public AnimatedTextureComponent(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public void resetStateTime() {
        this.stateTime = 0;
    }

    public void tickStateTime(float deltaTime) {
        this.stateTime += deltaTime;
    }
}
