package me.partlysunny.gdxlib.util;

import java.util.Random;

public final class RandomUtils {

    private static final Random random = new Random();

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public static float nextFloat() {
        return random.nextFloat();
    }

    public static float nextFloat(float bound) {
        return random.nextFloat() * bound;
    }

    public static float nextFloat(float min, float max) {
        return min + random.nextFloat() * (max - min);
    }

    public static double nextDouble() {
        return random.nextDouble();
    }

    public static double nextDouble(double bound) {
        return random.nextDouble() * bound;
    }

    public static double nextDouble(double min, double max) {
        return min + random.nextDouble() * (max - min);
    }

    public static boolean nextBoolean() {
        return random.nextBoolean();
    }

    public static boolean nextBoolean(float chance) {
        return random.nextFloat() < chance;
    }

    public static boolean nextBoolean(double chance) {
        return random.nextDouble() < chance;
    }

    public static boolean nextBoolean(int chance) {
        return random.nextInt(100) < chance;
    }

    public static int nextInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be less than max");
        }
        if (min == max) {
            return min;
        }
        if (min < 0 && max < 0) {
            return -nextInt(-max, -min);
        }
        if (min < 0) {
            return nextInt(0, -min + max) + min;
        } else return random.nextInt(max - min) + min;
    }

}
