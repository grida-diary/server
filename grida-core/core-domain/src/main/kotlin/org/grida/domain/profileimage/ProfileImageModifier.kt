package org.grida.domain.profileimage

import org.grida.domain.base.AccessManager
import org.grida.domain.image.ImageStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProfileImageModifier(
    private val profileImageRepository: ProfileImageRepository,
    private val profileImageReader: ProfileImageReader,
    private val accessManager: AccessManager
) {

    @Transactional
    fun modifyAsActivate(
        userId: Long,
        profileImageId: Long
    ) {
        val profileImage = profileImageReader.read(profileImageId)
        accessManager.ownerOnly(profileImage, userId)

        profileImageRepository.updateStatue(profileImageId, ImageStatus.ACTIVATE)
    }

    @Transactional
    fun modifyOriginalProfileImageAsDeactivate(userId: Long) {
        val originalProfileImage = profileImageReader.readActivateProfileImage(userId)

        profileImageRepository.updateStatue(originalProfileImage.id, ImageStatus.DEACTIVATE)
    }
}
