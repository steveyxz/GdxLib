package me.partlysunny.gdxlib.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lists {

    @SafeVarargs
    public static <T> List<T> of(T... items) {
        return Arrays.asList(items);
    }

    @SafeVarargs
    public static <T> List<T> implode(List<T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<T> list : lists) {
            result.addAll(list);
        }
        return result;
    }

}
