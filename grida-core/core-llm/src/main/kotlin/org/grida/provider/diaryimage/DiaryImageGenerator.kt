package org.grida.provider.diaryimage

import org.grida.model.image.ImageModel
import org.springframework.stereotype.Component

@Component
class DiaryImageGenerator(
    private val imageModel: ImageModel
) {

    fun generate(prompt: String): String {
        return imageModel.call(prompt)
    }
}
