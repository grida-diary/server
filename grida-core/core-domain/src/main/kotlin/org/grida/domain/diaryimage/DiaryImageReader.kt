package org.grida.domain.diaryimage

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DiaryImageReader(
    private val diaryImageRepository: DiaryImageRepository
) {

    @Transactional(readOnly = true)
    fun read(diaryId: Long): DiaryImage {
        return diaryImageRepository.findById(diaryId)
    }
}
