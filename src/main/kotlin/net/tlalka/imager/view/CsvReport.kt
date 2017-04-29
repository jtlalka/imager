package net.tlalka.imager.view

import net.tlalka.imager.data.GeoImage
import java.io.File
import java.io.PrintWriter

class CsvReport : ReportApi {

    companion object {
        val REPORT_NAME = "imager.csv"
        val REPORT_HEADER = arrayOf("image", "latitude", "longitude", "time", "distance", "bearing", "cardinal")
        val REPORT_ENCODE = "UTF-8"
        val CSV_SEPARATOR = ","

        fun String.toCsv() = "\"%s\"".format(this)
        fun Double.toCsv() = "\"%.6f\"".format(this)
        fun Long.toTime() = "\"%tT\"".format(this)

        fun PrintWriter.print(vararg objects: String) = this.println(objects.joinToString(CSV_SEPARATOR))
    }

    private var writer: PrintWriter? = null

    override fun header(header: String) {
        if (File(header).isDirectory) {
            writer = PrintWriter(File(header, REPORT_NAME), REPORT_ENCODE)
            writer?.print(*REPORT_HEADER)
        }
    }

    override fun write(image: GeoImage) {
        writer?.print(image.file.name.toCsv(), image.latitude.toCsv(), image.longitude.toCsv(), image.time.toTime(),
                image.distance.toCsv(), image.direction.toCsv(), image.cardinal)
    }

    override fun save() {
        writer?.flush()
        writer?.close()
    }
}
