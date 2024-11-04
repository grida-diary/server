package org.grida.provider.diaryimage

import org.grida.model.chat.ChatModel
import org.springframework.stereotype.Component

@Component
class DiaryAnalyzer(
    private val chatModel: ChatModel,
    private val promptBuilder: DiaryAnalyzePromptBuilder
) {

    fun analyze(key: DiaryImageKey): AnalyzedDiary {
        val prompt = promptBuilder.build(key)
        val result = chatModel.call(prompt, AnalyzedDiary::class.java)
        return result
    }
}
