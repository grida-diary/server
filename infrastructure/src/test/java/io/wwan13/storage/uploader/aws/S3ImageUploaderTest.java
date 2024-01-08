package io.wwan13.storage.uploader.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import io.wwan13.storage.config.S3Properties;
import io.wwan13.storage.exception.CannotReadImageException;
import io.wwan13.storage.exception.WrongImageUrlException;
import io.wwan13.storage.filenamegenerator.FileNameGenerator;
import org.junit.jupiter.api.*;

import java.io.InputStream;

import static io.wwan13.storage.uploader.ImageType.DIARY;
import static io.wwan13.storage.uploader.ImageType.PROFILE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("S3ImageUploader 는")
class S3ImageUploaderTest {

    static class MockS3Properties extends S3Properties {

        public MockS3Properties(String bucket) {
            super("accessKey", "secretKey", "region", bucket);
        }
    }

    static class MockAmazonS3 extends AmazonS3Client {

        @Override
        public PutObjectResult putObject(String bucketName, String key, InputStream input, ObjectMetadata metadata)
                throws SdkClientException, AmazonServiceException {
            return null;
        }
    }

    static class MockFileNameGenerator implements FileNameGenerator {

        @Override
        public String generate(String extension) {
            return "fileName.png";
        }
    }

    @Test
    void 이미지_url_로부터_이미지를_읽어와_s3에_저장한_뒤_파일_경로를_반환한다() {
        // given
        String bucket = "bucketName";
        AmazonS3 amazonS3 = new MockAmazonS3();
        FileNameGenerator fileNameGenerator = new MockFileNameGenerator();

        S3ImageUploader imageUploader = new S3ImageUploader(new MockS3Properties(bucket), amazonS3, fileNameGenerator);

        String imageUrl = "https://avatars.githubusercontent.com/u/64270501?v=4";

        // when
        String fileDirectory = imageUploader.upload(imageUrl, PROFILE);

        // then
        assertThat(fileDirectory).isEqualTo("/profile/fileName.png");
    }

    @Nested
    @DisplayName("다음과 같은 경우에 예외가 발생한다")
    class ExceptionTest {

        @Test
        void 이미지_url_의_형식이_올바르지_않은_경우() {
            // given
            String bucket = "bucketName";
            AmazonS3 amazonS3 = new MockAmazonS3();
            FileNameGenerator fileNameGenerator = new MockFileNameGenerator();

            S3ImageUploader imageUploader = new S3ImageUploader(new MockS3Properties(bucket), amazonS3, fileNameGenerator);

            String imageUrl = "wrong.url/";

            // when, then
            assertThatThrownBy(() -> imageUploader.upload(imageUrl, DIARY))
                    .isExactlyInstanceOf(WrongImageUrlException.class);
        }

        @Test
        void url_이_이미지를_담고_있지_않는_경우() {
            // given
            String bucket = "bucketName";
            AmazonS3 amazonS3 = new MockAmazonS3();
            FileNameGenerator fileNameGenerator = new MockFileNameGenerator();

            S3ImageUploader imageUploader = new S3ImageUploader(new MockS3Properties(bucket), amazonS3, fileNameGenerator);

            String imageUrl = "https://www.naver.com";

            // when, then
            assertThatThrownBy(() -> imageUploader.upload(imageUrl, DIARY))
                    .isExactlyInstanceOf(CannotReadImageException.class);
        }
    }
}