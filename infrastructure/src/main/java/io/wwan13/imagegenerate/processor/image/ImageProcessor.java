package io.wwan13.imagegenerate.processor.image;

public interface ImageProcessor {

    ImageProcessResult proceed(String prompt, int n);
}
