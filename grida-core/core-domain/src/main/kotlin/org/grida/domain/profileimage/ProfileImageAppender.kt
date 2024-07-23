package org.grida.domain.profileimage

import org.grida.domain.image.Image
import org.grida.domain.image.ImageStatus
import org.grida.domain.user.UserReader
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProfileImageAppender(
    private val profileImageRepository: ProfileImageRepository,
    private val userReader: UserReader
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
        val targetUser = userReader.read(userId)
        return profileImageRepository.save(profileImage, targetUser)
    }
}
