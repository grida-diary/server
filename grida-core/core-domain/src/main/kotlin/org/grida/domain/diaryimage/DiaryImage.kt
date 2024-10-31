package org.grida.domain.diaryimage

import org.grida.domain.base.Ownable
import org.grida.domain.image.Image

data class DiaryImage(
    val id: Long = 0,
    val userId: Long,
    val diaryId: Long,
    val image: Image
) : Ownable {

    override fun isOwner(accessorId: Long): Boolean {
        return userId == accessorId
    }

    companion object {
        private const val IMAGE_GENERATE_MAX_ATTEMPT_COUNT = 3L

        fun calculateRemainingAttemptCount(generatedCount: Long): Long {
            return IMAGE_GENERATE_MAX_ATTEMPT_COUNT - generatedCount
        }

        fun canGenerateNew(generatedCount: Long): Boolean {
            return generatedCount >= IMAGE_GENERATE_MAX_ATTEMPT_COUNT
        }
    }
}
