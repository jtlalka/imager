package net.tlalka.imager.utils

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class RxUtilsText {

    @Test
    fun rxCompSequential() {
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map { i -> i!! * 2 }
                .scan { _, _ -> 0 }
                .blockingSubscribe(::println)
    }

    @Test
    fun rxCompParallel() {
        Flowable.range(1, 10)
                .parallel()
                .runOn(Schedulers.computation())
                .map { i -> i!! * 2 }
                .sequential()
                .scan { _, _ -> 0 }
                .blockingSubscribe(::println)
    }
}
