package org.grida.diaryimage;

import lombok.RequiredArgsConstructor;
import org.grida.image.ImageMetaData;
import org.grida.image.ImageType;
import org.grida.processor.diaryanalyze.DiaryAnalyzeResult;
import org.grida.processor.imagegenerate.ImageGenerateProcessor;
import org.grida.processor.imagegenerate.ImageGenerateResult;
import org.grida.prompt.PromptKeywords;
import org.grida.prompt.PromptProvider;
import org.springframework.stereotype.Component;

import static org.grida.prompt.Prompt.GENERATE_DIARY_IMAGE;

@Component
@RequiredArgsConstructor
public class DiaryImageGenerator {

    private static final int DEFAULT_IMAGE_CREATE_COUNT = 1;

    private final PromptProvider promptProvider;
    private final DiaryAnalyzer diaryAnalyzer;
    private final ImageGenerateProcessor imageGenerateProcessor;

    public ImageMetaData generate(DiaryImageGenerateKey key) {
        DiaryAnalyzeResult diaryAnalyzeResult = diaryAnalyzer.analyze(key.toDiaryAnalyzePromptKeywords());

        PromptKeywords keywords = key.toDiaryImageGeneratePromptKeywords(diaryAnalyzeResult.result());
        String prompt = promptProvider.provide(GENERATE_DIARY_IMAGE, keywords);
        ImageGenerateResult result = imageGenerateProcessor.proceed(prompt, DEFAULT_IMAGE_CREATE_COUNT);

        return ImageMetaData.withRandomUuidFilename(
                result.getUrl(),
                ImageType.DIARY
        );
    }
}
