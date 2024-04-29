package org.grida.profileimage;

import lombok.RequiredArgsConstructor;
import org.grida.image.ImageMetaData;
import org.grida.image.ImageType;
import org.grida.processor.imagegenerate.ImageGenerateProcessor;
import org.grida.processor.imagegenerate.ImageGenerateResult;
import org.grida.prompt.PromptProvider;
import org.springframework.stereotype.Component;

import static org.grida.prompt.Prompt.GENERATE_PROFILE_IMAGE;

@Component
@RequiredArgsConstructor
public class ProfileImageGenerator {

    private final PromptProvider promptProvider;
    private final ImageGenerateProcessor imageGenerateProcessor;

    public ImageMetaData generate(ProfileImageGenerateKey key) {
        String prompt = promptProvider.provide(GENERATE_PROFILE_IMAGE, key.toPromptKeyword());

        ImageGenerateResult result = imageGenerateProcessor.proceed(prompt, 1);

        return ImageMetaData.withRandomUuidFilename(
                result.getUrl(),
                ImageType.PROFILE
        );
    }
}
