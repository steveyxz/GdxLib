package me.partlysunny.gdxlib.ecs.component.render;

import com.badlogic.gdx.graphics.g2d.PolygonSprite;

public class ShapeTextureComponent implements RendererComponent {

    private PolygonSprite polygonSprite;

    public ShapeTextureComponent(PolygonSprite polygonSprite) {
        this.polygonSprite = polygonSprite;
    }

    public ShapeTextureComponent() {
    }

    public PolygonSprite getPolygonSprite() {
        return polygonSprite;
    }
}
