package org.grida.domain.diaryimage

import org.grida.domain.image.ImageStatus
import org.grida.error.ActivateImageAlreadyExists
import org.grida.error.GridaException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DiaryImageValidator(
    private val diaryImageRepository: DiaryImageRepository
) {

    @Transactional(readOnly = true)
    fun validateAlreadyHasActivateDiaryImage(diaryId: Long) {
        if (diaryImageRepository.existsByDiaryIdAndStatus(diaryId, ImageStatus.ACTIVATE)) {
            throw GridaException(ActivateImageAlreadyExists)
        }
    }
}
