package org.grida.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ApiResponse 는")
class SuccessResponseTest {

    @Test
    void String_만_인렵하는_경우_data_필드가_아닌_message_필드에_입력된다() {
        // given
        String message = "message";

        // when
        SuccessResponse successResponse = (SuccessResponse) ApiResponse.ok(message);

        // then
        assertThat(successResponse.getMessage()).isEqualTo(message);
        assertThat(successResponse.getData()).isNull();
    }

    @Test
    void 제네릭_T_만_인렵하는_경우_message_필드가_아닌_data_필드에_입력된다() {
        // given
        Object object = new Object();

        // when
        SuccessResponse successResponse = (SuccessResponse) ApiResponse.ok(object);

        // then
        assertThat(successResponse.getMessage()).isEqualTo("");
        assertThat(successResponse.getData()).isEqualTo(object);
    }
}