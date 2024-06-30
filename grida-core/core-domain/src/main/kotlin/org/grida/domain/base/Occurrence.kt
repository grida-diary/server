package org.grida.domain.base

import java.time.LocalDateTime

data class Occurrence(
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val lastModifiedAt: LocalDateTime = LocalDateTime.now(),
)
