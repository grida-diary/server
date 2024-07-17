package org.grida.domain.base

import java.time.LocalDateTime

data class Timestamp(
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val lastModifiedAt: LocalDateTime = LocalDateTime.now(),
)
