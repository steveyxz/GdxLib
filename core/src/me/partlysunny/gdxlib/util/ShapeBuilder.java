package me.partlysunny.gdxlib.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class ShapeBuilder {

    /**
     * Creates a circle shape
     *
     * @param pos    The position of the circle
     * @param radius The radius of the circle
     * @return The circle shape
     */
    public static CircleShape circle(Vector2 pos, float radius) {
        CircleShape circleShape = new CircleShape();
        circleShape.setPosition(pos);
        circleShape.setRadius(radius);
        return circleShape;
    }

    /**
     * Creates a radial rectangle polygon
     *
     * @param center   The center coordinate of the rectangle
     * @param halfDim  The half dimensions (half the width and half the height) of the rectangle
     * @param rotation The rotation of the rectangle
     * @return The radial rectangle shape as a PolygonShape
     */
    public static PolygonShape radialRect(Vector2 center, Vector2 halfDim, float rotation) {
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(halfDim.x, halfDim.y, center, rotation);
        return polygonShape;
    }

    /**
     * Creates a radial rectangle with rotation 0
     *
     * @param center  The center coordinate of the rectangle
     * @param halfDim The half dimensions of the rectangle
     * @return The radial rectangle shape with rotation 0.
     */
    public static PolygonShape radialRect(Vector2 center, Vector2 halfDim) {
        return radialRect(center, halfDim, 0);
    }

    /**
     * Create a rectangle with a top left corner coordinate
     *
     * @param pos  The top left corner position of the rectangle
     * @param dims The dimensions of the rectangle
     * @return The resulting rectangle shape
     */
    public static PolygonShape rect(Vector2 pos, Vector2 dims) {
        PolygonShape polygonShape = new PolygonShape();
        pos.add(dims.x / 2, dims.y / 2);
        polygonShape.setAsBox(pos.x, pos.y, dims.scl(0.5f), 0);
        return polygonShape;
    }

    /**
     * Creates a square shape
     *
     * @param pos  The top left corner position of the square
     * @param side The side length of the square
     * @return The resulting square shape.
     */
    public static PolygonShape square(Vector2 pos, float side) {
        return rect(pos, new Vector2(side, side));
    }

    /**
     * Creates a square with a radius
     *
     * @param pos    The center position of the square
     * @param radius The radius of the square
     * @return The resulting square shape.
     */
    public static PolygonShape radialSquare(Vector2 pos, float radius) {
        return radialRect(pos, new Vector2(radius, radius));
    }

    /**
     * Creates a regular polygon shape with n sides.
     *
     * @param pos    The center position of the polygon
     * @param sides  The number of sides of the polygon
     * @param radius The radius of the polygon (the distance from the center to each point)
     * @return The resulting polygon
     */
    public static PolygonShape nSidedPolygon(Vector2 pos, int sides, float radius) {
        if (sides < 3) throw new IllegalArgumentException("Polygon must have three or more sides");
        float angle = 360f / sides;
        Vector2[] vertices = new Vector2[sides];
        for (int i = 0; i < sides; i++) {
            float currentAngle = angle * i;
            double angleInRadians = FastTrig.toRadians(currentAngle);
            Vector2 newPosition = new Vector2((float) FastTrig.cos(angleInRadians), (float) FastTrig.sin(angleInRadians));
            newPosition.scl(radius);
            newPosition.add(pos);
            vertices[i] = newPosition;
        }
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        return shape;
    }

    public static PolygonShape fromVertices(Vector2[] vertices) {
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        return shape;
    }
}
