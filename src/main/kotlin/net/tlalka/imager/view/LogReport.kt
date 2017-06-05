package net.tlalka.imager.view

import net.tlalka.imager.data.GeoImage

class LogReport : ReportApi {

    companion object {
        val HEADER = "\n\nDIRECTORY: %s\n"
        val FORMAT = "\n%s\t\t\t Dist: %8.2f\t\t Cord: %6.0f  ( %s )"
        val FOOTER = "\n\nDONE.\n"
    }

    override fun header(header: String) {
        print(HEADER, header)
    }

    override fun write(image: GeoImage) {
        print(FORMAT, image.file.name, image.distance, image.direction, image.cardinal)
    }

    override fun footer() {
        print(FOOTER)
    }

    private fun print(message: String, vararg objects: Any) {
        System.out.printf(message, *objects)
    }
}
