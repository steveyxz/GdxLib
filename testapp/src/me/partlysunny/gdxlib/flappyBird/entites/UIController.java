package me.partlysunny.gdxlib.flappyBird.entites;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.physics.providers.PhysicsProvider;
import me.partlysunny.gdxlib.ecs.component.render.providers.RendererProvider;
import me.partlysunny.gdxlib.ecs.entity.SimpleEntityProvider;
import me.partlysunny.gdxlib.flappyBird.components.UiComponent;

public class UIController extends SimpleEntityProvider {

    private final UiComponent uiComponent;

    public UIController(GameWorld world, UiComponent uiComponent) {
        super(world);
        this.uiComponent = uiComponent;
    }

    @Override
    protected RendererProvider getRendererProvider() {
        return null;
    }

    @Override
    protected PhysicsProvider getPhysicsProvider(Vector2 originPosition) {
        return null;
    }

    @Override
    protected void addExtraComponents(Entity e) {
        e.add(uiComponent);
    }
}
