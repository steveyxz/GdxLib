package me.partlysunny.gdxlib;

import com.badlogic.gdx.math.Vector2;
import me.partlysunny.gdxlib.util.resource.ResourceManager;
import me.partlysunny.gdxlib.util.resource.TextureResource;

public class MainGame extends GdxGame {
    @Override
    protected Vector2 getPhysicsGravity() {
        return new Vector2(0, -9.8f);
    }

    @Override
    protected void loadResources() {
        ResourceManager.getInstance().add("logo", new TextureResource("badlogic.jpg"));
    }

    @Override
    protected void update(float delta) {

    }

    @Override
    protected void createOriginalEntities() {

    }
}
