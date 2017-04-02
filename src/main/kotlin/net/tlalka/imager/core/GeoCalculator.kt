package net.tlalka.imager.core

import net.tlalka.imager.data.GeoImage
import net.tlalka.imager.utils.GeoUtils

class GeoCalculator {

    fun calculate(i1: GeoImage, i2: GeoImage) {
        val distance = GeoUtils.distanceInM(i1.latitude, i1.longitude, i2.latitude, i2.longitude)
        val direction = GeoUtils.bearingInDeg(i1.latitude, i1.longitude, i2.latitude, i2.longitude)

        i2.setImageVector(distance, direction, CardinalType[direction].name)
    }

    private enum class CardinalType constructor(private val degrees: Int) {
        N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315), X(-1);

        companion object {

            private val STEP = 22.5

            operator fun get(degrees: Double): CardinalType {
                val value = if (degrees > NW.degrees + STEP) 0.0 else degrees

                return values().firstOrNull { Math.abs(value - it.degrees) < STEP } ?: CardinalType.X
            }
        }
    }
}
