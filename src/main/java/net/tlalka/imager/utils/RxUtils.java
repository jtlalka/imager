package net.tlalka.imager.utils;

import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;

public class RxUtils {

    private RxUtils() {
    }

    public static <T> BiFunction<T, T, T> comp(BiConsumer<T, T> func) {
        return (t1, t2) -> {
            func.accept(t1, t2);
            return t2;
        };
    }
}
