package org.grida.domain.imagetheme

interface ImageThemeRepository {

    fun save(imageTheme: ImageTheme): ImageTheme

    fun findById(id: Long): ImageTheme

    fun findAllByIsDefault(isDefault: Boolean): List<ImageTheme>

    fun findAllByDiaryImage(diaryId: Long): List<ImageTheme>

    fun findAllByUserId(userId: Long): List<ImageTheme>
}
