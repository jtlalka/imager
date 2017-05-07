package net.tlalka.imager.utils

import io.reactivex.Observable
import io.reactivex.Single

object RxUtils {

    fun <T> Observable<T>.comp(func: (T, T) -> Unit): Observable<T> {
        return this.scan { t1: T, t2: T -> func(t1, t2); t2 }
    }

    fun <T, R> Observable<T>.flatSingle(single: (T) -> Single<R>): Observable<R> {
        return this.flatMap { t1 : T -> single(t1).toObservable() }
    }

    fun <T, R> Observable<T>.flatSuccess(func: (T) -> Observable<R>): Observable<R> {
        return this.flatMap { t1 : T -> func(t1).onErrorResumeNext(Observable.empty()) }
    }
}
