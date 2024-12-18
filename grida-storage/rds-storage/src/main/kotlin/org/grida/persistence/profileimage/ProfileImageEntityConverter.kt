package org.grida.persistence.profileimage

import org.grida.domain.image.Image
import org.grida.domain.profileimage.ProfileImage
import org.grida.domain.user.User
import org.grida.persistence.image.ImageEntity
import org.grida.persistence.user.toEntity

fun ProfileImage.toEntity(user: User, image: ImageEntity): ProfileImageEntity {
    return ProfileImageEntity(
        image = image,
        appearance = appearance,
        user = user.toEntity()
    )
}

fun ProfileImageEntity.toDomain(): ProfileImage {
    return ProfileImage(
        id = this.id,
        userId = this.user.id,
        image = Image(
            url = this.image.url,
            status = this.image.status,
            timestamp = this.toTimeStamp()
        ),
        appearance = appearance
    )
}
