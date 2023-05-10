package me.partlysunny.gdxlib.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.standard.CameraFollowComponent;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;

public class CameraMoverSystem extends IteratingSystem {

    private final Camera camera;

    public CameraMoverSystem(Camera camera) {
        super(Family.all(CameraFollowComponent.class, TransformComponent.class).get());
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = Mappers.getComponentMapper(TransformComponent.class).get(entity);
        camera.position.set(transformComponent.getPosition().x, transformComponent.getPosition().y, 0);
    }
}
