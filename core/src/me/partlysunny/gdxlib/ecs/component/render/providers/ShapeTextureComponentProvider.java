package me.partlysunny.gdxlib.ecs.component.render.providers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.render.RendererComponent;
import me.partlysunny.gdxlib.ecs.component.render.ShapeTextureComponent;
import me.partlysunny.gdxlib.util.PolygonHelper;
import me.partlysunny.gdxlib.util.resource.ResourceManager;
import me.partlysunny.gdxlib.util.resource.TextureResource;

public class ShapeTextureComponentProvider implements RendererProvider {

    private final PolygonSprite polygonSprite;

    public ShapeTextureComponentProvider(PolygonSprite polygonSprite) {
        this.polygonSprite = polygonSprite;
    }

    public ShapeTextureComponentProvider(PolygonRegion polygonRegion) {
        this.polygonSprite = new PolygonSprite(polygonRegion);
    }

    public ShapeTextureComponentProvider(Color color, PolygonShape polygonShape) {
        float[] polygonVertices = PolygonHelper.getVerticesAsFloat(polygonShape);
        TextureResource colorResource = ResourceManager.getInstance().getColorResource(color);
        TextureRegion colorRegion = new TextureRegion(colorResource.getInternal());
        PolygonRegion createdRegion = new PolygonRegion(new TextureRegion(colorRegion), polygonVertices, null);
        this.polygonSprite = new PolygonSprite(createdRegion);
    }

    @Override
    public RendererComponent createRenderer(GameWorld world) {
        return new ShapeTextureComponent(polygonSprite);
    }
}
