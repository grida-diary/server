package org.grida.domain.profileimage

import org.grida.domain.image.ImageStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProfileImageModifier(
    private val profileImageRepository: ProfileImageRepository,
    private val profileImageReader: ProfileImageReader,
    private val profileImageValidator: ProfileImageValidator
) {

    @Transactional
    fun modifyAsActivate(
        userId: Long,
        profileImageId: Long
    ) {
        profileImageValidator.validateAlreadyHasActivateProfileImage(userId)
        val profileImage = profileImageReader.read(profileImageId)
        profileImageValidator.validateIsOwner(profileImage, userId)

        profileImageRepository.updateStatus(profileImageId, ImageStatus.ACTIVATE)
    }

    @Transactional
    fun modifyOriginalProfileImageAsDeactivate(userId: Long) {
        val originalProfileImage = profileImageReader.readActivateProfileImage(userId)

        profileImageRepository.updateStatus(originalProfileImage.id, ImageStatus.DEACTIVATE)
    }
}
