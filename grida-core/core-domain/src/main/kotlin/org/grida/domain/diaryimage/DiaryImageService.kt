package org.grida.domain.diaryimage

import org.grida.domain.base.AccessManager
import org.grida.domain.diary.DiaryRepository
import org.grida.domain.image.Image
import org.grida.domain.image.ImageStatus
import org.grida.domain.user.UserRepository
import org.grida.error.ActivateImageAlreadyExists
import org.grida.error.GridaException
import org.grida.error.ImageGenerateAttemptOver
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DiaryImageService(
    private val diaryImageRepository: DiaryImageRepository,
    private val diaryRepository: DiaryRepository,
    private val userRepository: UserRepository,
    private val accessManager: AccessManager,
) {

    @Transactional
    fun generateDiaryImage(
        diaryId: Long,
        userId: Long,
        imageUrl: String,
    ): Long {
        val generatedImagesCount = diaryImageRepository.countByDiaryId(diaryId)
        if (DiaryImage.canGenerateNew(generatedImagesCount)) {
            throw GridaException(ImageGenerateAttemptOver)
        }

        val diary = diaryRepository.findById(diaryId)
        accessManager.ownerOnly(diary, userId)

        val diaryImage = DiaryImage(
            userId = userId,
            diaryId = diaryId,
            image = Image(
                url = imageUrl,
                status = ImageStatus.DEACTIVATE
            )
        )
        val user = userRepository.findById(userId)
        return diaryImageRepository.save(diaryImage, diary, user)
    }

    fun countRemainImageGenerateAttempt(diaryId: Long): Long {
        val generatedImagesCount = diaryImageRepository.countByDiaryId(diaryId)
        return DiaryImage.calculateRemainingAttemptCount(generatedImagesCount)
    }

    @Transactional
    fun applyDiaryImage(
        diaryImageId: Long,
        diaryId: Long,
        userId: Long,
    ) {
        if (diaryImageRepository.existsByDiaryIdAndStatus(diaryId, ImageStatus.ACTIVATE)) {
            throw GridaException(ActivateImageAlreadyExists)
        }

        val diaryImage = diaryImageRepository.findById(diaryImageId)
        accessManager.ownerOnly(diaryImage, userId)
        diaryImageRepository.updateStatus(diaryImageId, ImageStatus.ACTIVATE)
    }

    @Transactional
    fun changeDiaryImage(
        diaryImageId: Long,
        diaryId: Long,
        userId: Long,
    ) {
        val originalDiaryImage = diaryImageRepository.findByDiaryIdAndStatus(diaryId, ImageStatus.ACTIVATE)
        diaryImageRepository.updateStatus(originalDiaryImage.id, ImageStatus.DEACTIVATE)

        val newDiaryImage = diaryImageRepository.findById(diaryImageId)
        accessManager.ownerOnly(newDiaryImage, userId)
        diaryImageRepository.updateStatus(diaryImageId, ImageStatus.ACTIVATE)
    }
}
