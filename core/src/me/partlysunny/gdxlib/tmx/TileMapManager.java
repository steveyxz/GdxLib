package me.partlysunny.gdxlib.tmx;

import com.badlogic.gdx.graphics.OrthographicCamera;
import me.partlysunny.gdxlib.ecs.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class TileMapManager {

    private static final List<TileMapInstance> tileMaps = new ArrayList<>();

    public static void add(TileMapInstance tileMap) {
        tileMaps.add(tileMap);
    }

    public static void render() {
        tileMaps.forEach(TileMapInstance::render);
    }

    /**
     * Spawns all objects in all tile maps
     * This will be automatically called by GdxGame.create()
     * @param world the game world to create the objects in
     */
    public static void spawnObjects(GameWorld world) {
        tileMaps.forEach(tileMap -> tileMap.spawnObjects(world));
    }

    public static void setView(OrthographicCamera camera) {
        tileMaps.forEach(tileMap -> tileMap.setView(camera));
    }

    public static void dispose() {
        tileMaps.forEach(TileMapInstance::dispose);
    }

}
