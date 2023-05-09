package me.partlysunny.gdxlib.ecs.component.render;

import com.badlogic.gdx.graphics.g2d.PolygonSprite;

public class ShapeTextureComponent implements RendererComponent {

    private final PolygonSprite polygonSprite;

    public ShapeTextureComponent(PolygonSprite polygonSprite) {
        this.polygonSprite = polygonSprite;
    }

    public PolygonSprite getPolygonSprite() {
        return polygonSprite;
    }
}
