package org.grida.presentation.v1.diary.dto

data class DiaryModifyRequest(
    val content: String,
    val scope: String
)
