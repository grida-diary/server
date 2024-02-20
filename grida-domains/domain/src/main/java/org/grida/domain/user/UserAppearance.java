package org.grida.domain.user;

import java.util.Objects;

public record UserAppearance(
        Integer age,
        Gender gender,
        String hairStyle,
        String glasses
) {

    public boolean isAllEmpty() {
        return Objects.isNull(age) && Objects.isNull(gender)
                && Objects.isNull(hairStyle) && Objects.isNull(glasses);
    }
}
