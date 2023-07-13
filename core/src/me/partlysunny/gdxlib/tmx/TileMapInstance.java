package me.partlysunny.gdxlib.tmx;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.entity.EntityProvider;

public class TileMapInstance implements Disposable {

    private final TiledMap tileMap;
    private final TiledMapRenderer renderer;
    private final Vector2 scale = new Vector2(1f, 1f);
    private final Vector2 position = new Vector2(0f, 0f);

    public TileMapInstance(TiledMap tileMap, TiledMapRenderer renderer) {
        this.tileMap = tileMap;
        this.renderer = renderer;
    }

    public TileMapInstance(TiledMap tileMap, TiledMapRenderer renderer, Vector2 scale, Vector2 position) {
        this.tileMap = tileMap;
        this.renderer = renderer;
        this.scale.set(scale);
        this.position.set(position);
    }

    public void render() {
        renderer.render();
    }

    public void setView(OrthographicCamera camera) {
        Matrix4 matrix = camera.combined.cpy();
        matrix.scale(scale.x, scale.y, 1f);
        matrix.translate(position.x, position.y, 0f);
        renderer.setView(matrix, position.x, position.y, camera.viewportWidth * scale.x, camera.viewportHeight * scale.y);
    }

    public void setScale(Vector2 scale) {
        this.scale.set(scale);
    }

    public void setPosition(Vector2 position) {
        this.position.set(position);
    }

    @Override
    public void dispose() {
        tileMap.dispose();
    }

    public void spawnObjects(GameWorld world) {
        tileMap.getLayers().forEach(layer -> {
            layer.getObjects().forEach(object -> {
                String type = object.getProperties().get("type", String.class);
                TileObjectEntityTransformer transformer = MapObjects.transform(type);
                if (transformer != null) {
                    EntityProvider provider = transformer.fromTileObject(world, object);
                    //Spawn the entity
                    provider.createEntity(new Vector2(object.getProperties().get("x", Float.class), object.getProperties().get("y", Float.class)));
                }
            });
        });
    }
}
