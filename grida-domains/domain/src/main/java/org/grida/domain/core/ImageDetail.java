package org.grida.domain.core;

public record ImageDetail(
        String imagePath,
        boolean isActivate
) {

    public static ImageDetail activateImage(String imagePath) {
        return new ImageDetail(imagePath, true);
    }
}
