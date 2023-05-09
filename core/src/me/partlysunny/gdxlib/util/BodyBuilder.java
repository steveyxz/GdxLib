package me.partlysunny.gdxlib.util;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import me.partlysunny.gdxlib.ecs.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class BodyBuilder {

    private static GameWorld WORLD;

    public static void init(GameWorld world) {
        WORLD = world;
    }

    private BodyBuilder() {
    }

    public static BodyBuilder create() {
        if (WORLD == null) {
            throw new IllegalStateException("BodyBuilder has not been initialized with a world!");
        }
        return new BodyBuilder();
    }

    private final BodyDef bodyDef = new BodyDef();
    private final List<FixtureDef> fixtures = new ArrayList<>();

    public BodyBuilder type(BodyDef.BodyType type) {
        bodyDef.type = type;
        return this;
    }

    public BodyBuilder position(float x, float y) {
        bodyDef.position.set(x, y);
        return this;
    }

    public BodyBuilder angle(float angle) {
        bodyDef.angle = angle;
        return this;
    }

    public BodyBuilder linearDamping(float linearDamping) {
        bodyDef.linearDamping = linearDamping;
        return this;
    }

    public BodyBuilder angularDamping(float angularDamping) {
        bodyDef.angularDamping = angularDamping;
        return this;
    }

    public BodyBuilder allowSleep(boolean allowSleep) {
        bodyDef.allowSleep = allowSleep;
        return this;
    }

    public BodyBuilder awake(boolean awake) {
        bodyDef.awake = awake;
        return this;
    }

    public BodyBuilder fixedRotation(boolean fixedRotation) {
        bodyDef.fixedRotation = fixedRotation;
        return this;
    }

    public BodyBuilder bullet(boolean bullet) {
        bodyDef.bullet = bullet;
        return this;
    }

    public BodyBuilder active(boolean active) {
        bodyDef.active = active;
        return this;
    }

    public BodyBuilder gravityScale(float gravityScale) {
        bodyDef.gravityScale = gravityScale;
        return this;
    }

    public BodyBuilder addShape(Shape shape) {
        FixtureDef def = new FixtureDef();
        def.shape = shape;
        fixtures.add(def);
        return this;
    }

    public BodyBuilder addFixture(FixtureDef fixture) {
        fixtures.add(fixture);
        return this;
    }

    public Body build() {
        return WORLD.createBody(bodyDef, fixtures.toArray(new FixtureDef[0]));
    }

}
