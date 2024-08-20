package org.grida

import com.amazonaws.AmazonClientException
import com.amazonaws.services.s3.AmazonS3
import org.grida.config.S3ClientProperties
import org.grida.error.FileUploadFail
import org.grida.error.GridaException
import org.springframework.stereotype.Component
import java.io.File

@Component
class S3Client(
    private val properties: S3ClientProperties,
    private val amazonS3: AmazonS3,
) {
    fun uploadFile(
        file: File,
        saveAs: FileMetaData,
    ) {
        try {
            amazonS3.putObject(properties.bucket, saveAs.path, file)
        } catch (e: AmazonClientException) {
            throw GridaException(FileUploadFail)
        }
    }

    fun getFileUrl(path: String): String {
        return "${properties.host}/$path"
    }
}
