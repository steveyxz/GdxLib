package me.partlysunny.gdxlib.ecs.systems;

import com.badlogic.ashley.core.PooledEngine;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.systems.physics.PhysicsSystem;
import me.partlysunny.gdxlib.ecs.systems.physics.PositionSyncerSystem;
import me.partlysunny.gdxlib.ecs.systems.render.*;
import me.partlysunny.gdxlib.util.Debug;

public final class SystemManager {

    private SystemManager() {
    }

    public static void init(GameWorld world, BatchSet batchSet) {
        Debug.logDebug("Initializing Systems...");
        PooledEngine entityWorld = world.getEntityWorld();

        entityWorld.addSystem(new DestroyerSystem(world));
        //Renderer Systems
        entityWorld.addSystem(new AnimatedRendererSystem(batchSet.getSpriteBatch()));
        entityWorld.addSystem(new RegionalRendererSystem(batchSet.getSpriteBatch()));
        entityWorld.addSystem(new SimpleRendererSystem(batchSet.getSpriteBatch()));
        entityWorld.addSystem(new ShapeRendererSystem(batchSet.getPolygonSpriteBatch()));
        //Physics Systems
        entityWorld.addSystem(new PositionSyncerSystem());
        entityWorld.addSystem(new PhysicsSystem());
        Debug.logDebug("Systems Initialized!");
    }

}
