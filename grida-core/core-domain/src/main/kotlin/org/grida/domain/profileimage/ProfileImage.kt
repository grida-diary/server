package org.grida.domain.profileimage

import org.grida.domain.image.Image

data class ProfileImage(
    val id: Long = 0,
    val userId: Long,
    val image: Image,
    val appearance: Appearance
) {
    fun isOwner(accessorId: Long): Boolean {
        return userId == accessorId
    }

    fun activate(): ProfileImage {
        return ProfileImage(id, userId, image.activate(), appearance)
    }

    fun deactivate(): ProfileImage {
        return ProfileImage(id, userId, image.deactivate(), appearance)
    }
}
