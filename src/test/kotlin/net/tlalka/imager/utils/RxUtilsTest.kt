package net.tlalka.imager.utils

import io.reactivex.Observable
import io.reactivex.Single
import net.tlalka.imager.utils.RxUtils.comp
import net.tlalka.imager.utils.RxUtils.flatSingle
import net.tlalka.imager.utils.RxUtils.flatSuccess
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object RxUtilsTest : Spek({

    given("a observable stream of 6 integer") {

        val intObservable = Observable
                .range(0, 6)

        on("comp options") {
            val tester = intObservable
                    .comp { i1, i2 -> i2 + i1 }
                    .test()

            it("result same stream of data") {
                tester.assertResult(0, 1, 2, 3, 4, 5)
            }
        }

        on("flatSingle options") {
            val tester = intObservable
                    .flatSingle { it -> Single.just(it * 2) }
                    .test()

            it("result data from single action") {
                tester.assertResult(0, 2, 4, 6, 8, 10)
            }
        }

        on("flatSuccess options") {
            val tester = intObservable
                    .flatSuccess {
                        it -> Observable
                            .just(it)
                            .map { it -> 100 / it }
                    }
                    .test()

            it("result only correct data") {
                tester.assertResult(100, 50, 33, 25, 20)
            }
        }
    }
})
