package org.grida.processor.imagegenerate;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class ImageGenerateResult {

    private static final int FIRST_INDEX = 0;

    private final List<String> imageUrls;

    public String getUrl() {
        return imageUrls.get(FIRST_INDEX);
    }

    public List<String> getUrls() {
        return Collections.unmodifiableList(imageUrls);
    }
}
