package me.partlysunny.gdxlib.ecs.component.render;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

public class TiledMapRenderComponent implements RendererComponent {

    private TiledMap map;
    private TiledMapRenderer renderer;

    public TiledMapRenderComponent(TiledMap map, TiledMapRenderer renderer) {
        this.map = map;
        this.renderer = renderer;
    }

    public TiledMapRenderComponent() {}

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public TiledMapRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(TiledMapRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void reset() {
        map = null;
        renderer = null;
    }
}
