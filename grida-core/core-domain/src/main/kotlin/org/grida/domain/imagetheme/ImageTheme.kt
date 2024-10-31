package org.grida.domain.imagetheme

import org.grida.domain.base.Timestamp

data class ImageTheme(
    val id: Long = 0,
    val value: String,
    val timestamp: Timestamp
)
