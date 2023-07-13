package me.partlysunny.gdxlib;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import me.partlysunny.gdxlib.control.ControlHub;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.systems.LateDestroyer;
import me.partlysunny.gdxlib.ecs.systems.render.BatchSet;
import me.partlysunny.gdxlib.tmx.TileMapInstance;
import me.partlysunny.gdxlib.tmx.TileMapManager;
import me.partlysunny.gdxlib.util.Debug;
import me.partlysunny.gdxlib.util.camera.CameraHandler;
import me.partlysunny.gdxlib.util.resource.ResourceManager;

public abstract class GdxGame extends ApplicationAdapter {

    private static GdxGame instance;
    protected final ControlHub controlHub = new ControlHub();
    protected GameWorld gameWorld;
    protected BatchSet batchSet;
    protected Camera camera = null;
    protected CameraHandler cameraHandler = null;

    public static GdxGame getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GdxGame instance is null!");
        }
        return instance;
    }

    @Override
    public void create() {
        instance = this;
        Debug.logDebug("Loading resources...");
        loadResources();
        Debug.logDebug("Initializing sprite batches...");
        batchSet = new BatchSet(new SpriteBatch(), new PolygonSpriteBatch());
        Debug.logDebug("Initializing camera...");
        cameraHandler = createCameraHandler();
        camera = cameraHandler.createCamera();
        Debug.logDebug("Initializing game world...");
        World physicsWorld = new World(getPhysicsGravity(), true);
        PooledEngine entityWorld = new PooledEngine();
        gameWorld = new GameWorld(physicsWorld, entityWorld, batchSet);
        LateDestroyer.init(gameWorld);
        createOriginalEntities();
        Debug.logDebug("Initializing tile map entities");
        TileMapManager.spawnObjects(gameWorld);
    }


    @Override
    public void render() {
        //Clear the screen
        ScreenUtils.clear(0, 0, 0, 1);
        float delta = Gdx.graphics.getDeltaTime();
        //Update any custom logic that may exist
        update(delta);
        //Update the game world, rendering with a BatchSet
        if (camera instanceof OrthographicCamera) {
            OrthographicCamera oth = (OrthographicCamera) camera;
            TileMapManager.setView(oth);
            TileMapManager.render();
        }
        batchSet.setProjectionMatrix(camera.combined);
        batchSet.begin();
        gameWorld.update(delta);
        batchSet.end();
        Debug.renderBox2DDebug();
        //Dispatch any actions for this frame
        controlHub.getActionDispatcher().update();
        //Destroy entities that were marked for destruction
        LateDestroyer.update();
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        cameraHandler.updateViewport(camera, width, height);
    }

    @Override
    public void dispose() {
        //Dispose of all resources
        Debug.logDebug("Disposing of sprite renderers...");
        batchSet.dispose();
        Debug.logDebug("Disposing of game world...");
        gameWorld.dispose();
        Debug.logDebug("Disposing of resources...");
        ResourceManager.getInstance().dispose();
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public BatchSet getBatchSet() {
        return batchSet;
    }

    public Camera getCamera() {
        return camera;
    }

    public ControlHub getControlHub() {
        return controlHub;
    }

    protected abstract Vector2 getPhysicsGravity();

    /**
     * Load all resources here
     * Use {@code ResourceManager.getInstance().add()} to load resources
     */
    protected abstract void loadResources();

    /**
     * Update the game world here (recommended to try to use entities, this is for any custom systems you may have)
     * It will run before the game world is updated
     *
     * @param delta The time in seconds since the last render
     */
    protected abstract void update(float delta);

    /**
     * Create all the entities here
     */
    protected abstract void createOriginalEntities();

    /**
     * Create the camera handler here
     *
     * @return The camera handler, which will be used to create the camera, handles etc.
     */
    protected abstract CameraHandler createCameraHandler();
}
