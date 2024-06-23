package org.grida

import java.security.cert.Extension

data class FileMetaData(
    val path: String,
) {
    val extension: String
        get() = path.split(EXTENSION_DELIMITER)[EXTENSION_INDEX]

    companion object {
        private const val EXTENSION_DELIMITER = "."
        private const val EXTENSION_INDEX = 1

        fun of(
            directory: String,
            filename: String,
            extension: Extension,
        ): FileMetaData = FileMetaData("$directory/$filename.$extension")
    }
}
