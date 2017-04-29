package net.tlalka.imager.core

import com.drew.imaging.ImageMetadataReader
import com.drew.imaging.ImageProcessingException
import com.drew.metadata.exif.GpsDirectory
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import net.tlalka.imager.data.GeoImage
import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.FileTime

class GeoReader {

    fun getGeoImage(image: File): Observable<GeoImage> {
        return Single.zip(getGpsDirectory(image), getFileTime(image), BiFunction {
            i1 : GpsDirectory, i2 : FileTime -> getImageValue(i1, i2, image)
        }).toObservable()
    }

    private fun getGpsDirectory(image: File): Single<GpsDirectory> {
        return Single.create<GpsDirectory> {
            run {
                try {
                    val metadata = ImageMetadataReader.readMetadata(image)
                    val gpsDirectories = metadata.getDirectoriesOfType(GpsDirectory::class.java)

                    it.onSuccess(gpsDirectories.firstOrNull())

                    throw ImageProcessingException("AAAA")

                } catch (e: ImageProcessingException) {
                    it.onError(e)
                }
            }
        }
    }

    private fun getFileTime(image: File) : Single<FileTime> {
        return Single.create<FileTime> {
            run {
                val attr : BasicFileAttributes = Files.readAttributes(image.toPath(), BasicFileAttributes::class.java)
                val time : FileTime = attr.creationTime() ?: FileTime.fromMillis(0)

                it.onSuccess(time)
            }
        }
    }

    private fun getImageValue(gpsDirectory : GpsDirectory, fileTime: FileTime, image: File): GeoImage {
        val latitude = gpsDirectory.geoLocation.latitude
        val longitude = gpsDirectory.geoLocation.longitude
        val createTime = fileTime.toMillis()

        return GeoImage(image, latitude, longitude, createTime)
    }
}
