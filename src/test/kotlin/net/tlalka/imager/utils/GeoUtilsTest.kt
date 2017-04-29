package net.tlalka.imager.utils

import net.tlalka.imager.data.GeoImage
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class GeoUtilsTest {

    val delta = 0.00001

    @Test
    fun validateDistanceForSameLocation() {

        // given
        val image1 = GeoImage(File("."), 55.0, 22.1, 1)
        val image2 = GeoImage(File("."), 55.0, 22.1, 1)

        // when
        val result = GeoUtils.distanceInKm(image1.latitude, image1.longitude, image2.latitude, image2.longitude)

        // then
        assertEquals(0.0, result, delta)
    }

    @Test
    fun validateDistanceForDifferentDirections() {

        // given
        val image1 = GeoImage(File("."), 52.2297, 21.0122, 1)
        val image2 = GeoImage(File("."), 35.6895, 139.6917, 1)

        // when
        val result = GeoUtils.distanceInKm(image1.latitude, image1.longitude, image2.latitude, image2.longitude)

        // then
        assertEquals(8578.56907, result, delta)
    }

    @Test
    fun validateDistanceForShortDirections() {

        // given
        val image1 = GeoImage(File("."), 52.2297, 21.0122, 1)
        val image2 = GeoImage(File("."), 52.2296, 21.0122, 1)

        // when
        val result = GeoUtils.distanceInM(image1.latitude, image1.longitude, image2.latitude, image2.longitude)

        // then
        assertEquals(11.11949, result, delta)
    }

    @Test
    fun validateBearingInDegrees() {

        // given
        val image1 = GeoImage(File("."), 52.2297, 21.0122, 1)
        val image2 = GeoImage(File("."), 52.2296, 21.0122, 1)

        // when
        val result = GeoUtils.bearingInDeg(image1.latitude, image1.longitude, image2.latitude, image2.longitude)

        // then
        assertEquals(180.0, result, delta)
    }

    @Test
    fun validateBearingInRadians() {

        // given
        val image1 = GeoImage(File("."), 52.2297, 21.0122, 1)
        val image2 = GeoImage(File("."), 52.2296, 21.0122, 1)

        // when
        val result = GeoUtils.bearingInRad(image1.latitude, image1.longitude, image2.latitude, image2.longitude)

        // then
        assertEquals(Math.PI, result, delta)
    }
}