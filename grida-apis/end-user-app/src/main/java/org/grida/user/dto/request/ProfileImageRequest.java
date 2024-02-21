package org.grida.user.dto.request;

import org.grida.domain.user.Gender;
import org.grida.domain.user.UserAppearance;
import org.grida.profileimage.ProfileImageAppearance;

import static org.grida.validator.RequestValidator.isGreaterThan;
import static org.grida.validator.RequestValidator.required;

public record ProfileImageRequest(
        int age,
        String gender,
        String hairStyle,
        String hairLength,
        String hairColor,
        String glasses
) {

    public ProfileImageRequest {
        isGreaterThan("age", age, 1);
        required("gender", gender);
        required("hairStyle", hairStyle);
        required("hairLength", hairLength);
        required("hairColor", hairColor);
        required("glasses", glasses);
    }

    public UserAppearance toUserAppearance() {
        return new UserAppearance(
                age,
                Gender.of(gender),
                String.join(" ", hairStyle, hairLength, hairColor),
                glasses
        );
    }

    public ProfileImageAppearance toImageAppearance() {
        return new ProfileImageAppearance(
                age,
                gender,
                hairStyle,
                hairLength,
                hairColor,
                glasses
        );
    }
}
