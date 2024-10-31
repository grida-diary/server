package org.grida.presentation.v1.diary

data class DiaryModifyRequest(
    val content: String,
    val scope: String
)
