package me.partlysunny.gdxlib.util;


import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Logger;
import me.partlysunny.gdxlib.GdxGame;

public class Debug {

    private static final Logger LOGGER = new Logger("GdxLib", Logger.DEBUG);
    private static final Box2DDebugRenderer BOX_2D_DEBUG_RENDERER = new Box2DDebugRenderer();

    public static void setLogLevel(int level) {
        LOGGER.setLevel(level);
    }

    public static void logInfo(String message) {
        LOGGER.info(message);
    }

    public static void logInfo(String message, Object... args) {
        LOGGER.info(String.format(message, args));
    }

    public static void logError(String message) {
        LOGGER.error(message);
    }

    public static void logError(String message, Object... args) {
        LOGGER.error(String.format(message, args));
    }

    public static void logDebug(String message) {
        LOGGER.debug(message);
    }

    public static void logDebug(String message, Object... args) {
        LOGGER.debug(String.format(message, args));
    }

    public static void renderBox2DDebug() {
        if (LOGGER.getLevel() == Logger.DEBUG) {
            Matrix4 matrix = GdxGame.getInstance().getCamera().combined.cpy();
            matrix.setToTranslation(new Vector3(0, 0, 0));
            BOX_2D_DEBUG_RENDERER.render(GdxGame.getInstance().getGameWorld().getPhysicsWorld(), matrix);
        }
    }

}
