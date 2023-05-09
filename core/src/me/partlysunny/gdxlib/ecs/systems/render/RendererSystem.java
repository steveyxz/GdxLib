package me.partlysunny.gdxlib.ecs.systems.render;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;

public abstract class RendererSystem<T extends Component> extends IteratingSystem {

    private final Class<T> targetedComponent;
    protected final Batch batch;

    public RendererSystem(Class<T> targetedComponent, Batch batch) {
        super(Family.all(targetedComponent, TransformComponent.class).get());
        this.targetedComponent = targetedComponent;
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        render(entity, Mappers.getComponentMapper(TransformComponent.class).get(entity).getPosition(), Mappers.getComponentMapper(targetedComponent).get(entity), deltaTime);
    }

    protected abstract void render(Entity entity, Vector2 position, T renderComponent, float deltaTime);

}
