package me.partlysunny.gdxlib.flappyBird;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.GdxGame;
import me.partlysunny.gdxlib.Scene;
import me.partlysunny.gdxlib.control.action.KeyAction;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.flappyBird.entites.BackgroundEntity;
import me.partlysunny.gdxlib.flappyBird.entites.BaseEntity;
import me.partlysunny.gdxlib.flappyBird.entites.BirdEntity;
import me.partlysunny.gdxlib.flappyBird.entites.PipeEntity;
import me.partlysunny.gdxlib.flappyBird.systems.BirdTextureUpdater;
import me.partlysunny.gdxlib.util.Physics;
import me.partlysunny.gdxlib.util.camera.CameraHandler;
import me.partlysunny.gdxlib.util.camera.OrthoCameraHandler;
import me.partlysunny.gdxlib.util.resource.ResourceManager;
import me.partlysunny.gdxlib.util.resource.SoundResource;
import me.partlysunny.gdxlib.util.resource.TextureResource;

public class FlappyBirdScene extends Scene {

    public FlappyBirdScene(GdxGame parent) {
        super(parent);
    }

    @Override
    protected Vector2 getPhysicsGravity() {
        return new Vector2(0, Physics.toMeters(-198));
    }

    @Override
    protected void initializeSystems(Engine engine) {
        engine.addSystem(new BirdTextureUpdater(Physics.toMeters(150)));
    }

    @Override
    protected void loadResources() {
        ResourceManager instance = ResourceManager.getInstance();
        instance.add("bird_upflap", new TextureResource("flappy-bird-assets/sprites/yellowbird-upflap.png"));
        instance.add("bird_midflap", new TextureResource("flappy-bird-assets/sprites/yellowbird-midflap.png"));
        instance.add("bird_downflap", new TextureResource("flappy-bird-assets/sprites/yellowbird-downflap.png"));
        instance.add("pipe", new TextureResource("flappy-bird-assets/sprites/pipe-green.png"));
        instance.add("background_day", new TextureResource("flappy-bird-assets/sprites/background-day.png"));
        instance.add("base", new TextureResource("flappy-bird-assets/sprites/base.png"));
        instance.add("die", new SoundResource("flappy-bird-assets/audio/die.ogg"));
        instance.add("hit", new SoundResource("flappy-bird-assets/audio/hit.ogg"));
        instance.add("point", new SoundResource("flappy-bird-assets/audio/point.ogg"));
        instance.add("swoosh", new SoundResource("flappy-bird-assets/audio/swoosh.ogg"));
        instance.add("wing", new SoundResource("flappy-bird-assets/audio/wing.ogg"));

        controlHub.getActionMap().addActions("flap", new KeyAction(Input.Keys.SPACE, true)).addActions("restart", new KeyAction(Input.Keys.R, true));
    }

    @Override
    protected void update(float delta) {
    }

    @Override
    protected void createOriginalEntities() {
        SimpleEntityProvider.createSingular(BaseEntity.class, getGameWorld(), new Vector2(0, -30));
        SimpleEntityProvider.createSingular(BackgroundEntity.class, getGameWorld(), new Vector2(0, -30));
        SimpleEntityProvider.createSingular(BirdEntity.class, getGameWorld(), new Vector2(150, 400));
        SimpleEntityProvider.createSingular(PipeEntity.class, getGameWorld(), new Vector2(300, 0));
    }

    @Override
    protected CameraHandler createCameraHandler() {
        return new OrthoCameraHandler(new Vector2(360, 640));
    }
}
