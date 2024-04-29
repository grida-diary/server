package org.grida.processor;

import org.grida.client.image.OpenAiImageClient;
import org.grida.client.image.dto.ImageGenerateRequest;
import org.grida.client.image.dto.ImageResponse;
import org.grida.config.OpenAiProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("OpenAiAImageGenerateProcessor 는")
class OpenAiImageGenerateProcessorTest {

    static class StubProperties extends OpenAiProperties {
        public StubProperties() {
            super("", "", "", "");
        }
    }

    static class StubImageClient implements OpenAiImageClient {

        @Override
        public ImageResponse generateImage(ImageGenerateRequest imageGenerateRequestDto) {
            List<ImageResponse.ImageUrl> imageUrls = IntStream.range(0, imageGenerateRequestDto.n())
                    .mapToObj(n -> new ImageResponse.ImageUrl("imageUrl" + n))
                    .toList();

            return new ImageResponse(imageUrls);
        }

        @Override
        public ImageResponse editImage(MultiValueMap<String, Object> imageEditRequest) {
            return null;
        }

    }

    @Test
    void 생성한_이미지가_담긴_URL_을_한_개_제공한다() {
        // given
        OpenAiImageGenerateProcessor imageGenerateProcessor = new OpenAiImageGenerateProcessor(
                new StubProperties(),
                new StubImageClient()
        );

        int n = 1;

        // when
        String result = imageGenerateProcessor.proceed("", n).getUrl();

        // then
        assertThat(result).isEqualTo("imageUrl0");
    }

    @Test
    void 생성한_이미지가_담긴_URL_을_여러_개_제공한다() {
        // given
        OpenAiImageGenerateProcessor imageGenerateProcessor = new OpenAiImageGenerateProcessor(
                new StubProperties(),
                new StubImageClient()
        );

        int n = 5;

        // when
        List<String> result = imageGenerateProcessor.proceed("", n).getUrls();

        // then
        assertThat(result).contains("imageUrl0", "imageUrl1", "imageUrl2", "imageUrl3", "imageUrl4");
        assertThat(result.size()).isEqualTo(n);
    }
}