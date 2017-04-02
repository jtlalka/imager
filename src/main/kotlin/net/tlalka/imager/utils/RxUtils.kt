package net.tlalka.imager.utils

import io.reactivex.Observable

object RxUtils {

    fun <T> Observable<T>.comp(func: (T, T) -> Unit): Observable<T> {
        return this.scan {t1: T, t2: T -> func(t1, t2); t2 }
    }
}
