package org.grida.domain.profileimage

import jakarta.transaction.Transactional
import org.grida.domain.image.ImageStatus
import org.grida.exception.AccessFailedException
import org.grida.exception.AtcivateProfileImageAlreadyExistsException
import org.springframework.stereotype.Component

@Component
class ProfileImageModifier(
    private val profileImageReader: ProfileImageReader,
    private val profileImageRepository: ProfileImageRepository
) {

    @Transactional
    fun modifyAsActivate(
        userId: Long,
        profileImageId: Long
    ) {
        if (profileImageRepository.existsByUserIdAndStatus(userId, ImageStatus.ACTIVATE)) {
            throw AtcivateProfileImageAlreadyExistsException()
        }
        validateIsOwner(profileImageId, userId)

        profileImageRepository.updateStatus(profileImageId, ImageStatus.ACTIVATE)
    }

    @Transactional
    fun modifyOriginalProfileImageAsDeactivate(userId: Long) {
        val originalProfileImage = profileImageReader.readActivateProfileImage(userId)
        validateIsOwner(originalProfileImage.id, userId)
        profileImageRepository.updateStatus(originalProfileImage.id, ImageStatus.DEACTIVATE)
    }

    private fun validateIsOwner(profileImageId: Long, userId: Long) {
        val profileImage = profileImageRepository.findById(profileImageId)
        if (profileImage.userId != userId) {
            throw AccessFailedException()
        }
    }
}
