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
        val profileImage = profileImageReader.read(profileImageId)
        profileImageValidator.validateIsOwner(profileImage, userId)

        profileImageRepository.update(profileImage.activate())
    }

    @Transactional
    fun modifyOriginalProfileImageAsDeactivate(userId: Long) {
        val originalProfileImage = profileImageReader.readActivateProfileImage(userId)

        profileImageRepository.update(originalProfileImage.deactivate())
    }
}
