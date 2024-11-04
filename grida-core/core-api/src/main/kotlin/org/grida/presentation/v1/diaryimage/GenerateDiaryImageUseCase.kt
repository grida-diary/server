package org.grida.presentation.v1.diaryimage

import org.grida.S3Client
import org.grida.api.dto.IdResponse
import org.grida.domain.diaryimage.DiaryImageService
import org.grida.provider.diaryimage.DiaryImageGenerator
import org.springframework.stereotype.Component

@Component
class GenerateDiaryImageUseCase(
    private val diaryImageService: DiaryImageService,
    private val diaryImageGenerator: DiaryImageGenerator,
    private val s3Client: S3Client
) {

    fun execute(
        userId: Long,
        diaryId: Long,
        request: GenerateDiaryImageRequest
    ): IdResponse {
        val diaryImageUrl = diaryImageGenerator.generate(request.prompt)
        val uploadedUrl = s3Client.uploadImage(diaryImageUrl)
        val generatedDiaryImageId = diaryImageService.generateDiaryImage(diaryId, userId, uploadedUrl)
        return IdResponse(generatedDiaryImageId)
    }
}
