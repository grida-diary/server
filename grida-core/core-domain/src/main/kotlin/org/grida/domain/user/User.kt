package org.grida.domain.user

import org.grida.domain.base.Occurrence

data class User(
    val id: Long = 0,
    val occurrence: Occurrence = Occurrence(),
    val username: String,
    val password: String,
    val role: Role = Role.USER,
    val nickname: String,
)
