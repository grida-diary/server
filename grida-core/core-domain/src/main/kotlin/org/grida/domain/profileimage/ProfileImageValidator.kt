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

    fun validateIsOwner(
        profileImage: ProfileImage,
        userId: Long
    ) {
        if (profileImage.userId != userId) {
            throw GridaException(AccessFailed)
        }
    }

    @Transactional(readOnly = true)
    fun validateAlreadyHasActivateProfileImage(userId: Long) {
        if (profileImageRepository.existsByUserIdAndStatus(userId, ImageStatus.ACTIVATE)) {
            throw GridaException(ActivateProfileImageAlreadyExists)
        }
    }
}
