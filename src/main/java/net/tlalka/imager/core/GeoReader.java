package net.tlalka.imager.core;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import io.reactivex.Observable;
import net.tlalka.imager.data.GeoImage;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class GeoReader {

    public Observable<GeoImage> getGeoImage(File image) {
        return Observable
                .just(image)
                .flatMap(f -> getGpsDirectory(image))
                .flatMap(gps -> getImageValue(image, gps));
    }

    private Observable<GpsDirectory> getGpsDirectory(File image) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(image);
            Collection<GpsDirectory> gpsDirectories = metadata.getDirectoriesOfType(GpsDirectory.class);

            return Observable.fromIterable(gpsDirectories);

        } catch (ImageProcessingException | IOException e) {
            return Observable.empty();
        }
    }

    private Observable<GeoImage> getImageValue(File image, GpsDirectory gpsDirectory) {
        double latitude = gpsDirectory.getGeoLocation().getLatitude();
        double longitude = gpsDirectory.getGeoLocation().getLongitude();

        return Observable.just(new GeoImage(image, latitude, longitude));
    }
}
