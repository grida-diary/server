package io.wwan13.imagegenerate.processor.image;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class ImageProcessResult {

    private final List<String> imageUrls;

    public String getUrl() {
        return imageUrls.get(0);
    }

    public List<String> getUrls() {
        return Collections.unmodifiableList(imageUrls);
    }
}
