package net.tlalka.imager.view

import net.tlalka.imager.data.GeoImage

interface ReportApi {

    fun header(header: String)

    fun write(image: GeoImage)

    fun footer()
}
