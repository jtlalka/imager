package net.tlalka.imager.view;

import net.tlalka.imager.data.GeoImage;

import java.util.Arrays;
import java.util.List;

public class ReportFacade implements ReportApi {

    private final List<ReportApi> reports;

    public ReportFacade(List<ReportApi> reports) {
        this.reports = reports;
    }

    public ReportFacade(ReportApi... reports) {
        this(Arrays.asList(reports));
    }

    @Override
    public void header(String header) {
        reports.forEach(r -> r.header(header));
    }

    @Override
    public void write(GeoImage image) {
        reports.forEach(r -> r.write(image));
    }

    @Override
    public void save() {
        reports.forEach(ReportApi::save);
    }
}
