package org.grida.domain.diary

import org.grida.domain.base.ValueEnum

enum class DiaryScope(
    override val value: String
) : ValueEnum<DiaryScope> {
    PUBLIC("공개"),
    FRIENDS_ONLY("친구 공개"),
    PRIVATE("비공개");
}
