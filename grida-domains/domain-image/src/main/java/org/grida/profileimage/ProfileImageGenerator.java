package org.grida.profileimage;

import lombok.RequiredArgsConstructor;
import org.grida.base.ImageMetaData;
import org.grida.base.ImageType;
import org.grida.base.Keywords;
import org.grida.processor.imagegenerate.ImageGenerateProcessor;
import org.grida.processor.imagegenerate.ImageGenerateResult;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileImageGenerator {

    private final ProfileImageGeneratePrompt profileImageGeneratePrompt;
    private final ImageGenerateProcessor imageGenerateProcessor;

    public ImageMetaData generate(ProfileImageAppearance appearance) {
        Keywords keywords = appearance.toKeywords();
        String prompt = profileImageGeneratePrompt.create(keywords);

        ImageGenerateResult result = imageGenerateProcessor.proceed(prompt, 1);

        return ImageMetaData.withRandomUuidFilename(
                result.getUrl(),
                ImageType.PROFILE
        );
    }
}
