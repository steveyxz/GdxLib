package me.partlysunny.gdxlib;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.control.action.ActionMap;
import me.partlysunny.gdxlib.control.action.ActionSet;
import me.partlysunny.gdxlib.control.action.KeyAction;
import me.partlysunny.gdxlib.ecs.entity.EntityProvider;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.entities.GroundEntity;
import me.partlysunny.gdxlib.entities.PlayerEntity;
import me.partlysunny.gdxlib.entities.SquareEntity;
import me.partlysunny.gdxlib.util.Pair;
import me.partlysunny.gdxlib.util.camera.CameraHandler;
import me.partlysunny.gdxlib.util.camera.OrthoCameraHandler;

public class MainGame extends GdxGame {
    @Override
    protected Vector2 getPhysicsGravity() {
        return new Vector2(0, -4);
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
        EntityProvider shapeProvider = new SquareEntity(gameWorld);
        for (int i = 0; i < 4000; i++) {
            shapeProvider.createEntity(new Vector2((float) (Math.random() * 10000 - 5000), (float) (Math.random() * 10000 - 5000)));
        }
        SimpleEntityProvider.createSingular(GroundEntity.class, gameWorld, new Vector2(0, -1000));
    }

    @Override
    protected CameraHandler createCameraHandler() {
        return new OrthoCameraHandler(new Vector2(800, 400));
    }
}
