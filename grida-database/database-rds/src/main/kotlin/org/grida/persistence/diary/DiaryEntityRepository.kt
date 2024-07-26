package org.grida.persistence.diary

import org.grida.domain.diary.Diary
import org.grida.domain.diary.DiaryRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class DiaryEntityRepository(
    private val diaryJpaEntityRepository: DiaryJpaEntityRepository
) : DiaryRepository {

    @Transactional
    override fun save(diary: Diary): Long {
        val diaryEntity = diaryJpaEntityRepository.save(DiaryEntity.from(diary))
        return diaryEntity.id
    }
}