package org.grida.domain.profileimage

import org.grida.domain.base.AccessManager
import org.grida.domain.image.Image
import org.grida.domain.image.ImageStatus
import org.grida.domain.user.UserRepository
import org.grida.error.ActivateImageAlreadyExists
import org.grida.error.GridaException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProfileImageService(
    private val profileImageRepository: ProfileImageRepository,
    private val userRepository: UserRepository,
    private val accessManager: AccessManager
) {

    @Transactional
    fun append(
        userId: Long,
        imageUrl: String,
        appearance: String
    ): Long {
        val profileImage = ProfileImage(
            userId = userId,
            image = Image(
                url = imageUrl,
                status = ImageStatus.DEACTIVATE
            ),
            appearance = appearance
        )
        val targetUser = userRepository.findById(userId)
        return profileImageRepository.save(profileImage, targetUser)
    }

    fun readActivateProfileImage(userId: Long): ProfileImage {
        return profileImageRepository.findByUserIdAndStatus(userId, ImageStatus.ACTIVATE)
    }

    fun readProfileImageHistory(userId: Long): List<ProfileImage> {
        return profileImageRepository.findAllByUserId(userId)
    }

    @Transactional
    fun applyProfileImage(
        userId: Long,
        profileImageId: Long
    ) {
        if (profileImageRepository.existsByUserIdAndStatus(userId, ImageStatus.ACTIVATE)) {
            throw GridaException(ActivateImageAlreadyExists)
        }

        val profileImage = profileImageRepository.findById(profileImageId)
        accessManager.ownerOnly(profileImage, userId)

        profileImageRepository.updateStatue(profileImageId, ImageStatus.ACTIVATE)
    }

    @Transactional
    fun changeProfileImage(
        userId: Long,
        profileImageId: Long
    ) {
        val originalProfileImage = profileImageRepository.findByUserIdAndStatus(userId, ImageStatus.ACTIVATE)
        profileImageRepository.updateStatue(originalProfileImage.id, ImageStatus.DEACTIVATE)

        val newProfileImage = profileImageRepository.findById(profileImageId)
        accessManager.ownerOnly(newProfileImage, userId)
        profileImageRepository.updateStatue(profileImageId, ImageStatus.ACTIVATE)
    }

    fun hasActivateProfileImage(userId: Long): Boolean {
        return profileImageRepository.existsByUserIdAndStatus(userId, ImageStatus.ACTIVATE)
    }
}
