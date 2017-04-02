package net.tlalka.imager.view;

import net.tlalka.imager.data.GeoImage;

public interface ReportApi {

    void header(String header);

    void write(GeoImage image);

    void save();
}
