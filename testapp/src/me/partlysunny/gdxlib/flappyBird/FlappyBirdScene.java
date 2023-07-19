package me.partlysunny.gdxlib.flappyBird;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.kotcrab.vis.ui.building.utilities.Alignment;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;
import me.partlysunny.gdxlib.GdxGame;
import me.partlysunny.gdxlib.Scene;
import me.partlysunny.gdxlib.control.action.ActionSet;
import me.partlysunny.gdxlib.control.action.KeyAction;
import me.partlysunny.gdxlib.control.action.MouseAction;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.flappyBird.components.LevelComponent;
import me.partlysunny.gdxlib.flappyBird.components.UiComponent;
import me.partlysunny.gdxlib.flappyBird.entites.*;
import me.partlysunny.gdxlib.flappyBird.systems.BirdTextureUpdater;
import me.partlysunny.gdxlib.flappyBird.systems.PipeMover;
import me.partlysunny.gdxlib.flappyBird.systems.UiUpdater;
import me.partlysunny.gdxlib.util.Physics;
import me.partlysunny.gdxlib.util.RandomUtils;
import me.partlysunny.gdxlib.util.TextureModifier;
import me.partlysunny.gdxlib.util.camera.CameraHandler;
import me.partlysunny.gdxlib.util.camera.OrthoCameraHandler;
import me.partlysunny.gdxlib.util.resource.FontResource;
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
        engine.addSystem(new BirdTextureUpdater(Physics.toMeters(163)));
        engine.addSystem(new PipeMover());
        engine.addSystem(new UiUpdater());
    }

    @Override
    protected void loadResources() {
        ResourceManager instance = ResourceManager.getInstance();
        instance.add("bird_upflap", new TextureResource("flappy-bird-assets/sprites/yellowbird-upflap.png"));
        instance.add("bird_midflap", new TextureResource("flappy-bird-assets/sprites/yellowbird-midflap.png"));
        instance.add("bird_downflap", new TextureResource("flappy-bird-assets/sprites/yellowbird-downflap.png"));
        instance.add("pipe", new TextureResource("flappy-bird-assets/sprites/pipe-green.png"));
        instance.add("pipe_top", new TextureResource(TextureModifier.flipTexture(ResourceManager.getInstance().getTexture("pipe"), false, true)));
        instance.add("background_day", new TextureResource("flappy-bird-assets/sprites/background-day.png"));
        instance.add("base", new TextureResource("flappy-bird-assets/sprites/base.png"));
        instance.add("gameover", new TextureResource("flappy-bird-assets/sprites/gameover.png"));
        instance.add("message", new TextureResource("flappy-bird-assets/sprites/message.png"));
        instance.add("die", new SoundResource("flappy-bird-assets/audio/die.ogg"));
        instance.add("hit", new SoundResource("flappy-bird-assets/audio/hit.ogg"));
        instance.add("point", new SoundResource("flappy-bird-assets/audio/point.ogg"));
        instance.add("swoosh", new SoundResource("flappy-bird-assets/audio/swoosh.ogg"));
        instance.add("wing", new SoundResource("flappy-bird-assets/audio/wing.ogg"));
        instance.add("flappy_font", new FontResource("flappy-bird-assets/fonts/flappy-font.ttf", FontResource.FontType.TTF, 100));

        controlHub.getActionMap().addActions("flap", ActionSet.of(ActionSet.VerificationType.ANY, new KeyAction(Input.Keys.SPACE, true), new MouseAction(Input.Buttons.LEFT, true))).addActions("restart", new KeyAction(Input.Keys.R, true));
    }

    @Override
    protected void update(float delta) {
    }

    @Override
    protected void createOriginalEntities() {
        SimpleEntityProvider.createSingular(BaseEntity.class, getGameWorld(), new Vector2(0, -30));
        SimpleEntityProvider.createSingular(BackgroundEntity.class, getGameWorld(), new Vector2(0, -30));
        SimpleEntityProvider.createSingular(BirdEntity.class, getGameWorld(), new Vector2(163, 400));

        int pipeGap = 400;
        int pipeCount = 3;

        for (int i = 0; i < pipeCount; i++) {
            int xPos = i * pipeGap - 50;
            int bottomY = getRandomBottomY();
            new PipeEntity(gameWorld, LevelComponent.Side.BOTTOM, i).createEntity(new Vector2(xPos, bottomY));
            new PipeEntity(gameWorld, LevelComponent.Side.TOP, i).createEntity(new Vector2(xPos, getTopY(bottomY)));
        }

        VisLabel score = new VisLabel("0");
        score.setPosition(180, 600, Alignment.CENTER.getAlignment());
        score.setStyle(new Label.LabelStyle(ResourceManager.getInstance().getFont("flappy_font"), Color.WHITE));
        gameWorld.getUiWorld().addActor(score);

        VisImage gameOver = new VisImage(ResourceManager.getInstance().getTexture("gameover"));
        gameOver.setPosition(180, 200, Alignment.CENTER.getAlignment());
        gameOver.setVisible(false);
        gameWorld.getUiWorld().addActor(gameOver);

        VisImage tapToStart = new VisImage(ResourceManager.getInstance().getTexture("message"));
        tapToStart.setPosition(180, 382, Alignment.CENTER.getAlignment());
        tapToStart.setVisible(false);
        gameWorld.getUiWorld().addActor(tapToStart);

        UiComponent uiComponent = gameWorld.getEntityWorld().createComponent(UiComponent.class);
        uiComponent.setScoreLabel(score);
        uiComponent.setGameOverImage(gameOver);
        uiComponent.setTapToStartImage(tapToStart);

        new UIController(gameWorld, uiComponent).createEntity(new Vector2(0, 0));
    }

    public static int getTopY(int bottomY) {
        return bottomY + 450;
    }

    public static int getBottomY(int topY) {
        return topY - 450;
    }

    public static int getRandomBottomY() {
        return RandomUtils.nextInt(-100, 150);
    }

    @Override
    protected CameraHandler createCameraHandler() {
        return new OrthoCameraHandler(new Vector2(360, 640));
    }
}
