package org.grida.persistence.diaryimage

import org.grida.domain.diary.Diary
import org.grida.domain.diaryimage.DiaryImage
import org.grida.domain.diaryimage.DiaryImageRepository
import org.grida.domain.user.User
import org.grida.persistence.diary.DiaryEntity
import org.grida.persistence.user.UserEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class DiaryImageEntityRepository(
    private val diaryImageJpaEntityRepository: DiaryJpaEntityRepository
) : DiaryImageRepository {

    override fun save(
        diaryImage: DiaryImage,
        diary: Diary,
        user: User
    ): Long {
        val diaryEntity = diaryImage.toEntity(
            UserEntity.from(user),
            DiaryEntity.from(diary, user)
        )
        diaryImageJpaEntityRepository.save(diaryEntity)
        return diaryEntity.id
    }
}