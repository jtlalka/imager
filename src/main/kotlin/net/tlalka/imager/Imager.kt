package net.tlalka.imager

import io.reactivex.Observable
import net.tlalka.imager.core.FileReader
import net.tlalka.imager.core.GeoCalculator
import net.tlalka.imager.core.GeoReader
import net.tlalka.imager.utils.RxUtils.comp
import net.tlalka.imager.view.CsvReport
import net.tlalka.imager.view.LogReport
import net.tlalka.imager.view.ReportFacade

object Imager {

    @JvmStatic fun main(args: Array<String>) {

        val fileReader = FileReader()
        val geoReader = GeoReader()
        val geoCalculator = GeoCalculator()
        val report = ReportFacade(LogReport(), CsvReport())

        Observable
                .fromArray(*args)
                .doOnNext(report::header)
                .flatMap(fileReader::getImages)
                .flatMap {
                    geoReader.getGeoImage(it)
                        .toObservable()
                        .doOnError(this::logger)
                        .onErrorResumeNext(Observable.empty())
                }
                .comp(geoCalculator::calculate)
                .subscribe(report::write, this::logger, report::footer)
    }

    private fun logger(throwable: Throwable) {
        println("\nProgram error: " + throwable)
    }
}
