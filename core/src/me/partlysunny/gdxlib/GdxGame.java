package me.partlysunny.gdxlib;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import de.eskalon.commons.core.ManagedGame;
import de.eskalon.commons.screen.ManagedScreen;
import de.eskalon.commons.screen.ScreenManager;
import de.eskalon.commons.screen.transition.ScreenTransition;
import me.partlysunny.gdxlib.control.ControlHub;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.systems.LateDestroyer;
import me.partlysunny.gdxlib.ecs.systems.render.BatchSet;
import me.partlysunny.gdxlib.tmx.TileMapManager;
import me.partlysunny.gdxlib.util.Debug;
import me.partlysunny.gdxlib.util.camera.CameraHandler;
import me.partlysunny.gdxlib.util.resource.ResourceManager;
import org.jetbrains.annotations.Nullable;

public abstract class GdxGame extends ManagedGame<ManagedScreen, ScreenTransition> {

    private static GdxGame instance;

    public static GdxGame getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GdxGame instance is null!");
        }
        return instance;
    }

    protected BatchSet batchSet;

    @Override
    public void create() {
        super.create();
        Debug.logDebug("Initializing sprite batches...");
        batchSet = new BatchSet(new SpriteBatch(), new PolygonSpriteBatch());
        instance = this;
        initializeTransitions(this.getScreenManager());
        initializeScreens(this.getScreenManager());
        this.getScreenManager().pushScreen(getInitialScreen(), getInitialTransition());
    }


    @Override
    public void render() {
        super.render();
        GdxAI.getTimepiece().update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        batchSet.dispose();
    }

    public Scene getCurrentScene() {
        return (Scene) this.getScreenManager().getCurrentScreen();
    }

    public abstract void initializeScreens(ScreenManager<ManagedScreen, ScreenTransition> screenManager);

    public abstract void initializeTransitions(ScreenManager<ManagedScreen, ScreenTransition> transitionFactory);

    protected abstract String getInitialScreen();

    @Nullable
    protected abstract String getInitialTransition();

}
