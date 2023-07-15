package me.partlysunny.gdxlib.ecs.component.ai.behaviours;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.*;
import com.badlogic.gdx.ai.steer.utils.rays.CentralRayWithWhiskersConfiguration;
import com.badlogic.gdx.ai.steer.utils.rays.SingleRayConfiguration;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.GdxGame;
import me.partlysunny.gdxlib.ecs.component.ai.EnemyRadiusProximity;
import me.partlysunny.gdxlib.ecs.component.ai.SimpleCollisionDetector;
import me.partlysunny.gdxlib.ecs.component.ai.SteeringComponent;

public class BasicPursueBehaviour implements BehaviourProvider {

    /**
     * The distance at which the enemy will be able to see objects to dodge
     * This is NOT the distance at which the enemy will see the target, that is determined by the steering component
     */
    private final float viewRange;
    private final Steerable<Vector2> target;
    private final RaycastAvoidanceSettings raycastAvoidanceSettings;

    public BasicPursueBehaviour(float viewRange, Steerable<Vector2> target, RaycastAvoidanceSettings raycastAvoidanceSettings) {
        this.viewRange = viewRange;
        this.target = target;
        this.raycastAvoidanceSettings = raycastAvoidanceSettings;
    }

    @Override
    public SteeringBehavior<Vector2> getBehaviour(SteeringComponent steeringComponent) {
        RaycastObstacleAvoidance<Vector2> collisionAvoidance = new RaycastObstacleAvoidance<>(
                steeringComponent,
                new CentralRayWithWhiskersConfiguration<>(steeringComponent, raycastAvoidanceSettings.getMainWhiskerLength(), raycastAvoidanceSettings.getSideWhiskerLength(), raycastAvoidanceSettings.getSideWhiskerAngle()),
                new SimpleCollisionDetector(GdxGame.getInstance().getGameWorld().getPhysicsWorld()),
                raycastAvoidanceSettings.getMinDistanceToAvoid()
        );
        Pursue<Vector2> pursue = new Pursue<>(steeringComponent, target);
        LookWhereYouAreGoing<Vector2> face = new LookWhereYouAreGoing<>(steeringComponent);
        face.setAlignTolerance(0.001f);
        BlendedSteering<Vector2> blendedSteering = new BlendedSteering<>(steeringComponent);
        blendedSteering.add(face, 1);
        blendedSteering.add(pursue, 1);
        return new PrioritySteering<>(steeringComponent, 0.001f).add(collisionAvoidance).add(blendedSteering);
    }
}
