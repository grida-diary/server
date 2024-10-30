package org.grida.domain.profileimage

import org.grida.domain.image.ImageStatus
import org.grida.error.GridaException
import org.grida.error.NoSuchData
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProfileImageReader(
    private val profileImageRepository: ProfileImageRepository
) {

    @Transactional(readOnly = true)
    fun read(profileImageId: Long): ProfileImage {
        return profileImageRepository.findById(profileImageId)
            ?: throw GridaException(NoSuchData(ProfileImage::class, profileImageId))
    }

    @Transactional(readOnly = true)
    fun readActivateProfileImage(userId: Long): ProfileImage {
        return profileImageRepository.findByUserIdAndStatus(userId, ImageStatus.ACTIVATE)
            ?: throw GridaException(NoSuchData(ProfileImage::class, userId))
    }

    @Transactional(readOnly = true)
    fun hasActivateProfileImage(userId: Long): Boolean {
        return profileImageRepository.existsByUserIdAndStatus(userId, ImageStatus.ACTIVATE)
    }

    @Transactional(readOnly = true)
    fun readAllProfileImages(userId: Long): List<ProfileImage> {
        return profileImageRepository.findAllByUserId(userId)
    }
}
