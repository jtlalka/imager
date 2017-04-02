package net.tlalka.imager.view

import net.tlalka.imager.data.GeoImage

class ReportFacade(private val reports: List<ReportApi>) : ReportApi {

    constructor(vararg reports: ReportApi) : this(reports.asList())

    override fun header(header: String) {
        reports.forEach { it.header(header) }
    }

    override fun write(image: GeoImage) {
        reports.forEach { it.write(image) }
    }

    override fun save() {
        reports.forEach { it.save() }
    }
}
