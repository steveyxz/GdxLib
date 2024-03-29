package me.partlysunny.gdxlib.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.Mappers;
import me.partlysunny.gdxlib.ecs.component.standard.DestroyComponent;

public class DestroyerSystem extends IteratingSystem {

    private final GameWorld world;

    public DestroyerSystem(GameWorld world) {
        super(Family.all(DestroyComponent.class).get());
        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DestroyComponent component = Mappers.get(DestroyComponent.class, entity);
        if (component.getTimeToLive() <= 0) world.destroy(entity);
        else component.setTimeToLive(component.getTimeToLive() - deltaTime);
    }
}
