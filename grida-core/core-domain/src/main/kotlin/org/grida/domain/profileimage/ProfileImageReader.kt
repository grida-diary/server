package org.grida.domain.profileimage

import org.grida.domain.image.ImageStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProfileImageReader(
    private val profileImageRepository: ProfileImageRepository
) {

    @Transactional
    fun readActivateProfileImage(userId: Long): ProfileImage {
        return profileImageRepository.findByUserIdAndStatus(userId, ImageStatus.ACTIVATE)
    }
}
