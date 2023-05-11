package me.partlysunny.gdxlib.ecs.component.physics.providers;

import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.physics.PhysicsComponent;

public interface PhysicsProvider {

    PhysicsComponent createPhysics(GameWorld world);

}
