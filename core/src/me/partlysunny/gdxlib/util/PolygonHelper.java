package me.partlysunny.gdxlib.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public final class PolygonHelper {

    public static Vector2[] getVertices(PolygonShape shape) {
        int vertexCount = shape.getVertexCount();
        Vector2[] vertices = new Vector2[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            vertices[i] = new Vector2();
            shape.getVertex(i, vertices[i]);
        }
        return vertices;
    }

    public static float[] getVerticesAsFloat(PolygonShape shape) {
        int vertexCount = shape.getVertexCount();
        float[] vertices = new float[vertexCount * 2];
        for (int i = 0; i < vertexCount; i++) {
            Vector2 vertex = new Vector2();
            shape.getVertex(i, vertex);
            vertices[i * 2] = vertex.x;
            vertices[i * 2 + 1] = vertex.y;
        }
        return vertices;
    }

    public static Vector2 center(Vector2[] shape) {
        float x = 0;
        float y = 0;
        for (Vector2 vertex : shape) {
            x += vertex.x;
            y += vertex.y;
        }
        return new Vector2(x / shape.length, y / shape.length);
    }

    public static PolygonShape scale(PolygonShape shape, float scale) {
        Vector2[] vertices = getVertices(shape);
        if (vertices.length == 0) {
            return ShapeBuilder.fromVertices(vertices);
        }
        Vector2 fixed = center(vertices);
        for (Vector2 vertex : vertices) {
            vertex.x = fixed.x + (vertex.x - fixed.x) * scale;
            vertex.y = fixed.y + (vertex.y - fixed.y) * scale;
        }
        return ShapeBuilder.fromVertices(vertices);
    }

    public static float getWidth(PolygonShape shape) {
        Vector2[] vertices = getVertices(shape);
        float minX = vertices[0].x;
        float maxX = vertices[0].x;
        for (Vector2 vertex : vertices) {
            if (vertex.x < minX) {
                minX = vertex.x;
            }
            if (vertex.x > maxX) {
                maxX = vertex.x;
            }
        }
        return maxX - minX;
    }

    public static float getHeight(PolygonShape shape) {
        Vector2[] vertices = getVertices(shape);
        float minY = vertices[0].y;
        float maxY = vertices[0].y;
        for (Vector2 vertex : vertices) {
            if (vertex.y < minY) {
                minY = vertex.y;
            }
            if (vertex.y > maxY) {
                maxY = vertex.y;
            }
        }
        return maxY - minY;
    }

}
