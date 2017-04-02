package net.tlalka.imager.core

import io.reactivex.Observable
import java.io.File
import javax.activation.MimetypesFileTypeMap

class FileReader {

    fun getImages(directory: String): Observable<File> {
        return Observable
                .fromArray(*getFileListFromDirectory(directory))
                .filter { this.isImageFile(it) }
    }

    private fun getFileListFromDirectory(path: String): Array<File> {
        return File(path).listFiles() ?: arrayOf<File>()
    }

    private fun isImageFile(file: File): Boolean {
        return MimetypesFileTypeMap().getContentType(file).startsWith("image", true)
    }
}
