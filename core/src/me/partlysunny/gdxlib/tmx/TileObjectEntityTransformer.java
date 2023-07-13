package me.partlysunny.gdxlib.tmx;

import com.badlogic.gdx.maps.MapObject;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.entity.EntityProvider;

public interface TileObjectEntityTransformer {

    EntityProvider fromTileObject(GameWorld world, MapObject tileObject);

}
