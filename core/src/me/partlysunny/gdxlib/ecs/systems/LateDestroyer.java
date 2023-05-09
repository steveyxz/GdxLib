package me.partlysunny.gdxlib.ecs.systems;

import com.badlogic.gdx.physics.box2d.Body;
import me.partlysunny.gdxlib.ecs.GameWorld;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class LateDestroyer {

    private static GameWorld world;

    public static void init(GameWorld world) {
        LateDestroyer.world = world;
    }

    private static final Queue<Body> bodiesToDestroy = new LinkedBlockingQueue<>();

    public static void destroy(Body body) {
        bodiesToDestroy.add(body);
    }

    /**
     * Call this method after the physics world has been stepped.
     */
    public static void update() {
        Body body;
        while ((body = bodiesToDestroy.poll()) != null) {
            world.getPhysicsWorld().destroyBody(body);
        }
    }

}
