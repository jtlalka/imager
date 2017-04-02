package net.tlalka.imager.core;

import io.reactivex.Observable;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

public class FileReader {

    private static final File[] EMPTY_ARRAY = {};

    public Observable<File> getImages(String directory) {
        return Observable
                .fromArray(getFileListFromDirectory(directory))
                .filter(this::isImageFile);
    }

    private File[] getFileListFromDirectory(String path) {
        File[] files = new File(path).listFiles();
        return files != null ? files : EMPTY_ARRAY;
    }

    private boolean isImageFile(File file) {
        return new MimetypesFileTypeMap().getContentType(file).startsWith("image");
    }
}
