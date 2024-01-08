package io.wwan13.storage.cdn;

import io.wwan13.storage.config.CdnProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("CdnProcessor 는")
class CdnProcessorTest {

    static class MockCdnProperties extends CdnProperties {

        public MockCdnProperties() {
            super("https://storage.domain.com");
        }
    }

    @Test
    void 이미지를_불러올_수_있는_cdn_url_을_제공한다() {
        // given
        CdnProcessor cdnProcessor = new CdnProcessor(new MockCdnProperties());
        String filePath = "/image/diary/fileName.png";

        // when
        String result = cdnProcessor.getUrl(filePath);

        // then
        assertThat(result).isEqualTo("https://storage.domain.com" + filePath);
    }
}