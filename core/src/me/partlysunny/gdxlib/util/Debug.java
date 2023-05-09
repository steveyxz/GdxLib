package me.partlysunny.gdxlib.util;


import com.badlogic.gdx.utils.Logger;

public class Debug {

    private static final Logger LOGGER = new Logger("GdxLib", Logger.DEBUG);

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

}
