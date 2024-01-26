package org.grida;

import org.grida.config.CdnProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("CdnClient 는")
class CdnClientTest {

    @Test
    void cdn_url_을_반환한다() {
        // given
        CdnProperties properties = new CdnProperties("https://domain.com");
        CdnClient cdnClient = new CdnClient(properties);
        String filePath = "/file/path/image.png";

        // when
        String result = cdnClient.getUrl(filePath);

        // then
        assertThat(result).isEqualTo("https://domain.com/file/path/image.png");
    }
}