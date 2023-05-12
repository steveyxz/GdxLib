package me.partlysunny.gdxlib.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
        TransformComponent transformComponent = Mappers.get(TransformComponent.class, entity);
        CameraFollowComponent cameraFollowComponent = Mappers.get(CameraFollowComponent.class, entity);
        Vector2 entityCenter = transformComponent.getCenter();
        //Find the direction vector of the camera to the expected position
        Vector3 cameraDirectionFromEntity = new Vector3(entityCenter.x, entityCenter.y, camera.position.z).sub(camera.position);
        //Calculate the distance from the camera to the expected position
        //Use this distance to calculate the smoothing multiplier
        //Now multiply that by the speed of the camera
        float cameraToCenterDist = (float) Math.sqrt(Math.abs(cameraDirectionFromEntity.len2()));
        float maxSpeed = 10;
        float speedMultiplier = MathUtils.clamp(cameraFollowComponent.getFollowSpeedFunction().apply(cameraToCenterDist), -maxSpeed, maxSpeed) * cameraFollowComponent.getFollowSpeed();
        //Multiply the direction vector by the multiplier
        //Add the direction vector to the position of the camera
        cameraDirectionFromEntity.scl(speedMultiplier);
        camera.position.add(cameraDirectionFromEntity);
    }
}
