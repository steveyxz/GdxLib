package me.partlysunny.gdxlib.ecs.component.render.providers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import me.partlysunny.gdxlib.ecs.GameWorld;
import me.partlysunny.gdxlib.ecs.component.render.RendererComponent;
import me.partlysunny.gdxlib.ecs.component.render.PolygonalTextureComponent;
import me.partlysunny.gdxlib.util.PolygonHelper;
import me.partlysunny.gdxlib.util.resource.ResourceManager;

public class PolygonalTextureComponentProvider implements RendererProvider {

    private static final EarClippingTriangulator TRIANGULATOR = new EarClippingTriangulator();
    private final PolygonSprite polygonSprite;

    public PolygonalTextureComponentProvider(PolygonSprite polygonSprite) {
        this.polygonSprite = polygonSprite;
    }

    public PolygonalTextureComponentProvider(PolygonRegion polygonRegion) {
        this.polygonSprite = new PolygonSprite(polygonRegion);
    }

    public PolygonalTextureComponentProvider(Color color, PolygonShape polygonShape) {
        float[] polygonVertices = PolygonHelper.getVerticesAsFloat(polygonShape);
        Texture texture = ResourceManager.getInstance().getColorTexture(color);
        TextureRegion colorRegion = new TextureRegion(texture);
        PolygonRegion createdRegion = new PolygonRegion(new TextureRegion(colorRegion), polygonVertices, TRIANGULATOR.computeTriangles(polygonVertices).toArray());
        this.polygonSprite = new PolygonSprite(createdRegion);
    }

    @Override
    public RendererComponent createRenderer(GameWorld world) {
        return new PolygonalTextureComponent(polygonSprite);
    }
}
