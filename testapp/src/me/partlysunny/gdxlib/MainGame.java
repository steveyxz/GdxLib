package me.partlysunny.gdxlib;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.entity.EntityProvider;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.entities.PlayerEntity;
import me.partlysunny.gdxlib.entities.SquareEntity;
import me.partlysunny.gdxlib.util.camera.CameraHandler;
import me.partlysunny.gdxlib.util.camera.OrthoCameraHandler;
import me.partlysunny.gdxlib.util.resource.ResourceManager;
import me.partlysunny.gdxlib.util.resource.TextureResource;

public class MainGame extends GdxGame {
    @Override
    protected Vector2 getPhysicsGravity() {
        return new Vector2(0, -9.8f);
    }

    @Override
    protected void loadResources() {
        ResourceManager.getInstance().add("logo", new TextureResource("badlogic.jpg"));
    }

    @Override
    protected void update(float delta) {

    }

    @Override
    protected void createOriginalEntities() {
        SimpleEntityProvider.createSingular(PlayerEntity.class, gameWorld, new Vector2(0, 0));
        EntityProvider shapeProvider = new SquareEntity(gameWorld);
        for (int i = 0; i < 4000; i++) {
            shapeProvider.createEntity(new Vector2((float) (Math.random() * 10000 - 5000), (float) (Math.random() * 10000 - 5000)));
        }
    }

    @Override
    protected CameraHandler createCameraHandler() {
        return new OrthoCameraHandler(new Vector2(800, 400));
    }
}
