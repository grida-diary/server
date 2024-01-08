package io.wwan13.imagegenerate.generator;

import io.wwan13.imagegenerate.processor.image.ImageProcessResult;
import io.wwan13.imagegenerate.processor.image.ImageProcessor;
import io.wwan13.imagegenerate.processor.naturallanguage.NaturalLanguageProcessResult;
import io.wwan13.imagegenerate.processor.naturallanguage.NaturalLanguageProcessor;
import io.wwan13.imagegenerate.prompt.diaryimagegenerate.DiaryImageGenerateKeywords;
import io.wwan13.imagegenerate.prompt.diaryimagegenerate.DiaryImageGeneratePrompt;
import io.wwan13.imagegenerate.prompt.naturallanguageprocess.NaturalLanguageProcessKeywords;
import io.wwan13.imagegenerate.prompt.naturallanguageprocess.NaturalLanguageProcessPrompt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
public class DiaryImageGenerator {

    private static final int IMAGE_AMOUNT = 1;

    private final NaturalLanguageProcessPrompt naturalLanguageProcessPrompt;
    private final DiaryImageGeneratePrompt diaryImageGeneratePrompt;
    private final NaturalLanguageProcessor naturalLanguageProcessor;
    private final ImageProcessor imageProcessor;

    public String generate(String diary, String gender, int age) {
        NaturalLanguageProcessResult naturalLanguageProcessResult = analyzeEmotionAndBehavior(diary);
        ImageProcessResult imageProcessResult = createDiaryImage(naturalLanguageProcessResult, gender, age);

        return imageProcessResult.getUrl();
    }

    private NaturalLanguageProcessResult analyzeEmotionAndBehavior(String diary) {
        NaturalLanguageProcessKeywords keywords = NaturalLanguageProcessKeywords.builder()
                .diary(diary)
                .build();

        return naturalLanguageProcessor.proceed(naturalLanguageProcessPrompt.create(keywords));
    }

    private ImageProcessResult createDiaryImage(NaturalLanguageProcessResult naturalLanguageProcessResult,
                                                String gender,
                                                int age) {
        DiaryImageGenerateKeywords keywords = DiaryImageGenerateKeywords.builder()
                .emotion(naturalLanguageProcessResult.getEmotion())
                .behavior(naturalLanguageProcessResult.getBehavior())
                .gender(gender)
                .age(age)
                .build();

        return imageProcessor.proceed(diaryImageGeneratePrompt.create(keywords), IMAGE_AMOUNT);
    }
}
