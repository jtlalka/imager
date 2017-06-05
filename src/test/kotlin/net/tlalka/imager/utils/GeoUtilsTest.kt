package net.tlalka.imager.utils

import net.tlalka.imager.data.GeoImage
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.File

object GeoUtilsTest: Spek({

    val DISTANCE_DELTA = 0.00001

    given("two images with the same location") {

        val image1 = GeoImage(File("."), 55.0, 22.1, 1)
        val image2 = GeoImage(File("."), 55.0, 22.1, 1)

        on("calculate distance in kilometers") {
            val result = GeoUtils.distanceInKm(image1.latitude, image1.longitude, image2.latitude, image2.longitude)

            it("should return 0") {
                assertEquals(0.0, result, DISTANCE_DELTA)
            }
        }

        on("calculate distance in meters") {
            val result = GeoUtils.distanceInM(image1.latitude, image1.longitude, image2.latitude, image2.longitude)

            it("should return 0") {
                assertEquals(0.0, result, DISTANCE_DELTA)
            }
        }

        on("calculate bearing in degrees") {
            val result = GeoUtils.bearingInDeg(image1.latitude, image1.longitude, image2.latitude, image2.longitude)

            it("should return 0") {
                assertEquals(0.0, result, DISTANCE_DELTA)
            }
        }

        on("calculate bearing in radians") {
            val result = GeoUtils.bearingInRad(image1.latitude, image1.longitude, image2.latitude, image2.longitude)

            it("should return 0") {
                assertEquals(0.0, result, DISTANCE_DELTA)
            }
        }
    }

    given("two images with different location") {

        val image1 = GeoImage(File("."), 52.2297, 21.0122, 1)
        val image2 = GeoImage(File("."), 52.2296, 21.0122, 1)

        on("calculate distance in meters") {
            val result = GeoUtils.distanceInM(image1.latitude, image1.longitude, image2.latitude, image2.longitude)

            it("should return distance in meters") {
                assertEquals(11.11949, result, DISTANCE_DELTA)
            }
        }

        on("calculate distance in kilometers") {
            val result = GeoUtils.distanceInKm(image1.latitude, image1.longitude, image2.latitude, image2.longitude)

            it("should return distance in kilometers") {
                assertEquals(0.01111, result, DISTANCE_DELTA)
            }
        }

        on("calculate bearing in degrees") {
            val result = GeoUtils.bearingInDeg(image1.latitude, image1.longitude, image2.latitude, image2.longitude)

            it("should bearing value") {
                assertEquals(180.0, result, DISTANCE_DELTA)
            }
        }

        on("calculate bearing in radians") {
            val result = GeoUtils.bearingInRad(image1.latitude, image1.longitude, image2.latitude, image2.longitude)

            it("should bearing value") {
                assertEquals(Math.PI, result, DISTANCE_DELTA)
            }
        }
    }

    given("two images with large distance location") {

        val image1 = GeoImage(File("."), 52.2297, 21.0122, 1)
        val image2 = GeoImage(File("."), 35.6895, 139.6917, 1)

        on("calculate distance in kilometers") {
            val result = GeoUtils.distanceInKm(image1.latitude, image1.longitude, image2.latitude, image2.longitude)

            it("should return distance in kilometers") {
                assertEquals(8578.56907, result, DISTANCE_DELTA)
            }
        }
    }
})