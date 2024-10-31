package org.grida.domain.image

import org.grida.domain.base.Timestamp

data class Image(
    val id: Long = 0,
    val url: String,
    val status: ImageStatus = ImageStatus.ACTIVATE,
    val timestamp: Timestamp = Timestamp()
)
