package me.partlysunny.gdxlib.flappyBird.components;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.control.EntityController;
import me.partlysunny.gdxlib.ecs.component.physics.Box2DPhysicsComponent;
import me.partlysunny.gdxlib.flappyBird.GameState;

public class FlapController extends EntityController {

    public static final float FLAP_VELOCITY = 3.5f;

    public FlapController(Entity entity, String... actions) {
        super(entity, actions);
    }

    @Override
    protected void update(String action, Entity entity) {
        if (!GameState.isGameStarted) {
            GameState.isGameStarted = true;
            GameState.isGameOver = false;
            GameState.score = 0;
        }
        Box2DPhysicsComponent transform = Mappers.get(Box2DPhysicsComponent.class, entity);
        transform.setLinearVelocity(new Vector2(0, FLAP_VELOCITY));
    }
}
