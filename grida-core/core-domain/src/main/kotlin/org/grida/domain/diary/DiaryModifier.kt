package org.grida.domain.diary

import org.grida.domain.base.AccessManager
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DiaryModifier(
    private val diaryRepository: DiaryRepository,
    private val diaryReader: DiaryReader,
    private val accessManager: AccessManager
) {

    @Transactional
    fun modify(
        diaryId: Long,
        userId: Long,
        content: String,
        scope: DiaryScope
    ) {
        val diary = diaryReader.read(diaryId)
        accessManager.ownerOnly(diary, userId)

        diaryRepository.updateContent(diaryId, content)
        diaryRepository.updateScope(diaryId, scope)
    }

    @Transactional
    fun modifyScope(
        diaryId: Long,
        userId: Long,
        scope: DiaryScope
    ) {
        val diary = diaryReader.read(diaryId)
        accessManager.ownerOnly(diary, userId)

        diaryRepository.updateScope(diaryId, scope)
    }
}