package net.tlalka.imager.data;

import java.io.File;

public class GeoImage {

    private File file;
    private double latitude;
    private double longitude;
    private double distance;
    private double direction;
    private String cardinal;

    public GeoImage(File file, double latitude, double longitude) {
        this.file = file;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = 0D;
        this.cardinal = "";
    }

    public void setImageVector(double distance, double direction, String cardinal) {
        this.distance = distance;
        this.direction = direction;
        this.cardinal = cardinal;
    }

    public File getFile() {
        return file;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getDistance() {
        return distance;
    }

    public double getDirection() {
        return direction;
    }

    public String getCardinal() {
        return cardinal;
    }
}
