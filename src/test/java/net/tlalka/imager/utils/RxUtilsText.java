package net.tlalka.imager.utils;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import static net.tlalka.imager.utils.RxUtils.comp;

public class RxUtilsText {

    @Test
    public void rxCompSequential() throws Exception {
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(i -> i * 2)
                .scan(comp((i1, i2) -> i2 = 0))
                .blockingSubscribe(System.out::println);
    }

    @Test
    public void rxCompParallel() {
        Flowable.range(1, 10)
                .parallel()
                .runOn(Schedulers.computation())
                .map(i -> i * 2)
                .sequential()
                .scan(comp((i1, i2) -> i2 = 0))
                .blockingSubscribe(System.out::println);
    }
}
