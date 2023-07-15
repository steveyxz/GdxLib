package me.partlysunny.gdxlib.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import me.partlysunny.gdxlib.GdxGame;

import java.util.ArrayList;
import java.util.List;

public class BodyBuilder {

    private final BodyDef bodyDef = new BodyDef();
    private final List<FixtureDef> fixtures = new ArrayList<>();

    private BodyBuilder() {
    }

    private BodyBuilder(Vector2 originPosition) {
        bodyDef.position.set(originPosition);
    }

    public static BodyBuilder create(Vector2 originPosition) {
        return new BodyBuilder(originPosition).bodyType(BodyDef.BodyType.DynamicBody);
    }

    public static BodyBuilder createWithPixelPosition(Vector2 originPosition) {
        return create(Physics.toMeters(originPosition));
    }

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

    public BodyBuilder bodyType(BodyDef.BodyType bodyType) {
        bodyDef.type = bodyType;
        return this;
    }

    public BodyBuilder addShape(Shape shape) {
        return addShape(shape, 0.2f);
    }

    public BodyBuilder addShape(Shape shape, float density) {
        if (shape instanceof CircleShape) {
            CircleShape circleShape = (CircleShape) shape;
            circleShape.setPosition(circleShape.getPosition().add(circleShape.getRadius(), circleShape.getRadius()));
        }
        FixtureDef def = new FixtureDef();
        def.shape = shape;
        def.density = density;
        fixtures.add(def);
        return this;
    }

    public BodyBuilder addFixture(FixtureDef fixture) {
        fixtures.add(fixture);
        return this;
    }

    public Body build() {
        return GdxGame.getInstance().getCurrentScene().getGameWorld().createBody(bodyDef, fixtures.toArray(new FixtureDef[0]));
    }
}
