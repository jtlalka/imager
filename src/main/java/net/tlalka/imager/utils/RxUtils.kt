package net.tlalka.imager.utils

import io.reactivex.functions.BiConsumer
import io.reactivex.functions.BiFunction

object RxUtils {

    fun <T> comp(func: BiConsumer<T, T>): BiFunction<T, T, T> {
        return BiFunction { t1, t2 ->
            func.accept(t1, t2)
            t2
        }
    }
}
