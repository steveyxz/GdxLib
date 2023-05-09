package me.partlysunny.gdxlib.util;

import jdk.jfr.internal.LogLevel;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Debug {

    private static final Logger LOGGER = Logger.getLogger("GdxLib");

    public static void setLogLevel(Level level) {
        LOGGER.setLevel(level);
    }

    public static void log(String message) {
        LOGGER.info(message);
    }

    public static void log(String message, Object... args) {
        LOGGER.info(String.format(message, args));
    }

    public static void logError(String message) {
        LOGGER.severe(message);
    }

    public static void logError(String message, Object... args) {
        LOGGER.severe(String.format(message, args));
    }

    public static void logWarning(String message) {
        LOGGER.warning(message);
    }

    public static void logWarning(String message, Object... args) {
        LOGGER.warning(String.format(message, args));
    }

    public static void logDebug(String message) {
        LOGGER.fine(message);
    }

    public static void logDebug(String message, Object... args) {
        LOGGER.fine(String.format(message, args));
    }

    public static void logTrace(String message) {
        LOGGER.finest(message);
    }

    public static void logTrace(String message, Object... args) {
        LOGGER.finest(String.format(message, args));
    }

    public static void logException(Exception e) {
        LOGGER.severe(e.getMessage());
    }

    public static void logException(Exception e, String message) {
        LOGGER.severe(message);
        LOGGER.severe(e.getMessage());
    }

    public static void logException(Exception e, String message, Object... args) {
        LOGGER.severe(String.format(message, args));
        LOGGER.severe(e.getMessage());
    }

}
