package org.grida.persistence.profileimage

import org.grida.domain.image.ImageStatus
import org.grida.domain.profileimage.ProfileImage
import org.grida.domain.profileimage.ProfileImageRepository
import org.grida.domain.user.User
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class ProfileImageEntityRepository(
    private val profileImageJpaEntityRepository: ProfileImageJpaEntityRepository
) : ProfileImageRepository {

    @Transactional
    override fun save(
        profileImage: ProfileImage,
        user: User
    ): Long {
        val profileImageEntity = profileImage.toEntity(user)
        profileImageJpaEntityRepository.save(profileImageEntity)
        return profileImageEntity.id
    }

    override fun findById(id: Long): ProfileImage {
        val profileImageEntity = profileImageJpaEntityRepository.findByIdOrException(id)
        return profileImageEntity.toDomain()
    }

    override fun findByUserIdAndStatus(userId: Long, status: ImageStatus): ProfileImage {
        val profileImageEntity =
            profileImageJpaEntityRepository.findByUserIdAndStatusOrException(userId, status)
        return profileImageEntity.toDomain()
    }

    override fun findAllByUserId(userId: Long): List<ProfileImage> {
        val profileImageEntities = profileImageJpaEntityRepository.findAllByUserId(userId)
        return profileImageEntities.map { it.toDomain() }
    }

    override fun existsByUserIdAndStatus(userId: Long, status: ImageStatus): Boolean {
        return profileImageJpaEntityRepository.existsByUserIdAndStatus(userId, status)
    }

    @Transactional
    override fun updateStatue(
        profileImageId: Long,
        status: ImageStatus
    ): Long {
        val profileImageEntity = profileImageJpaEntityRepository.findByIdOrException(profileImageId)
        profileImageEntity.updateStatue(status)
        return profileImageEntity.id
    }
}
