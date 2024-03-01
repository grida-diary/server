package org.grida.base;

import java.util.UUID;

public record ImageMetaData(
        String url,
        String directory,
        String name,
        String extension
) {

    private static final String IMAGE_PATH_FORMAT = "%s/%s.%s";

    public static ImageMetaData withRandomUuidFilename(String url, ImageType imageType) {
        return new ImageMetaData(
                url,
                imageType.getDirectory(),
                UUID.randomUUID().toString(),
                imageType.getExtension()
        );
    }

    public String toImagePath() {
        return String.format(IMAGE_PATH_FORMAT, directory, name, extension);
    }
}
