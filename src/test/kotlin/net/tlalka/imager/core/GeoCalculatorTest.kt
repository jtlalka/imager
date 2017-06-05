package net.tlalka.imager.core

import net.tlalka.imager.data.GeoImage
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import java.io.File

object GeoCalculatorTest: Spek({

    val DISTANCE_DELTA = 0.00001

    given("two images with different location") {

        val image1 = GeoImage(File("."), 52.2297, 21.0122, 1)
        val image2 = GeoImage(File("."), 52.2296, 21.0122, 1)

        on("geo calculation") {
            GeoCalculator().calculate(image1, image2)

            it("not update image1 values") {
                assertEquals(0.0, image1.distance, DISTANCE_DELTA)
                assertEquals(0.0, image1.direction, DISTANCE_DELTA)
                assertEquals(" ", image1.cardinal)
            }

            it("update image2 values") {
                assertNotEquals(0.0, image2.distance, DISTANCE_DELTA)
                assertNotEquals(0.0, image2.direction, DISTANCE_DELTA)
                assertNotEquals(" ", image2.cardinal)
            }
        }
    }

    given("two images with the same location") {

        val image1 = GeoImage(File("."), 55.0, 22.1, 1)
        val image2 = GeoImage(File("."), 55.0, 22.1, 1)

        on("geo calculation") {
            GeoCalculator().calculate(image1, image2)

            it("not update image1 values") {
                assertEquals(0.0, image1.distance, DISTANCE_DELTA)
                assertEquals(0.0, image1.direction, DISTANCE_DELTA)
                assertEquals(" ", image1.cardinal)
            }

            it("update image2 values") {
                assertEquals(0.0, image2.distance, DISTANCE_DELTA)
                assertEquals(0.0, image2.direction, DISTANCE_DELTA)
                assertEquals("N", image2.cardinal)
            }
        }
    }
})