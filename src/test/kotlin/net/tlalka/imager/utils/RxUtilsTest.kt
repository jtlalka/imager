package net.tlalka.imager.utils

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import net.tlalka.imager.utils.RxUtils.comp
import org.junit.Test

class RxUtilsTest {

    @Test
    fun rxCompSequential() {
        Observable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map { i -> i * 2 }
                .comp {i1, i2 -> println(i1.toString() + " ->> " + i2) }
                .blockingSubscribe(::println)
    }

    @Test
    fun rxCompParallel() {
        Flowable.range(1, 10)
                .parallel()
                .runOn(Schedulers.computation())
                .map { i -> i * 2 }
                .sequential()
                .scan { _, _ -> 0 }
                .blockingSubscribe(::println)
    }
}
