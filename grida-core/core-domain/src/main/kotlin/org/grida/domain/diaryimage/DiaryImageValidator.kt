package org.grida.domain.diaryimage

import org.grida.domain.image.ImageStatus
import org.grida.error.ActivateImageAlreadyExists
import org.grida.error.GridaException
import org.grida.error.ImageGenerateAttemptOver
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DiaryImageValidator(
    private val diaryImageRepository: DiaryImageRepository,
    private val diaryImageReader: DiaryImageReader
) {

    @Transactional(readOnly = true)
    fun validateAlreadyHasActivateDiaryImage(diaryId: Long) {
        if (diaryImageRepository.existsByDiaryIdAndStatus(diaryId, ImageStatus.ACTIVATE)) {
            throw GridaException(ActivateImageAlreadyExists)
        }
    }

    @Transactional(readOnly = true)
    fun validateRemainingAttemptCount(diaryId: Long) {
        val generatedImagesCount = diaryImageReader.countGeneratedImages(diaryId)
        if (generatedImagesCount >= DiaryImage.IMAGE_GENERATE_MAX_ATTEMPT_COUNT) {
            throw GridaException(ImageGenerateAttemptOver)
        }
    }
}
