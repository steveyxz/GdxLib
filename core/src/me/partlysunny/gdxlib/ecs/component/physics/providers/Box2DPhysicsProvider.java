package me.partlysunny.gdxlib.ecs.component.physics.providers;

import com.badlogic.gdx.physics.box2d.Body;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.physics.Box2DPhysicsComponent;
import me.partlysunny.gdxlib.ecs.component.physics.PhysicsComponent;

public class Box2DPhysicsProvider implements PhysicsProvider {

    private final Body body;
    private final float decelerationRate;

    public Box2DPhysicsProvider(Body body, float decelerationRate) {
        this.body = body;
        this.decelerationRate = decelerationRate;
    }

    @Override
    public PhysicsComponent createPhysics(GameWorld world) {
        Box2DPhysicsComponent component = world.getEntityWorld().createComponent(Box2DPhysicsComponent.class);
        component.setLinkedBody(body);
        component.setDecelerationRate(decelerationRate);
        return component;
    }
}
