package io.wwan13.storage.uploader.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import io.wwan13.storage.exception.CannotReadImageException;
import io.wwan13.storage.exception.WrongImageUrlException;
import io.wwan13.storage.filenamegenerator.FileNameGenerator;
import io.wwan13.storage.uploader.ImageType;
import io.wwan13.storage.uploader.ImageUploader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class S3ImageUploader implements ImageUploader {

    private static final String IMAGE_EXTENSION = "png";

    private final String s3Bucket;
    private final AmazonS3 amazonS3;
    private final FileNameGenerator fileNameGenerator;

    public S3ImageUploader(@Value("${storage.aws.bucket}") String s3Bucket,
                           AmazonS3 amazonS3,
                           FileNameGenerator fileNameGenerator) {
        this.s3Bucket = s3Bucket;
        this.amazonS3 = amazonS3;
        this.fileNameGenerator = fileNameGenerator;
    }

    @Override
    public String upload(String imageUrl, ImageType imageType) {
        String fileName = fileNameGenerator.generate(IMAGE_EXTENSION);
        InputStream imageInputStream = toInputStreamFromImageUrl(imageUrl);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(IMAGE_EXTENSION);

        amazonS3.putObject(s3Bucket, fileName, imageInputStream, metadata);
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
