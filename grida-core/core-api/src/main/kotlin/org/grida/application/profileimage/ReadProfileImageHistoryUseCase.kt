package org.grida.application.profileimage

import org.grida.datetime.DateTimeUtil
import org.grida.domain.profileimage.ProfileImageService
import org.springframework.stereotype.Component

@Component
class ReadProfileImageHistoryUseCase(
    private val profileImageService: ProfileImageService
) {

    fun execute(userId: Long): ProfileImageHistoryResponse {
        val profileImages = profileImageService.readProfileImageHistory(userId)

        return ProfileImageHistoryResponse(
            count = profileImages.size,
            profileImages = profileImages.map {
                ProfileImageResponse(
                    profileImageId = it.id,
                    imageUrl = it.image.url,
                    status = it.image.status.name,
                    createdAt = DateTimeUtil.toDefaultDateTimeFormat(it.image.timestamp.createdAt),
                    appearance = it.appearance
                )
            }
        )
    }
}
