package org.grida.processor.imagegenerate;

public interface ImageGenerateProcessor {

    ImageGenerateResult proceed(String prompt, int n);
}
