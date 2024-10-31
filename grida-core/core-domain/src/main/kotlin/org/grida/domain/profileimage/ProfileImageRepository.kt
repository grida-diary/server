package org.grida.domain.profileimage

import org.grida.domain.image.ImageStatus
import org.grida.domain.user.User

interface ProfileImageRepository {

    fun save(profileImage: ProfileImage, user: User): Long

    fun findById(id: Long): ProfileImage

    fun findByUserIdAndStatus(userId: Long, status: ImageStatus): ProfileImage

    fun findAllByUserId(userId: Long): List<ProfileImage>

    fun existsByUserIdAndStatus(userId: Long, status: ImageStatus): Boolean

    fun updateStatue(profileImageId: Long, status: ImageStatus): Long
}
