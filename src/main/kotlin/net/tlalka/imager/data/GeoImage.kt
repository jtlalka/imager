package net.tlalka.imager.data

import java.io.File

class GeoImage(val file: File, val latitude: Double, val longitude: Double, val time: Long) {

    var distance = 0.0
    var direction = 0.0
    var cardinal = " "

    fun setImageVector(distance: Double, direction: Double, cardinal: String) {
        this.distance = distance
        this.direction = direction
        this.cardinal = cardinal
    }
}
