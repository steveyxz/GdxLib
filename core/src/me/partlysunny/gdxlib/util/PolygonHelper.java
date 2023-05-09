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

}
