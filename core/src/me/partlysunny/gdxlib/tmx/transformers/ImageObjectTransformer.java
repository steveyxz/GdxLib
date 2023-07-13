package me.partlysunny.gdxlib.tmx.transformers;

import com.badlogic.gdx.maps.MapObject;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.entity.EntityProvider;
import me.partlysunny.gdxlib.ecs.entity.concrete.ImageEntityProvider;
import me.partlysunny.gdxlib.tmx.TileObjectEntityTransformer;

public class ImageObjectTransformer implements TileObjectEntityTransformer {
    @Override
    public EntityProvider fromTileObject(GameWorld world, MapObject tileObject) {
        return new ImageEntityProvider(world, tileObject.getProperties().get("texture", String.class));
    }
}
