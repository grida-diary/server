package org.grida.domain.profileimage

import org.grida.domain.image.ImageStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProfileImageReader(
    private val profileImageRepository: ProfileImageRepository
) {

    @Transactional(readOnly = true)
    fun readActivateProfileImage(userId: Long): ProfileImage {
        return profileImageRepository.findByUserIdAndStatus(userId, ImageStatus.ACTIVATE)
    }

    @Transactional(readOnly = true)
    fun hasActivateProfileImage(userId: Long): Boolean {
        return profileImageRepository.existsByUserIdAndStatus(userId, ImageStatus.ACTIVATE)
    }
}
