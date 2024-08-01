package org.grida.domain.profileimage

import org.grida.domain.base.Ownable
import org.grida.domain.image.Image

data class ProfileImage(
    val id: Long = 0,
    val userId: Long,
    val image: Image,
    val appearance: Appearance
) : Ownable {

    override fun isOwner(accessorId: Long): Boolean {
        return userId == accessorId
    }
}
