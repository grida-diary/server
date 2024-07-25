package org.grida.domain.user

import org.grida.domain.base.Timestamp

data class User(
    val id: Long = 0,
    val timestamp: Timestamp = Timestamp(),
    val username: String,
    val password: String,
    val role: Role = Role.ROLE_USER,
    val nickname: String,
)
