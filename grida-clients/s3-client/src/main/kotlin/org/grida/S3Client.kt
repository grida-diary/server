package org.grida

import com.amazonaws.AmazonClientException
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import org.grida.config.S3ClientProperties
import org.grida.error.FileUploadFail
import org.grida.error.GridaException
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.net.URL
import java.util.UUID

@Component
class S3Client(
    private val properties: S3ClientProperties,
    private val amazonS3: AmazonS3,
) {

    fun uploadImage(
        file: ByteArray,
        extension: String
    ): String {
        val key = provideKey(extension)

        val metadata = ObjectMetadata()
        metadata.contentLength = file.size.toLong()

        return try {
            amazonS3.putObject(properties.bucket, key, ByteArrayInputStream(file), metadata)
            amazonS3.getUrl(properties.bucket, key).toString()
        } catch (e: AmazonClientException) {
            throw GridaException(FileUploadFail)
        }
    }

    fun uploadImage(
        imageUrl: String
    ): String {
        val extension = imageUrl.split("?").first().split(".").last()
        val key = provideKey(extension)

        return try {
            val imageStream = downloadImage(imageUrl)
            val metadata = ObjectMetadata()
            metadata.contentType = "image/$extension"

            val imageBytes = imageStream.readBytes()
            metadata.contentLength = imageBytes.size.toLong()

            amazonS3.putObject(properties.bucket, key, ByteArrayInputStream(imageBytes), metadata)
            key
        } catch (e: AmazonClientException) {
            throw GridaException(FileUploadFail)
        }
    }

    private fun downloadImage(imageUrl: String): InputStream {
        return URL(imageUrl).openStream()
    }

    fun provideKey(extension: String): String {
        return "images/${UUID.randomUUID()}.$extension"
    }
}
