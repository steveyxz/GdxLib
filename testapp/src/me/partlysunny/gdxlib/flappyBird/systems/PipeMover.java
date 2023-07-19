package me.partlysunny.gdxlib.flappyBird.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.physics.Box2DPhysicsComponent;
import me.partlysunny.gdxlib.ecs.component.physics.PhysicsComponent;
import me.partlysunny.gdxlib.ecs.component.render.SimpleTextureComponent;
import me.partlysunny.gdxlib.ecs.component.render.VisibilityComponent;
import me.partlysunny.gdxlib.flappyBird.FlappyBirdScene;
import me.partlysunny.gdxlib.flappyBird.GameState;
import me.partlysunny.gdxlib.flappyBird.components.LevelComponent;
import me.partlysunny.gdxlib.util.Pair;
import me.partlysunny.gdxlib.util.Physics;
import me.partlysunny.gdxlib.util.resource.ResourceManager;

import java.util.HashMap;
import java.util.Map;

public class PipeMover extends IteratingSystem {

    public static float PIPE_MOVE_SPEED = -2f;

    private static final Map<Integer, Pair<LevelComponent.Side, Integer>> xMap = new HashMap<>();

    public PipeMover() {
        super(Family.all(LevelComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        LevelComponent levelComponent = Mappers.get(LevelComponent.class, entity);
        Box2DPhysicsComponent physicsComponent = Mappers.get(Box2DPhysicsComponent.class, entity);
        SimpleTextureComponent textureComponent = Mappers.get(SimpleTextureComponent.class, entity);
        VisibilityComponent vis = Mappers.get(VisibilityComponent.class, entity);
        if (!GameState.isGameStarted) {
            physicsComponent.setLinearVelocity(new Vector2(0, 0));
            vis.setVisible(false);
            return;
        }
        vis.setVisible(true);
        if (levelComponent.getSide() == LevelComponent.Side.TOP) {
            textureComponent.setTexture(ResourceManager.getInstance().getTexture("pipe_top"));
        } else {
            textureComponent.setTexture(ResourceManager.getInstance().getTexture("pipe"));
        }
        if (physicsComponent.getPosition().x < Physics.toMeters(-50)) {
            if (xMap.containsKey(levelComponent.getId())) {
                Pair<LevelComponent.Side, Integer> otherY = xMap.get(levelComponent.getId());
                if (otherY.getFirst() == LevelComponent.Side.TOP) {
                    physicsComponent.setPosition(Physics.toMeters(new Vector2(1150, FlappyBirdScene.getBottomY(otherY.getSecond()))));
                } else {
                    physicsComponent.setPosition(Physics.toMeters(new Vector2(1150, FlappyBirdScene.getTopY(otherY.getSecond()))));
                }
                xMap.remove(levelComponent.getId());
            } else {
                int y;
                if (levelComponent.getSide() == LevelComponent.Side.BOTTOM) {
                    y = FlappyBirdScene.getRandomBottomY();
                } else {
                    y = FlappyBirdScene.getTopY(FlappyBirdScene.getRandomBottomY());
                }
                physicsComponent.setPosition(Physics.toMeters(new Vector2(1150, y)));
                xMap.put(levelComponent.getId(), new Pair<>(levelComponent.getSide(), y));
            }
        }
        physicsComponent.setLinearVelocity(new Vector2(PIPE_MOVE_SPEED, 0));
    }
}
