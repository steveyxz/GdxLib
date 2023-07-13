package me.partlysunny.gdxlib.tmx;

import java.util.HashMap;
import java.util.Map;

public class MapObjects {

    private static final Map<String, TileObjectEntityTransformer> transformers = new HashMap<>();

    public static void register(String type, TileObjectEntityTransformer transformer) {
        transformers.put(type, transformer);
    }

    public static TileObjectEntityTransformer transform(String type) {
        return transformers.get(type);
    }

}
