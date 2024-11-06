package org.grida.application.diary

data class DiaryModifyRequest(
    val content: String,
    val scope: String
)
