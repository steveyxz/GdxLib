package me.partlysunny.gdxlib.ecs.component.ai.behaviours;

import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.component.ai.SteeringComponent;

public interface BehaviourProvider {

    SteeringBehavior<Vector2> getBehaviour(SteeringComponent steeringComponent);

}
