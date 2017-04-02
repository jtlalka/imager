package net.tlalka.imager

import io.reactivex.Observable
import net.tlalka.imager.core.FileReader
import net.tlalka.imager.core.GeoCalculator
import net.tlalka.imager.core.GeoReader
import net.tlalka.imager.view.ReportFacade
import net.tlalka.imager.view.SysReport

object Imager {

    @JvmStatic fun main(args: Array<String>) {

        val fileReader = FileReader()
        val geoReader = GeoReader()
        val report = ReportFacade(SysReport())

        Observable
                .fromArray(*args)
                .doOnNext { report.header(it) }
                .flatMap { fileReader.getImages(it) }
                .flatMap { geoReader.getGeoImage(it) }
                .scan { i1, i2 -> GeoCalculator.calculate(i1, i2) }

                .subscribe(
                        { report.write(it) },
                        { logger(it) },
                        { report.save() })
    }

    private fun logger(throwable: Throwable) {
        System.err.println(throwable.message)
    }
}
