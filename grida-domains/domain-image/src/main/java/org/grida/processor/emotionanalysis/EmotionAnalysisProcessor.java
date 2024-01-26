package org.grida.processor.emotionanalysis;

public interface EmotionAnalysisProcessor {

    EmotionAnalysisResult proceed(String prompt);
}
