package io.wwan13.storage.filenamegenerator.uuid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("UuidFileNameGenerator 는")
class UuidFileNameGeneratorTest {

    @Test
    void uuid_로_이루어진_파일_이름을_생성할_수_있다() {
        // given
        UuidFileNameGenerator fileNameGenerator = new UuidFileNameGenerator();
        String extension = "png";

        // when
        String fileName = fileNameGenerator.generate(extension);

        // then
        assertThat(fileName).isNotBlank();
        assertThat(fileName).contains(extension);
    }
}