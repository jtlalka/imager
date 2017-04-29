package net.tlalka.imager.core

import com.drew.imaging.ImageMetadataReader
import com.drew.imaging.ImageProcessingException
import com.drew.metadata.exif.GpsDirectory
import io.reactivex.Observable
import net.tlalka.imager.data.GeoImage
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.FileTime

class GeoReader {

    fun getGeoImage(image: File): Observable<GeoImage> {
        return Observable
                .just(image)
                .flatMap { getGpsDirectory(image) }
                .flatMap { getImageValue(image, it) }
    }

    private fun getGpsDirectory(image: File): Observable<GpsDirectory> {
        try {
            val metadata = ImageMetadataReader.readMetadata(image)
            val gpsDirectories = metadata.getDirectoriesOfType(GpsDirectory::class.java)

            return Observable.fromIterable(gpsDirectories)

        } catch (e: ImageProcessingException) {
            return Observable.empty()
        } catch (e: IOException) {
            return Observable.empty()
        }
    }

    private fun getImageValue(image: File, gpsDirectory: GpsDirectory): Observable<GeoImage> {
        val latitude = gpsDirectory.geoLocation.latitude
        val longitude = gpsDirectory.geoLocation.longitude

        val attr : BasicFileAttributes = Files.readAttributes(image.toPath(), BasicFileAttributes::class.java)
        val time = attr.creationTime() ?: FileTime.fromMillis(0)

        return Observable.just(GeoImage(image, latitude, longitude, time.toMillis()))
    }
}
