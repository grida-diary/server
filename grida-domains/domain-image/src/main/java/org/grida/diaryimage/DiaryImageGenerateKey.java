package org.grida.diaryimage;

import org.grida.prompt.PromptKeywords;

public record DiaryImageGenerateKey(
    String diary,
    String mainCharacterImageUrl
) {

    public PromptKeywords toDiaryAnalyzePromptKeywords() {
        return PromptKeywords.with()
                .keyword("#DIARY#", diary)
                .build();
    }

    public PromptKeywords toDiaryImageGeneratePromptKeywords(String diaryAnalysisResult) {
        return PromptKeywords.with()
                .keyword("#DIARY_ANALYSIS_RESULT#", diaryAnalysisResult)
                .keyword("#MAIN_CHARACTER_IMAGE_URL#", mainCharacterImageUrl)
                .build();
    }
}
