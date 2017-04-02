package net.tlalka.imager.data

import java.io.File

class GeoImage(val file: File, val latitude: Double, val longitude: Double) {
    var distance: Double = 0.toDouble()
        private set
    var direction: Double = 0.toDouble()
        private set
    var cardinal: String = ""
        private set

    init {
        this.distance = 0.0
        this.cardinal = ""
    }

    fun setImageVector(distance: Double, direction: Double, cardinal: String) {
        this.distance = distance
        this.direction = direction
        this.cardinal = cardinal
    }
}
