package me.partlysunny.gdxlib;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.control.action.ActionMap;
import me.partlysunny.gdxlib.control.action.ActionSet;
import me.partlysunny.gdxlib.control.action.KeyAction;
import me.partlysunny.gdxlib.ecs.entity.EntityProvider;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.entities.CircleEntity;
import me.partlysunny.gdxlib.entities.GroundEntity;
import me.partlysunny.gdxlib.entities.PlayerEntity;
import me.partlysunny.gdxlib.entities.PolygonEntity;
import me.partlysunny.gdxlib.util.Pair;
import me.partlysunny.gdxlib.util.Physics;
import me.partlysunny.gdxlib.util.camera.CameraHandler;
import me.partlysunny.gdxlib.util.camera.OrthoCameraHandler;

public class MainGame extends GdxGame {
    @Override
    protected Vector2 getPhysicsGravity() {
        return new Vector2(0, -Physics.toMeters(150));
    }

    @Override
    protected void loadResources() {
        controlHub.getActionMap().addActions(ActionMap.of(
                Pair.of("up", ActionSet.of(new KeyAction(Input.Keys.W))),
                Pair.of("left", ActionSet.of(new KeyAction(Input.Keys.A))),
                Pair.of("down", ActionSet.of(new KeyAction(Input.Keys.S))),
                Pair.of("right", ActionSet.of(new KeyAction(Input.Keys.D)))
        ));
    }

    @Override
    protected void update(float delta) {

    }

    @Override
    protected void createOriginalEntities() {
        SimpleEntityProvider.createSingular(PlayerEntity.class, gameWorld, new Vector2(0, 0));
        EntityProvider shapeProvider = new CircleEntity(gameWorld);
        for (int i = 0; i < 50; i++) {
            shapeProvider.createEntity(new Vector2((float) (Math.random() * 300 - 150), (float) (Math.random() * 300)));
        }
        EntityProvider shapeProvider2 = new PolygonEntity(gameWorld);
        for (int i = 0; i < 50; i++) {
            shapeProvider2.createEntity(new Vector2((float) (Math.random() * 300 - 150), (float) (Math.random() * 300)));
        }
        SimpleEntityProvider.createSingular(GroundEntity.class, gameWorld, new Vector2(0, -200));
    }

    @Override
    protected CameraHandler createCameraHandler() {
        return new OrthoCameraHandler(new Vector2(800, 400));
    }
}
