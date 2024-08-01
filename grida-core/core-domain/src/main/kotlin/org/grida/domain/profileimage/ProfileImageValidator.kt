package org.grida.domain.profileimage

import org.grida.domain.image.ImageStatus
import org.grida.error.ActivateImageAlreadyExists
import org.grida.error.GridaException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProfileImageValidator(
    private val profileImageRepository: ProfileImageRepository
) {

    @Transactional(readOnly = true)
    fun validateAlreadyHasActivateProfileImage(userId: Long) {
        if (profileImageRepository.existsByUserIdAndStatus(userId, ImageStatus.ACTIVATE)) {
            throw GridaException(ActivateImageAlreadyExists)
        }
    }
}
