package org.grida.image;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageType {

    DIARY("image/diary", "png"),
    PROFILE("image/profile", "png");

    private final String directory;
    private final String extension;
}
