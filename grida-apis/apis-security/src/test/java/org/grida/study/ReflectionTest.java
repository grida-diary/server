package org.grida.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("isAssignableFrom 는")
public class ReflectionTest {
    
    @Test
    void 기본_타입과_참조_타입을_구분한다() {
        // given
        Class<Long> referenceClass = Long.class;
      
        // when
        boolean result = long.class.isAssignableFrom(referenceClass);

        // then
        assertThat(result).isFalse();
    }
}
