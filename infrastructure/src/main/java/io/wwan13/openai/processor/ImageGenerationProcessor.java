package io.wwan13.openai.processor;

import io.wwan13.annotation.Processor;
import io.wwan13.openai.image.OpenAiImageClient;
import io.wwan13.openai.image.dto.ImageGenerateRequestDto;
import io.wwan13.openai.image.dto.ImageGenerateResponseDto;
import io.wwan13.openai.model.MemberProfile;
import io.wwan13.openai.model.ProcessResult;
import io.wwan13.openai.util.ImageGenerationUtil;
import lombok.RequiredArgsConstructor;

@Processor
@RequiredArgsConstructor
public class ImageGenerationProcessor {

    private final OpenAiImageClient imageClient;
    private final ImageGenerationUtil imageGenerationUtil;

    public String proceed(MemberProfile memberProfile, ProcessResult processResult) {
        String prompt = imageGenerationUtil.createPrompt(memberProfile, processResult);
        ImageGenerateRequestDto request = ImageGenerateRequestDto.prompt(prompt);

        ImageGenerateResponseDto response = imageClient.generateImage(request);
        return imageGenerationUtil.getResult(response);
    }
}
