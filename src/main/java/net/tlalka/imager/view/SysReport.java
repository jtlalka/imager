package net.tlalka.imager.view;

import net.tlalka.imager.data.GeoImage;

public class SysReport implements ReportApi {

    private static final String HEADER = "\n\nDIRECTORY: %s\n";
    private static final String FORMAT = "\n%s\t\t\tV(dist): %8.2f\t\t V(cord): %6.0f  ( %s )";
    private static final String FOOTER = "\n\nDONE.\n";

    @Override
    public void header(String directory) {
        print(HEADER, directory);
    }

    @Override
    public void write(GeoImage image) {
        print(FORMAT, image.getFile().getName(), image.getDistance(), image.getDirection(), image.getCardinal());
    }

    @Override
    public void save() {
        print(FOOTER);
    }

    private void print(String message, Object... objects) {
        System.out.printf(message, objects);
    }
}
