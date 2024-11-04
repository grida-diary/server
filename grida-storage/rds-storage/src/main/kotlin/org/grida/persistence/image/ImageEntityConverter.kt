package org.grida.persistence.image

import org.grida.domain.image.Image

fun Image.toEntity(): ImageEntity {
    return ImageEntity(
        url = this.url,
        status = this.status
    )
}

fun ImageEntity.toDomain(): Image {
    return Image(
        id = this.id,
        url = this.url,
        status = this.status,
        timestamp = this.toTimeStamp()
    )
}
