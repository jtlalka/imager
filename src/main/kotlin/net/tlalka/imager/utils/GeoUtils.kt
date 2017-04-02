package net.tlalka.imager.utils

object GeoUtils {

    val earthRadiusInKm = 6371.0

    fun distanceInKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLng = Math.toRadians(lon2 - lon1)

        val a = Math.pow(Math.sin(dLat / 2.0), 2.0) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.pow(Math.sin(dLng / 2.0), 2.0)

        val c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a))

        return earthRadiusInKm * c
    }

    fun distanceInM(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        return 1000.0 * distanceInKm(lat1, lon1, lat2, lon2)
    }

    fun bearingInRad(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val rLat1 = Math.toRadians(lat1)
        val rLat2 = Math.toRadians(lat2)
        val longDiff = Math.toRadians(lon2 - lon1)

        val y = Math.sin(longDiff) * Math.cos(rLat2)
        val x = Math.cos(rLat1) * Math.sin(rLat2) - Math.sin(rLat1) * Math.cos(rLat2) * Math.cos(longDiff)

        return Math.atan2(y, x)
    }

    fun bearingInDeg(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        return (Math.toDegrees(bearingInRad(lat1, lon1, lat2, lon2)) + 360) % 360
    }
}
