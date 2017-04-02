package net.tlalka.imager

import io.reactivex.Observable
import net.tlalka.imager.core.FileReader
import net.tlalka.imager.core.GeoCalculator
import net.tlalka.imager.core.GeoReader
import net.tlalka.imager.utils.RxUtils.comp
import net.tlalka.imager.view.ReportFacade
import net.tlalka.imager.view.SysReport

object Imager {

    @JvmStatic fun main(args: Array<String>) {

        val fileReader = FileReader()
        val geoReader = GeoReader()
        val geoCalculator = GeoCalculator()
        val report = ReportFacade(SysReport())

        Observable
                .fromArray(*args)
                .doOnNext { report.header(it) }
                .flatMap { fileReader.getImages(it) }
                .flatMap { geoReader.getGeoImage(it) }
                .comp { i1, i2 -> geoCalculator.calculate(i1, i2) }

                .subscribe(
                        { report.write(it) },
                        { logger(it) },
                        { report.save() })
    }

    private fun logger(throwable: Throwable) {
        print(throwable.message)
    }
}
