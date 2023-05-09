package me.partlysunny.gdxlib.ecs.component.render.providers;

import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.render.RendererComponent;

public interface RendererProvider {

    RendererComponent createRenderer(GameWorld world);

}
