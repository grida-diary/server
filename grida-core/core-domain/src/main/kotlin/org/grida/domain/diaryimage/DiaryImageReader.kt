package org.grida.domain.diaryimage

import org.grida.error.GridaException
import org.grida.error.NoSuchData
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DiaryImageReader(
    private val diaryImageRepository: DiaryImageRepository
) {

    @Transactional(readOnly = true)
    fun read(diaryImageId: Long): DiaryImage {
        return diaryImageRepository.findById(diaryImageId)
            ?: throw GridaException(NoSuchData(DiaryImage::class, diaryImageId))
    }

    @Transactional(readOnly = true)
    fun countGeneratedImages(diaryId: Long): Long {
        return diaryImageRepository.countByDiaryId(diaryId)
    }
}
