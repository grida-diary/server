package org.grida;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grida.config.StorageProperties;
import org.grida.exception.StorageClientException;
import org.grida.model.FileData;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.grida.exception.StorageClientErrorCode.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class StorageClient {

    private final StorageProperties properties;
    private final AmazonS3 amazonS3;

    public void upload(String imageUrl, FileData fileData) {
        InputStream imageInputStream = toInputStreamFromImageUrl(imageUrl, fileData.getExtension());

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(fileData.getExtension());

        try {
            amazonS3.putObject(properties.getBucket(), fileData.getFilePath(), imageInputStream, metadata);
        } catch (AmazonClientException e) {
            log.error("Fail to upload {}", fileData.getFilePath());
            throw new StorageClientException(IMAGE_UPLOAD_FAIL);
        }
    }

    private InputStream toInputStreamFromImageUrl(String imageUrl, String extension) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, extension, outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (MalformedURLException e) {
            log.error("{} is invalid image URL", imageUrl);
            throw new StorageClientException(INVALID_IMAGE_URL);
        } catch (IOException | IllegalArgumentException e) {
            log.error("No readable image in URL {}", imageUrl);
            throw new StorageClientException(CANNOT_READ_IMAGE);
        }
    }
}
