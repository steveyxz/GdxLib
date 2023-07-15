package me.partlysunny.gdxlib.ecs.component.ai;

import com.badlogic.gdx.ai.utils.Collision;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;

public class SimpleCollisionDetector implements RaycastCollisionDetector<Vector2> {

    private final World world;
    private final SimpleRaycastCallback callback;

    public SimpleCollisionDetector (World world) {
        this(world, new SimpleRaycastCallback());
    }

    public SimpleCollisionDetector (World world, SimpleRaycastCallback callback) {
        this.world = world;
        this.callback = callback;
    }

    @Override
    public boolean collides (Ray<Vector2> ray) {
        return findCollision(null, ray);
    }

    @Override
    public boolean findCollision (Collision<Vector2> outputCollision, Ray<Vector2> inputRay) {
        callback.collided = false;
        if (!inputRay.start.epsilonEquals(inputRay.end, MathUtils.FLOAT_ROUNDING_ERROR)) {
            callback.outputCollision = outputCollision;
            world.rayCast(callback, inputRay.start, inputRay.end);
        }
        return callback.collided;
    }

    public static class SimpleRaycastCallback implements RayCastCallback {
        public Collision<Vector2> outputCollision;
        public boolean collided;

        public SimpleRaycastCallback() {
        }

        @Override
        public float reportRayFixture (Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
            if (outputCollision != null) outputCollision.set(point, normal);
            collided = true;
            return fraction;
        }
    }
}
