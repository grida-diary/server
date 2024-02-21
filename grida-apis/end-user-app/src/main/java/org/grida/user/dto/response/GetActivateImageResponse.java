package org.grida.user.dto.response;

import org.grida.domain.user.UserAppearance;

public record GetActivateImageResponse(
        int age,
        String gender,
        String hairStyle,
        String hairLength,
        String hairColor,
        String glasses,
        String imagePath,
        boolean canRefresh
) {

    public static GetActivateImageResponse of(
            UserAppearance appearance,
            String imageUrl,
            boolean canRefresh
    ) {
        return new GetActivateImageResponse(
                appearance.age(),
                appearance.gender().getValue(),
                appearance.getHairStyle(),
                appearance.getHairLength(),
                appearance.getHairColor(),
                appearance.glasses(),
                imageUrl,
                canRefresh
        );
    }
}
