package me.partlysunny.gdxlib.tmx;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TmxLoader {

    private static final TmxMapLoader mapLoader = new TmxMapLoader();

    public static TileMapInstance load(String fileName) {
        TiledMap loaded = mapLoader.load(fileName);
        return new TileMapInstance(loaded, new OrthogonalTiledMapRenderer(loaded));
    }

}
