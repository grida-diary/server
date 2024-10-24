package org.grida.domain.diaryimage

import org.grida.domain.base.AccessManager
import org.grida.domain.diary.DiaryReader
import org.grida.domain.image.Image
import org.grida.domain.image.ImageStatus
import org.springframework.stereotype.Service

@Service
class DiaryImageService(
    private val diaryImageAppender: DiaryImageAppender,
    private val diaryImageReader: DiaryImageReader,
    private val diaryImageModifier: DiaryImageModifier,
    private val diaryImageValidator: DiaryImageValidator,
    private val diaryReader: DiaryReader,
    private val accessManager: AccessManager
) {

    fun generateDiaryImage(
        diaryId: Long,
        userId: Long,
        imageUrl: String
    ): Long {
        diaryImageValidator.validateRemainingAttemptCount(diaryId)

        val diary = diaryReader.read(diaryId)
        accessManager.ownerOnly(diary, userId)

        val diaryImage = DiaryImage(
            userId = userId,
            diaryId = diaryId,
            image = Image(imageUrl, ImageStatus.DEACTIVATE)
        )

        return diaryImageAppender.append(diaryImage, diaryId, userId)
    }

    fun countRemainImageGenerateAttempt(diaryId: Long): Long {
        val generatedImagesCount = diaryImageReader.countGeneratedImages(diaryId)
        return DiaryImage.IMAGE_GENERATE_MAX_ATTEMPT_COUNT - generatedImagesCount
    }

    fun applyDiaryImage(
        diaryImageId: Long,
        diaryId: Long,
        userId: Long
    ) {
        diaryImageValidator.validateAlreadyHasActivateDiaryImage(diaryId)
        diaryImageModifier.modifyAsActivate(diaryImageId, userId)
    }

    fun changeDiaryImage(
        diaryImageId: Long,
        diaryId: Long,
        userId: Long
    ) {
        diaryImageModifier.modifyOriginalDiaryImageAsDeactivate(diaryId, userId)
        diaryImageModifier.modifyAsActivate(diaryImageId, userId)
    }
}
