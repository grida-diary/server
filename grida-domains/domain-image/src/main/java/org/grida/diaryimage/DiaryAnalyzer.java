package org.grida.diaryimage;

import lombok.RequiredArgsConstructor;
import org.grida.processor.diaryanalyze.DiaryAnalyzeProcessor;
import org.grida.processor.diaryanalyze.DiaryAnalyzeResult;
import org.grida.prompt.PromptKeywords;
import org.grida.prompt.PromptProvider;
import org.springframework.stereotype.Component;

import static org.grida.prompt.Prompt.DIARY_ANALYSIS;

@Component
@RequiredArgsConstructor
public class DiaryAnalyzer {

    private final PromptProvider promptProvider;
    private final DiaryAnalyzeProcessor diaryAnalyzeProcessor;

    public DiaryAnalyzeResult analyze(PromptKeywords keywords) {
        String prompt = promptProvider.provide(DIARY_ANALYSIS, keywords);
        return diaryAnalyzeProcessor.proceed(prompt);
    }
}
