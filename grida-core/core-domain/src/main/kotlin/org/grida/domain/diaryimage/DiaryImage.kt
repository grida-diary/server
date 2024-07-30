package org.grida.domain.diaryimage

import org.grida.domain.image.Image

data class DiaryImage(
    val id: Long = 0,
    val userId: Long,
    val diaryId: Long,
    val image: Image
)
