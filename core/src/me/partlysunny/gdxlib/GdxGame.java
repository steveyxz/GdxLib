package me.partlysunny.gdxlib;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.systems.DestroyerSystem;
import me.partlysunny.gdxlib.ecs.systems.LateDestroyer;
import me.partlysunny.gdxlib.ecs.systems.render.BatchSet;
import me.partlysunny.gdxlib.util.Debug;
import me.partlysunny.gdxlib.util.resource.ResourceManager;

public abstract class GdxGame extends ApplicationAdapter {

	private GameWorld gameWorld;
	private BatchSet batchSet;

	@Override
	public void create() {
		Debug.logDebug("Loading resources...");
		loadResources();
		Debug.logDebug("Initializing sprite batches...");
		batchSet = new BatchSet(new SpriteBatch(), new PolygonSpriteBatch());
		Debug.logDebug("Initializing game world...");
		World physicsWorld = new World(getPhysicsGravity(), true);
		PooledEngine entityWorld = new PooledEngine();
		gameWorld = new GameWorld(physicsWorld, entityWorld, batchSet);
		LateDestroyer.init(gameWorld);
		createOriginalEntities();
	}

	@Override
	public void render() {
		//Clear the screen
		ScreenUtils.clear(1, 0, 0, 1);
		float delta = Gdx.graphics.getDeltaTime();
		//Update any custom logic that may exist
		update(delta);
		//Update the game world, rendering with a BatchSet
		batchSet.begin();
		gameWorld.update(delta);
		batchSet.end();
		//Destroy entities that were marked for destruction
		LateDestroyer.update();
	}
	
	@Override
	public void dispose () {
		//Dispose of all resources
		Debug.logDebug("Disposing of sprite renderers...");
		batchSet.dispose();
		Debug.logDebug("Disposing of game world...");
		gameWorld.dispose();
		Debug.logDebug("Disposing of resources...");
		ResourceManager.getInstance().dispose();
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
	 * @param delta The time in seconds since the last render
	 */
	protected abstract void update(float delta);

	/**
	 * Create all the entities here
	 */
	protected abstract void createOriginalEntities();
}
