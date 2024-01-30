package org.grida.processor.imagegenerate;

import java.util.Collections;
import java.util.List;

public record ImageGenerateResult(
        List<String> imageUrls
) {

    private static final int FIRST_INDEX = 0;

    public String getUrl() {
        return imageUrls.get(FIRST_INDEX);
    }

    public List<String> getUrls() {
        return Collections.unmodifiableList(imageUrls);
    }
}
