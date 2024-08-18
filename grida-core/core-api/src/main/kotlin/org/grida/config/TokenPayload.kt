package org.grida.config

import io.wwan13.wintersecurity.jwt.payload.annotation.Payload
import io.wwan13.wintersecurity.jwt.payload.annotation.Roles
import io.wwan13.wintersecurity.jwt.payload.annotation.Subject
import org.grida.domain.user.Role

@Payload
data class TokenPayload(
    @Subject
    val id: Long,

    @Roles
    val role: Role
)
