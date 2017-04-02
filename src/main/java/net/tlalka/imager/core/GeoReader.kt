package net.tlalka.imager.core

import com.drew.imaging.ImageMetadataReader
import com.drew.imaging.ImageProcessingException
import com.drew.metadata.exif.GpsDirectory
import io.reactivex.Observable
import net.tlalka.imager.data.GeoImage
import java.io.File
import java.io.IOException

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

        return Observable.just(GeoImage(image, latitude, longitude))
    }
}
