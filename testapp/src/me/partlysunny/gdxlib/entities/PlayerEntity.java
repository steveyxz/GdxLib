package me.partlysunny.gdxlib.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.control.ControllerComponent;
import me.partlysunny.gdxlib.ecs.component.standard.CameraFollowComponent;
import me.partlysunny.gdxlib.ecs.entity.ShapeEntityProvider;
import me.partlysunny.gdxlib.util.Physics;
import me.partlysunny.gdxlib.util.ShapeBuilder;

public class PlayerEntity extends ShapeEntityProvider {
    public PlayerEntity(GameWorld world) {
        super(world);
    }

    @Override
    protected float getDecelerateRate() {
        return 0.95f;
    }

    @Override
    protected BodyDef.BodyType getBodyType() {
        return BodyDef.BodyType.DynamicBody;
    }

    @Override
    protected Shape getShape() {
        return ShapeBuilder.circle(Vector2.Zero, Physics.toMeters(50));
    }

    @Override
    protected Color getColor() {
        return Color.LIME;
    }


    @Override
    protected void addExtraComponents(Entity e) {
        CameraFollowComponent cameraFollow = world.getEntityWorld().createComponent(CameraFollowComponent.class);
        cameraFollow.setFollowSpeed(0.01f);
        cameraFollow.setFollowSpeedFunction(CameraFollowComponent.SLOWER_AS_CLOSER);
        e.add(cameraFollow);
        ControllerComponent component = world.getEntityWorld().createComponent(ControllerComponent.class);
        component.setController(new MovementController(e));
        e.add(component);
    }
}
