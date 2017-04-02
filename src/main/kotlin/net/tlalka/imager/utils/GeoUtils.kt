package net.tlalka.imager.utils

import java.lang.Math.atan2
import java.lang.Math.cos
import java.lang.Math.pow
import java.lang.Math.sin
import java.lang.Math.toDegrees
import java.lang.Math.toRadians

object GeoUtils {

    val earthRadiusInKm = 6371.0

    fun distanceInKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val dLat = toRadians(lat2 - lat1)
        val dLng = toRadians(lon2 - lon1)

        val a = pow(sin(dLat / 2.0), 2.0) + cos(toRadians(lat1)) * cos(toRadians(lat2)) * pow(sin(dLng / 2.0), 2.0)
        val c = 2.0 * atan2(Math.sqrt(a), Math.sqrt(1.0 - a))

        return earthRadiusInKm * c
    }

    fun distanceInM(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        return 1000.0 * distanceInKm(lat1, lon1, lat2, lon2)
    }

    fun bearingInRad(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val rLat1 = toRadians(lat1)
        val rLat2 = toRadians(lat2)
        val longDiff = toRadians(lon2 - lon1)

        val y = sin(longDiff) * cos(rLat2)
        val x = cos(rLat1) * sin(rLat2) - sin(rLat1) * cos(rLat2) * cos(longDiff)

        return atan2(y, x)
    }

    fun bearingInDeg(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        return (toDegrees(bearingInRad(lat1, lon1, lat2, lon2)) + 360) % 360
    }
}
