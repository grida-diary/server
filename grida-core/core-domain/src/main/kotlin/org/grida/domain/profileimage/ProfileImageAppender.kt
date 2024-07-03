package org.grida.domain.profileimage

import jakarta.transaction.Transactional
import org.grida.domain.image.Image
import org.grida.domain.image.ImageStatus
import org.grida.domain.user.UserRepository
import org.springframework.stereotype.Component

@Component
class ProfileImageAppender(
    private val profileImageRepository: ProfileImageRepository,
    private val userRepository: UserRepository
) {

    @Transactional
    fun appendAsDeactivate(
        userId: Long,
        imageUrl: String,
        appearance: Appearance,
    ): Long {
        val profileImage = ProfileImage(
            userId = userId,
            image = Image(imageUrl, ImageStatus.DEACTIVATE),
            appearance = appearance
        )
        val targetUser = userRepository.findById(userId)
        return profileImageRepository.save(profileImage, targetUser)
    }
}
