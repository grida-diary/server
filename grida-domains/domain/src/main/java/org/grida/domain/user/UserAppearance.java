package org.grida.domain.user;

import java.util.Objects;

public record UserAppearance(
        Integer age,
        Gender gender,
        String hairStyle,
        String glasses
) {

    private static final String DELIMITER = " ";
    private static final int HAIR_STYLE_INDEX = 0;
    private static final int HAIR_LENGTH_INDEX = 1;
    private static final int HAIR_COLOR_INDEX = 2;

    public boolean isAllEmpty() {
        return Objects.isNull(age) && Objects.isNull(gender)
                && Objects.isNull(hairStyle) && Objects.isNull(glasses);
    }

    public String getHairStyle() {
        return hairStyle.split(DELIMITER)[HAIR_STYLE_INDEX];
    }

    public String getHairLength() {
        return hairStyle.split(DELIMITER)[HAIR_LENGTH_INDEX];
    }

    public String getHairColor() {
        return hairStyle.split(DELIMITER)[HAIR_COLOR_INDEX];
    }
}
