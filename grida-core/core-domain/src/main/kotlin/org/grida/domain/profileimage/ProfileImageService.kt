package org.grida.domain.profileimage

import org.springframework.stereotype.Service

@Service
class ProfileImageService(
    private val profileImageAppender: ProfileImageAppender,
    private val profileImageReader: ProfileImageReader,
    private val profileImageModifier: ProfileImageModifier,
    private val profileImageValidator: ProfileImageValidator,
    private val profileImageGenerator: ProfileImageGenerator
) {

    fun generateSampleProfileImage(
        userId: Long,
        appearance: Appearance
    ): Long {
        val imageUrl = profileImageGenerator.generate(appearance)
        return profileImageAppender.appendAsDeactivate(userId, imageUrl, appearance)
    }

    fun readActivateProfileImage(userId: Long): ProfileImage {
        return profileImageReader.readActivateProfileImage(userId)
    }

    fun readProfileImageHistory(userId: Long): List<ProfileImage> {
        return profileImageReader.readAllProfileImages(userId)
    }

    fun applyProfileImage(
        userId: Long,
        profileImageId: Long
    ) {
        profileImageValidator.validateAlreadyHasActivateProfileImage(userId)
        profileImageModifier.modifyAsActivate(userId, profileImageId)
    }

    fun changeProfileImage(
        userId: Long,
        profileImageId: Long
    ) {
        profileImageModifier.modifyOriginalProfileImageAsDeactivate(userId)
        profileImageModifier.modifyAsActivate(userId, profileImageId)
    }

    fun hasActivateProfileImage(userId: Long): Boolean {
        return profileImageReader.hasActivateProfileImage(userId)
    }
}
