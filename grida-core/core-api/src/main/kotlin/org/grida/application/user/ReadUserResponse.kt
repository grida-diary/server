package org.grida.application.user

data class ReadUserResponse(
    val userId: Long,
    val nickname: String,
    val gender: String,
    val age: Int,
    val theme: String
)
