package org.grida.domain.diaryimage

import org.grida.domain.diary.DiaryReader
import org.grida.domain.diary.DiaryValidator
import org.grida.domain.image.Image
import org.grida.domain.image.ImageStatus
import org.springframework.stereotype.Service

@Service
class DiaryImageService(
    private val diaryImageAppender: DiaryImageAppender,
    private val diaryImageGenerator: DiaryImageGenerator,
    private val diaryReader: DiaryReader,
    private val diaryValidator: DiaryValidator
) {

    fun generateDiaryImage(
        diaryId: Long,
        userId: Long
    ): Long {
        val diary = diaryReader.read(diaryId)
        diaryValidator.validateIsOwner(diary, userId)
        val generatedImageUrl = diaryImageGenerator.generate(diary.content)

        val diaryImage = DiaryImage(
            userId = userId,
            diaryId = diaryId,
            image = Image(generatedImageUrl, ImageStatus.DEACTIVATE)
        )
        return diaryImageAppender.append(diaryImage, diaryId, userId)
    }
}