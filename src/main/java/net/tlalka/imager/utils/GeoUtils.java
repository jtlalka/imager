package net.tlalka.imager.utils;

public class GeoUtils {

    public static final double earthRadiusInKm = 6371D;

    private GeoUtils() {
    }

    public static double distanceInKm(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lon2 - lon1);

        double a = Math.pow(Math.sin(dLat / 2D), 2D)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.pow(Math.sin(dLng / 2D), 2D);

        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));

        return earthRadiusInKm * c;
    }

    public static double distanceInM(double lat1, double lon1, double lat2, double lon2) {
        return 1000D * distanceInKm(lat1, lon1, lat2, lon2);
    }

    public static double bearingInRad(double lat1, double lon1, double lat2, double lon2) {
        double rLat1 = Math.toRadians(lat1);
        double rLat2 = Math.toRadians(lat2);
        double longDiff = Math.toRadians(lon2 - lon1);

        double y = Math.sin(longDiff) * Math.cos(rLat2);
        double x = Math.cos(rLat1) * Math.sin(rLat2)
                - Math.sin(rLat1) * Math.cos(rLat2) * Math.cos(longDiff);

        return Math.atan2(y, x);
    }

    public static double bearingInDeg(double lat1, double lon1, double lat2, double lon2) {
        return (Math.toDegrees(bearingInRad(lat1, lon1, lat2, lon2)) + 360) % 360;
    }
}
