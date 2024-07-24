package org.grida.domain.profileimage

import org.grida.domain.image.ImageStatus
import org.grida.error.AccessFailed
import org.grida.error.ActivateProfileImageAlreadyExists
import org.grida.error.GridaException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProfileImageValidator(
    private val profileImageRepository: ProfileImageRepository
) {

    @Transactional
    fun validateIsOwner(
        profileImageId: Long,
        userId: Long
    ) {
        val profileImage = profileImageRepository.findById(profileImageId)
        if (profileImage.userId != userId) {
            throw GridaException(AccessFailed)
        }
    }

    @Transactional
    fun validateAlreadyHasActivateProfileImage(userId: Long) {
        if (profileImageRepository.existsByUserIdAndStatus(userId, ImageStatus.ACTIVATE)) {
            throw GridaException(ActivateProfileImageAlreadyExists)
        }
    }
}
