package me.partlysunny.gdxlib.tmx;

import com.badlogic.gdx.graphics.OrthographicCamera;

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

    public static void spawnObjects() {
        tileMaps.forEach(TileMapInstance::spawnObjects);
    }

    public static void setView(OrthographicCamera camera) {
        tileMaps.forEach(tileMap -> tileMap.setView(camera));
    }

    public static void dispose() {
        tileMaps.forEach(TileMapInstance::dispose);
    }

}
