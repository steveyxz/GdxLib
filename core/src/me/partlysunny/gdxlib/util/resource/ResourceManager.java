package me.partlysunny.gdxlib.util.resource;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

    private static ResourceManager instance;

    private ResourceManager() {
    }

    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    private final Map<String, Resource<?>> resources = new HashMap<>();

    public <T> T get(String name, Class<T> type) {
        Resource<?> resource = resources.get(name);
        if (resource == null) {
            throw new RuntimeException("Resource not found: " + name);
        }
        Object internal = resource.getInternal();
        if (!type.isInstance(internal)) throw new RuntimeException("Resource " + name + " is not of type: " + type);
        return type.cast(internal);
    }

    public Texture getTexture(String name) {
        return get(name, Texture.class);
    }

    public Sound getSound(String name) {
        return get(name, Sound.class);
    }

    public void add(String name, Resource<?> resource) {
        resources.put(name, resource);
    }

    public void dispose() {
        for (Resource<?> resource : resources.values()) {
            resource.dispose();
        }
    }

}
