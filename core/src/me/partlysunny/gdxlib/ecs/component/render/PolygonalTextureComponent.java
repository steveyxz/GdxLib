package me.partlysunny.gdxlib.ecs.component.render;

import com.badlogic.gdx.graphics.g2d.PolygonSprite;

public class PolygonalTextureComponent implements RendererComponent {

    private PolygonSprite polygonSprite;

    public PolygonalTextureComponent(PolygonSprite polygonSprite) {
        this.polygonSprite = polygonSprite;
    }

    public PolygonalTextureComponent() {
    }

    public PolygonSprite getPolygonSprite() {
        return polygonSprite;
    }
}
