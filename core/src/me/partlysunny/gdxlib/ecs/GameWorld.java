package me.partlysunny.gdxlib.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import me.partlysunny.gdxlib.GdxGame;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.control.ControllerComponent;
import me.partlysunny.gdxlib.ecs.component.physics.Box2DPhysicsComponent;
import me.partlysunny.gdxlib.ecs.systems.LateDestroyer;
import me.partlysunny.gdxlib.ecs.systems.SystemManager;
import me.partlysunny.gdxlib.ecs.systems.render.BatchSet;

public class GameWorld implements Disposable {

    private static final float STEP_TIME = 1f / 30f;
    private final World physicsWorld;
    private final PooledEngine entityWorld;
    private final Stage uiWorld;

    public GameWorld(World physicsWorld, PooledEngine entityWorld, BatchSet batchSet, Stage uiWorld) {
        this.physicsWorld = physicsWorld;
        this.entityWorld = entityWorld;
        this.uiWorld = uiWorld;
        SystemManager.init(this, batchSet);
    }

    public World getPhysicsWorld() {
        return physicsWorld;
    }

    public PooledEngine getEntityWorld() {
        return entityWorld;
    }

    public Stage getUiWorld() {
        return uiWorld;
    }

    public void update(float delta) {
        physicsWorld.step(STEP_TIME, 6, 2);
        entityWorld.update(delta);
        uiWorld.act(delta);
        uiWorld.draw();
    }

    @Override
    public void dispose() {
        physicsWorld.dispose();
        entityWorld.clearPools();
        uiWorld.dispose();
    }

    public Body createBody(BodyDef def, FixtureDef... fixtures) {
        Body body = physicsWorld.createBody(def);
        for (FixtureDef fixture : fixtures) {
            body.createFixture(fixture);
        }
        return body;
    }

    public Entity findEntityWithPhysicsBody(Body body) {
        for (Entity entity : entityWorld.getEntities()) {
            Box2DPhysicsComponent physics = Mappers.get(Box2DPhysicsComponent.class, entity);
            if (physics != null && physics.getLinkedBody().equals(body)) {
                return entity;
            }
        }
        return null;
    }

    public void destroy(Entity entity) {
        Box2DPhysicsComponent physics = Mappers.get(Box2DPhysicsComponent.class, entity);
        if (physics != null) {
            LateDestroyer.destroy(physics.getLinkedBody());
        }
        ControllerComponent controller = Mappers.get(ControllerComponent.class, entity);
        if (controller != null) {
            GdxGame.getInstance().getCurrentScene().getControlHub().getControllerHandler().removeController(controller.getController());
        }
        entityWorld.removeEntity(entity);
    }
}
