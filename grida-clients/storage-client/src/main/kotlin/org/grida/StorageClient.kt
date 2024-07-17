package org.grida

import com.amazonaws.AmazonClientException
import com.amazonaws.services.s3.AmazonS3
import org.grida.config.StorageClientProperties
import org.grida.exception.FileUploadFailException
import org.springframework.stereotype.Component
import java.io.File

@Component
class StorageClient(
    private val properties: StorageClientProperties,
    private val amazonS3: AmazonS3,
) {
    fun uploadFile(
        file: File,
        saveAs: FileMetaData,
    ) {
        try {
            amazonS3.putObject(properties.bucket, saveAs.path, file)
        } catch (e: AmazonClientException) {
            throw FileUploadFailException()
        }
    }

    fun getFileUrl(path: String): String {
        return "${properties.host}/$path"
    }
}
