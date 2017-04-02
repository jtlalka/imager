package net.tlalka.imager.core;

import net.tlalka.imager.data.GeoImage;
import net.tlalka.imager.utils.GeoUtils;

public class GeoCalculator {

    public static GeoImage calculate(GeoImage i1, GeoImage i2) {

        double d = GeoUtils.distanceInM(i1.getLatitude(), i1.getLongitude(), i2.getLatitude(), i2.getLongitude());
        double e = GeoUtils.bearingInDeg(i1.getLatitude(), i1.getLongitude(), i2.getLatitude(), i2.getLongitude());

        i2.setImageVector(d, e, CardinalType.get(e).name());
        return i2;
    }

    private enum CardinalType {
        N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315), X(-1);

        private static final double STEP = 22.5D;
        private final int degrees;

        CardinalType(int degrees) {
            this.degrees = degrees;
        }

        public static CardinalType get(double degrees) {
            double value = degrees > NW.degrees + STEP ? 0 : degrees;

            for (CardinalType cardinalType : values()) {
                if (Math.abs(value - cardinalType.degrees) < STEP) {
                    return cardinalType;
                }
            }
            return CardinalType.X;
        }
    }
}
