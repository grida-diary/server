package org.grida.presentation.v1.profileimage

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
                    imageId = it.id,
                    imageUrl = it.image.url,
                    status = it.image.status.name,
                    createdAt = DateTimeUtil.toDefaultDateTimeFormat(it.image.timestamp.createdAt),
                    gender = it.appearance.gender.name,
                    age = it.appearance.age,
                    hairStyle = it.appearance.hairStyle,
                    glasses = it.appearance.glasses,
                    bodyShape = it.appearance.bodyShape
                )
            }
        )
    }
}
