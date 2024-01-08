package io.wwan13.storage.uploader.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import io.wwan13.storage.config.S3Properties;
import io.wwan13.storage.exception.CannotReadImageException;
import io.wwan13.storage.exception.WrongImageUrlException;
import io.wwan13.storage.filenamegenerator.FileNameGenerator;
import io.wwan13.storage.uploader.ImageType;
import io.wwan13.storage.uploader.ImageUploader;
import lombok.RequiredArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@RequiredArgsConstructor
public class S3ImageUploader implements ImageUploader {

    private static final String IMAGE_EXTENSION = "png";

    private final S3Properties properties;
    private final AmazonS3 amazonS3;
    private final FileNameGenerator fileNameGenerator;

    @Override
    public String upload(String imageUrl, ImageType imageType) {
        String fileName = fileNameGenerator.generate(IMAGE_EXTENSION);
        InputStream imageInputStream = toInputStreamFromImageUrl(imageUrl);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(IMAGE_EXTENSION);

        amazonS3.putObject(properties.getBucket(), fileName, imageInputStream, metadata);
        return imageType.getDirectory(fileName);
    }

    private InputStream toInputStreamFromImageUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, IMAGE_EXTENSION, outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (MalformedURLException e) {
            throw new WrongImageUrlException();
        } catch (IOException | IllegalArgumentException e) {
            throw new CannotReadImageException();
        }
    }
}
