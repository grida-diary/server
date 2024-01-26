package org.grida.processor.emotionanalysis;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmotionAnalysisResult {

    private final String emotion;
    private final String behavior;
}
