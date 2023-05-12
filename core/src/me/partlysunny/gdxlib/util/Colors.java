package me.partlysunny.gdxlib.util;

import com.badlogic.gdx.graphics.Color;

public class Colors {

    public static int toRGBAInt(Color color) {
        int intBits = color.toIntBits();
        int a = (intBits & 0xff000000) >>> 24;
        int b = (intBits & 0x00ff0000) >>> 16;
        int g = (intBits & 0x0000ff00) >>> 8;
        int r = (intBits & 0x000000ff);
        return (r << 24) | (g << 16) | (b << 8) | a;
    }

    public static int toABGRInt(Color color) {
        int intBits = color.toIntBits();
        int a = (intBits & 0xff000000) >>> 24;
        int b = (intBits & 0x00ff0000) >>> 16;
        int g = (intBits & 0x0000ff00) >>> 8;
        int r = (intBits & 0x000000ff);
        return (a << 24) | (b << 16) | (g << 8) | r;
    }

    public static int reverse(int i) {
        // HD, Figure 7-1
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
        i = (i << 24) | ((i & 0xff00) << 8) |
                ((i >>> 8) & 0xff00) | (i >>> 24);
        return i;
    }

}
