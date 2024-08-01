package org.grida.domain.diaryimage

import org.grida.domain.base.AccessManager
import org.grida.domain.image.ImageStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DiaryImageModifier(
    private val diaryImageRepository: DiaryImageRepository,
    private val diaryImageReader: DiaryImageReader,
    private val accessManager: AccessManager
) {

    @Transactional
    fun modifyAsActivate(diaryImageId: Long, userId: Long) {
        val diaryImage = diaryImageReader.read(diaryImageId)
        accessManager.ownerOnly(diaryImage, userId)

        diaryImageRepository.updateStatus(diaryImageId, ImageStatus.ACTIVATE)
    }
}
