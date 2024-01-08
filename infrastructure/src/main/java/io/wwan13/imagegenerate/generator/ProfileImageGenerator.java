package io.wwan13.imagegenerate.generator;

import io.wwan13.imagegenerate.processor.image.ImageProcessResult;
import io.wwan13.imagegenerate.processor.image.ImageProcessor;
import io.wwan13.imagegenerate.prompt.profileimagegenerate.ProfileImageGenerateKeywords;
import io.wwan13.imagegenerate.prompt.profileimagegenerate.ProfileImageGeneratePrompt;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProfileImageGenerator {

    private static final int IMAGE_AMOUNT = 1;

    private final ProfileImageGeneratePrompt profileImageGeneratePrompt;
    private final ImageProcessor imageProcessor;

    public String generate(String gender, int age) {
        ProfileImageGenerateKeywords keywords = ProfileImageGenerateKeywords.builder()
                .gender(gender)
                .age(age)
                .build();

        ImageProcessResult result = imageProcessor
                .proceed(profileImageGeneratePrompt.create(keywords), IMAGE_AMOUNT);

        return result.getUrl();
    }
}
