package me.partlysunny.gdxlib;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.control.action.ActionMap;
import me.partlysunny.gdxlib.control.action.ActionSet;
import me.partlysunny.gdxlib.control.action.KeyAction;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.entities.EnemyEntity;
import me.partlysunny.gdxlib.entities.GroundEntity;
import me.partlysunny.gdxlib.entities.ObstacleEntity;
import me.partlysunny.gdxlib.entities.PlayerEntity;
import me.partlysunny.gdxlib.tmx.TileMapInstance;
import me.partlysunny.gdxlib.tmx.TileMapManager;
import me.partlysunny.gdxlib.tmx.TmxLoader;
import me.partlysunny.gdxlib.util.Pair;
import me.partlysunny.gdxlib.util.camera.CameraHandler;
import me.partlysunny.gdxlib.util.camera.OrthoCameraHandler;

public class MainScene extends Scene {
    public MainScene(GdxGame parent) {
        super(parent);
    }

    @Override
    protected Vector2 getPhysicsGravity() {
        //return new Vector2(0, -Physics.toMeters(400));
        return new Vector2(0, 0);
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
        Entity player = SimpleEntityProvider.createSingular(PlayerEntity.class, gameWorld, new Vector2(0, 0));
        SimpleEntityProvider.createSingular(GroundEntity.class, gameWorld, new Vector2(0, -200));
        EnemyEntity enemy = new EnemyEntity(gameWorld, player);
        enemy.createEntity(new Vector2(0, 100));
        for (int i = 0; i < 20; i++) {
            SimpleEntityProvider.createSingular(ObstacleEntity.class, gameWorld, new Vector2((float) (Math.random() * 2000 - 1000), (float) (Math.random() * 2000 - 1000)));
        }
        for (int i = 0; i < 10; i++) {
            //SimpleEntityProvider.createSingular(InvisibleObstacleEntity.class, gameWorld, new Vector2((float) (Math.random() * 2000 - 1000), (float) (Math.random() * 2000 - 1000)));
        }

        TileMapInstance test1 = TmxLoader.load("test1.tmx");
        test1.setPosition(new Vector2(-150, -150));
        test1.setScale(new Vector2(4, 4));
        TileMapManager.add(test1);
    }

    @Override
    protected CameraHandler createCameraHandler() {
        return new OrthoCameraHandler(new Vector2(800, 400));
    }
}
