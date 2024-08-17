package org.grida.persistence.diary

import org.grida.domain.diary.Diary
import org.grida.domain.user.User
import org.grida.persistence.user.toEntity

fun Diary.toEntity(user: User): DiaryEntity {
    return DiaryEntity(
        id = this.id,
        targetDate = this.targetDate,
        content = this.content,
        scope = this.scope,
        user = user.toEntity()
    )
}

fun DiaryEntity.toDomain(): Diary {
    return Diary(
        id = this.id,
        timestamp = this.toTimeStamp(),
        targetDate = this.targetDate,
        content = this.content,
        scope = this.scope,
        userId = this.user.id
    )
}
