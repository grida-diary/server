package org.grida.persistence.diaryimage

import org.grida.domain.diaryimage.DiaryImage
import org.grida.domain.image.Image
import org.grida.persistence.diary.DiaryEntity
import org.grida.persistence.user.UserEntity

fun DiaryImage.toEntity(
    userEntity: UserEntity,
    diaryEntity: DiaryEntity
): DiaryImageEntity {
    return DiaryImageEntity(
        id = this.id,
        imageUrl = this.image.url,
        status = this.image.status,
        user = userEntity,
        diary = diaryEntity
    )
}

fun DiaryImageEntity.toDomain(): DiaryImage {
    return DiaryImage(
        id = this.id,
        userId = this.user.id,
        diaryId = this.diary.id,
        image = Image(
            url = imageUrl,
            status = this.status,
            timestamp = this.toTimeStamp()
        )
    )
}