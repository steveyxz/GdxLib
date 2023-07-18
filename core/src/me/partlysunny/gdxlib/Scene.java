package me.partlysunny.gdxlib;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import de.eskalon.commons.screen.ManagedScreen;
import me.partlysunny.gdxlib.control.ControlHub;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.systems.LateDestroyer;
import me.partlysunny.gdxlib.tmx.TileMapManager;
import me.partlysunny.gdxlib.util.Debug;
import me.partlysunny.gdxlib.util.camera.CameraHandler;

public abstract class Scene extends ManagedScreen {

    protected final GdxGame parent;
    protected final ControlHub controlHub = new ControlHub();
    protected GameWorld gameWorld;
    protected Camera camera = null;
    protected CameraHandler cameraHandler = null;

    protected Scene(GdxGame parent) {
        this.parent = parent;
    }

    @Override
    protected void create() {
        Debug.logDebug("STARTING LOAD SEQUENCE FOR SCENE " + this.getClass().getSimpleName() + ":");
        Debug.logDebug("Loading resources...");
        loadResources();
        Debug.logDebug("Initializing camera...");
        cameraHandler = createCameraHandler();
        camera = cameraHandler.createCamera();
        Debug.logDebug("Initializing game world...");
        World physicsWorld = new World(getPhysicsGravity(), true);
        PooledEngine entityWorld = new PooledEngine();
        gameWorld = new GameWorld(physicsWorld, entityWorld, parent.batchSet, new Stage());
        initializeSystems(gameWorld.getEntityWorld());
        LateDestroyer.init(gameWorld);
        createOriginalEntities();
        Debug.logDebug("Initializing tile map entities");
        TileMapManager.spawnObjects(gameWorld);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(gameWorld.getUiWorld());
    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {
        //Clear the screen
        ScreenUtils.clear(0, 0, 0, 1);
        //Update any custom logic that may exist
        update(delta);
        //Update the game world, rendering with a BatchSet
        if (camera instanceof OrthographicCamera) {
            OrthographicCamera oth = (OrthographicCamera) camera;
            TileMapManager.setView(oth);
            TileMapManager.render();
        }
        parent.batchSet.setProjectionMatrix(camera.combined);
        parent.batchSet.begin();
        gameWorld.update(delta);
        parent.batchSet.end();
        Debug.renderBox2DDebug();
        //Dispatch any actions for this frame
        controlHub.getActionDispatcher().update();
        //Destroy entities that were marked for destruction
        LateDestroyer.update();
        if (isCameraPixelPerfect()) {
            camera.position.set((int) camera.position.x, (int) camera.position.y, 100);
        }
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        cameraHandler.updateViewport(camera, width, height);
        gameWorld.getUiWorld().getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public Camera getCamera() {
        return camera;
    }

    public ControlHub getControlHub() {
        return controlHub;
    }

    protected boolean isCameraPixelPerfect() {
        return true;
    }

    protected abstract Vector2 getPhysicsGravity();

    protected abstract void initializeSystems(Engine engine);

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
