package org.grida.domain.profileimage

import org.grida.domain.image.ImageStatus
import org.springframework.stereotype.Component

@Component
class ProfileImageReader(
    private val profileImageRepository: ProfileImageRepository
) {

    fun readActivateProfileImage(userId: Long): ProfileImage {
        return profileImageRepository.findByUserIdAndStatus(userId, ImageStatus.ACTIVATE)
    }
}