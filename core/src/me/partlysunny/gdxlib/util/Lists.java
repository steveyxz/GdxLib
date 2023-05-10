package me.partlysunny.gdxlib.util;

import java.util.Arrays;
import java.util.List;

public class Lists {

    @SafeVarargs
    public static <T> List<T> of(T... items) {
        return Arrays.asList(items);
    }

}
