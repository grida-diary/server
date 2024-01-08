package io.wwan13.imagegenerate.processor.openai.processor;

import io.wwan13.imagegenerate.config.OpenAiProperties;
import io.wwan13.imagegenerate.exception.InvalidNumberOfImagesException;
import io.wwan13.imagegenerate.processor.openai.client.image.OpenAiImageClient;
import io.wwan13.imagegenerate.processor.openai.client.image.dto.ImageGenerateRequestDto;
import io.wwan13.imagegenerate.processor.openai.client.image.dto.ImageGenerateResponseDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("OpenAiImageProcessor 는")
class OpenAiImageProcessorTest {

    static class MockOpenAiProperties extends OpenAiProperties {

        public MockOpenAiProperties() {
            super("secretKey",
                    "imageModel",
                    "chatModel",
                    "chatRole");
        }
    }

    @Test
    void 이미지_한_개를_생성할_수_있다() {
        // given
        class MockImageClient implements OpenAiImageClient {

            @Override
            public ImageGenerateResponseDto generateImage(ImageGenerateRequestDto imageGenerateRequestDto) {
                return new ImageGenerateResponseDto(List.of(new ImageGenerateResponseDto.ImageUrl("imageUrl")));
            }
        }

        int n = 1;
        OpenAiImageProcessor processor = new OpenAiImageProcessor(new MockOpenAiProperties(), new MockImageClient());

        // when
        String url = processor.proceed("prompt", n).getUrl();

        // then
        assertThat(url).isEqualTo("imageUrl");
    }

    @ParameterizedTest(name = "이미지 {0}개를 생성할 수 있다")
    @ValueSource(ints = {2, 3, 4, 5})
    void 이미지_여러_개를_생성할_수_있다(int n) {
        // given
        class MockImageClient implements OpenAiImageClient {

            @Override
            public ImageGenerateResponseDto generateImage(ImageGenerateRequestDto imageGenerateRequestDto) {
                List<ImageGenerateResponseDto.ImageUrl> urls = IntStream.range(0, n)
                        .mapToObj(n -> new ImageGenerateResponseDto.ImageUrl("imageUrl" + n))
                        .collect(Collectors.toList());
                return new ImageGenerateResponseDto(urls);
            }
        }

        OpenAiImageProcessor processor = new OpenAiImageProcessor(new MockOpenAiProperties(), new MockImageClient());

        // when
        List<String> urls = processor.proceed("prompt", n).getUrls();

        // then
        assertThat(urls.size()).isEqualTo(n);
        IntStream.range(0, n)
                .forEach(index -> assertThat(urls.get(index)).isEqualTo("imageUrl" + index));
    }

    @Nested
    @DisplayName("다음과 같은 경우에 예외가 발생한다")
    class ExceptionTest {

        @ParameterizedTest(name = "{0}개의 이미지를 생성하려는 경우")
        @ValueSource(ints = {0, -1, -2, -3})
        void 한_개_미만의_이미지를_생성하려는_경우(int n) {
            // given
            class MockImageClient implements OpenAiImageClient {

                @Override
                public ImageGenerateResponseDto generateImage(ImageGenerateRequestDto imageGenerateRequestDto) {
                    List<ImageGenerateResponseDto.ImageUrl> urls = IntStream.range(0, n)
                            .mapToObj(n -> new ImageGenerateResponseDto.ImageUrl("imageUrl" + n))
                            .collect(Collectors.toList());
                    return new ImageGenerateResponseDto(urls);
                }
            }

            OpenAiImageProcessor processor
                    = new OpenAiImageProcessor(new MockOpenAiProperties(), new MockImageClient());

            // when, then
            assertThatThrownBy(() -> processor.proceed("prompt", n))
                    .isExactlyInstanceOf(InvalidNumberOfImagesException.class);
        }
    }
}