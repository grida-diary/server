package org.grida.provider.profileimage

import org.grida.model.chat.ChatModel
import org.grida.model.image.ImageModel
import org.springframework.stereotype.Component

@Component
class ProfileImageGenerator(
    private val chatModel: ChatModel,
    private val imageModel: ImageModel,
    private val promptBuilder: ProfileImagePromptBuilder,
) {

    fun generate(key: ProfileImageKey): String {
        val prompt = promptBuilder.build(key)
        val result = chatModel.call(prompt, GeneratedPrompt::class.java)

        return imageModel.call(result.value)
    }
}
