package io.wwan13.openai.processor;

import io.wwan13.annotation.Processor;
import io.wwan13.openai.image.OpenAiImageClient;
import io.wwan13.openai.image.dto.ImageGenerateRequestDto;
import io.wwan13.openai.image.dto.ImageGenerateResponseDto;
import io.wwan13.openai.model.MemberProfile;
import io.wwan13.openai.model.ProcessResult;
import io.wwan13.openai.util.ImageGenerationUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Processor
@RequiredArgsConstructor
public class ImageGenerationProcessor {

    private final OpenAiImageClient imageClient;
    private final ImageGenerationUtil imageGenerationUtil;

    public String proceed(MemberProfile memberProfile, ProcessResult processResult) {
        String prompt = imageGenerationUtil.createDiaryPrompt(memberProfile, processResult);
        ImageGenerateResponseDto response = getResponse(prompt, 1);
        return imageGenerationUtil.getResult(response);
    }

    public List<String> proceed(MemberProfile memberProfile) {
        String prompt = imageGenerationUtil.creatProfilePrompt(memberProfile);
        ImageGenerateResponseDto response = getResponse(prompt, 4);
        return imageGenerationUtil.getResults(response);
    }

    private ImageGenerateResponseDto getResponse(String prompt, Integer n) {
        ImageGenerateRequestDto request = new ImageGenerateRequestDto(prompt, n);
        return imageClient.generateImage(request);
    }
}
