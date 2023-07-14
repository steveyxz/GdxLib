package me.partlysunny.gdxlib.ecs.systems.render;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.render.VisibilityComponent;
import me.partlysunny.gdxlib.ecs.component.standard.ScaleComponent;
import me.partlysunny.gdxlib.ecs.component.standard.TransformComponent;
import org.jetbrains.annotations.Nullable;

public abstract class RendererSystem<T extends Component> extends IteratingSystem {

    protected final Batch batch;
    private final Class<T> targetedComponent;

    public RendererSystem(Class<T> targetedComponent, Batch batch) {
        super(Family.all(targetedComponent, TransformComponent.class).get());
        this.targetedComponent = targetedComponent;
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VisibilityComponent visible = Mappers.get(VisibilityComponent.class, entity);
        if (visible != null && !visible.isVisible()) {
            return;
        }
        render(entity, Mappers.get(TransformComponent.class, entity), Mappers.get(ScaleComponent.class, entity), Mappers.get(targetedComponent, entity), deltaTime);
    }

    protected abstract void render(Entity entity, TransformComponent position, @Nullable ScaleComponent scale, T renderComponent, float deltaTime);

}
