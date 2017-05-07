package net.tlalka.imager.core

import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.exif.GpsDirectory
import io.reactivex.Single
import net.tlalka.imager.data.GeoImage
import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.FileTime

class GeoReader {

    fun getGeoImage(image: File): Single<GeoImage> {
        return Single
                .just(image)
                .flatMap { getGpsDirectory(image) }
                .flatMap { getImageValue(image, it) }
    }

    private fun getGpsDirectory(image: File): Single<GpsDirectory> {
        return Single.create { emitter ->
            run {
                val metadata = ImageMetadataReader.readMetadata(image)
                val gpsDirectories = metadata.getDirectoriesOfType(GpsDirectory::class.java)

                emitter.onSuccess(gpsDirectories.first())
            }
        }
    }

    private fun getImageValue(image: File, gpsDirectory: GpsDirectory): Single<GeoImage> {
        return Single.create { emitter ->
            run {
                val latitude = gpsDirectory.geoLocation.latitude
                val longitude = gpsDirectory.geoLocation.longitude

                val attr: BasicFileAttributes = Files.readAttributes(image.toPath(), BasicFileAttributes::class.java)
                val time = attr.creationTime() ?: FileTime.fromMillis(0)

                emitter.onSuccess(GeoImage(image, latitude, longitude, time.toMillis()))
            }
        }
    }
}
