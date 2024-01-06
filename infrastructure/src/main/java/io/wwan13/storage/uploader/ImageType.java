package io.wwan13.storage.uploader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageType {
    PROFILE("profile", "/profile/%s"),
    DIARY("diary", "/diary/%s");

    private final String name;
    private final String filePath;

    public String getDirectory(String fileName) {
        return String.format(filePath, fileName);
    }
}
