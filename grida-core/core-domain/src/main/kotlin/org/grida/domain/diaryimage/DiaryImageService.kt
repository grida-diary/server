package org.grida.domain.diaryimage

import org.grida.domain.base.AccessManager
import org.grida.domain.diary.DiaryReader
import org.grida.domain.image.Image
import org.grida.domain.image.ImageStatus
import org.springframework.stereotype.Service

@Service
class DiaryImageService(
    private val diaryImageAppender: DiaryImageAppender,
    private val diaryImageGenerator: DiaryImageGenerator,
    private val diaryImageModifier: DiaryImageModifier,
    private val diaryImageValidator: DiaryImageValidator,
    private val diaryReader: DiaryReader,
    private val accessManager: AccessManager
) {

    fun generateDiaryImage(
        diaryId: Long,
        userId: Long
    ): Long {
        diaryImageValidator.validateRemainingAttemptCount(diaryId)

        val diary = diaryReader.read(diaryId)
        accessManager.ownerOnly(diary, userId)

        val generatedImageUrl = diaryImageGenerator.generate(diary.content)
        val diaryImage = DiaryImage(
            userId = userId,
            diaryId = diaryId,
            image = Image(generatedImageUrl, ImageStatus.DEACTIVATE)
        )

        return diaryImageAppender.append(diaryImage, diaryId, userId)
    }

    fun applyDiaryImage(
        diaryImageId: Long,
        diaryId: Long,
        userId: Long
    ) {
        diaryImageValidator.validateAlreadyHasActivateDiaryImage(diaryId)
        diaryImageModifier.modifyAsActivate(diaryImageId, userId)
    }
}