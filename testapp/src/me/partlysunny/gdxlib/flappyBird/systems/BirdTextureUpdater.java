package me.partlysunny.gdxlib.flappyBird.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.physics.Box2DPhysicsComponent;
import me.partlysunny.gdxlib.ecs.component.render.SimpleTextureComponent;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;
import me.partlysunny.gdxlib.flappyBird.components.BirdComponent;
import me.partlysunny.gdxlib.util.resource.ResourceManager;

public class BirdTextureUpdater extends IteratingSystem {

    public static final float NEGATIVE_VELOCITY_FALL_ANIMATION_THRESHOLD = -3f;
    public static final float ROTATION_THRESHOLD = 15f;
    private final float initialX;

    public BirdTextureUpdater(float initialX) {
        super(Family.all(TransformComponent.class, BirdComponent.class, Box2DPhysicsComponent.class).get());
        this.initialX = initialX;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SimpleTextureComponent texture = Mappers.get(SimpleTextureComponent.class, entity);
        Box2DPhysicsComponent physics = Mappers.get(Box2DPhysicsComponent.class, entity);
        float yVelocity = physics.getLinearVelocity().y;
        if (yVelocity > 0) {
            texture.setTexture(ResourceManager.getInstance().getTexture("bird_upflap"));
            if (physics.getRotation() < ROTATION_THRESHOLD) {
                physics.applyTorque(0.5f);
            } else {
                physics.setAngularVelocity(0f);
            }
        } else if (yVelocity < NEGATIVE_VELOCITY_FALL_ANIMATION_THRESHOLD) {
            texture.setTexture(ResourceManager.getInstance().getTexture("bird_downflap"));
            if (physics.getRotation() > -ROTATION_THRESHOLD) {
                physics.applyTorque(-0.5f);
            } else {
                physics.setAngularVelocity(0f);
            }
        } else {
            texture.setTexture(ResourceManager.getInstance().getTexture("bird_midflap"));
            physics.setRotation(0f);
        }
        physics.setPosition(new Vector2(initialX, physics.getPosition().y));
    }
}
