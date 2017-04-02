package net.tlalka.imager;

import io.reactivex.Observable;
import net.tlalka.imager.core.FileReader;
import net.tlalka.imager.core.GeoCalculator;
import net.tlalka.imager.core.GeoReader;
import net.tlalka.imager.view.SysReport;
import net.tlalka.imager.view.ReportFacade;

public class Imager {

    public static void main(String[] args) {

        FileReader fileReader = new FileReader();
        GeoReader geoReader = new GeoReader();
        ReportFacade report = new ReportFacade(new SysReport());

        Observable
                .fromArray(args)
                .doOnNext(report::header)
                .flatMap(fileReader::getImages)
                .flatMap(geoReader::getGeoImage)
                .scan(GeoCalculator::calculate)

                .subscribe(report::write, Imager::logger, report::save);
    }

    private static void logger(Throwable throwable) {
        System.err.println(throwable.getMessage());
    }
}
