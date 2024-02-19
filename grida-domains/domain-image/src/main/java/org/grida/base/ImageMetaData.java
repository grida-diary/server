package org.grida.base;

import java.util.UUID;

public record ImageMetaData(
        String url,
        String directory,
        String name,
        String extension
) {

    public static ImageMetaData withRandomUuidFilename(String url, ImageType imageType) {
        return new ImageMetaData(
                url,
                imageType.getDirectory(),
                UUID.randomUUID().toString(),
                imageType.getExtension()
        );
    }
}
