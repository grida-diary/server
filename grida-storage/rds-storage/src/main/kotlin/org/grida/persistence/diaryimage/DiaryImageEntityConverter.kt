package org.grida.persistence.diaryimage

import org.grida.domain.diary.Diary
import org.grida.domain.diaryimage.DiaryImage
import org.grida.domain.user.User
import org.grida.persistence.diary.toEntity
import org.grida.persistence.image.toDomain
import org.grida.persistence.image.toEntity
import org.grida.persistence.user.toEntity

fun DiaryImage.toEntity(
    user: User,
    diary: Diary
): DiaryImageEntity {
    return DiaryImageEntity(
        id = this.id,
        image = this.image.toEntity(),
        user = user.toEntity(),
        diary = diary.toEntity(user)
    )
}

fun DiaryImageEntity.toDomain(): DiaryImage {
    return DiaryImage(
        id = this.id,
        userId = this.user.id,
        diaryId = this.diary.id,
        image = this.image.toDomain()
    )
}
